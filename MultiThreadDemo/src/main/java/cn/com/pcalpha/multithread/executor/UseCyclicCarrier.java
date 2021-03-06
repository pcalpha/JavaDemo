package cn.com.pcalpha.multithread.executor;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UseCyclicCarrier {
	static class Runner implements Runnable{
		private CyclicBarrier barrier;
		private String name;
		
		

		public Runner(CyclicBarrier barrier, String name) {
			super();
			this.barrier = barrier;
			this.name = name;
		}



		@Override
		public void run() {
			try {
				Thread.sleep(1000*((new Random()).nextInt(5)));
				System.out.println(name+"׼��OK");
				barrier.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(name+"exec");
			
			
		}
		
	}
	
	public static void main(String[] args) {
		CyclicBarrier barrier = new CyclicBarrier(3);
		ExecutorService executor = Executors.newFixedThreadPool(3);
		executor.submit(new Thread(new Runner(barrier, "a1")));
		executor.submit(new Thread(new Runner(barrier, "a2")));
		executor.submit(new Thread(new Runner(barrier, "a3")));
		
		executor.shutdown();
	}
}
