package br.ufs.hu.prevsep.web.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI(@Value("${prevsep.api.version}") String version,
                                 @Value("${prevsep.security.oauth.authorization.server}") String authorizationServer,
                                 @Value("${prevsep.security.oauth.token.url}") String tokenUrl) {
        return new OpenAPI()
                .info(new Info()
                        .title("PrevSep Web API")
                        .version(version)
                        .description("PrevSep Web API")
                ).schemaRequirement("oauth", oAuthSecuritySchema(authorizationServer, tokenUrl));

    }

    public SecurityScheme oAuthSecuritySchema(String authorizationServer, String tokenURI) {
        return new SecurityScheme()
                .name("oauth")
                .type(SecurityScheme.Type.OAUTH2)
                .in(SecurityScheme.In.HEADER)
                .flows(new OAuthFlows()
                        .clientCredentials(new OAuthFlow()
                                .tokenUrl(authorizationServer + tokenURI)
                        )
                );
    }

}
