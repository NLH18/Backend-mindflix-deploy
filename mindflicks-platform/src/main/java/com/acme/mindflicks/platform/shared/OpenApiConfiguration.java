package com.acme.mindflicks.platform.shared;


import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI mindflicksOpenApi(){

        var openApi = new OpenAPI();

        openApi
                .info(new Info()
                        .title("ACME Mindflix Platform application REST API documentation.")
                        .description("This is an Spring Boot RESTful service for mindflicks platform.")
                        .version("v1.0.0")
                        .license(new License().name("Apache 2.0")
                                .url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("ACME Mindflicks Platform wiki documentation")
                        .url("https://https://github.com/ReelHaven/Backend"));
        return openApi;

    };

}
