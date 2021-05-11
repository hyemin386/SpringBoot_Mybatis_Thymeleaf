package com.hm.j1.board;

import java.util.List;

public interface BoardService {

	//list
	public List<BoardVO> getList() throws Exception;
	
	//select
	public BoardVO getSelect(BoardVO boardVO) throws Exception;
	
	//insert
	public int setInsert(BoardVO boardVO) throws Exception;
	
	//update
	public int setUpdate(BoardVO boardVO) throws Exception;
	
	//delete
	public int setDelete(BoardVO boardVO) throws Exception;
}
