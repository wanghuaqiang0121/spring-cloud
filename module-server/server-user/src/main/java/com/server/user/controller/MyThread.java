package com.server.user.controller;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyThread extends Thread implements Runnable{
	
	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			if (i % 2 == 0) {
				System.out.println(i);
			}
		}
	}
	
	public static void main(String[] args) {
		
		Lock lock = new ReentrantLock();
		lock.lock();
		
		
		ExecutorService service = Executors.newFixedThreadPool(10);
		service.execute(new MyThread());
		service.execute(new MyThread());
		service.submit(new MyThread());
		
		service.shutdown();
		
		// 状态的状态  新建--调用start()就绪状态 等待cpu配资源--执行run 执行中--执行完了就死亡了，中途可能阻塞
		
		// 这是继承Thread的方式
		new Thread(){
			@Override
			public void run() {
				//这里是一个线程
			}
		}.start();
		
		
		//这里是实现Runnable的方式
		//这里的new Runnable() 是一个匿名的对象
		 new Thread(new Runnable() {
			
			@Override
			public void run() {
				//这里是一个线程
				
			}
		}).start();
		
		 @SuppressWarnings({ "unchecked", "rawtypes" })
		FutureTask futureTask = new FutureTask(new Callable() {
			@Override
			public Object call() throws Exception {
				
				return null;
			}
		});
		 new Thread(futureTask).start();
	}
}

