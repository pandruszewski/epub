package com.adobe.epubcheck.util;

import java.util.HashMap;
import java.util.Map;

public class Dictionary {

	public static final Dictionary dictionary = new Dictionary();
	private Map<String, AdditionalInfo> info = new HashMap<String, AdditionalInfo>();
	private AdditionalInfo additionalInfo = null;

	private Dictionary() {
		initInfoMap();
	}

	private void initInfoMap() {
		
		//=====/API
		info.put(
				"API-001",
				new AdditionalInfo(
						Severity.WARNING,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Possibly problematic Epub file extension"	//ShortDescription
				)
		);
		
		info.put(
				"API-002",
				new AdditionalInfo(
						Severity.WARNING,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Uncomon Epub file extension"	//ShortDescription
				)
		);
		
		info.put(
				"API-003",
				new AdditionalInfo(
						Severity.FATAL,
						"General", //Main category
						"File Access",	//SubCategory
						"Problem with archive"	//ShortDescription
				)
		);
		
		info.put(
				"API-004",
				new AdditionalInfo(
						Severity.FATAL,
						"General", //Main category
						"File Access",	//SubCategory
						"Problem with archive"	//ShortDescription
				)
		);
		
		info.put(
				"API-005",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Problem with archive"	//ShortDescription
				)
		);
		
