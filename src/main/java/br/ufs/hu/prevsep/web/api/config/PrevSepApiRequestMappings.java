package br.ufs.hu.prevsep.web.api.config;

import org.springframework.beans.factory.annotation.Value;

public class PrevSepApiRequestMappings {
    // API Base Endpoints
    public static final String API = "/api";
    public static final String API_VERSION = "/v1";
    public static final String API_BASE_ENDPOINT = API + API_VERSION;

    // Hello World Endpoints
    public static final String HELLO_WORLD = API_BASE_ENDPOINT + "/hello-world";
}
