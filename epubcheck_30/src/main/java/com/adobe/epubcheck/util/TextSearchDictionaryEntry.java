package com.adobe.epubcheck.util;

public class TextSearchDictionaryEntry
{
	
	String searchedValue;
	String regexExp;
	String errorCode;
	
	
	public TextSearchDictionaryEntry(String searchedValue, String regex,String errorCode)
	{
		this.searchedValue=searchedValue;
		this.regexExp=regex;
		this.errorCode=errorCode;
	}
	
	public String getSearchedValue() {
		return searchedValue;
	}
	public void setSearchedValue(String searchedValue) {
		this.searchedValue = searchedValue;
	}
	public String getRegexExp() {
		return regexExp;
	}
	public void setRegexExp(String regexExp) {
		this.regexExp = regexExp;
	}
	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	
	
}