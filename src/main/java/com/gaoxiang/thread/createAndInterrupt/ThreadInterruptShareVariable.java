package com.gaoxiang.thread.createAndInterrupt;

/**
 * 使用共享变量 （shared variable）发出信号 告诉线程必须停止正在运行的任务 
 * 线程必须周期性的核查这一变量（尤其在冗余操作期间），然后有秩序地中止任务
 * @author gaoxiang
 *
 */
public class ThreadInterruptShareVariable implements Runnable {

	public volatile boolean stop = false;
	
	public void run() {
		while(!stop){
			System.out.println(Thread.currentThread().getName() + "is Running" +System.currentTimeMillis());
		}
		 System.out.println( "Thread exiting under request..." );
	}

}
