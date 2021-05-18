package com.hm.j1.board.qna;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.hm.j1.board.BoardFileVO;
import com.hm.j1.board.BoardService;
import com.hm.j1.board.BoardVO;
import com.hm.j1.error.MyException;
import com.hm.j1.util.FileManager;
import com.hm.j1.util.Pager;

@Service
public class QnaService implements BoardService{

	@Autowired
	private QnaMapper qnaMapper;
	
	@Autowired
	private FileManager fileManager;

	@Override
	public List<BoardVO> getList(Pager pager) throws Exception {
		pager.makeRow();		
		pager.makeNum(qnaMapper.getTotalCount(pager));
		
		return qnaMapper.getList(pager);
	}

	@Override
	public BoardVO getSelect(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		qnaMapper.setHitUpdate(boardVO);
		return qnaMapper.getSelect(boardVO);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int setInsert(BoardVO boardVO, MultipartFile[] files) throws Exception {
		//1. qna insert
		int result = qnaMapper.setInsert(boardVO);
		//2. qna ref update
		result = qnaMapper.setRefUpdate(boardVO);
		//3. file save
		String filePath ="upload/qna/";
		for(MultipartFile multipartFile:files) {
			if(multipartFile.getSize()==0) {
				continue;
			}
			String fileName = fileManager.save(multipartFile, filePath);
			System.out.println(fileName);
			BoardFileVO boardFileVO = new BoardFileVO();
			boardFileVO.setFileName(fileName);
			boardFileVO.setOriName(multipartFile.getOriginalFilename());
			boardFileVO.setNum(boardVO.getNum());
			qnaMapper.setFileInsert(boardFileVO);
		}			
		
		return result;
	}

	@Override
	public int setUpdate(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		/*Random random = new Random();
		int result = random.nextInt(1);
		if(result == 0) {
			throw new MyException("update 실패");
		}
		return result; */
		return qnaMapper.setUpdate(boardVO);
	}

	@Override
	public int setDelete(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return qnaMapper.setDelete(boardVO);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public int setReplyInsert(BoardVO boardVO, MultipartFile [] files) throws Exception {
		//boardVO.num = 부모의 글번호
		//1. step update
		int result = qnaMapper.setReplyUpdate(boardVO);
		//2. reply insert
		result = qnaMapper.setReplyInsert(boardVO);
		//3. file Hdd에 저장
		String filePath ="upload/qna/";
		
		for(MultipartFile multipartFile:files) {
			if(multipartFile.getSize()==0) {
				continue;
			}
			String fileName = fileManager.save(multipartFile, filePath);
			System.out.println(fileName);
			BoardFileVO boardFileVO = new BoardFileVO();
			boardFileVO.setFileName(fileName);
			boardFileVO.setOriName(multipartFile.getOriginalFilename());
			boardFileVO.setNum(boardVO.getNum());
			qnaMapper.setFileInsert(boardFileVO);
		}			
		
		return result;
	}
	
	
}
