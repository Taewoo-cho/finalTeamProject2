package com.bitc.wub.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bitc.wub.dto.UserArticleDto;
import com.bitc.wub.dto.UserCommentDto;
import com.bitc.wub.dto.UserDto;
import com.bitc.wub.dto.UserRecommendDto;

@Mapper
public interface UserMapper {
	
//	로그인
	public int login(UserDto user) throws Exception;
	
//	정보 가져오기
	public UserDto userInfo(String userId) throws Exception;

//	회원 가입
	public void insertUser(UserDto user) throws Exception;
	
//	아이디 중복 검사
	public int idCheck(String userId) throws Exception;

//	회원 탈퇴
	public void deleteUser(int userIdx) throws Exception;
	
//	회원 정보 수정 페이지
	public UserDto openUser(int userIdx) throws Exception;
	
//	회원 정보 수정
	public void updateUser(UserDto user) throws Exception;
	
//	내가 작성한 판매글
	public List<UserArticleDto> selectUserArticle(int userIdx) throws Exception;

//	마음에 드는 판매글
	public List<UserRecommendDto> selectUserRecommend(int userIdx) throws Exception;

//	내가 작성한 댓글
	public List<UserCommentDto> selectUserComment(int userIdx) throws Exception;
	
	

}
