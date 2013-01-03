package com.adobe.epubcheck.ctc.epubpackage;

import java.util.zip.ZipFile;

import org.w3c.dom.Document;

import com.adobe.epubcheck.util.EPUBVersion;

public class EpubPackage 
{
	String packageMainFilePath;
	ZipFile zip;
	Document packDoc;	
	public Document getPackDoc() {
		return packDoc;
	}

	public void setPackDoc(Document packDoc) {
		this.packDoc = packDoc;
	}

	String packageMainPath="";
	PackageIdentifier pid=new PackageIdentifier();
	PackageLanguage language= new PackageLanguage();
	PackageManifest manifest = new PackageManifest();
	PackageSpine spine = new PackageSpine();
	PackageMetadata metadata = new PackageMetadata();
	public PackageMetadata getMetadata() {
		return metadata;
	}

	public void setMetadata(PackageMetadata metadata) {
		this.metadata = metadata;
	}

	EPUBVersion version;
	
	public EPUBVersion getVersion() {
		return version;
	}

	public void setVersion(EPUBVersion version) {
		this.version = version;
	}

	public EpubPackage(String packageMainFile,ZipFile zip,Document doc)
	{
		this.packageMainFilePath=packageMainFile;
		this.zip=zip;
		this.packDoc=doc;
		if (packageMainFile.lastIndexOf('/') > 0)
			packageMainPath = packageMainFile.substring(0,
					packageMainFile.lastIndexOf('/'));
		pid=new PackageIdentifier();
		manifest = new PackageManifest();
		spine = new PackageSpine();
		
	}
	
	public String getPackageMainFile() {
		return packageMainFilePath;
	}

	public void setPackageMainFile(String packageMainFile) {
		this.packageMainFilePath = packageMainFile;
	}

	public String getPackageMainPath() {
		return packageMainPath;
	}

	public void setPackageMainPath(String packageMainPath) {
		this.packageMainPath = packageMainPath;
	}

	public PackageIdentifier getPid() {
		return pid;
	}

	public void setPid(PackageIdentifier pid) {
		this.pid = pid;
	}

	public PackageLanguage getLanguage() {
		return language;
	}

	public void setLanguage(PackageLanguage language) {
		this.language = language;
	}

	public PackageManifest getManifest() {
		return manifest;
	}

	public void setManifest(PackageManifest manifest) {
		this.manifest = manifest;
	}

	public PackageSpine getSpine() {
		return spine;
	}

	public void setSpine(PackageSpine spine) {
		this.spine = spine;
	}
	public ZipFile getZip() {
		return zip;
	}

	public void setZip(ZipFile zip) {
		this.zip = zip;
	}

}
