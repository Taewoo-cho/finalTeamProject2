package com.bitc.wub.service;

import java.util.List;

import com.bitc.wub.dto.UserArticleDto;
import com.bitc.wub.dto.UserDto;

public interface UserService {
	
//	로그인
	int login(UserDto user) throws Exception;
	
	
	
//	회원 가입
	void insertUser(UserDto user) throws Exception;
	
//	아이디 중복 체크
	int idCheck(String userId) throws Exception;
	
//	회원 정보 수정
	void updateUser(UserDto user) throws Exception;
	
//	회원 탈퇴
	void deleteUser(int userIdx) throws Exception;


//	내가 작성한 글
	List<UserArticleDto> selectUserArticle() throws Exception;
	
//	관심 있는 판매글


}
