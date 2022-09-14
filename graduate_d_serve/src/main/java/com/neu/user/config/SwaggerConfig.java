package com.neu.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;

import springfox.documentation.service.Contact;

import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @author 陈锦房
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    //配置第一个分组
    @Bean
    public Docket docket1(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("第一组");

    }
    //配置第二个分组
    @Bean
    public Docket docket2(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("第二组");

    }

    @Bean
    public Docket myDocket(){
        return  new Docket(DocumentationType.SWAGGER_2)
                .groupName("测试").
                apiInfo(apiInfo()).
                select().
                apis(RequestHandlerSelectors.basePackage("com.neu.user.controller")).build();
    }

    private ApiInfo apiInfo() {
        //配置个人信息
        Contact contact = new Contact("cjf", "http://localhost:8081/swagger-ui.html", "532055876@qq.com ");
        return new ApiInfo(
                //标题
                "cjf的SwaggerAPI文档",
                //描述
                "ApiDocumentation",
                //版本
                "1.0",
                //邮箱
                "urn:tos",
                contact,
                "Apache2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }
}
