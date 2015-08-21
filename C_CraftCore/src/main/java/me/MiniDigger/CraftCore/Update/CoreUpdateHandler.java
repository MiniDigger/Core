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
package me.MiniDigger.CraftCore.Update;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.bukkit.Bukkit;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Update.UpdateHandler;
import me.MiniDigger.Core.Update.UpdateType;

public class CoreUpdateHandler implements UpdateHandler {
	
	private static final int	BYTE_SIZE	 = 1024;
	private static final String	JAR_URL;
	private static final String	VERSIONS_URL	= "http://game-repo.minidigger.me/TheCore/version.txt";
	
	private UpdateType	        type;
	
	static {
		if (Core.getCore().getInstance().getConfig().getBoolean("debug")) {
			JAR_URL = "http://api.minidigger.me/addons-dev/Core.jar";
		} else {
			JAR_URL = "http://api.minidigger.me/addons/Core.jar";
		}
	}
	
	public CoreUpdateHandler() {
		type = UpdateType.valueOf((Core.getCore().getInstance()).getConfig().getString("update-type"));
		if (type == null) {
			type = UpdateType.FORCE;
		}
	}
	
	@Override
	public boolean updateCheck() {
		// Core.getCore().getInstance().debug(getVersion().getRaw());
		if (getLatestVersion().isNewer(getVersion(), type)) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					update();
				}
				
			}).start();
			return true;
		}
		// Core.getCore().getInstance().debug("not newer!");
		return false;
	}
	
	private PluginVersion getVersion() {
		return new CorePluginVersion((Core.getCore().getInstance()).getDescription().getVersion());
	}
	
	private PluginVersion getLatestVersion() {
		try {
			final URL url = new URL(VERSIONS_URL);
			final BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String inputLine;
			
			while ((inputLine = in.readLine()) != null) {
				return new CorePluginVersion(inputLine);
			}
			
			in.close();
		} catch (final IOException e) {
			Core.getCore().getInstance().error("Could not reach repo!");
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	@Override
	public void update() {
		saveFile(new File((Core.getCore().getInstance()).getDataFolder().getParent(), Bukkit.getServer().getUpdateFolder()), "Core.jar", JAR_URL);
	}
	
	private void saveFile(final File folder, final String file, final String link) {
		if (!folder.exists()) {
			folder.mkdir();
		}
		BufferedInputStream in = null;
		FileOutputStream fout = null;
		try {
			// Download the file
			final URL url = new URL(link);
			final int fileLength = url.openConnection().getContentLength();
			in = new BufferedInputStream(url.openStream());
			fout = new FileOutputStream(folder.getAbsolutePath() + File.separator + file);
			
			final byte[] data = new byte[BYTE_SIZE];
			int count;
			
			long downloaded = 0;
			while ((count = in.read(data, 0, BYTE_SIZE)) != -1) {
				downloaded += count;
				fout.write(data, 0, count);
				final int percent = (int) ((downloaded * 100) / fileLength);
				if (((percent % 10) == 0)) {
					Core.getCore().getInstance().info("Downloading update: " + percent + "% of " + fileLength + " bytes.");
				}
			}
			
			Core.getCore().getInstance().info("Finished updating.");
			if (in != null) {
				in.close();
			}
			if (fout != null) {
				fout.close();
			}
			// Bukkit.getServer().shutdown();
		} catch (final Exception ex) {
			Core.getCore().getInstance().error("The auto-updater tried to download a new update, but was unsuccessful. " + ex.getMessage());
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (fout != null) {
					fout.close();
				}
			} catch (final Exception ex) {}
		}
	}
}
