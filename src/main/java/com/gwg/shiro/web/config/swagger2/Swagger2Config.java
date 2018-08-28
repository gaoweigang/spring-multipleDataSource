package com.gwg.shiro.web.config.swagger2;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
import springfox.documentation.builders.ApiInfoBuilder;

import springfox.documentation.builders.RequestHandlerSelectors;

import springfox.documentation.service.ApiInfo;

import springfox.documentation.service.Contact;

import springfox.documentation.spi.DocumentationType;

import springfox.documentation.spring.web.plugins.Docket;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * SpringMVC与swagger2集成，swagger2的配置需要在SpringMVC容器中，否则找不到服务
 * 这个配置需要与@EnableWebMvc一起,需要在SpringMVC容器中，否则会报错
 */
public class Swagger2Config {



    @Bean

    public Docket customImplementation() {
        return new Docket(DocumentationType.SWAGGER_2)//
                .select()//
                .apis(RequestHandlerSelectors.basePackage("com.gwg.shiro.web.controller"))//
                .build()//
                .apiInfo(apiInfo());

    }



    private ApiInfo apiInfo() {

        return new ApiInfoBuilder()//
                .title("shiro 系统 API")//
                .description("")//描述
                .license("")//
                .licenseUrl("http://unlicense.org")//
                .termsOfServiceUrl("")//
                .version("0.0.1")//版本号
                .contact(new Contact("", "", ""))//创建人
                .build();

    }



}