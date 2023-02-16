package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import com.cos.blog.RoleType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder //빌더 패턴!
//ORM -> Java(다른 언어) Object -> 테이블로 매핑해주는 기술
@Entity // User 클래스가 MySQL에 테이블이 생성된다.
//@DynamicInsert // insert할 때 null인 필드 제외시켜준다.
public class User {
	
	@Id // Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	// 만약 오라클을 연결하면 Identity는 시퀀스를 따라간다는 것이고,
	// MySQL을 연결하면 auto_increment을 따라간다는 뜻이다.
	private int id; // 시퀀스, auto_increment
	
	@Column(nullable = false, length = 30, unique = true)
	private String username; //아이디
	
	@Column(nullable = false, length = 100) //123456 => 해쉬 (비밀번호 암호화)
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email; 
	
	//@ColumnDefault("'user'") //회원의 디폴트 값. 문자이기 때문에 ' ' 사용
	//private String role; //Enum을 쓰는 게 좋다. 지금은 그냥 role로 쓰겠다. //admin, user, manager 권한 다르게 하는 것을 하기 위해서
	@Enumerated(EnumType.STRING)
	private RoleType role; //Enum을 쓰는 게 좋다. //ADMIN, USER
	
	@CreationTimestamp //시간이 자동 입력
	private Timestamp createDate;

}// class() end