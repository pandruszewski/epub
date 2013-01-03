package com.adobe.epubcheck.ctc.epubpackage;

import java.util.Vector;

public class PackageManifest {

	
	Vector<ManifestItem> items = new Vector<ManifestItem>();
	
	public Vector getItems()
	{
		return items;
	}
	
	public int itemsLength()
	{
		return items.size();
	}
	
	public void addItem(ManifestItem mi)
	{
		items.add(mi);
	}
	public ManifestItem getItem(int i)
	{
		return items.get(i);
	}
	
}
