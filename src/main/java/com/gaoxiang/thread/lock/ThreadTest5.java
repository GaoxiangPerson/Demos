package com.gaoxiang.thread.lock;


/**
 * Lock接口 用来代替 synchronized 
 * 它的实现 ReentrantLock -常用的可重入锁
 * ReentrantReadWriteLock.ReadLock ---读写锁中的读锁
 * ReentrantReadWriteLock.WriteLock ---读写锁中的写锁
 * 
 * 它的几个常用的api
 * lock() 获取锁
 * lockInterruptibly() 如果当前线程没有中断就 获取锁
 * tryLock() 尝试非阻塞获取锁，调用该方法后立刻返回，能够获取到返回true，否则返回false
 * unlock() 释放锁
 * newCondition() 返回绑定当前锁的 Condition 实例，用于进行等待、唤醒
 * 
 * 可重入锁-ReentrantLock 就是支持重新进入的锁，表示该锁能够支持一个线程对资源的重复加锁
 * ProductQueue 代表了一个生产者 消费者的方法
 * 这样控制了一个 
 */
public class ThreadTest5 {

	public static void main(String[] args) {
		
		ProductQueue<Object> q = new ProductQueue<Object>();
		for(int i=0;i<50;i++){
			Product p = new Product(q);
			p.setName("Product_Thread"+i);
			p.start();
		}
		for(int i=0;i<50;i++){
			Consumer c = new Consumer(q);
			c.setName("Consumert_Thread"+i);
			c.start();
		}
		
	}
	
}
