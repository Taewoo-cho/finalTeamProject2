package com.bitc.wub.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
			session.setAttribute("userIdx", user.getUserIdx());
			session.setMaxInactiveInterval(30*60);
			
			// 로그인 성공
			return "/board/write";
		}
		
		else {
			// 로그인 실패
			return "redirect:/user/login";
		}
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













