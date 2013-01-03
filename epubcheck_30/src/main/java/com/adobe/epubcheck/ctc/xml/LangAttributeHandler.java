package com.adobe.epubcheck.ctc.xml;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class LangAttributeHandler extends DefaultHandler
{

	private String xmlLangAttr=null;
	private String langAttr=null;
	
	
	public String getXmlLangAttr() {
		return xmlLangAttr;
	}

	public void setXmlLangAttr(String xmlLangAttr) {
		this.xmlLangAttr = xmlLangAttr;
	}

	public String getLangAttr() {
		return langAttr;
	}

	public void setLangAttr(String langAttr) {
		this.langAttr = langAttr;
	}

	
	
	
	
	
	
	public void startElement(String uri, String localName,String qName, 
			Attributes attributes) throws SAXException {

	//System.out.println("Start Tag -->:<" +qName+">");

		if(qName.compareToIgnoreCase("HTML")==0)
		{
			
			
			for(int i=0;i<attributes.getLength();i++)
			{
				String attrName=attributes.getQName(i);
				String attrValue=attributes.getValue(i);
				if(attrName.compareToIgnoreCase("xml:lang")==0)
				{
					xmlLangAttr=attrValue;
				}
				if(attrName.compareToIgnoreCase("lang")==0)
				{
					langAttr=attrValue;
				}
			}	
			
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
