package com.gaoxiang.thread.lock;

public class Product extends Thread {
	
		private ProductQueue<Object> q;
		
		public Product(ProductQueue<Object> q) {
			this.q = q;
		}
		
		@Override
		public void run() {
			try {
				q.put(new Object());
				System.out.println(Thread.currentThread().getName()+"*****"+q.size());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
}
