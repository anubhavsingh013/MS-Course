package com.in28minutes.rest.webservices.restful_web_services.helloworld;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	private MessageSource messageSource;

	public HelloWorldController(MessageSource messageSource){
		this.messageSource=messageSource;
	}

	@GetMapping(path="/helloworld")
	public String hello() {
		return "Hello world";
	}
	
	@GetMapping(path="/helloworld-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello world") ;
	}
	
	@GetMapping(path="/helloworld-pathvariable/{name}")
	public HelloWorldBean helloWorldBean(@PathVariable String name) {
		return new HelloWorldBean("Hello "+name) ;
	}

	// 'en' - English (good morining)
	// 'nl' - Dutch (Godemorgen)
	// 'fr' - French (Bonjour)
	// 'de' Deutsh (Gueten Morgen)
	@GetMapping(path="/hello-world-internationalized")
	public String helloWorldInternationalized() {
		Locale locale= LocaleContextHolder.getLocale();
		return messageSource.getMessage("good.morning.message", null, "Default Message", locale);
		// return "Hello world V2";
	}
}
