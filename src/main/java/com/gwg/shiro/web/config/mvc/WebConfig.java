package com.gwg.shiro.web.config.mvc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.ServletContext;
import java.util.List;

/**
 * SpringMVC容器
 * 1.想要使用Java配置的方式搭建Springmvc,只需要将@EnableWebMvc添加到你的一个@Configuration class即可,使用这种方式后就
 * 不需要再配置web.xml文件了
 * 2.在这里为什么实现WebMvcConfigurer？
 * 想要以Java形式定制默认的配置，你可以简单的实现WebMvcConfigurer接口，根据需要配置，或者继承WebMvcConfigurerAdapter并重写需要的方法：
 *
 * 二.SpringMvc与Swagger2集成
 * SpringMVC与swagger2集成，swagger2的配置需要在SpringMVC容器中，否则找不到服务
 */
@Configuration
@EnableWebMvc // 启用SpringMVC
@ComponentScan("com.gwg.shiro.web.controller")//Controller添加到SpringMVC的容器中，在这里不能添加到Spring的容器中，否者服务找不到。SpringMVC的容器可以访问Spring的容器中的Bean
@EnableSwagger2 //启用Swagger2
public class WebConfig extends WebMvcConfigurerAdapter {

   /* @Bean
    public RequestMappingHandlerAdapter annotationMethodHandlerAdapter(){
        return new RequestMappingHandlerAdapter();
    }*/

    /**
     * Java形式注册拦截器：
     * @param interceptorRegistry
     */
    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry) {

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry resourceHandlerRegistry) {
        resourceHandlerRegistry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        resourceHandlerRegistry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");

    }

    /**
     * 跨域处理
     * @param corsRegistry
     */
    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        //添加映射路径
        corsRegistry.addMapping("/**")
                //放行哪些原始域
                .allowedOrigins("http://localhost:3000")
                //是否发送Cookie信息
                .allowCredentials(true)
                 //放行哪些原始域(请求方式)
                .allowedMethods("GET","POST", "PUT", "DELETE", "OPTIONS")
                //放行哪些原始域(头部信息)
                .allowedHeaders("Content-Type", "Access-Control-Allow-Headers", "Authorization", "X-Requested-With");

    }

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