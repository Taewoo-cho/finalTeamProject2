package com.bitc.wub.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitc.wub.dto.UserArticleDto;
import com.bitc.wub.dto.UserCommentDto;
import com.bitc.wub.dto.UserDto;
import com.bitc.wub.dto.UserRecommendDto;
import com.bitc.wub.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	
//	로그인
	@Override
	public int login(UserDto user) throws Exception {
		return userMapper.login(user);
	}
	
//	회원 정보 가져오기
	@Override
	public UserDto userInfo(String userId) throws Exception {
		return userMapper.userInfo(userId);
	}

	
//	회원 가입
	@Override
	public void insertUser(UserDto user) throws Exception {
		userMapper.insertUser(user);
		
	}
	
//	아이디 중복 체크
	@Override
	public int idCheck(String userId) throws Exception {
		return userMapper.idCheck(userId);
	}

	
//	회원 탈퇴
	@Override
	public void deleteUser(int userIdx) throws Exception {
		userMapper.deleteUser(userIdx);
		
	}
	
// 회원 정보 수정 페이지
	@Override
	public UserDto openUser(int userIdx) throws Exception {
		return userMapper.openUser(userIdx);
	}
		
//	회원 정보 수정
	@Override
	public void updateUser(UserDto user) throws Exception {
		userMapper.updateUser(user);		
	}

//	내가 작성한 판매글
	@Override
	public List<UserArticleDto> selectUserArticle(int userIdx) throws Exception {
		return userMapper.selectUserArticle(userIdx);
	}
	

//	관심 있는 판매글
	@Override
	public List<UserRecommendDto> selectUserRecommend(int userIdx) throws Exception {
		return userMapper.selectUserRecommend(userIdx);
	}

//	내가 작성한 댓글
	@Override
	public List<UserCommentDto> selectUserComment(int userIdx) throws Exception {
		return userMapper.selectUserComment(userIdx);
	}

	

	

}
