package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더 패턴!
@Entity // User 클래스가 MySQL에 테이블이 생성된다.
public class Reply {

	@Id // Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	// 만약 오라클을 연결하면 Identity는 시퀀스를 따라간다는 것이고,
	// MySQL을 연결하면 auto_increment을 따라간다는 뜻이다.
	private int id; // 시퀀스, auto_increment

	@Column(nullable = false, length = 200)
	private String content; // 답변

	// 누가, 어느 게시물의 답변.. 이런 것들이 필요.
	@ManyToOne
	@JoinColumn(name = "boardId")
	// private int boardId; //보드를 누가 적었는지.
	private Board board; // DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장할 수 있다.

	@ManyToOne // Many=Board, User=One //한 명의 유저는 여러 게시글을 작성할 수 있다.
	@JoinColumn(name = "userId")
	// private int userId; //보드를 누가 적었는지.
	private User user; // DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장할 수 있다.

	@CreationTimestamp //시간이 자동 입력
	private Timestamp createDate;
}// class() end