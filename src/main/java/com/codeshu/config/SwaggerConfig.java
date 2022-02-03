package com.codeshu.config;

import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.springframework.beans.factory.annotation.Qualifier;
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
 * @author ShuCode
 * @date 2022/1/28 14:00
 * @Email 13828965090@163.com
 */
@Configuration
@EnableSwagger2  //开启Swagger
public class SwaggerConfig {
	@Bean
	public Docket docket(){
		Docket docket = new Docket(DocumentationType.SWAGGER_2);
		docket.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.codeshu.controller"))
				.build();
		return docket;
	}
	@Bean
	public ApiInfo apiInfo(){
		Contact contact = new Contact("CodeShu", "http://codeshu.com", "13828965090@163.com");
		ApiInfo apiInfo = new ApiInfo(
				"张一术的文档",
				"社区养老院综合信息管理系统的Api文档",
				"v1.0",
				"urn:tos",
				contact,
				"Apache 2.0",
				"http://www.apache.org/licenses/LICENSE-2.0", new ArrayList());
		return apiInfo;
	}
}
