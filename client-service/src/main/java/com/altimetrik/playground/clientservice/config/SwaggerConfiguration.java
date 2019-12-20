package com.altimetrik.playground.clientservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@Configuration
public class SwaggerConfiguration {
    @Bean
    public Docket countryAPI(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.altimetrik.playground.clientservice"))
                .paths(regex("/frontend.*"))
                .build()
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {

        ApiInfo apiInfo = new ApiInfo(
                "Altimetrik Frontend Service API",
                "Spring Boot Application",
                "1.0",
                "Terms Of Service",
                new Contact("Kedi Wagobera Edgar",
                        "https://www.kedikebba.com/",
                        "kebbakedi@gmail.com")
                ,
                "Apache License Version 2.0",
                "https://www.apache.org/license.html", Collections.emptyList());
        return apiInfo;
    }
}
