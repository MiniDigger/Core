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
package me.MiniDigger.CraftCore.Team;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.ChatColor;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Chat.ChatChannel;
import me.MiniDigger.Core.Game.Game;
import me.MiniDigger.Core.Team.Team;
import me.MiniDigger.Core.User.User;
import me.MiniDigger.CraftCore.Chat.Channels.TeamChannel;
import mkremins.fanciful.FancyMessage;

public class CoreTeam implements Team {

	private String						name;
	private ChatColor					color;
	private Game						game;
	private int							size;
	private final List<UUID>			players	= new ArrayList<UUID>();
	private final Map<String, String>	data	= new HashMap<>();
	private final ChatChannel			channel;

	public CoreTeam(final int teamSize, final String name, final ChatColor color, final Game game) {
		this.name = name;
		this.color = color;
		this.game = game;
		size = teamSize;
		channel = new TeamChannel(name, color, "chat.hear.team", "chat.speak.team", new FancyMessage("[").color(ChatColor.DARK_BLUE).then("Team").color(color).then("]").color(ChatColor.DARK_BLUE));
	}

	@Override
	public ChatColor getColor() {
		return color;
	}

	@Override
	public void setColor(final ChatColor color) {
		this.color = color;
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
	public Game getGame() {
		return game;
	}

	@Override
	public void setGame(final Game game) {
		this.game = game;
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public void setSize(final int size) {
		this.size = size;
	}

	@Override
	public String getData(final String key) {
		return data.get(key);
	}

	@Override
	public void setData(final String key, final String data) {
		if (this.data.containsKey(key)) {
			this.data.remove(key);
		}
		this.data.put(key, data);
	}

	@Override
	public List<UUID> getPlayers() {
		return players;
	}

	@Override
	public void join(final UUID player) {
		if (!players.contains(player)) {
			players.add(player);
			try {
				final User u = Core.getCore().getUserHandler().get(player);
				u.setPrefix(getColor() + u.getPrefix());
			}
			catch (final Exception ex) {}
		}
	}

	@Override
	public void leave(final UUID player) {
		players.remove(player);
		try {
			final User u = Core.getCore().getUserHandler().get(player);
			u.setPrefix(u.getPrefix().replaceFirst(getColor() + "", ""));
		}
		catch (final Exception ex) {}
	}

	@Override
	public ChatChannel getChannel() {
		return channel;
	}
}
