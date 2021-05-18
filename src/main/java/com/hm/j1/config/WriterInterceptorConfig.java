package com.hm.j1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.hm.j1.interceptor.WriterInterceptor;

@Configuration
public class WriterInterceptorConfig  implements WebMvcConfigurer {

	/* WriterInterceptorConfig 
	   /qna/update에서 실행되도록 url설정 */
	
	@Autowired
	private WriterInterceptor writerInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry.addInterceptor(writerInterceptor)
		.addPathPatterns("/qna/update");
	}
	
}
