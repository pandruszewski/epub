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

public class EpubNavCheck implements DocumentValidator{

	XmlDocParser docParser;
	ZipFile zip;
	Report report;
	Document  packageMainDocument;
	String pathRootFile;
	EpubPackage epack;
	
	public  EpubNavCheck(ZipFile zip, Document  packageMainDocument, Report report, String pathRootFile)
	{
		this.zip=zip;
		this.report=report;
		this.packageMainDocument=packageMainDocument;
		this.pathRootFile=pathRootFile;
		docParser = new XmlDocParser(zip,report);
		
	}
	
	public  EpubNavCheck(EpubPackage epack, Report report)
	{
		this.zip=epack.getZip();
		this.report=report;
		this.packageMainDocument=epack.getPackDoc();
		this.pathRootFile=epack.getPackageMainFile();
		this.epack=epack;
		docParser = new XmlDocParser(zip,report);
		
	}
	
	
	@Override
	public boolean validate() {
	
		boolean result=false;
		Vector<String> navDocPath=getNAVDocuments(packageMainDocument,pathRootFile);
		
		
		
		
		if(navDocPath!=null && navDocPath.size()>0)
		{			
			String navDoc="";
			boolean hasNavElements=false;
			for(int i=0;i<navDocPath.size();i++)
			{
				
				navDoc=navDocPath.get(i);
				if(navDoc!=null)
				{	
					String fileToParse="";
					if(epack.getPackageMainPath()!=null && epack.getPackageMainPath().length()>0)
					{
						fileToParse=epack.getPackageMainPath()+"/"+navDocPath;
					}
					else
						fileToParse=navDoc;
						
					if(checkNavDoc(fileToParse))
					{	
						hasNavElements=true;
					}
				}
			}
			
			if(hasNavElements)
				result=true;
			else
			{	
				CheckingReport.addCheckMessage(pathRootFile,
						String.valueOf(-1),
						String.valueOf(-1),
						"Navigation not defined for Epub3", 
						"CTC-015");	
			}
			
		}
		else
		{
			CheckingReport.addCheckMessage(pathRootFile,
					String.valueOf(-1),
					String.valueOf(-1),
					"Navigation not defined for Epub3", 
					"CTC-015");	
		}
			
		return result;
	}
	
	
	
	private Vector<String> getNAVDocuments(Document doc,String pathRootFile)
	{
		
				Vector<String> navItems = new Vector<String>();
		
					
					NodeList manifestList = doc.getElementsByTagName("manifest");
					for(int m=0;m<manifestList.getLength();m++)
					{
						
						
						Node manifestNode=manifestList.item(m);
						NodeList itemNodes=manifestNode.getChildNodes();
						
						for(int it=0;it<itemNodes.getLength();it++)
						{
							NamedNodeMap itemNodeAttributes=itemNodes.item(it).getAttributes();
							if(itemNodeAttributes!=null && itemNodeAttributes.getNamedItem("properties")!=null)
							{
								String nodePropertiesAttr=itemNodeAttributes.getNamedItem("properties").getNodeValue();
								if(nodePropertiesAttr!=null && nodePropertiesAttr.compareToIgnoreCase("nav")==0)
								{
									String hrefValue=null;
									if(itemNodeAttributes.getNamedItem("href").getNodeValue() !=null)
									{
										hrefValue=itemNodeAttributes.getNamedItem("href").getNodeValue();
									}
									
											
									navItems.add(hrefValue);
								}
								
							}
						}	
					}
					
			
		
		
					
			
		
		return navItems;
	}
	
	
	
	
	private boolean checkNavDoc(String navDocEntry)
	{
		boolean containsNavElements =false;
		
		Document doc = docParser.parseDocument(navDocEntry);
			
			NodeList n=doc.getElementsByTagName("nav");
			if(n.getLength()>1)
			{
				containsNavElements=true;
				/*
				CheckingReport.addCheckMessage(navDocEntry,
						String.valueOf(-1),
						String.valueOf(-1),
						"Navigation document contains "+n.getLength()+" NAV elements", 
						"CTC-015");
				*/		
			}
			
		
		return containsNavElements;
	}

	
	
}
