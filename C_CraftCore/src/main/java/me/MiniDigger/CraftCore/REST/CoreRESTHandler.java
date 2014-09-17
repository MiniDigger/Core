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
	
	@Override
	public URL showFile(String name, String version) {
		URL classUrl;
		try {
			classUrl = new URL(BASE_URL + "/v1/addOns/showFile/" + name + "/" + version);
			return classUrl;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public JSONObject get(final String urlToRead) {
		URL url;
		HttpURLConnection conn;
		BufferedReader rd;
		String line;
		String result = "";
		try {
			url = new URL(BASE_URL + urlToRead);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("User-Agent", "CoreREST Client v1");
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while ((line = rd.readLine()) != null) {
				result += line;
			}
			rd.close();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		
		final JSONParser parser = new JSONParser();
		try {
			return (JSONObject) parser.parse(result);
		} catch (final ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public JSONObject checkLicence(String licence, String token, String sessionToken) {
		JSONObject response = ((CoreRESTHandler) Core.getCore().getRESTHandler()).get("v1/licence/isvalid/" + licence + "/" + token + "/" + sessionToken);
		if (response.get("success") == null || response.get("success") == Boolean.valueOf(false)) {
			Core.getCore().getInstance().error("Could not request licenceCheck");
			try {
				final JSONObject error = (JSONObject) response.get("result");
				final Integer id = (Integer) error.get("id");
				final String message = (String) error.get("message");
				Core.getCore().getInstance().error("Error #" + id.intValue() + ": " + message);
			} catch (final Exception ex) {
				Core.getCore().getInstance().error("Error: " + (String) response.get("result"));
			}
			return null;
		}
		
		return (JSONObject) response.get("result");
	}
	
	@Override
	public JSONObject registerLicence(String licence, String sessionToken) {
		JSONObject response = ((CoreRESTHandler) Core.getCore().getRESTHandler()).get("v1/licence/register/" + licence + "/" + sessionToken);
		if (response.get("success") == null || response.get("success") == Boolean.valueOf(false)) {
			Core.getCore().getInstance().error("Could not register licence");
			try {
				final JSONObject error = (JSONObject) response.get("result");
				final Integer id = (Integer) error.get("id");
				final String message = (String) error.get("message");
				Core.getCore().getInstance().error("Error #" + id.intValue() + ": " + message);
			} catch (final Exception ex) {
				Core.getCore().getInstance().error("Error: " + (String) response.get("result"));
			}
			return null;
		}
		
		return (JSONObject) response.get("result");
	}
	
	@Override
	public AddOnBean checkUpdate(AddOnBean bean) {
		final JSONObject response = get("v1/addOns/checkupdate/" + bean.getName() + "/" + bean.getVersion());
		
		if (response.get("success") == null || response.get("success") == Boolean.valueOf(false)) {
			Core.getCore().getInstance().error("Could not request updateCheck");
			try {
				final JSONObject error = (JSONObject) response.get("result");
				final Integer id = (Integer) error.get("id");
				final String message = (String) error.get("message");
				Core.getCore().getInstance().error("Error #" + id.intValue() + ": " + message);
			} catch (final Exception ex) {
				Core.getCore().getInstance().error("Error: " + (String) response.get("result"));
			}
			bean.setVersion(null);
			return bean;
		}
		
		JSONObject result = (JSONObject) response.get("result");
		if (result.get("update") == Boolean.TRUE) {
			bean.setVersion((String) result.get("version"));
		} else {
			bean.setVersion(null);
		}
		
		return bean;
	}
	
	@Override
	public AddOnBean requestInfos(AddOnBean bean, final boolean exact) {
		final JSONObject response = get("v1/addOns/by/name/" + bean.getName());
		
		if (response.get("success") == null || response.get("success") == Boolean.valueOf(false)) {
			Core.getCore().getInstance().error("Could not request infos for AddOn " + bean.getName());
			try {
				final JSONObject error = (JSONObject) response.get("result");
				final Integer id = (Integer) error.get("id");
				final String message = (String) error.get("message");
				Core.getCore().getInstance().error("Error #" + id.intValue() + ": " + message);
			} catch (final Exception ex) {
				Core.getCore().getInstance().error("Error: " + (String) response.get("result"));
			}
			return bean;
		}
		
		final JSONArray addOns = (JSONArray) response.get("result");
		for (final Object obj : addOns) {
			final AddOnBean addOn = new CoreAddOnBean((JSONObject) obj);
			if (bean.getName().equalsIgnoreCase(addOn.getName())) {
				if (!exact) {
					try {
						final CorePluginVersion vBean = new CorePluginVersion(bean.getVersion());
						final CorePluginVersion vAddOn = new CorePluginVersion(addOn.getVersion());
						if (!vBean.isNewerOrEqual(vAddOn, UpdateType.FORCE)) {
							bean = addOn;
						}
					} catch (final Exception ex) {
						bean = addOn;
					}
				} else {
					if (bean.getVersion() == null || bean.getVersion().equals("")) {
						bean.setVersion(addOn.getVersion());
					}
					if (bean.getAuthor() == null || bean.getAuthor().equals("")) {
						bean.setAuthor(addOn.getAuthor());
					}
					if (bean.getPackage() == null || bean.getPackage().equals("")) {
						bean.setPackage(addOn.getPackage());
					}
				}
			}
		}
		return bean;
	}
	
	@Override
	public List<AddOnBean> getAllAddOns() {
		final List<AddOnBean> result = new ArrayList<>();
		final JSONObject response = get("v1/addOns/all");
		
		if (response.get("success") == null || response.get("success") == Boolean.valueOf(false)) {
			Core.getCore().getInstance().error("Could not request Infos for all AddOns");
			try {
				final JSONObject error = (JSONObject) response.get("result");
				final Integer id = (Integer) error.get("id");
				final String message = (String) error.get("message");
				Core.getCore().getInstance().error("Error #" + id.intValue() + ": " + message);
			} catch (final Exception ex) {
				Core.getCore().getInstance().error("Error: " + (String) response.get("result"));
			}
			return result;
		}
		
		final JSONArray addOns = (JSONArray) response.get("result");
		
		for (final Object obj : addOns) {
			final AddOnBean addOn = new CoreAddOnBean((JSONObject) obj);
			result.add(addOn);
		}
		
		return result;
	}
	
	@Override
	public List<AddOnBean> getAllAddOnsBy(final String name) {
		final List<AddOnBean> result = new ArrayList<>();
		final JSONObject response = get("v1/addOns/by/author/" + name);
		
		if (response.get("success") == null || response.get("success") == Boolean.valueOf(false)) {
			Core.getCore().getInstance().error("Could not request Infos for all AddOns by " + name);
			try {
				final JSONObject error = (JSONObject) response.get("result");
				final Integer id = (Integer) error.get("id");
				final String message = (String) error.get("message");
				Core.getCore().getInstance().error("Error #" + id.intValue() + ": " + message);
			} catch (final Exception ex) {
				Core.getCore().getInstance().error("Error: " + (String) response.get("result"));
			}
			return result;
		}
		
		final JSONArray addOns = (JSONArray) response.get("result");
		
		for (final Object obj : addOns) {
			final AddOnBean addOn = new CoreAddOnBean((JSONObject) obj);
			result.add(addOn);
		}
		
		return result;
	}
}
