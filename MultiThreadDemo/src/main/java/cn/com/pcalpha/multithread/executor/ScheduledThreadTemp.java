package cn.com.pcalpha.multithread.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadTemp implements Runnable{

	@Override
	public void run() {
		System.out.println("run");
	}
	
	public static void main(String[] args) {
		ScheduledThreadTemp temp = new ScheduledThreadTemp();
		ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
		service.scheduleWithFixedDelay(temp, 1, 2, TimeUnit.SECONDS);
	}

}
