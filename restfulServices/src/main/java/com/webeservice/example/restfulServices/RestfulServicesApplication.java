package com.webeservice.example.restfulServices;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@SpringBootApplication
public class RestfulServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulServicesApplication.class, args);
	}

	@Bean
	public LocaleResolver localeResolver() {

		AcceptHeaderLocaleResolver localRes = new AcceptHeaderLocaleResolver();
		localRes.setDefaultLocale(Locale.US);
		return localRes;
	}

	@Bean
	public ResourceBundleMessageSource messageSrc() {

		ResourceBundleMessageSource msgsrc = new ResourceBundleMessageSource();
		msgsrc.setBasename("messages");
		return msgsrc;
	}

}
