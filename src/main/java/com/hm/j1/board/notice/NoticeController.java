package com.hm.j1.board.notice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/notice/**")
public class NoticeController {
	
	@ModelAttribute("board") //속성의 이름 
	public String getBoard() {
		return "notice"; //value 값, 객체
	}
	
	
	@GetMapping("list")
	public String getList() throws Exception {
		return "board/list";
	}
	
}
