package com.adobe.epubcheck.ctc;

import java.util.zip.ZipFile;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.adobe.epubcheck.api.Report;
import com.adobe.epubcheck.ctc.epubpackage.EpubPackage;
import com.adobe.epubcheck.ctc.epubpackage.ManifestItem;
import com.adobe.epubcheck.ctc.xml.ScriptTagHandler;
import com.adobe.epubcheck.ctc.xml.XMLContentDocParser;
import com.adobe.epubcheck.opf.DocumentValidator;
import com.adobe.epubcheck.util.CheckingReport;
import com.adobe.epubcheck.util.SearchDictionary;
import com.adobe.epubcheck.util.SearchDictionary.DictionaryType;

public class EpubScriptCheck implements DocumentValidator {

	XmlDocParser docParser;
	ZipFile zip;
	Report report;
	Document  packageMainDocument;
	String pathRootFile;
	EpubPackage epack;
	
	public  EpubScriptCheck(ZipFile zip, Document  packageMainDocument, Report report, String pathRootFile)
	{
		this.zip=zip;
		this.report=report;
		this.packageMainDocument=packageMainDocument;
		this.pathRootFile=pathRootFile;
		docParser = new XmlDocParser(zip,report);
		
	}
	
	public  EpubScriptCheck(EpubPackage epack, Report report)
	{
		this.zip=epack.getZip();
		this.report=report;
		this.packageMainDocument=epack.getPackDoc();
		this.pathRootFile=epack.getPackageMainFile();
		this.epack=epack;
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
					ScriptTagHandler sh = new ScriptTagHandler();
					String fileToParse="";
					if(epack.getPackageMainPath()!=null && epack.getPackageMainPath().length()>0)
					{
						fileToParse=epack.getPackageMainPath()+"/"+mi.getHref();
					}
					else
						fileToParse=mi.getHref();
					parser.parseDoc(fileToParse, sh);
					if(sh.getScriptElementCount()>0)
					{
					
						if(mi.getProperties()!=null && mi.getProperties().contains("scripted"))
						{
							CheckingReport.addCheckMessage("EpubScriptCheck",
									String.valueOf(-1),
									String.valueOf(-1),
									"Content file "+mi.getHref()+" contains SCRIPT tag and it's marked as scripted.","CTC-017");
							
						}
						else
						{
							CheckingReport.addCheckMessage("EpubScriptCheck",
									String.valueOf(-1),
									String.valueOf(-1),
									"Content file "+mi.getHref()+" contains SCRIPT tag and it's NOT marked as scripted.","CTC-018");
							
						}
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
	
}
