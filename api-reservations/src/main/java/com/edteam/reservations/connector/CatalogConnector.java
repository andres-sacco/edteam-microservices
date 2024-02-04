package com.edteam.reservations.connector;

import com.edteam.reservations.connector.configuration.EndpointConfiguration;
import com.edteam.reservations.connector.configuration.HostConfiguration;
import com.edteam.reservations.connector.configuration.HttpConnectorConfiguration;
import com.edteam.reservations.connector.response.CityDTO;
import com.edteam.reservations.enums.APIError;
import com.edteam.reservations.exception.EdteamException;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutException;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import reactor.netty.http.client.HttpClient;

import java.util.concurrent.TimeUnit;

@Component
public class CatalogConnector {

    private final String HOST = "api-catalog";

    private final String ENDPOINT = "get-city";

    private HttpConnectorConfiguration configuration;


    @Autowired
    public CatalogConnector(HttpConnectorConfiguration configuration) {
        this.configuration = configuration;
    }

    @CircuitBreaker(name = "api-catalog", fallbackMethod = "fallbackGetCity")
    public CityDTO getCity(String code) {

        System.out.println("calling to api-catalog");

        HostConfiguration hostConfiguration = configuration.getHosts().get(HOST);
        EndpointConfiguration endpointConfiguration = hostConfiguration.getEndpoints().get(ENDPOINT);

        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, Math.toIntExact(endpointConfiguration.getConnectionTimeout()))
                .doOnConnected(conn -> conn
                        .addHandler(new ReadTimeoutHandler(endpointConfiguration.getReadTimeout(), TimeUnit.MILLISECONDS))
                        .addHandler(new WriteTimeoutHandler(endpointConfiguration.getWriteTimeout(), TimeUnit.MILLISECONDS)));


        WebClient client = WebClient.builder()
                .baseUrl("http://" + hostConfiguration.getHost() + ":" + hostConfiguration.getPort() + endpointConfiguration.getUrl())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();


        return client.get()
                .uri(urlEncoder -> urlEncoder.build(code))
                .retrieve()
                .bodyToMono(CityDTO.class)
                .share()
                .block();
    }

    public CityDTO fallbackGetCity(String code, CallNotPermittedException ex) {
        System.out.println("calling to fallbackGetCity-1");

        return new CityDTO();
    }

    public CityDTO fallbackGetCity(String code, Exception ex) {
        System.out.println("calling to fallbackGetCity-2");

        throw new EdteamException(APIError.VALIDATION_ERROR);
    }
}
