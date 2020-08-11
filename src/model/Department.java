package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import base.Employee;

public class Department  implements Serializable{

	private static final long serialVersionUID = 4161377284095271549L;

	private String name;
	
	private HashMap<String, List<Employee>> mothTable = new HashMap<String, List<Employee>>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HashMap<String, List<Employee>> getMothTable() {
		return mothTable;
	}

	public void setMothTable(HashMap<String, List<Employee>> mothTable) {
		this.mothTable = mothTable;
	}

	@Override
	public String toString() {
		return "Department [name=" + name + ", mothTable=" + mothTable + "]";
	}
	
	
}
