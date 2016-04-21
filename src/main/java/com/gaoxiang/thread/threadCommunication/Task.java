package com.gaoxiang.thread.threadCommunication;

public class Task {
		
		public boolean flag = true;
		public Object object = new Object();
		
		public   void product(){
			synchronized (object) {
				while(!flag){
					try {
						object.wait();
					} catch (InterruptedException e) {
					}
				}
				System.out.println(Thread.currentThread().getName()+"生产了一个产品"+"**********");
				flag = false;
				object.notifyAll();
			}
		}
	
		public  void consume(){
			synchronized (object) {
				while(flag){
					try {
						object.wait();
					} catch (InterruptedException e) {
						// TODO: handle exception
					}
				}
				System.out.println(Thread.currentThread().getName()+"消费了一个产品"+"#########");
				flag = true;
				object.notifyAll();
			}
		}
}
