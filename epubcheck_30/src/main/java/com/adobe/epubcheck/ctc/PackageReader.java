package com.adobe.epubcheck.ctc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Vector;
import java.util.zip.ZipFile;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.adobe.epubcheck.api.Report;
import com.adobe.epubcheck.ctc.epubpackage.EpubPackage;
import com.adobe.epubcheck.ctc.epubpackage.ManifestItem;
import com.adobe.epubcheck.ctc.epubpackage.MetadataElement;
import com.adobe.epubcheck.ctc.epubpackage.PackageLanguage;
import com.adobe.epubcheck.ctc.epubpackage.PackageManifest;
import com.adobe.epubcheck.ctc.epubpackage.PackageSpine;
import com.adobe.epubcheck.ctc.epubpackage.SpineItem;
import com.adobe.epubcheck.ocf.EncryptionFilter;
import com.adobe.epubcheck.util.CheckingReport;
import com.adobe.epubcheck.util.EPUBVersion;

public class PackageReader 
{

	String containerEntry = "META-INF/container.xml";
	ZipFile zip;
	Hashtable<String, EncryptionFilter> enc;
	Report report;
	String checkType;
	String version;
	EpubPackage epack;
	String mainPackageFile;
	boolean hasContainerFile=false;
	boolean hasOpfFile=false;
	boolean hasMimetype=false;
	boolean hasContentFile=false;
	public PackageReader(ZipFile zip, Report report)
	{
		this.zip=zip;
		this.report=report;
	}
	
	
	
	
	public EpubPackage readPackageData()
	{
		
		Vector<String> pathToRootFile=getPathToRootFile();
		
		hasMimetype=hasMimeType(zip);
		
		XmlDocParser p=new XmlDocParser(zip, report);
		for(int i=0;i<pathToRootFile.size();i++)
		{
			
			mainPackageFile=pathToRootFile.get(i);
			Document doc=p.parseDocument(pathToRootFile.get(i));
			if(doc!=null)
			{	
				hasOpfFile=true;
				epack = new EpubPackage(pathToRootFile.get(i),zip,doc);
				epack.setPackageMainFile(mainPackageFile);
				epack.setVersion(getEpubVersion(doc));
				getMainPackageData(doc,epack);
				getMetadata(doc,epack);
				getManifest(doc,epack);
				getSpine(doc,epack);
				
				if(epack.getVersion()==EPUBVersion.VERSION_3)
				{	
						if(!(hasContainerFile&&hasOpfFile&&hasMimetype&&hasContentFile))
						{
							String messageCode="CTC-053";
							String message="Epub does not contain minimal set of specified in Epub3 standard";
							CheckingReport.addCheckMessage("EpubPackage",
									String.valueOf(-1),
									String.valueOf(-1),
									message,messageCode);
						}
				}
			}
		}
		
		return epack;
	}
	
	public String getMainPackageFile() {
		return mainPackageFile;
	}

	public void setMainPackageFile(String mainPackageFile) {
		this.mainPackageFile = mainPackageFile;
	}

	
	private boolean hasMimeType(ZipFile zip)
	{
		while(zip.entries().hasMoreElements())
		{
			if(zip.entries().nextElement().getName().compareToIgnoreCase("mimetype")==0)
				return true;
		}
		return false;
	}
	
	private Vector<String> getPathToRootFile() 
	{
		Vector<String> rootFiles = new Vector<String>();
			
			XmlDocParser p=new XmlDocParser(zip, report);
			Document doc=p.parseDocument(containerEntry);
			if(doc!=null)
			{	
				hasContainerFile=true;
			
			NodeList nList = doc.getElementsByTagName("rootfiles");

			for (int i = 0; i < nList.getLength(); i++) {
				Node n = nList.item(i);
				String ln = n.getNodeName();
				if (n.getNodeName().compareToIgnoreCase("rootfiles") == 0) {

					NodeList cn = n.getChildNodes();
					int stop = 0;
					for (int j = 0; j < cn.getLength(); j++) {
						Node currentNode = cn.item(j);
						if (currentNode.getNodeName().compareToIgnoreCase(
								"rootfile") == 0) {

							NamedNodeMap attr = currentNode.getAttributes();
							Node path = attr.getNamedItem("full-path");
							String nodeValue = path.getNodeValue();
							rootFiles.add(nodeValue);
						}
					}
				}
			}
			}
		
		return rootFiles;
	}
	
