package me.MiniDigger.CraftCore.Menu;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Menu.ItemBarMenu;
import me.MiniDigger.Core.Menu.ItemBarMenu.ClickHandler;
import me.MiniDigger.Core.Menu.MenuHandler;
import me.MiniDigger.Core.Prefix.Prefix;
import me.MiniDigger.Core.User.User;

import me.MiniDigger.CraftCore.Item.CoreItemBuilder;

public class CoreMenuHandler implements MenuHandler {
	
	private FileConfiguration	     config;
	private File	                 configFile	= new File(Core.getCore().getInstance().getDataFolder(), "menu.yml");
	
	private Map<String, ItemBarMenu>	menus	= new HashMap<String, ItemBarMenu>();
	private Map<UUID, String>	     selected	= new HashMap<UUID, String>();
	
	@Override
	public void load() {
		config = YamlConfiguration.loadConfiguration(configFile);
		for (final String s : config.getKeys(false)) {
			try {
				ItemBarMenu m = new CoreItemBarMenu(s);
				for (String key : config.getConfigurationSection(s).getKeys(false)) {
					int i = Integer.parseInt(key);
					Material mat = Material.valueOf(config.getString(s + "." + i + ".mat"));
					CoreItemBuilder ib = new CoreItemBuilder(mat);
					
					try {
						ib.name(config.getString(s + "." + i + ".name").replaceAll("&", "§"));
					} catch (Exception ex) {}
					
					try {
						int data = Integer.parseInt(config.getString(s + "." + i + ".data"));
						ib.data(data).durability(data);
					} catch (Exception ex2) {}
					
					for (String ss : config.getStringList(s + "." + i + ".lore")) {
						try {
							ib.lore(ss.replaceAll("&", "§"));
						} catch (Exception ex) {}
					}
					
					m.setIcon(i, ib.build());
					
					final String msg = config.getString(s + "." + i + ".action.msg");
					final String open = config.getString(s + "." + i + ".action.open");
					final String pCmd = config.getString(s + "." + i + ".action.pCmd");
					final String cCmd = config.getString(s + "." + i + ".action.cCmd");
					
					m.setAction(i, new ClickHandler() {
						
						@Override
						public void click(ItemBarMenu m, ItemStack is, User u) {
							if (msg != null) {
								u.sendMessage(Prefix.API.getPrefix().then(msg));
							}
							if (open != null) {
								openMenu(u, open);
							}
							if (pCmd != null) {
								Bukkit.getServer().dispatchCommand(u.getPlayer(), pCmd.replaceAll("%p%", u.getRealName()));
							}
							if (cCmd != null) {
								Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), cCmd.replaceAll("%p%", u.getRealName()));
							}
						}
					});
				}
				menus.put(s, m);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	@Override
	public void openMenu(User u, String name) {
		closeMenu(u);
		selected.remove(u.getUUID());
		selected.put(u.getUUID(), name);
		getMenu(name).open(u);
	}
	
	@Override
	public void closeMenu(User u) {
		try {
			selected.remove(u.getUUID());
			getMenu(selected.get(u.getUUID())).close(u);
		} catch (Exception ex) {
		}
	}
	
	@Override
	public ItemBarMenu getMenu(String name) {
		return menus.get(name);
	}
	
	@Override
	public ItemBarMenu getMenu(UUID id) {
		return getMenu(selected.get(id));
	}
}
