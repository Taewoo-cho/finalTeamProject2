package com.bitc.wub.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bitc.wub.dto.SearchDto;

@Mapper
public interface SearchMapper {

//	검색 결과 페이지(최신순 정렬)
	public List<SearchDto> openSearchResult(String searchContent) throws Exception;
	
//	페이징
//	public Page<SearchDto> openSearchResult(String searchContent) throws Exception;
}
