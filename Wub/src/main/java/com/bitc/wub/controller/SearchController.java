package com.bitc.wub.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
		return "search/startSearch";
	}
	
//	검색 결과 페이지(기본 최신순 정렬)
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public ModelAndView openSearchResult(HttpServletRequest request, String searchContent) throws Exception {
		ModelAndView mv = new ModelAndView("search/searchResult");
		HttpSession session = request.getSession();
		String search = searchContent;
		session.setAttribute("searchContent", search);
		List<SearchDto> searchArticle = searchService.openSearchResultNew(searchContent);
		mv.addObject("searchArticle", searchArticle);
		return mv;
	}
	
//	검색 결과 정렬
	@RequestMapping(value="/searchorder", method=RequestMethod.GET)
	public ModelAndView openSearchResultFilter(@RequestParam("order") String order, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("search/searchResult");
		HttpSession session = request.getSession();
		String searchContent = (String)session.getAttribute("searchContent");
		System.out.println("============");
		System.out.println(searchContent);
		System.out.println("============");
		
		List<SearchDto> searchArticle = null;
		switch(order) {
//			최신순 정렬
			case "new" : 
				searchArticle = searchService.openSearchResultNew(searchContent);
				break;
//			조회수 정렬
			case "hit" :
				searchArticle = searchService.openSearchResultHit(searchContent);
				break;
		}
		mv.addObject("searchArticle", searchArticle);
		return mv;
	}
	
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
