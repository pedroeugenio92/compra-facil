package com.project.cotafacil.util.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import io.swagger.models.auth.In;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@EnableWebMvc
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	
	@Value("${release.version}")
	private String releaseVersion;
	
	@Value("${api.version}")
	private String apiVersion;
	
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.project.cotafacil.controller"))
				.paths(PathSelectors.any()).build()
				.securitySchemes(Arrays.asList(new ApiKey("jwtToken", HttpHeaders.AUTHORIZATION, In.HEADER.name())))
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Compra da Hora Java API")
				.description("Compra da Hora Java API - Endpoint's documentation").
				version(releaseVersion.concat("_").concat(apiVersion))
				.build();
	}

}

