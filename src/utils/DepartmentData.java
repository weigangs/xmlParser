package utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import base.Const;
import base.Employee;
import model.Department;
import model.XmlElementAtom;

public class DepartmentData {
	
	public DepartmentData(){
		
	}
	
	private Department  department = null;
	
	private String month = "";
	
	public void setDepartment(XmlElementAtom root){
		department = new Department();
		if(root != null){
			department.setName(root.getLabelName());
			List<XmlElementAtom> eles = root.getChilds();
			if(eles.size() != 0){
				List<XmlElementAtom> emps = null;
				Employee empEntity = null;
				List<Employee> empList = null;
				for(XmlElementAtom month : eles){
					emps = month.getChilds();
					empList = new ArrayList<Employee>();
					for(XmlElementAtom employee : emps){
						try{
							empEntity = (Employee)Class.forName("model."+upFirstChar(employee.getAttributes().get(Const.XML_EP_TYPE))).getConstructor(XmlElementAtom.class).newInstance(employee);
							empList.add(empEntity);
						}catch(Exception e){
							e.printStackTrace();
						}
					}
					department.getMothTable().put(month.getAttributes().get(Const.XML_MONTH_VAL), empList);
				}
			}
		}
	}
	
	public BigDecimal getMonthAmt(){
		BigDecimal total = new BigDecimal("0");
		List<Employee> empee = department.getMothTable().get(month);
		if(department == null){
			throw new RuntimeException("department is null");
		}else if(StringUtils.isEmpty(month)){
			throw new RuntimeException("month is empty");
		}
		//start
		HashMap<String, String> proKeyVals = ResourseFactory.getProKVInstance();
		if(empee != null && empee.size() != 0){
			for(Employee emp : empee){
				if(isBirthDay(emp.getBirthday())){
					total = total.add(new BigDecimal(proKeyVals.get(Const.BIRTHDAY_AWARD)));
				}
				total = total.add(emp.getSalary());
			}
		}
		return total;
	}
	
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getMonth() {
		return month;
	}
	
	public void setMonth(String month) {
		this.month = month;
	}
	
	public boolean isBirthDay(String birthday){
		String arrs [] = birthday.split("-");
		if(month.equals(arrs[1])){
			return true;
		}
		return false;
	}
	
	public String upFirstChar(String str){
		if(StringUtils.isNotEmpty(str)){
			str = str.trim();
			char ch = str.charAt(0);
			ch = Character.toUpperCase(ch);
			return ch + str.substring(1, str.length());
		}
		return "";
	}
	
	
}
