package com.hm.j1.board.notice;

import java.util.List;

import com.hm.j1.board.BoardFileVO;
import com.hm.j1.board.BoardVO;

import lombok.Data;

@Data
public class NoticeVO extends BoardVO {

	private List<BoardFileVO> files;
}
