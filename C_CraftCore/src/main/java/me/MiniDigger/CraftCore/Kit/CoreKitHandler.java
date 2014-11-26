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
package me.MiniDigger.CraftCore.Kit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.entity.Player;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Kit.Kit;
import me.MiniDigger.Core.Kit.KitHandler;
import me.MiniDigger.Core.User.User;

public class CoreKitHandler implements KitHandler {
	
	private final List<Kit>	            kits	  = new ArrayList<Kit>();
	private final HashMap<UUID, String>	activKits	= new HashMap<UUID, String>();
	
	@Override
	public Kit createKit(final String name) {
		final Kit k = new CoreKit(kits.size() + 1, name);
		
		return createKit(k);
	}
	
	@Override
	public Kit createKit(final Kit kit) {
		if (getKit(kit.getName()) == null) {
			kits.add(kit);
			kit.save();
			return kit;
		} else {
			System.out.println("A kit named " + kit.getName() + " already exists!");
			return null;
		}
	}
	
	@Override
	public Kit getKit(final String name) {
		for (final Kit k : kits) {
			if (k.getName().equalsIgnoreCase(name)) {
				return k;
			}
		}
		return null;
	}
	
	@Override
	public void removeKit(final String name) {
		if (getKit(name) != null) {
			getKit(name).remove();
			kits.remove(getKit(name));
		}
	}
	
	@Override
	public void give(final User user, final String kit) {
		give(user, getKit(kit));
	}
	
	@Override
	public void give(final User user, final Kit kit) {
		give(user.getPlayer(), kit);
	}
	
	@Override
	public void give(final Player p, final String kit) {
		give(p, getKit(kit));
	}
	
	@Override
	public void give(final Player p, final Kit kit) {
		if (activKits.containsKey(p.getUniqueId())) {
			activKits.remove(p.getUniqueId());
		}
		activKits.put(p.getUniqueId(), kit.getName());
		
		p.getInventory().clear();
		
		p.getInventory().setContents(kit.getContent());
		p.getInventory().setArmorContents(kit.getArmor());
		
		p.updateInventory();
	}
	
	@Override
	public List<Kit> getKits() {
		return kits;
	}
	
	@Override
	public String getActivKit(final UUID id) {
		return activKits.get(id);
	}
	
	@Override
	public boolean loadAll() {
		Core.getCore().getInstance().info("Erstelle Tabelle für Kits...");
		if (!new CoreKit(-1).createTable()) {
			Core.getCore().getInstance().error("Tabelle wurde nicht erstellt!");
		}
		return true;
	}
	
	@Override
	public boolean saveAll() {
		return true;
	}
}
