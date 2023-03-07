package com.example.restwithspringbootandjavaerudio.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Autowired
    private ConfigProperties configProperties;

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Restful API with java 17 and Spring Boot")
                        .version("v1")
                        .description("que bonita sua roooooupa, que ropinha muito looooooouca.")
                        .termsOfService("")
                        .license(new License().name("Apache 2.0").url(configProperties.getUrlExample())));
    }
}
