package com.bitc.wub.common;

import java.io.File;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bitc.wub.dto.ImgDto;

@Component
public class FileUtils {
	
	public List<ImgDto> parseFileInfo(int bookIdx, MultipartHttpServletRequest multiFiles) throws Exception {
		
		// 매개변수로 받은 파일 정보가 없을 경우 null 리턴
		if (ObjectUtils.isEmpty(multiFiles)) {
			return null;
		}
		
		List<ImgDto> fileList = new ArrayList<>();
		
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
		ZonedDateTime current = ZonedDateTime.now();
		
		// 파일 경로
		String path = "/WakeUpBooks/img/" + current.format(format);

		// aws 파일 경로
		String awsPath = "/home/ec2-user/WakeUpBooks/img/" +current.format(format);
		
		File file = new File(awsPath);
		
		// 폴더가 존재하지 않을 시 폴더 생성
		if(file.exists() == false) {
			file.mkdirs(); // 폴더생성
		}
		
		Iterator<String> iterator = multiFiles.getFileNames();
		String newFileName;
		String originalFileExtension;
		String contentType;
		
		while(iterator.hasNext()) {
			String name = iterator.next();
//			지정한 파일명을 가지고 있는 파일의 모든 정보를 가져옴
			List<MultipartFile> list = multiFiles.getFiles(name);
			
			for (MultipartFile mFile : list) {
				if (mFile.isEmpty() == false) {
					contentType = mFile.getContentType();
					
					if(ObjectUtils.isEmpty(contentType)) {
						break;
					} else {
						if (contentType.contains("image/jpeg")) {
							originalFileExtension = ".jpg";
						} else if (contentType.contains("image/png")) {
							originalFileExtension = ".png";
						} else if (contentType.contains("image/gif")) {
							originalFileExtension = ".gif";
						} else {
							break;
						}
					}

					// 저장 되는 이름 = 시간.확장자자
				newFileName = Long.toString(System.nanoTime()) + originalFileExtension;
					
					ImgDto imgDto = new ImgDto();
					
					// imgDto에 idx, 파일크기, 업로드시 이름, 실제저장 되는 경로 + 파일이름 
					imgDto.setBookIdx(bookIdx);
					imgDto.setFileSize(Long.toString(mFile.getSize()));
					imgDto.setOriginalFileName(mFile.getOriginalFilename());
					// db에 저장되는 이름
					imgDto.setStoredFilePath(path + "/" + newFileName);
					
					// 데이터베이스에 저장할 목록에 저장
					fileList.add(imgDto);
					
					file = new File(awsPath + "/" + newFileName);
					// 현재 파일(메모리에만 존재함)을 지정한 위치에 이동하여 저장
					mFile.transferTo(file);
					
				}
			}
		}
		
		return fileList;
	}

}
