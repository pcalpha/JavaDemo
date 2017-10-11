package cn.com.pcalpha.multithread.thread.masterwork;

import java.util.Random;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Master master = new Master(new Worker(),10);
		
		Random r = new Random();
		for(int i=0;i<10;i++){
			Task t = new Task();
			t.setId(i);
			t.setName("����"+i);
			t.setResult(r.nextInt(1000));
			master.submit(t);
		}
		
		master.execute();
		
		while(true){
			if(master.isComplete()){
				int ret = master.getResult();
				System.out.println(ret);
				break;
			}	
		}
		
		
		

	}

}
