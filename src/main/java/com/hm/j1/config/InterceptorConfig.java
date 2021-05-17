package com.hm.j1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.hm.j1.interceptor.TestInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

	@Autowired
	private TestInterceptor TestInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//Interceptor bean을 등록
		registry.addInterceptor(TestInterceptor)
		.addPathPatterns("/notice/**"); //앞에 registy 생략되있음		
		//어떤 URL이 왔을 때 발생시킬 것인지 설정
		
		WebMvcConfigurer.super.addInterceptors(registry);
	}
}
