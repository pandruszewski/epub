package com.adobe.epubcheck.ctc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;
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
import com.adobe.epubcheck.opf.DocumentValidator;
import com.adobe.epubcheck.util.CheckingReport;

public class EpubTocCheck implements DocumentValidator{

	XmlDocParser docParser;
	ZipFile zip;
	Report report;
	Document  packageMainDocument;
	String pathRootFile;
	EpubPackage epack;
	
	public  EpubTocCheck(ZipFile zip, Document  packageMainDocument, Report report, String pathRootFile)
	{
		this.zip=zip;
		this.report=report;
		this.packageMainDocument=packageMainDocument;
		this.pathRootFile=pathRootFile;
		docParser = new XmlDocParser(zip,report);
		
	}
	
	public  EpubTocCheck(EpubPackage epack, Report report)
	{
		this.zip=epack.getZip();
		this.report=report;
		this.packageMainDocument=epack.getPackDoc();
		this.pathRootFile=epack.getPackageMainFile();
		this.epack=epack;
		docParser = new XmlDocParser(zip,report);
		
	}
	
	
	@Override
	public boolean validate() 
	{
	
		boolean result=true;
	
		if(epack.getSpine().getToc()==null)
		{
			CheckingReport.addCheckMessage(pathRootFile,
					String.valueOf(-1),
					String.valueOf(-1),
					"<toc> element not defined", 
					"CTC-016");
			result=false;
		}
		
		
		
		
		
		
		
			
		return result;
	}
	
	
	

	
	
}
