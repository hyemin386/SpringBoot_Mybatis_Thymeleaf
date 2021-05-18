package com.hm.j1.error;

import java.sql.SQLException;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorController {

	@ExceptionHandler(NullPointerException.class)
	public String getNull(Model model) {
		model.addAttribute("message", "Null 발생");
		return "error/500";
	}
	
	@ExceptionHandler(SQLException.class)
	public String getSql(Model model) {
		model.addAttribute("message", "DB문제 발생");
		return "error/500";
	}
	
	@ExceptionHandler(Throwable.class)
	public String getAll (Model model) {
		model.addAttribute("message", "Exception 발생");
		return "error/500";
	}
	
	@ExceptionHandler(MyException.class)
	public String getMyException(Model model, Exception exception) {
		model.addAttribute("message", exception.getMessage());
		return "error/500";
	}
}
