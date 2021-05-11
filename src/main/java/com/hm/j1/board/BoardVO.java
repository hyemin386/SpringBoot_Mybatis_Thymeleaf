package com.hm.j1.board;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
@Getter //getter 생성됨
@Setter //setter 생성
@ToString //toString override
@NoArgsConstructor //default 생성자
@AllArgsConstructor //매개변수 생성자 
*/

/*
@AllArgsConstructor // 모든 매개변수가 있는 생성자
@EqualsAndHashCode //hash, equals 메서드
@RequiredArgsConstructor //필수 매개변수만 있는 생성자 생성
*/

@Data //lombok의 모든 annotation 선언과 같음
public class BoardVO {
	
	private Long num;
	private String title;
	private String name;
	private String contents;
	private Date regDate;
	private Long hit;
		
}
