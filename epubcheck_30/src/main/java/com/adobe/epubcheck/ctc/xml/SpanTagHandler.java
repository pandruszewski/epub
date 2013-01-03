package com.adobe.epubcheck.ctc.xml;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SpanTagHandler extends DefaultHandler
{

	
	Element currentElement=null;
	Element topElement=null;
	int elementCounter=0;
	int generateMessage=0;
	
	
	public Element getTopElement()
	{
		return topElement;
	}
	public int getGenerateMessage()
	{
		return generateMessage;
	}
	public void countNestedElements(Element e)
	{
		for(int i=0;i<e.nestedElements.size();i++)
		{
			Element childElement = e.nestedElements.get(i);
			countNestedElements(childElement);
			if(childElement.elementName.compareToIgnoreCase("DIV")==0)
			{
				e.divElementsCounter++;
				e.spanOrDivElementsCounter++;
				
			}
			if(childElement.elementName.compareToIgnoreCase("DIV")==0)
			{
				e.divElementsCounter++;
				e.spanOrDivElementsCounter++;
			}
			
		}
		if(e.spanOrDivElementsCounter>5)
			generateMessage++;
	}
	
	public void startElement(String uri, String localName,String qName, 
			Attributes attributes) throws SAXException 
	{

			Element newElement= new Element(qName);
			newElement.setParentElement(currentElement);
			if(currentElement!=null)
			{
				currentElement.addNestedElement(newElement);
				currentElement=newElement;
			}
			else
			{	
				currentElement=newElement;
				topElement=currentElement;
			}	
	
	}

	public void endElement(String uri, String localName,
			String qName) throws SAXException 
			{
				currentElement=currentElement.getParentElement();
			}

	public void characters(char ch[], int start, int length) throws SAXException {

	//System.out.println("-----Tag value----------->"+new String(ch, start, length));

	}
	
	
}

class Element
{
	HashMap<String, String> attrs = new HashMap<String, String>();
	
	Element parentElement;
	Vector <Element> nestedElements=new Vector<Element>();
	String elementName;
	int divElementsCounter=0;
	int spanElementsCounter=0;
	int spanOrDivElementsCounter=0;
	
	
	public Element(String name)
	{
		elementName=name;
	}
	public void setParentElement(Element e)
	{
		parentElement=e;
		
	}
	public Element getParentElement()
	{
		return parentElement;
		
	}
	public void addNestedElement(Element e)
	{
		nestedElements.add(e);
	}
	
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