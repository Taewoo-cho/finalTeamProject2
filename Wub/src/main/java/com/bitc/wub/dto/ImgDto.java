package com.bitc.wub.dto;

import lombok.Data;

@Data
public class ImgDto {
	
	private int fileIdx;
	
	private int bookIdx;
	private String originalFileName;
	private String storedFilePath;
	private String fileSize;
	
	

}
