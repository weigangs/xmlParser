package utils;

public class DepartmentDataFactory {
	
	private static DepartmentData data = null;
	
	public static DepartmentData getInstance(){
		if(data == null){
			synchronized(DepartmentDataFactory.class){
				if(data == null){
					data = new DepartmentData();
				}
				return data;
			}
		}else{
			return data;
		}
	}
	
}
