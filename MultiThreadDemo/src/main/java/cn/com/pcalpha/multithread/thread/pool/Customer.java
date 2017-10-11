package cn.com.pcalpha.multithread.thread.pool;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Customer implements Runnable {
	private BlockingQueue<Data> queue;
	private static Random r = new Random();
	
	
	public Customer(BlockingQueue<Data> queue) {
		super();
		this.queue = queue;
	}


	@Override
	public void run() {
		while(true){
			
			try {
				Data data = this.queue.take();
				Thread.sleep(r.nextInt(1000));
				System.out.println("��ǰ�߳�"+Thread.currentThread().getName()+",���ѳɹ�����������idΪ"+data.getId());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
	}
	

}
