package com.daodao.hr.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/daodao/hr/dept")
public class DepartmentController {

	@RequestMapping(value = "list")
	public String test(Model model) {
		
		
		
		return "/hr/departmentList";
	}

}
