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
package me.MiniDigger.CraftCore.License;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Licence.LicenseHandler;

import me.MiniDigger.CraftCore.CoreMain;
import me.MiniDigger.CraftCore.Util.CoreShutdownUtil;

public class CoreLicenseHandler implements LicenseHandler {

	@SuppressWarnings("deprecation")
	private final String	licence			= (CoreMain.getCore().getInstance()).getConfig().getString("licence");
	private final String	sessionToken	= "SESSION" + Core.getCore().getRandomUtil().nextInt(10000)/*
																										 * generateToken
																										 * (
																										 * )
																										 */;

	private boolean failed = false;

	@SuppressWarnings("deprecation")
	@Override
	public boolean register() {
		if (licence == null) {
			CoreMain.getCore().getInstance().error("Du hast keine Lizenz angegeben!");
			CoreMain.getCore().getInstance().error("Trage deine Lizenz in " + (CoreMain.getCore().getInstance()).getDataFolder().getAbsolutePath() + "/config.yml unter licence: ein");
			CoreMain.getCore().getCommonMethods().killPlugin();
			return false;
		}

		final JSONObject result = CoreMain.getCore().getRESTHandler().registerLicence(licence, sessionToken);
		final String tokenR = (String) result.get("result");
		return tokenR.equalsIgnoreCase(sessionToken);
	}

	@Override
	public void performCheckAsync() {
		if (CoreShutdownUtil.isShuttingDown()) {
			return;
		}

		final Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				performCheckSync();
			}
		});
		thread.setName("CoreLicenceChecker");
		thread.start();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void performCheckSync() {
		final String token = "TOKEN" + Core.getCore().getRandomUtil().nextInt(10000)/*
																					 * generateToken
																					 * (
																					 * )
																					 */;

		final String s = CoreMain.getCore().getRESTHandler().checkLicence(licence, token, sessionToken);

		if (s == null) {
			if (!failed) {
				failed = true;
				CoreMain.getCore().getInstance().error("Licence check failed! Try again!");
				performCheckAsync();
			}
			CoreMain.getCore().getCommonMethods().killPlugin();
			return;
		}

		JSONObject result;
		try {
			result = (JSONObject) new JSONParser().parse(s);
		}
		catch (final Exception ex) {
			if (!checkToken(s, token)) {
				CoreMain.getCore().getInstance().error(s);
				CoreMain.getCore().getCommonMethods().killPlugin();
				return;
			} else {
				return;
			}
		}

		final String tokenR = (String) result.get("result");
		if (!checkToken(tokenR, token)) {
			if (!failed) {
				failed = true;
				CoreMain.getCore().getInstance().error("Licence check failed! Try again!");
				performCheckAsync();
			}
			CoreMain.getCore().getCommonMethods().killPlugin();
		}
	}

	@Override
	public void performCheck(final boolean sync) {
		if (sync) {
			performCheckSync();
		} else {
			performCheckAsync();
		}
	}

	@SuppressWarnings({ "deprecation", "unused" })
	private String generateToken() {
		final String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		String token = "";
		final int rnd = CoreMain.getCore().getRandomUtil().nextInt(40) + 15;
		for (int i = 0; i < rnd; i++) {
			token += chars.charAt(CoreMain.getCore().getRandomUtil().nextInt(chars.length() - 1));
		}
		return token;
	}

	@SuppressWarnings("deprecation")
	private boolean checkToken(String tokenR, final String token) {
		tokenR = CoreMain.getCore().getBaseUtil().decode(tokenR);
		String read = "";
		String readOld = "";
		String key = "";
		boolean keyEnd = false;
		// Core.getCore().getInstance().debug("TK: " + token);
		// Core.getCore().getInstance().debug("TR: " + tokenR);
		// Core.getCore().getInstance().debug("Tr: " +
		// tokenR.replace("MiniDiggerTheBoss", ""));
		for (int i = 0; i < tokenR.length(); i++) {
			read += tokenR.charAt(i);
			if (!token.startsWith(read) && !keyEnd) {
				key += tokenR.charAt(i);
				if (key.equals("MiniDiggerTheBoss")) {
					keyEnd = true;
				}
				if (!"MiniDiggerTheBoss".startsWith(key)) {
					// Core.getCore().getInstance().debug("SOMETHING IS WRONG
					// WITH " + key);
					if (key.equalsIgnoreCase("i")) {
						key = "Mi";
						readOld = CoreMain.getCore().getStringUtil().replaceLast(readOld, "M", "");
						// Core.getCore().getInstance().debug("FIXED");
					} else {
						// Core.getCore().getInstance().debug("COULD NOT FIX");
						return false;
					}
				}
			} else {
				readOld += tokenR.charAt(i);
			}
			// Core.getCore().getInstance().debug("read: " + read);
			// Core.getCore().getInstance().debug("readOld: " + readOld);
			// Core.getCore().getInstance().debug("key: " + key);
		}

		// Core.getCore().getInstance().debug("END");
		// Core.getCore().getInstance().debug("Key: " + key);
		// Core.getCore().getInstance().debug("readold: " + readOld);
		// Core.getCore().getInstance().debug("read: " + read);
		// Core.getCore().getInstance().debug("token: " + token);
		if (key.equals("MiniDiggerTheBoss")) {
			// Core.getCore().getInstance().debug("key right");
			if (readOld.equals(token)) {
				// Core.getCore().getInstance().debug("token right");
				return true;
			}
		}
		// Core.getCore().getInstance().debug("FAILED!");
		// Core.getCore().getInstance().debug("Key: " + key);
		// Core.getCore().getInstance().debug("readold: " + readOld);
		// Core.getCore().getInstance().debug("read: " + read);
		// Core.getCore().getInstance().debug("token: " + token);
		// Core.getCore().getInstance().debug("TK: " + token);
		// Core.getCore().getInstance().debug("TR: " + tokenR);
		// Core.getCore().getInstance().debug("Tr: " +
		// tokenR.replace("MiniDiggerTheBoss", ""));
		return false;
	}
}
