package com.gaoxiang.thread.threadCommunication;

public class ThreadTest2 {

	/**
	 * 线程之间的通信主要方法是
	 * wait(),notify(),notifyall()
	 * 
	 * 还有学习 jion() 方法， yield() 方法
	 */
	
	/**
	 * 最简单的方法 说明 wait() ,notifyall()
	 * 
	 * 生产者--消费者  且生产1个  消费1个的模式
	 */
	
	/**
	 * 核心代码 是Task类的 生产  消费方法
	 * Task类称为资源类
	 */
	
	/*public static void main(String[] args) {
		
		Task t = new Task();
		//20个生产者线程
		for(int i=0;i<20;i++){
			Producer p1= new Producer(t);
			p1.setName("producer"+i);
			p1.start();
		}
		//20个消费者线程
		for(int j=0;j<20;j++){
			Consumer c1= new Consumer(t);
			c1.setName("Consumer"+j);
			c1.start();	
		}
	}
*/	
	
	/**
	 * 介绍 jion() 方法， yield() 方法
	 * jion()  它是一个静态的原生(native)方法，当前正在执行的线程把运行机会交给线程池中拥有相同优先级的线程
	 * 			线程从运行状态转到可运行状态，而不是等待或阻塞状态
	 * yield() 
	 * 		使得一个线程在另一个线程结束后再执行。如果join()方法在一个线程实例上调用，
	 * 		当前运行着的线程将阻塞直到这个线程实例完成了执行
	 */
	
/*	public static void main(String[] args) {
		Set s = new Set();
		Get g = new Get();
		
		//分别设置线程优先级
		s.setPriority(Thread.MIN_PRIORITY);
		g.setPriority(Thread.MAX_PRIORITY);
		
		s.start();
		g.start();
	}*/
	
	 public static void main(String[] args) throws InterruptedException
	   {
	      Thread t = new Thread(new Runnable()
	         {
	            public void run()
	            {
	               System.out.println("First task started");
	               System.out.println("Sleeping for 2 seconds");
	               try
	               {
	                  Thread.sleep(2000);
	               } catch (InterruptedException e)
	               {
	                  e.printStackTrace();
	               }
	               System.out.println("First task completed");
	            }
	         });
	      Thread t1 = new Thread(new Runnable()
	         {
	            public void run()
	            {
	               System.out.println("Second task completed");
	            }
	         });
	      t.start(); // Line 15
	      t.join(); // Line 16   主线程调用t线程的jion方法，那么主线程后面的代码 等到t线程执行完后 才会执行
	      t1.start();
	   }
	
	
	
}


class Set extends Thread{
	
	@Override
	public void run() {
		 for (int i = 0; i < 5; i++)
	      {
	         System.out.println("I am Set : Set Item " + i);
	         Thread.yield();
	      }
	}
	
}

class Get extends Thread{
	
	@Override
	public void run() {
		 for (int i = 0; i < 5; i++)
	      {
	         System.out.println("I am get : get Item " + i);
	         Thread.yield();
	      }
	}
	
}