package cn.com.pcalpha.multithread.thread.sync.context;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;


public class UseConcurrentQueen {
	public static void main(String[] args) throws InterruptedException {
		ConcurrentLinkedQueue<String> q = new ConcurrentLinkedQueue<String>();
		q.offer("a");
		q.offer("b");
		q.offer("c");
		q.offer("d");
		q.add("e");
		
		System.out.println(q.poll());
		System.out.println(q.size());
		System.out.println(q.peek());
		System.out.println(q.size());
		
		LinkedBlockingQueue<String> q2 = new LinkedBlockingQueue<String>();
		q2.offer("1");
		q2.offer("2");
		q2.offer("3");
		q2.offer("4");
		
		System.out.println(q2.poll());
		System.out.println(q2.size());
		System.out.println(q2.peek());
		System.out.println(q2.size());
		
		ArrayBlockingQueue<String> abq = new ArrayBlockingQueue<String>(5);
		abq.offer("a", 2, TimeUnit.SECONDS);
		abq.add("b");
		abq.add("c");
		abq.add("d");
		abq.add("e");
		
		LinkedBlockingQueue<String> lbq = new LinkedBlockingQueue<String>(10);
		q.offer("a");
		q.offer("b");
		q.offer("c");
		q.offer("d");
		
		final SynchronousQueue<String> sq = new SynchronousQueue<String>();
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					System.out.println(sq.take());
				} catch (Exception e) {
					
				}
				
			}
		});
		t1.start();
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					sq.add("a");
				} catch (Exception e) {
					
				}
				
			}
		});
		t2.start();
		
		
		
	}

}
