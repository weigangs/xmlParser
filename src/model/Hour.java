package model;

import java.math.BigDecimal;

import base.Const;
import base.Employee;

public class Hour extends Employee{
	
	private static final long serialVersionUID = -692543001437687526L;
	
	private double workingHours;
	
	public Hour(XmlElementAtom atom){
		super(atom);
		this.setWorkingHours(Double.valueOf(atom.getAttributes().get(Const.XML_EP_WKH)));
	}
	
	public double getWorkingHours() {
		return workingHours;
	}

	public void setWorkingHours(double workingHours) {
		this.workingHours = workingHours;
	}



	@Override
	public BigDecimal getSalary() {
		double plusHours = 0.00;
		BigDecimal temp = new BigDecimal("0");
		BigDecimal baseUnit = new BigDecimal(proKeyVals.get(super.getType()+Const.UNIT_SUFFIX));
		if(workingHours > Double.parseDouble(proKeyVals.get(Const.HOUR_PAY_MORE_BOUND))){
			plusHours = workingHours - Double.parseDouble(proKeyVals.get(Const.HOUR_PAY_MORE_BOUND));
			temp = new BigDecimal(proKeyVals.get(Const.HOUR_RATE)).
					multiply(baseUnit);
			temp = temp.multiply((new BigDecimal(plusHours)));
			temp = temp.add(new BigDecimal(proKeyVals.get(Const.HOUR_PAY_MORE_BOUND)).multiply(baseUnit));
		}else{
			temp = new BigDecimal(workingHours).multiply(baseUnit);
		}
		return temp;
	}

}
