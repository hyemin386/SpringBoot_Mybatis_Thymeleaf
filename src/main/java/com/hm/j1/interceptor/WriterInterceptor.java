package com.hm.j1.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.hm.j1.board.BoardVO;
import com.hm.j1.member.MemberVO;

@Component
public class WriterInterceptor implements HandlerInterceptor {

	/* Controller 종료 후 실행
	   작성자를 출력, 로그인 유저이름 출력 */

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		//메서드 형식
		String method = request.getMethod();

		if(method.equals("GET")) {
			this.check(request, modelAndView);
		}

	}

	private void check(HttpServletRequest request, ModelAndView modelAndView) {
		//1. 로그인 유저
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO)session.getAttribute("member");

		//2. 작성자 
		BoardVO boardVO = (BoardVO)modelAndView.getModel().get("vo");

		//3. 유저와 작성자가 일치하지 않을 경우
		if(memberVO != null) {
			if(!memberVO.getUserName().equals(boardVO.getWriter())) {
				modelAndView.setViewName("common/result");
				modelAndView.addObject("msg", "작성자가 아닙니다");
				modelAndView.addObject("path", "./list");
			} 
		} else { //4. 로그인을 하지 않았을 경우
			modelAndView.setViewName("common/result");
			modelAndView.addObject("msg", "로그인을 해주세요");
			modelAndView.addObject("path", "../member/login");
		}	
	}

}
