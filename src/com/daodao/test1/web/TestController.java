package com.daodao.test1.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/daodao/test1/test")
public class TestController {
	

	@RequestMapping(value = "")
	public String test(Model model) {
		
		return "/test1/test";
	}

}
