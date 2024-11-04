package com.familycard.familycardback.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Value("${familyCard.server.domain}")
    private String domain;

    @Value("${familyCard.server.domain1}")
    private String domain1;

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .addServersItem(new Server().url("http://localhost:8080"))
                .addServersItem(new Server().url(domain))
                .addServersItem(new Server().url(domain1))
                .info(new Info()
                        .title("Family Card backend API")
                        .description("패밀리 카드 api 명세")
                        .version("0.9.0"));
    }
}
