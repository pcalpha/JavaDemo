package cn.com.pcalpha.multithread.thread.conn04;

public class DoubleCheckSingleton {
	private static DoubleCheckSingleton ds;
	
	public static DoubleCheckSingleton getInstance(){
		if(ds==null){
			try {
				Thread.sleep(3000);//ģ�����׼��ʱ��
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			synchronized (DoubleCheckSingleton.class) {
				if(ds==null){
					ds = new DoubleCheckSingleton();
				}
			}
		}
		return ds;
		
	}
	
	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println(DoubleCheckSingleton.getInstance().hashCode());
				
			}
		},"t1");
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println(DoubleCheckSingleton.getInstance().hashCode());
				
			}
		},"t2");
		
		Thread t3 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println(DoubleCheckSingleton.getInstance().hashCode());
				
			}
		},"t3");
		
		t1.start();
		t2.start();
		t3.start();
		
	}
}
