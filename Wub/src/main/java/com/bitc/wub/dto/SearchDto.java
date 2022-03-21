package com.bitc.wub.dto;

import java.util.List;

import lombok.Data;

@Data
public class SearchDto {
	private int bookIdx;
	private String bookTitle;
	private String bookContents;
	
	private int userIdx;
	private String userName;
	private String userLocal;
	
	private String bookPrice;
	private int hitCnt;
	private String createDate;
	
	private char bookTab;
	private char soldYn;
	private char deletedYn;
	private char bookNegotiation;
	
	private String MainCategory;
	private String detailCategory;
	
	private String tagIdx;
	private String tagContent;
	
	private int fileIdx;
	private String originalFileName;
	private String storedFilePath;
	private String fileSize;
	
	private List<SearchDto> searchImgList;
}
