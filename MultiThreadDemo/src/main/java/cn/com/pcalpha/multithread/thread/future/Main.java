package cn.com.pcalpha.future;

public class Main {

	public static void main(String[] args) {
		FutureClient fc = new FutureClient();
		Data data = fc.request("Ո��һ�β�ԃ");
		System.out.println("Ո��l���ɹ�");
		System.out.println("Ո������");
		
		System.out.println(data.getRequest());

	}

}
