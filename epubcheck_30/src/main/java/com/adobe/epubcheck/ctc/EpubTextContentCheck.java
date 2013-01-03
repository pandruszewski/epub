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

public class EpubTextContentCheck implements DocumentValidator{
	ZipFile zip;
	Hashtable<String, EncryptionFilter> enc;
	Report report;
	String checkType;
	EPUBVersion version;
	EpubPackage epack;
	
	
	String containerEntry = "META-INF/container.xml";
	String[] validTypes = new String[] { "application/xhtml+xml",
			"application/x-dtbncx+xml", "text/css" };
	String[] searchStrings = new String[] { "script", "SCRIPT", "js", "JS",
			"span" };

	public EpubTextContentCheck(Report report, ZipFile zip,  EPUBVersion version) 
	{
		this.zip = zip;
		this.enc = new Hashtable<String, EncryptionFilter>();
		this.report = report;
		this.version=version;
		
		
		
	}	
		
	public EpubTextContentCheck(Report report, EpubPackage epack) 
	{
		this.epack=epack;
		this.zip = epack.getZip();
		this.enc = new Hashtable<String, EncryptionFilter>();
		this.report = report;
		this.version=epack.getVersion();
	}	
	
	
	public boolean validate()
	{
		SearchDictionary tsd = new SearchDictionary(DictionaryType.SEARCH);
		SearchDictionary validTypes = new SearchDictionary(DictionaryType.VALID_TEXT_MEDIA_TYPES);
		//PackageReader epackReader=new PackageReader(zip, report);
		//epackReader.readPackageData();
			
		for(int i=0;i<epack.getManifest().itemsLength();i++)
		{
			ManifestItem itemEntry=epack.getManifest().getItem(i);
			if(isValidMediaType(validTypes, itemEntry.getMediaType()))
			{
				//String entry=epack.getPackageMainPath()+"/"+itemEntry.getHref();
				
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
						TagsTextSearchHandler handler = new TagsTextSearchHandler();
						parser.parseDoc(fileToParse, handler);
						HashMap<String,Integer[]> tagRanges= handler.getRanges(); 
						String[] keys= tagRanges.keySet().toArray(new String[0]); 
						for(int t=0;t<keys.length;t++)
						{
							Integer[] tagRange=tagRanges.get(keys[t]);
							
							find( fileToParse, tsd,tagRange[0],tagRange[1],tagRange[2],tagRange[3]);
						}
				} catch (ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
	public Vector<String> find(String entry, SearchDictionary tds,int startLine,int beginChar,int endLine,int endChar) {
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
				if(lineCounter>=startLine && lineCounter<=endLine)
				{
					if(lineCounter==startLine && beginChar<line.length()-1)
					{
						line=line.substring(beginChar, line.length());
					}
					if(lineCounter==endLine && endChar<line.length()-1)
					{
						line=line.substring(0, endChar);
					}
					
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
		    				    	System.out.println(message);
		    				    	CheckingReport.addCheckMessage(entry,
		    								String.valueOf(lineCounter),
		    								String.valueOf(matchedPos),
		    								message,messageCode);
		    					
		    				}
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
	
	
	
	
	
		
}


