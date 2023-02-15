package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder //빌더 패턴!
@Entity // User 클래스가 MySQL에 테이블이 생성된다.
public class Board {
	
	@Id // Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	// 만약 오라클을 연결하면 Identity는 시퀀스를 따라간다는 것이고,
	// MySQL을 연결하면 auto_increment을 따라간다는 뜻이다.
	private int id; // 시퀀스, auto_increment
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob // 대용량 데이터
	private String content; // 섬머노트 라이브러리 툴 사용 -> <html> 태그가 섞여서 디자인이 됨.
	
	@ColumnDefault("0")
	private int count; //조회수
	
	@ManyToOne(fetch=FetchType.EAGER) //Many=Board, User=One //한 명의 유저는 여러 게시글을 작성할 수 있다.
	@JoinColumn(name="userId")
	// private int userId; //보드를 누가 적었는지.
	private User user; //DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장할 수 있다.

	@OneToMany(mappedBy="board", fetch=FetchType.EAGER)
	//mappedBy > 연관관계의 주인이 아니다. (난 FK가 아니다.) DB에 칼럼을 만들지 마세요.
	//@JoinColumn(name="replyId") // 필요가 없다. 사용하면 DB 1정규화 -> 원자성 규칙이 깨짐.
	private List<Reply> reply;
	
	@CreationTimestamp //시간이 자동 입력
	private Timestamp createDate;
	

}// class() end