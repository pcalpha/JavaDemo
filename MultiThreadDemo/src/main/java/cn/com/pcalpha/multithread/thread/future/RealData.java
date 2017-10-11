package cn.com.pcalpha.future;

public class RealData implements Data {
	private String result;
	public RealData(String queryStr){
		System.out.println("根据"+queryStr+"进行查询");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("操作完毕，返回结果");
		result = "查询结果";
		
	}
	
	@Override
	public String getRequest(){
		return result;
	}
}
