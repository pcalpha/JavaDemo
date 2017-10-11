package cn.com.pcalpha.multithread.thread.sync.context;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class UserCopyOnWrite {
	public static void main(String[] args) {
		CopyOnWriteArrayList<String> cwal = new CopyOnWriteArrayList<String>();
		CopyOnWriteArraySet<String> cwas = new CopyOnWriteArraySet<String>();
		
		cwal.add("a");
		
	}
}
