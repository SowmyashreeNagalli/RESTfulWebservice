package com.webeservice.example.restfulServices.hello;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@Autowired
	private ResourceBundleMessageSource msgSrc;

	@GetMapping("/hello")
	public String helloWorld() {

		return "Hello World!!";
	}

	@GetMapping("/hello-i18n")
	public String getMsgIntrnzn(@RequestHeader(name = "Accept-Language", required = false) String locale) {

		return msgSrc.getMessage("label.hello", null, new Locale(locale));
	}
	
	@GetMapping("/hello-i18n2")
	public String getMsgIntrnzn2(@RequestHeader(name = "Accept-Language", required = false) String locale) {

		return msgSrc.getMessage("label.hello", null, LocaleContextHolder.getLocale());
	}
}
