/*******************************************************************************
 * Test
 *******************************************************************************/
package me.MiniDigger.CraftCore.Command.Commands;

import java.util.List;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Command.Command;
import me.MiniDigger.Core.Command.CommandArgs;
import me.MiniDigger.Core.Prefix.Prefix;
import me.MiniDigger.Core.Squad.Squad;
import me.MiniDigger.Core.User.User;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class SquadCommands {
	
	@Command(name = "squad", description = "Alles mit Squads", usage = "", permission = "squad", consol = false)
	public void squad(final CommandArgs args) {
		// TODO Some help about sqoads
	}
	
	@Command(name = "squad.create", description = "Erstellt ein neues Squad", usage = "", permission = "squad.create", consol = false)
	public void create(final CommandArgs args) {
		Core.getCore().getSquadHandler().createSquad(args.getUser().getUUID());
		args.getUser().sendMessage(Prefix.SQUAD.getPrefix().then("Du hast ein Squad erstellt!").color(ChatColor.GREEN));
		args.getUser().sendMessage(Prefix.HINT.getPrefix().then("Benutze /squad invite <player> um einen Spieler einzuladen"));
	}
	
	@Command(name = "squad.destroy", description = "Zerstört dein Squad", usage = "", permission = "squad.destroy", consol = false)
	public void destroy(final CommandArgs args) {
		final Squad s = Core.getCore().getSquadHandler().getSquad(args.getUser().getUUID());
		if (s == null || s.getOwner() != args.getUser().getUUID()) {
			args.getUser().sendMessage(Prefix.SQUAD.getPrefix().then("Du bist kein Squad-Führer!").color(ChatColor.RED));
			return;
		}
		
		s.destroy();
		
		args.getUser().sendMessage(Prefix.SQUAD.getPrefix().then("Du hast dein Squad aufgelöst!").color(ChatColor.GREEN));
	}
	
	@Command(name = "squad.join", description = "Tritt einem Squad bei", usage = "", permission = "squad.join", consol = false)
	public void join(final CommandArgs args) {
		final List<Squad> invs = Core.getCore().getSquadHandler().getInvitations(args.getUser().getUUID());
		
		// TODO Better Squad join, this is crap
		if (invs.size() == 0) {
			args.getUser().sendMessage(Prefix.SQUAD.getPrefix().then("Du bist zu keinem Squad eingeladen ;(").color(ChatColor.RED));
			return;
		} else if (invs.size() == 1) {
			final Squad s = invs.get(0);
			if (s.join(args.getUser().getUUID())) {
				args.getUser().sendMessage(
				        Prefix.SQUAD.getPrefix().then("Du bist dem Squad von ").color(ChatColor.GREEN)
				                .then(Core.getCore().getUserHandler().get(s.getOwner()).getDisplayName()).color(ChatColor.YELLOW).then(" beigetreten!")
				                .color(ChatColor.GREEN));
			} else {
				args.getUser().sendMessage(Prefix.SQUAD.getPrefix().then("Du konntest dem Squad nicht betreten!").color(ChatColor.RED));
				args.getUser().sendMessage(
				        Prefix.SQUAD.getPrefix().then("Entweder das Squad ist voll, du bist schon in dem Squad oder die Einladung wurde zurückgezogen!")
				                .color(ChatColor.RED));
			}
			return;
		}
		
		args.getUser().sendMessage(Prefix.SQUAD.getPrefix().then("Du kannst folgenden Squads betreten: ").color(ChatColor.AQUA));
		
		final ChatColor one = ChatColor.AQUA;
		final ChatColor two = ChatColor.DARK_AQUA;
		for (final Squad squad : invs) {
			args.getUser().sendMessage(
			        Prefix.SQUAD.getPrefix().then(Core.getCore().getUserHandler().get(squad.getOwner()).getDisplayName()).color(one)
			                .command("/squad join " + Core.getCore().getUserHandler().get(squad.getOwner()).getDisplayName())
			                .then(": " + squad.getMembers().size() + " Mitglieder").color(two));
		}
		
		args.getUser().sendMessage(Prefix.SQUAD.getPrefix().then("Klicke auf einen Spieler um seinem Squad beizutreten!"));
	}
	
	@Command(name = "squad.leave", description = "Verlässt ein Squad", usage = "", permission = "squad.leave", consol = false)
	public void leave(final CommandArgs args) {
		final Squad s = Core.getCore().getSquadHandler().getSquad(args.getUser().getUUID());
		if (s == null) {
			args.getUser().sendMessage(Prefix.SQUAD.getPrefix().then("Du bist in keinem Squad!").color(ChatColor.RED));
			return;
		}
		if (s.getOwner().equals(args.getUser().getUUID())) {
			args.getUser().sendMessage(Prefix.SQUAD.getPrefix().then("Du bist in Squad Leader! Du kannst das Squad nicht verlassen!"));
			args.getUser().sendMessage(
			        Prefix.HINT.getPrefix().then("Benutze ").color(ChatColor.GOLD).then("squad delete ").color(ChatColor.YELLOW).then("um das Squad aufzulösen")
			                .color(ChatColor.GOLD));
			return;
		}
		s.leave(args.getUser().getUUID());
		args.getUser().sendMessage(Prefix.SQUAD.getPrefix().then("Du hast das Squad verlassen").color(ChatColor.GREEN));
	}
	
	@Command(name = "squad.invite", description = "Läd einen Spieler zu deinem Squad ein", usage = "squad invite <spieler>", permission = "squad.invite", consol = false, min = 1, max = 1)
	public void invite(final CommandArgs args) {
		final Squad s = Core.getCore().getSquadHandler().getSquad(args.getUser().getUUID());
		if (s == null || s.getOwner() != args.getUser().getUUID()) {
			args.getUser().sendMessage(Prefix.SQUAD.getPrefix().then("Du bist kein Squad-Führer!").color(ChatColor.RED));
			return;
		}
		
		final Player p = Bukkit.getPlayer(args.getArgs()[0]);
		if (p == null) {
			args.getUser().sendMessage(
			        Prefix.SQUAD.getPrefix().then("Spieler ").color(ChatColor.RED).then(args.getArgs()[0]).color(ChatColor.YELLOW).then(" konnte nicht gefunden werden!")
			                .color(ChatColor.RED));
			return;
		}
		final User user = Core.getCore().getUserHandler().get(p.getUniqueId());
		
		s.invite(user.getUUID());
		
		args.getUser().sendMessage(
		        Prefix.SQUAD.getPrefix().then("Du hast ").color(ChatColor.GREEN).then(user.getDisplayName()).color(ChatColor.YELLOW).then(" in dein Squad eingeladen!")
		                .color(ChatColor.GREEN));
	}
	
	@Command(name = "squad.kick", description = "Kickt einen Spieler aus deinem Squad", usage = "squad kick <spieler>", permission = "squad.kick", min = 1, max = 1, consol = false)
	public void kick(final CommandArgs args) {
		final Squad s = Core.getCore().getSquadHandler().getSquad(args.getUser().getUUID());
		if (s == null || s.getOwner() != args.getUser().getUUID()) {
			args.getUser().sendMessage(Prefix.SQUAD.getPrefix().then("Du bist kein Squad-Führer!").color(ChatColor.RED));
			return;
		}
		
		final Player p = Bukkit.getPlayer(args.getArgs()[0]);
		if (p == null) {
			args.getUser().sendMessage(
			        Prefix.SQUAD.getPrefix().then("Spieler ").color(ChatColor.RED).then(args.getArgs()[0]).color(ChatColor.YELLOW).then(" konnte nicht gefunden werden!")
			                .color(ChatColor.RED));
			return;
		}
		final User user = Core.getCore().getUserHandler().get(p.getUniqueId());
		
		if (!s.kick(user.getUUID())) {
			args.getUser().sendMessage(Prefix.SQUAD.getPrefix().then("Der Spieler ist nicht in deinem Squad!").color(ChatColor.RED));
			return;
		}
		
		args.getUser().sendMessage(
		        Prefix.SQUAD.getPrefix().then("Du hast ").color(ChatColor.GREEN).then(user.getDisplayName()).color(ChatColor.YELLOW).then(" aus deinem Squad gekickt!")
		                .color(ChatColor.GREEN));
	}
}
