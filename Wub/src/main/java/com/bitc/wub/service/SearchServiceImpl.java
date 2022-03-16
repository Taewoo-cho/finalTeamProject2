package com.bitc.wub.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitc.wub.dto.SearchDto;
import com.bitc.wub.mapper.SearchMapper;

@Service
public class SearchServiceImpl implements SearchService {
	
	@Autowired
	private SearchMapper searchMapper;

	@Override
	public List<SearchDto> openSearchResult(String searchContent) throws Exception {
		return searchMapper.openSearchResult(searchContent);
	}

}
