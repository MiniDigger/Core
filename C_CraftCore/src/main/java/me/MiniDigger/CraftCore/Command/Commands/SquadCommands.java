package me.MiniDigger.CraftCore.Command.Commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;



public class SquadCommands {
	
	@Command(name = "squad", description = "Alles mit Squads", usage = "", permission = "squad")
	public void squad(CommandArgs args) {
		if (!args.isUser()) {
			CommonMethods.onlyPlayer(args.getSender(), "/squad");
			return;
		}
	}
	
	@Command(name = "squad.create", description = "Erstellt ein neues Squad", usage = "", permission = "squad.create")
	public void create(CommandArgs args) {
		if (!args.isUser()) {
			CommonMethods.onlyPlayer(args.getSender(), "/squad create");
			return;
		}
		
		SquadHandler.getInstance().createSquad(args.getUser().getUuid());
		args.getUser().sendMessage(Prefix.SQUAD.getPrefix().then("Du hast ein Squad erstellt!").color(ChatColor.GREEN));
		args.getUser().sendMessage(Prefix.HINT.getPrefix().then("Benutze /squad invite <player> um einen Spieler einzuladen"));
	}
	
	@Command(name = "squad.destroy", description = "Zerstört dein Squad", usage = "", permission = "squad.destroy")
	public void destroy(CommandArgs args) {
		if (!args.isUser()) {
			CommonMethods.onlyPlayer(args.getSender(), "/squad destroy");
			return;
		}
		
		Squad s = SquadHandler.getInstance().getSquad(args.getUser().getUuid());
		if (s == null || s.getOwner() != args.getUser().getUuid()) {
			args.getUser().sendMessage(Prefix.SQUAD.getPrefix().then("Du bist kein Squad-Führer!").color(ChatColor.RED));
			return;
		}
		
		s.destroy();
		
		args.getUser().sendMessage(Prefix.SQUAD.getPrefix().then("Du hast dein Squad aufgelöst!").color(ChatColor.GREEN));
	}
	
	@Command(name = "squad.join", description = "Tritt einem Squad bei", usage = "", permission = "squad.join")
	public void join(CommandArgs args) {
		if (!args.isUser()) {
			CommonMethods.onlyPlayer(args.getSender(), "/squad join");
			return;
		}
		
		ArrayList<Squad> invs = SquadHandler.getInstance().getInvitations(args.getUser().getUuid());
		
		if (invs.size() == 0) {
			args.getUser().sendMessage(Prefix.SQUAD.getPrefix().then("Du bist zu keinem Squad eingeladen ;(").color(ChatColor.RED));
			return;
		} else if (invs.size() == 1) {
			Squad s = invs.get(0);
			if (s.join(args.getUser().getUuid())) {
				args.getUser().sendMessage(
				        Prefix.SQUAD.getPrefix().then("Du bist dem Squad von ").color(ChatColor.GREEN).then(UserManager.getInstance().get(s.getOwner()).getDisplayName())
				                .color(ChatColor.YELLOW).then(" beigetreten!").color(ChatColor.GREEN));
			} else {
				args.getUser().sendMessage(Prefix.SQUAD.getPrefix().then("Du konntest dem Squad nicht betreten!").color(ChatColor.RED));
				args.getUser().sendMessage(
				        Prefix.SQUAD.getPrefix().then("Entweder das Squad ist voll, du bist schon in dem Squad oder die Einladung wurde zurückgezogen!")
				                .color(ChatColor.RED));
			}
			return;
		}
		
		args.getUser().sendMessage(Prefix.SQUAD.getPrefix().then("Du kannst folgenden Squads betreten: ").color(ChatColor.AQUA));
		
		ChatColor one = ChatColor.AQUA;
		ChatColor two = ChatColor.DARK_AQUA;
		for (Squad squad : invs) {
			args.getUser().sendMessage(
			        Prefix.SQUAD.getPrefix().then(UserManager.getInstance().get(squad.getOwner()).getDisplayName()).color(one)
			                .command("/squad join " + UserManager.getInstance().get(squad.getOwner()).getDisplayName())
			                .then(": " + squad.getMembers().size() + " Mitglieder").color(two));
		}
		
		args.getUser().sendMessage(Prefix.SQUAD.getPrefix().then("Klicke auf einen Spieler um seinem Squad beizutreten!"));
		
	}
	
