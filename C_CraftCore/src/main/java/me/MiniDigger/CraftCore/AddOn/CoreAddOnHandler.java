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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.AddOn.AddOnBean;
import me.MiniDigger.Core.AddOn.AddOnHandler;
import me.MiniDigger.CraftCore.CoreMain;

public class CoreAddOnHandler implements AddOnHandler {
	
	private File	  addOnFile	= new File(((CoreMain) Core.getCore().getInstance()).getDataFolder(), "DO_NOT_EDIT");
	private JSONArray	addOns;
	@Override
	public void load() {
		BufferedReader rd;
		String line;
		String result = "";
		try {
			rd = new BufferedReader(new InputStreamReader(new FileInputStream(addOnFile)));
			while ((line = rd.readLine()) != null) {
				result += line;
			}
			rd.close();
		} catch (Exception ex) {
			
		}
		
		JSONParser parser = new JSONParser();
		try {
			addOns = (JSONArray) parser.parse(result);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	@Override
	public List<String> getInstalledNames() {
		List<String> result = new ArrayList<>();
		
		if (addOns != null) {
			for (Object obj : addOns) {
				AddOnBean bean = new CoreAddOnBean((JSONObject) obj);
				result.add(bean.getName());
			}
		}
		
		return result;
	}
	@Override
	public List<AddOnBean> getInstalledBeans() {
		List<AddOnBean> result = new ArrayList<>();
		
		if (addOns != null) {
			for (Object obj : addOns) {
				AddOnBean bean = new CoreAddOnBean((JSONObject) obj);
				result.add(bean);
			}
		}
		
		return result;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void listAsInstalled(AddOnBean bean) {
		addOns.add(bean);
	}
	@Override
	public void listAsUnInstalled(String name) {
		ArrayList<JSONObject> toRemove = new ArrayList<>();
		for(Object obj : addOns.toArray()){
			AddOnBean bean = new CoreAddOnBean((JSONObject) obj);
			if(bean.getName().equalsIgnoreCase(name)){
				toRemove.add((JSONObject) obj);
			}
		}
		
		for(JSONObject obj : toRemove){
			addOns.remove(obj);
		}
		
		save();
	}
	
	public void save() {
		addOns.trimToSize();
		PrintWriter writer;
		try {
			writer = new PrintWriter(addOnFile);
			writer.print(addOns.toJSONString());
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
