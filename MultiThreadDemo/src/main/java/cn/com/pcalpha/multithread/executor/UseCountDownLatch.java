package cn.com.pcalpha.multithread.executor;

import java.util.concurrent.CountDownLatch;

public class UseCountDownLatch {
	public static void main(String[] args) {
		final CountDownLatch countDown = new CountDownLatch(2);
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				try {
					System.out.println("�����߳�t1"+"�ȴ������̴߳������");
					countDown.await();
					System.out.println("t1�̼߳���ִ��");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		},"t1");
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				try {
					System.out.println("t2�̳߳�ʼ��");
					Thread.sleep(3000);
					System.out.println("t2��ʼ����ϣ�֪ͨt1ִ��");
					countDown.countDown();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		},"t2");
		
		Thread t3 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				try {
					System.out.println("t3�̳߳�ʼ��");
					Thread.sleep(4000);
					System.out.println("t3��ʼ����ϣ�֪ͨt1ִ��");
					countDown.countDown();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
		},"t3");
		
		t1.start();
		t2.start();
		t3.start();
		
	}
}
