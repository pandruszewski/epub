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

public class HTML5StructureHandler extends DefaultHandler
{

	
	String[] HTML5SpecTags=new String[]{"article","aside","audio","bdi","canvas","command","datalist","details","dialog","embed","figcaption","figure","footer","header","hgroup","keygen","mark","meter","nav","output","progress","rp","rt","ruby","section","source","summary","time","track","wbr","video"};
	private Locator locator;
	private String entry;
	
	public HTML5StructureHandler(String entry)
	{
		this.entry=entry;
		
	}
	
	public void setFileName(String fileName)
	{
		this.entry=fileName;
	}
	
	public void setDocumentLocator(Locator locator) {
        this.locator = locator;
    }
	
	public void startElement(String uri, String localName,String qName, 
			Attributes attributes) throws SAXException {
		System.out.println(qName+" "+attributes);
	//System.out.println("Start Tag -->:<" +qName+">");
		/*		
		for(int i=0;i<HTML5SpecTags.length;i++)
				{
					
					if(qName.compareToIgnoreCase(HTML5SpecTags[i])==0)
						{
						
							String messageCode="CTC-023";
							String message="Found <"+HTML5SpecTags[i]+"> (HTML5 specific tag) in file "+entry+" at line "+locator.getLineNumber()+" and column "+locator.getColumnNumber();
							CheckingReport.addCheckMessage(entry,
    								String.valueOf(locator.getLineNumber()),
    								String.valueOf(locator.getColumnNumber()),
    								message,messageCode);
							
						}
				}	
		*/		
	}

	public void endElement(String uri, String localName,
			String qName) throws SAXException {

	//System.out.println("End Tag   -->:</" + qName+">");

	}

	public void characters(char ch[], int start, int length) throws SAXException {

	//System.out.println("-----Tag value----------->"+new String(ch, start, length));

	}
	
	

	
}