package utils;

public class SaleRateDataFactory {
	
	private static SaleRateData data = null;
	
	public static SaleRateData getInstance(){
		if(data == null){
			synchronized(SaleRateDataFactory.class){
				if(data == null){
					data = new SaleRateData();
				}
				return data;
			}
		}else{
			return data;
		}
	}
	
}
