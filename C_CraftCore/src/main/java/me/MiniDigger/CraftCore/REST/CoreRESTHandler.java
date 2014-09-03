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
package me.MiniDigger.CraftCore.REST;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.AddOn.AddOnBean;
import me.MiniDigger.Core.REST.RESTHandler;
import me.MiniDigger.Core.Update.UpdateType;
import me.MiniDigger.CraftCore.AddOn.CoreAddOnBean;
import me.MiniDigger.CraftCore.Update.CorePluginVersion;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CoreRESTHandler implements RESTHandler {
	
	private static final String	BASE_URL	= "http://core/";
	
	public CoreRESTHandler() {
		
	}
	
	public JSONObject get(String urlToRead) {
		URL url;
		HttpURLConnection conn;
		BufferedReader rd;
		String line;
		String result = "";
		try {
			url = new URL(BASE_URL + urlToRead);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while ((line = rd.readLine()) != null) {
				result += line;
			}
			rd.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JSONParser parser = new JSONParser();
		try {
			return (JSONObject) parser.parse(result);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public AddOnBean requestInfos(AddOnBean bean, boolean exact) {
		JSONObject response = get("v1/addOns/by/name/" + bean.getName());
		
		if (response.get("success") == null || response.get("success") == Boolean.valueOf(false)) {
			Core.getCore().getInstance().error("Could not request infos for AddOn " + bean.getName());
			try {
				JSONObject error = (JSONObject) response.get("result");
				Integer id = (Integer) error.get("id");
				String message = (String) error.get("message");
				Core.getCore().getInstance().error("Error #" + id.intValue() + ": " + message);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return bean;
		}
		
		JSONArray addOns = (JSONArray) response.get("result");
		for (Object obj : addOns) {
			AddOnBean addOn = new CoreAddOnBean((JSONObject) obj);
			if (bean.getName().equalsIgnoreCase(addOn.getName())) {
				if (!exact) {
					try {
						CorePluginVersion vBean = new CorePluginVersion(bean.getVersion());
						CorePluginVersion vAddOn = new CorePluginVersion(addOn.getVersion());
						if (!vBean.isNewerOrEqual(vAddOn, UpdateType.FORCE)) {
							bean = addOn;
						}
					} catch (Exception ex) {
						bean = addOn;
					}
				} else {
					if (bean.getVersion() == null || bean.getVersion().equals("")) {
						bean.setVersion(addOn.getVersion());
					}
					if (bean.getAuthor() == null || bean.getAuthor().equals("")) {
						bean.setAuthor(addOn.getAuthor());
					}
				}
			}
		}
		return bean;
	}
	
	@Override
	public List<AddOnBean> getAllAddOns() {
		List<AddOnBean> result = new ArrayList<>();
		JSONObject response = get("v1/addOns/all");
		
		if (response.get("success") == null || response.get("success") == Boolean.valueOf(false)) {
			Core.getCore().getInstance().error("Could not request Infos for all AddOns");
			try {
				JSONObject error = (JSONObject) response.get("result");
				Integer id = (Integer) error.get("id");
				String message = (String) error.get("message");
				Core.getCore().getInstance().error("Error #" + id.intValue() + ": " + message);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return result;
		}
		
		JSONArray addOns = (JSONArray) response.get("result");
		
		for (Object obj : addOns) {
			AddOnBean addOn = new CoreAddOnBean((JSONObject) obj);
			result.add(addOn);
		}
		
		return result;
	}
	
	@Override
	public List<AddOnBean> getAllAddOnsBy(String name) {
		List<AddOnBean> result = new ArrayList<>();
		JSONObject response = get("v1/addOns/by/author/" + name);
		
		if (response.get("success") == null || response.get("success") == Boolean.valueOf(false)) {
			Core.getCore().getInstance().error("Could not request Infos for all AddOns by " + name);
			try {
				JSONObject error = (JSONObject) response.get("result");
				Integer id = (Integer) error.get("id");
				String message = (String) error.get("message");
				Core.getCore().getInstance().error("Error #" + id.intValue() + ": " + message);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return result;
		}
		
		JSONArray addOns = (JSONArray) response.get("result");
		
		for (Object obj : addOns) {
			AddOnBean addOn = new CoreAddOnBean((JSONObject) obj);
			result.add(addOn);
		}
		
		return result;
	}
}
