package com.adobe.epubcheck.ctc;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.adobe.epubcheck.api.Report;
import com.adobe.epubcheck.ctc.epubpackage.EpubPackage;
import com.adobe.epubcheck.ctc.epubpackage.ManifestItem;

import com.adobe.epubcheck.ctc.xml.CSSStyleAttributeHandler;
import com.adobe.epubcheck.ctc.xml.TagsTextSearchHandler;
import com.adobe.epubcheck.ctc.xml.XMLContentDocParser;
import com.adobe.epubcheck.ocf.EncryptionFilter;
import com.adobe.epubcheck.ocf.OCFData;
import com.adobe.epubcheck.opf.DocumentValidator;
import com.adobe.epubcheck.util.CheckingReport;

import com.adobe.epubcheck.util.EPUBVersion;
import com.adobe.epubcheck.util.SearchDictionary;

import com.adobe.epubcheck.util.TextSearchDictionaryEntry;
import com.adobe.epubcheck.util.SearchDictionary.DictionaryType;
import com.adobe.epubcheck.xml.XMLParser;

public class EpubCSSCheck implements DocumentValidator{
	ZipFile zip;
	Hashtable<String, EncryptionFilter> enc;
	Report report;
	String checkType;
	EPUBVersion version;
	EpubPackage epack;
	
	
	
	public EpubCSSCheck(Report report, ZipFile zip,  EPUBVersion version) 
	{
		this.zip = zip;
		this.enc = new Hashtable<String, EncryptionFilter>();
		this.report = report;
		this.version=version;
		
		
		
	}	
		
	public EpubCSSCheck(EpubPackage epack,Report report) 
	{
		this.epack=epack;
		this.zip = epack.getZip();
		this.enc = new Hashtable<String, EncryptionFilter>();
		this.report = report;
		this.version=epack.getVersion();
	}	
	
	
	public boolean validate()
	{
		SearchDictionary tsd = new SearchDictionary(DictionaryType.CSS_VALUES);
		SearchDictionary cssTypes = new SearchDictionary(DictionaryType.CSS_FILES);
		
		SearchDictionary validTypes = new SearchDictionary(DictionaryType.VALID_TEXT_MEDIA_TYPES);
		
		for(int i=0;i<epack.getManifest().itemsLength();i++)
		{
			ManifestItem itemEntry=epack.getManifest().getItem(i);
		
			if(isValidMediaType(cssTypes, itemEntry.getMediaType()))
			{
						String fileToParse="";
						if(epack.getPackageMainPath()!=null && epack.getPackageMainPath().length()>0)
						{
							fileToParse=epack.getPackageMainPath()+"/"+itemEntry.getHref();
						}
						else
							fileToParse=itemEntry.getHref();
							
														
							find( fileToParse, tsd);
			}
			if(isValidMediaType(validTypes, itemEntry.getMediaType()))
			{
						String fileToParse="";
						if(epack.getPackageMainPath()!=null && epack.getPackageMainPath().length()>0)
						{
							fileToParse=epack.getPackageMainPath()+"/"+itemEntry.getHref();
						}
						else
							fileToParse=itemEntry.getHref();
							
						XMLContentDocParser parser;
						try {
							parser = new XMLContentDocParser(epack.getZip(), report);
						
						CSSStyleAttributeHandler h = new CSSStyleAttributeHandler();
						
						parser.parseDoc(fileToParse,h);
						Vector<CSSStyleAttributeHandler.StyleAttribute> v = h.getStyleAttributesValues();
													
						
						for(int e=0;e<v.size();e++)
						{	
						
							CSSStyleAttributeHandler.StyleAttribute value=v.elementAt(e);
						
							searchInsideValue( value, tsd,fileToParse);
						}
						} catch (ParserConfigurationException e1) {
							 
							e1.printStackTrace();
						} catch (SAXException e1) {
							
							e1.printStackTrace();
						}
			}
			
		}
		
		
		return true;
	}
	
	
	private boolean isValidMediaType(SearchDictionary validTypes, String typeToCheck) {
		for (int i = 0; i < validTypes.getDictEntries().size(); i++) {
			if ((validTypes.getDictEntries().get(i).getRegexExp()).compareToIgnoreCase(typeToCheck) == 0) {
				return true;
			}
		}
		return false;
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
	public Vector<String> find(String entry, SearchDictionary tds) {
		Vector<String> result = new Vector<String>();
	
		InputStream is;
		try {

			is = getInputStream(entry);

			Scanner in = null;

			in = new Scanner(is);
			int lineCounter = 1;
			
			
			while (in.hasNextLine()) 
			{
			
				
				String line = in.nextLine();
					
						for (int s = 0; s < tds.getDictEntries().size(); s++) 
						{
							int pos = 0;
							
							TextSearchDictionaryEntry de = tds.getDictEntries().get(s);
							String searchedValue= de.getSearchedValue();
							String regExValue = de.getRegexExp();
							String messageCode = de.getErrorCode();
							
							
							Pattern p = Pattern.compile(regExValue);
		    				Matcher matcher = p.matcher(line);
		    				int position=0;
		    				while (matcher.find(position))
		    				{	
		    					position=matcher.end();
		    				
		    				    	
		    				    	String matchedPos=String.valueOf(matcher.start());
		    				    	String message="Found "+searchedValue+" in file "+entry+" at line "+lineCounter+" and column "+matchedPos;
		    				    
		    				    	CheckingReport.addCheckMessage(entry,
		    								String.valueOf(lineCounter),
		    								String.valueOf(matchedPos),
		    								message,messageCode);
		    					
		    				}
						}
						
				lineCounter++;
			}
		} catch (IOException e1) {
			
			CheckingReport.addCheckMessage("EpubTextContentCheck",
					String.valueOf(-1),
					String.valueOf(-1),
					"ZipEntry "+entry+" not found. Missed package file or wrong item href value","CTC-0003");
			
		
		} catch (Exception e) {
			e.printStackTrace();
			CheckingReport.addCheckMessage("EpubTextContentCheck",
					String.valueOf(-1),
					String.valueOf(-1),
					"Error during package content reading. Runtime message:"+e.getMessage(),"CTC-004");
			
		}

		return result;
	}
	
	private void searchInsideValue(CSSStyleAttributeHandler.StyleAttribute entry, SearchDictionary tds,String file)
	{
		for (int s = 0; s < tds.getDictEntries().size(); s++) 
		{
			int pos = 0;
			
			TextSearchDictionaryEntry de = tds.getDictEntries().get(s);
			String searchedValue= de.getSearchedValue();
			String regExValue = de.getRegexExp();
			String messageCode = de.getErrorCode();
			
			
			Pattern p = Pattern.compile(regExValue);
			Matcher matcher = p.matcher(entry.getValue());
			int position=0;
			while (matcher.find(position))
			{	
				position=matcher.end();
			
			    	
			    	String matchedPos=String.valueOf(matcher.start());
			    	String message="Found "+searchedValue+" in file "+file+" at line "+entry.getLine()+" and column "+entry.getColumn();
			    
			    	CheckingReport.addCheckMessage(file,
							String.valueOf(entry.getLine()),
							String.valueOf(entry.getColumn()),
							message,messageCode);
				
			}
		}
	}
	
	
	
		
}


