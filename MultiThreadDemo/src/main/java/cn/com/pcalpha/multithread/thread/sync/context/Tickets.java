package cn.com.pcalpha.multithread.thread.sync.context;

import java.util.Vector;

public class Tickets {
	
	
	public static void main(String[] args) {
		final Vector<String> tickets = new Vector<String>();
		for(int i=1;i<=1000;i++){
			tickets.add("��Ʊ"+i);
		}
		
		/*for(Iterator itor = tickets.iterator();itor.hasNext();){
			String str = (String) itor.next();
			tickets.remove(20);
		}*/
		
		for(int i=1;i<10;i++){
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					while(true){
						if(tickets.isEmpty()){
							break;
						}
						System.out.println(Thread.currentThread().getName()+"--"+tickets.remove(0));
					}
				}
			},"t"+i).start();
		}
	}
	
}
