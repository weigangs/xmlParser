package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class XmlElementAtom{
	
	private String labelName = "";
	
	private HashMap<String, String> attributes = new HashMap<String, String>();
	
	private List<XmlElementAtom> childs = new ArrayList<XmlElementAtom>();

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public HashMap<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(HashMap<String, String> attributes) {
		this.attributes = attributes;
	}

	public List<XmlElementAtom> getChilds() {
		return childs;
	}

	public void setChilds(List<XmlElementAtom> childs) {
		this.childs = childs;
	}

	public void clearData(){
		if(labelName != null){
			labelName = null;
		}
		if(attributes != null){
			attributes.clear();
			attributes = null;
		}
		if(childs != null){
			childs.clear();
			childs = null;
		}
	}
	
}
