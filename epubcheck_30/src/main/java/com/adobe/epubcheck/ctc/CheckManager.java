package com.adobe.epubcheck.ctc;

import java.util.zip.ZipFile;

import com.adobe.epubcheck.api.Report;
import com.adobe.epubcheck.ctc.ContentValidator.ValidationType;
import com.adobe.epubcheck.ctc.epubpackage.EpubPackage;
import com.adobe.epubcheck.util.EPUBVersion;

public class CheckManager 
{
	ZipFile zip;
	EpubPackage epack;
	EpubCheckContentFactory factory;
	Report report;
	
	
	public CheckManager(ZipFile zip, Report report)
	{
		this.zip=zip;
		this.report=report;
		PackageReader pr= new PackageReader(zip, report);
		epack=pr.readPackageData();
		factory= EpubCheckContentFactory.getInstance();
		
	}
	
	public void checkPackage()
	{
		System.out.println("Validating against EPUB version "+epack.getVersion()+" - custom validation");
		
		if(epack.getVersion().equals(EPUBVersion.VERSION_3))
		{	
			factory.newInstance(report, ValidationType.MULTIPLE_CSS, epack).validate();
			
		}
		if(epack.getVersion().equals(EPUBVersion.VERSION_2))
		{	
			factory.newInstance(report, ValidationType.EPUB3_STRUCTURE, epack).validate();
		}
		factory.newInstance(report, ValidationType.HTML_STRUCTURE, epack).validate();
		
		factory.newInstance(report, ValidationType.LINK, epack).validate();
		
		factory.newInstance(report, ValidationType.INLINESCRIPT, epack).validate();
		
		factory.newInstance(report, ValidationType.CSS_SEARCH, epack).validate();
		
		if(epack.getVersion().equals(EPUBVersion.VERSION_3))
		{	
			factory.newInstance(report, ValidationType.RENDITION, epack).validate();
		}
		if(epack.getVersion().equals(EPUBVersion.VERSION_3))
		{	
			factory.newInstance(report, ValidationType.CFI, epack).validate();
		}
	/*	if(epack.getVersion().equals(EPUBVersion.VERSION_3))
		{
			factory.newInstance(report, ValidationType.NAV, epack).validate();
		}*/
		if(epack.getVersion().equals(EPUBVersion.VERSION_3))
		{
			factory.newInstance(report, ValidationType.METADATA_V3, epack).validate();
		}
		if(epack.getVersion().equals(EPUBVersion.VERSION_2))
		{
			factory.newInstance(report, ValidationType.METADATA_V2, epack).validate();
		}
		
		
	
		if(epack.getVersion().equals(EPUBVersion.VERSION_3))
		{	
			factory.newInstance(report, ValidationType.NCX, epack).validate();
		}
	/*
		if(epack.getVersion().equals(EPUBVersion.VERSION_2))
		{
			
			factory.newInstance(report, ValidationType.HTML5, epack).validate();
		}
	*/	
		/*
		if(epack.getVersion().equals(EPUBVersion.VERSION_3))
		{
			
			factory.newInstance(report, ValidationType.HTML4, epack).validate();
		}
		*/
		factory.newInstance(report, ValidationType.LANG, epack).validate();
		
		factory.newInstance(report, ValidationType.SPINE, epack).validate();
		
		factory.newInstance(report, ValidationType.TEXT, epack).validate();
		
		factory.newInstance(report, ValidationType.SCRIPT, epack).validate();
		
		factory.newInstance(report, ValidationType.SPAN, epack).validate();
		
		factory.newInstance(report, ValidationType.STYLE, epack).validate();
		
	}
	
}
