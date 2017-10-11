package cn.com.pcalpha.multithread.thread.thread06;

public class StringLock {
	
	public void method(){
		//�ַ���������������
		synchronized ("�ַ�������") {
			try {
				while(true){
					System.out.println("��ǰ�̣߳�"+Thread.currentThread().getName()+"��ʼ");
					Thread.sleep(1000);
					System.out.println("��ǰ�̣߳�"+Thread.currentThread().getName()+"����");
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		final StringLock sl = new StringLock();
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				sl.method();
			}
		},"t1");
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				
			}
		},"t2");
		
		t1.start();
		t2.start();
	}
}
