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

public class Epub3StructureHandler extends DefaultHandler
{

	
	String[] HTMLEpub3SpecTags=new String[]{"audio","nav","video"};
	private Locator locator;
	private String entry;
	private int specificTagsCount=0;
	
	public int getSpecificTagsCount() {
		return specificTagsCount;
	}
	public Epub3StructureHandler(String entry)
	{
		this.entry=entry;
		
	}
	public void setDocumentLocator(Locator locator) {
        this.locator = locator;
    }
	
	public void startElement(String uri, String localName,String qName, 
			Attributes attributes) throws SAXException {

	//System.out.println("Start Tag -->:<" +qName+">");
				for(int i=0;i<HTMLEpub3SpecTags.length;i++)
				{
					
					if(qName.compareToIgnoreCase(HTMLEpub3SpecTags[i])==0)
						{
						specificTagsCount++;
						
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