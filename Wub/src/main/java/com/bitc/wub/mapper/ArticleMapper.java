package com.bitc.wub.mapper;

import java.util.List;

import com.bitc.wub.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ArticleMapper {

	
	// 상세 글 보기
	ArticleDto selectArticleDetail(int bookIdx) throws Exception;

	// 댓글 리스트
	List<CommentDto> selectCommentList(int bookIdx) throws Exception;

	// 댓글 쓰기
	void insertComment(CommentDto commentDto) throws Exception;

	// 조회수 상승
	void countHitCnt(int bookIdx) throws Exception;

	// 게시글의 이미지 정보를 가져옴
	List<ImgDto> selectArticleImgList(int bookIdx) throws Exception;

	// 게시글 작성(이미지 제외)
	void insertArticle(ArticleDto articleDto) throws Exception;

	// 게시글 작성(이미지)
	void insertArticleFileList(List<ImgDto> list) throws Exception;

	// 도서 분류
	List<TagDto> tagMainCategory() throws Exception;
	List<TagDto> tagDetailCategory(@Param("tagIdx") int tagIdx, @Param("tagIdx2") int tagIdx2) throws Exception;

	// idx -> String 바꾸기
	String selectMainCategory(String mainCategory) throws Exception;
	String selectDetailCategory(String detailCategory) throws Exception;

	// 글 수정
	void editArticle(ArticleDto articleDto) throws Exception;
	void editImgArticle(int bookIdx) throws Exception;

	// 유저닉네임 
	String selectUserInfo(int userIdx) throws Exception;

	// 유저 주소
	String selectUserLocalInfo(int userIdx) throws Exception;

	// 게시글, 코멘트 삭제
	void deleteArticle(int bookIdx) throws Exception;
	void deleteComment(int bookIdx) throws Exception;

	// 게시글이 받은 추천 수(article)
	int selectCountRecommend(int bookIdx) throws Exception;

	// 추천 조작 방지 1회만 적용
	int selectRecommend(RecommendDto recommend) throws Exception;
	// 추천
	void insertRecommend(RecommendDto recommend) throws Exception;
}
