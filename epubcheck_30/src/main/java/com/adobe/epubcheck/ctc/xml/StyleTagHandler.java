package com.adobe.epubcheck.ctc.xml;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.adobe.epubcheck.util.CheckingReport;

public class StyleTagHandler extends DefaultHandler
{

	int styleAtributeCounter=0;
	public int getStyleAttributeCounter()
	{
		return styleAtributeCounter;
	}
	public void startElement(String uri, String localName,String qName, 
			Attributes attributes) throws SAXException {

	//System.out.println("Start Tag -->:<" +qName+">");

		
			
			ScriptElement scriptElement = new ScriptElement();
			for(int i=0;i<attributes.getLength();i++)
			{
				
				String attrName=attributes.getQName(i);
				if(attrName.compareToIgnoreCase("style")==0)
				{
					styleAtributeCounter++;
				}
					
			}
			
		
	
	}

	public void endElement(String uri, String localName,
			String qName) throws SAXException {

	

	}

	public void characters(char ch[], int start, int length) throws SAXException {

	

	}
	
	
}
