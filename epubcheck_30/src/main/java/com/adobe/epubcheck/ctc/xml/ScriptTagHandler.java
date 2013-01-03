package com.adobe.epubcheck.ctc.xml;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ScriptTagHandler extends DefaultHandler
{

	Vector<ScriptElement> scriptElements = new Vector();
	
	public int getScriptElementCount()
	{
		return scriptElements.size();
	}
	public void startElement(String uri, String localName,String qName, 
			Attributes attributes) throws SAXException {

	//System.out.println("Start Tag -->:<" +qName+">");

		if(qName.compareToIgnoreCase("SCRIPT")==0)
		{
			
			ScriptElement scriptElement = new ScriptElement();
			for(int i=0;i<attributes.getLength();i++)
			{
				String attrName=attributes.getQName(i);
				String attrValue=attributes.getValue(i);
				scriptElement.addAttribute(attrName, attrValue);
			}
			scriptElements.add(scriptElement);
		}
	
	}

	public void endElement(String uri, String localName,
			String qName) throws SAXException {

	//System.out.println("End Tag   -->:</" + qName+">");

	}

	public void characters(char ch[], int start, int length) throws SAXException {

	//System.out.println("-----Tag value----------->"+new String(ch, start, length));

	}
	
	
}
class ScriptElement
{
	HashMap<String, String> attrs = new HashMap<String, String>();
	
	public void addAttribute(String name, String value)
	{
		attrs.put(name,value);
	}
	public String getAttribute(String name)
	{
		return attrs.get(name);
	}
	public Vector getAllAttributes()
	{
		Vector<String[]> attributes = new Vector<String[]>();
		Set keys=attrs.keySet();
		Iterator it=keys.iterator();
		while(it.hasNext())
		{
			String[] attribute = new String[2];
			String key=(String)it.next();
			String value=attrs.get(key);
			attribute[0]=key;
			attribute[1]=value;
		}
		return attributes;
	}
	
}