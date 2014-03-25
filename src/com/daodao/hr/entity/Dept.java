package com.daodao.hr.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.daodao.common.entity.BaseEntity;

@Entity
@Table(name = "HR_DEPARTMENT")
public class Dept extends BaseEntity {

	private static final long serialVersionUID = -6387353793509402364L;
	
	private String deptNo;
	
	private String deptName;

	public String getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	

}
