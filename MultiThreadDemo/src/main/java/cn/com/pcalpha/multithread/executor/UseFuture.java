package cn.com.pcalpha.multithread.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class UseFuture implements Callable<String>{
	private String param;
	public UseFuture(String param){
		this.param = param;
	}
	
	@Override
	public String call() throws Exception {
		Thread.sleep(3000);//ģ��ִ�к�ʱ
		String result = this.param+"�������";
		return result;
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		String queryStr = "query";
		//����FutureTask,���Ҵ�����Ҫ��������ҵ�������,����һ����ʵ����Callable�ӿڵ���
		FutureTask<String> future = new FutureTask<String>(new UseFuture(queryStr));
		//����һ���̶��̵߳��̳߳�
		ExecutorService executor = Executors.newFixedThreadPool(1);
		//submit ����ʵ�ִ���һ��ʵ��Callable�ӿڵĲ������з���ֵ
		//execute Runnable�ӿڣ��޷���ֵ
		Future f = executor.submit(future);
		System.out.println("�������");
		/*try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		//���û�ȡ���ݷ��������call()����û��������ȴ�
		System.out.println("����="+future.get());
		executor.shutdown();
	}
	
	
}
