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
 */
package me.MiniDigger.Core.AddOn.TicTacToe;

import java.util.HashMap;
import java.util.UUID;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Command.Command;
import me.MiniDigger.Core.Command.CommandArgs;
import me.MiniDigger.Core.Event.Events.UserJoinGameEvent;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Game.Game;
import me.MiniDigger.Core.Game.GameType;
import me.MiniDigger.Core.Prefix.Prefix;
import me.MiniDigger.Core.User.User;
import me.MiniDigger.CraftCore.AddOn.CoreAddOn;
import me.MiniDigger.CraftCore.Event.Events.CoreUserJoinGameEvent;
import me.MiniDigger.CraftCore.Feature.Features.TwoPlayerFeature;
import me.MiniDigger.CraftCore.User.CoreBot;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.inventory.Inventory;

public class TicTacToeAddOn extends CoreAddOn {
	
	private static HashMap<UUID, Inventory>	savedGame	= new HashMap<>();
	
	public static void saveGame(UUID user, Inventory inv) {
		if (savedGame.containsKey(user)) {
			savedGame.remove(savedGame.get(user));
		}
		savedGame.put(user, inv);
	}
	
	@Override
	public void enable() {
		GameType.TICTACTOE.setClass(TicTacToeGame.class);
		super.enable();
	}
	
	@Command(name = "tictactoe")
	public void tictactoe(CommandArgs args) {
		if (args.getArgs().length == 0) {
			for (Game game : Core.getCore().getGameHandler().getGames(args.getUser())) {
				if (game instanceof TicTacToeGame) {
					TicTacToeGame g = (TicTacToeGame) game;
					g.openInv(((TwoPlayerFeature) g.getPhase().getFeature(FeatureType.TWOPLAYER)).isOne(args.getUser().getUUID()) ? 1 : 2);
				}
			}
			return;
		}
		if (args.getArgs()[0].equalsIgnoreCase("doStep")) {
			args.getUser().sendMessage(Prefix.TICTACTOE.getPrefix().then("Du kannst gerade keinen Zug machen!"));
			return;
		} else if (args.getArgs()[0].equalsIgnoreCase("showLastGame")) {
			args.getPlayer().openInventory(savedGame.get(args.getPlayer().getUniqueId()));
			return;
		}
		TicTacToeGame game = new TicTacToeGame();
		Core.getCore().getGameHandler().addGame(game);
		game.init();
		UserJoinGameEvent e1 = new CoreUserJoinGameEvent(game, args.getUser());
		Bukkit.getPluginManager().callEvent((Event) e1);
		game.join(args.getUser()).name();
		
		if (args.getArgs()[0].equalsIgnoreCase("bot")) {
			User bot = new CoreBot(CoreBot.getBotUUID());
			bot.setDisplayName("TTT#" + ((int) (args.getUser().getUUID().getMostSignificantBits() / 200)));
			((User) bot).setDisplayName("TTT#" + ((int) (args.getUser().getUUID().getMostSignificantBits() / 200)));
			UserJoinGameEvent e2 = new CoreUserJoinGameEvent(game, bot);
			Bukkit.getPluginManager().callEvent((Event) e2);
			game.join(bot);
		} else {
			@SuppressWarnings("deprecation") User user = Core.getCore().getUserHandler().get(Bukkit.getPlayer(args.getArgs()[0]).getUniqueId());
			UserJoinGameEvent e2 = new CoreUserJoinGameEvent(game, user);
			Bukkit.getPluginManager().callEvent((Event) e2);
			game.join(user);
			user.sendMessage(Prefix.TICTACTOE.getPrefix().then(args.getUser().getDisplayName() + " hat dich herausgefordert!"));
		}
		args.getUser().sendMessage(Prefix.TICTACTOE.getPrefix().then("Das Spiel startet jetzt..."));
		game.start();
	}
}