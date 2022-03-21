package com.bitc.wub.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bitc.wub.dto.SearchDto;

@Mapper
public interface SearchMapper {

//	검색 결과 최신순 정렬
	public List<SearchDto> openSearchResultNew(String searchContent) throws Exception;
	
//	검색 결과 조회수 정렬
	public List<SearchDto> openSearchResultHit(String searchContent) throws Exception;
	
//	페이징
//	public Page<SearchDto> openSearchResult(String searchContent) throws Exception;
}
