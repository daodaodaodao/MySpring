package com.daodao.hr.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.format.annotation.DateTimeFormat;

import com.daodao.common.entity.BaseEntity;

@Entity
@Table(name = "HR_EMPLOYEE")
public class Emp extends BaseEntity {

	private static final long serialVersionUID = 8157095733953865151L;
	
	private String name;
	
	private String empNo;
	
	private String sex;
	
	private String job;
	
	private double sal;
	
	private Dept dept;
	
	private Date birthday;
	
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public double getSal() {
		return sal;
	}

	public void setSal(double sal) {
		this.sal = sal;
	}
    
    @ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "dept_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Transient
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	

}