	private void getMainPackageData(Document doc,EpubPackage epack)
	{
		
		NodeList dList=doc.getChildNodes();
		NodeList nList = doc.getElementsByTagName("package");
		if(nList.getLength()>0)
		{
			NamedNodeMap packAttr=nList.item(0).getAttributes();
			Node langNode=packAttr.getNamedItem("xml:lang");
			PackageLanguage pl =new PackageLanguage();
			epack.setLanguage(pl);
			if(langNode!=null)
			pl.setLanguage(langNode.getNodeValue());
		}
		
		
		
		
		
	}
	private void getMetadata(Document doc,EpubPackage epack)
	{
		
		
		NodeList nList = doc.getElementsByTagName("metadata");
		if(nList.getLength()>0)
		{
			Node metadata=nList.item(0);
			NodeList metaNodes = metadata.getChildNodes();
			
			for(int i=0;i<metaNodes.getLength();i++)
			{
				
				
			
				
				
				if(!metaNodes.item(i).getNodeName().startsWith("#"))
				{
					
					MetadataElement meta = new MetadataElement();
					
					Node n = metaNodes.item(i);
					meta.setName(n.getNodeName());
					
					if (n.hasChildNodes())
					{    
						//System.out.println("Dodaje element o nazwie "+metaNodes.item(i).getNodeName()+" z wartoscia "+n.getFirstChild().getNodeValue());
						meta.setValue(n.getFirstChild().getNodeValue());
					}
			        else
			        {	
			        	//System.out.println("Dodaje element o nazwie "+metaNodes.item(i).getNodeName()+" z wartoscia "+n.getNodeValue());
			        	meta.setValue(n.getNodeValue());
			        }
					
					
					
					NamedNodeMap  attrs = metaNodes.item(i).getAttributes();
					for(int a=0;a<attrs.getLength();a++)
					{
						//System.out.println("	Dodaje attrybut "+attrs.item(a).getNodeName()+" z wartoscia "+attrs.item(a).getNodeValue());
						
						if(attrs.item(a).getNodeName().compareToIgnoreCase("elementLineNumber")!=0 && attrs.item(a).getNodeName().compareToIgnoreCase("elementColumnNumber")!=0)
						meta.addAttribute(attrs.item(a).getNodeName(),attrs.item(a).getNodeValue());
					}
					
				
					epack.getMetadata().addMetaElement(meta);
				}
					
					
			}		
			
		}
		
		
		
		
		int stop=0;
	}
	private void getManifest(Document doc,EpubPackage epack) 
	{

				
		NodeList nList = doc.getElementsByTagName("manifest");

			for (int i = 0; i < nList.getLength(); i++) {
				Node n = nList.item(i);
				String ln = n.getNodeName();
				if (n.getNodeName().compareToIgnoreCase("manifest") == 0) {
					PackageManifest manifest= new PackageManifest();
					epack.setManifest(manifest);
					NodeList cn = n.getChildNodes();
					int stop = 0;
					for (int j = 0; j < cn.getLength(); j++) {
						Node currentNode = cn.item(j);
						if (currentNode.getNodeName().compareToIgnoreCase("item") == 0) 
						{
							
							ManifestItem item = new ManifestItem();
							
							NamedNodeMap attr = currentNode.getAttributes();

							Node hrefNode = attr.getNamedItem("href");					
							String hrefValue="";
							
							Node mediaTypeNode = attr.getNamedItem("media-type");
							String mediaTypeValue="";
							
							Node propertiesNode = attr.getNamedItem("properties");
							String propertiesValue="";
							
							if(mediaTypeNode.getNodeValue().compareToIgnoreCase("application/xhtml+xml")==0)
								hasContentFile=true;
							
							if (hrefNode != null) 
							{
								hrefValue=hrefNode.getNodeValue();
								item.setHref(hrefValue);
								
								
							}
							if (mediaTypeNode != null) 
							{
								mediaTypeValue=mediaTypeNode.getNodeValue();
								item.setMediaType(mediaTypeValue);
							}
														
							if (propertiesNode != null) 
							{									
								propertiesValue=propertiesNode.getNodeValue();
								item.setProperties(propertiesValue);							
							}

							manifest.addItem(item);
						}
					}
				}
			}
	}


	private void getSpine(Document doc,EpubPackage epack) 
	{

				
		NodeList nList = doc.getElementsByTagName("spine");

			for (int i = 0; i < nList.getLength(); i++) {
				Node n = nList.item(i);
				String ln = n.getNodeName();
				if (n.getNodeName().compareToIgnoreCase("spine") == 0) {
					PackageSpine spine= new PackageSpine();
					epack.setSpine(spine);
					NamedNodeMap spineAttrs = n.getAttributes();
					
					Node idNode = spineAttrs.getNamedItem("id");	
					if(idNode!=null)
					spine.setId(idNode.getNodeValue());
					
					Node tocNode = spineAttrs.getNamedItem("toc");
					if(tocNode!=null)
					spine.setToc(tocNode.getNodeValue());
					
					Node pageProgressionDirectionNode = spineAttrs.getNamedItem("page-progression-direction");
					if(pageProgressionDirectionNode!=null)
						spine.setPageProgressionDirection(pageProgressionDirectionNode.getNodeValue());
					
					NodeList cn = n.getChildNodes();

					for (int j = 0; j < cn.getLength(); j++) {
						Node currentNode = cn.item(j);
					
						if (currentNode.getNodeName().compareToIgnoreCase("itemref") == 0) 
						{
							
							SpineItem item = new SpineItem();
							
							NamedNodeMap attr = currentNode.getAttributes();

							Node idrefNode = attr.getNamedItem("idref");					
							if(idrefNode!=null)
								item.setIdref(idrefNode.getNodeValue());
							
							Node idSpineNode = attr.getNamedItem("id");
							if(idSpineNode!=null)
							item.setId(idSpineNode.getNodeValue());
							
							Node linearNode = attr.getNamedItem("linear");
							if(linearNode!=null)
								item.setLinear(linearNode.getNodeValue());
														
							Node propertiesNode = attr.getNamedItem("properties");
							if(propertiesNode!=null)
							item.setProperties(propertiesNode.getNodeValue());
							
							spine.addItem(item);
						}
					}
				}
			
		

		
	}
	}

	private EPUBVersion getEpubVersion(Document doc)
	{
		
		NodeList packageNode = doc.getElementsByTagName("package");
		NamedNodeMap packageNodeAttr = packageNode.item(0).getAttributes();
		Node node=packageNodeAttr.getNamedItem("version");
		if(node!=null)
			version=node.getNodeValue();
		if(version!=null && version.startsWith("3"))
		{
			return EPUBVersion.VERSION_3; 
		}
		else if(version!=null && version.startsWith("2"))
		{
			return EPUBVersion.VERSION_2;
		}
		else
			return null;
	}
	
	
}
