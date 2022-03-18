package com.bitc.wub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bitc.wub.dto.SearchDto;
import com.bitc.wub.service.SearchService;

@Controller
public class SearchController {

	@Autowired
	public SearchService searchService;
	
//	검색 페이지 열기(임시용)
	@RequestMapping(value="/startsearch", method=RequestMethod.GET)
	public String openheader() throws Exception {
		return "/search/startSearch";
	}
	
//	검색 결과 페이지(최신순 정렬)
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public ModelAndView openSearchResult(String searchContent) throws Exception {
		ModelAndView mv = new ModelAndView("/search/searchResult");
		List<SearchDto> searchArticle = searchService.openSearchResult(searchContent);
		mv.addObject("searchArticle", searchArticle);
		return mv;
	}
	
//	책 상세 페이지로 이동
//	인기순 정렬
//	최신순 정렬
//	판매완료 제외
	
//	페이징
//	@RequestMapping(value="/search", method=RequestMethod.GET)
//	public ModelAndView openSearchResult(@RequestParam(required = false, defaultValue = "1") int pageNum, String searchContent) throws Exception {
//		ModelAndView mv = new ModelAndView("/search/searchResult");
//		PageInfo<SearchDto> searchArticle = new PageInfo<>(searchService.openSearchResult(pageNum, searchContent), 5);
//		mv.addObject("searchArticle", searchArticle);
//		return mv;
//	}
}
