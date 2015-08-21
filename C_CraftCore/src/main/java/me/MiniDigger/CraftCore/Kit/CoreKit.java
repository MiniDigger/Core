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

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Kit.Kit;
import me.MiniDigger.Core.SQL.SQLQuery;

import me.MiniDigger.CraftCore.SQL.CoreSQLQuery;

public class CoreKit implements Kit {
	
	private int	        id;
	
	private String	    name;
	private String	    perm;
	private int	        charge;
	
	private ItemStack[]	content;
	private ItemStack[]	armor;
	
	public CoreKit(final int id) {
		this(id, "", "");
	}
	
	public CoreKit(final int id, final String name) {
		this(id, name, "");
	}
	
	public CoreKit(final int id, final String name, final String perm) {
		this(id, name, perm, 0);
	}
	
	public CoreKit(final int id, final String name, final String perm, final int charge) {
		this.id = id;
		this.name = name;
		this.perm = perm;
		this.charge = charge;
		
		armor = new ItemStack[4];
		for (int i = 0; i < armor.length; i++) {
			armor[i] = new ItemStack(Material.AIR);
		}
		
		content = new ItemStack[36];
		for (int i = 0; i < content.length; i++) {
			content[i] = new ItemStack(Material.AIR);
		}
	}
	
	@Override
	public void remove() {
		// Try insertion
		SQLQuery query = null;
		try {
			query = new CoreSQLQuery("DELETE FROM `kits` WHERE `kits`.`id` = ?");
			final PreparedStatement stmt = query.getStatement();
			
			stmt.setInt(1, id);
			
			stmt.execute();
			query.kill();
		} catch (final Exception ex) {
			Core.getCore().getInstance().debug("Failed to delete kit");
		}
	}
	
	@Override
	public boolean save() {
		// Try insertion
		SQLQuery query = null;
		try {
			query = new CoreSQLQuery("INSERT INTO `kits` (`id`, `name`, `perm`, `charge`, `content`, `armor`) VALUES (?,?,?,?,?,?);");
			final PreparedStatement stmt = query.getStatement();
			stmt.setInt(1, id);
			stmt.setString(2, name);
			stmt.setString(3, perm);
			stmt.setInt(4, charge);
			stmt.setString(5, Core.getCore().getItemUtil().itemArrayToBase64(content));
			stmt.setString(6, Core.getCore().getItemUtil().itemArrayToBase64(armor));
			
			stmt.execute();
			query.kill();
		} catch (final Exception ex) {
			try {
				query.kill();
			} catch (final Exception exe) {}
			try {
				query = new CoreSQLQuery("UPDATE `kits` SET `id`=?,`name`=?,`perm`=?,`charge`=?,`content`=?,`armor`=? WHERE `id` LIKE ?");
				final PreparedStatement stmt = query.getStatement();
				stmt.setInt(1, id);
				stmt.setString(2, name);
				stmt.setString(3, perm);
				stmt.setInt(4, charge);
				stmt.setString(5, Core.getCore().getItemUtil().itemArrayToBase64(content));
				stmt.setString(6, Core.getCore().getItemUtil().itemArrayToBase64(armor));
				
				stmt.setInt(7, id);
				
				stmt.execute();
				query.kill();
			} catch (final Exception e) {
				try {
					query.kill();
				} catch (final Exception exe) {}
				return false;
			}
		}
		return true;
	}
	
	@Override
	public boolean load() {
		SQLQuery query = null;
		try {
			query = new CoreSQLQuery("SELECT * FROM `kits` WHERE `id` LIKE ?");
			final PreparedStatement stmt = query.getStatement();
			stmt.setInt(1, id);
			
			final ResultSet r = stmt.executeQuery();
			if (r == null) {
				throw new NullPointerException("ResultSet returned by query can not be null!");
			}
			
			r.next();
			id = r.getInt("id");
			name = r.getString("name");
			perm = r.getString("perm");
			charge = r.getInt("charge");
			content = Core.getCore().getItemUtil().itemArrayFromBase64(r.getString("content"));
			armor = Core.getCore().getItemUtil().itemArrayFromBase64(r.getString("armor"));
			
			query.kill();
		} catch (final Exception ex) {
			try {
				query.kill();
			} catch (final Exception exe) {}
			return false;
		}
		return true;
	}
	
	@Override
	public boolean createTable() {
		final SQLQuery q = new CoreSQLQuery(
		        "CREATE TABLE IF NOT EXISTS `kits` ( `id` int(11) NOT NULL, `name` varchar(40) NOT NULL, `perm` varchar(40) NOT NULL, `charge` int(11) NOT NULL, `content` varchar(10000) NOT NULL, `armor` varchar(5000) NOT NULL, UNIQUE (`id`) , PRIMARY KEY (`id`))");
		try {
			q.getStatement().execute();
			q.kill();
			return true;
		} catch (final SQLException e) {
			try {
				q.kill();
			} catch (final Exception exe) {}
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public void setArmor(final int slot, final ItemStack is) {
		armor[slot] = is;
	}
	
	@Override
	public void setContent(final int slot, final ItemStack is) {
		content[slot] = is;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public void setName(final String name) {
		this.name = name;
	}
	
	@Override
	public String getPerm() {
		return perm;
	}
	
	@Override
	public void setPerm(final String perm) {
		this.perm = perm;
	}
	
	@Override
	public int getCharge() {
		return charge;
	}
	
	@Override
	public void setCharge(final int charge) {
		this.charge = charge;
	}
	
	@Override
	public ItemStack[] getContent() {
		return content;
	}
	
	@Override
	public void setContent(final ItemStack[] content) {
		this.content = content;
	}
	
	@Override
	public ItemStack[] getArmor() {
		return armor;
	}
	
	@Override
	public void setArmor(final ItemStack[] armor) {
		this.armor = armor;
	}
}
