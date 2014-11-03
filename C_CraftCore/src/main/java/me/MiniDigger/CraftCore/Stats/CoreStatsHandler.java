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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.SQL.SQLQuery;
import me.MiniDigger.Core.Stats.Stats;
import me.MiniDigger.Core.Stats.StatsHandler;
import me.MiniDigger.Core.Stats.StatsType;
import me.MiniDigger.CraftCore.SQL.CoreSQLQuery;

public class CoreStatsHandler implements StatsHandler {
	
	private ArrayList<Stats>	stats	= new ArrayList<>();
	
	@Override
	public boolean loadAll() {
		Core.getCore().getInstance().info("Erstelle Tabelle f�r Stats...");
		if (!new CoreStats(null).createTable()) {
			Core.getCore().getInstance().error("Tabelle wurde nicht erstellt!");
		}
		stats = new ArrayList<>();
		final ArrayList<String> uuids = new ArrayList<>();
		final SQLQuery q = new CoreSQLQuery("SELECT * FROM `stats`");
		final PreparedStatement stmt = q.getStatement();
		ResultSet r;
		try {
			r = stmt.executeQuery();
		} catch (final SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		try {
			while (r.next()) {
				try {
					uuids.add(r.getString("uuid"));
				} catch (final Exception ex) {
					// skip on single error
				}
			}
		} catch (final SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		boolean b = true;
		for (final String s : uuids) {
			final Stats stat = new CoreStats(UUID.fromString(s));
			System.out.println("load " + s);
			if (!stat.load()) {
				b = false;
			}
		}
		
		q.kill();
		
		return b;
	}
	
	@Override
	public boolean saveAll() {
		for (final Stats s : stats) {
			s.save();
		}
		return true;
	}
	
	@Override
	public List<String> getTop(final StatsType type, final int count) {
		if (type == null) {
			return new ArrayList<>();
		}
		final SQLQuery query = new CoreSQLQuery("SELECT u.displayName, s." + type.getGame() + "." + type.getStats()
		        + " FROM stats AS s, users AS u WHERE s.uuid = u.uuid ORDER BY s." + type.getGame() + "." + type.getStats() + " DESC");
		final PreparedStatement stmt = query.getStatement();
		final List<String> result = new ArrayList<>();
		try {
			
			final ResultSet r = stmt.executeQuery();
			int i = 0;
			while (r.next() && (count == -1 || i < count)) {
				try {
					result.add(r.getString("displayName") + ":" + r.getLong(type.getGame() + "." + type.getStats()));
				} catch (final Exception ex) {
					continue;
				}
				i++;
			}
		} catch (final Exception ex) {
			ex.printStackTrace();
		}
		
		query.kill();
		
		return result;
	}
	
	@Override
	public Stats get(final UUID user) {
		for (final Stats s : stats) {
			if (s.getUser().equals(user)) {
				return s;
			}
		}
		final Stats s = new CoreStats(user);
		if (!s.load()) {
			s.init();
		}
		stats.add(s);
		s.save();
		return s;
	}
	
}
