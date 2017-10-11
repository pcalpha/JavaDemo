package cn.com.pcalpha.multithread.thread.conn04;

public class InnerSingleton {
	
	private static class Singleton{
		private static Singleton single = new Singleton();
	}
	
	public static Singleton getInstance(){
		return Singleton.single;
	}
}
