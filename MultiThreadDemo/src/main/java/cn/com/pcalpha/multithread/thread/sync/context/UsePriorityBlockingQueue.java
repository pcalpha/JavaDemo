package cn.com.pcalpha.multithread.thread.sync.context;

import java.util.Iterator;
import java.util.concurrent.PriorityBlockingQueue;

//
public class UsePriorityBlockingQueue {
	
	public static void main(String[] args) {
		PriorityBlockingQueue<Task> pbq = new PriorityBlockingQueue<Task>();
		Task t1 = new Task();
		t1.setId(3);
		t1.setName("t1");
		
		Task t2 = new Task();
		t2.setId(6);
		t2.setName("t2");
		
		Task t3 = new Task();
		t3.setId(1);
		t3.setName("t3");
		
		pbq.add(t1);
		pbq.add(t2);
		pbq.add(t3);
		
		for(Iterator<Task> it=pbq.iterator();it.hasNext();){
			Task t = it.next();
			System.out.println(t.getId());
		}
	}
}

class Task implements Comparable<Task>{
	private int id;
	private String name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public int compareTo(Task o) {
		return this.id>o.getId()?1:(this.id<o.getId()?-1:0);
	}
	
	
}
