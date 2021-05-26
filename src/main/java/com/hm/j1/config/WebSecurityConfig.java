package com.hm.j1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public PasswordEncoder PasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		// Security를 무시(제외)
		web.ignoring()
		.antMatchers("/resources/**")
		.antMatchers("/images/**")
		.antMatchers("/css/**")
		.antMatchers("/js/**")
		.antMatchers("/vendor/**")
		.antMatchers("/favicon/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 로그인, 권한 설정
		http
		//권한 에러 발생시 처리(403)
		.exceptionHandling()
			.accessDeniedPage(null) //에러페이지 경로작성
			.accessDeniedHandler(null) //에러처리 class
			.and()
		.cors().and()
		.csrf().disable()
		.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/notice/list", "/notice/select").permitAll()
			.antMatchers("/notice/**").hasRole("ADMIN")
			.antMatchers("/qna/list").permitAll()
			.antMatchers("/qna/**").hasAnyRole("MEMBER", "ADMIN")
			.antMatchers("/member/join").permitAll()
			.antMatchers("member/**").hasAnyRole("MEMBER", "ADMIN")
			.anyRequest().authenticated()
			.and()
		.formLogin()
			.loginPage("/member/login")
			.defaultSuccessUrl("/member/memberLoginResult") //로그인 성공 후 전송할 url	
			.failureUrl("/member/loginFail?error") //로그인 실패 메세지 띄워주기
			.permitAll()
			.and()
		.logout()
			.logoutUrl("/member/logout") //로그아웃
			.logoutSuccessUrl("/") //로그아웃 성공시 index로 이동
			.invalidateHttpSession(true)
			.deleteCookies("JSESSIONID") //쿠키에 저장된 정보 삭제해줌
			.permitAll()
			;
	}
			
	
}
