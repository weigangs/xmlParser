package model;

import java.math.BigDecimal;

import base.Const;
import base.Employee;
import utils.SaleRateData;
import utils.SaleRateDataFactory;

public  class Sale extends Employee{

	private static final long serialVersionUID = 3690435877474935463L;
	
	private BigDecimal amount;
	
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Sale(XmlElementAtom atom) {
		super(atom);
		this.setAmount(new BigDecimal(atom.getAttributes().get(Const.XML_EP_AMT)));
	}

	@Override
	public BigDecimal getSalary() {
		BigDecimal temp = new BigDecimal("0");
		temp = new BigDecimal(proKeyVals.get(super.getType()+Const.UNIT_SUFFIX));
		SaleRateData saleRateData = SaleRateDataFactory.getInstance();
		if(new BigDecimal("20000").compareTo(amount) == -1){
			temp = temp.add(new BigDecimal(saleRateData.getRate(amount, proKeyVals)).
					multiply(amount.subtract(new BigDecimal("20000"))));
		}
		return temp;
	}

}
