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
package me.MiniDigger.CraftCore.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.MiniDigger.Core.Lang.LangKeyType;
import me.MiniDigger.Core.Lang.LogLevel;
import me.MiniDigger.Core.User.User;
import me.MiniDigger.Core.User.UserHandler;
import me.MiniDigger.CraftCore.Lang.MSG;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class CoreUserHandler implements UserHandler {

	private final List<User>	users	= new ArrayList<>();
	private User				bot;

	@Override
	public boolean loadAll() {
		MSG.log(LogLevel.INFO, LangKeyType.SQL.CREATE_TABLE, "User");
		if (!new CoreUser(null).createTable()) {
			MSG.log(LogLevel.INFO, LangKeyType.SQL.CREATE_TABLE_FAILED);
		}
		// users = new ArrayList<>();
		// final ArrayList<String> uuids = new ArrayList<>();
		// final SQLQuery q = new CoreSQLQuery("SELECT `uuid` FROM `users`");
		// final PreparedStatement stmt = q.getStatement();
		// ResultSet r;
		// try {
		// r = stmt.executeQuery();
		// } catch (final SQLException e) {
		// return false;
		// }
		//
		// try {
		// while (r.next()) {
		// try {
		// uuids.add(r.getString("uuid"));
		// } catch (final Exception ex) {
		// // skip on single error
		// }
		// }
		// } catch (final SQLException e) {
		// return false;
		// }
		// boolean b = true;
		// for (final String s : uuids) {
		// final Stats stat = new CoreStats(UUID.fromString(s));
		// if (!stat.load()) {
		// b = false;
		// }
		// }
		//
		// q.kill();
		// Don't load all user, could be a bit too much ;D
		bot = new CoreBot(CoreBot.getBotUUID());
		users.add(bot);
		return true;
	}

	@Override
	public boolean saveAll() {
		for (final User u : users) {
			u.save();
		}
		return true;
	}

	@Override
	public User getBot() {
		return bot;
	}

	@Override
	public User get(final UUID id) {
		if (id == null) {
			return null;
		}

		for (final User u : users) {
			if (u.getUUID().equals(id)) {
				return u;
			}
		}

		final User u = new CoreUser(id);
		if (!u.load()) {
			u.init();
			if (u.getPlayer() != null) {
				final PermissionUser user = PermissionsEx.getUser(u.getPlayer());
				u.setPrefix(user.getPrefix());
			}
		}
		users.add(u);
		u.save();
		return u;
	}

	@Override
	public List<User> getOnlineUsers() {
		final List<User> users = new ArrayList<>();

		for (final Player p : Bukkit.getOnlinePlayers()) {
			users.add(get(p.getUniqueId()));
		}

		return users;
	}

	@Override
	public List<Player> getOnlinePlayers() {
		final List<Player> players = new ArrayList<>();

		for (final Player p : Bukkit.getOnlinePlayers()) {
			players.add(p);
		}

		return players;
	}

	@Override
	public User getFromDisplayName(final String name) {
		for (final User u : getOnlineUsers()) {
			if (u.getDisplayName().equalsIgnoreCase(name)) {
				return u;
			}
		}
		return null;
	}
}
