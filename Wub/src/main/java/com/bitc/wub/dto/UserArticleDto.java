package com.bitc.wub.dto;

import lombok.Data;

@Data
public class UserArticleDto {

	private int userIdx;
	
	private int bookIdx;
	private String bookTitle;
	private String bookPrice;
	private String createDate;
	private char soldYn;
	private int hitCnt;

}
