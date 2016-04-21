package com.gaoxiang.thread.threadPool;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 *java中的线程池 
 *1、可以通过 ThreadPoolExecutor 来创建一个线程池
 *构造方法：public ThreadPoolExecutor(   
 *          int corePoolSize,   
 *          int maximumPoolSize,   
 *          long keepAliveTime,   
 *          TimeUnit unit,   
 *          BlockingQueue workQueue)  
 *参数的含义：corePoolSize 指的是保留的线程池大小。--保留线程池的大小
 *			 maximumPoolSize 指的是线程池的最大大小。
 *			 keepAliveTime 指的是空闲线程结束的超时时间。
 *			 unit 是一个枚举，表示 keepAliveTime 的单位。
 *			 workQueue 表示存放任务的队列。
 */
public class ThreadPoolTest {

	public static void main(String[] args) {
		
		 BlockingQueue queue = new LinkedBlockingQueue();   
		 ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 6, 1, TimeUnit.DAYS, queue);   
		 
		 for(int i=0;i<40;i++){
			 executor.execute(new Runnable() {
				
				public void run() {
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
					}
					 System.out.println(String.format("thread %d finished", this.hashCode()));   
				}
			});
			 
		 }
		 
		 executor.shutdown(); 
		 
	/**
	 * 1、BlockingQueue 只是一个接口，常用的实现类有 LinkedBlockingQueue 和 ArrayBlockingQueue。
	 * 用 LinkedBlockingQueue 的好处在于没有大小限制。这样的话，因为队列不会满
	 * 所以 execute() 不会抛出异常，而线程池中运行的线程数也永远不会超过 corePoolSize 个
	 * keepAliveTime 参数也就没有意义了。
	 * 
	 * 2、shutdown() 方法不会阻塞。调用 shutdown() 方法之后，主线程就马上结束了，
	 * 而线程池会继续运行直到所有任务执行完才会停止。如果不调用 shutdown() 方法，那么线程池会一直保持下去，以便随时添加新的任务。
	 * 
	 * summit()方法用于提交带有返回值的任务，会返回一个fature对象---这里详细留在Executors框架去整理
	 * 
	 */
		 
	}
}

/**
 * Executor 框架---重点---三部分组成
 * 1、任务：  被执行的任务需要实现接口 :Runnable接口 Callable(带有返回值的任务)接口
 * 2、任务的执行： 包含任务执行机制的核心接口 Executor接口，以及继承它的 ExecutorServices接口，以及两个
 * 关键实现类 ThreadPoolExecutor ScheduledThreadPoolExecutor
 * 3、异步计算的结果：包括接口 Future接口 和实现Future接口的FutureTask类
 * 
 * ScheduledThreadPoolExecutor 实现类，可以在给定时间 延迟执行任务，比timer更加灵活，功能更强大
 * Runnable接口 Callable接口的实现类，都能被线程池所执行
 * 
 * public static ExecutorService newFixedThreadPool(int nThreads)
 * 创建固定数目线程的线程池。
 *
 * public static ExecutorService newCachedThreadPool()
 * 创建一个可缓存的线程池，调用execute将重用以前构造的线程（如果线程可用）。如果现有线程没有可用的，
 * 则创建一个新线   程并添加到池中。终止并从缓存中移除那些已有 60 秒钟未被使用的线程
 * 
 * public static ExecutorService newSingleThreadExecutor()
 *创建一个单线程化的Executor。
 * 
 *public static ScheduledExecutorService newScheduledThreadPool(int corePoolSize)
 *创建一个支持定时及周期性的任务执行的线程池，多数情况下可用来替代Timer类。 
 * 
 *   这四种方法都是用的Executors中的ThreadFactory建立的线程
 */


/**
 *   一般来说，CachedTheadPool在程序执行过程中通常会创建与所需数量相同的线程，
 *   然后在它回收旧线程时停止创建新线程，因此它是合理的Executor的首选，
 *   只有当这种方式会引发问题时（比如需要大量长时间面向连接的线程时），才需要考虑用FixedThreadPool
 */


/**
 * 一类是实现了Runnable接口的类，一类是实现了Callable接口的类。两者都可以被ExecutorService执行，
 * 但是Runnable任务没有返回值，而Callable任务有返回值。并且Callable的call()方法只能通过ExecutorService
 * 的submit(Callable<T> task) 方法来执行，并且返回一个 <T>Future<T>，是表示任务等待完成的 Future
 * 
 * Callable中的call()方法类似Runnable的run()方法，区别同样是有返回值，后者没有
 * 
 */


/**
 * 
 * 当将一个Callable的对象传递给ExecutorService的submit方法，则该call方法自动在一个线程上执行，
 * 并且会返回执行结果Future对象。同样，将Runnable的对象传递给ExecutorService的submit方法，
 * 则该run方法自动在一个线程上执行，并且会返回执行结果Future对象，但是在该Future对象上调用get方法，将返回null。
 * 
 */

/**
 *FutureTask  
 * 实现了Runnable接口--同样实现了Future接口
 * 有方法： get() cancel()
 * 	run()
 * 
 */
//@SuppressWarnings("all")  
class FutureTaskDemo{
	
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		Callable privateTask = new PrivateTask();
		FutureTask futureTask = new FutureTask(privateTask);
		
		ExecutorService thredPool = Executors.newCachedThreadPool();
		futureTask = (FutureTask) thredPool.submit(privateTask);
		
		System.out.println(futureTask.get());
		thredPool.shutdown();
	}
	
}

//@SuppressWarnings("all")  
class PrivateTask implements Callable{
	Integer totalMoney;
		
	public Integer call() throws Exception {
		Thread.sleep(5000);
		totalMoney = new Integer(new Random().nextInt(10000));
		System.out.println("当前有"+totalMoney+"在账户中！！！");
		return totalMoney;
	}
	
	
}




