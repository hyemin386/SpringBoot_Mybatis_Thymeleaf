package com.hm.j1.board.qna;

import java.util.List;

import com.hm.j1.board.BoardFileVO;
import com.hm.j1.board.BoardVO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QnaVO extends BoardVO {
	
	private Long ref;
	private Long step;
	private Long depth;
	
	private List<BoardFileVO> files;
	
}
