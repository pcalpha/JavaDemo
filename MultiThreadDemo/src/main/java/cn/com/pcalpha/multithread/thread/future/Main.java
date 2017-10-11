package cn.com.pcalpha.future;

public class Main {

	public static void main(String[] args) {
		FutureClient fc = new FutureClient();
		Data data = fc.request("請求一次查詢");
		System.out.println("請求發生成功");
		System.out.println("請求其他");
		
		System.out.println(data.getRequest());

	}

}
