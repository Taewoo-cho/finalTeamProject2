package com.bitc.wub.dto;

import lombok.Data;

@Data
public class CommentDto {

	private int commentIdx;
	private int bookidx;
	private int userIdx;
	private String commentContent;
	private String createDt;
	private char deletedYn;
}
