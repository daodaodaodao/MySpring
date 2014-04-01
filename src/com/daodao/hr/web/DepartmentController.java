package com.daodao.hr.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.daodao.common.web.Servlets;
import com.daodao.hr.entity.Dept;
import com.daodao.hr.service.DepartmentService;

@RestController
@RequestMapping(value = "/daodao/hr/dept")
public class DepartmentController {
	
	@Autowired
	DepartmentService deptService;

	@RequestMapping(value = "list")
	public ModelAndView deptList(//Model model,
			@RequestParam(value="search_deptNo",required=false) String deptNo,
			@RequestParam(value="search_deptName",required=false) String deptName,
			@RequestParam(value="search_page",required=false) String currentPage) {
		
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
		
		int page = 1;
		if( StringUtils.isNotEmpty(currentPage) ){
			page = Integer.valueOf(currentPage);
			
		}
		
		Page<Dept> depts =  this.deptService.getHqlPageInfo(hql, page, 10, searchParams);
		
		//model.addAttribute("depts", depts);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("depts", depts);
		modelAndView.setViewName("/hr/departmentList");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "addForm")
	public ModelAndView addDeptForm(){
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/hr/departmentForm");
		
		return modelAndView;
		
	}
	
	@RequestMapping(value = "updateForm/{id}")
	public ModelAndView updateDeptForm(//Model model,
			ServletRequest request,
			@PathVariable(value="id") String id){

		Map<String, Object> searchParams = Servlets.getParametersStartingWith(
				request, "search_");
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("dept", this.deptService.getById(id));
		modelAndView.addObject("searchParams", searchParams);
		modelAndView.setViewName("/hr/departmentForm");
		
		return modelAndView;
		
	}
	
	@RequestMapping(value = "update")
	public ModelAndView updateDept(@Valid @ModelAttribute Dept dept,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes,
			ServletRequest request){
		
		ModelAndView modelAndView = new ModelAndView();
		
        if(result.hasErrors()) {
    		modelAndView.addObject("dept", dept);
    		modelAndView.setViewName("/hr/departmentForm");
    		return modelAndView;
        }  
		
		this.deptService.update(dept);

		Map<String, Object> searchParams = Servlets.getParametersStartingWith(
				request, "search_");
		modelAndView = new ModelAndView("redirect:/daodao/hr/dept/list", searchParams);
		
		return modelAndView;
		
	}
	
	@RequestMapping(value = "delete/{id}")
	public ModelAndView deleteDept(Model model,ServletRequest request,
			@PathVariable(value="id") String id){
		
		this.deptService.deleteForFlag(id);

		Map<String, Object> searchParams = Servlets.getParametersStartingWith(
				request, "search_");
		
		ModelAndView modelAndView = new ModelAndView("redirect:/daodao/hr/dept/list",searchParams);
		
		return modelAndView;
	} 
	
    @RequestMapping("/test") 
    public Dept viewJsonXml() {
		Dept dept = new Dept();  
		dept.setDeptName("jsonXmlName");
		dept.setDeptNo("jsonXmlNo");
		return dept;  
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
