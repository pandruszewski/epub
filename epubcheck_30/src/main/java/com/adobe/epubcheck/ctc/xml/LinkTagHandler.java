package com.adobe.epubcheck.ctc.xml;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.thaiopensource.util.Localizer;

public class LinkTagHandler extends DefaultHandler
{

	private Vector<LinkMarkup> linkTags = new Vector<LinkMarkup>();
	public Vector<LinkMarkup> getLinkMarkupsValues() {
		return linkTags;
	}


	public void setLinkMarkupsValues(Vector<LinkMarkup> styleHrefValues) {
		this.linkTags = styleHrefValues;
	}


	private Locator locator;
		
	
	public void setDocumentLocator(Locator locator) {
        this.locator = locator;
    }
	
	int linkTagsCount=0;
	int alternateStyleSheetsCount=0;
	int styleSheetsCount=0;
	

	public int getLinksTagsCount() 
	{
		return linkTagsCount;
	}
	
	public int getAlternateStyleSheetsCount() 
	{
		return alternateStyleSheetsCount;
	}
	public int getStyleSheetsCount() 
	{
		return styleSheetsCount;
	}

	
	
	
	
	public void checkForMultipleStyleSheets()
	{
		
		
		alternateStyleSheetsCount=0;
		for(int i=0;i<linkTags.size();i++)
		{
			if(linkTags.get(i).relAttribute.compareToIgnoreCase("stylesheet")==0)
			{
				styleSheetsCount++;
			}
			if(linkTags.get(i).relAttribute.compareToIgnoreCase("alternate stylesheet")==0)
			{
				alternateStyleSheetsCount++;
			}
		}
		linkTagsCount=styleSheetsCount+alternateStyleSheetsCount;
	}
	
	public void startElement(String uri, String localName,String qName, 
			Attributes attributes) throws SAXException {

		if(qName.compareToIgnoreCase("link")==0)
		{	
			LinkMarkup la=new LinkMarkup();
			for(int i=0;i<attributes.getLength();i++)
			{
				String attrName=attributes.getQName(i);
				String attrValue=attributes.getValue(i);
				if(attrName.compareToIgnoreCase("rel")==0)
				{
					la.setRelAttribute(attrValue);
					
				}
				if(attrName.compareToIgnoreCase("type")==0)
				{
					la.setTypeAttribute(attrValue);
					
				}
				if(attrName.compareToIgnoreCase("href")==0)
				{
					la.setHrefAttribute(attrValue);
					
				}
				if(attrName.compareToIgnoreCase("class")==0)
				{
					la.setClassAttribute(attrValue);
					
				}
				if(attrName.compareToIgnoreCase("title")==0)
				{
					la.setClassAttribute(attrValue);
					
				}
			}	
			linkTags.add(la);
		
		}
	}

	
	
class LinkMarkup
{
	String relAttribute="";
	String typeAttribute="";
	String hrefAttribute="";
	String classAttribute="";
	String titleAttribute="";
	
	public String getRelAttribute() {
		return relAttribute;
	}
	public void setRelAttribute(String relAttribute) {
		this.relAttribute = relAttribute;
	}
	public String getTypeAttribute() {
		return typeAttribute;
	}
	public void setTypeAttribute(String typeAttribute) {
		this.typeAttribute = typeAttribute;
	}
	public String getHrefAttribute() {
		return hrefAttribute;
	}
	public void setHrefAttribute(String hrefAttribute) {
		this.hrefAttribute = hrefAttribute;
	}
	public String getClassAttribute() {
		return classAttribute;
	}
	public void setClassAttribute(String classAttribute) {
		this.classAttribute = classAttribute;
	}
	public String getTitleAttribute() {
		return titleAttribute;
	}
	public void setTitleAttribute(String titleAttribute) {
		this.titleAttribute = titleAttribute;
	}
	
	
}

}

