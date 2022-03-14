package com.bitc.wub.dto;

import java.util.List;

import lombok.Data;

@Data
public class ArticleDto {
	private int bookIdx;
	private String bookTitle;
	private String bookContents;
	
	private int userIdx;
	private String userName;
	private String userLocal;
	
	private String bookPrice;
	private int hitCnt;
	private String createdDt;
	
	private char bookTab;
	private char soldYn;
	private char deletedYn;
	private char bookNegotiation;
	
	// 태그 리스트
	private String MainCategori;
	private String detailCategori;
	
	// 이미지에 대한 정보를 저장하기 위한 멤버 변수
	private List<ImgDto> ImgList;

}
