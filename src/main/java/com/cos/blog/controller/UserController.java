package com.cos.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 인증이 안 된 사용자들이 출입할 수 있는 경로를 /auth/ ** 허용
// 그냥 주소가 / 이면 index.jsp 으로 가는데 이것도 허용시켜 줄 것이다.
// static 이하에 있는 /js/**, /css/**, /image/** 도 허용시켜 줄 것이다.

@Controller
public class UserController {
	
	@GetMapping("/auth/joinForm")
	public String joinForm() {
		return "user/joinForm";
	}
	
	@GetMapping("/auth/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}
}// class() end