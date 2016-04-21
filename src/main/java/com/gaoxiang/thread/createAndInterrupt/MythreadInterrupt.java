package com.gaoxiang.thread.createAndInterrupt;

/**
 * 测试  interrupt()方法能不能直接将线程 中断掉
 * @author gaoxiang
 *
 */
public class MythreadInterrupt implements Runnable {

	public void run() {
		// TODO Auto-generated method stub
		//线程构建一个无限循环，如果不被中断会一直执行下去
		while(true){
		
			System.out.println(Thread.currentThread().getName() + "is Running" +System.currentTimeMillis());
			
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
