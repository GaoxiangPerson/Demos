package com.gaoxiang.thread.createAndInterrupt;

public class ThreadTest1 {

	/**
	 * 新建线程
	 * 终止线程
	 */
	/*public static void main(String[] args) {
		
		*//**
		 * 自定义的线程一定要 设置名称
		 *//*
		MyThread1 myThread1 = new MyThread1();
		MyThread2 myThread2 = new MyThread2();
		myThread1.setName("gx-Thread-1");
		myThread1.start();
		Thread t = new Thread(myThread2);
		t.setName("gx-Thread-2");
		t.start();
	}*/
	
	/**
	 * 中断线程
	 * 忘掉 stop()
	 * 1、thread.interrupt() 并不能简单的将线程给中断掉
	 * 
	 * 
	 */
/*	public static void main(String[] args) {
		
		Thread t = new Thread(new MythreadInterrupt());
		t.setName("gx-interrupt-1");
		
        try {
        	   System.out.println( "Starting thread..." );
               t.start();
               Thread.sleep( 3000 );
               System.out.println( "Interrupting thread..." );
	           t.interrupt();
	           Thread.sleep( 3000 );
	           System.out.println("Stopping application..." );
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}*/
	
	/**
	 * 中断线程--最受推荐的方式
	 * 使用共享变量 （shared variable）发出信号 告诉线程必须停止正在运行的任务 
	 * 线程必须周期性的核查这一变量（尤其在冗余操作期间），然后有秩序地中止任务
	 * 
	 * 这种形式的停止  只是停止 run 里面while里面的代码，并不会停止run 里面的代码
	 * 
	 * 线程任务 ThreadInterruptShareVariable  类
	 */
	public static void main(String[] args) {
		ThreadInterruptShareVariable task = new ThreadInterruptShareVariable();
		try {
			Thread t = new Thread(task);
			 
			System.out.println("strating thread");
			t.start();
			Thread.sleep(2000);
			
			System.out.println( "Asking thread to stop..." );
			task.stop = true;
			System.out.println( "Stopping application..." );
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	/**
	 * 如果需要停止 run里面的全部代码
	 * 可以用 thread.interrupt() 去中断线程
	 * 	
	 * 然后用Thread.isInterrupted() 来读取中断状态
	 * 具体的api如下所示
	 * public static boolean interrupted()测试当前线程是否已经中断。线程的中断状态 由该方法清除。
	 *换句话说，如果连续两次调用该方法，则第二次调用将返回 false（在第一次调用已清除了其中断状态之后，
	 *且第二次调用检验完中断状态前，当前线程再次中断的情况除外）。
	 *public boolean isInterrupted()
	 *测试线程是否已经中断。线程的中断状态不受该方法的影响。
	 *public void interrupt()中断
	 *  
	 *  interrupt方法是唯一能将中断状态设置为true的方法
	 * 静态方法interrupted会将当前线程的中断状态清除，但这个方法的命名极不直观，很容易造成误解，需要特别注意。
	 *  
	 *  所以你可以 在run 中的任务模块中  通过 isInterrupted()检查任务中断状态，然后抛出 InterruptedException，在上层调用
	 *  线程方法这里来处理这个异常
	 *  
	 */
	
	
	
	
}
