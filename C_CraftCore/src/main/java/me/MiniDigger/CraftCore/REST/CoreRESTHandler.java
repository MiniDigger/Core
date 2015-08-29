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
package me.MiniDigger.CraftCore.REST;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.AddOn.AddOnBean;
import me.MiniDigger.Core.REST.RESTHandler;
import me.MiniDigger.Core.Update.UpdateType;
import me.MiniDigger.CraftCore.AddOn.CoreAddOnBean;
import me.MiniDigger.CraftCore.Update.CorePluginVersion;

public class CoreRESTHandler implements RESTHandler {

	private static final String		BASE_URL	= "http://api.minidigger.me/";
	private final static boolean	debug		= Core.getCore().getInstance().getConfig().getBoolean("debug");

	public CoreRESTHandler() {

	}

	@Override
	public URL showFile(final String name, final String version) {
		URL classUrl;
		try {
			classUrl = new URL(BASE_URL + "/v1/addOns/showFile/" + name + "/" + version + "/" + debug);
			return classUrl;
		}
		catch (final Exception ex) {
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
		}
		catch (final Exception e) {
			e.printStackTrace();
		}

		final JSONParser parser = new JSONParser();
		try {
			return (JSONObject) parser.parse(result);
		}
		catch (final Exception e) {
			Core.getCore().getInstance().debug("RESULT: " + result);
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String checkLicence(final String licence, final String token, final String sessionToken) {
		// Core.getCore().getInstance().debug("");
		// Core.getCore().getInstance().debug("=======================================");
		// Core.getCore().getInstance().debug("PRÜFE LICENCE: " + licence);
		// Core.getCore().getInstance().debug("TOKEN: " + token);
		// Core.getCore().getInstance().debug("SESSIONTOKEN: " + sessionToken);
		final JSONObject response = ((CoreRESTHandler) Core.getCore().getRESTHandler()).get("v1/licence/isvalid/" + licence + "/" + token + "/" + sessionToken);

		if (!checkResponse(response, "Could not request licenceCheck")) {
			return null;
		}
		// Core.getCore().getInstance().debug(response.toJSONString());
		// Core.getCore().getInstance().debug("=======================================");
		// Core.getCore().getInstance().debug("");

		return (String) response.get("result");
	}

	@Override
	public JSONObject registerLicence(final String licence, final String sessionToken) {
		final JSONObject response = ((CoreRESTHandler) Core.getCore().getRESTHandler()).get("v1/licence/register/" + licence + "/" + sessionToken);

		if (!checkResponse(response, "Could not register licence")) {
			return null;
		}

		return response;
	}

	@Override
	public AddOnBean checkUpdate(final AddOnBean bean) {
		final JSONObject response = get("v1/addOns/checkupdate/" + bean.getName() + "/" + bean.getVersion());

		if (!checkResponse(response, "Could not request updateCheck")) {
			bean.setVersion(null);
			return bean;
		}

		if (response.get("success") == null || response.get("success") == Boolean.valueOf(false)) {
			Core.getCore().getInstance().error("Could not request updateCheck");
			try {
				final JSONObject error = (JSONObject) response.get("result");
				final Integer id = (Integer) error.get("id");
				final String message = (String) error.get("message");
				Core.getCore().getInstance().error("Error #" + id.intValue() + ": " + message);
			}
			catch (final Exception ex) {
				Core.getCore().getInstance().error("Error: " + (String) response.get("result"));
			}
			bean.setVersion(null);
			return bean;
		}

		final JSONObject result = (JSONObject) response.get("result");
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

		if (!checkResponse(response, "Could not request infos for AddOn " + bean.getName())) {
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
					}
					catch (final Exception ex) {
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
					if (bean.getPackageDev() == null || bean.getPackageDev().equals("")) {
						bean.setPackageDev(addOn.getPackageDev());
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

		if (!checkResponse(response, "Could not request Infos for all AddOns")) {
			return result;
		}

		final JSONArray addOns = (JSONArray) response.get("result");

		if (addOns == null) {
			return result;
		}

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

		if (!checkResponse(response, "Could not resquest Addons with name " + name)) {
			return result;
		}

		final JSONArray addOns = (JSONArray) response.get("result");

		for (final Object obj : addOns) {
			final AddOnBean addOn = new CoreAddOnBean((JSONObject) obj);
			result.add(addOn);
		}

		return result;
	}

	private boolean checkResponse(final JSONObject response, final String msg) {
		if (response == null || response.get("success") == null || response.get("success") == Boolean.valueOf(false)) {
			Core.getCore().getInstance().error(msg);
			try {
				Core.getCore().getInstance().debug("res: " + response.toJSONString());

				final JSONObject error = (JSONObject) response.get("result");
				final Integer id = (Integer) error.get("id");
				final String message = (String) error.get("message");
				Core.getCore().getInstance().error("Error #" + id.intValue() + ": " + message);
			}
			catch (final Exception ex) {
				Core.getCore().getInstance().error("Error: " + (String) response.get("result"));
			}
			return false;
		}
		return true;
	}
}
