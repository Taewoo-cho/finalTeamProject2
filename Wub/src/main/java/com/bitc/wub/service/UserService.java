package com.bitc.wub.service;

import java.util.List;

import com.bitc.wub.dto.UserArticleDto;
import com.bitc.wub.dto.UserCommentDto;
import com.bitc.wub.dto.UserDto;
import com.bitc.wub.dto.UserRecommendDto;

public interface UserService {
	
//	로그인
	public int login(UserDto user) throws Exception;
	
//	정보 가져오기
	public UserDto userInfo(String userId) throws Exception;
	
	
//	회원 가입
	public void insertUser(UserDto user) throws Exception;
	
//	아이디 중복 체크
	public int idCheck(String userId) throws Exception;
	
//	회원 정보 수정 페이지
	public UserDto openUser(int userIdx) throws Exception;
	
//	회원 정보 수정
	public void updateUser(UserDto user) throws Exception;
	
//	회원 탈퇴
	public int deleteUser(UserDto user) throws Exception;


//	내가 작성한 글
	public List<UserArticleDto> selectUserArticle(int userIdx) throws Exception;

//	관심 있는 판매글
	public List<UserRecommendDto> selectUserRecommend(int userIdx) throws Exception;

//	내가 작성한 댓글
	public List<UserCommentDto> selectUserComment(int userIdx) throws Exception;



}
