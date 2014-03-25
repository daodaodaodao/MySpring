package com.daodao.hr.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.daodao.common.entity.BaseEntity;

@Entity
@Table(name = "HR_SALARY_GRADE")
public class Salgrade extends BaseEntity {

	private static final long serialVersionUID = 5728731193379946271L;
	
	private String gradeName;
	
	private double loSal;
	
	private double hiSal;

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public double getLoSal() {
		return loSal;
	}

	public void setLoSal(double loSal) {
		this.loSal = loSal;
	}

	public double getHiSal() {
		return hiSal;
	}

	public void setHiSal(double hiSal) {
		this.hiSal = hiSal;
	}

}
