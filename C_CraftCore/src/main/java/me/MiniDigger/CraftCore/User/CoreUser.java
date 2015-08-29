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

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Chat.ChatChannel;
import me.MiniDigger.Core.Lang.LangKeyType;
import me.MiniDigger.Core.Lang.LangType;
import me.MiniDigger.Core.SQL.SQLQuery;
import me.MiniDigger.Core.Stats.Stats;
import me.MiniDigger.Core.User.User;

import me.MiniDigger.CraftCore.Chat.Channels.EmptyChannel;
import me.MiniDigger.CraftCore.Lang.MSG;
import me.MiniDigger.CraftCore.SQL.CoreSQLQuery;

import mkremins.fanciful.FancyMessage;

public class CoreUser implements User {

	protected UUID		uuid;
	protected String	displayName;
	protected String	prefix;
	protected String	suffix;

	protected boolean	muted;
	protected Date		muteTime;

	protected boolean	banned;
	protected Date		banTime;

	protected Date	firstSeen;
	protected Date	lastSeen;
	protected Long	playTime;

	protected List<ChatChannel>	listenChannels	= new ArrayList<>();
	protected ChatChannel		speakChannel;

	protected LangType language;

	// PRIVATE
	protected Date	sessionStart;
	private int		retrys	= 0;

	public CoreUser(final UUID id) {
		if (id == null) {
			return;
		}
		uuid = id;
		if (Bukkit.getPlayer(id) != null) {
			displayName = Bukkit.getPlayer(id).getDisplayName();
		} else {
			displayName = Bukkit.getOfflinePlayer(id).getName();
		}

		language = Core.getCore().getLangHandler().getDefaultLang();
	}

	// TODO Save language of user into sql

