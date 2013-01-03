package com.adobe.epubcheck.ctc;

import java.io.IOException;
import java.io.InputStream;
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
import com.adobe.epubcheck.ctc.xml.Epub3StructureHandler;


import com.adobe.epubcheck.ctc.xml.XMLContentDocParser;
import com.adobe.epubcheck.opf.DocumentValidator;
import com.adobe.epubcheck.util.CheckingReport;
import com.adobe.epubcheck.util.SearchDictionary;
import com.adobe.epubcheck.util.SearchDictionary.DictionaryType;

public class Epub3StructureCheck implements DocumentValidator{

	XmlDocParser docParser;
	ZipFile zip;
	Report report;
	Document  packageMainDocument;
	String pathRootFile;
	EpubPackage epack;
	
	public  Epub3StructureCheck(ZipFile zip, Document  packageMainDocument, Report report, String pathRootFile)
	{
		this.zip=zip;
		this.report=report;
		this.packageMainDocument=packageMainDocument;
		this.pathRootFile=pathRootFile;
		docParser = new XmlDocParser(zip,report);
		
	}
	
	public  Epub3StructureCheck(EpubPackage epack, Report report)
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
		String navDocPath=getNAVDocument(packageMainDocument,pathRootFile);
		
		
		
		SearchDictionary vtsd=new SearchDictionary(DictionaryType.VALID_TEXT_MEDIA_TYPES);
		
		for(int i=0;i<epack.getManifest().itemsLength();i++)
		{
			ManifestItem mi = epack.getManifest().getItem(i);
			if(isValidMediaType(vtsd,mi.getMediaType()))
			{
				try {
					XMLContentDocParser parser = new XMLContentDocParser(epack.getZip(), report);
					Epub3StructureHandler epub3StructureHandler = new Epub3StructureHandler(mi.getHref());
					String fileToParse="";
					
					if(epack.getPackageMainPath()!=null && epack.getPackageMainPath().length()>0)
					{
						fileToParse=epack.getPackageMainPath()+"/"+mi.getHref();
					}
					else
						fileToParse=mi.getHref();
					
					
					
					parser.parseDoc(fileToParse, epub3StructureHandler);
					if(navDocPath!=null || epub3StructureHandler.getSpecificTagsCount()>0)
					{
					
						String messageCode="CTC-052";
						String message="Wrong use of Epub3 constructs";
						CheckingReport.addCheckMessage("EpubPackage",
								String.valueOf(-1),
								String.valueOf(-1),
								message,messageCode);
						
						result=true;
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
	
	private String getNAVDocument(Document doc,String pathRootFile)
	{
		
	
		
					
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
									if(itemNodeAttributes.getNamedItem("href").getNodeValue() !=null)
									{
										String hrefValue=itemNodeAttributes.getNamedItem("href").getNodeValue();
										
										
										return hrefValue;
									}		
								}
							}
						}	
					}
					
			
		
		
			
		
		return null;
	}
	
	
	
	
	
	
	
}
