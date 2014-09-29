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
package me.MiniDigger.CraftCore.License;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Licence.LicenseHandler;
import me.MiniDigger.CraftCore.CoreMain;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class CoreLicenseHandler implements LicenseHandler {
	
	@SuppressWarnings("deprecation") private final String	licence	   = ((CoreMain) CoreMain.getCore().getInstance()).getConfig().getString("licence");
	private final String	                              sessionToken	= generateToken();
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean register() {
		if (licence == null) {
			CoreMain.getCore().getInstance().error("Du hast keine Lizenz angegeben!");
			CoreMain.getCore().getInstance()
			        .error("Trage deine Lizenz in " + ((CoreMain) Core.getCore().getInstance()).getDataFolder().getAbsolutePath() + "/config.yml unter licence: ein");
			CoreMain.getCore().getCommonMethods().killPlugin();
			return false;
		}
		
		final JSONObject result = CoreMain.getCore().getRESTHandler().registerLicence(licence, sessionToken);
		final String tokenR = (String) result.get("result");
		return tokenR.equalsIgnoreCase(sessionToken);
	}
	
	@Override
	public void performCheckAsync() {
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
		final String token = generateToken();
		
		final String s = CoreMain.getCore().getRESTHandler().checkLicence(licence, token, sessionToken);
		
		if (s == null) {
			CoreMain.getCore().getCommonMethods().killPlugin();
			return;
		}
		
		JSONObject result;
		try {
			result = (JSONObject) new JSONParser().parse(s);
		} catch (final Exception ex) {
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
	
	@SuppressWarnings("deprecation")
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
		// System.out.println("TK: " + token);
		// System.out.println("TR: " + tokenR);
		// System.out.println("Tr: " + tokenR.replace("MiniDiggerTheBoss", ""));
		for (int i = 0; i < tokenR.length(); i++) {
			read += tokenR.charAt(i);
			if (!token.startsWith(read) && !keyEnd) {
				key += tokenR.charAt(i);
				if (key.equals("MiniDiggerTheBoss")) {
					keyEnd = true;
				}
				if (!"MiniDiggerTheBoss".startsWith(key)) {
					// System.out.println("SOMETHING IS WRONG WITH " + key);
					if (key.equalsIgnoreCase("i")) {
						key = "Mi";
						readOld = Core.getCore().getStringUtil().replaceLast(readOld, "M", "");
						// System.out.println("FIXED");
					} else {
						// System.out.println("COULD NOT FIX");
						return false;
					}
				}
			} else {
				readOld += tokenR.charAt(i);
			}
			// System.out.println("read: " + read);
			// System.out.println("readOld: " + readOld);
			// System.out.println("key: " + key);
		}
		
		// System.out.println("END");
		// System.out.println("Key: " + key);
		// System.out.println("readold: " + readOld);
		// System.out.println("read: " + read);
		// System.out.println("token: " + token);
		if (key.equals("MiniDiggerTheBoss")) {
			// System.out.println("key right");
			if (readOld.equals(token)) {
				// System.out.println("token right");
				return true;
			}
		}
		// System.out.println("FAILED!");
		// System.out.println("Key: " + key);
		// System.out.println("readold: " + readOld);
		// System.out.println("read: " + read);
		// System.out.println("token: " + token);
		// System.out.println("TK: " + token);
		// System.out.println("TR: " + tokenR);
		// System.out.println("Tr: " + tokenR.replace("MiniDiggerTheBoss", ""));
		return false;
	}
}
