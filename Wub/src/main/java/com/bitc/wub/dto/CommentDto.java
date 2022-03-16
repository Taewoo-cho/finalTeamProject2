package com.bitc.wub.dto;

import lombok.Data;

@Data
public class CommentDto {

	private int commentIdx;
	private int bookIdx;

	private String commentContent;
	private String createDate;
	private char deletedYn;
	
	private int userIdx;
	private String userName;
	
}