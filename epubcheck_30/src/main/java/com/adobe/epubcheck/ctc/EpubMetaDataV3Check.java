package com.adobe.epubcheck.ctc;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.adobe.epubcheck.api.Report;
import com.adobe.epubcheck.ctc.epubpackage.EpubPackage;
import com.adobe.epubcheck.opf.DocumentValidator;
import com.adobe.epubcheck.util.CheckingReport;
import com.adobe.epubcheck.util.EPUBVersion;

public class EpubMetaDataV3Check implements DocumentValidator{

	
	Document doc;
	String pathRootFile;
	EpubPackage epack;
	
	public EpubMetaDataV3Check(Document doc,String pathRootFile)
	{
		this.doc=doc;
		this.pathRootFile=pathRootFile;
	}
	
	public EpubMetaDataV3Check(EpubPackage epack, Report report)
	{
		this.epack=epack;
		this.doc=epack.getPackDoc();
		this.pathRootFile=epack.getPackageMainFile();
	}
	
	@Override
	public boolean validate() {
		
		
		
		boolean result=isMetaDataValid(doc,pathRootFile);
		
		return result;
	}
	
	private boolean isMetaDataValid(Document doc,String pathRootFile)
	{
		boolean isMetadataValid=false;
		NodeList metadataList = doc.getElementsByTagName("metadata");
		if(metadataList.getLength()==1)
		{
			
			boolean isDefiniedIdentifier=false;
			boolean isDefiniedTitle=false;
			boolean isDefiniedLanguage=false;
			boolean isDefiniedMeta=false;
			Node metadataNode = metadataList.item(0);
			NodeList childList = metadataNode.getChildNodes();
			for(int i=0;i<childList.getLength();i++)
			{
				if(childList.item(i).getNodeName().compareToIgnoreCase("dc:identifier")==0)
				{
					//System.out.println("!!!!!!!!!DC IDENTIFIER");
					NamedNodeMap a=childList.item(i).getAttributes();
					isDefiniedIdentifier=true;
					
				}
				if(childList.item(i).getNodeName().compareToIgnoreCase("dc:title")==0)
				{
					//System.out.println("!!!!!!!!!DC TITLE");
					isDefiniedTitle=true;
				}
				if(childList.item(i).getNodeName().compareToIgnoreCase("dc:language")==0)
				{
					//System.out.println("!!!!!!!!!DC LANGUAGE");
					NamedNodeMap a=childList.item(i).getAttributes();
					isDefiniedLanguage=true;
					
				}
				if(childList.item(i).getNodeName().compareToIgnoreCase("meta")==0)
				{
					//System.out.println("!!!!!!!!!DC META");
					NamedNodeMap a=childList.item(i).getAttributes();
					if(a.getNamedItem("property")!=null)
					{
						isDefiniedMeta=true;
					}
				}
			}
			if(!(isDefiniedIdentifier&&isDefiniedTitle&&isDefiniedLanguage&&isDefiniedMeta))
			{
				CheckingReport.addCheckMessage(pathRootFile,
						String.valueOf(-1),
						String.valueOf(-1),
						"Not minimal metadata set declared", 
						"CTC-025");
				
			}
		}
		else
		{
			isMetadataValid = false;
			CheckingReport.addCheckMessage(pathRootFile,
					String.valueOf(-1),
					String.valueOf(-1),
					"Missing or more then one metadata definition tag", 
					"CTC-024");
			
			
		}
		return isMetadataValid;
	}

	
	
	
}
