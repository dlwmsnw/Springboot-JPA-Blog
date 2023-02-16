package com.cos.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice // 어디서 Exception이 발생해도 여기로 오게 하는 컨트롤러.
@RestController
public class GlobalExceptionHandler {
	
	/*
	 * @ExceptionHandler(value=IllegalArgumentException.class) public String
	 * handleArgumentException(IllegalArgumentException e) { return
	 * "<h1>"+e.getMessage()+"</h1>"; }
	 */
	
	@ExceptionHandler(value=Exception.class)
	public String handleArgumentException(Exception e) {
		return "<h1>"+e.getMessage()+"</h1>";
	}

}// class() end