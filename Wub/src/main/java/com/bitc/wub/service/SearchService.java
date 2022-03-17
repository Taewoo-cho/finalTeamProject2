package com.bitc.wub.service;

import java.util.List;

import com.bitc.wub.dto.SearchDto;

public interface SearchService {

	public List<SearchDto> openSearchResult(String searchContent) throws Exception;
}
