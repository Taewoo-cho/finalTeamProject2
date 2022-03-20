package com.bitc.wub.service;

import java.util.List;

import com.bitc.wub.dto.RecommendDto;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bitc.wub.dto.ArticleDto;
import com.bitc.wub.dto.CommentDto;
import com.bitc.wub.dto.TagDto;

public interface ArticleService {

	// 상세 글 읽기
	ArticleDto selectArticleDetail(int bookIdx) throws Exception;

	// 댓글 목록 읽기
	List<CommentDto> selectCommentList(int bookIdx) throws Exception;

	// 댓글 쓰기
	void insertComment(CommentDto commentDto) throws Exception;

	// 댓글 삭제
	void deleteComment(CommentDto commentDto) throws Exception;

	// 게시글 쓰기
	void insertArticle(ArticleDto articleDto, MultipartHttpServletRequest multiFile) throws Exception;

	// 도서 분류
	List<TagDto> tagMainCategory() throws Exception;
	List<TagDto> tagDetailCategory(int tagIdx) throws Exception;

	// 게시글 수정
	void editArticle(ArticleDto articleDto, MultipartHttpServletRequest multiFiles) throws Exception;

	// 게시글 비활성화
	void deleteArticle(int bookIdx) throws Exception;

	// 추천
	String recommendArticle(RecommendDto recommend) throws Exception;

	// 거래완료
	void articleSoldYn(int bookIdx) throws Exception;

	// 메인 페이지
    List<ArticleDto> selectLatestPost() throws Exception;
	List<ArticleDto> selectHitPost() throws Exception;
	List<ArticleDto> selectFollowPost() throws Exception;

	// 리스트 페이지
	List<ArticleDto> selectLatestPosts() throws Exception;
	List<ArticleDto> selectHitPosts() throws Exception;
	List<ArticleDto> selectFollowPosts() throws Exception;


}
