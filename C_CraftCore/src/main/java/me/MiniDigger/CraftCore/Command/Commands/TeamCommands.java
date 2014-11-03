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
package me.MiniDigger.CraftCore.Command.Commands;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Command.Command;
import me.MiniDigger.Core.Command.CommandArgs;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Prefix.Prefix;
import me.MiniDigger.Core.Team.Team;
import me.MiniDigger.CraftCore.Feature.Features.TeamSelectFeature;
import mkremins.fanciful.FancyMessage;

import org.bukkit.ChatColor;

public class TeamCommands {
	
	@Command(name = "team", description = "Macht alles mit Teams", usage = "", permission = "team")
	public void team(final CommandArgs args) {
		Prefix.API.getPrefix().then("Klicke hier ").color(ChatColor.YELLOW).command("/team join ").then("um einem Team beizutreten").color(ChatColor.GOLD)
		        .send(args.getSender());
		Prefix.API.getPrefix().then("Klicke hier ").color(ChatColor.YELLOW).suggest("/team list ").then("um alle Teams anzuzeigen").color(ChatColor.GOLD)
		        .send(args.getSender());
	}
	
	@Command(name = "team.join", description = "Joint einem Team", usage = "<team>", permission = "team.join", min = 1, max = 1)
	public void join(final CommandArgs args) {
		final TeamSelectFeature f = (TeamSelectFeature) Core.getCore().getGameHandler().getMainGame().getPhase().getFeature(FeatureType.TEAM_SELECT);
		if (f == null) {
			Prefix.API.getPrefix().then("Du kannst zur Zeit keinem Team beitreten!").color(ChatColor.RED).send(args.getSender());
			return;
		}
		
		final Team t = f.getTeam(args.getArgs()[0]);
		if (t == null) {
			Prefix.API.getPrefix().then("Diese Team gibt es nicht! Verfügbare Teams: ").color(ChatColor.RED).send(args.getSender());
			final FancyMessage msg = Prefix.API.getPrefix();
			for (final Team team : f.getTeams()) {
				msg.then(" " + team.getName()).color(team.getColor()).command("/team join " + team.getName());
			}
			msg.send(args.getSender());
			return;
		}
		
		if (f.getTeam(args.getPlayer()) != null) {
			f.getTeam(args.getPlayer()).leave(args.getPlayer().getUniqueId());
		}
		t.join(args.getPlayer().getUniqueId());
		Prefix.API.getPrefix().then("Du bist Team " + t.getName() + " beitreten!").color(ChatColor.GREEN).send(args.getSender());
	}
	
	@Command(name = "team.list", description = "Zeigt alle Teams", usage = "", permission = "team.list")
	public void list(final CommandArgs args) {
		final TeamSelectFeature f = (TeamSelectFeature) Core.getCore().getGameHandler().getMainGame().getPhase().getFeature(FeatureType.TEAM_SELECT);
		if (f == null) {
			Prefix.API.getPrefix().then("Es gibt zur Zeit keine Teams").color(ChatColor.RED).send(args.getSender());
			return;
		}
		Prefix.API.getPrefix().then("========= Teams ===========").color(ChatColor.GOLD).send(args.getSender());;
		for (final Team team : f.getTeams()) {
			Prefix.API.getPrefix().then(team.getName() + ": ").color(team.getColor()).command("/team join " + team.getName()).then(team.getPlayers().size() + " Spieler")
			        .color(ChatColor.AQUA).send(args.getSender());;
		}
		Prefix.API.getPrefix().then("========= ===== ===========").color(ChatColor.GOLD).send(args.getSender());;
	}
}
