package me.MiniDigger.Core.AddOn.Custom;

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

public class CustomAddOn extends CoreAddOn {

	@Override
	public void enable() {
		GameType.CUSTOM.setClass(CustomGame.class);
		Core.getCore().getCommandHandler().registerCommands(this);
		super.enable();
	}

	@Override
	public void disable() {
		Core.getCore().getCommandHandler().unregisterCommands(this);
		super.disable();
	}

	@Command(name = "custom", permission = "custom", usage = "", consol = true, description = "Initiiert ein Custom Game", max = 0, sync = true)
	public void crank(final CommandArgs args) {
		final CustomGame game = new CustomGame();
		game.init();
		Core.getCore().getGameHandler().addGame(game);
		// for (final User user :
		// Core.getCore().getUserHandler().getOnlineUsers()) {
		final User user = args.getUser();
		final UserJoinGameEvent e1 = new CoreUserJoinGameEvent(game, user);
		Bukkit.getPluginManager().callEvent((Event) e1);
		Core.getCore().getGameHandler().joinGame(user, game);
		// }
		game.start();
	}

	@Completer(name = "custom")
	public List<String> crankC(final CommandArgs args) {
		final List<String> result = new ArrayList<>();

		result.add("");

		return result;
	}
}
