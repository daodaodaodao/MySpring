package com.daodao.hr.web;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.daodao.hr.entity.Dept;
import com.daodao.hr.service.DepartmentService;

@Controller
@RequestMapping(value = "/daodao/hr/dept")
public class DepartmentController {
	
	@Autowired
	DepartmentService deptService;

	@RequestMapping(value = "list")
	public String deptList(Model model) {
		
		String hql = "from Dept where deleted = false";
		
		Page<Dept> depts =  this.deptService.getHqlPageInfo(hql, 10, 1, Arrays.asList());
		
		model.addAttribute("depts", depts);
		
		return "/hr/departmentList";
	}
	
	@RequestMapping(value = "add")
	public String addDept(){
		
		return "/hr/addDept";
		
	}

}
