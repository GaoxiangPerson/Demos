package com.gaoxiang.thread.threadCommunication;

public class Producer extends Thread {

		private Task task;
		public Producer(Task t) {
			// TODO Auto-generated constructor stub
			this.task = t;
		}
		
		@Override
		public void run() {
			task.product();
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
