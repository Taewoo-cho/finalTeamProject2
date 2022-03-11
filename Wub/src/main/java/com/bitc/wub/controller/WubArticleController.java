package com.bitc.wub.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.bitc.wub.dto.ArticleDto;
import com.bitc.wub.dto.CommentDto;
import com.bitc.wub.service.ArticleService;


@Controller
public class WubArticleController {
	
	@Autowired
	ArticleService articleService;

	// 글 쓰기 페이지 이동
	@RequestMapping(value="/article/write", method=RequestMethod.GET)
	public String articleWrite() throws Exception {
		return "board/article/write";
	}
	
	// 글쓰기 페이지에서 DB로 INSERT(이미지 첨부)
	@RequestMapping(value="/article/write", method=RequestMethod.POST)
	public String insertArticle(ArticleDto articleDto, MultipartHttpServletRequest multiFile) { 
		
		//articleService.insertArticle(articleDto, multiFile);
		return "redirect:/mypage";
	}

	// 상세 글 읽기
	@RequestMapping(value = "/article/{articleIdx}", method=RequestMethod.GET)
	public ModelAndView article(@PathVariable("articleIdx") int articleIdx) throws Exception {
		ModelAndView mv = new ModelAndView("/board/article");
		
		// 조회수 상승
		articleService.countHitCnt(articleIdx);
		
		// 게시글 조회
		ArticleDto article = articleService.selectArticleDetail(articleIdx);
		
		// 댓글 리스트 조회
		List<CommentDto> commentList = articleService.selectCommentList(articleIdx);
		
		mv.addObject("commentList", commentList);
		mv.addObject("article", article);
			
		return mv;
	}
	
	// 댓글 쓰기
	@RequestMapping(value="/article/{articleIdx}", method=RequestMethod.POST)
	public String writeComment(CommentDto commentDto) throws Exception {
		
		articleService.insertComment(commentDto);
		return "redirect:/article/{articleIdx}";
		
	}
	
	// 글 수정하기
	@RequestMapping(value="/article/{articleIdx}/edit", method=RequestMethod.PUT)
	public String editArticle(ArticleDto articleDto) throws Exception {
		
		//articleService.editArticle(articleDto);
		return "redirect:/article/{articleIdx}";
	}
	
	// 글 삭제하기
	@RequestMapping(value="/article/{articleIdx}/delete", method=RequestMethod.DELETE)
	public String deleteArticle(@PathVariable("articleIdx") int articleIdx) throws Exception {
		
		//articleService.deleteArticle(articleIdx);
		return "/myPage";
	}
	
	// 판매완료, 구매완료, 취소
	
	// article html 확인용
	@RequestMapping(value="/article", method=RequestMethod.GET)
	public String articleView() throws Exception {
		return "/board/article";
	}
	
}
