package com.bitc.wub.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitc.wub.dto.UserDto;
import com.bitc.wub.service.UserService;

@Controller
public class WubUserController {
	
	private final Logger logger = LoggerFactory.getLogger(WubUserController.class);

	@Autowired
	private UserService userService;
	

//	로그인
	@RequestMapping(value="/user/login", method=RequestMethod.GET)
	public String login() throws Exception {
		return "/user/login";
	}

//	입력정보 바탕으로 DB에서 정보 있는지 확인
	@RequestMapping(value="/user/loginCheck", method=RequestMethod.POST)
	public String loginCheck(UserDto user, HttpServletRequest request) throws Exception {
		int count = userService.selectUserInfoYn(user);
		
		// 정보 있으면 세션에 저장
		if (count == 1) {
			HttpSession session = request.getSession();
			session.setAttribute("userId", user.getUserId());
			// 세션 id에서 idx로 수정
			// session.setAttribute("userIdx", user.getUserIdx());
			session.setMaxInactiveInterval(30*60);
			
			// 로그인 성공
			// 메인 페이지 접속 주소 보고 수정할 것
			return "/board/write";
		}
		
		else {
			// 로그인 실패
			return "redirect:/user/login";
		}
	}
	
	
//	로그아웃
	@RequestMapping(value="/user/logout", method =  RequestMethod.GET)
	public String logout(HttpSession session) throws Exception {
		session.invalidate();
		// 로그아웃시 메인 페이지 이동
		return "redirect:/main";
	}
	
	
	
//	마이페이지
	@RequestMapping(value="/user/mypage", method=RequestMethod.GET)
	public String mypage() throws Exception {
		return "/user/mypage";
	}
	
	
//	회원 정보
	@RequestMapping(value="/user/profile", method=RequestMethod.GET)
	public String profile() throws Exception {
		return "/user/profile";
	}
	
	
	
//	회원 탈퇴
	@RequestMapping(value="user/delete/{userIdx}", method=RequestMethod.DELETE)
	public String deleteUser(@PathVariable("userIdx") int userIdx) throws Exception {
		userService.deleteUser(userIdx);
		return "redirect:/main";
	}

	
//	나의 판매글
	@RequestMapping(value="/user/myBoardList", method=RequestMethod.GET)
	public String myBoardList() throws Exception {
		return "/user/myBoardList";
	}
	
	
//	마음에 드는 판매글
	@RequestMapping(value="/user/myRecommend", method=RequestMethod.GET)
	public String myRecommend() throws Exception {
		return "/user/myRecommend";
	}
	
	
	
//	회원가입
	@RequestMapping(value="/user/signUp", method=RequestMethod.GET)
	public String signUp() throws Exception {
		return "/user/signUp";
	}
	
	@RequestMapping(value="/user/signUp", method=RequestMethod.POST)
	public String insertUser(UserDto user) throws Exception {
		userService.insertUser(user);
		
		return "redirect:/user/signUpOk";
	}
	
//	가입 시 아이디 중복 검사
	@RequestMapping(value="/user/idCheck", method=RequestMethod.POST)
	@ResponseBody
	public String userIdCheckPOST (String userId) throws Exception {
		logger.info("idCheck() 진입");
		
		int result = userService.idCheck(userId);
		
		logger.info("결과값 = " + result);
		
		if(result != 0) {
			return "fail";
		}
		else {
			return "success";
		}
	}
	
	
}













