package cn.com.pcalpha.multithread.thread.masterwork;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Worker implements Runnable {
	private ConcurrentLinkedQueue<Task> workQueue;
	private ConcurrentHashMap<String, Object> resultMap;
	
	public void setWorkQueue(ConcurrentLinkedQueue<Task> workQueue){
		this.workQueue = workQueue;
	}
	
	public void setResult(ConcurrentHashMap<String, Object> resultMap){
		this.resultMap = resultMap;
	}

	@Override
	public void run() {
		while(true){
			Task input = this.workQueue.poll();
			//����ִ�����
			if(input==null){
				break;
			}
			//ִ��ҵ����
			Object output = handle(input);
			this.resultMap.put(Integer.toString(input.getId()),output);
		}
		
	}

	private Object handle(Task input) {
		
		Object output = null;
		try {
			//��ʾ����task����ĺ�ʱ
			Thread.sleep(500);
			output = input.getResult();
			System.out.println(Thread.currentThread().getName()+"������"+output);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return output;
	}

}
