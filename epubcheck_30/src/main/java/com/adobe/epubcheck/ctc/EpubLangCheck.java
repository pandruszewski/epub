package com.adobe.epubcheck.ctc;

import java.util.zip.ZipFile;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.adobe.epubcheck.api.Report;
import com.adobe.epubcheck.ctc.epubpackage.EpubPackage;
import com.adobe.epubcheck.ctc.epubpackage.ManifestItem;
import com.adobe.epubcheck.ctc.xml.LangAttributeHandler;
import com.adobe.epubcheck.ctc.xml.ScriptTagHandler;
import com.adobe.epubcheck.ctc.xml.StyleTagHandler;
import com.adobe.epubcheck.ctc.xml.XMLContentDocParser;
import com.adobe.epubcheck.opf.DocumentValidator;
import com.adobe.epubcheck.util.CheckingReport;
import com.adobe.epubcheck.util.SearchDictionary;
import com.adobe.epubcheck.util.SearchDictionary.DictionaryType;

public class EpubLangCheck implements DocumentValidator {

	XmlDocParser docParser;
	ZipFile zip;
	Report report;
	Document  packageMainDocument;
	String pathRootFile;
	EpubPackage epack;
	
	public  EpubLangCheck(ZipFile zip, Document  packageMainDocument, Report report, String pathRootFile)
	{
		this.zip=zip;
		this.report=report;
		this.packageMainDocument=packageMainDocument;
		this.pathRootFile=pathRootFile;
		docParser = new XmlDocParser(zip,report);
		
	}
	
