package com.adobe.epubcheck.ctc.epubpackage;

import java.util.Vector;

public class PackageMetadata {

	Vector<MetadataElement> m = new Vector<MetadataElement>();
	
	
	public Vector<MetadataElement> getMetaElements()
	{
		return m;
	}
	
	public void addMetaElement(MetadataElement meta)
	{
		m.add(meta);
	}
	public MetadataElement getMetaElement(int i)
	{
		return m.get(i);
	}
	public int metadataElementsCount()
	{
		return m.size();
	}
}
