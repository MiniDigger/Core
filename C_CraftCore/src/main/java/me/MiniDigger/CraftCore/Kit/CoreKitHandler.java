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
package me.MiniDigger.CraftCore.Kit;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.entity.Player;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Kit.Kit;
import me.MiniDigger.Core.Kit.KitHandler;
import me.MiniDigger.Core.SQL.SQLQuery;
import me.MiniDigger.Core.User.User;

import me.MiniDigger.CraftCore.SQL.CoreSQLQuery;

public class CoreKitHandler implements KitHandler {

	private final List<Kit>				kits		= new ArrayList<Kit>();
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
			Core.getCore().getInstance().debug("A kit named " + kit.getName() + " already exists!");
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
		if (kit == null) {
			Core.getCore().getInstance().debug("kit is null");
			return;
		}
		if (p == null) {
			Core.getCore().getInstance().debug("player is null");
			return;
		}
		if (activKits.containsKey(p.getUniqueId())) {
			activKits.remove(p.getUniqueId());
		}
		activKits.put(p.getUniqueId(), kit.getName());

		p.getInventory().clear();

		p.getInventory().setContents(kit.getContent());
		p.getInventory().setHelmet(kit.getArmor()[0]);
		p.getInventory().setChestplate(kit.getArmor()[1]);
		p.getInventory().setLeggings(kit.getArmor()[2]);
		p.getInventory().setBoots(kit.getArmor()[3]);

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

		final List<Integer> ids = new ArrayList<Integer>();
		final SQLQuery q = new CoreSQLQuery("SELECT `id` FROM `kits`");
		final PreparedStatement stmt = q.getStatement();
		ResultSet r;
		try {
			r = stmt.executeQuery();
		}
		catch (final SQLException e) {
			return false;
		}

		try {
			while (r.next()) {
				try {
					ids.add(r.getInt("id"));
				}
				catch (final Exception ex) {
					// skip on single error
				}
			}
		}
		catch (final SQLException e) {
			return false;
		}

		for (final int id : ids) {
			final Kit k = new CoreKit(id);
			k.load();
			kits.add(k);
		}

		Core.getCore().getInstance().debug(ids.size() + " kits loaded!");

		return true;
	}

	@Override
	public boolean saveAll() {
		return true;
	}
}
