package com.hm.j1.board;

import java.util.List;

public interface BoardMapper {

	//list
	public List<BoardVO> getList() throws Exception;
	
	//count
	public Long getTotalCount() throws Exception;
	
	//select
	public BoardVO getSelect(BoardVO boardVO) throws Exception;
	
	//insert
	public int setInsert(BoardVO boardVO) throws Exception;
	
	//update
	public int setUpdate(BoardVO boardVO) throws Exception;
	
	//hitUpdate
	public int setHitUpdate(BoardVO boardVO) throws Exception;
	
	//delete
	public int setDelete(BoardVO boardVO) throws Exception;
	
}
