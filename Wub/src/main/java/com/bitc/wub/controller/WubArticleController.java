package com.bitc.wub.controller;



import java.util.List;

import com.bitc.wub.dto.RecommendDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
		
		List<TagDto> tagList = articleService.tagMainCategory();
		
		mv.addObject("tagList", tagList);
		
		return mv;
	}
	
	
	// 세부 분류
	@ResponseBody
	@RequestMapping(value="/article/write/detailCategory", method=RequestMethod.POST)
	public List<TagDto> detailCategory(@RequestBody TagDto tagDto) throws Exception {
		
		int mainCategory = Integer.parseInt(tagDto.getTagIdx());
		
		List<TagDto> tagList = articleService.tagDetailCategory(mainCategory);
		
		return tagList;
	}
	
	
	// 글쓰기 페이지에서 DB로 INSERT(이미지 첨부)
	@RequestMapping(value="/article/write", method=RequestMethod.POST) 
	public String insertArticle(ArticleDto articleDto, MultipartHttpServletRequest multiFiles) throws Exception {
	
		articleService.insertArticle(articleDto, multiFiles); 
		
		String bookIdx = Integer.toString(articleDto.getBookIdx());
		
		return "redirect:/article/openArticle?bookIdx=" + bookIdx ; // / + 메인페이지 혹은 다른페이지로 리다이렉트
	}

	//메인페이지
	@RequestMapping(value="/main", method=RequestMethod.GET)
	public ModelAndView mainPage() throws Exception {
		ModelAndView mv = new ModelAndView("board/main");

		// 최신글 리스트
		List<ArticleDto> latestPostList = articleService.selectLatestPost();

		// 조회수 리스트
		List<ArticleDto> hitPostList = articleService.selectHitPost();

		// 좋아요 리스트
		List<ArticleDto> followPostList = articleService.selectFollowPost();

		mv.addObject("latestPost", latestPostList);
		mv.addObject("hitPost", hitPostList);
		mv.addObject("followPost", followPostList);

		return mv;
	}

	// 주제별 메인 페이지
	@RequestMapping(value="/main/list", method=RequestMethod.GET)
	public ModelAndView mainPostPage(@RequestParam("post") String post) throws Exception {
		ModelAndView mv = new ModelAndView("/board/mainList");

		List<ArticleDto> list = null;
		switch(post) {
			case "latest":
				list = articleService.selectLatestPosts();
				break;
			case "hit":
				list = articleService.selectHitPosts();
				break;
			case "follow" :
				list = articleService.selectFollowPosts();
				break;
		}
		mv.addObject("list", list);
		return mv;

	}

	// 상세 글 읽기
	@RequestMapping(value = "/article/openArticle", method=RequestMethod.GET)
	public ModelAndView article(@RequestParam("bookIdx") int bookIdx) throws Exception {
		ModelAndView mv = new ModelAndView("/board/article");
		
		// 게시글(이미지 조회)
		ArticleDto article = articleService.selectArticleDetail(bookIdx);
		
		
		// 댓글 리스트 조회
		List<CommentDto> commentList = articleService.selectCommentList(bookIdx);
		
		
		mv.addObject("commentList", commentList);
		mv.addObject("article", article);
			
		return mv;
	}
	
	// 댓글 쓰기
	@RequestMapping(value="/article/comment", method=RequestMethod.POST)
	public String writeComment(CommentDto commentDto) throws Exception {

		articleService.insertComment(commentDto);
		String bIdx = Integer.toString(commentDto.getBookIdx());
		return "redirect:/article/openArticle?bookIdx=" + bIdx;
		
	}

	// 댓글 삭제
	@RequestMapping(value="/article/comment/delete", method=RequestMethod.DELETE)
	@ResponseBody
	public void deleteComment(CommentDto commentDto) throws Exception {
		articleService.deleteComment(commentDto);
	}
	
	// 글 수정 페이지
	@RequestMapping(value="/article/edit", method=RequestMethod.GET)
	public ModelAndView articleEdit(@RequestParam("bookIdx") int bookIdx) throws Exception {
		ModelAndView mv = new ModelAndView("/board/editArticle");
		
		List<TagDto> tagList = articleService.tagMainCategory();
		ArticleDto article = articleService.selectArticleDetail(bookIdx);
		
		mv.addObject("tagList", tagList);
		mv.addObject("article", article);
		
		return mv;
	}
	
	// 글 수정하기
	@RequestMapping(value="/article/editArticle", method=RequestMethod.POST)
	public String editArticle(@RequestParam("bookIdx") int bookIdx, ArticleDto articleDto, MultipartHttpServletRequest multiFiles) throws Exception {
		
		articleService.editArticle(articleDto, multiFiles);
		
		String idx = Integer.toString(bookIdx);
		
		return "redirect:/article/openArticle?bookIdx=" + idx;
	}
	
	// 글 삭제하기
	@RequestMapping(value="/article/delete", method=RequestMethod.DELETE)
	public String deleteArticle(@RequestParam("bookIdx") int bookIdx) throws Exception {
		
		articleService.deleteArticle(bookIdx);
		return "board/write";
	}

	// 추천
	@RequestMapping(value = "/article/recommend", method=RequestMethod.POST)
	@ResponseBody
	public String recommendArticle(RecommendDto recommend) throws Exception {
		String flag = articleService.recommendArticle(recommend);
		return flag;

	}

	// 판매완료, 구매완료
	@RequestMapping(value="/article/soldYn", method=RequestMethod.PUT)
	@ResponseBody
	public void soldYn(int bookIdx) throws Exception {
		articleService.articleSoldYn(bookIdx);
	}


	


	
	
}
