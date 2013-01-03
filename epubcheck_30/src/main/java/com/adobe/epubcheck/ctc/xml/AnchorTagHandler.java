package com.adobe.epubcheck.ctc.xml;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.thaiopensource.util.Localizer;

public class AnchorTagHandler extends DefaultHandler
{

	private Vector<DocTagContent> tagsContent = new Vector<DocTagContent>();
	
	DocTagContent currentScriptTag=null; 
	
	

	boolean scriptReading=false;
	
	public Vector<DocTagContent> getHrefAttributesValues() {
		return tagsContent;
	}


	public void setHrefAttributesValues(Vector<DocTagContent> styleHrefValues) {
		this.tagsContent = styleHrefValues;
	}


	private Locator locator;
		
	
	public void setDocumentLocator(Locator locator) {
        this.locator = locator;
    }
	
	
	public void startElement(String uri, String localName,String qName, 
			Attributes attributes) throws SAXException {

		if(qName.compareToIgnoreCase("script")==0)
		{
			scriptReading=true;
			currentScriptTag = new DocTagContent();
			
			currentScriptTag.setLine(locator.getLineNumber());
			currentScriptTag.setColumn(locator.getColumnNumber());
			tagsContent.add(currentScriptTag);
		}
		
		if(qName.compareToIgnoreCase("A")==0)
		{	
		
			for(int i=0;i<attributes.getLength();i++)
			{
				String attrName=attributes.getQName(i);
				String attrValue=attributes.getValue(i);
				if(attrName.compareToIgnoreCase("href")==0)
				{
					
					DocTagContent sa = new DocTagContent();
					sa.setValue(attrValue);
					sa.setLine(locator.getLineNumber());
					sa.setColumn(locator.getColumnNumber());
					tagsContent.add(sa);
				}
				
			}	
			
		
		}
		if(qName.compareToIgnoreCase("iframe")==0)
		{	
		
			for(int i=0;i<attributes.getLength();i++)
			{
				String attrName=attributes.getQName(i);
				String attrValue=attributes.getValue(i);
				if(attrName.compareToIgnoreCase("src")==0)
				{
					
					DocTagContent sa = new DocTagContent();
					sa.setValue(attrValue);
					sa.setLine(locator.getLineNumber());
					sa.setColumn(locator.getColumnNumber());
					tagsContent.add(sa);
				}
				
			}	
			
		
		}
		if(qName.compareToIgnoreCase("img")==0)
		{	
		
			for(int i=0;i<attributes.getLength();i++)
			{
				String attrName=attributes.getQName(i);
				String attrValue=attributes.getValue(i);
				if(attrName.compareToIgnoreCase("src")==0)
				{
					
					DocTagContent sa = new DocTagContent();
					sa.setValue(attrValue);
					sa.setLine(locator.getLineNumber());
					sa.setColumn(locator.getColumnNumber());
					tagsContent.add(sa);
				}
				
			}	
			
		
		}
	}
		public void characters(char ch[], int start, int length) throws SAXException 
		{
			if(scriptReading==true)
			{
				currentScriptTag.value=new String(ch,start,length);
				tagsContent.add(currentScriptTag);
				scriptReading=false;
			}
			
		}
		
	
	
	
	
	public class DocTagContent
	{
		int line;
		int column;
		String value="";
		
		public int getLine() {
			return line;
		}
		public void setLine(int line) {
			this.line = line;
		}
		public int getColumn() {
			return column;
		}
		public void setColumn(int column) {
			this.column = column;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		
	}

}
