package cn.com.pcalpha.multithread.thread.thread05;

public class SyncException {
	
	private int i = 0;
	public synchronized void opetation(){
		while(true){
			try {
				i++;
				Thread.sleep(1000);
				System.out.println(Thread.currentThread().getName()+",i = "+i);
				if(i==5){
					Integer.parseInt("a");
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("log info i = "+i);//��¼��־
				continue;
				//throw new RuntimeException();
				
			}
		}
	}
	
	public static void main(String[] args) {
		final SyncException se = new SyncException();
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				se.opetation();
			}
		});
		t1.start();
	}
}
