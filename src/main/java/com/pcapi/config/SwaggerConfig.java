package com.pcapi.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SwaggerConfig {

    /**
     *Donde ver la pagina generada:
     * html
     *http://localhost:8080/swagger-ui/
     * JSon
     *http://localhost:8080/v2/api-docs
     */

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiDetails())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();

    }

    private ApiInfo apiDetails() {

        return  new ApiInfo("Spring Boot Laptop API REST",
                "Library API rest docs",
                "1.0",
                "https://www.google.com",
                new Contact("Alvaro","https://www.artstation.com/alvarowrn","alvarowrn97@gmail.com"),
                "MIT",
                "No license",
                Collections.emptyList());
    }

}
