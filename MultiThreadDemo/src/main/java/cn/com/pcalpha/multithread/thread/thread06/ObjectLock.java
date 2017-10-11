package cn.com.pcalpha.multithread.thread.thread06;

public class ObjectLock {
	
	public void method1(){
		synchronized(this) {
			try {
				System.out.println("method1 exetuteing");
				Thread.sleep(10000);
				System.out.println("method1 finish");
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	public void method2(){
		synchronized(ObjectLock.class) {
			try {
				System.out.println("method2 exetuteing");
				Thread.sleep(5000);
				System.out.println("method2 finish");
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	private Object lock = new Object();
	public void method3(){
		synchronized (lock) {
			try {
				System.out.println("method3 exetuteing");
				Thread.sleep(2000);
				System.out.println("method3 finish");
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	public static void main(String[] args) {
		final ObjectLock ol = new ObjectLock();
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				ol.method1();
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				ol.method2();
			}
		});
		
		Thread t3 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				ol.method3();
			}
		});
		t1.start();
		t2.start();
		t3.start();
		
	}
}
