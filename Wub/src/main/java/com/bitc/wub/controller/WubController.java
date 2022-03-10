package com.bitc.wub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WubController {
	
	@RequestMapping(value ="/")
	public String index() throws Exception {
		return "index";
	}
}
