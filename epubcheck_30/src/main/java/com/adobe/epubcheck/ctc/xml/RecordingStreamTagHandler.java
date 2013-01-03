package com.adobe.epubcheck.ctc.xml;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class RecordingStreamTagHandler extends DefaultHandler
{
	 private Locator locator;
	RecordingInputStream ris;
	public void setRecorder(RecordingInputStream ris)
	{
		this.ris=ris;
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
	
		if(qName.compareToIgnoreCase("H1")==0)
		{
			int stop=0;
			String location = "";
			location = locator.getSystemId(); // XML-document name;
            location += " line " + locator.getLineNumber();
            location += ", column " + locator.getColumnNumber();
            location += ": ";
           
            System.out.println(location);
		}
	
	}

	public void endElement(String uri, String localName,
			String qName) throws SAXException {

	//System.out.println("End Tag   -->:</" + qName+">");
		if(qName.compareToIgnoreCase("HEAD")==0 )
		{
			int stop=0;
			
		}
		

	}

	public void characters(char ch[], int start, int length) throws SAXException {

	//System.out.println("-----Tag value----------->"+new String(ch, start, length));

	}
	
	
}
