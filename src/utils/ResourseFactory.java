package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;

import model.XmlElementAtom;

public class ResourseFactory {
	
	
	private static HashMap<String, String> proKeyVals =  null;
	private static XmlElementAtom root = null;
	
	public static HashMap<String, String> getProKVInstance(){
		if(proKeyVals == null){
			synchronized(ResourseFactory.class){
				if(proKeyVals == null){
					Resourse resourse = new Resourse();
					proKeyVals = CfgFileParserUtils.parse(resourse.getResourse("config.properties"));
				}
				return proKeyVals;
			}
		}else{
			return proKeyVals;
		}
	}
	
	public static XmlElementAtom getXmlRoot(){
		if(root == null){
			synchronized(ResourseFactory.class){
				if(root == null){
					Resourse resourse = new Resourse();
					root = XmlParserUtils.parse(resourse.getResourse("xml.xml"));
				}
				return root;
			}
		}else{
			return root;
		}
	}
	
}

class Resourse{
	public InputStream getResourse(String fileName){
		ClassLoader classLoader = getClass().getClassLoader();
		URL url = classLoader.getResource(fileName);
		FileInputStream in = null;
		try {
			in = new FileInputStream(url.getFile());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return in;
	}
}
