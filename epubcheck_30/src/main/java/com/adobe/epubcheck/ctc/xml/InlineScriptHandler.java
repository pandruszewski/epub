package com.adobe.epubcheck.ctc.xml;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.adobe.epubcheck.util.CheckingReport;

public class InlineScriptHandler extends DefaultHandler
{

	
	
	String[] scriptContainers = new String[]{"onblur","onchange",
	"oncontextmenu",
	"onfocus",
	"onformchange",
	"onforminput",
	"oninput",
	"oninvalid",
	"onselect",
	"onsubmit",
	"onkeydown",
	"onkeypress",
	"onkeyup",
	"onclick",
	"ondblclick",
	"ondrag",
	"ondragend",
	"ondragenter",
	"ondragleave",
	"ondragover",
	"ondragstart",
	"ondrop",
	"onmousedown",
	"onmousemove",
	"onmouseout",
	"onmouseover",
	"onmouseup",
	"onmousewheel",
	"onscroll"};
	
	private Locator locator;
	private String fileName;
	
	public void setFileName(String fileName)
	{
		this.fileName=fileName;
	}
	
	public InlineScriptHandler(String fileName)
	{
		this.fileName=fileName;
	}
	
	
	public void setDocumentLocator(Locator locator) {
        this.locator = locator;
	}
	
	
	
	
	public void startElement(String uri, String localName,String qName, 
			Attributes attributes) throws SAXException 
		{
			
			for(int i=0;i<attributes.getLength();i++)
			{
				String attrName=attributes.getQName(i);
				String attrValue=attributes.getValue(i);
				for(int c=0;c<scriptContainers.length;c++)
				{
					if(attrName.compareToIgnoreCase(scriptContainers[c])==0)
					{
								CheckingReport.addCheckMessage("EpubScriptCheck",
								String.valueOf(locator.getLineNumber()),
								String.valueOf(locator.getColumnNumber()),
								"Inline scripts found","CTC-043");
					}
				}
			}	
			
		}
	
	

	public void endElement(String uri, String localName,
			String qName) throws SAXException {

	

	}

	public void characters(char ch[], int start, int length) throws SAXException {

	

	}


	
}
