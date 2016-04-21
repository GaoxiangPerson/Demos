package com.gaoxiang.thread.threadCommunication;

public class Consumer extends Thread {

	private Task task;
	public Consumer(Task t) {
		// TODO Auto-generated constructor stub
		this.task = t;
	}
	
	@Override
	public void run() {
		task.consume();
	}
	
	/**
	 * @return the task
	 */
	public Task getTask() {
		return task;
	}
	/**
	 * @param task the task to set
	 */
	public void setTask(Task task) {
		this.task = task;
	}
}
