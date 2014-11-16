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
package me.MiniDigger.Core.AddOn.TicTacToe;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Command.Command;
import me.MiniDigger.Core.Command.CommandArgs;
import me.MiniDigger.Core.Command.Completer;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Game.Game;
import me.MiniDigger.Core.Prefix.Prefix;
import me.MiniDigger.Core.User.User;

import me.MiniDigger.CraftCore.Feature.Features.TwoPlayerFeature;
import me.MiniDigger.CraftCore.Phase.CoreRepeatingPhase;
import me.MiniDigger.CraftCore.User.CoreBot;

public class TicTacToePhase extends CoreRepeatingPhase {
	
	private UUID	acting;
	private boolean	didStep	= false;
	
	public TicTacToePhase(final Game game) {
		super(game);
		init();
	}
	
	public void setActing(final UUID id) {
		acting = id;
	}
	
	public UUID getActing() {
		return acting;
	}
	
	@Override
	public String getName() {
		return "TicTacToe";
	}
	
	@Override
	public String getBarMessage() {
		return null;
	}
	
	@Override
	public boolean displayBar() {
		return false;
	}
	
	@Override
	public boolean displayLevel() {
		return false;
	}
	
	@Override
	public void init() {
		addFeature(new TwoPlayerFeature(this));
	}
	
	@Override
	public void startPhase() {
		super.startPhase();
		final User u = Core.getCore().getUserHandler().get(acting);
		final User o = ((TwoPlayerFeature) getFeature(FeatureType.TWOPLAYER)).getOther(u);
		u.sendMessage(Prefix.TICTACTOE.getPrefix().then("Du bist nun an der Reihe im Spiel gegen " + o.getDisplayName()));
		u.sendMessage(Prefix.TICTACTOE.getPrefix().then("Klicke hier um deinen Zug zu machen!").command("/tictactoe doStep"));
		o.sendMessage(Prefix.TICTACTOE.getPrefix().then("Bitte warte, während " + u.getDisplayName() + " seinen Zug macht!"));
		
		if (acting.equals(CoreBot.getBotUUID())) {
			// System.out.println("best slot = " + ((TicTacToeGame)
			// getGame()).getBestSlot());
			final int i = ((TicTacToeGame) getGame()).click(acting, ((TicTacToeGame) getGame()).getBestSlot());
			// System.out.println("result " + i);
			if (i == 1) {
				didStep = true;
				endPhase();
			} else if (i == 2) {
				HandlerList.unregisterAll(this);
				Core.getCore().getCommandHandler().unregisterCommands(this);
				try {
					finalize();
				} catch (final Throwable ee) {
					ee.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public void endPhase() {
		next = new TicTacToePhase(game);
		((TicTacToePhase) next).setActing(((TwoPlayerFeature) getFeature(FeatureType.TWOPLAYER)).getOther(acting));
		super.endPhase();
	}
	
	@Command(name = "tictactoe.doStep", permission = "tictactoe.doStep", consol = false, max = 0, min = 0, usage = "", description = "Öffnet das Inventar um einen Zug zu machen")
	public void doStepCommand(final CommandArgs args) {
		if (!args.getUser().getUUID().equals(acting)) {
			System.out.println("möööp");
			return;
		}
		final TicTacToeGame game = (TicTacToeGame) getGame();
		final TwoPlayerFeature tpf = ((TwoPlayerFeature) getFeature(FeatureType.TWOPLAYER));
		game.openInv(tpf.isOne(args.getUser().getUUID()) ? 1 : 2);
	}
	
	@Completer(name = "tictactoe.doStep")
	public List<String> doStepCommandC(final CommandArgs args) {
		final List<String> result = new ArrayList<>();
		
		result.add("");
		
		return result;
	}
	
	@EventHandler
	public void invClick(final InventoryClickEvent e) {
		final TwoPlayerFeature tpf = ((TwoPlayerFeature) getFeature(FeatureType.TWOPLAYER));
		if (e.getInventory().getTitle().contains("TicTacToe")
		        && e.getInventory().getTitle().contains(Core.getCore().getUserHandler().get((tpf.getOther(e.getWhoClicked().getUniqueId()))).getDisplayName() + "")) {
			e.setCancelled(true);
			if (e.getClick() == ClickType.LEFT) {
				if (e.getSlot() < 0 || e.getSlot() > 9) {
					return;
				}
				if (didStep || !e.getWhoClicked().getUniqueId().equals(acting)) {
					return;
				}
				final int i = ((TicTacToeGame) getGame()).click(e.getWhoClicked().getUniqueId(), e.getSlot());
				if (i == 1) {
					didStep = true;
					endPhase();
				} else if (i == 2) {
					HandlerList.unregisterAll(this);
					Core.getCore().getCommandHandler().unregisterCommands(this);
					try {
						finalize();
					} catch (final Throwable ee) {
						ee.printStackTrace();
					}
				}
				
			}
		}
	}
	
	@EventHandler
	public void onCrafting(final PrepareItemCraftEvent e) {
		final TwoPlayerFeature tpf = ((TwoPlayerFeature) getFeature(FeatureType.TWOPLAYER));
		if (e.getInventory().getTitle().contains("TicTacToe")
		        && e.getInventory().getTitle().contains(Core.getCore().getUserHandler().get((tpf.getOther(e.getViewers().get(0).getUniqueId()))).getDisplayName())) {
			e.getInventory().setItem(0, ((TicTacToeGame) getGame()).getItem(e.getViewers().get(0).getUniqueId()));
		}
	}
}
