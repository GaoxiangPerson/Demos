package com.gaoxiang.thread.currentContainer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 1、并发容器  map list等
 * ConcurrentHashMap
 * hashMap 线程不安全 多线程是弃用
 * hashTable  效率太低
 * 而ConcurrentHashMap 采用分锁技术，采用多把锁
 * 
 * 它是由  Segment数组结构和 HashEntry 数组结构 Segment是一种可重入锁ReentrantLock
 * 是一种数组和链表结构， 一个Segment里包含一个HashEntry数组
 * 每个HashEntry是一个链表结构的元素
 * 每个Segment守护者一个HashEntry数组里的元素,当对HashEntry数组的数据进行修改时，必须首先获得它对应的Segment锁。
 * 
 * 
 * 并发队列：
 * ConcurrentLinkedQueue--非阻塞队列  基于链接节点的无界线程安全队列
 * 
 * 
 * 
 * 阻塞队列：
 * 在队列为空时，获取元素的线程会等待队列变为非空。当队列满时，存储元素的线程会等待队列可用
 * 接口---BlockingQueue
 * 子类：
 * ArrayBlockingQueue
 * 
 * DelayQueue
 * 一个使用优先级队列实现的无界阻塞队列
 * LinkedBlockingQueue
 * 
 * PriorityBlockingQueue
 * --支持优先级排序
 * LinkedBlockingDeque
 * 
 * SynchronousQueue：
 * 一个不存储元素的阻塞队列。
 * 
 * 2、原子类 A
 * java.util.concurrent.atomic 在这个包下面是包含所以的原子类
 * 其基本的特性就是在多线程环境下，当有多个线程同时执行这些类的实例包含的方法时，具有排他性，
 * 即当某个线程进入方法，执行其中的指令时，不会被其他线程打断，而别的线程就像自旋锁一样，
 * 一直等到该方法执行完成，才由JVM从等待队列中选择一个另一个线程进入，这只是一种逻辑上的理解。
 * 分为下面四类：
 * AtomicBoolean，AtomicInteger，AtomicLong，AtomicReference
 * 类似，分别操作基本类型和引用类型
 * 
 * AtomicIntegerArray，AtomicLongArray
 * 可以用原子方式更新其元素的 int 数组
 * 
 * AtomicLongFieldUpdater，AtomicIntegerFieldUpdater，AtomicReferenceFieldUpdater
 * 针对引用的字段进行操作，而且仅能操作volatile修饰的字段
 * 
 * AtomicMarkableReference，AtomicStampedReference，AtomicReferenceArray
 * 
 *	AtomicMarkableReference类描述的一个<Object,Boolean>的对，可以原子的修改Object或者Boolean的值，
 *	这种数据结构在一些缓存或者状态描述中比较有用。这种结构在单个或者同时修改Object/Boolean的时候能够有效的提高吞吐量。
 *
 *	AtomicStampedReference类维护带有整数“标志”的对象引用，可以用原子方式对其进行更新。对比AtomicMarkableReference类的
 *	<Object,Boolean>，AtomicStampedReference维护的是一种类似<Object,int>的数据结构，其实就是对对象（引用）的一个并发计数。
 *	但是与AtomicInteger不同的是，此数据结构可以携带一个对象引用（Object），并且能够对此对象和计数同时进行原子操作。
 *
 *	
 */
public class CurrentContainerDemo {

	Map m = new ConcurrentHashMap<String, String>();
}
