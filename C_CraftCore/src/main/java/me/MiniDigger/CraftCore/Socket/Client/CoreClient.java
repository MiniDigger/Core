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
package me.MiniDigger.CraftCore.Socket.Client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import me.MiniDigger.Core.SQL.SQLQuery;
import me.MiniDigger.Core.Socket.Client.Client;
import me.MiniDigger.CraftCore.SQL.CoreSQLQuery;

public class CoreClient implements Client {
	
	private String	name;
	private String	password;
	
	public CoreClient(final String name) {
		this.name = name;
		password = "";
	}
	
	@Override
	public boolean save() {
		// Try insertion
		SQLQuery query = null;
		try {
			query = new CoreSQLQuery("INSERT INTO `external_users`(`name`, `password`) VALUES (?,?)");
			final PreparedStatement stmt = query.getStatement();
			stmt.setString(1, name);
			stmt.setString(2, password);
			
			stmt.execute();
			query.kill();
		} catch (final Exception ex) {
			try {
				query.kill();
			} catch (final Exception exe) {}
			try {
				query = new CoreSQLQuery("UPDATE `external_users` SET `name`=?,`password`=? WHERE `name` LIKE ?");
				final PreparedStatement stmt = query.getStatement();
				stmt.setString(1, name);
				stmt.setString(2, password);
				
				stmt.setString(9, name);
				
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
			query = new CoreSQLQuery("SELECT * FROM `external_users` WHERE `name` LIKE ?");
			final PreparedStatement stmt = query.getStatement();
			stmt.setString(1, name);
			
			final ResultSet r = stmt.executeQuery();
			if (r == null) {
				throw new NullPointerException("ResultSet returned by query can not be null!");
			}
			
			r.next();
			name = r.getString("name");
			password = r.getString("password");
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
		        "CREATE TABLE IF NOT EXISTS `external_users` (`name` varchar(40) NOT NULL, `password` varchar(40) NOT NULL, PRIMARY KEY (`name`), UNIQUE KEY `name` (`name`))");
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
	public String getName() {
		return name;
	}
	
	@Override
	public String getPassword() {
		return password;
	}
	
	@Override
	public void setName(final String name) {
		this.name = name;
	}
	
	@Override
	public void setPassword(final String password) {
		this.password = password;
	}
	
}
