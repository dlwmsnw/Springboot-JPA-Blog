package com.cos.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.service.BoardService;

@Controller
public class BoardController { 
	
	// index로 갈 때 필요한 boardService 연결.
	@Autowired
	private BoardService boardService;
	 
	// 컨트롤러에서 세션을 어떻게 찾는지?
	// @AuthenticationPrincipal PrincipalDetail principal
	@GetMapping({"","/"})
	public String index(Model model) { 
		model.addAttribute("boards", boardService.글목록()); 
		// /WEB-INF/views/index.jsp
		return "index"; // viewResolver 작동!!
	}
	
	// USER 권한이 필요
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}

}// class() end