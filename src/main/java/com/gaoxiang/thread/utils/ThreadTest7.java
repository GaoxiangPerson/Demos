package com.gaoxiang.thread.utils;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.LockSupport;


/**
 *1、控制并发线程数的---Semaphore 信号量 
 *   它是用来控制同时访问 特定资源的 线程数量，通过协调各个线程，保证合理的使用公共资源
 *
 *2、等待多线程完成的 CountDownLatch--维护的是一个计数器
 *	允许一个或多个线程 等待其他线程 完成操作 其实和join方法的功能是有点类似的
 *
 *3、同步屏障 CyclicBarrier
 *
 *4、线程间交换数据Exchanger
 *
 *5、线程阻塞工具 LockSupport
 */


public class ThreadTest7 {

	
	/**
	 * 做一件事情，启用了30个线程，
	 * 但是中间的某一个 步骤只能同时10个线程操作
	 * 这个时候就用 Semaphore来控制了
	 */
	private static final int THREAD_COUNT = 30;
	private static ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_COUNT);
	private static Semaphore semaphore = new Semaphore(10);
	
	public static void main(String[] args) {
		
		for(int i=0;i<THREAD_COUNT;i++){
			threadPool.execute(new Runnable() {
				
				public void run() {
					try {
						semaphore.acquire();
						//doSomething();---这里做只能10个线程操作的那部门工作--类似数据库连接等等，流量控制等等
						//说明只有10个线程获取到许可证
						semaphore.release();
					} catch (InterruptedException e) {
						
					}
				}
			});
		}
		
		threadPool.shutdown();
	}
	
}


class CountDownLatchTest{
	
	/**
	 * CountDownLatch 实际上是维护一计数器
	 * 每调用一次 countDown() 方法 计数器就减1 
	 * CountDownLatch的await() 方法会阻塞
	 * 直至到减为0  就会执行，不再等待
	 */
	static CountDownLatch downLatch = new CountDownLatch(2);
	
	public static void main(String[] args) throws InterruptedException {
		
		new Thread(new Runnable() {
			
			public void run() {
				System.out.println(1);
				downLatch.countDown();
				System.out.println(2);
				downLatch.countDown();
			}
		}).start();
		
		downLatch.await();
		
		System.out.println(3);
	}
	
}


class CyclicBarrierDemo{
	/**
	 * CyclicBarrier 被称为同步屏障
	 * 和CountDownLatch类似都是关于线程的计数器
	 * 
	 * 它的构造方法CyclicBarrier(int parties)
	 * 每个线程调用 它的 await() 方法，告诉我已经到达了屏障，且该线程阻塞，直到到达线程的总量达到 parties个数，阻塞结束
	 * 
	 * 
	 */
	static CyclicBarrier c = new CyclicBarrier(2);   //如果这里是3，那么永远也不会打印值，因为 只有两个线程调用 await() 方法，永远都不会到三 
														//所有线程会一直阻塞下去
	public static void main(String[] args) {
		
		new Thread(new Runnable() {
			
			public void run() {
				try {
					c.await();
				} catch (Exception e) {
					// TODO: handle exception
				}
				System.out.println(1);
			}
		}).start();;
		
		try {
			c.await();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		System.out.println(2);
	}
}


/**
 * Exchanger ---用于线程间协作的工具类
 * 用于线程间的数据交换
 * 它提供一个同步点，两个线程可以交换彼此的数据，两个线程通过 exchange() 交换数据
 * 
 * 如果一个线程先执行 exchange() 方法，它会一直等待第二个线程也执行 exchange()  方法，当两个线程都到达同步点，两个线程就可以交换数据
 * 将本线程 生产出来的数据 传递给 对方 
 *
 *示例：银行流水的对照
 */
class ExchangerDemo{
	
	private static final Exchanger<String> exgr = new Exchanger<String>();
	private static ExecutorService threadPool = Executors.newFixedThreadPool(2);
	
	public static void main(String[] args) {
		
		threadPool.execute(new Runnable() {
			
			public void run() {
				try {
					String A = "银行流水A"; //A线程生产的数据---只能接收字符串数据
					exgr.exchange(A);
				} catch (Exception e) {
					
				}
			}
		});
		
		threadPool.execute(new Runnable() {
			
			public void run() {
				try {
					String B = "银行流水B";
					String A =exgr.exchange("B");
					System.out.println("交换到的数据是："+A);   //这个时候的到的数据就是A 线程的数据
					System.out.println("自己生产的数据是："+B);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
		});
		
		
		threadPool.shutdown();
	}
	
}


/**
 * LockSupport 线程阻塞工具
 * LockSupport是用来创建锁和其他同步类的基本线程阻塞原语。 
 * LockSupport中的park() 和 unpark() 的作用分别是阻塞线程和解除阻塞线程
 * 
 * park和wait的区别。wait让线程阻塞前，必须通过synchronized获取同步锁。
 * 
 *
 */
class LockSupportDemo{
	
	private static Thread mainThread;
	
	public static void main(String[] args) {
		
		ThreadA taA = new ThreadA("a-Thread");
		
		mainThread = Thread.currentThread();
		
		System.out.println(Thread.currentThread().getName()+" start ta");
		taA.start();
		
		System.out.println(Thread.currentThread().getName()+" block");
		//主线程阻塞
		LockSupport.park();
		
		 System.out.println(Thread.currentThread().getName()+" continue");
		
	}
	
	
	 static class ThreadA extends Thread{

	        public ThreadA(String name) {
	            super(name);
	        }

	        public void run() {
	            System.out.println(Thread.currentThread().getName()+" wakup others");
	            // 唤醒“主线程”
	            LockSupport.unpark(mainThread);
	        }
	    }
	
}















