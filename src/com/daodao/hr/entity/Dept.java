package com.daodao.hr.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

import com.daodao.common.entity.BaseEntity;

@Entity
@Table(name = "HR_DEPARTMENT")
@XmlRootElement
public class Dept extends BaseEntity {

	private static final long serialVersionUID = -6387353793509402364L;
	
	@NotEmpty(message = "部门编号不能为空！")
	private String deptNo;
	
	@NotEmpty(message = "部门名称不能为空！")
	private String deptName;

	@XmlAttribute
	public String getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}

	@XmlAttribute
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	

}
