package com.bitc.wub.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bitc.wub.dto.ArticleDto;
import com.bitc.wub.dto.CommentDto;
import com.bitc.wub.dto.TagDto;

public interface ArticleService {

	ArticleDto selectArticleDetail(int BookIdx) throws Exception;

	List<CommentDto> selectCommentList(int BookIdx) throws Exception;

	void insertComment(CommentDto commentDto) throws Exception;

	void countHitCnt(int BookIdx) throws Exception;

	void insertArticle(ArticleDto articleDto, MultipartHttpServletRequest multiFile) throws Exception;

	List<TagDto> tagMainCategori() throws Exception;

	List<TagDto> tagDetailCategori(int tagIdx) throws Exception;


}
