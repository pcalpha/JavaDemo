package cn.com.pcalpha.multithread.thread.pool;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Provider implements Runnable {
	private BlockingQueue<Data> queue;
	private volatile boolean isRunning = true;
	private static AtomicInteger count = new AtomicInteger();
	private static Random r = new Random();
	

	public Provider(BlockingQueue<Data> queue) {
		this.queue = queue;
	}


	@Override
	public void run() {
		while(isRunning){
			try {
				Thread.sleep(r.nextInt(1000));
				int id = count.incrementAndGet();
				Data data = new Data(Integer.toString(id),"����"+id);
				System.out.println("��ǰ�߳�"+Thread.currentThread().getName()+",��ȡ�����ݣ�idΪ��"+id+",����װ�ص������������С�����");
				if(!this.queue.offer(data,2,TimeUnit.SECONDS)){
					System.out.println("�ύ������ʧ�ܡ�����");
				}
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			
		}
		
		
	}
	
	public void stop(){
		isRunning = false;
	}

}