	public  EpubLangCheck(EpubPackage epack, Report report)
	{
		this.zip=epack.getZip();
		this.report=report;
		this.packageMainDocument=epack.getPackDoc();
		this.pathRootFile=epack.getPackageMainFile();
		this.epack=epack;
	}
	
	
	@Override
	public boolean validate() 
	{
	
		boolean result=false;
		SearchDictionary vtsd=new SearchDictionary(DictionaryType.VALID_TEXT_MEDIA_TYPES);
		for(int i=0;i<epack.getManifest().itemsLength();i++)
		{
			ManifestItem mi = epack.getManifest().getItem(i);
			if(isValidMediaType(vtsd,mi.getMediaType()))
			{
				try {
					XMLContentDocParser parser = new XMLContentDocParser(epack.getZip(), report);
					LangAttributeHandler sh = new LangAttributeHandler();
					String fileToParse="";
					if(epack.getPackageMainPath()!=null && epack.getPackageMainPath().length()>0)
					{
						fileToParse=epack.getPackageMainPath()+"/"+mi.getHref();
					}
					else
					fileToParse=mi.getHref(); 
					
					parser.parseDoc(fileToParse, sh);
					String langAttribute = sh.getLangAttr();
					String xmlLangAttribute=sh.getXmlLangAttr();
					if(langAttribute!=null && xmlLangAttribute!=null)
					{
						
						
						
						if(xmlLangAttribute.compareToIgnoreCase(langAttribute)!=0)
						{
							CheckingReport.addCheckMessage("EpubLangCheck",
									String.valueOf(-1),
									String.valueOf(-1),
									"Content file "+mi.getHref()+" has different language value in attributes xml:lang and lang","CTC-026");
						
						}
						
						if(!isValidLanguageDefinition(xmlLangAttribute))
						{
							CheckingReport.addCheckMessage("EpubLangCheck",
									String.valueOf(-1),
									String.valueOf(-1),
									"Content file "+mi.getHref()+" has invalid language value at attribute xml:lang","CTC-027");
						
						}
						if(!isValidLanguageDefinition(langAttribute))
						{
							CheckingReport.addCheckMessage("EpubLangCheck",
									String.valueOf(-1),
									String.valueOf(-1),
									"Content file "+mi.getHref()+" has invalid language definition at attribute lang","CTC-028");
						
						}	
							
					}
					else
					{
						if(xmlLangAttribute==null)
						{
							CheckingReport.addCheckMessage("EpubLangCheck",
									String.valueOf(-1),
									String.valueOf(-1),
									"Content file "+mi.getHref()+" doesn't contains xml:lang attribute (language definition)","CTC-029");
						}
						if(langAttribute==null)
						{
							CheckingReport.addCheckMessage("EpubLangCheck",
									String.valueOf(-1),
									String.valueOf(-1),
									"Content file "+mi.getHref()+" doesn't contains lang attribute (language definition)","CTC-030");
						}
					}
					
				} catch (ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return result;
	}
	
	private boolean isValidLanguageDefinition(String language)
	{
		
		for(int i=0;i<langValues.length;i++)
		{
			if(language.compareToIgnoreCase(langValues[i][1])==0)
			{
				return true;
			}
		}
		return false;
	}
	
	private boolean isValidMediaType(SearchDictionary validTypes, String typeToCheck) {
		for (int i = 0; i < validTypes.getDictEntries().size(); i++) {
			if ((validTypes.getDictEntries().get(i).getRegexExp()).compareToIgnoreCase(typeToCheck) == 0) {
				return true;
			}
		}
		return false;
	}
	
	
	String[][] langValues=new String[][]{
			
			{"Abkhazian","ab"},
			{"Afar","aa"},
			{"Afrikaans","af"},
			{"Albanian","sq"},
			{"Amharic","am"},
			{"Arabic","ar"},
			{"Aragonese","an"},
			{"Armenian","hy"},
			{"Assamese","as"},
			{"Aymara","ay"},
			{"Azerbaijani","az"},
			{"Bashkir","ba"},
			{"Basque","eu"},
			{"Bengali (Bangla)","bn"},
			{"Bhutani","dz"},
			{"Bihari","bh"},
			{"Bislama","bi"},
			{"Breton","br"},
			{"Bulgarian","bg"},
			{"Burmese","my"},
			{"Byelorussian (Belarusian)","be"},
			{"Cambodian","km"},
			{"Catalan","ca"},
			{"Cherokee"," "},
			{"Chewa"," "},
			{"Chinese (Simplified)","zh"},
			{"Chinese (Traditional)","zh"},
			{"Corsican","co"},
			{"Croatian","hr"},
			{"Czech","cs"},
			{"Danish","da"},
			{"Divehi"," "},
			{"Dutch","nl"},
			{"Edo"," "},
			{"English","en"},
			{"Esperanto","eo"},
			{"Estonian","et"},
			{"Faeroese","fo"},
			{"Farsi","fa"},
			{"Fiji","fj"},
			{"Finnish","fi"},
			{"Flemish"," "},
			{"French","fr"},
			{"Frisian","fy"},
			{"Fulfulde"," "},
			{"Galician","gl"},
			{"Gaelic (Scottish)","gd"},
			{"Gaelic (Manx)","gv"},
			{"Georgian","ka"},
			{"German","de"},
			{"Greek","el"},
			{"Greenlandic","kl"},
			{"Guarani","gn"},
			{"Gujarati","gu"},
			{"Haitian Creole","ht"},
			{"Hausa","ha"},
			{"Hawaiian"," "},
			{"Hebrew","he, iw"},
			{"Hindi","hi"},
			{"Hungarian","hu"},
			{"Ibibio"," "},
			{"Icelandic","is"},
			{"Ido","io"},
			{"Igbo"," "},
			{"Indonesian","id, in"},
			{"Interlingua","ia"},
			{"Interlingue","ie"},
			{"Inuktitut","iu"},
			{"Inupiak","ik"},
			{"Irish","ga"},
			{"Italian","it"},
			{"Japanese","ja"},
			{"Javanese","jv"},
			{"Kannada","kn"},
			{"Kanuri"," "},
			{"Kashmiri","ks"},
			{"Kazakh","kk"},
			{"Kinyarwanda (Ruanda)","rw"},
			{"Kirghiz","ky"},
			{"Kirundi (Rundi)","rn"},
			{"Konkani"," "},
			{"Korean","ko"},
			{"Kurdish","ku"},
			{"Laothian","lo"},
			{"Latin","la"},
			{"Latvian (Lettish)","lv"},
			{"Limburgish ( Limburger)","li"},
			{"Lingala","ln"},
			{"Lithuanian","lt"},
			{"Macedonian","mk"},
			{"Malagasy","mg"},
			{"Malay","ms"},
			{"Malayalam","ml"},
			{" "," "},
			{"Maltese","mt"},
			{"Maori","mi"},
			{"Marathi","mr"},
			{"Moldavian","mo"},
			{"Mongolian","mn"},
			{"Nauru","na"},
			{"Nepali","ne"},
			{"Norwegian","no"},
			{"Occitan","oc"},
			{"Oriya","or"},
			{"Oromo (Afaan Oromo)","om"},
			{"Papiamentu"," "},
			{"Pashto (Pushto)","ps"},
			{"Polish","pl"},
			{"Portuguese","pt"},
			{"Punjabi","pa"},
			{"Quechua","qu"},
			{"Rhaeto-Romance","rm"},
			{"Romanian","ro"},
			{"Russian","ru"},
			{"Sami (Lappish)"," "},
			{"Samoan","sm"},
			{"Sangro","sg"},
			{"Sanskrit","sa"},
			{"Serbian","sr"},
			{"Serbo-Croatian","sh"},
			{"Sesotho","st"},
			{"Setswana","tn"},
			{"Shona","sn"},
			{"Sichuan Yi","ii"},
			{"Sindhi","sd"},
			{"Sinhalese","si"},
			{"Siswati","ss"},
			{"Slovak","sk"},
			{"Slovenian","sl"},
			{"Somali","so"},
			{"Spanish","es"},
			{"Sundanese","su"},
			{"Swahili (Kiswahili)","sw"},
			{"Swedish","sv"},
			{"Syriac"," "},
			{"Tagalog","tl"},
			{"Tajik","tg"},
			{"Tamazight"," "},
			{"Tamil","ta"},
			{"Tatar","tt"},
			{"Telugu","te"},
			{"Thai","th"},
			{"Tibetan","bo"},
			{"Tigrinya","ti"},
			{"Tonga","to"},
			{"Tsonga","ts"},
			{"Turkish","tr"},
			{"Turkmen","tk"},
			{"Twi","tw"},
			{"Uighur","ug"},
			{"Ukrainian","uk"},
			{"Urdu","ur"},
			{"Uzbek","uz"},
			{"Venda"," "},
			{"Vietnamese","vi"},
			{"Volapük","vo"},
			{"Wallon","wa"},
			{"Welsh","cy"},
			{"Wolof","wo"},
			{"Xhosa","xh"},
			{"Yi"," "},
			{"Yiddish","yi, ji"},
			{"Yoruba","yo"},
			{"Zulu","zu"},
		};

	
	
}
