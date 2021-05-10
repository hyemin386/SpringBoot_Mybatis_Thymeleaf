package com.hm.j1.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *  THYMELEAF PROJECT
 *  **/

@Controller
public class HomeController {

	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("message","Thymeleaf Project");
		return "index";
	}
	
}
