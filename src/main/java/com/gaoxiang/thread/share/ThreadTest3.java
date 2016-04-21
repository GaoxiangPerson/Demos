package com.gaoxiang.thread.share;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * volatitle  保证了共享变量的可见性
 * 被称之为 轻量级 的synchronized，在某些情况下 并synchronized 的开销小
 * 因为它不会引起线程上下文的切换和调度
 * 
 * 它可修饰的共享变量有：实例变量、静态变量、数组元素 ----都存放在堆内存
 * Volatile 变量具有 synchronized 的可见性特性，但是不具备原子特性
 * 
 * 正确使用Volatile变量的条件--同时满足两个条件：
 * 1、对变量的写操作、不依赖于当前值
 * 2、该变量没有包含在 具有 其他变量的不变式中
 * 可以被写入 volatile 变量的这些有效值独立于任何程序的状态，包括变量的当前状态
 * 用法示例：
 * a、状态标志   布尔状态标志，用来指示发生一个重要操作
 * b、一次性安全发布  
 * c、独立观察（independent observation）
 * d、“volatile bean” 模式
 *
 *
 *线程组  ThreadGroup ---新建、一些方法等等
 *
 *
 *Daemon 线程 守护线程，设置一定要在线程start之前进行设置  setDaemon(true)
 *当程序中没有前台线程时，后台线程就会被jvm中断掉
 *
 */
public class ThreadTest3 {

	public static void main(String[] args) {
		
		//创建线程组
		ThreadGroup searchGroup = new ThreadGroup("Searcher");
		Result result = new Result();
		//线程任务task
		SearchTask task = new SearchTask(result);
		
		for(int i=0;i<5;i++){
			//将线程加入到线程组里  并启动线程 启动后 主线程休眠1S
			Thread thread = new Thread(searchGroup, task);
			thread.start();
			
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		/**
		 * 测试线程组的几个方法
		 * activeCount()---线程组中的线程个数
		 * list()--将线程组信息 标准输出至控制台
		 * enumerate(Thread[] list) --将线程组及其子组中国的所有活动线程 复制到指定数组中
		 * interrupt() --中断此线程组 中的所有线程
		 * 
		 * 
		 */
		System.out.printf("Number of Threads: %d\n",searchGroup. activeCount());
		System.out.printf("Information about the Thread Group\n");
		searchGroup.list();
		
		
		
	}
	
}



class DaemonThread {
	
	/**
	 * main 线程会休眠3S
	 * main线程在休眠过程中 守护线程会一直运行
	 * 但是当main线程 休眠完成后，执行完成后 守护线程就死掉了
	 * 
	 * 如果将t.setDaemon(true);给去掉，分线程就会一直运行
	 * 
	 */
	public static void main(String[] args) throws InterruptedException {
		
		Runnable r = new Runnable() {
			public void run() {
				String daemon = (Thread.currentThread().isDaemon() ? "daemon"
						: "not daemon");
				while (true) {
					System.out.println("I'm running at " + new Date()
							+ ", I am " + daemon);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						System.out.println("I was interrupt, I am " + daemon);
					}
				}
			}

		};

		Thread t = new Thread(r);
		t.setDaemon(true);
		t.start();
		Thread.sleep(3000);
		System.out.println("main thread exits");
	}
		
	
	/**
	 * 线程的优先级
	 * setPriority()
	 */
	
	
	/**
	 * synchronized
	 * 修饰代码块，-----需要单独加锁，是某个对象
	 * 修饰方法-----锁就是 调用该方法的实例对象，需要调用this.wait()
	 * 修饰静态方法----锁是这个类的 字节码 对象 是这个类的锁
	 * 修饰类--和修饰静态方法就类似额
	 */
	
	
}
