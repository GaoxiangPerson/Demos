package com.gaoxiang.thread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 
 * 一个生产者-消费者的 Q
 *
 */
public class ProductQueue<T> {

	private final T[] items;
	
	  private int head, tail, count;  
	  
	  public ProductQueue(int maxSize) {  
	        items = (T[]) new Object[maxSize];  
	   }  
	  
	  public ProductQueue() {  
	        this(10);  
	  }
	  
	  private final Lock lock = new ReentrantLock();
	  private Condition notFull = lock.newCondition();
	  private Condition notEmpty = lock.newCondition();
	  
	  /**
	   * 入Q的操作
	   */
	  public void put(T t) throws InterruptedException {
		  
		  	lock.lock();
		  	
		  	try {
		  		//Q中的数满了，这个时候不生产了
				while(count == getCapacity()){
					notFull.await();
				}
				items[tail] = t;  
	            if (++tail == getCapacity()) {  
	                tail = 0;  
	            }  
	            ++count;  
	            notEmpty.signalAll(); 
		  		
			} finally{
				lock.unlock();
			}
	  }
	  
	  /**
	   * 从Q中取值的操作
	   */
	  public T take() throws InterruptedException {
		  
		  lock.lock();
		  try {
			while(count == 0){
				notEmpty.await();
			}
			 T ret = items[head];  
	         items[head] = null;//GC  
	            //  
	         if (++head == getCapacity()) {  
	               head = 0;  
	          }  
	          --count;  
	         notFull.signalAll();  
	        return ret;
		  } finally {
			  lock.unlock();
		  } 
	  }
	 
	  
	  /**
	   * Q的总长度
	   * @return
	   */
	  public int getCapacity() {  
	        return items.length;  
	    }  
	  
	  	/**
	  	 * 当前Q中的数量
	  	 * @return
	  	 */
	    public int size() {  
	        lock.lock();  
	        try {  
	            return count;  
	        } finally {  
	            lock.unlock();  
	        }  
	    }  
}
