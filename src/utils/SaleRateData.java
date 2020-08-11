package utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import base.Const;

public class SaleRateData {
	private List<HashMap<String, Object>> rateData = new ArrayList<HashMap<String, Object>>();
	
	public double getRate(BigDecimal amount, HashMap<String, String> proKeyVals){
		if(rateData.size() == 0){
			filterRateData(proKeyVals);
			if(rateData.size() == 0)
				return 0.00;
		}
		BigDecimal floor = null;
		BigDecimal ceiling = null;
		Double rate = 0.00;
		for(int i = 0; i < rateData.size(); i++){
			rate = (Double)rateData.get(i).get(Const.SALE_RATE);
			floor =  (BigDecimal)rateData.get(i).get(Const.AMT_FLOOR);
			ceiling = (BigDecimal)rateData.get(i).get(Const.AMT_CEILING);
			if(amount.compareTo(floor) == 1){
				if(ceiling.compareTo(BigDecimal.ZERO) == 0){
					return rate;
				}else if(amount.compareTo(ceiling) == -1){
					return rate;
				}
			}
		}
		return 0.00;
	}
	
	public void filterRateData(HashMap<String, String> proKeyVals){
		String arrs [] = null;
		HashMap<String, Object> item = null;
		for(String key : proKeyVals.keySet()){
			if(key.contains(Const.SALE_LEVEL_PRE)){
				arrs = proKeyVals.get(key).split(",");
				item = new HashMap<String, Object>();
				item.put(Const.AMT_FLOOR, new BigDecimal(arrs[0]));
				item.put(Const.AMT_CEILING, new BigDecimal(arrs[1]));
				item.put(Const.SALE_RATE, new Double(Double.parseDouble(arrs[2]) * 0.01));
				rateData.add(item);
			}
		}
	}
	
}
