package cn.com.pcalpha.multithread.thread.thread07;

public class VolatileThread extends Thread {
	
	//private boolean isRunning = true;
	private volatile boolean isRunning = true;

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
	
	public void run(){
		System.out.println("����run��������");
		while(isRunning){
			
		}
		System.out.println("�߳�ֹͣ");
	}
	
	public static void main(String[] args) throws InterruptedException {
		VolatileThread rt = new VolatileThread();
		rt.start();
		Thread.sleep(3000);
		rt.setRunning(false);
		System.out.println("isRunning����Ϊfalse");
		Thread.sleep(1000);
		System.out.println(rt.isRunning);
	}
}
