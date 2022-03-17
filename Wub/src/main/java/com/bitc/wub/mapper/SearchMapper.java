package com.bitc.wub.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bitc.wub.dto.SearchDto;

@Mapper
public interface SearchMapper {

	public List<SearchDto> openSearchResult(String searchContent) throws Exception;

}
