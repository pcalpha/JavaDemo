package cn.com.pcalpha.multithread.thread.sync.context;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserConcurrentHashMap {
	public static void main(String[] args) {
		ConcurrentHashMap<String,Object> chm = new ConcurrentHashMap<String,Object>();
		chm.put("a1", "dd");
		chm.put("a2", "oo");
		chm.put("a3", "ee");
		
		chm.putIfAbsent("a3", "33");
		
		for(Map.Entry<String, Object> e:chm.entrySet()){
			System.out.println("key="+e.getKey()+",value="+e.getValue());
		}
	}
}
