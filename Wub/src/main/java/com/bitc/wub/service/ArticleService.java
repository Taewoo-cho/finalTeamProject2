package com.bitc.wub.service;

import java.util.List;

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

	// 게시글 쓰기
	void insertArticle(ArticleDto articleDto, MultipartHttpServletRequest multiFile) throws Exception;

	// 도서 분류
	List<TagDto> tagMainCategory() throws Exception;

	List<TagDto> tagDetailCategory(int tagIdx) throws Exception;

	// 게시글 수정
	void editArticle(ArticleDto articleDto, MultipartHttpServletRequest multiFiles) throws Exception;


}
