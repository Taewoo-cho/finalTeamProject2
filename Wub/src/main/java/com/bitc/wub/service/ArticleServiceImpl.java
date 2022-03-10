package com.bitc.wub.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bitc.wub.dto.ArticleDto;
import com.bitc.wub.dto.CommentDto;
import com.bitc.wub.mapper.ArticleMapper;

public class ArticleServiceImpl implements ArticleService {
	
	@Autowired
	private ArticleMapper articleMapper;

	// 상세 글 보기
	@Override
	public ArticleDto selectArticleDetail(int articleIdx) throws Exception {
		
		return articleMapper.selectArticleDetail(articleIdx);
	}

	// 댓글 리스트
	@Override
	public List<CommentDto> selectCommentList(int articleIdx) throws Exception {
		
		return articleMapper.selectCommentList(articleIdx);
	}
	
	// 댓글 쓰기
	@Override
	public void insertComment(CommentDto commentDto) throws Exception {
		articleMapper.insertComment(commentDto);
		
	}
	
	// 조회수 상승
	@Override
	public void countHitCnt(int articleIdx) throws Exception {
		articleMapper.countHitCnt(articleIdx);
		
	}

}
