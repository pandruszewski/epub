package com.adobe.epubcheck.ctc.xml;

import java.util.HashMap;

import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class TagsTextSearchHandler extends DefaultHandler{

	
	private Locator locator;
	
	
	HashMap<String,Integer[]> ranges = new HashMap<String,Integer[]>(); 
	public HashMap<String, Integer[]> getRanges() {
		return ranges;
	}

	public void setRanges(HashMap<String, Integer[]> ranges) {
		this.ranges = ranges;
	}

	public void setDocumentLocator(Locator locator) {
        this.locator = locator;
    }
	
	public void startElement(String uri, String localName,String qName, 
			Attributes attributes) throws SAXException {

	//System.out.println("Start Tag -->:<" +qName+">");
		//byte[] bs=ris.getBytes();
		//String val=new String(bs);
		//logger.warn(new String(bs));
		//ris.resetSink();
	
		if(qName.compareToIgnoreCase("HEAD")==0)
		{
			
				Integer[] r= new Integer[4];
				r[0]=locator.getLineNumber();
				r[1]=locator.getColumnNumber();
				ranges.put("head",r);
			
				
				
		}
		if(qName.compareToIgnoreCase("BODY")==0)
		{
			
				Integer[] r= new Integer[4];
				r[0]=locator.getLineNumber();
				r[1]=locator.getColumnNumber();
				ranges.put("body",r);
			
				
				
		}
	
	}

	public void endElement(String uri, String localName,
			String qName) throws SAXException {

	//System.out.println("End Tag   -->:</" + qName+">");
		if(qName.compareToIgnoreCase("HEAD")==0 )
		{
			if(ranges.containsKey("head"))
			{
				Integer[] r=ranges.get("head");
				r[2]=locator.getLineNumber();
				r[3]=locator.getColumnNumber();
				ranges.put("head",r);
			}
			
		}
		if(qName.compareToIgnoreCase("Body")==0 )
		{
			if(ranges.containsKey("body"))
			{
				Integer[] r=ranges.get("body");
				r[2]=locator.getLineNumber();
				r[3]=locator.getColumnNumber();
				ranges.put("body",r);
			}
			
		}

	}

	public void characters(char ch[], int start, int length) throws SAXException {

	//System.out.println("-----Tag value----------->"+new String(ch, start, length));

	}
	

	
}
