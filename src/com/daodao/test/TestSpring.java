package com.daodao.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.daodao.hr.entity.Dept;
import com.daodao.hr.service.DepartmentService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml",
		"classpath:applicationContext-daodao.xml" })
public class TestSpring {

	@Autowired
	DepartmentService deptService;

	@Autowired
	ThreadPoolTaskExecutor taskExecutor;

	@Test
	public void testThreadPool() throws Exception {

		Thread myThread = new MyThread(this.deptService);
		this.taskExecutor.execute(myThread);
		
		int count = this.taskExecutor.getActiveCount();
		
		while( count > 0 ){
			System.out.println(count);
			Thread.sleep(100);
			count = this.taskExecutor.getActiveCount();
		};
		System.out.println("end");

	}

	private class MyThread extends Thread {

		DepartmentService deptService;

		private MyThread(DepartmentService deptService) {
			this.deptService = deptService;
		}

		public void run() {

			Dept dept = new Dept();
			dept.setDeptName("jsonXmlName");
			dept.setDeptNo("jsonXmlNo");

			this.deptService.update(dept);

		}
	}

}
