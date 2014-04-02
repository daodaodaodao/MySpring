package com.daodao.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.util.concurrent.ListenableFutureTask;

public class TestFuture {

	ListenableFutureTask<String> task10 = new ListenableFutureTask<String>(
			new Callable<String>() {
				@Override
				public String call() throws Exception {
					Thread.sleep(10 * 1000L);
					System.out.println("=======task10 execute");
					return "hello10";
				}
			});

	ListenableFutureTask<String> task5 = new ListenableFutureTask<String>(
			new Callable<String>() {
				@Override
				public String call() throws Exception {
					Thread.sleep(5 * 1000L);
					System.out.println("=======task5 execute");
					return "hello5";
				}
			});

	@Test
	public void testExecutorService() throws Exception {

		this.task10.addCallback(new ListenableFutureCallback<String>() {
			@Override
			public void onSuccess(String result) {
				System.out.println("===success callback 1");
			}

			@Override
			public void onFailure(Throwable t) {
			}
		});

		this.task10.addCallback(new ListenableFutureCallback<String>() {
			@Override
			public void onSuccess(String result) {
				System.out.println("===success callback 2");
			}

			@Override
			public void onFailure(Throwable t) {
			}
		});

		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.submit(this.task10);
		executorService.submit(this.task5);

		System.out.println(this.task10.get());
		System.out.println(this.task5.get());
	}

	@Test
	public void testThreadPool() throws Exception {

		this.task10.addCallback(new ListenableFutureCallback<String>() {
			@Override
			public void onSuccess(String result) {
				System.out.println("===success callback 10");
			}

			@Override
			public void onFailure(Throwable t) {
			}
		});

		this.task5.addCallback(new ListenableFutureCallback<String>() {
			@Override
			public void onSuccess(String result) {
				System.out.println("===success callback 5");
			}

			@Override
			public void onFailure(Throwable t) {
			}
		});

		ThreadPoolTaskExecutor poolTaskExecutor = new ThreadPoolTaskExecutor();
		// 线程池所使用的缓冲队列
		poolTaskExecutor.setQueueCapacity(200);
		// 线程池维护线程的最少数量
		poolTaskExecutor.setCorePoolSize(5);
		// 线程池维护线程的最大数量
		poolTaskExecutor.setMaxPoolSize(1000);
		// 线程池维护线程所允许的空闲时间
		poolTaskExecutor.setKeepAliveSeconds(30000);
		poolTaskExecutor.initialize();

		poolTaskExecutor.submit(this.task10);
		poolTaskExecutor.submit(this.task5);

		System.out.println(this.task10.get());
		System.out.println(this.task5.get());
	}
}
