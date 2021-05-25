package com.hm.j1.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;

import com.hm.j1.board.BoardFileVO;
import com.hm.j1.util.FileManager;

@Service
//Spring security에서 사용하는 service
//UserDetailsService 구현
public class MemberService implements UserDetailsService {

	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private FileManager fileManager;	
	@Autowired
	private PasswordEncoder passwordEncoder; //pw 암호화 시켜주는 역할
	
	//login 메서드
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberVO memberVO = new MemberVO();
		memberVO.setUserName(username);
		memberVO = memberMapper.memberLogin(memberVO);
		
		return memberVO;
	}
	
	//비밀번호 검증 메서드생성하기
	public boolean memberError(MemberVO memberVO, Errors errors) throws Exception {
		boolean result = false;
		
		/*기본 제공 검증
		if(errors.hasErrors()) {
			result = true;
		} */
		
		result = errors.hasErrors();
		
		//password 일치 여부 검증
		if(memberVO.getPassword().equals(memberVO.getPassword1())) {
			errors.rejectValue("password1", "memberVO.password.notEqual"); //message properties에 담아놓은 메시지 출력
			result = true;
		}
		
		//UserName 중복 여부 검증
		MemberVO checkMember = memberMapper.getUsertName(memberVO);
		if(checkMember != null) {
			errors.rejectValue("userName", "member.id.equal");
			result = true;
		}
		
		return result;
	}
	
	public int memberJoin(MemberVO memberVO, MultipartFile multipartFile) throws Exception {
		//0. 사전작업 (패스워드 암호화 및 사용자계정 활성화)
		//패스워드 암호화
		memberVO.setPassword(passwordEncoder.encode(memberVO.getPassword()));
		//사용자 계정 활성화
		memberVO.setEnabled(true);
		
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
			memberFileVO.setUserName(memberVO.getUsername());
		//3. MemberFiles table 저장
			result = memberMapper.memberJoinFile(memberFileVO);
		}
		
		return result;
	}
	
	/*public MemberVO memberLogin(MemberVO memberVO) throws Exception {
		return memberMapper.memberLogin(memberVO);
	}*/
}
