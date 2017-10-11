package cn.com.pcalpha.multithread.thread.thread05;

//�̳�������
public class SyncDubbo2 {
	
	static class Main {
		public int i = 10;
		public synchronized void operateMain(){
			
			try {
				i--;
				System.out.println("Main print i="+i);
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		
	}
	
	static class Sub extends Main {
		public synchronized void operateSub(){
			try {
				while(i>0){
					i--;
					System.out.println("Sub print i="+i);
					Thread.sleep(1000);
					this.operateMain();
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	}
	
	
	
	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				//Sub sub = new Sub();
				//sub.operateSub();
			}
		});
		
		t1.start();
		
	}
}
