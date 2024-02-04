package com.edteam.reservations.connector.configuration;

import java.util.HashMap;

public class HostConfiguration {

    private String host;

    private int port;

    private HashMap<String, EndpointConfiguration> endpoints;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public HashMap<String, EndpointConfiguration> getEndpoints() {
        return endpoints;
    }

    public void setEndpoints(HashMap<String, EndpointConfiguration> endpoints) {
        this.endpoints = endpoints;
    }
}
