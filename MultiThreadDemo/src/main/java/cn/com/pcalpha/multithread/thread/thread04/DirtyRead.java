package cn.com.pcalpha.multithread.thread.thread04;

public class DirtyRead {
	
	private String username = "cyd";
	private String password = "123456";
	
	public synchronized void setValue(String username, String password){
		this.username = username;
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		this.password = password;
		System.out.println("setValue:username="+username+",password="+password);
		
	}
	
	public void getValue(){
		System.out.println("getValue:username="+username+",password="+password);
	}
	
	public static void main(String[] args) throws InterruptedException {
		final DirtyRead dr = new DirtyRead();
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				dr.setValue("chh", "456789");
				
			}
		});
		t1.start();
		Thread.sleep(1000);
		
		dr.getValue();
	}
	
}
