package com.hm.j1.member;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class MemberVO {

	private String userName;
	private String password1; //pw 입력
	
	@Length(min=0, max=6)
	private String password; //확인 pw
		
	@NotEmpty
	private String name;
	
	@NotEmpty
	private String email;
	
	@NotEmpty
	private String phone;
	
}
