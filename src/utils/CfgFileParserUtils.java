package utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class CfgFileParserUtils {
	
	public static HashMap<String, String> parse(String filePath){
		FileInputStream in = null;
		if(StringUtils.isNotEmpty(filePath)){
			try{
				in = new FileInputStream(filePath);
			}catch(Exception e){
				e.printStackTrace();
			}
			return parse(in);
		}else{
			throw new RuntimeException("filePath is empty!");
		}
	}
	
	public static HashMap<String, String> parse(InputStream in){
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		HashMap<String, String> proVals = new HashMap<String, String>();
		String kv [] = null;
		String temp = "";
		try{
			while((temp = reader.readLine()) != null){
				temp = temp.trim();
				if(!temp.startsWith("#")){
					kv = temp.split("=");
					proVals.put(kv[0], kv[kv.length-1]);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(reader != null){
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return proVals;
	}
}
