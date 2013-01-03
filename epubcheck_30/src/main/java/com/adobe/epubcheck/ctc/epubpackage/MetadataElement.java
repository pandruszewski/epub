package com.adobe.epubcheck.ctc.epubpackage;

import java.util.HashMap;
import java.util.Vector;

public class MetadataElement {

	HashMap<String,String> attributes=new HashMap<String,String>();
	String value="";
	String name="";
	
	public void addAttribute(String attrName, String attrValue)
	{
		attributes.put(attrName,attrValue);
	}
	public HashMap getAllAttributes()
	{
		return attributes;
	}
	public String getAttribute(String attrName)
	{
		return attributes.get(attrName);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
}
