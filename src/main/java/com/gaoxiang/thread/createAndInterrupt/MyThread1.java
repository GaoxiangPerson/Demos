package com.gaoxiang.thread.createAndInterrupt;

/**
 * 第一种  继承Thread
 */
public class  MyThread1 extends Thread{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		for(int i=0;i<100;i++){
			System.out.println(Thread.currentThread().getName()+"--"+i+"--");
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}