package com.cos.blog.config;

import org.aspectj.weaver.ast.And;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration // 빈 등록 : 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것. (IoC에서 관리가 된다.)
@EnableWebSecurity // 시큐리티 필터가 등록이 된다.
@EnableGlobalMethodSecurity(prePostEnabled =  true) // 특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다는 뜻.
// 위에 세 가지는 세트다. (이해가 안 가면 그냥 시큐리티 할 때 이 세 가지 걸면 됨.)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			    .antMatchers("/auth/**")
		  	   .permitAll()
			   .anyRequest()
			   .authenticated()
			.and()
				.formLogin()
				.loginPage("/auth/loginForm");
	}
}// class() end