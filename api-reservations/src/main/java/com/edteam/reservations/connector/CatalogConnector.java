package com.edteam.reservations.connector;

import com.edteam.reservations.connector.response.CityDTO;
import io.netty.resolver.DefaultAddressResolverGroup;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Component
public class CatalogConnector {

    public CityDTO getCity(String code) {
        WebClient client = WebClient.builder()
                .baseUrl("http://localhost:6070/api/flights/catalog/city/{code}")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .clientConnector(new ReactorClientHttpConnector(HttpClient.create().resolver(DefaultAddressResolverGroup.INSTANCE)))
                .build();

        return client.get()
                .uri(uriBuilder -> uriBuilder.build(code))
                .retrieve()
                .bodyToMono(CityDTO.class)
                .share()
                .block();
    }
}