	@Override
	public boolean save() {
		// Try insertion
		SQLQuery query = null;
		try {
			query = new CoreSQLQuery("INSERT INTO `users`(`uuid`, `displayName`, `prefix`, `suffix`, `banned`, `banTime`, `muted`, `muteTime`,`firstSeen`,`lastSeen`,`playTime`) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
			final PreparedStatement stmt = query.getStatement();
			stmt.setString(1, uuid.toString());
			stmt.setString(2, displayName);
			stmt.setString(3, prefix);
			stmt.setString(4, suffix);
			stmt.setBoolean(5, banned);
			stmt.setLong(6, banTime.getTime());
			stmt.setBoolean(7, muted);
			stmt.setLong(8, muteTime.getTime());
			stmt.setLong(9, firstSeen.getTime());
			stmt.setLong(10, lastSeen.getTime());
			stmt.setLong(11, playTime);

			stmt.execute();
			query.kill();
		}
		catch (final Exception ex) {
			try {
				query.kill();
			}
			catch (final Exception exx) {}
			// Try update
			try {
				query = new CoreSQLQuery("UPDATE `users` SET `uuid`=?,`displayName`=?,`prefix`=?,`suffix`=?,`banned`=?,`banTime`=?,`muted`=?,`muteTime`=?,`firstSeen`=?,`lastSeen`=?,`playTime`=? WHERE `uuid` LIKE ?");
				final PreparedStatement stmt = query.getStatement();
				stmt.setString(1, uuid.toString());
				stmt.setString(2, displayName);
				stmt.setString(3, prefix);
				stmt.setString(4, suffix);
				stmt.setBoolean(5, banned);
				stmt.setLong(6, banTime.getTime());
				stmt.setBoolean(7, muted);
				stmt.setLong(8, muteTime.getTime());
				stmt.setLong(9, firstSeen.getTime());
				stmt.setLong(10, lastSeen.getTime());
				stmt.setLong(11, playTime);

				stmt.setString(12, uuid.toString());

				stmt.execute();
				query.kill();
			}
			catch (final Exception e) {
				try {
					query.kill();
				}
				catch (final Exception exx) {}
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean load() {
		SQLQuery query = null;
		try {
			query = new CoreSQLQuery("SELECT * FROM `users` WHERE `uuid` LIKE ?");
			final PreparedStatement stmt = query.getStatement();
			stmt.setString(1, uuid.toString());

			final ResultSet r = stmt.executeQuery();
			if (r == null) {
				throw new NullPointerException(MSG.msg(LangKeyType.SQL.QUERY_FAILED));
			}

			r.next();
			displayName = r.getString("displayName");
			prefix = r.getString("prefix");
			suffix = r.getString("suffix");
			banned = r.getBoolean("banned");
			banTime = new Date(r.getLong("banTime"));
			muted = r.getBoolean("muted");
			muteTime = new Date(r.getLong("muteTime"));
			firstSeen = new Date(r.getLong("firstSeen"));
			lastSeen = new Date(r.getLong("lastSeen"));
			playTime = r.getLong("playTime");
			query.kill();
		}
		catch (final Exception ex) {
			try {
				query.kill();
			}
			catch (final Exception exx) {}
			return false;
		}
		return true;
	}

	@Override
	public boolean createTable() {
		final SQLQuery q = new CoreSQLQuery(
				"CREATE TABLE IF NOT EXISTS `users` (`uuid` varchar(40) NOT NULL,`displayName` varchar(25) NOT NULL,`prefix` varchar(25) NOT NULL,`suffix` varchar(25) NOT NULL, `banned` tinyint(1) NOT NULL DEFAULT '0', `banTime` bigint(20) NOT NULL, `muted` tinyint(1) NOT NULL DEFAULT '0', `muteTime` bigint(20) NOT NULL,`firstSeen` bigint(20) NOT NULL,`lastSeen` bigint(20) NOT NULL,`playTime` bigint(20) NOT NULL, PRIMARY KEY (`uuid`), UNIQUE KEY `uuid` (`uuid`))");
		try {
			q.getStatement().execute();
			q.kill();
			return true;
		}
		catch (final SQLException e) {
			try {
				q.kill();
			}
			catch (final Exception exx) {}
			return false;
		}
	}

	@Override
	public void init() {
		prefix = "";
		suffix = "";
		muted = false;
		muteTime = new Date();
		banned = false;
		banTime = new Date();
		firstSeen = new Date();
		lastSeen = new Date();
		playTime = 0L;
	}

	@Override
	public void startSession() {
		sessionStart = new Date();
		lastSeen = new Date();
	}

	@Override
	public void stopSession() {
		final Date d = new Date();
		long l = sessionStart.getTime();
		sessionStart = new Date();
		l = d.getTime() - l;
		if (playTime == null) {
			playTime = 0L;
		}
		playTime += l;
		lastSeen = new Date();
		save();
	}

	@Override
	public void ban() {
		banned = true;
	}

	@Override
	public void mute() {
		muted = true;
	}

	@Override
	public boolean checkUnban() {
		if (!banned) {
			return true;
		}
		if (banTime == null) {
			return false;
		}

		final Date nowTime = new Date();
		if (banTime.getTime() <= nowTime.getTime()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean checkUnMute() {
		if (!muted) {
			return true;
		}
		if (muteTime == null) {
			return false;
		}

		final Date nowTime = new Date();
		if (muteTime.getTime() <= nowTime.getTime()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void unBan() {
		banned = false;
		banTime = new Date();
	}

	@Override
	public void unMute() {
		muted = false;
		muteTime = new Date();
	}

	@Override
	public void sendMessage(final FancyMessage msg) {
		if (!uuid.equals(CoreBot.getBotUUID())) {
			final Player p = getPlayer();
			if (p == null) {
				retrys++;
				if (retrys == 10) {
					retrys = 0;
					return;
				}
				Bukkit.getScheduler().runTaskLater(Core.getCore().getInstance(), new Runnable() {

					@Override
					public void run() {
						sendMessage(msg);
					}
				}, 20 * 2);
			} else {
				retrys = 0;
				msg.send(p);
			}
		}
	}

	@Override
	public Player getPlayer() {
		return Bukkit.getPlayer(uuid);
	}

	@Override
	public boolean hasPermission(final String perm) {
		if (getPlayer() == null) {
			return false;
		}
		return getPlayer().hasPermission(perm);
	}

	@Override
	public Stats getStats() {
		return Core.getCore().getStatsHandler().get(uuid);
	}

	@Override
	public String getRealName() {
		return getPlayer().getName();
	}

	@Override
	public UUID getUUID() {
		return uuid;
	}

	@Override
	public void setDisplayName(final String name) {
		displayName = name;
	}

	@Override
	public String getDisplayName() {
		return displayName;
	}

	@Override
	public void setPrefix(final String prefix) {
		this.prefix = Core.getCore().getChatColorUtil().replaceAndToMc(prefix);
	}

	@Override
	public String getPrefix() {
		return prefix;
	}

	@Override
	public void setSuffix(final String suffix) {
		this.suffix = Core.getCore().getChatColorUtil().replaceAndToMc(suffix);
	}

	@Override
	public String getSuffix() {
		return suffix;
	}

	@Override
	public boolean isMuted() {
		return muted;
	}

	@Override
	public Date getMuteTime() {
		return muteTime;
	}

	@Override
	public boolean isBanned() {
		return banned;
	}

	@Override
	public Date getBanTime() {
		return banTime;
	}

	@Override
	public Date getFirstSeen() {
		return firstSeen;
	}

	@Override
	public Date getLastSeen() {
		return lastSeen;
	}

	@Override
	public long getPlayTime() {
		return playTime;
	}

	@Override
	public List<ChatChannel> getListenChannels() {
		return listenChannels;
	}

	@Override
	public ChatChannel getSpeakChannel() {
		return speakChannel;
	}

	@Override
	public void joinChannel(final ChatChannel ch) {
		speakChannel = ch;
		if (!listenChannels.contains(ch)) {
			listenChannels.add(ch);
		}
		ch.join(this);
	}

	@Override
	public void leaveChannel(final ChatChannel ch) {
		ch.leave(this);
		if (ch.getName().equals(speakChannel.getName())) {
			speakChannel = new EmptyChannel();
		}

		for (final ChatChannel cc : listenChannels) {
			if (cc.getName().equals(ch.getName())) {
				listenChannels.remove(cc);
				break;
			}
		}
	}

	@Override
	public void setPrimaryChannel(final ChatChannel ch) {
		speakChannel = ch;
		if (!listenChannels.contains(ch)) {
			listenChannels.add(ch);
			ch.join(this);
		}
	}

	@Override
	public LangType getLanguage() {
		return language;
	}

	@Override
	public void setLanguage(final LangType lang) {
		language = lang;
	}
}
