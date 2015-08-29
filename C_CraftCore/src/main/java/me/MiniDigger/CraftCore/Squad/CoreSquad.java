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
package me.MiniDigger.CraftCore.Squad;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.ChatColor;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Chat.ChatChannel;
import me.MiniDigger.Core.Prefix.Prefix;
import me.MiniDigger.Core.Squad.Squad;
import me.MiniDigger.Core.User.User;
import mkremins.fanciful.FancyMessage;

public class CoreSquad implements Squad {

	private final UUID		owner;
	private ArrayList<UUID>	members;
	private ArrayList<UUID>	invs;
	private ChatChannel		channel;
	private final int		LIMIT	= 4;

	public CoreSquad(final UUID id) {
		owner = id;
		final User u = Core.getCore().getUserHandler().get(id);
		if (!u.getListenChannels().contains(channel)) {
			u.getListenChannels().add(channel);
		}
	}

	@Override
	public UUID getOwner() {
		return owner;
	}

	@Override
	public ArrayList<UUID> getMembers() {
		return members;
	}

	@Override
	public ArrayList<UUID> getInvs() {
		return invs;
	}

	@Override
	public ChatChannel getChannel() {
		return channel;
	}

	@Override
	public void setChannel(final ChatChannel channel) {
		this.channel = channel;
	}

	@Override
	public void joinServer() {
		// TODO Squad Join

	}

	@Override
	public boolean join(final UUID user) {
		if (members.size() >= LIMIT) {
			return false;
		}
		if (invs.contains(user)) {
			invs.remove(user);
		} else {
			return false;
		}
		final User u = Core.getCore().getUserHandler().get(user);
		if (!members.contains(user)) {
			members.add(user);
		}
		if (!u.getListenChannels().contains(channel)) {
			u.getListenChannels().add(channel);
		}
		return true;
	}

	@Override
	public void leave(final UUID user) {
		final User u = Core.getCore().getUserHandler().get(user);
		if (members.contains(user)) {
			members.remove(user);
		}
		if (u.getListenChannels().contains(channel)) {
			u.getListenChannels().remove(channel);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public void destroy() {
		for (final UUID id : (ArrayList<UUID>) members.clone()) {
			leave(id);
		}
		Core.getCore().getChatHandler().unregisterChannel(channel);
	}

	@Override
	public void sendMessage(final FancyMessage msg) {
		for (final UUID id : members) {
			Core.getCore().getUserHandler().get(id).sendMessage(msg);
		}

		Core.getCore().getUserHandler().get(owner).sendMessage(msg);
	}

	@Override
	public void chat(final User user, final String message) {
		channel.chat(user, message);
	}

	@Override
	public void invite(final UUID uuid) {
		if (members.contains(uuid)) {
			return;
		}
		if (!invs.contains(uuid)) {
			invs.add(uuid);
		}
	}

	@Override
	public boolean kick(final UUID uuid) {
		if (members.contains(uuid)) {
			members.remove(uuid);
			final User user = Core.getCore().getUserHandler().get(uuid);
			user.sendMessage(Prefix.SQUAD.getPrefix().then("Du wurdest aus dem Squad gekickt!").color(ChatColor.RED));
			return true;
		} else if (invs.contains(uuid)) {
			invs.remove(uuid);
			final User user = Core.getCore().getUserHandler().get(uuid);
			user.sendMessage(Prefix.SQUAD.getPrefix().then("Die Einladung wurde zur�ckgezogen!").color(ChatColor.RED));
			return true;
		} else {
			return false;
		}
	}

}
