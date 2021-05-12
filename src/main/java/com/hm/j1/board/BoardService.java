package com.hm.j1.board;

import java.util.List;

import com.hm.j1.util.Pager;

public interface BoardService {

	//list
	public List<BoardVO> getList(Pager pager) throws Exception;
	
	//select
	public BoardVO getSelect(BoardVO boardVO) throws Exception;
	
	//insert
	public int setInsert(BoardVO boardVO) throws Exception;
	
	//update
	public int setUpdate(BoardVO boardVO) throws Exception;
	
	//delete
	public int setDelete(BoardVO boardVO) throws Exception;
}
