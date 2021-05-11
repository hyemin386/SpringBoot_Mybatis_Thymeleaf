package com.hm.j1.board.notice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hm.j1.board.BoardVO;

@SpringBootTest
class NoticeMapperTest {

	@Autowired
	private NoticeMapper noticeMapper;
	
	@Test
	void getListTest() throws Exception {
		List<BoardVO> ar = noticeMapper.getList();
		for(BoardVO boardVO : ar) {
			System.out.println(boardVO.toString());
		}
		
		System.out.println(ar.size());
		assertNotEquals(0, ar.size());
	}

}
