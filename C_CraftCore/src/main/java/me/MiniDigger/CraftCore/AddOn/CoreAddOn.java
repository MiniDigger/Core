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
import me.MiniDigger.Core.Chat.ChatChannel;
import me.MiniDigger.Core.Packet.Packet;
import me.MiniDigger.CraftCore.CoreMain;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.libs.jline.internal.InputStreamReader;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.PluginDescriptionFile;

public class CoreAddOn implements AddOn {
	
	private String	              name;
	private PluginDescriptionFile	description;
	private File	              dataFolder;
	private FileConfiguration	  config;
	private File	              configFile;
	
	public void enable() {
		
	}
	
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
	
	public void load() {
		loadConfig();
		try {
			description = new PluginDescriptionFile(new InputStreamReader(getClass().getResourceAsStream("addon.info")));
		} catch (InvalidDescriptionException e) {
			Core.getCore().getInstance().error("Could not load description for AddOn " + name);
			e.printStackTrace();
		}
	}
	
	public String getName() {
		return name;
	}
	
	public File getDataFolder() {
		return dataFolder;
	}
	
	public FileConfiguration getConfig() {
		return config;
	}
	
	public PluginDescriptionFile getDescription() {
		return description;
	}
	
	public void saveConfig() {
		try {
			config.save(configFile);
		} catch (IOException e) {
			Core.getCore().getInstance().error("Error while saving ConfigFile for AddOn " + name);
			e.printStackTrace();
		}
	}
	
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
			} catch (IOException e) {
				Core.getCore().getInstance().error("Error while creating ConfigFile for AddOn " + name);
				e.printStackTrace();
			}
		}
		
		config = YamlConfiguration.loadConfiguration(configFile);
	}
}
