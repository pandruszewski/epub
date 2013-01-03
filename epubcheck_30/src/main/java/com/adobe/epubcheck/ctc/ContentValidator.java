package com.adobe.epubcheck.ctc;

import java.util.zip.ZipFile;

import com.adobe.epubcheck.api.Report;
import com.adobe.epubcheck.ctc.epubpackage.EpubPackage;
import com.adobe.epubcheck.opf.DocumentValidator;


public interface ContentValidator {
	
public enum ValidationType {
	TEXT,CONTENT,SPINE,NCX,NAV,SCRIPT,SPAN,STYLE,HTML5,HTML4,METADATA_V3,METADATA_V2,LANG,CSS_SEARCH,LINK,RENDITION,CFI,INLINESCRIPT,HTML_STRUCTURE,MULTIPLE_CSS,EPUB3_STRUCTURE
}
	

		public DocumentValidator newInstance(Report report, ValidationType vt, EpubPackage epack);
			
}
