package com.bitc.wub.service;

import java.util.List;

import com.bitc.wub.dto.ArticleDto;
import com.bitc.wub.dto.CommentDto;

public interface ArticleService {

	ArticleDto selectArticleDetail(int articleIdx) throws Exception;

	List<CommentDto> selectCommentList(int articleIdx) throws Exception;

	void insertComment(CommentDto commentDto) throws Exception;

	void countHitCnt(int articleIdx) throws Exception;

}
