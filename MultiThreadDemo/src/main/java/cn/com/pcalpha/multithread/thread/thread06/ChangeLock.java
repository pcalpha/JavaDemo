package cn.com.pcalpha.multithread.thread.thread06;

public class ChangeLock {
	private String lock = "lock";
	
	public void method(){
		synchronized (lock) {
			try {
				System.out.println("��ǰ�̣߳�"+Thread.currentThread().getName()+"��ʼ");
				lock = "change";//�����޸���
				Thread.sleep(2000);
				System.out.println("��ǰ�̣߳�"+Thread.currentThread().getName()+"����");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public static void main(String[] args) {
		final ChangeLock cl = new ChangeLock();
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				cl.method();
			}
		},"t1");
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				cl.method();

			}
		},"t2");
		t1.start();
		t2.start();
	}
	
}
