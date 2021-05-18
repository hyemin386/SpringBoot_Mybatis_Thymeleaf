package com.hm.j1.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.hm.j1.member.MemberVO;

@Component
public class AdminInterceptor implements HandlerInterceptor {

	/* Controller 진입 전 admin 판별 
	 * admin이면 진행 
	 * admin이 아니라면 
	 * 1. 로그인 폼으로 redirect 
	 * 2. 권한이 없음 alert창 띄우고 index로 이동 */
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("preHandel 시작");
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		boolean result = false;
		if(memberVO != null && memberVO.getUserName().equals("admin")) {
			result = true;
		} else {
			//1. redirect login
			response.sendRedirect("/member/login");
			/*2. forward
			request.setAttribute("msg", "권한이 없습니다");
			request.setAttribute("path", "../member/login");
			RequestDispatcher view = request.getRequestDispatcher("../common/result.html");
			view.forward(request, response); */
		}
		
		System.out.println("preHandel 종료");
		
		return result;
	}
	
}
