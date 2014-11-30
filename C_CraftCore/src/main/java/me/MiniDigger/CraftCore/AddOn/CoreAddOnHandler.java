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
 * Written by Martin Benndorf <admin@minidigger.me>, 2013-2014 and others
 */
package me.MiniDigger.CraftCore.AddOn;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.AddOn.AddOn;
import me.MiniDigger.Core.AddOn.AddOnBean;
import me.MiniDigger.Core.AddOn.AddOnClassLoader;
import me.MiniDigger.Core.AddOn.AddOnHandler;

public class CoreAddOnHandler implements AddOnHandler {
	
	private final File	                        addOnFile	= new File((Core.getCore().getInstance()).getDataFolder(), "DO_NOT_EDIT");
	private JSONArray	                        addOns;
	private ArrayList<AddOn>	                active;
	
	private final Map<String, Class<?>>	        classes	  = new HashMap<String, Class<?>>();
	private final Map<String, AddOnClassLoader>	loaders	  = new LinkedHashMap<String, AddOnClassLoader>();
	
	@Override
	public void load() {
		active = new ArrayList<>();
		
		if (!addOnFile.exists()) {
			try {
				addOnFile.createNewFile();
			} catch (final IOException e) {
				e.printStackTrace();
			}
		}
		
		BufferedReader rd;
		String line;
		String result = "";
		try {
			rd = new BufferedReader(new InputStreamReader(new FileInputStream(addOnFile)));
			while ((line = rd.readLine()) != null) {
				result += line;
			}
			rd.close();
		} catch (final Exception ex) {
			
		}
		
		final JSONParser parser = new JSONParser();
		try {
			addOns = (JSONArray) parser.parse(result);
		} catch (final ParseException e) {
			addOns = new JSONArray();
			Core.getCore().getInstance().error("AddOn file currupted! Creating new one...");
		}
	}
	
	@Override
	public void enableAddOns() {
		for (final AddOnBean b : getInstalledBeans()) {
			AddOnBean bean = new CoreAddOnBean();
			bean.setName(b.getName());
			bean.setVersion(b.getVersion());
			bean = Core.getCore().getRESTHandler().requestInfos(bean, true);
			
			Core.getCore().getInstance().info("Loading Addon " + b.getName() + " v" + bean.getVersion() + " by " + bean.getAuthor());
			
			if (bean.getVersion() == null || bean.getAuthor() == null || bean.getPackage() == null) {
				Core.getCore().getInstance().error("Error while loading Addon " + b.getName() + ": Request returned null!");
				return;
			}
			
			CoreAddOnClassLoader loader;
			try {
				final URL url = Core.getCore().getRESTHandler().showFile(bean.getName(), bean.getVersion());
				// System.out.println(url.toExternalForm());
				loader = new CoreAddOnClassLoader(getClass().getClassLoader(), bean.getPackage(), url);
			} catch (final Exception e) {
				Core.getCore().getInstance().error("Could not load AddOn " + bean.getName() + " v" + bean.getVersion() + " by " + bean.getAuthor() + " (CL):");
				e.printStackTrace();
				continue;
			}
			
			loaders.put(bean.getName(), loader);
			loader.getAddOn().load(bean);
			active.add(loader.getAddOn());
		}
		
		for (final AddOn addon : active) {
			try {
				Core.getCore().getInstance().info("Enabling Addon " + addon.getName() + " v" + addon.getBean().getVersion() + " by " + addon.getBean().getAuthor());
				addon.enable();
				addon.checkUpdate();
			} catch (final Exception ex) {
				Core.getCore().getInstance()
				        .error("Could not enable AddOn " + addon.getName() + " v" + addon.getBean().getVersion() + " by " + addon.getBean().getAuthor() + ":");
			}
		}
		
		Core.getCore().getCommandHandler().registerHelp();
	}
	
	@Override
	public void disableAddOns() {
		for (final AddOn addon : active) {
			Core.getCore().getInstance().info("Disabling Addon " + addon.getName() + " v" + addon.getBean().getVersion() + " by " + addon.getBean().getAuthor());
			addon.disable();
			final AddOnClassLoader loader = loaders.get(addon.getName());
			loaders.remove(addon.getName());
			
			for (final String s : loader.getClasses()) {
				removeClass(s);
			}
		}
		
		Core.getCore().getCommandHandler().registerHelp();
	}
	
	@Override
	public boolean update(final String name) {
		for (final AddOnBean addon : getInstalledBeans()) {
			if (addon.getName().equalsIgnoreCase(name)) {
				final AddOnBean bean = Core.getCore().getRESTHandler().checkUpdate(addon);
				if (bean.getVersion() == null) {
					return false;
				} else {
					listAsUnInstalled(name);
					listAsInstalled(bean);
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public List<String> getInstalledNames() {
		final List<String> result = new ArrayList<>();
		
		if (addOns != null) {
			for (final Object obj : addOns) {
				final AddOnBean bean = new CoreAddOnBean((JSONObject) obj);
				result.add(bean.getName());
			}
		}
		
		return result;
	}
	
	@Override
	public List<AddOnBean> getInstalledBeans() {
		final List<AddOnBean> result = new ArrayList<>();
		
		if (addOns != null) {
			for (final Object obj : addOns) {
				final AddOnBean bean = new CoreAddOnBean((JSONObject) obj);
				result.add(bean);
			}
		}
		
		return result;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void listAsInstalled(final AddOnBean bean) {
		addOns.add(bean.toJson());
		
		save();
	}
	
	@Override
	public void listAsUnInstalled(final String name) {
		final ArrayList<JSONObject> toRemove = new ArrayList<>();
		for (final Object obj : addOns.toArray()) {
			final AddOnBean bean = new CoreAddOnBean((JSONObject) obj);
			if (bean.getName().equalsIgnoreCase(name)) {
				toRemove.add((JSONObject) obj);
			}
		}
		
		for (final JSONObject obj : toRemove) {
			addOns.remove(obj);
		}
		
		save();
	}
	
	private void save() {
		addOns.trimToSize();
		PrintWriter writer;
		try {
			writer = new PrintWriter(addOnFile);
			writer.print(addOns.toJSONString());
			writer.close();
		} catch (final FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Class<?> getClassByName(final String name) {
		Class<?> cachedClass = classes.get(name);
		
		if (cachedClass != null) {
			return cachedClass;
		} else {
			for (final String current : loaders.keySet()) {
				final AddOnClassLoader loader = loaders.get(current);
				
				try {
					cachedClass = loader.findClass(name, false);
				} catch (final ClassNotFoundException e) {}
				if (cachedClass != null) {
					return cachedClass;
				}
			}
		}
		return null;
	}
	
	@Override
	public void setClass(final String name, final Class<?> clazz) {
		if (!classes.containsKey(name)) {
			classes.put(name, clazz);
		}
	}
	
	public void removeClass(final String name) {
		@SuppressWarnings("unused") Class<?> clazz = classes.remove(name);
		clazz = null; // Bye!
	}
}
