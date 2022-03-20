package com.bitc.wub.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bitc.wub.dto.UserArticleDto;
import com.bitc.wub.dto.UserDto;

@Mapper
public interface UserMapper {
	
//	로그인
	int login(UserDto user) throws Exception;

//	회원 가입
	public void insertUser(UserDto user) throws Exception;
	
//	아이디 중복 검사
	public int idCheck(String userId) throws Exception;

//	회원 탈퇴
	public void deleteUser(int userIdx) throws Exception;
	
//	회원 정보 수정
	public void updateUser(UserDto user) throws Exception;

//	내가 작성한 판매글
	public List<UserArticleDto> selectUserArticle() throws Exception;
	
	

}
