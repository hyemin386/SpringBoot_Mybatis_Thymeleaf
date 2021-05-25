package com.hm.j1.member;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

	//join
	public int memberJoin(MemberVO memberVO) throws Exception;
	
	public int setMemberRole(Map<String, String> map) throws Exception;
	
	//file
	public int memberJoinFile(MemberFileVO memberFileVO) throws Exception;
	
	//login
	public MemberVO memberLogin(MemberVO memberVO);
	
	//id 중복확인
	public MemberVO getUsertName(MemberVO memberVO) throws Exception;
}