	@Command(name = "squad.leave", description = "Verlässt ein Squad", usage = "", permission = "squad.leave")
	public void leave(CommandArgs args) {
		if (!args.isUser()) {
			CommonMethods.onlyPlayer(args.getSender(), "/squad leave");
			return;
		}
		
		Squad s = SquadHandler.getInstance().getSquad(args.getUser().getUuid());
		if (s == null) {
			args.getUser().sendMessage(Prefix.SQUAD.getPrefix().then("Du bist in keinem Squad!").color(ChatColor.RED));
			return;
		}
		if (s.getOwner().equals(args.getUser().getUuid())) {
			args.getUser().sendMessage(Prefix.SQUAD.getPrefix().then("Du bist in Squad Leader! Du kannst das Squad nicht verlassen!"));
			args.getUser().sendMessage(
			        Prefix.HINT.getPrefix().then("Benutze ").color(ChatColor.GOLD).then("squad delete ").color(ChatColor.YELLOW).then("um das Squad aufzulösen")
			                .color(ChatColor.GOLD));
			return;
		}
		s.leave(args.getUser().getUuid());
		args.getUser().sendMessage(Prefix.SQUAD.getPrefix().then("Du hast das Squad verlassen").color(ChatColor.GREEN));
	}
	
	@Command(name = "squad.invite", description = "Läd einen Spieler zu deinem Squad ein", usage = "squad invite <spieler>", permission = "squad.invite")
	public void invite(CommandArgs args) {
		if (!args.isUser()) {
			CommonMethods.onlyPlayer(args.getSender(), "/squad invite");
			return;
		}
		
		Squad s = SquadHandler.getInstance().getSquad(args.getUser().getUuid());
		if (s == null || s.getOwner() != args.getUser().getUuid()) {
			args.getUser().sendMessage(Prefix.SQUAD.getPrefix().then("Du bist kein Squad-Führer!").color(ChatColor.RED));
			return;
		}
		
		if (args.getArgs().length != 1) {
			args.getUser().sendMessage(Prefix.SQUAD.getPrefix().then("Falsche Benutzung! ").color(ChatColor.RED).then("squad invite <spieler>").color(ChatColor.YELLOW));
			return;
		}
		
		@SuppressWarnings("deprecation") Player p = Bukkit.getPlayer(args.getArgs()[0]);
		if (p == null) {
			args.getUser().sendMessage(
			        Prefix.SQUAD.getPrefix().then("Spieler ").color(ChatColor.RED).then(args.getArgs()[0]).color(ChatColor.YELLOW).then(" konnte nicht gefunden werden!")
			                .color(ChatColor.RED));
			return;
		}
		User user = UserManager.getInstance().get(p.getUniqueId());
		
		s.invite(user.getUuid());
		
		args.getUser().sendMessage(
		        Prefix.SQUAD.getPrefix().then("Du hast ").color(ChatColor.GREEN).then(user.getDisplayName()).color(ChatColor.YELLOW).then(" in dein Squad eingeladen!")
		                .color(ChatColor.GREEN));
	}
	
	@Command(name = "squad.kick", description = "Kickt einen Spieler aus deinem Squad", usage = "squad kick <spieler>", permission = "squad.kick")
	public void kick(CommandArgs args) {
		if (!args.isUser()) {
			CommonMethods.onlyPlayer(args.getSender(), "/squad kick");
			return;
		}
		
		Squad s = SquadHandler.getInstance().getSquad(args.getUser().getUuid());
		if (s == null || s.getOwner() != args.getUser().getUuid()) {
			args.getUser().sendMessage(Prefix.SQUAD.getPrefix().then("Du bist kein Squad-Führer!").color(ChatColor.RED));
			return;
		}
		
		if (args.getArgs().length != 1) {
			args.getUser().sendMessage(Prefix.SQUAD.getPrefix().then("Falsche Benutzung! ").color(ChatColor.RED).then("squad kick <spieler>").color(ChatColor.YELLOW));
			return;
		}
		
		@SuppressWarnings("deprecation") Player p = Bukkit.getPlayer(args.getArgs()[0]);
		if (p == null) {
			args.getUser().sendMessage(
			        Prefix.SQUAD.getPrefix().then("Spieler ").color(ChatColor.RED).then(args.getArgs()[0]).color(ChatColor.YELLOW).then(" konnte nicht gefunden werden!")
			                .color(ChatColor.RED));
			return;
		}
		User user = UserManager.getInstance().get(p.getUniqueId());
		
		if (!s.kick(user.getUuid())) {
			args.getUser().sendMessage(Prefix.SQUAD.getPrefix().then("Der Spieler ist nicht in deinem Squad!").color(ChatColor.RED));
			return;
		}
		
		args.getUser().sendMessage(
		        Prefix.SQUAD.getPrefix().then("Du hast ").color(ChatColor.GREEN).then(user.getDisplayName()).color(ChatColor.YELLOW).then(" aus deinem Squad gekickt!")
		                .color(ChatColor.GREEN));
	}
}
