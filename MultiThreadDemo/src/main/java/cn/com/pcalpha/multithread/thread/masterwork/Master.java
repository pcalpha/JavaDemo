package cn.com.pcalpha.multithread.thread.masterwork;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Master {
	//1 ��һ����װ����ļ���
	ConcurrentLinkedQueue<Task> workQueue = new ConcurrentLinkedQueue<Task>();
	
	//2 ��װ���е�worker����
	Set<Thread> workers = new HashSet<Thread>();
	
	//3 ��װÿһ��worker����ִ������Ľ����
	
	private ConcurrentHashMap<String, Object> resultMap = new ConcurrentHashMap<String,Object>();
	
	//4 ���췽��
	public Master(Worker worker,int workCount){
		//ÿһ��worker������Ҫ��worker�����ã������������ȡ��resultMap����������ύ
		worker.setWorkQueue(workQueue);
		worker.setResult(resultMap);
		for(int i=0;i<workCount;i++){
			//key��ʾÿһ��worker������,value��ʾ�߳�ִ�ж���
			workers.add(new Thread(worker,"t"+i));
		}
	}
	
	//5 �ύ����
	public void submit(Task task){
		this.workQueue.add(task);
		
	}
	
	//6 ִ�з���
	public void execute(){
		for(Iterator<Thread> itor = workers.iterator();itor.hasNext();){
			Thread worker = itor.next();
			System.out.println(Thread.currentThread().getName()+"��ʼִ��");
			worker.start();
		}
	}

	//7 �ж��߳��Ƿ�ִ�����
	public boolean isComplete() {
		for(Iterator<Thread> itor = workers.iterator();itor.hasNext();){
			Thread worker = itor.next();
			if(worker.getState()!=Thread.State.TERMINATED){
				return false;
			}
		}
		return true;
	}
	//���ؽ��������
	public int getResult(){
		int ret = 0;
		for(Map.Entry<String, Object> result:resultMap.entrySet()){
			ret +=(Integer)result.getValue();
		}
		
		return ret;

	}

	
	
	
	
	
}
