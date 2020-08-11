package model;

import java.math.BigDecimal;

import base.Const;
import base.Employee;

public class Salary extends Employee{

	private static final long serialVersionUID = 8830467915374073122L;
	
	public Salary(XmlElementAtom atom){
		super(atom);
	}
	
	@Override
	public BigDecimal getSalary() {
		return new BigDecimal(proKeyVals.get(super.getType()+Const.UNIT_SUFFIX));
	}

}
