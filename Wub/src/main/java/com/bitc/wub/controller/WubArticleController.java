package com.bitc.wub.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.bitc.wub.dto.ArticleDto;
import com.bitc.wub.dto.CommentDto;
import com.bitc.wub.dto.TagDto;
import com.bitc.wub.service.ArticleService;


@Controller
public class WubArticleController {
	
	@Autowired
	ArticleService articleService;

	// 글 쓰기 페이지 이동
	@RequestMapping(value="/article/write", method=RequestMethod.GET)
	public ModelAndView articleWrite() throws Exception {
		ModelAndView mv = new ModelAndView("board/write");
		
		List<TagDto> tagList = articleService.tagMainCategori();
		
		mv.addObject("tagList", tagList);
		
		return mv;
	}
	
	
	// 세부 분류
	@ResponseBody
	@RequestMapping(value="/article/write/detailCategori", method=RequestMethod.POST)
	public List<TagDto> detailCategori(@RequestBody TagDto tagDto) throws Exception {
		
		int mainCategori = Integer.parseInt(tagDto.getTagIdx());
		
		List<TagDto> tagList = articleService.tagDetailCategori(mainCategori);
		
		return tagList;
	}
	
	
	// 글쓰기 페이지에서 DB로 INSERT(이미지 첨부)
	@RequestMapping(value="/article/write", method=RequestMethod.POST) 
	public String insertArticle(ArticleDto articleDto, MultipartHttpServletRequest multiFiles) throws Exception {
	
		articleService.insertArticle(articleDto, multiFiles); 
		
		String bookIdx = Integer.toString(articleDto.getBookIdx());
		
		return "redirect:/article/openArticle?bookIdx=" + bookIdx ; // / + 메인페이지 혹은 다른페이지로 리다이렉트
	}
	

	// 상세 글 읽기
	@RequestMapping(value = "/article/openArticle", method=RequestMethod.GET)
	public ModelAndView article(@RequestParam("bookIdx") int bookIdx) throws Exception {
		ModelAndView mv = new ModelAndView("/board/article");
		
		// 게시글(이미지 조회)
		ArticleDto article = articleService.selectArticleDetail(bookIdx);
		
		
		// 댓글 리스트 조회
		//List<CommentDto> commentList = articleService.selectCommentList(bookIdx);
		
		
		//mv.addObject("commentList", commentList);
		mv.addObject("article", article);
			
		return mv;
	}
	
	// 댓글 쓰기
	@RequestMapping(value="/article/openArticle/comment", method=RequestMethod.POST)
	public String writeComment(CommentDto commentDto) throws Exception {
		
		articleService.insertComment(commentDto);
		String bookIdx = Integer.toString(commentDto.getBookIdx());
		return "redirect:/article/openArticle?bookIdx=" + bookIdx;
		
	}
	
	// 글 수정하기
	@RequestMapping(value="/article/edit", method=RequestMethod.PUT)
	public String editArticle(ArticleDto articleDto) throws Exception {
		
		//articleService.editArticle(articleDto);
		String bookIdx = Integer.toString(articleDto.getBookIdx());
		return "redirect:/article?bookIdx=" + bookIdx;
	}
	
	// 글 삭제하기
	@RequestMapping(value="/article/delete", method=RequestMethod.DELETE)
	public String deleteArticle(@PathVariable("articleIdx") int articleIdx) throws Exception {
		
		//articleService.deleteArticle(articleIdx);
		return "/main";
	}
	
	// 판매완료, 구매완료, 취소
	
	// article html 확인용 구현 후 삭제
	@RequestMapping(value="/article", method=RequestMethod.GET)
	public String articleView() throws Exception {
		return "board/article";
	}
	
	
}
