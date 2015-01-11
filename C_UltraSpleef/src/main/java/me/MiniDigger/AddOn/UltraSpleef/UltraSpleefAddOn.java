package me.MiniDigger.AddOn.UltraSpleef;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Command.Command;
import me.MiniDigger.Core.Command.CommandArgs;
import me.MiniDigger.Core.Command.Completer;
import me.MiniDigger.Core.Event.Events.UserJoinGameEvent;
import me.MiniDigger.Core.Game.GameType;
import me.MiniDigger.Core.User.User;

import me.MiniDigger.CraftCore.AddOn.CoreAddOn;
import me.MiniDigger.CraftCore.Event.Events.CoreUserJoinGameEvent;

public class UltraSpleefAddOn extends CoreAddOn {
	
	@Override
	public void enable() {
		GameType.ULTRASPLEEF.setClass(UltraSpleefGame.class);
		Core.getCore().getCommandHandler().registerCommands(this);
		super.enable();
	}
	
	@Override
	public void disable() {
		Core.getCore().getCommandHandler().unregisterCommands(this);
		super.disable();
	}
	
	@Command(name = "ultraspleef", permission = "ultraspleef", usage = "", consol = true, description = "Initiiert ein Ultraspleef Game", max = 0)
	public void getthedrop(final CommandArgs args) {
		final UltraSpleefGame game = new UltraSpleefGame();
		Core.getCore().getGameHandler().addGame(game);
		for (final User user : Core.getCore().getUserHandler().getOnlineUsers()) {
			final UserJoinGameEvent e1 = new CoreUserJoinGameEvent(game, user);
			Bukkit.getPluginManager().callEvent((Event) e1);
			game.join(user);
		}
		game.start();
	}
	
	@Completer(name = "ultraspleef")
	public List<String> getthedropC(final CommandArgs args) {
		final List<String> result = new ArrayList<>();
		
		result.add("");
		
		return result;
	}
}