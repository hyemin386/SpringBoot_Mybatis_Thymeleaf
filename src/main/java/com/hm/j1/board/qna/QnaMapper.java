package com.hm.j1.board.qna;

import org.apache.ibatis.annotations.Mapper;

import com.hm.j1.board.BoardMapper;
import com.hm.j1.board.BoardVO;

@Mapper
public interface QnaMapper extends BoardMapper{
	
	public int setReplyInsert(BoardVO boardVO) throws Exception; //서브쿼리를 이용할 예정
	
	public int setReplyUpdate(BoardVO boardVO) throws Exception;
	
	public int setRefUpdate(BoardVO boardVO) throws Exception;
}
