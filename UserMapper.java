package com.bitc.wub.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.bitc.wub.dto.UserDto;

@Mapper
public interface UserMapper {
	
//	로그인 체크
	int selectMemberInfoYn(@Param("userId") String userId, @Param("userPw") String userPw) throws Exception;

//	회원 가입
	public void insertUser(UserDto user) throws Exception;

//	회원 탈퇴
	public void deleteUser(int userIdx) throws Exception;
	
//	아이디 중복 검사
	public int idCheck(String userId) throws Exception;
	

}
