package com.bitc.wub.service;

import java.util.List;

import com.bitc.wub.dto.SearchDto;

public interface SearchService {

//	검색 결과 최신순 정렬
	public List<SearchDto> openSearchResultNew(String searchContent) throws Exception;
	
//	검색 결과 조회수 정렬
	public List<SearchDto> openSearchResultHit(String searchContent) throws Exception;
	
//	페이징
//	public Page<SearchDto> openSearchResult(int pageNum, String searchContent) throws Exception;
}
