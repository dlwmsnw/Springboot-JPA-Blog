package com.cos.blog.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

//@Getter
//@Setter
@Data               //Getter & Setter
//@AllArgsConstructor //생성자
@NoArgsConstructor  //빈생성자 ~~~() 표기.
//@RequiredArgsConstructor //final이 붙은 것들에 대한 Constructor을 만들어 준다.

public class Member {
	private int id;
	private String username;
	private String password;
	private String email;
	
	@Builder
	public Member(int di, String username, String password, String email) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
}// class () end