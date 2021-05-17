package com.hm.j1.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/member/**")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	//회원가입
	@GetMapping("join")
	public String memberJoin() throws Exception {
		return "member/memberJoin";
	}
	
	@PostMapping("join")
	public String setJoin(MemberVO memberVO, MultipartFile avatar)throws Exception{
		int result = memberService.memberJoin(memberVO, avatar);
		
		return "redirect:../";
	}
	
	//로그인
	@GetMapping("login")
	public String memberLogin() throws Exception {
		return "member/memberLogin";
	}
	
	@PostMapping("login")
	public String memberLogin(MemberVO memberVO, HttpSession session) throws Exception {
		memberVO = memberService.memberLogin(memberVO);
		session.setAttribute("member", memberVO);
		System.out.println(memberVO.getName()+" 로그인 성공");
		return "redirect:../";
	}
	
	//로그아웃
	@GetMapping("logout")
	public String memberLogout(HttpSession session) throws Exception {
		session.invalidate();
		System.out.println("로그아웃 성공");
		return "redirect:../";
	}
	
	//마이페이지
	@GetMapping("page")
	public String memberPage () throws Exception {
		return "member/memberPage";
	}
	
}
