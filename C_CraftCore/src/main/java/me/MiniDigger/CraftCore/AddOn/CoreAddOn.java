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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.AddOn.AddOn;
import me.MiniDigger.Core.AddOn.AddOnBean;
import me.MiniDigger.Core.Chat.ChatChannel;
import me.MiniDigger.Core.Packet.Packet;
import me.MiniDigger.CraftCore.CoreMain;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

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
	public void load() {
		loadConfig();
		bean = new CoreAddOnBean();
		bean.setName(name);
		bean = Core.getCore().getRESTHandler().requestInfos(bean, false);
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
			Core.getCore().getInstance().error("Error while saving ConfigFile for AddOn " + name);
			e.printStackTrace();
		}
	}
	
	@Override
	public void loadConfig() {
		dataFolder = new File(((CoreMain) Core.getCore().getInstance()).getDataFolder(), name);
		if (!dataFolder.exists()) {
			Core.getCore().getInstance().info("Creating DataFolder for AddOn " + name);
			dataFolder.mkdir();
		}
		
		configFile = new File(dataFolder, name + ".yml");
		if (!configFile.exists()) {
			Core.getCore().getInstance().info("Creating ConfigFile for AddOn " + name);
			try {
				configFile.createNewFile();
			} catch (final IOException e) {
				Core.getCore().getInstance().error("Error while creating ConfigFile for AddOn " + name);
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
