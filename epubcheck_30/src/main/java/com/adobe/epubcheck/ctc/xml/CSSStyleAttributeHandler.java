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

public class CSSStyleAttributeHandler extends DefaultHandler
{

	private Vector<CSSStyleAttributeHandler.StyleAttribute> styleAttributesValues = new Vector<CSSStyleAttributeHandler.StyleAttribute>();
	public Vector<CSSStyleAttributeHandler.StyleAttribute> getStyleAttributesValues() {
		return styleAttributesValues;
	}


	public void setStyleAttributesValues(Vector<CSSStyleAttributeHandler.StyleAttribute> styleAttributesValues) {
		this.styleAttributesValues = styleAttributesValues;
	}


	private Locator locator;
		
	
	public void setDocumentLocator(Locator locator) {
        this.locator = locator;
    }
	
	
	public void startElement(String uri, String localName,String qName, 
			Attributes attributes) throws SAXException {

		for(int i=0;i<attributes.getLength();i++)
			{
				String attrName=attributes.getQName(i);
				String attrValue=attributes.getValue(i);
				if(attrName.compareToIgnoreCase("style")==0)
				{
					
					StyleAttribute sa = new StyleAttribute();
					sa.setValue(attrValue);
					sa.setLine(locator.getLineNumber());
					sa.setColumn(locator.getColumnNumber());
					styleAttributesValues.add(sa);
				}
				
			}	
			
		
	
	}

	
	public class StyleAttribute
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
