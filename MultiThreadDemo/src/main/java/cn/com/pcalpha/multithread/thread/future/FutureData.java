package cn.com.pcalpha.future;

public class FutureData implements Data {
	private RealData realData;
	private boolean isReady = false;
	public synchronized void setRealData(RealData realData){
		if(isReady){
			return;
		}
		this.realData = realData;
		isReady = true;
		notify();
	}
	
	@Override
	public synchronized Object getRequest() {
		while(!isReady){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		return this.realData.getRequest();
	}
	
	
}
