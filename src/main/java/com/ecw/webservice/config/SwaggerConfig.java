package com.ecw.webservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "Ecw Web Service",
                "Ecw wen service desciption",
                "version V1",
                "www.eclinicalworks.com",
                getContactInfo(),
                "open source",
                "ww.eclinicalworks.com/licence-info",
                new ArrayList<>()
        );
    }

    private Contact getContactInfo(){
        return new Contact(
                "Lalit Shingadiya",
                "www.eclinicalworks.com/lalits",
                "lalit.shingadiya@eclinicalworks.com"
        );
    }
}
