package com.adobe.epubcheck.ctc;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.adobe.epubcheck.api.Report;
import com.adobe.epubcheck.ctc.epubpackage.EpubPackage;
import com.adobe.epubcheck.ctc.epubpackage.MetadataElement;
import com.adobe.epubcheck.opf.DocumentValidator;
import com.adobe.epubcheck.util.CheckingReport;
import com.adobe.epubcheck.util.EPUBVersion;

public class EpubRenditionCheck implements DocumentValidator{

	
	Document doc;
	String pathRootFile;
	EpubPackage epack;
	
	public EpubRenditionCheck(Document doc,String pathRootFile)
	{
		this.doc=doc;
		this.pathRootFile=pathRootFile;
	}
	
	public EpubRenditionCheck(EpubPackage epack, Report report)
	{
		this.epack=epack;
		this.doc=epack.getPackDoc();
		this.pathRootFile=epack.getPackageMainFile();
	}
	
	@Override
	public boolean validate() {
		
		
		
		boolean result=isRenditionDefined(doc,epack.getPackageMainFile());
		
		
		
		
		return result;
	}
	
	
	private boolean isRenditionDefined(Document doc,String pathRootFile)
	{
		
		boolean result = false;
		NodeList nList = doc.getElementsByTagName("metadata");
		if(nList.getLength()>0)
		{
			Node metadata=nList.item(0);
			NodeList metaNodes = metadata.getChildNodes();
			
			for(int i=0;i<metaNodes.getLength();i++)
			{
				
				
				if(metaNodes.item(i).getNodeName().compareToIgnoreCase("meta")==0)
				{
					Node n = metaNodes.item(i);
					String nodeValue=null;
					 if (n.hasChildNodes())
						 nodeValue=n.getFirstChild().getNodeValue();
				        else 
				        	nodeValue=n.getNodeValue();
					
					NamedNodeMap  attrs = n.getAttributes();
					Node p=attrs.getNamedItem("property");
					if(p!=null)
					{	
					
							if(p.getNodeValue().contains("rendition:layout"))
							{
								CheckingReport.addCheckMessage(pathRootFile,
										String.valueOf(attrs.getNamedItem("elementLineNumber").getNodeValue()),
										String.valueOf(attrs.getNamedItem("elementColumnNumber").getNodeValue()),
										"Rendition:layout set to "+nodeValue+" in "+pathRootFile+" document at line "+attrs.getNamedItem("elementLineNumber").getNodeValue()+" and column "+attrs.getNamedItem("elementColumnNumber").getNodeValue() , 
										"CTC-038");
								result = true;
							}
							if(p.getNodeValue().contains("rendition:orientation"))
							{
								CheckingReport.addCheckMessage(pathRootFile,
										String.valueOf(attrs.getNamedItem("elementLineNumber").getNodeValue()),
										String.valueOf(attrs.getNamedItem("elementColumnNumber").getNodeValue()),
										"Rendition:orientation set to "+nodeValue+" in "+pathRootFile+" document at line "+attrs.getNamedItem("elementLineNumber").getNodeValue()+" and column "+attrs.getNamedItem("elementColumnNumber").getNodeValue() , 
										"CTC-039");
								result = true;
							}
							if(p.getNodeValue().contains("rendition:page-spread"))
							{
								CheckingReport.addCheckMessage(pathRootFile,
										String.valueOf(attrs.getNamedItem("elementLineNumber").getNodeValue()),
										String.valueOf(attrs.getNamedItem("elementColumnNumber").getNodeValue()),
										"Rendition:page-spread set to "+nodeValue+" in "+pathRootFile+" document at line "+attrs.getNamedItem("elementLineNumber").getNodeValue()+" and column "+attrs.getNamedItem("elementColumnNumber").getNodeValue() , 
										"CTC-040");
								result = true;
							}
					}	
				}
			}		
			
		}
		
		
		
		NodeList mList = doc.getElementsByTagName("manifest");
		if(mList.getLength()>0)
		{
			Node manifest=mList.item(0);
			NodeList itemNodes = manifest.getChildNodes();
			
			for(int i=0;i<itemNodes.getLength();i++)
			{
				
				
				if(itemNodes.item(i).getNodeName().compareToIgnoreCase("item")==0)
				{
					Node n = itemNodes.item(i);
				
					String nodeValue=null;
					 if (n.hasChildNodes())
						 nodeValue=n.getFirstChild().getNodeValue();
				        else 
				        	nodeValue=n.getNodeValue();
					
					
					NamedNodeMap  attrs = n.getAttributes();
					Node p=attrs.getNamedItem("properties");
					if(p!=null)
					{	//System.out.println("	Dodaje attrybut "+attrs.item(a).getNodeName()+" z wartoscia "+attrs.item(a).getNodeValue());
					
						if (p.hasChildNodes())
							 nodeValue=p.getFirstChild().getNodeValue();
					        else 
					        	nodeValue=p.getNodeValue();
						
						
							if(p.getNodeValue().contains("rendition:layout"))
							{
								CheckingReport.addCheckMessage(pathRootFile,
										String.valueOf(attrs.getNamedItem("elementLineNumber").getNodeValue()),
										String.valueOf(attrs.getNamedItem("elementColumnNumber").getNodeValue()),
										"Rendition:layout set to "+nodeValue+" in "+pathRootFile+" document at line "+attrs.getNamedItem("elementLineNumber").getNodeValue()+" and column "+attrs.getNamedItem("elementColumnNumber").getNodeValue() , 
										"CTC-038");
								result = true;
							}
							if(p.getNodeValue().contains("rendition:orientation"))
							{
								CheckingReport.addCheckMessage(pathRootFile,
										String.valueOf(attrs.getNamedItem("elementLineNumber").getNodeValue()),
										String.valueOf(attrs.getNamedItem("elementColumnNumber").getNodeValue()),
										"Rendition:orientation set to "+nodeValue+" in "+pathRootFile+" document at line "+attrs.getNamedItem("elementLineNumber").getNodeValue()+" and column "+attrs.getNamedItem("elementColumnNumber").getNodeValue() , 
										"CTC-039");
								result = true;
							}
							if(p.getNodeValue().contains("rendition:page-spread"))
							{
								CheckingReport.addCheckMessage(pathRootFile,
										String.valueOf(attrs.getNamedItem("elementLineNumber").getNodeValue()),
										String.valueOf(attrs.getNamedItem("elementColumnNumber").getNodeValue()),
										"Rendition:page-spread-* set to "+nodeValue+" in "+pathRootFile+" document at line "+attrs.getNamedItem("elementLineNumber").getNodeValue()+" and column "+attrs.getNamedItem("elementColumnNumber").getNodeValue() , 
										"CTC-040");
								result = true;
							}
					}	
				}
			}		
			
		}
	
		
		
		
		int stop=0;
		return result;
	}
	
	
	
	
	
	
	
}
