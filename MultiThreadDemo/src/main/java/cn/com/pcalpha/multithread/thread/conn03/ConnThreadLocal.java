package cn.com.pcalpha.multithread.thread.conn03;

public class ConnThreadLocal {
	private static ThreadLocal<String> th = new ThreadLocal<String>();

	public ThreadLocal<String> getTh() {
		System.out.println(Thread.currentThread().getName()+":"+th.get());
		return th;
	}

	public void setTh(String th) {
		this.th.set(th);
	}
	
	public static void main(String[] args) {
		final ConnThreadLocal ct = new ConnThreadLocal();
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				ct.setTh("a");
				ct.getTh();
			}
		},"t1");
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ct.getTh();
			}
		},"t2");
		
		t1.start();
		t2.start();
	}
	
	
}
