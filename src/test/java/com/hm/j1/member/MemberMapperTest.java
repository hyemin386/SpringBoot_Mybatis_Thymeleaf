package com.hm.j1.member;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberMapperTest {

	@Autowired
	private MemberMapper memberMapper;
	
	//@Test
	void test() throws Exception{
		MemberVO memberVO = new MemberVO();
		memberVO.setUsername("id1");
		memberVO.setPassword("pw1");
		memberVO.setName("id1");
		memberVO.setPhone("01012341234");
		memberVO.setEmail("id1@naver.com");
		memberVO.setEnabled(true);
		int result = memberMapper.memberJoin(memberVO);
		assertEquals(1, result);
	}

	//@Test
	void setMemberRoleTest() throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userName", "id1");
		map.put("roleName", "MEMBER");
		int result = memberMapper.setMemberRole(map);
		assertEquals(1, result);
	}
	
	@Test
	void getMemberLoginTest() throws Exception {
		MemberVO memberVO = new MemberVO();
		memberVO.setUsername("id1");
		
		memberVO = memberMapper.memberLogin(memberVO);
		
		for(RoleVO roleVO: memberVO.getRoles()) {
			System.out.println(roleVO.getRoleName());
		}
		assertNotNull(memberVO);
	}
}
