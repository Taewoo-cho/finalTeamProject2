package com.bitc.wub.dto;

import lombok.Data;

@Data
public class UserRecommendDto {

	private int userIdx;
	private int bookIdx;
	private int recommendIdx;

	private String bookTitle;
	private String bookPrice;
	
	private char deletedYn;
	private char soldYn;
}
