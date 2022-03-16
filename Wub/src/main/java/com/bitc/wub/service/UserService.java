package com.bitc.wub.service;

import com.bitc.wub.dto.UserDto;

public interface UserService {

//	로그인 체크
	int selectuserInfoYn(String userId, String userPw) throws Exception;
	
//	회원 가입
	void insertUser(UserDto user) throws Exception;
	
//	회원 탈퇴
	void deleteUser(int userIdx) throws Exception;

//	아이디 중복 체크
	int idCheck(String userId) throws Exception;



}
