package com.adobe.epubcheck.ctc;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.adobe.epubcheck.api.Report;
import com.adobe.epubcheck.ctc.epubpackage.EpubPackage;
import com.adobe.epubcheck.opf.DocumentValidator;
import com.adobe.epubcheck.util.CheckingReport;

public class EpubSpineCheck implements DocumentValidator{

	Document doc;
	String pathRootFile;
	
	public EpubSpineCheck(EpubPackage epubPack, Report report)
	{
		this.doc=epubPack.getPackDoc();
		pathRootFile=epubPack.getPackageMainFile();
	}
	@Override
	public boolean validate() {
		
		 boolean resExists=isSpineDefined(doc,pathRootFile);
		 boolean resElements=countSpineElements(doc,pathRootFile);
		 
		 if(resExists == false ||  resElements==false)
			 return false;
		 else
			 return true;
	}
	
	
	
	private boolean isSpineDefined(Document doc,String pathRootFile)
	{
		NodeList spine = doc.getElementsByTagName("spine");
		if(spine.getLength()<1)
		{
			CheckingReport.addCheckMessage(pathRootFile,
					String.valueOf(-1),
					String.valueOf(-1),
					"<spine> element not defined", 
					"CTC-002");
			System.out.println("Spine element not found at package root document "+pathRootFile);
			return false;
		}
		
		return true;
	}
	
	private boolean countSpineElements(Document doc,String pathRootFile)
	{
		NodeList spine = doc.getElementsByTagName("spine");
		if(spine.getLength()>0)
		{
			
			NodeList spineElements= spine.item(0).getChildNodes();
			if(spineElements.getLength()>40)
			{
			CheckingReport.addCheckMessage(pathRootFile,
					String.valueOf(-1),
					String.valueOf(-1),
					"Excessive container usage: spine item content size", 
					"CTC-020");
			
			return false;
			}
		}
		
		return true;
	}
	
	
	
}
