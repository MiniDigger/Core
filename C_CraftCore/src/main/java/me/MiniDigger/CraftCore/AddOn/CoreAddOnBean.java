/**
 * █████████████████████████████████████████████████████████████████████████████████████████████████████████████████
 * █░░░░░░░░░░░░░░█░░░░░░██░░░░░░█░░░░░░░░░░░░░░████░░░░░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░░░░░░░░░░░███░░░░░░░░░░░░░░█
 * █░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░████░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀▄▀░░███░░▄▀▄▀▄▀▄▀▄▀░░█
 * █░░░░░░▄▀░░░░░░█░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░░░░░████░░▄▀░░░░░░░░░░█░░▄▀░░░░░░▄▀░░█░░▄▀░░░░░░░░▄▀░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░████████████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░████░░▄▀░░███░░▄▀░░█████████
 * █████░░▄▀░░█████░░▄▀░░░░░░▄▀░░█░░▄▀░░░░░░░░░░████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░░░▄▀░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀▄▀░░███░░▄▀▄▀▄▀▄▀▄▀░░█
 * █████░░▄▀░░█████░░▄▀░░░░░░▄▀░░█░░▄▀░░░░░░░░░░████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░▄▀░░░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░████████████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░██░░▄▀░░█████░░▄▀░░█████████
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░░░░░████░░▄▀░░░░░░░░░░█░░▄▀░░░░░░▄▀░░█░░▄▀░░██░░▄▀░░░░░░█░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░████░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░██░░▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█
 * █████░░░░░░█████░░░░░░██░░░░░░█░░░░░░░░░░░░░░████░░░░░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░██░░░░░░░░░░█░░░░░░░░░░░░░░█
 * █████████████████████████████████████████████████████████████████████████████████████████████████████████████████
 * 
 * Copyright © MiniDigger and others - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Martin Benndorf <admin@minidigger.me>, 2013-2015 and others
 */
package me.MiniDigger.CraftCore.AddOn;

import org.json.simple.JSONObject;

import me.MiniDigger.Core.AddOn.AddOnBean;

public class CoreAddOnBean implements AddOnBean {
	
	private String	     name;
	private String	     author;
	private String	     version;
	private String	     classPackage;
	private String	     classPackageDev;
	
	private final String	NAME	   = "name";
	private final String	AUTHOR	   = "author";
	private final String	VERSION	   = "version";
	private final String	PACKAGE	   = "package";
	private final String	PACKAGEDEV	= "packagedev";
	
	public CoreAddOnBean() {
		
	}
	
	public CoreAddOnBean(final JSONObject obj) {
		if (obj.containsKey(NAME)) {
			name = (String) obj.get(NAME);
		}
		if (obj.containsKey(AUTHOR)) {
			author = (String) obj.get(AUTHOR);
		}
		if (obj.containsKey(VERSION)) {
			version = (String) obj.get(VERSION);
		}
		if (obj.containsKey(PACKAGE)) {
			classPackage = (String) obj.get(PACKAGE);
		}
		if (obj.containsKey(PACKAGEDEV)) {
			classPackageDev = (String) obj.get(PACKAGEDEV);
		}
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public void setName(final String name) {
		this.name = name;
	}
	
	@Override
	public String getAuthor() {
		return author;
	}
	
	@Override
	public void setAuthor(final String author) {
		this.author = author;
	}
	
	@Override
	public String getVersion() {
		return version;
	}
	
	@Override
	public void setVersion(final String version) {
		this.version = version;
	}
	
	@Override
	public void setPackage(final String classPackage) {
		this.classPackage = classPackage;
	}
	
	@Override
	public String getPackage() {
		return classPackage;
	}
	
	@Override
	public void setPackageDev(final String classPackage) {
		classPackageDev = classPackage;
	}
	
	@Override
	public String getPackageDev() {
		return classPackageDev;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJson() {
		final JSONObject obj = new JSONObject();
		
		obj.put(NAME, name);
		obj.put(VERSION, version);
		obj.put(AUTHOR, author);
		obj.put(PACKAGE, classPackage);
		obj.put(PACKAGEDEV, classPackageDev);
		
		return obj;
	}
}
