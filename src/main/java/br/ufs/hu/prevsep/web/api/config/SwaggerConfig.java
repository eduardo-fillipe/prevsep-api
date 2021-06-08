package br.ufs.hu.prevsep.web.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
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
                        .title("PrevSep+ Web API")
                        .version(version)
                        .description("The RestFull API of the Sepse prevention system, PrevSep. Built in partnership with " +
                                "the Universitary Hospital of the Federal University of Sergipe, HU-UFS.")
                        .license(new License()
                                .name("GNU GPLv3")
                                .url("https://www.gnu.org/licenses/gpl-3.0.en.html"))
                        .contact(new Contact()
                                .name("PrevSep+ Team")
                                .email("ascom.huufs@ebserh.gov.br")
                                .url("https://www.gov.br/ebserh/pt-br/hospitais-universitarios/regiao-nordeste/hu-ufs"))
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
