package cn.com.pcalpha.multithread.thread.thread01;

public class MyThread extends Thread {
	private int i =5;
	
	public static void main(String[] args) {
		MyThread myThread = new MyThread();
		Thread t1 = new Thread(myThread,"t1");
		Thread t2 = new Thread(myThread,"t2");
		Thread t3 = new Thread(myThread,"t3");
		Thread t4 = new Thread(myThread,"t4");
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
	}

	@Override
	public synchronized void run() {
		i--;
		System.out.println(this.currentThread().getName()+";"+"i="+i);
	}
}
