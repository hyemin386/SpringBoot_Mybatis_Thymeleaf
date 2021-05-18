package com.hm.j1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.hm.j1.interceptor.FirstInterceptor;
import com.hm.j1.interceptor.SecondInterceptor;

@Configuration
public class SecondInterceptorConfig implements WebMvcConfigurer {

	@Autowired
	private SecondInterceptor secondInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(secondInterceptor)
		.addPathPatterns("/**")
		.order(0);
	}
}
