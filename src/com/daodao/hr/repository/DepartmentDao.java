package com.daodao.hr.repository;

import java.util.List;

import com.daodao.common.repository.BaseDao;
import com.daodao.hr.entity.Dept;

public interface DepartmentDao  extends BaseDao<Dept>{
	
	public List<Dept> getByDeleted(boolean del);

}
