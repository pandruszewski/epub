package com.adobe.epubcheck.util;

import java.util.Vector;

public class SearchDictionary 
{

	public enum DictionaryType {
	    SEARCH, VALID_TEXT_MEDIA_TYPES,CSS_FILES,CSS_VALUES,LINK_VALUES
	}
	
	
	public SearchDictionary(DictionaryType dt)
	{
		if(dt.equals(DictionaryType.SEARCH))
		{
			buildSearchDictionary();
		}
		if(dt.equals(DictionaryType.VALID_TEXT_MEDIA_TYPES))
		{
			buildValidTypesDictionary();
		}
		if(dt.equals(DictionaryType.CSS_VALUES))
		{
			buildCssSearchDictionary();
		}
		if(dt.equals(DictionaryType.CSS_FILES))
		{
			buildCSSTypesDictionary();
		}
		if(dt.equals(DictionaryType.LINK_VALUES))
		{
			buildLinkSearchDictionary();
		}
	}
	Vector<TextSearchDictionaryEntry> v = new Vector<TextSearchDictionaryEntry>();
/*
	String[] validTypes = new String[] 
	    { "application/xhtml+xml",
			"application/x-dtbncx+xml", "text/css" };
	
	*/
	
	public void buildCSSTypesDictionary()
	{
		String description;
		String value;
		String messageCode;
		TextSearchDictionaryEntry de;
		
		
		//search eval() expression
		description="text/css";
		value="text/css";
		messageCode="";
		de=new TextSearchDictionaryEntry(description,value,messageCode);
		v.add(de);
		
		
			
		
		
	}
	
	
	public void buildCssSearchDictionary()
	{
		String description;
		String value;
		String messageCode;
		TextSearchDictionaryEntry de;
		
		
		//search eval() expression
		description="rotateX()";
		value="rotateX()";
		messageCode="CTC-031";
		de=new TextSearchDictionaryEntry(description,value,messageCode);
		v.add(de);
		
		description="rotateY()";
		value="rotateY()";
		messageCode="CTC-032";
		de=new TextSearchDictionaryEntry(description,value,messageCode);
		v.add(de);
		
		
		description="column-count";
		value="column-count";
		messageCode="CTC-033";
		de=new TextSearchDictionaryEntry(description,value,messageCode);
		v.add(de);
		
		description="column-gap";
		value="column-gap";
		messageCode="CTC-034";
		de=new TextSearchDictionaryEntry(description,value,messageCode);
		v.add(de);
		
		description="column-rule";
		value="column-rule";
		messageCode="CTC-035";
		de=new TextSearchDictionaryEntry(description,value,messageCode);
		v.add(de);
		
		description="keyframes";
		value="keyframes";
		messageCode="CTC-036";
		de=new TextSearchDictionaryEntry(description,value,messageCode);
		v.add(de);
		
		description="transition";
		value="transition";
		messageCode="CTC-037";
		de=new TextSearchDictionaryEntry(description,value,messageCode);
		v.add(de);
		
	}
	
	public void buildValidTypesDictionary()
	{
		String description;
		String value;
		String messageCode;
		TextSearchDictionaryEntry de;
		
		
		//search eval() expression
		description="application/xhtml+xml";
		value="application/xhtml+xml";
		messageCode="";
		de=new TextSearchDictionaryEntry(description,value,messageCode);
		v.add(de);
		
		
	}
	
	
	public void buildSearchDictionary()
	{
		String description;
		String value;
		String messageCode;
		TextSearchDictionaryEntry de;
		
		/*
		description="<span>";
		value="<*[Ss][Pp][Aa][Nn]*>";
		messageCode="CTC-005";
		de=new TextSearchDictionaryEntry(description,value,messageCode);
		v.add(de);
		*/
		
		//search eval() expression
		description="eval()";
		value="[Ee][Vv][Aa][Ll]*\\(";
		messageCode="CTC-006";
		de=new TextSearchDictionaryEntry(description,value,messageCode);
		v.add(de);
		
		description="XMLHttpRequest";
		value="XMLHttpRequest";
		messageCode="CTC-005";
		de=new TextSearchDictionaryEntry(description,value,messageCode);
		v.add(de);
		
		description=".js";
		value="\\.[Jj][Ss]\\s";
		messageCode="CTC-012";
		de=new TextSearchDictionaryEntry(description,value,messageCode);
		v.add(de);
		
	/*	
		description="Http:";
		value="[Hh][Tt][Tt][Pp]*\\:";
		messageCode="CTC-007";
		de=new TextSearchDictionaryEntry(description,value,messageCode);
		v.add(de);
		
		description="Ftp:";
		value="[Ff][Tt][Pp]*\\:";
		messageCode="CTC-008";
		de=new TextSearchDictionaryEntry(description,value,messageCode);
		v.add(de);
		
		description="www:";
		value="[Ww][Ww][Ww]*\\.";
		messageCode="CTC-009";
		de=new TextSearchDictionaryEntry(description,value,messageCode);
		v.add(de);
	*/	
		
		
	}
	public void buildLinkSearchDictionary()
	{
		String description;
		String value;
		String messageCode;
		TextSearchDictionaryEntry de;
		
		
		description="Http:";
		value="[Hh][Tt][Tt][Pp]*\\:";
		messageCode="CTC-007";
		de=new TextSearchDictionaryEntry(description,value,messageCode);
		v.add(de);
		
		description="Ftp:";
		value="[Ff][Tt][Pp]*\\:";
		messageCode="CTC-008";
		de=new TextSearchDictionaryEntry(description,value,messageCode);
		v.add(de);
		
		description="www:";
		value="[Ww][Ww][Ww]*\\.";
		messageCode="CTC-009";
		de=new TextSearchDictionaryEntry(description,value,messageCode);
		v.add(de);
		
		
		
		
	}
	
	public Vector<TextSearchDictionaryEntry> getDictEntries()
	{
		return v;
	}

	
}
