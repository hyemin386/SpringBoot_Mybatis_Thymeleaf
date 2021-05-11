package com.hm.j1.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hm.j1.board.BoardVO;

/**
 *  THYMELEAF PROJECT
 *  **/

@Controller
public class HomeController {

	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("message","Thymeleaf Project");
		BoardVO boardVO = new BoardVO();
		boardVO.setNum(1L);
		boardVO.setTitle("title");
		boardVO.setWriter("writer");
		
		System.out.println(boardVO.toString());
		return "index";
	}
	
}
