package cn.com.pcalpha.multithread.executor;

import java.util.concurrent.Executors;

public class UseExecutors {
	public static void main(String[] args) {
		Executors.newFixedThreadPool(5);
		Executors.newCachedThreadPool();
		Executors.newSingleThreadExecutor();
		Executors.newScheduledThreadPool(5);
	}
}
