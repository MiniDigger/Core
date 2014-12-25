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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.AddOn.AddOn;
import me.MiniDigger.Core.AddOn.AddOnBean;
import me.MiniDigger.Core.Chat.ChatChannel;
import me.MiniDigger.Core.Lang.LangKeyType;
import me.MiniDigger.Core.Lang.LogLevel;
import me.MiniDigger.Core.Packet.Packet;

import me.MiniDigger.CraftCore.Lang._;

public class CoreAddOn implements AddOn {
	
	private String	          name;
	private File	          dataFolder;
	private FileConfiguration	config;
	private File	          configFile;
	private AddOnBean	      bean;
	
	@Override
	public void enable() {
		
	}
	
	@Override
	public void checkUpdate() {
		final AddOnBean bean = Core.getCore().getRESTHandler().checkUpdate(this.bean);
		if (bean.getVersion() != null) {
			_.log(LogLevel.INFO, LangKeyType.AddOn.FOUND_UPDATE, name, this.bean.getVersion(), bean.getVersion());
		}
	}
	
	@Override
	public void disable() {
		
	}
	
	public List<ChatChannel> registerChatChannel() {
		return new ArrayList<>();
	}
	
	public List<Object> registerCommandHandler() {
		return new ArrayList<>();
	}
	
	public List<Packet> registerPacket() {
		return new ArrayList<>();
	}
	
	@Override
	public void load(final AddOnBean bean) {
		name = bean.getName();
		this.bean = bean;
		loadConfig();
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public File getDataFolder() {
		return dataFolder;
	}
	
	@Override
	public FileConfiguration getConfig() {
		return config;
	}
	
	@Override
	public void saveConfig() {
		try {
			config.save(configFile);
		} catch (final IOException e) {
			_.log(LogLevel.ERROR, LangKeyType.AddOn.ERROR_SAVE_CONFIG, name);
			e.printStackTrace();
		}
	}
	
	@Override
	public void loadConfig() {
		dataFolder = new File((Core.getCore().getInstance()).getDataFolder(), name);
		if (!dataFolder.exists()) {
			_.log(LogLevel.INFO, LangKeyType.AddOn.CREATE_FOLDER, name);
			dataFolder.mkdir();
		}
		
		configFile = new File(dataFolder, name + ".yml");
		if (!configFile.exists()) {
			_.log(LogLevel.INFO, LangKeyType.AddOn.CREATE_CONFIG, name);
			try {
				configFile.createNewFile();
			} catch (final IOException e) {
				_.log(LogLevel.ERROR, LangKeyType.AddOn.ERROR_CREATE_CONFIG, name);
				e.printStackTrace();
			}
		}
		
		config = YamlConfiguration.loadConfiguration(configFile);
	}
	
	@Override
	public AddOnBean getBean() {
		return bean;
	}
}
