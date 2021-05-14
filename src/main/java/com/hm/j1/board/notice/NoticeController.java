package com.hm.j1.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndView;

import com.hm.j1.board.BoardVO;
import com.hm.j1.util.Pager;

@Controller
@RequestMapping("/notice/**")
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	@ModelAttribute("board") //속성의 이름 
	public String getBoard() {
		return "notice"; //value 값, 객체
	}
		
	@GetMapping("list")
	public String getList(Model model, Pager pager) throws Exception {
		List<BoardVO> ar = noticeService.getList(pager);
		model.addAttribute("list",ar);
		model.addAttribute("pager", pager);
		return "board/list";
	}
	
	@GetMapping("fileDown")
	public ModelAndView fileDown(String fileName, String oriName) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.addObject("fileName", fileName);
		mv.addObject("oriName", oriName);
		mv.addObject("filePath", "upload/notice");
		// view 이름은 Bean의 이름과 일치
		mv.setViewName("fileDown");
		return mv;
	}
	
	@GetMapping("select")
	public ModelAndView getSelect(BoardVO boardVO) throws Exception {
		ModelAndView mv = new ModelAndView();
		boardVO = noticeService.getSelect(boardVO);
		mv.addObject("vo", boardVO);
		mv.setViewName("board/select");
		return mv;
	}
	
	@GetMapping("insert")
	public String setInsert(Model model) throws Exception {
		model.addAttribute("vo", new BoardVO());
		model.addAttribute("action", "insert");
		return "board/form";
	}
	
	@PostMapping("insert")
	public String setInsert(BoardVO boardVO, MultipartFile [] files) throws Exception {
		int result = noticeService.setInsert(boardVO, files);
		System.out.println(files.length);
		for(MultipartFile f:files) {
			System.out.println(f.getOriginalFilename());
		}
		return "redirect:./list";
	}
	
	@GetMapping("update")
	public String setUpdate(BoardVO boardVO, Model model) throws Exception {
		boardVO = noticeService.getSelect(boardVO);
		model.addAttribute("vo", boardVO);	
		model.addAttribute("action", "update");
		return "board/form";
	}
	
	@PostMapping("update")
	public String setUpdate(BoardVO boardVO) throws Exception {
		int result = noticeService.setUpdate(boardVO);
		return "redirect:./list";
	}
	
	@GetMapping("delete")
	public String setDelete(BoardVO boardVO) throws Exception {
		int result = noticeService.setDelete(boardVO);
		return "redirect:./list";
	}
	
}
