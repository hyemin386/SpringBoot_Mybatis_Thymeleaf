package com.hm.j1.util;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManager {

	@Autowired
	private ResourceLoader resourceLoader;
	 
	public String save(MultipartFile multipartFile, String filePath) throws Exception {
		//filePath : /resources/static/ 제외한 하위경로
		/**
	  		ResourceLoader -> classPath 경로를 받아오기 위해 사용
	  		ClassPathResource -> classPath 경로를 받아오기 위해 사용, ResourceLoader과 같지만 
	                             시작경로에서 classPath를 제외함
		 **/
		//1. 경로설정 첫번째 방법
		String path="classpath:/static/";

		//path는 static 까지의 경로를 나타냄 filePath는 static후의 하위경로를 찾음
		File file = new File(resourceLoader.getResource(path).getFile(), filePath);
		
		/**
		 	저장할 폴더가 시스템에 고정일 경우
		 	String path ="c:/files" 바로 파일객체를 생성해서 쓰면됨
		 	File = file = new File(path, filePath);
		 **/
						
		/*1. 경로설정 2번째 방법
			String path ="static";
			ClassPathResource classPathResource = new ClassPathResource(path);
			File file = new File(classPathResource.getFile(), filePath);
		*/
		
		System.out.println(file.getAbsolutePath()); //경로확인용 프린트 출력
		
		if(!file.exists()) {
			file.mkdirs();
		}
		
		//2. 저장할 파일명 생성
		String fileName = UUID.randomUUID().toString()+"_"+multipartFile.getOriginalFilename();
		
		//3. 저장하기
		file = new File(file, fileName); //file: 경로명, fileName: 파일명		
		multipartFile.transferTo(file); //저장하는 첫번째 방법
		//FileCopyUtils.copy(multipartFile.getBytes(), file); 두번째 방법
		
		return fileName;
	}
}
