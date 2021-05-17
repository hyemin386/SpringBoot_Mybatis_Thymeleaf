package com.hm.j1.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.hm.j1.board.BoardFileVO;
import com.hm.j1.util.FileManager;

@Service
public class MemberService {

	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private FileManager fileManager;
	
	public int memberJoin(MemberVO memberVO, MultipartFile multipartFile) throws Exception {
		//1. Member 테이블에 저장
		int result = memberMapper.memberJoin(memberVO);
		//2. HDD에 저장
		String filePath= "upload/member/";
		if(multipartFile.getSize() != 0) {
			String fileName= fileManager.save(multipartFile, filePath);
			System.out.println(fileName);
			MemberFileVO memberFileVO = new MemberFileVO();
			memberFileVO.setFileName(fileName);
			memberFileVO.setOriName(multipartFile.getOriginalFilename());
			memberFileVO.setUserName(memberVO.getUserName());
		//3. MemberFiles table 저장
			result = memberMapper.memberJoinFile(memberFileVO);
		}
		
		return result;
	}
	
	public MemberVO memberLogin(MemberVO memberVO) throws Exception {
		return memberMapper.memberLogin(memberVO);
	}
}
