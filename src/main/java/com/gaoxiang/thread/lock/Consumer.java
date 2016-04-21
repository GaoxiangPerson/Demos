package com.gaoxiang.thread.lock;

public class Consumer extends Thread {
	private ProductQueue<Object> q;
	
	public Consumer(ProductQueue<Object> q) {
		this.q = q;
	}
	
	@Override
	public void run() {
		try {
			q.take();
			System.out.println(Thread.currentThread().getName()+"-----"+q.size());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
