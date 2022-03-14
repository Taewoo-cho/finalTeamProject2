package com.bitc.wub.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.bitc.wub.dto.ArticleDto;
import com.bitc.wub.dto.CommentDto;
import com.bitc.wub.dto.ImgDto;
import com.bitc.wub.dto.TagDto;

@Mapper
public interface ArticleMapper {

	// 상세 글 보기
	ArticleDto selectArticleDetail(int BookIdx) throws Exception;

	// 댓글 리스트
	List<CommentDto> selectCommentList(int BookIdx) throws Exception;

	// 댓글 쓰기
	void insertComment(CommentDto commentDto) throws Exception;

	// 조회수 상승
	void countHitCnt(int BookIdx) throws Exception;

	// 게시글의 이미지 정보를 가져옴
	List<ImgDto> selectArticleImgList(int BookIdx) throws Exception;

	// 게시글 작성(이미지 제외)
	void insertArticle(ArticleDto articleDto) throws Exception;

	// 게시글 작성(이미지)
	void insertArticleFileList(List<ImgDto> imgList) throws Exception;

	List<TagDto> tagMainCategori() throws Exception;

	List<TagDto> tagDetailCategori(@Param("tagIdx") int tagIdx, @Param("tagIdx2") int tagIdx2) throws Exception;
	
	

}
