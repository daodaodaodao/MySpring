package com.daodao.hr.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.daodao.common.web.Servlets;
import com.daodao.hr.entity.Dept;
import com.daodao.hr.service.DepartmentService;

@Controller
@RequestMapping(value = "/daodao/hr/dept")
public class DepartmentController {
	
	@Autowired
	DepartmentService deptService;

	@RequestMapping(value = "list")
	public String deptList(Model model,
			@RequestParam(value="search_deptNo",required=false) String deptNo,
			@RequestParam(value="search_deptName",required=false) String deptName) {
		
		String hql = "from Dept where deleted = false";
		List<Object> searchParams =  new ArrayList<Object>();
		
		if( StringUtils.isNotEmpty(deptNo) ){
			hql +=" and deptNo like ? ";
			searchParams.add("%"+deptNo+"%");
		}
		if( StringUtils.isNotEmpty(deptName) ){
			hql +=" and deptName like ? ";
			searchParams.add("%"+deptName+"%");
		}
		
		hql +=" order by createdAt desc ";
		
		Page<Dept> depts =  this.deptService.getHqlPageInfo(hql, 1, 10, searchParams);
		
		model.addAttribute("depts", depts);
		
		return "/hr/departmentList";
	}
	
	@RequestMapping(value = "addForm")
	public String addDeptForm(){
		
		return "/hr/departmentForm";
		
	}
	
	@RequestMapping(value = "updateForm/{id}")
	public String updateDeptForm(Model model,
			ServletRequest request,
			@PathVariable(value="id") String id){

		Map<String, Object> searchParams = Servlets.getParametersStartingWith(
				request, "search_");
		
		model.addAttribute("searchParams", searchParams);
		model.addAttribute("dept", this.deptService.getById(id));
		
		return "/hr/departmentForm";
		
	}
	
	@RequestMapping(value = "update")
	public String updateDept(ServletRequest request,
			@ModelAttribute Dept dept,
			RedirectAttributes redirectAttributes){
		
		this.deptService.update(dept);

		Map<String, Object> searchParams = Servlets.getParametersStartingWith(
				request, "search_");
		
		redirectAttributes.addAllAttributes(searchParams);
		
		return "redirect:/daodao/hr/dept/list";
		
	}
	
	@RequestMapping(value = "delete/{id}")
	public String deleteDept(Model model,
			@PathVariable(value="id") String id){
		
		this.deptService.deleteForFlag(id);
		
		return "redirect:/daodao/hr/dept/list";
	}
			
	
	@ModelAttribute
	public Dept preLoad(
			@RequestParam(value="id",required=false) String id){
		
		if( StringUtils.isEmpty(id) ){
			return new Dept();
		} else {
			return this.deptService.getById(id);
		}
	}

}
