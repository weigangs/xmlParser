package base;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;

import model.XmlElementAtom;
import utils.ResourseFactory;

public abstract class Employee implements Serializable{
	
	public static HashMap<String, String> proKeyVals = ResourseFactory.getProKVInstance();
	
	private static final long serialVersionUID = -6634561742960293112L;

	private String name;
	
	private String type;
	
	private String birthday;
	
	public Employee(XmlElementAtom atom){
		this.setName(atom.getAttributes().get(Const.XML_EP_NAME));
		this.setType(atom.getAttributes().get(Const.XML_EP_TYPE));
		this.setBirthday(atom.getAttributes().get(Const.XML_EP_BIRTH));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	public abstract BigDecimal getSalary();

	@Override
	public String toString() {
		return "Employee [name=" + name + ", type=" + type + ", birthday=" + birthday + "]";
	}
	
}
