package cn.com.pcalpha.multithread.thread.thread03;

public class MyObject {
	
	public synchronized void method1(){
		try {
			System.out.println(Thread.currentThread().getName());
			Thread.sleep(4000);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public synchronized void method2(){
		System.out.println(Thread.currentThread().getName());
	}
	
	public static void main(String[] args) {
		final MyObject obj = new MyObject();
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				obj.method1();	
			}
		},"t1");
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				obj.method2();	
			}
		},"t2");
		
		t1.start();
		t2.start();
	}
}
