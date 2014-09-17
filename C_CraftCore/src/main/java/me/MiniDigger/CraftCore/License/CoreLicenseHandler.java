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
		
		JSONObject result = CoreMain.getCore().getRESTHandler().registerLicence(licence, sessionToken);
		String tokenR = (String) result.get("result");
		return tokenR.equalsIgnoreCase(sessionToken);
	}
	
	@Override
	public void performCheckAsync() {
		Thread thread = new Thread(new Runnable() {
			
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
		String token = generateToken();
		
		JSONObject result = CoreMain.getCore().getRESTHandler().checkLicence(licence, CoreMain.getCore().getBaseUtil().encode(token), sessionToken);
		
		if (result == null) {
			CoreMain.getCore().getCommonMethods().killPlugin();
			return;
		}
		
		String tokenR = (String) result.get("result");
		if (!checkToken(tokenR, token)) {
			CoreMain.getCore().getCommonMethods().killPlugin();
		}
	}
	
	@Override
	public void performCheck(boolean sync) {
		if (sync) {
			performCheckSync();
		} else {
			performCheckAsync();
		}
	}
	
	@SuppressWarnings("deprecation")
	private String generateToken() {
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		String token = "";
		int rnd = CoreMain.getCore().getRandomUtil().nextInt(40) + 15;
		for (int i = 0; i < rnd; i++) {
			token += chars.charAt(CoreMain.getCore().getRandomUtil().nextInt(chars.length() - 1));
		}
		return token;
	}
	
	@SuppressWarnings("deprecation")
	private boolean checkToken(String tokenR, String token) {
		tokenR = CoreMain.getCore().getBaseUtil().decode(tokenR);
		String read = "";
		String readOld = "";
		String key = "";
		boolean keyEnd = false;
		for (int i = 0; i < tokenR.length(); i++) {
			read += tokenR.charAt(i);
			if (!token.startsWith(read) && !keyEnd) {
				key += tokenR.charAt(i);
				if (key.equals("MiniDiggerTheBoss")) {
					keyEnd = true;
				}
				if (!"MiniDiggerTheBoss".startsWith(key)) {
					return false;
				}
			} else {
				readOld += tokenR.charAt(i);
			}
		}
		if (key.equals("MiniDiggerTheBoss") && readOld.equals(token)) {
			return true;
		} else {
			return false;
		}
	}
}
