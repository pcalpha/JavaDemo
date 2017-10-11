package cn.com.pcalpha.multithread.thread.conn01;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ListAdd22 {
	
	private volatile static List list = new ArrayList();
	
	public void add(){
		list.add("cddd");
	}
	
	public int size(){
		return list.size();
	}
	
	public static void main(String[] args) {
		final ListAdd22 list2 = new ListAdd22();
		final CountDownLatch countDownLatch = new CountDownLatch(1);
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					for(int i=0;i<10;i++){
						list2.add();
						System.out.println("��ǰ�̣߳�"+Thread.currentThread().getName()+"�����һ��Ԫ��");
						Thread.sleep(500);
						if(list2.size()==5){
							System.out.println("����֪ͨ");
							countDownLatch.countDown();
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
				
				if (list2.size()!=5) {
					try {
						System.out.println("t2����");
						countDownLatch.await();
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				System.out.println("��ǰ�̣߳�"+Thread.currentThread().getName()+"�յ�֪ͨ���߳�ֹͣ");
				throw new RuntimeException();
				
			}
		},"t2");
		
		t2.start();
		t1.start();
	}
}
