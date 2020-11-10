package com.webeservice.example.restfulServices.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class) //JSR-303 validation
public class SwaggerConfig {

	// Swagger Metadata : http://localhost:8080/v2/api-docs
	// Swagger UI : http://localhost:8080/swagger-ui.html
	// Swagger editor : https://editor.swagger.io/

	@Bean
	public Docket api() {

		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(getApiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.webeservice.example.restfulServices"))
				.paths(PathSelectors.ant("/users/**"))
				.build();
	}

	private ApiInfo getApiInfo() {

		return new ApiInfoBuilder()
				.title("Super Learnings Spring Boot RESTful Web Services")
				.description("This page lists all API's of User Management")
				.version("2.0")
				.contact(new Contact("Sowmya", "http://superLearning.com", "sowmya.n001@gmail.com"))
				.license("License 2.4")
				.licenseUrl("http://superLearning.com/license.html")
				.build();
	}
}
