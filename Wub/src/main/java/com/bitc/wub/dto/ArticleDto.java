package com.bitc.wub.dto;

import lombok.Data;

@Data
public class ArticleDto {
	private int articleIdx;
	private String articleTitle;
	private String articleContents;
	
	private int userIdx;
	private int hitCnt;
	private String createdDt;
	
	
	private char soldYn;
	private char deletedYn;

}
