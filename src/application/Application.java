package application;

import java.math.BigDecimal;

import utils.DepartmentData;
import utils.DepartmentDataFactory;
import utils.ResourseFactory;

public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//
		//caculate
		DepartmentData departmentData = DepartmentDataFactory.getInstance();
		departmentData.setDepartment(ResourseFactory.getXmlRoot());
		String month = "9";
		departmentData.setMonth(month);
		BigDecimal total = departmentData.getMonthAmt();
		System.out.println(month+"-month total is:"+total.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		month = "10";
		departmentData.setMonth(month);
		total = departmentData.getMonthAmt();
		System.out.println(month+"-month total is:"+total.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	}
	
}