		info.put(
				"API-006",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Problem with archive"	//ShortDescription
				)
		);
		
		info.put(
				"API-007",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Problem with archive"	//ShortDescription
				)
		);
		
		info.put(
				"API-008",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Wrong mimetype for Epub file"	//ShortDescription
				)
		);
		
		info.put(
				"API-009",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"IO Error"	//ShortDescription
				)
		);

				
		//=====/BMP
		
		
		info.put(
				"BMP-001",
				new AdditionalInfo(
						Severity.ERROR,
						"Resources", //Main category
						"Corrupted Resources",	//SubCategory
						"Wrong mimetype for image resource"	//ShortDescription
				)
		);
		
		info.put(
				"BMP-002",
				new AdditionalInfo(
						Severity.ERROR,
						"Resources", //Main category
						"Epub Structure",	//SubCategory
						"Path to image resource is missing"	//ShortDescription
				)
		);
		
		info.put(
				"BMP-003",
				new AdditionalInfo(
						Severity.ERROR,
						"Resources", //Main category
						"Corrupted Resources",	//SubCategory
						"Path to image cannot be decrypted"	//ShortDescription
				)
		);
		
		info.put(
				"BMP-004",
				new AdditionalInfo(
						Severity.ERROR,
						"Resources", //Main category
						"Corrupted Resources",	//SubCategory
						"Wrong image resource file"	//ShortDescription
				)
		);
		
		info.put(
				"BMP-005",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Corrupted Resources",	//SubCategory
						"Unable to read image resource"	//ShortDescription
				)
		);
		
		//=====/CSS
		
		info.put(
				"CSS-001",
				new AdditionalInfo(
						Severity.ERROR,
						"Layout", //Main category
						"CSS",	//SubCategory
						"Missing CSS"	//ShortDescription
				)
		);
		
		info.put(
				"CSS-002",
				new AdditionalInfo(
						Severity.ERROR,
						"Layout", //Main category
						"CSS",	//SubCategory
						"Exception thrown in CSS"	//ShortDescription
				)
		);
		
		info.put(
				"CSS-003",
				new AdditionalInfo(
						Severity.ERROR,
						"Layout", //Main category
						"CSS",	//SubCategory
						"Included font is not of opentype"	//ShortDescription
				)
		);
		
		info.put(
				"CSS-004",
				new AdditionalInfo(
						Severity.ERROR,
						"Layout", //Main category
						"CSS",	//SubCategory
						"Not part of the EPUB 3 CSS Profile"	//ShortDescription
				)
		);
		
		info.put(
				"CSS-005",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"CSS",	//SubCategory
						""	//ShortDescription
				)
		);
		
		info.put(
				"CSS-006",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"CSS",	//SubCategory
						"Unicode-bidi properties included in EPUB Style Sheet"	//ShortDescription
				)
		);
		

		
		//=====/DTB
		
		info.put(
				"DTB-001",
				new AdditionalInfo(
						Severity.ERROR,
						"Accessibility", //Main category
						"DTBook",	//SubCategory
						"Missing DTBook file"	//ShortDescription
				)
		);
		
		info.put(
				"DTB-002",
				new AdditionalInfo(
						Severity.ERROR,
						"Accessibility", //Main category
						"DTBook",	//SubCategory
						"DTBook cannot be decrypted"	//ShortDescription
				)
		);
		
		
		//=====/NAV
		
		info.put(
				"NAV-001",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"nav file is not supported in Epub2"	//ShortDescription
				)
		);
		
		info.put(
				"NAV-002",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"nav file is not supported in Epub2"	//ShortDescription
				)
		);
		
		info.put(
				"NAV-003",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Missing DTBook file"	//ShortDescription
				)
		);
		
		info.put(
				"NAV-004",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Nav file cannot be decrypted"	//ShortDescription
				)
		);
				
		
		
		//=====/NCX
		
		info.put(
				"NAV-001",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"NCX file is missing"	//ShortDescription
				)
		);
		
		
		info.put(
				"NAV-002",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"NCX file cannot be decrypted"	//ShortDescription
				)
		);
		
		info.put(
				"NAV-003",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Wrong NCX file structure"	//ShortDescription
				)
		);
		
		//=====/OCF
		
		info.put(
				"OCF-001",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Required META-INF/container.xml resource is missing"	//ShortDescription
				)
		);
		
		info.put(
				"OCF-002",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Wrong declaration of Mimetype"	//ShortDescription
				)
		);
		
		info.put(
				"OCF-003",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Invalid Version Exception"	//ShortDescription
				)
		);
		
		info.put(
				"OCF-004",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Integrity",	//SubCategory
						"Entry not found in zip file"	//ShortDescription
				)
		);
		
		info.put(
				"OCF-005",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"No rootfiles with media type 'application/oebps-package+xml"	//ShortDescription
				)
		);
		
		//=====/OPF
		
		info.put(
				"OPF-001",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Integrity",	//SubCategory
						"Missing resource file"	//ShortDescription
				)
		);

		info.put(
				"OPF-002",
				new AdditionalInfo(
						Severity.WARNING,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Resource file cannot be decrypted"	//ShortDescription
				)
		);

		info.put(
				"OPF-003",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Integrity",	//SubCategory
						"OPF file  is missing"	//ShortDescription
				)
		);

		info.put(
				"OPF-004",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Wrong unique-identifier attribute"	//ShortDescription
				)
		);

		info.put(
				"OPF-005",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Generic message"	//ShortDescription
				)
		);

		info.put(
				"OPF-006",
				new AdditionalInfo(
						Severity.WARNING,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Item not declared in the OPF file but existing"	//ShortDescription
				)
		);

		info.put(
				"OPF-007",
				new AdditionalInfo(
						Severity.WARNING,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Empty directory in a ZIP"	//ShortDescription
				)
		);

		info.put(
				"OPF-008",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Unable to read zip file entries"	//ShortDescription
				)
		);

		info.put(
				"OPF-009",
				new AdditionalInfo(
						Severity.WARNING,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"File name contains non-ascii characters"	//ShortDescription
				)
		);

		info.put(
				"OPF-010",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Filename ends in dot"	//ShortDescription
				)
		);

		info.put(
				"OPF-011",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Disallowed characters in OCF file name"	//ShortDescription
				)
		);

		info.put(
				"OPF-012",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Filename contains spaces"	//ShortDescription
				)
		);

		info.put(
				"OPF-013",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"IO Exception"	//ShortDescription
				)
		);

		info.put(
				"OPF-014",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"File not declared in OPF manifest"	//ShortDescription
				)
		);

		info.put(
				"OPF-015",
				new AdditionalInfo(
						Severity.WARNING,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Spine contains only non-linear resources"	//ShortDescription
				)
		);

		info.put(
				"OPF-016",
				new AdditionalInfo(
						Severity.WARNING,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"text/html not appropriate for XHTML/OPS"	//ShortDescription
				)
		);

		info.put(
				"OPF-017",
				new AdditionalInfo(
						Severity.WARNING,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"text/html not appropriate for OEBPS 1.2"	//ShortDescription
				)
		);

		info.put(
				"OPF-018",
				new AdditionalInfo(
						Severity.WARNING,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Deprecated media-type"	//ShortDescription
				)
		);

		info.put(
				"OPF-019",
				new AdditionalInfo(
						Severity.WARNING,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"text/html is not appropriate for OEBPS 1.2"	//ShortDescription
				)
		);

		info.put(
				"OPF-020",
				new AdditionalInfo(
						Severity.WARNING,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Use of OPS media-type in OEBPS 1.2 context"	//ShortDescription
				)
		);

		info.put(
				"OPF-021",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Fallback item could not be found"	//ShortDescription
				)
		);

		info.put(
				"OPF-022",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Fallback item could not be found"	//ShortDescription
				)
		);

		info.put(
				"OPF-023",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Not a permissible spine media-type"	//ShortDescription
				)
		);

		info.put(
				"OPF-024",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Non-standard media-type"	//ShortDescription
				)
		);

		info.put(
				"OPF-025",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Non-standard media-type"	//ShortDescription
				)
		);

		info.put(
				"OPF-026",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Circular reference in fallback chain"	//ShortDescription
				)
		);

		info.put(
				"OPF-027",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Circular reference in fallback chain"	//ShortDescription
				)
		);

		info.put(
				"OPF-028",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Fallback item could not be found"	//ShortDescription
				)
		);

		info.put(
				"OPF-029",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Spine item with non-standard media-type but with no fallback"	//ShortDescription
				)
		);

		info.put(
				"OPF-030",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Spine item with fallback to non-spine-allowed media-type"	//ShortDescription
				)
		);

		info.put(
				"OPF-031",
				new AdditionalInfo(
						Severity.ERROR,
						"Security", //Main category
						"Scripted",	//SubCategory
						"Scripted property not set for the item"	//ShortDescription
				)
		);

		info.put(
				"OPF-032",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"File name contains characters disallowed in OCF file names"	//ShortDescription
				)
		);

		info.put(
				"OPF-033",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"OEBPS 1.2 syntax used in Epub"	//ShortDescription
				)
		);
		
		info.put(
				"OPF-034",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Unique-identifier attribute must be present and have a value"	//ShortDescription
				)
		);

		info.put(
				"OPF-035",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Generic message"	//ShortDescription
				)
		);


		info.put(
				"OPF-036",
				new AdditionalInfo(
						Severity.ERROR,
						"Security", //Main category
						"External references",	//SubCategory
						"No remote resources are permitted"	//ShortDescription
				)
		);

		info.put(
				"OPF-037",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Illegal Argument Exception"	//ShortDescription
				)
		);

		info.put(
				"OPF-038",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Item not found"	//ShortDescription
				)
		);

		info.put(
				"OPF-039",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"ToC attribute references resource with non-NCX mime type"	//ShortDescription
				)
		);

		info.put(
				"OPF-040",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Integrity",	//SubCategory
						"Item not found"	//ShortDescription
				)
		);

		info.put(
				"OPF-041",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Use of deprecated element"	//ShortDescription
				)
		);

		info.put(
				"OPF-042",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Not valid role"	//ShortDescription
				)
		);

		info.put(
				"OPF-043",
				new AdditionalInfo(
						Severity.WARNING,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Wrong DateTime syntax"	//ShortDescription
				)
		);

		info.put(
				"OPF-044",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"DateTime is not valid"	//ShortDescription
				)
		);

		info.put(
				"OPF-045",
				new AdditionalInfo(
						Severity.WARNING,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Empty element"	//ShortDescription
				)
		);

		info.put(
				"OPF-046",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Wrong media type"	//ShortDescription
				)
		);
		
		info.put(
				"OPF-047",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"The media-type has already been assigned a handler"	//ShortDescription
				)
		);
		
		info.put(
				"OPF-048",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Illegal argument Exception"	//ShortDescription
				)
		);
		
		info.put(
				"OPF-049",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Layout",	//SubCategory
						"Conflict attributes"	//ShortDescription
				)
		);
		
		info.put(
				"OPF-050",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Property not defined"	//ShortDescription
				)
		);
		
		info.put(
				"OPF-051",
				new AdditionalInfo(
						Severity.ERROR,
						"Security", //Main category
						"External references",	//SubCategory
						"External links to resources"	//ShortDescription
				)
		);
		
		info.put(
				"OPF-052",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Integrity",	//SubCategory
						"Referenced resource missing in the package"	//ShortDescription
				)
		);
		
		info.put(
				"OPF-053",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Referenced resource exists, but not declared in the OPF file"	//ShortDescription
				)
		);
		
		info.put(
				"OPF-054",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Fragment identifier missing"	//ShortDescription
				)
		);
		
		info.put(
				"OPF-055",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Hyperlink to non-standard resource"	//ShortDescription
				)
		);
		
		info.put(
				"OPF-056",
				new AdditionalInfo(
						Severity.WARNING,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Hyperlink to resource outside spine"	//ShortDescription
				)
		);
		
		info.put(
				"OPF-057",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Non-standard image resource"	//ShortDescription
				)
		);
		
		info.put(
				"OPF-058",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Wrong mime type"	//ShortDescription
				)
		);
		
		info.put(
				"OPF-059",
				new AdditionalInfo(
						Severity.WARNING,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Hyperlink to resource outside spine"	//ShortDescription
				)
		);
		
		info.put(
				"OPF-060",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Fragment identifier used for image resource"	//ShortDescription
				)
		);
		
		info.put(
				"OPF-061",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Fragment identifier used for stylesheet resource"	//ShortDescription
				)
		);
		
		info.put(
				"OPF-062",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Fragment identifier is not defined"	//ShortDescription
				)
		);
		
		info.put(
				"OPF-063",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Fragment identifier defines incompatible resource type"	//ShortDescription
				)
		);
		
		info.put(
				"OPF-064",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Fragment identifier defines incompatible resource type"	//ShortDescription
				)
		);
		
		
		//=====/OPS
		
		
		
		info.put(
				"OPS-001",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Integrity",	//SubCategory
						"OPS/XHTML file is missing"	//ShortDescription
				)
		);
		
		info.put(
				"OPS-002",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"OPS/XHTML file cannot be decrypted"	//ShortDescription
				)
		);
		
		info.put(
				"OPS-003",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"IO Exception"	//ShortDescription
				)
		);
		
		info.put(
				"OPS-004",
				new AdditionalInfo(
						Severity.WARNING,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Use of non-registered URI scheme type in href"	//ShortDescription
				)
		);
		
		info.put(
				"OPS-005",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Generic message"	//ShortDescription
				)
		);
		
		info.put(
				"OPS-006",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Undecleared prefix"	//ShortDescription
				)
		);
		
		info.put(
				"OPS-007",
				new AdditionalInfo(
						Severity.WARNING,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Empty or whitespace-only value of attribute ssml:ph"	//ShortDescription
				)
		);
		
		info.put(
				"OPS-008",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Conflicting attributes found"	//ShortDescription
				)
		);
		
		info.put(
				"OPS-009",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Video poster wrong image type"	//ShortDescription
				)
		);
		
		info.put(
				"OPS-010",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Empty src attribute"	//ShortDescription
				)
		);
		
		info.put(
				"OPS-011",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Object type a do not match"	//ShortDescription
				)
		);
		
		info.put(
				"OPS-012",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Element doesn't provide fallback"	//ShortDescription
				)
		);
		
		info.put(
				"OPS-013",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Property not declared in OPF"	//ShortDescription
				)
		);
		
		info.put(
				"OPS-014",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Property which shouldn't be declared in OPF"	//ShortDescription
				)
		);
		
		
		//=====/OVL
		
		info.put(
				"OVL-001",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Integrity",	//SubCategory
						"Missing file"	//ShortDescription
				)
		);
		
		info.put(
				"OVL-002",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"File cannot be decrypted"	//ShortDescription
				)
		);
		
		info.put(
				"OVL-003",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"IOException"	//ShortDescription
				)
		);
		
		info.put(
				"OVL-004",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Undecleared prefix"	//ShortDescription
				)
		);
		
		info.put(
				"OVL-005",
				new AdditionalInfo(
						Severity.ERROR,
						"Accessibility", //Main category
						"Epub Structure",	//SubCategory
						"Non-standard audio type refernence"	//ShortDescription
				)
		);
		
		
		
		
		//=====/TOL
		
		info.put(
				"TOL-001",
				new AdditionalInfo(
						Severity.FATAL,
						"General", //Main category
						"Checks not supported",	//SubCategory
						"Type and version of epub not to by validated by checker"	//ShortDescription
				)
		);
		
		info.put(
				"TOL-002",
				new AdditionalInfo(
						Severity.FATAL,
						"General", //Main category
						"Checks not supported",	//SubCategory
						"Type and version of epub not to by validated by checker"	//ShortDescription
				)
		);
		
		
		//=====/XPA
		
		
		info.put(
				"XPA-001",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Wrong file encoding"	//ShortDescription
				)
		);
		
		info.put(
				"XPA-002",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"I/O error during resource reading"	//ShortDescription
				)
		);
		
		info.put(
				"XPA-003",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Resource could not be parsed"	//ShortDescription
				)
		);
		
		info.put(
				"XPA-004",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Generic message"	//ShortDescription
				)
		);
		
		info.put(
				"XPA-005",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Tool Configuration",	//SubCategory
						"A problem in XML parser detected"	//ShortDescription
				)
		);
		
		info.put(
				"XPA-006",
				new AdditionalInfo(
						Severity.ERROR,
						"Security", //Main category
						"External references",	//SubCategory
						"Unresolved external XML entity"	//ShortDescription
				)
		);
		
		info.put(
				"XPA-007",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Generic message"	//ShortDescription
				)
		);
		
		info.put(
				"XPA-008",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Undeclared Entity"	//ShortDescription
				)
		);
		
		info.put(
				"XPA-009",
				new AdditionalInfo(
						Severity.ERROR,
						"Security", //Main category
						"External references",	//SubCategory
						"External entity declaration found"	//ShortDescription
				)
		);
		
		info.put(
				"XPA-010",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Undeclared Entity"	//ShortDescription
				)
		);
		
		info.put(
				"XPA-011",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"External entities"	//ShortDescription
				)
		);
		
		//=====/SCR
		
		
		info.put(
				"CTC-001",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Package root not found"	//ShortDescription
				)
		);
		
		info.put(
				"CTC-002",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Spine element not found"	//ShortDescription
				)
		);
		
		info.put(
				"CTC-003",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"ZipEntry not found"	//ShortDescription
				)
		);
		
		info.put(
				"CTC-004",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"IO Error",	//SubCategory
						"Error during package content reading"	//ShortDescription
				)
		);
		
		info.put(
				"CTC-005",
				new AdditionalInfo(
						Severity.ERROR,
						"Security", //Main category
						"Scripting",	//SubCategory
						"Processing of external returns from xhr calls"	//ShortDescription
				)
		);
		
		info.put(
				"CTC-006",
				new AdditionalInfo(
						Severity.ERROR,
						"Security", //Main category
						"Scripting",	//SubCategory
						"eval() found"	//ShortDescription
				)
		);
		
		info.put(
				"CTC-007",
				new AdditionalInfo(
						Severity.ERROR,
						"Security", //Main category
						"External references",	//SubCategory
						"Http found"	//ShortDescription
				)
		);
		
		info.put(
				"CTC-008",
				new AdditionalInfo(
						Severity.ERROR,
						"Security", //Main category
						"External references",	//SubCategory
						"Ftp found"	//ShortDescription
				)
		);
		
		info.put(
				"CTC-009",
				new AdditionalInfo(
						Severity.ERROR,
						"Security", //Main category
						"External references",	//SubCategory
						"WWW found"	//ShortDescription
				)
		);
		
		info.put(
				"CTC-010",
				new AdditionalInfo(
						Severity.INFO,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"TOC not defined"	//ShortDescription
				)
		);
		
		info.put(
				"CTC-011",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"No navigation definition document"	//ShortDescription
				)
		);
		
		info.put(
				"CTC-012",
				new AdditionalInfo(
						Severity.ERROR,
						"Security", //Main category
						"Scripting",	//SubCategory
						"Scripted: presence of .js files"	//ShortDescription
				)
		);
		
		//aa
		info.put(
				"CTC-013",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Parse navigation document error"	//ShortDescription
				)
		);
		
		//aa
		info.put(
				"CTC-014",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Parse navigation document error"	//ShortDescription
				)
		);
		
		info.put(
				"CTC-015",
				new AdditionalInfo(
						Severity.INFO,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Too much NAV elements"	//ShortDescription
				)
		);
		
		info.put(
				"CTC-017",
				new AdditionalInfo(
						Severity.INFO,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Document contains SCRIPT tag, and document it's not marked as 'scripted'"	//ShortDescription
				)
		);
		
		info.put(
				"CTC-018",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Document contains SCRIPT tag, but it's not marked as 'scripted' "	//ShortDescription
				)
		);
		
		info.put(
				"CTC-019",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Style inline declaration "	//ShortDescription
				)
		);
		
		info.put(
				"CTC-020",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Spine contains more then 40 elements "	//ShortDescription
				)
		);
		
		
		info.put(
				"CTC-021",
				new AdditionalInfo(
						Severity.INFO,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Element contains more then 5 DIV or SPAN tags"	//ShortDescription
				)
		);
		
		info.put(
				"CTC-022",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Not allowed HTML4 tags "	//ShortDescription
				)
		);
		info.put(
				"CTC-023",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Not allowed HTML5 tags "	//ShortDescription
				)
		);
		info.put(
				"CTC-024",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Missing or more then one metadata definition tag "	//ShortDescription
				)
		);
		info.put(
				"CTC-025",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Not minimal metadata set declared"	//ShortDescription
				)
		);
		info.put(
				"CTC-026",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Different values in lang and xml:lang attributes"	//ShortDescription
				)
		);
		info.put(
				"CTC-027",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Invalid language definition value in xml:lang attribute"	//ShortDescription
				)
		);
		info.put(
				"CTC-028",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Invalid language definition value in lang attribute"	//ShortDescription
				)
		);
		info.put(
				"CTC-029",
				new AdditionalInfo(
						Severity.ERROR,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Missing xml:lang attribute"	//ShortDescription
				)
		);
		info.put(
				"CTC-030",
				new AdditionalInfo(
						Severity.WARNING,
						"General", //Main category
						"Language",	//SubCategory
						"Missing xml:lang attribute"	//ShortDescription
				)
		);
		info.put(
				"CTC-030",
				new AdditionalInfo(
						Severity.WARNING,
						"General", //Main category
						"Language",	//SubCategory
						"Missing lang attribute"	//ShortDescription
				)
		);
		info.put(
				"CTC-031",
				new AdditionalInfo(
						Severity.INFO,
						"General", //Main category
						"CSS3",	//SubCategory
						"Found rotateX() directive"	//ShortDescription
				)
		);
		
		info.put(
				"CTC-032",
				new AdditionalInfo(
						Severity.INFO,
						"General", //Main category
						"CSS3",	//SubCategory
						"Found rotateY() directive"	//ShortDescription
				)
		);
		
		info.put(
				"CTC-033",
				new AdditionalInfo(
						Severity.INFO,
						"General", //Main category
						"CSS3",	//SubCategory
						"Found column-count directive"	//ShortDescription
				)
		);
		
		info.put(
				"CTC-034",
				new AdditionalInfo(
						Severity.INFO,
						"General", //Main category
						"CSS3",	//SubCategory
						"Found column-gap directive"	//ShortDescription
				)
		);
		
		info.put(
				"CTC-035",
				new AdditionalInfo(
						Severity.INFO,
						"General", //Main category
						"CSS3",	//SubCategory
						"Found column-rule directive"	//ShortDescription
				)
		);
		
		info.put(
				"CTC-036",
				new AdditionalInfo(
						Severity.INFO,
						"General", //Main category
						"CSS3",	//SubCategory
						"Found keyframes directive"	//ShortDescription
				)
		);
		
		info.put(
				"CTC-037",
				new AdditionalInfo(
						Severity.INFO,
						"General", //Main category
						"CSS3",	//SubCategory
						"Found transition directive"	//ShortDescription
				)
		);
		
		
		info.put(
				"CTC-038",
				new AdditionalInfo(
						Severity.INFO,
						"Accessibility", //Main category
						"Layout",	//SubCategory
						"Found rendition:layout directive"	//ShortDescription
				)
		);
		
		info.put(
				"CTC-039",
				new AdditionalInfo(
						Severity.INFO,
						"Accessibility", //Main category
						"Orientation",	//SubCategory
						"Found rendition:orientation directive"	//ShortDescription
				)
		);
		
		info.put(
				"CTC-041",
				new AdditionalInfo(
						Severity.INFO,
						"Accessibility", //Main category
						"Spread",	//SubCategory
						"Found Outer-Publication CFI"	//ShortDescription
				)
		);
		
		
		info.put(
				"CTC-042",
				new AdditionalInfo(
						Severity.INFO,
						"Accessibility", //Main category
						"Spread",	//SubCategory
						"Found Intra-publication CFI"	//ShortDescription
				)
		);
		
		info.put(
				"CTC-043",
				new AdditionalInfo(
						Severity.WARNING,
						"Security", //Main category
						"Scripting",	//SubCategory
						"Inline scripts found"	//ShortDescription
				)
		);
		info.put(
				"CTC-044",
				new AdditionalInfo(
						Severity.WARNING,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"Invalid file extension"	//ShortDescription
				)
		);
		info.put(
				"CTC-045",
				new AdditionalInfo(
						Severity.WARNING,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"HTML4 DOCTYPE definition"	//ShortDescription
				)
		);
		info.put(
				"CTC-046",
				new AdditionalInfo(
						Severity.WARNING,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"HTML4 DOCTYPE definition"	//ShortDescription
				)
		);
		
		info.put(
				"CTC-047",
				new AdditionalInfo(
						Severity.WARNING,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"HTML4 specific tags found"	//ShortDescription
				)
		);
		info.put(
				"CTC-048",
				new AdditionalInfo(
						Severity.WARNING,
						"General", //Main category
						"Epub Structure",	//SubCategory
						"HTML5 specific tags found"	//ShortDescription
				)
		);
		info.put(
				"CTC-049",
				new AdditionalInfo(
						Severity.INFO,
						"Accessibility", //Main category
						"CSS",	//SubCategory
						"Multiple CSS for publication"	//ShortDescription
				)
		);
		
		

		info.put(
				"CTC-050",
				new AdditionalInfo(
						Severity.INFO,
						"Accessibility", //Main category
						"Spread",	//SubCategory
						"Found Intra-publication CFI"	//ShortDescription
				)
		);
		
		
		info.put(
				"CTC-051",
				new AdditionalInfo(
						Severity.INFO,
						"Accessibility", //Main category
						"Spread",	//SubCategory
						"Found Outer-publication CFI"	//ShortDescription
				)
		);
		
		info.put(
				"CTC-052",
				new AdditionalInfo(
						Severity.ERROR,
						"General ", //Main category
						"Epub Structure",	//SubCategory
						"Wrong use of Epub3 constructs"	//ShortDescription
				)
		);
		
		info.put(
				"CTC-053",
				new AdditionalInfo(
						Severity.ERROR,
						"General ", //Main category
						"Epub3 compatibility",	//SubCategory
						"Epub does not contain minimal set"	//ShortDescription
				)
		);
		
	}

//	public void setMessage(String id, String message) {
//		AdditionalInfo additionalObject = info.get(id);
//		if (additionalObject != null) {
//			additionalObject.setMessage(message);
//		}
//	}

	public AdditionalInfo getAdditionalInfo(String messageUI){
		AdditionalInfo additionalInfo = null;
		additionalInfo = info.get(messageUI);
		
		return additionalInfo;
	}
	

	public Severity getSeverity() {
		if (additionalInfo != null)
			return additionalInfo.getSeverity();
		return null;
	}

	
//	public String getMessage() {
//		if (additionalInfo != null)
//			return additionalInfo.getMessage();
//
//		return null;
//	}

	public String getMessageMainCategory() {
		if (additionalInfo != null)
			return additionalInfo.getMessageMainCategory();
		return null;
	}

	public String getMessageSubCategory() {
		if (additionalInfo != null)
			return additionalInfo.getMessageSubCategory();
		return null;
	}
	
	public String getMessageShortDescription() {
		if (additionalInfo != null)
			return additionalInfo.getMessageShortDescription();
		return null;
	}

}
