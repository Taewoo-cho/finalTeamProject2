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

//	검색 결과 최신순 정렬
	@Override
	public List<SearchDto> openSearchResultNew(String searchContent) throws Exception {
		return searchMapper.openSearchResultNew(searchContent);
	}
	
//	검색 결과 조회수 정렬
	@Override
	public List<SearchDto> openSearchResultHit(String searchContent) throws Exception {
		return searchMapper.openSearchResultHit(searchContent);
	}

//	페이징
//	@Override
//	public Page<SearchDto> openSearchResult(int pageNum, String searchContent) throws Exception {
//		PageHelper.startPage(pageNum, 4);
//		return searchMapper.openSearchResult(searchContent);
//	}

}
