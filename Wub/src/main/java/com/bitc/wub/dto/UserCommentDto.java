package com.bitc.wub.dto;

import lombok.Data;

@Data
public class UserCommentDto {

	private int userIdx;
	private int bookIdx;
	private int commentIdx;
	
	private String bookTitle;
	private String bookPrice;
	private char soldYn;

	private String commentContent;
	private String createDate;
}
