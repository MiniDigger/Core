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
 */
package me.MiniDigger.CraftCore.AddOn;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.AddOn.AddOn;
import me.MiniDigger.Core.AddOn.AddOnBean;
import me.MiniDigger.Core.AddOn.AddOnHandler;
import me.MiniDigger.CraftCore.CoreMain;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CoreAddOnHandler implements AddOnHandler {
	
	private final File	     addOnFile	= new File(((CoreMain) Core.getCore().getInstance()).getDataFolder(), "DO_NOT_EDIT");
	private JSONArray	     addOns;
	private ArrayList<AddOn>	active;
	
	@Override
	public void load() {
		active = new ArrayList<>();
		
		if (!addOnFile.exists()) {
			try {
				addOnFile.createNewFile();
			} catch (IOException e) {
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
		for (String name : getInstalledNames()) {
			// TODO Version support
			Core.getCore().getInstance().info("Loading Addon " + name);
			Class<?> c = Core.getCore().getClassHandler().getLoader().load(Core.getCore().getRESTHandler().showFile(name), name);
			if (c != null) {
				try {
					AddOn addon = (AddOn) c.newInstance();
					addon.load();
					Core.getCore().getInstance().info("Loaded Addon " + name + " v" + addon.getBean().getVersion() + " by " + addon.getBean().getAuthor());
					active.add(addon);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		for (AddOn addon : active) {
			Core.getCore().getInstance().info("Enabling Addon " + addon.getName() + " v" + addon.getBean().getVersion() + " by " + addon.getBean().getAuthor());
			addon.enable();
		}
	}
	
	@Override
	public void disableAddOns() {
		for (AddOn addon : active) {
			Core.getCore().getInstance().info("Disabling Addon " + addon.getName() + " v" + addon.getBean().getVersion() + " by " + addon.getBean().getAuthor());
			addon.disable();
		}
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
}
