package com.adobe.epubcheck.util;

public class AdditionalInfo {

	private Severity severity;
	private String messageMainCategory;
	private String messageSubCategory;
	private String messageShortDescription;

	public AdditionalInfo(Severity severity, 
			String messageMainCategory, 
			String messageSubCategory,
			String messageShortDescription) {

		this.severity = severity;
		this.messageMainCategory = messageMainCategory;
		this.messageSubCategory = messageSubCategory;
		this.messageShortDescription = messageShortDescription;
	}

	public Severity getSeverity() {
		if(this.severity != null)
			return this.severity;
		return  null;
	}

	public String getMessageMainCategory() {
		if(messageMainCategory!= null)
			return this.messageMainCategory;
		return "-UNDEFINED-";
	}

	public String getMessageSubCategory() {
		if(messageSubCategory!= null)
			return this.messageSubCategory;
		return "-UNDEFINED-";
	}
	
	public String getMessageShortDescription() {
		if(messageShortDescription!= null)
			return this.messageShortDescription;
		return "-UNDEFINED-";
	}
}
