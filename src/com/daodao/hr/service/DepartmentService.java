package com.daodao.hr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daodao.common.service.BaseService;
import com.daodao.hr.entity.Dept;
import com.daodao.hr.repository.DepartmentDao;

@Service
public class DepartmentService extends BaseService<Dept> {
	
	@Autowired
	protected DepartmentDao deptDao;

//	@Override
//	protected BaseDao<Dept> getDao() {
//		return deptDao;
//	}
	
	public List<Dept> getAll(){
		return this.deptDao.getByDeleted(false);
		
	}

}
