package com.gaoxiang.thread.createAndInterrupt;

/**
 * 第二种 实现runnable接口
 */
public class MyThread2 implements Runnable{
	
	public void run() {
		// TODO Auto-generated method stub
		for(int i=0;i<100;i++){
			System.out.println(Thread.currentThread().getName()+"**"+i+"**");
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
