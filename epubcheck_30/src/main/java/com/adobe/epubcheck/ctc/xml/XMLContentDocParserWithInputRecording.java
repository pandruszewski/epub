package com.adobe.epubcheck.ctc.xml;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.adobe.epubcheck.api.Report;
import com.adobe.epubcheck.ocf.EncryptionFilter;

public class XMLContentDocParserWithInputRecording {
	
	SAXParserFactory factory;
	SAXParser saxParser;
	ZipFile zip;
	Report report;
	Hashtable<String, EncryptionFilter> enc;
	RecordingInputStream ris;
	BufferedReader in;
	
	public XMLContentDocParserWithInputRecording(ZipFile zip,Report report) throws ParserConfigurationException, SAXException 
	{
		this.zip=zip;
		this.report=report;
		factory = SAXParserFactory.newInstance();
		saxParser = factory.newSAXParser();
		this.enc = new Hashtable<String, EncryptionFilter>();
		
	}
	
	public void parseDoc(String fileEntry, DefaultHandler handler) 
	{
		
		InputStream is;
		
		
		
		try {
			is = getInputStream(fileEntry);
		
			
			
			saxParser.parse(is, handler);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
}
