	package com.adobe.epubcheck.ctc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.adobe.epubcheck.api.Report;
import com.adobe.epubcheck.ctc.epubpackage.EpubPackage;
import com.adobe.epubcheck.ctc.epubpackage.ManifestItem;
import com.adobe.epubcheck.ctc.xml.HTML5StructureHandler;
import com.adobe.epubcheck.ctc.xml.HTML5TagHandler;
import com.adobe.epubcheck.ctc.xml.HTMLTagsAnalyseHandler;
import com.adobe.epubcheck.ctc.xml.InlineScriptHandler;
import com.adobe.epubcheck.ctc.xml.ScriptTagHandler;
import com.adobe.epubcheck.ctc.xml.XMLContentDocParser;
import com.adobe.epubcheck.ocf.EncryptionFilter;
import com.adobe.epubcheck.opf.DocumentValidator;
import com.adobe.epubcheck.util.CheckingReport;
import com.adobe.epubcheck.util.SearchDictionary;
import com.adobe.epubcheck.util.TextSearchDictionaryEntry;
import com.adobe.epubcheck.util.SearchDictionary.DictionaryType;

public class EpubHTML5StructureCheck implements DocumentValidator {

	XmlDocParser docParser;
	ZipFile zip;
	Report report;
	Document  packageMainDocument;
	String pathRootFile;
	EpubPackage epack;
	Hashtable<String, EncryptionFilter> enc;
	public  EpubHTML5StructureCheck(ZipFile zip, Document  packageMainDocument, Report report, String pathRootFile)
	{
		this.zip=zip;
		this.report=report;
		this.packageMainDocument=packageMainDocument;
		this.pathRootFile=pathRootFile;
		docParser = new XmlDocParser(zip,report);
		
		
	}
	
	public  EpubHTML5StructureCheck(EpubPackage epack, Report report)
	{
		this.zip=epack.getZip();
		this.report=report;
		this.packageMainDocument=epack.getPackDoc();
		this.pathRootFile=epack.getPackageMainFile();
		this.epack=epack;
		this.enc = new Hashtable<String, EncryptionFilter>();
	}
	
	
	@Override
	public boolean validate() 
	{
	
		boolean result=false;
		SearchDictionary vtsd=new SearchDictionary(DictionaryType.VALID_TEXT_MEDIA_TYPES);
		for(int i=0;i<epack.getManifest().itemsLength();i++)
		{
			ManifestItem mi = epack.getManifest().getItem(i);
			if(isValidMediaType(vtsd,mi.getMediaType()))
			{
				try {
					XMLContentDocParser parser = new XMLContentDocParser(epack.getZip(), report);
					HTMLTagsAnalyseHandler sh = new HTMLTagsAnalyseHandler(null);
					String fileToParse="";
					if(epack.getPackageMainPath()!=null && epack.getPackageMainPath().length()>0)
					{
						fileToParse=epack.getPackageMainPath()+"/"+mi.getHref();
					}
					else
						fileToParse=mi.getHref();
					
					//sh.setFileName(fileToParse);
					//parser.parseDoc(fileToParse, sh);
					/***VALIDATE FILE EXTENSION***/
					
					String fileExtension=mi.getHref().substring(mi.getHref().lastIndexOf('.')+1, mi.getHref().length());
					if(!(fileExtension.compareToIgnoreCase("html")==0 || fileExtension.compareToIgnoreCase("htm")==0 || fileExtension.compareToIgnoreCase("xhtml")==0))
					{
						CheckingReport.addCheckMessage(mi.getHref(),
								String.valueOf(-1),
								String.valueOf(-1),
								"Invalid file extension for HTML5 file( file name:"+mi.getHref()+")","CTC-044");
					}
					/***VALIDATE DOCTYPE***/
					int docTypeMatches=find(fileToParse);
					if(docTypeMatches>1)
					{
						CheckingReport.addCheckMessage(mi.getHref(),
								String.valueOf(-1),
								String.valueOf(-1),
								"HTML4 DOCTYPE definition at file "+mi.getHref(),"CTC-046");
					}
					
					parser.parseDoc(fileToParse, sh);
				
					if(sh.getHtml4SpecTagsCounter()>0)
					{
						CheckingReport.addCheckMessage(mi.getHref(),
								String.valueOf(-1),
								String.valueOf(-1),
								"HTML5 specific tags are included in Epub3","CTC-047");
					}
					/*
					if(sh.getHtml5SpecTagsCounter()>0)
					{
						CheckingReport.addCheckMessage(mi.getHref(),
								String.valueOf(-1),
								String.valueOf(-1),
								"HTML5 specific tags found "+mi.getHref(),"CTC-048");
					}
					*/
					
				} catch (ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return result;
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
	public int find(String entry) 
	{
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
			Pattern p = Pattern.compile("<*!*[Dd][Oo][Cc][Tt][Yy][Pp][Ee]");
				Matcher matcher = p.matcher(line);
	
				
				
				int positiveMatches=0;
				while (matcher.find())
				{
						Pattern patternElement = Pattern.compile("[Hh][Tt][Mm][Ll]");
						Matcher matcherElement = patternElement.matcher(line);
						
						
	
						
						if(matcherElement.find())
						{
							positiveMatches++;
							//System.out.println("Spasowanie "+"[Hh][Tt][Mm][Ll]");
						}
						matcherElement.reset();
						
						patternElement = Pattern.compile("[Pp][Uu][Bb][Ll][Ii][Cc]");
						matcherElement = patternElement.matcher(line);
						if(matcherElement.find())
						{
							positiveMatches++;
							//System.out.println("Spasowanie "+"[Pp][Uu][Bb][Ll][Ii][Cc]");
						}
						matcherElement.reset();
						
						patternElement = Pattern.compile("[Ww][3][Cc]//[Dd][Tt][Dd]");
						matcherElement = patternElement.matcher(line);
						if(matcherElement.find())
						{
							positiveMatches++;
						//	System.out.println("Spasowanie "+"[Ww][3][Cc]//[Dd][Tt][Dd]");
						}
						matcherElement.reset();
						
						
						patternElement = Pattern.compile("^[Xx][Hh][Tt][Mm][Ll]");
						matcherElement = patternElement.matcher(line);
						if(matcherElement.find())
						{
							positiveMatches++;
					//		System.out.println("Spasowanie "+"[Xx][Hh][Tt][Mm][Ll]");
						}
						matcherElement.reset();
						/*
					}*/
					
					int x=0;
					return positiveMatches;
					//Pattern p = Pattern.compile("XHTML 1.0 Strict");
					//Matcher matcher = p.matcher(line);
					
					
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

		return 0;
	}

	
	
}
