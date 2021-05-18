package com.hm.j1.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.hm.j1.member.MemberMapper;

@Component
public class MemberInterceptor implements HandlerInterceptor{

	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String userName = request.getParameter("userName");
		memberMapper.memberJoin(null);
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
}
