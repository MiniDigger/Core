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
package me.MiniDigger.CraftCore.Stats;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.SQL.SQLQuery;
import me.MiniDigger.Core.Stats.Stats;
import me.MiniDigger.Core.Stats.StatsType;

import me.MiniDigger.CraftCore.CoreMain;
import me.MiniDigger.CraftCore.SQL.CoreSQLQuery;

public class CoreStats implements Stats {
	
	private UUID	                    user;
	private HashMap<StatsType, Integer>	stats;
	
	public CoreStats(final UUID id) {
		user = id;
	}
	
	@Override
	public boolean save() {
		// create query
		String q = "INSERT INTO `stats`(";
		int rows = 1;
		q += "`uuid`,";
		for (final StatsType type : StatsType.values()) {
			q += "`" + type.getGame() + "." + type.getStats() + "`,";
			rows++;
		}
		q = Core.getCore().getStringUtil().replaceLast(q, ",", "");
		q += ") VALUES (";
		for (int i = 0; i < rows; i++) {
			q += "?,";
		}
		q = Core.getCore().getStringUtil().replaceLast(q, ",", "");
		q += ")";
		// Try insertion
		SQLQuery query = null;
		try {
			query = new CoreSQLQuery(q);
			final PreparedStatement stmt = query.getStatement();
			stmt.setString(1, user.toString());
			int i;
			for (i = 2; i <= StatsType.values().size() + 1; i++) {
				stmt.setLong(i, get(StatsType.values().get(i)));
			}
			stmt.execute();
			query.kill();
		} catch (final Exception ex) {
			try {
				query.kill();
			} catch (final Exception e) {}
			// Try update
			try {
				// create query
				q = "UPDATE `stats` SET ";
				q += "`uuid`=?,";
				for (final StatsType type : StatsType.values()) {
					q += "`" + type.getGame() + "." + type.getStats() + "`=?,";
				}
				q = Core.getCore().getStringUtil().replaceLast(q, ",", "");
				q += "WHERE `uuid` LIKE ?";
				query = new CoreSQLQuery(q);
				final PreparedStatement stmt = query.getStatement();
				int i;
				stmt.setString(1, user.toString());
				for (i = 2; i <= StatsType.values().size() + 1; i++) {
					stmt.setLong(i, get(StatsType.values().get(i)));
				}
				stmt.setString(i + 1, user.toString());
				stmt.execute();
				query.kill();
			} catch (final Exception e) {
				try {
					query.kill();
				} catch (final Exception exx) {}
				return false;
			}
		}
		return true;
	}
	
	@Override
	public boolean load() {
		SQLQuery query = null;
		try {
			query = new CoreSQLQuery("SELECT * FROM `stats` WHERE `uuid` LIKE ?");
			final PreparedStatement stmt = query.getStatement();
			stmt.setString(1, user.toString());
			
			final ResultSet r = stmt.executeQuery();
			if (r == null) {
				throw new NullPointerException("ResultSet returned by query can not be null!");
			}
			
			r.next();
			user = UUID.fromString(r.getString("user"));
			stats = new HashMap<>();
			
			for (final StatsType type : StatsType.values()) {
				stats.put(type, (int) r.getLong(type.getGame() + "." + type.getStats()));
			}
			
			query.kill();
		} catch (final Exception ex) {
			try {
				query.kill();
			} catch (final Exception exx) {}
			return false;
		}
		return true;
	}
	
	@Override
	public boolean createTable() {
		String query = "CREATE TABLE IF NOT EXISTS `stats` (";
		
		query += "`uuid` varchar(40) NOT NULL,";
		for (final StatsType type : StatsType.values()) {
			query += "`" + type.getGame() + "." + type.getStats() + "` bigint(20) NOT NULL DEFAULT '" + type.getDefaultValue() + "',";
		}
		
		query += "PRIMARY KEY (`uuid`), UNIQUE KEY `uuid` (`uuid`))";
		final SQLQuery q = new CoreSQLQuery(query);
		
		try {
			q.getStatement().execute();
			q.kill();
			return true;
		} catch (final SQLException e) {
			try {
				q.kill();
			} catch (final Exception exx) {}
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public UUID getUser() {
		return user;
	}
	
	@Override
	public void set(final StatsType type, final int value) {
		stats.remove(type);
		stats.put(type, value);
	}
	
	@Override
	public void add(final StatsType type, final int value) {
		set(type, get(type) + value);
	}
	
	@Override
	public void remove(final StatsType type, final int value) {
		set(type, get(type) - value);
	}
	
	@Override
	public int get(final StatsType type) {
		if (type == null) {
			return 0;
		}
		if (stats.get(type) == null) {
			stats.put(type, type.getDefaultValue());
		}
		
		Bukkit.getScheduler().runTaskLaterAsynchronously((CoreMain) Core.getCore().getInstance(), new Runnable() {
			
			@Override
			public void run() {
				load();
			}
		}, 2);
		
		return stats.get(type);
	}
	
	@Override
	public void init() {
		stats = new HashMap<>();
	}
	
}
