package cn.com.pcalpha.multithread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class UseConditionLock {
	private ReentrantLock lock = new ReentrantLock();
	
	private Condition condition = lock.newCondition();
	
	public void method1(){
		
		try {
			lock.lock();
			System.out.println("��ǰ�߳�"+Thread.currentThread().getName()+"method1����");
			Thread.sleep(3000);
			System.out.println("��ǰ�߳�"+Thread.currentThread().getName()+"method1�ͷ���");
			condition.await();
			System.out.println("��ǰ�߳�"+Thread.currentThread().getName()+"method1����ִ��");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			lock.unlock();
		}
		
	}
	
	public void method2(){
		try {
			lock.lock();
			System.out.println("��ǰ�߳�"+Thread.currentThread().getName()+"����method2");
			Thread.sleep(3000);
			System.out.println("��ǰ�߳�"+Thread.currentThread().getName()+"method2��������");
			condition.signal();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			lock.unlock();
		}
		
	}
	
	public static void main(String[] args) {
		final UseConditionLock url = new UseConditionLock();
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				url.method1();
			}
		},"t1");
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				url.method2();
			}
		},"t2");
		
		t1.start();
		t2.start();
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
