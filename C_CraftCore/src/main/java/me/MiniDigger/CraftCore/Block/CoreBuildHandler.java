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
package me.MiniDigger.CraftCore.Block;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.block.Block;

import me.MiniDigger.Core.Block.BuildHandler;
import me.MiniDigger.Core.User.User;

public class CoreBuildHandler implements BuildHandler {
	
	private final ArrayList<UUID>	            builders	= new ArrayList<>();
	private final HashMap<UUID, List<Material>>	allow	 = new HashMap<UUID, List<Material>>();
	private final HashMap<UUID, List<Material>>	disallow	= new HashMap<UUID, List<Material>>();
	
	@Override
	public boolean isBuilder(final User user) {
		return builders.contains(user.getUUID());
	}
	
	@Override
	public void setBuilder(final User user, final boolean builder) {
		if (builder) {
			if (!isBuilder(user)) {
				builders.add(user.getUUID());
			}
		} else {
			if (isBuilder(user)) {
				builders.remove(user.getUUID());
			}
		}
	}
	
	@Override
	public void allow(final User u, final Material... mat) {
		if (!allow.containsKey(u.getUUID())) {
			allow.put(u.getUUID(), new ArrayList<Material>());
		}
		
		final List<Material> l = allow.remove(u.getUUID());
		l.addAll(Arrays.asList(mat));
		allow.put(u.getUUID(), l);
		
		List<Material> l1 = disallow.remove(u.getUUID());
		if (l1 == null) {
			l1 = new ArrayList<Material>();
		}
		l1.addAll(Arrays.asList(mat));
		disallow.put(u.getUUID(), l1);
	}
	
	@Override
	public void disallow(final User u, final Material... mat) {
		if (!disallow.containsKey(u.getUUID())) {
			disallow.put(u.getUUID(), new ArrayList<Material>());
		}
		
		if (mat == null) {
			return;
		}
		
		final List<Material> l = disallow.remove(u.getUUID());
		l.addAll(Arrays.asList(mat));
		disallow.put(u.getUUID(), l);
		
		List<Material> l1 = allow.remove(u.getUUID());
		if (l1 == null) {
			l1 = new ArrayList<Material>();
		}
		l1.removeAll(Arrays.asList(mat));
		allow.put(u.getUUID(), l1);
	}
	
	@Override
	public boolean allow(final User u, final Block b) {
		boolean a = false;
		if (allow.containsKey(u.getUUID())) {
			a = allow.get(u.getUUID()).contains(b.getType());
		}
		boolean d = false;
		if (disallow.containsKey(u.getUUID())) {
			d = disallow.get(u.getUUID()).contains(b.getType());
		}
		
		if (a) {
			return true;
		}
		if (d) {
			return false;
		}
		return isBuilder(u);
	}
}
