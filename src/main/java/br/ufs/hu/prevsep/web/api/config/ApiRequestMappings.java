package br.ufs.hu.prevsep.web.api.config;

public class ApiRequestMappings {
    //Swagger
    public static final String SWAGGER_ENTRYPOINT = "/swagger-ui.html";

    // API Base Endpoints
    public static final String API = "/api";
    public static final String API_VERSION = "/v1";
    public static final String API_BASE_ENDPOINT = API + API_VERSION;

    public static final String USERS = API_BASE_ENDPOINT + "/users";
    public static final String DOCTORS = API_BASE_ENDPOINT + "/doctors";
    public static final String MANAGERS = API_BASE_ENDPOINT + "/managers";
    public static final String NURSES = API_BASE_ENDPOINT + "/nurses";
    public static final String FORMS = API_BASE_ENDPOINT + "/forms";

    public static final String FORMS_SEPSE = FORMS + "/sepse";
}
