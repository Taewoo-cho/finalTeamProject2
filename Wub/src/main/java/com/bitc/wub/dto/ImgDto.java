package com.bitc.wub.dto;

import lombok.Data;

@Data
public class ImgDto {
	
	private int fileIdx;
	private int bookIdx;
	
	private String originalFileName;
	private String storedFilePath;
	private int fileSize;
	private char deletedYn;
	

}
