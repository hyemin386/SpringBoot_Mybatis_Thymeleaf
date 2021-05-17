package com.hm.j1.member;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

	//join
	public int memberJoin(MemberVO memberVO) throws Exception;
	
	//file
	public int memberJoinFile(MemberFileVO memberFileVO) throws Exception;
	
	//login
	public MemberVO memberLogin(MemberVO memberVO) throws Exception;
}
