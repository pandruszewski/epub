package com.adobe.epubcheck.util;

public class CheckMessage {

	public Severity severity;
	private String messageUI;
	private String messageShortDescription;
	private String messageLongCntxDescription;
	private String sourceErroneousFileName;
	private String sourceErroneousLineNumber;
	private String sourceErroneousColumnNumber;
	private String sourceLineText;
	private String messageMainCategory;
	private String messageSubCategory;

	public CheckMessage(String file, String lineNumber, String columnNumber,
			String messageLongCntxDescription, String... messageUI) {

		this.messageUI = messageUI[0];
		this.sourceErroneousFileName = file;
		this.sourceErroneousLineNumber = lineNumber;
		this.sourceErroneousColumnNumber = columnNumber;
		this.messageLongCntxDescription = messageLongCntxDescription;

		if (messageUI.length > 0) {
			AdditionalInfo additionalInfo = Dictionary.dictionary
					.getAdditionalInfo(this.messageUI);
			if (additionalInfo != null) {
				this.messageShortDescription = additionalInfo
						.getMessageShortDescription();
				this.severity = additionalInfo.getSeverity();
				this.messageMainCategory = additionalInfo
						.getMessageMainCategory();
				this.messageSubCategory = additionalInfo
						.getMessageSubCategory();
			}
		}
	}

	public String toString() {
		String text = "";
		text = "ID: " + messageUI + System.getProperty("line.separator")
				+ "SEVERITY: " + (severity!= null ? severity : "-UNDEFINED-")
				+ System.getProperty("line.separator")
				+ System.getProperty("line.separator")
				+ "ERRONEOUS FILE NAME: " + sourceErroneousFileName
				+ System.getProperty("line.separator");
		if (sourceErroneousLineNumber.length() > 0
				&& sourceErroneousColumnNumber.length() > 0)
			text += "LINE NUMBER: " + sourceErroneousLineNumber
					+ System.getProperty("line.separator");
		text += "COLUMN NUMBER: " + sourceErroneousColumnNumber
				+ System.getProperty("line.separator");
		text += "DESCRIPTION (short): " + messageShortDescription
				+ System.getProperty("line.separator");
		text += "DESCRIPTION (long): " + messageLongCntxDescription
				+ System.getProperty("line.separator");
		text += "=========================================================================================================================="
				+ System.getProperty("line.separator");

		return text;
	}

	public String toString(boolean printAll, boolean printSome) {
		String text = "";

		if (printSome) {
			text += System.getProperty("line.separator") + "ID: " + messageUI
					+ System.getProperty("line.separator") + "Description: "
					+ sourceLineText;
			text += System.getProperty("line.separator")
					+ "=========================================================================================================================="
					+ System.getProperty("line.separator");

		} else if (printAll) {
			text = System.getProperty("line.separator") + "ID: " + messageUI
					+ System.getProperty("line.separator")
					+ System.getProperty("line.separator") + "Error File: "
					+ sourceErroneousFileName
					+ System.getProperty("line.separator");
			if (sourceErroneousLineNumber.length() > 0
					&& sourceErroneousColumnNumber.length() > 0)
				text += "Number of line: " + sourceErroneousLineNumber
						+ System.getProperty("line.separator")
						+ "Number of column: " + sourceErroneousColumnNumber
						+ System.getProperty("line.separator");
			text += "Description: " + messageLongCntxDescription;
			text += System.getProperty("line.separator")
					+ "=========================================================================================================================="
					+ System.getProperty("line.separator");
		}

		return text;
	}

	public String toStringFilter(boolean error, boolean exception,
			boolean warning, boolean info, boolean printAll, boolean printSome) {
		String text = "";
		if ((error || exception || warning || info) && severity != null) {
			switch (severity) {
			case ERROR:
				if (error)
					text += "TYPE: " + (severity!= null ? severity : "-UNDEFINED-") + toString(printAll, printSome);
				break;
			case FATAL:
				if (exception)
					text += "TYPE: " + (severity!= null ? severity : "-UNDEFINED-") + toString(printAll, printSome);
				break;
			case WARNING:
				if (warning)
					text += "TYPE: " + (severity!= null ? severity : "-UNDEFINED-") + toString(printAll, printSome);
				break;
			case INFO:
				if (info)
					text += "TYPE: " + (severity!= null ? severity : "-UNDEFINED-") + toString(printAll, printSome);
			}
		} else {
			text += "TYPE: " + (severity!= null ? severity : "-UNDEFINED-") + toString(printAll, printSome);
		}
		return text;
	}

}
