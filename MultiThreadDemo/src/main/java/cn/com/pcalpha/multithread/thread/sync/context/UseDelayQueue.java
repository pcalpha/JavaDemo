package cn.com.pcalpha.multithread.thread.sync.context;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class UseDelayQueue {
	public static void main(String[] args) {
		Wangba wb = new Wangba();
		Thread shangwang = new Thread(wb);
		shangwang.start();
		wb.shangji("1", "a", 1);
		wb.shangji("2", "b", 10);
		wb.shangji("3", "c", 5);
		
	}
	
}
class Wangba implements Runnable{
	private DelayQueue<Wangmin> queue = new DelayQueue<Wangmin>();
	public boolean yinye = true;
	
	public void shangji(String id,String name,int money){
		Wangmin man = new Wangmin();
		man.setId(id);
		man.setName(name);
		man.setEndTime(1000*money+System.currentTimeMillis());
		
		this.queue.add(man);
	}
	
	public void xiaji(Wangmin man){
		System.out.println("xiaji:"+man.getName()+",id="+man.getId());
	}

	@Override
	public void run() {
		while(yinye){
			try {
				Wangmin man = queue.take();
				xiaji(man);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
class Wangmin implements Delayed{
	
	private String id;
	private String name;
	private long endTime;
	private TimeUnit timeUnit = TimeUnit.SECONDS;
	
	@Override
	public int compareTo(Delayed o) {
		Wangmin w = (Wangmin) o;
		return this.getDelay(this.timeUnit)-w.getDelay(this.timeUnit)>0?1:0;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		return timeUnit.convert(endTime, unit)-timeUnit.convert(System.currentTimeMillis(), unit);
		//return  endTime - System.currentTimeMillis();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public TimeUnit getTimeUnit() {
		return timeUnit;
	}

	public void setTimeUnit(TimeUnit timeUnit) {
		this.timeUnit = timeUnit;
	}
	
	
	
	
	
}