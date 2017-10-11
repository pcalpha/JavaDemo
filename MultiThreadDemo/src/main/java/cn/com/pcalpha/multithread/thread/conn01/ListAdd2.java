package cn.com.pcalpha.multithread.thread.conn01;

import java.util.ArrayList;
import java.util.List;

public class ListAdd2 {
	
	private volatile static List list = new ArrayList();
	
	public void add(){
		list.add("cddd");
	}
	
	public int size(){
		return list.size();
	}
	
	public static void main(String[] args) {
		final ListAdd2 list2 = new ListAdd2();
		final Object lock = new Object();
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					synchronized (lock) {
						for(int i=0;i<10;i++){
							list2.add();
							System.out.println("��ǰ�̣߳�"+Thread.currentThread().getName()+"�����һ��Ԫ��");
							Thread.sleep(500);
							if(list2.size()==5){
								System.out.println("����֪ͨ");
								lock.notify();
							}
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
		},"t1");
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				synchronized (lock) {
					if (list2.size()!=5) {
						try {
							System.out.println("t2����");
							lock.wait();
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
					System.out.println("��ǰ�̣߳�"+Thread.currentThread().getName()+"�յ�֪ͨ���߳�ֹͣ");
					lock.notify();
					throw new RuntimeException();
				}
			}
		},"t2");
		
		t2.start();
		t1.start();
	}
}
