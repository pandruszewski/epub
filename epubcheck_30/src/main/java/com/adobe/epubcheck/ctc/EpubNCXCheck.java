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

public class EpubNCXCheck implements DocumentValidator{

	
	Document doc;
	String pathRootFile;
	EpubPackage epack;
	
	public EpubNCXCheck(Document doc,String pathRootFile)
	{
		this.doc=doc;
		this.pathRootFile=pathRootFile;
	}
	
	public EpubNCXCheck(EpubPackage epack, Report report)
	{
		this.epack=epack;
		this.doc=epack.getPackDoc();
		this.pathRootFile=epack.getPackageMainFile();
	}
	
	@Override
	public boolean validate() {
		
		
		
		boolean result=isNCXDefined(doc,pathRootFile);
		
		return result;
	}
	
	private boolean isNCXDefined(Document doc,String pathRootFile)
	{
		boolean isNCXdefined=false;
		NodeList spineList = doc.getElementsByTagName("spine");
		if(spineList.getLength()>0)
		{
			for(int i=0; i<spineList.getLength();i++)
			{
				NamedNodeMap attrs=spineList.item(i).getAttributes();
				Node n=attrs.getNamedItem("toc");
				if(n!=null)
				{
					String tocID=n.getNodeValue();
					NodeList manifestList = doc.getElementsByTagName("manifest");
					for(int m=0;m<manifestList.getLength();m++)
					{
						
						
						Node manifestNode=manifestList.item(m);
						NodeList itemNodes=manifestNode.getChildNodes();
						
						for(int it=0;it<itemNodes.getLength();it++)
						{
							NamedNodeMap itemNodeAttributes=itemNodes.item(it).getAttributes();
							if(itemNodeAttributes!=null)
							{
								String manifestNodeID=itemNodeAttributes.getNamedItem("id").getNodeValue();
								if(manifestNodeID!=null && manifestNodeID.compareToIgnoreCase(tocID)==0)
								{
									if(itemNodeAttributes.getNamedItem("href").getNodeValue() !=null);
									isNCXdefined=true;
									/*
									CheckingReport.addCheckMessage(pathRootFile,
											String.valueOf(-1),
											String.valueOf(-1),
											"Found NCX definition. NCX is defined in document "+itemNodeAttributes.getNamedItem("href").getNodeValue(), 
											"CTC-010");
										*/
								}
							}
						}	
					}
					
				}
				else
				{
					/*
					CheckingReport.addCheckMessage(pathRootFile,
							String.valueOf(-1),
							String.valueOf(-1),
							"TOC attribute at spine is not defined. Can't find NCX document", 
							"CTC-010");
					*/		
					//System.out.println("TOC attribute at spine is not defined "+doc.getDocumentURI());
				}
			}
		}
		if(isNCXdefined == false)
		{
			CheckingReport.addCheckMessage(pathRootFile,
					String.valueOf(-1),
					String.valueOf(-1),
					".NCX file not included for older devices", 
					"CTC-010");
			//System.out.println("TOC attribute at spine is not defined "+doc.getDocumentURI());
			
		}
		return isNCXdefined;
	}

	
	
	
}
