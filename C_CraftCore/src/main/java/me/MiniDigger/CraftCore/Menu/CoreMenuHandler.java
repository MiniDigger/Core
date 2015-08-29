/**
 * █████████████████████████████████████████████████████████████████████████████
 * ████████████████████████████████████
 * █░░░░░░░░░░░░░░█░░░░░░██░░░░░░█░░░░░░░░░░░░░░████░░░░░░░░░░░░░░█░░░░░░░░░░░░░
 * ░█░░░░░░░░░░░░░░░░███░░░░░░░░░░░░░░█
 * █░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░████░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░
 * ░█░░▄▀▄▀▄▀▄▀▄▀▄▀░░███░░▄▀▄▀▄▀▄▀▄▀░░█
 * █░░░░░░▄▀░░░░░░█░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░░░░░████░░▄▀░░░░░░░░░░█░░▄▀░░░░░░▄▀░
 * ░█░░▄▀░░░░░░░░▄▀░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░████████████░░▄▀░░█████████░░▄▀░░██░░▄▀░
 * ░█░░▄▀░░████░░▄▀░░███░░▄▀░░█████████
 * █████░░▄▀░░█████░░▄▀░░░░░░▄▀░░█░░▄▀░░░░░░░░░░████░░▄▀░░█████████░░▄▀░░██░░▄▀░
 * ░█░░▄▀░░░░░░░░▄▀░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░████░░▄▀░░█████████░░▄▀░░██░░▄▀░
 * ░█░░▄▀▄▀▄▀▄▀▄▀▄▀░░███░░▄▀▄▀▄▀▄▀▄▀░░█
 * █████░░▄▀░░█████░░▄▀░░░░░░▄▀░░█░░▄▀░░░░░░░░░░████░░▄▀░░█████████░░▄▀░░██░░▄▀░
 * ░█░░▄▀░░░░░░▄▀░░░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░████████████░░▄▀░░█████████░░▄▀░░██░░▄▀░
 * ░█░░▄▀░░██░░▄▀░░█████░░▄▀░░█████████
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░░░░░████░░▄▀░░░░░░░░░░█░░▄▀░░░░░░▄▀░
 * ░█░░▄▀░░██░░▄▀░░░░░░█░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░████░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░
 * ░█░░▄▀░░██░░▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█
 * █████░░░░░░█████░░░░░░██░░░░░░█░░░░░░░░░░░░░░████░░░░░░░░░░░░░░█░░░░░░░░░░░░░
 * ░█░░░░░░██░░░░░░░░░░█░░░░░░░░░░░░░░█
 * █████████████████████████████████████████████████████████████████████████████
 * ████████████████████████████████████
 *
 * Copyright © MiniDigger and others - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Martin Benndorf <admin@minidigger.me>, 2013-2015 and others
 */
package me.MiniDigger.CraftCore.Menu;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Menu.ItemBarMenu;
import me.MiniDigger.Core.Menu.ItemBarMenu.ClickHandler;
import me.MiniDigger.Core.Menu.MenuHandler;
import me.MiniDigger.Core.Prefix.Prefix;
import me.MiniDigger.Core.User.User;
import me.MiniDigger.CraftCore.Item.CoreItemBuilder;

public class CoreMenuHandler implements MenuHandler {

	private FileConfiguration	config;
	private final File			configFile	= new File(Core.getCore().getInstance().getDataFolder(), "menu.yml");

	private final Map<String, ItemBarMenu>	menus		= new HashMap<String, ItemBarMenu>();
	private final Map<UUID, String>			selected	= new HashMap<UUID, String>();

	@Override
	public void load() {
		config = YamlConfiguration.loadConfiguration(configFile);
		for (final String s : config.getKeys(false)) {
			try {
				final ItemBarMenu m = new CoreItemBarMenu(s);
				for (final String key : config.getConfigurationSection(s).getKeys(false)) {
					final int i = Integer.parseInt(key);
					final Material mat = Material.valueOf(config.getString(s + "." + i + ".mat"));
					final CoreItemBuilder ib = new CoreItemBuilder(mat);

					try {
						ib.name(config.getString(s + "." + i + ".name").replaceAll("&", "§"));
					}
					catch (final Exception ex) {}

					try {
						final int data = Integer.parseInt(config.getString(s + "." + i + ".data"));
						ib.data(data).durability(data);
					}
					catch (final Exception ex2) {}

					for (final String ss : config.getStringList(s + "." + i + ".lore")) {
						try {
							ib.lore(ss.replaceAll("&", "§"));
						}
						catch (final Exception ex) {}
					}

					m.setIcon(i, ib.build());

					final String msg = config.getString(s + "." + i + ".action.msg");
					final String open = config.getString(s + "." + i + ".action.open");
					final String pCmd = config.getString(s + "." + i + ".action.pCmd");
					final String cCmd = config.getString(s + "." + i + ".action.cCmd");

					m.setAction(i, new ClickHandler() {

						@Override
						public void click(final ItemBarMenu m, final ItemStack is, final User u, final Entity entity) {
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
			}
			catch (final Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public void openMenu(final User u, final String name) {
		closeMenu(u);
		selected.remove(u.getUUID());
		selected.put(u.getUUID(), name);
		getMenu(name).open(u);
	}

	@Override
	public void closeMenu(final User u) {
		try {
			selected.remove(u.getUUID());
			getMenu(selected.get(u.getUUID())).close(u);
		}
		catch (final Exception ex) {}
	}

	@Override
	public ItemBarMenu getMenu(final String name) {
		return menus.get(name);
	}

	@Override
	public ItemBarMenu getMenu(final UUID id) {
		return getMenu(selected.get(id));
	}

	@Override
	public void addMenu(final ItemBarMenu m) {
		menus.put(m.getName(), m);
	}
}
