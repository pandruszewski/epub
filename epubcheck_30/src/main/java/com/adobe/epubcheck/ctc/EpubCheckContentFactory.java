package com.adobe.epubcheck.ctc;




import com.adobe.epubcheck.api.Report;
import com.adobe.epubcheck.ctc.epubpackage.EpubPackage;
import com.adobe.epubcheck.opf.DocumentValidator;
import com.adobe.epubcheck.opf.DocumentValidatorFactory;
import com.adobe.epubcheck.util.EPUBVersion;
import com.adobe.epubcheck.util.GenericResourceProvider;

public class EpubCheckContentFactory implements ContentValidator
{

	static private EpubCheckContentFactory instance = new EpubCheckContentFactory();

	static public EpubCheckContentFactory getInstance() {
		return instance;
	}

	
	
	


	@Override
	public DocumentValidator newInstance(Report report, ValidationType vt,
			EpubPackage epack) {
		
		if(vt.equals(ValidationType.METADATA_V3)==true)
			return new EpubMetaDataV3Check(epack,report);
		if(vt.equals(ValidationType.METADATA_V2)==true)
			return new EpubMetaDataV2Check(epack,report);
		if(vt.equals(ValidationType.TEXT)==true)
			return new EpubTextContentCheck(report, epack);
		else if(vt.equals(ValidationType.NAV)==true)
			return new EpubNavCheck(epack, report);
		else if(vt.equals(ValidationType.NCX)==true)
			return new EpubNCXCheck(epack, report);
		else if(vt.equals(ValidationType.SPINE)==true)
			return new EpubSpineCheck(epack, report);
		else if(vt.equals(ValidationType.SCRIPT)==true)
			return new EpubScriptCheck(epack, report);
		else if(vt.equals(ValidationType.SPAN)==true)
			return new EpubSpanCheck(epack, report);
		else if(vt.equals(ValidationType.STYLE)==true)
			return new EpubStyleCheck(epack, report);
		else if(vt.equals(ValidationType.HTML5)==true)
			return new EpubHTML5TagsCheck(epack, report);
		else if(vt.equals(ValidationType.HTML4)==true)
			return new EpubHTML4TagsCheck(epack, report);
		else if(vt.equals(ValidationType.LANG)==true)
			return new EpubLangCheck(epack, report);
		else if(vt.equals(ValidationType.CSS_SEARCH)==true)
			return new EpubCSSCheck(epack, report);
		else if(vt.equals(ValidationType.LINK)==true)
			return new EpubExtLinksCheck(epack, report);
		else if(vt.equals(ValidationType.RENDITION)==true)
			return new EpubRenditionCheck(epack, report);
		else if(vt.equals(ValidationType.CFI)==true)
			return new EpubCfiCheck(epack, report);
		else if(vt.equals(ValidationType.INLINESCRIPT)==true)
			return new EpubInlineScriptCheck(epack, report);
		else if(vt.equals(ValidationType.HTML_STRUCTURE)==true)
			return new EpubHTML5StructureCheck(epack, report);
		else if(vt.equals(ValidationType.MULTIPLE_CSS)==true)
			return new EpubStyleSheetsCheck(epack, report);
		else if(vt.equals(ValidationType.EPUB3_STRUCTURE)==true)
			return new Epub3StructureCheck(epack, report);
		else if(vt.equals(ValidationType.TOC)==true)
			return new EpubTocCheck(epack, report);
		else
			return null;
		
	}







	

}
