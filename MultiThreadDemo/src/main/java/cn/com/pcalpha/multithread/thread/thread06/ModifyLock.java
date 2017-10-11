package cn.com.pcalpha.multithread.thread.thread06;

public class ModifyLock {
	
	private String name;
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public synchronized void changeAttribuge(String name, int age){
		try {
			System.out.println("��ǰ�߳�"+Thread.currentThread().getName()+"��ʼ");
			this.setName(name);
			this.setAge(age);
			
			System.out.println("��ǰ�߳�"+Thread.currentThread().getName()+"�޸Ķ�������Ϊ"
			+this.getName()+","+this.getAge());
			Thread.sleep(2000);
			System.out.println("��ǰ�߳�"+Thread.currentThread().getName()+"����");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		final ModifyLock ml = new ModifyLock();
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				ml.changeAttribuge("a",11);
			}
		},"t1");
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				ml.changeAttribuge("b",22);
			}
		},"t2");
		
		t1.start();
		
		Thread.sleep(1000);
		
		t2.start();
	}
	
}
