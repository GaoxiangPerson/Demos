package com.gaoxiang.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * 主要学学的是IO NIO
 * 
 * 
 * 
 *
 */
public class IODemo {

	
	public static void main(String[] args) {
		//在配置文件的文件夹里面有一个a.txt 运用流 读取里面的内容
		//运用buffere 缓存区 可以提高读写速度
		try {
			InputStream input = new FileInputStream("D://a.txt");
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			System.out.println("name="+reader.readLine());
			System.out.println("Age="+reader.readLine());
			System.out.println("Email="+reader.readLine());
			System.out.println("Phone="+reader.readLine());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			// TODO: handle exception
		}
		
	}
}


/**
 * NIO 由以下三个核心组成
 * 1、Channels
 * 2、Buffers
 * 3、Selectors
 * 
 * 数据可以从Channel->Buffer  也可以从 Buffer->Channel
 * Channel有以下几种实现：
 * FileChannel、DatagramChannel、SocketChannel、ServerSocketChannel
 * 
 * 在NIO中buffer 有以下几种
 * ByteBuffer、CharBuffer、DoubleBuffer、FloatBuffer、IntBuffer、LongBuffer、ShortBuffer
 * 还有一个 MappedByteBuffer，用于表示内存映射文件
 * 
 * Selector允许单线程处理多个Channel
 * 要使用Selector，得想Selector中注册Channel  然后调用它的select() 方法，一直阻塞到某个注册的通道有事件就绪
 * 
 * 
 * Channel--NIO的通道
 * 
 * 
 *
 */
class NIODemo{
	
	
	
	
	
}











