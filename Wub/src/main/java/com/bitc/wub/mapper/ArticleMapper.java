package com.bitc.wub.mapper;

import java.util.List;

import com.bitc.wub.dto.ArticleDto;
import com.bitc.wub.dto.CommentDto;

public interface ArticleMapper {

	// 상세 글 보기
	ArticleDto selectArticleDetail(int articleIdx) throws Exception;

	// 댓글 리스트
	List<CommentDto> selectCommentList(int articleIdx) throws Exception;

	// 댓글 쓰기
	void insertComment(CommentDto commentDto) throws Exception;

	void countHitCnt(int articleIdx) throws Exception;

}
