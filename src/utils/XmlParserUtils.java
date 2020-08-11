package utils;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Stack;

import model.XmlElementAtom;

/**
 * 
 * @author Nicholas Paul
 * date 2020-08-08
 *
 */
public class XmlParserUtils {

	public static XmlElementAtom parse(String filePath){
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
	
	public static XmlElementAtom parse(InputStream in){
		XmlElementAtom root = new XmlElementAtom();
		ByteArrayOutputStream byteStore = new ByteArrayOutputStream();
		if(in != null){
			try{
				int length = 0;
				byte bytes [] = new byte[1024];
				while((length = in.read(bytes)) != -1){
					byteStore.write(bytes, 0, length);
				}
			}catch(Exception e){
				throw new RuntimeException("reading error!");
			}finally{
				if(in != null){
					try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}else{
			throw new RuntimeException("resourse reading failed!");
		}
		try {
			String xml = new String(byteStore.toByteArray(), "UTF-8");
			if(StringUtils.isNotEmpty(xml)){
				StringBuilder str = new StringBuilder(xml.trim());
				extract(str, root);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return root;
	}
	
	/**
	 * push every element header into stack
	 * pop if there is match(<label></label>) of two element
	 * @param str
	 * @param root
	 */
	public static void extract(StringBuilder str, XmlElementAtom root){
		Stack<XmlElementAtom> stack = new Stack<XmlElementAtom>();
		int off = 0;
		int index = 0;
		String temp = "";
		XmlElementAtom child = null;
		stack.push(root);
		index = str.indexOf(">", off);
		if(!isHeader(temp = str.substring(off, index+1))){
			throw new RuntimeException("parse xml error!");
		}
		parseHeader(temp, root);
		off = index+1;
		while(str.length() > off){
			index = str.indexOf(">", off);
			if(index == -1){
				throw new RuntimeException("parse xml error!");
			}
			child = new XmlElementAtom();
			temp = str.substring(off, index+1);
			temp = temp.trim();
			if(isHeader(temp)){
				stack.peek().getChilds().add(child);
				parseHeader(temp, child);
				if(!isSingleHeader(temp)){
					stack.push(child);
				}
			}else{
				if(isMatch(stack, temp)){
					stack.pop();
				}
			}
			off = index+1;
		}
		if(!stack.isEmpty()){
			throw new RuntimeException("parse xml error!");
		}
	}
	
	/**
	 * if start with "<char"
	 * @param str
	 * @return
	 */
	public static boolean isHeader(String str){
		if(str.length() < 3){
			throw new RuntimeException("parse xml error!");
		}else if(str.charAt(1) != '/'){
			return true;
		}
		return false;
	}
	/**
	 * if end with "/>"
	 * @param str
	 * @return
	 */
	public static boolean isSingleHeader(String str){
		if(str.charAt(str.length()-2) == '/'){
			return true;
		}
		return false;
	}
	
	public static void parseHeader(String str, XmlElementAtom ele){
		str = str.substring(1, str.length()-1);
		int off = 0;
		int index = 0;
		index = str.indexOf(" ");
		if(index != -1){
			ele.setLabelName(str.substring(0, index));
		}else{
			//no attrs
			ele.setLabelName(str);
			return;
		}
		off = index+1;
		index = str.indexOf("=");
		while(index != -1){
			index = str.indexOf("\"", index+2);
			if(index == -1){
				throw new RuntimeException("parse xml failed!");
			}
			setAttrs(str.substring(off, index+1), ele.getAttributes());
			off = index+1;
			index = str.indexOf("=", off);
		}
	}
	
	public static void setAttrs(String str, HashMap<String, String> attrs){
		if(StringUtils.isNotEmpty(str)){
			String keyVal [] = str.split("=");
			if(keyVal.length == 2){
				attrs.put(keyVal[0].trim(), keyVal[1].replace("\"", "").trim());
			}else{
				throw new RuntimeException("parse xml error");
			}
		}
	}
	
	public static boolean isMatch(Stack<XmlElementAtom> stack, String str){
		if(StringUtils.isNotEmpty(str)){
			str = str.substring(2, str.length()-1);
			if(str.equals(stack.peek().getLabelName())){
				return true;
			}
			return false;
		}else{
			throw new RuntimeException("parse xml error!");
		}
	}
}
