package com.hm.j1.board.notice;

import java.util.List;

import org.apache.ibatis.session.SqlSessionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.hm.j1.board.BoardFileVO;
import com.hm.j1.board.BoardService;
import com.hm.j1.board.BoardVO;
import com.hm.j1.util.FileManager;
import com.hm.j1.util.Pager;

@Service
public class NoticeService implements BoardService {

	@Autowired
	private NoticeMapper noticeMapper;
	@Autowired
	private FileManager fileManager;
	
	@Override
	public List<BoardVO> getList(Pager pager) throws Exception {
		// TODO Auto-generated method stub
		
		/* if(pager.getCurPage()%2==0) {
			throw new SqlSessionException();
		} */
	
		pager.makeRow();
		Long totalCount = noticeMapper.getTotalCount(pager);
		pager.makeNum(totalCount);
		return noticeMapper.getList(pager);
	}

	@Override
	public BoardVO getSelect(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		noticeMapper.setHitUpdate(boardVO);
		return noticeMapper.getSelect(boardVO);
	}

	@Override
	@Transactional(rollbackFor = Exception.class) //exception이 발생할 때 rollback을 하겠다!
	public int setInsert(BoardVO boardVO, MultipartFile [] files) throws Exception {
		// TODO Auto-generated method stub
		int result = noticeMapper.setInsert(boardVO);
		
		/*if(result<1) {
			//예외는 발생하지않고 결과가 0이 나올 경우에
			throw new Exception(); //예외 강제발생
		}*/
		
		String filePath ="upload/notice/";
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
			noticeMapper.setFileInsert(boardFileVO);
		}			
		return result;
	}

	@Override
	public int setUpdate(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return noticeMapper.setUpdate(boardVO);
	}

	@Override
	public int setDelete(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return noticeMapper.setDelete(boardVO);
	}
	
}
