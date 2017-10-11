package cn.com.pcalpha.multithread.thread.conn02;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MyQueen {
	//װԪ�صļ���
	private final LinkedList<Object> list = new LinkedList<Object>();
	//������
	private AtomicInteger count = new AtomicInteger(0);
	//ָ������
	private int minSize = 0;
	//ָ������
	private int maxSize;
	
	//���췽��
	public MyQueen(int maxSize) {
		super();
		this.maxSize = maxSize;
	}
	
	public int size() {
		return count.get();
	}
	
	//������
	private Object lock = new Object();
	
	public void put(Object obj){
		synchronized (lock) {
			while(count.get()==maxSize){
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			list.add(obj);
			count.incrementAndGet();
			lock.notify();
			System.out.println("�¼����Ԫ��Ϊ��"+obj);
		}
	}
	
	public Object take(){
		Object obj = null;
		synchronized (lock) {
			while(count.get()==minSize){
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			obj = list.removeFirst();
			count.decrementAndGet();
			lock.notify();
			System.out.println("ȡ����Ԫ��Ϊ��"+obj);
		}
		return obj;
	}
	
	public static void main(String[] args) {
		final MyQueen queen = new MyQueen(5);
		queen.put("a");
		queen.put("b");
		queen.put("c");
		queen.put("d");
		queen.put("e");
		
		System.out.println("�������ȣ�"+queen.size());
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				queen.put("f");
				queen.put("g");
				
			}
		},"t1");
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				Object obj = queen.take();
				System.out.println("�Ƴ���Ԫ��Ϊ"+obj);
				Object obj2 = queen.take();
				System.out.println("�Ƴ���Ԫ��Ϊ"+obj2);
			}
		},"t2");
		
		t1.start();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t2.start();
		
	}
	
	
	
	
	
}
