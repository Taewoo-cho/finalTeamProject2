package com.bitc.wub.controller;

import java.util.List;

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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bitc.wub.dto.UserArticleDto;
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
	
	//입력정보 바탕으로 DB에서 정보 있는지 확인
	@RequestMapping(value="/user/loginCheck", method=RequestMethod.POST)
	public String login(UserDto user, HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		int idx = userService.login(user);
		System.out.println(idx);
		
		if (idx == 0) {
			// 로그인 실패
			int result = 0;
			rttr.addFlashAttribute("result", result);
			return "redirect:/user/login";
		}
		
		else {
			// 로그인 성공
			HttpSession session = request.getSession();
			session.setAttribute("userIdx", idx);
			
			System.out.println(session.getAttribute("userIdx"));
			session.setMaxInactiveInterval(30*60);
			
			return "redirect:/main";
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
	@RequestMapping(value="/user/myArticle", method=RequestMethod.GET)
	public ModelAndView myArticle() throws Exception {
		ModelAndView mv = new ModelAndView("user/myArticle");
		
		List<UserArticleDto> myArticle = userService.selectUserArticle();
		mv.addObject("myArticle", myArticle);
		
		return mv;
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













