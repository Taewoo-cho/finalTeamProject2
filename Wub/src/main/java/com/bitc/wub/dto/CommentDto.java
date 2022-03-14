package com.bitc.wub.dto;

import lombok.Data;

@Data
public class CommentDto {

	private int commentIdx;
	private int bookIdx;
	private int userIdx;
	private String commentContent;
	private String createDt;
	private char deletedYn;
}
