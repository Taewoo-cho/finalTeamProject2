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
	private String createDate;
	
	private char bookTab;
	private char soldYn;
	private char deletedYn;
	private char bookNegotiation;
	
	// 태그 리스트
	private String mainCategory;
	private String detailCategory;
	
	
	// 이미지에 대한 정보를 저장하기 위한 멤버 변수 추가
	private List<ImgDto> ImgList;
	
	// 유저 정보를 저장하기 위한 멤버 변수
	

}
