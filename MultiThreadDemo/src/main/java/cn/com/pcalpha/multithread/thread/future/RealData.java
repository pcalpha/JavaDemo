package cn.com.pcalpha.future;

public class RealData implements Data {
	private String result;
	public RealData(String queryStr){
		System.out.println("����"+queryStr+"���в�ѯ");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("������ϣ����ؽ��");
		result = "��ѯ���";
		
	}
	
	@Override
	public String getRequest(){
		return result;
	}
}
