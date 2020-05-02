package com.liada.commons.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.auth.BasicAuthRequestInterceptor;
@Configuration
public class FeignClientConfig {
	@Bean
	public BasicAuthRequestInterceptor getBasicAuthRequestInterceptor() {
		return new BasicAuthRequestInterceptor("llq", "hello");
	}
}
