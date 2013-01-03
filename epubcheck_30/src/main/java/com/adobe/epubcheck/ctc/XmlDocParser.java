package com.adobe.epubcheck.ctc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Stack;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;

import com.adobe.epubcheck.api.Report;
import com.adobe.epubcheck.ocf.EncryptionFilter;

public class XmlDocParser {
	
	ZipFile zip;
	Hashtable<String, EncryptionFilter> enc;
	Report report;
	
	String version;


	
public XmlDocParser(ZipFile zip,Report report)	
{
	this.zip=zip;
	this.report=report;
	this.enc = new Hashtable<String, EncryptionFilter>();
	
}
public Document parseDocument(String fileEntry)
{
	
		Document doc=null;
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();		
			InputStream is;
			is = getInputStream(fileEntry);
			doc = readXML(is,"elementLineNumber","elementColumnNumber");
		} 
		catch (ParserConfigurationException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (SAXException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doc;
	
	
	}	


	public InputStream getInputStream(String name) throws IOException {
		ZipEntry entry = zip.getEntry(name);
		if (entry == null)
			return null;
		InputStream in = zip.getInputStream(entry);
		EncryptionFilter filter = (EncryptionFilter) enc.get(name);
		if (filter == null)
			return in;
		if (filter.canDecrypt())
			return filter.decrypt(in);
		return null;
	}

	
	
	
	
	public static Document readXML(InputStream is, final String lineNumAttribName, final String columnNumAttribName) throws IOException, SAXException 
	{
	    final Document doc;
	    SAXParser parser;
	    try {
	        SAXParserFactory factory = SAXParserFactory.newInstance();
	        parser = factory.newSAXParser();
	        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
	        doc = docBuilder.newDocument();           
	    } catch(ParserConfigurationException e){
	        throw new RuntimeException("Can't create SAX parser / DOM builder.", e);
	    }

	    final Stack<Element> elementStack = new Stack<Element>();
	    final StringBuilder textBuffer = new StringBuilder();
	    DefaultHandler handler = new DefaultHandler() {
	        private Locator locator;

	        @Override
	        public void setDocumentLocator(Locator locator) {
	            this.locator = locator; //Save the locator, so that it can be used later for line tracking when traversing nodes.
	        }
	       
	        @Override
	        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {               
	            addTextIfNeeded();
	            Element el = doc.createElement(qName);
	            for(int i = 0;i < attributes.getLength(); i++)
	                el.setAttribute(attributes.getQName(i), attributes.getValue(i));
	            el.setAttribute(lineNumAttribName, String.valueOf(locator.getLineNumber()));
	            el.setAttribute(columnNumAttribName, String.valueOf(locator.getColumnNumber()));
	            elementStack.push(el);               
	        }
	       
	        @Override
	        public void endElement(String uri, String localName, String qName){
	            addTextIfNeeded();
	            Element closedEl = elementStack.pop();
	            if (elementStack.isEmpty()) { // Is this the root element?
	                doc.appendChild(closedEl);
	            } else {
	                Element parentEl = elementStack.peek();
	                parentEl.appendChild(closedEl);                   
	            }
	        }
	       
	        @Override
	        public void characters (char ch[], int start, int length) throws SAXException {
	            textBuffer.append(ch, start, length);
	        }
	       
	        // Outputs text accumulated under the current node
	        private void addTextIfNeeded() {
	            if (textBuffer.length() > 0) {
	                Element el = elementStack.peek();
	                Node textNode = doc.createTextNode(textBuffer.toString());
	                el.appendChild(textNode);
	                textBuffer.delete(0, textBuffer.length());
	            }
	        }           
	    };
	    parser.parse(is, handler);
	   
	    return doc;
	}   

	
	
}
