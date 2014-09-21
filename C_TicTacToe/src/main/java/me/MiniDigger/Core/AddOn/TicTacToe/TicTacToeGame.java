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

import java.util.ArrayList;
import java.util.UUID;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Event.Events.UserLeaveGameEvent;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Game.GameType;
import me.MiniDigger.Core.Prefix.Prefix;
import me.MiniDigger.Core.Stats.Stats;
import me.MiniDigger.Core.Stats.StatsType;
import me.MiniDigger.Core.User.User;
import me.MiniDigger.CraftCore.Event.Events.CoreUserLeaveGameEvent;
import me.MiniDigger.CraftCore.Feature.Features.TwoPlayerFeature;
import me.MiniDigger.CraftCore.Game.CoreGame;
import me.MiniDigger.CraftCore.User.CoreBot;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TicTacToeGame extends CoreGame {
	
	private ItemStack	itemOne;
	private ItemStack	itemTwo;
	
	private int[][]	  fields;
	
	public TicTacToeGame() {
		super();
	}
	
	@Override
	public void init() {
		super.init();
		
		fields = new int[3][3];
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				fields[x][y] = 0;
			}
		}
	}
	
	public int getBestSlot() {
		TwoPlayerFeature tpf = (TwoPlayerFeature) getPhase().getFeature(FeatureType.TWOPLAYER);
		int[][] fields = new int[3][3];
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				fields[x][y] = checkSlot(fieldToSlot(x, y));
			}
		}
		
		ArrayList<Integer> possible = new ArrayList<>();
		
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				if (fields[x][y] == 0) {
					possible.add(fieldToSlot(x, y));
				}
			}
		}
		
		if (possible.size() == 1) {
			return possible.get(0);
		}
		
		// int i = tpf.isOne(Bot.getBotUUID()) ? 1 : 2;
		int waswießich = 0;
		for (int i = tpf.isOne(CoreBot.getBotUUID()) ? 1 : 2;;) {
			waswießich++;
			if (waswießich == 3) {
				break;
			}
			for (int slot : possible) {
				String[] s = slotToField(slot).split(":");
				int x = Integer.parseInt(s[0]);
				int y = Integer.parseInt(s[1]);
				fields[x][y] = i;
				
				if (fields[0][0] == i && fields[0][1] == i && fields[0][2] == i) {
					return slot;
				} else if (fields[1][0] == i && fields[1][1] == i && fields[1][2] == i) {
					return slot;
				} else if (fields[2][0] == i && fields[2][1] == i && fields[2][2] == i) {
					return slot;
				} else if (fields[0][0] == i && fields[1][0] == i && fields[2][0] == i) {
					return slot;
				} else if (fields[0][1] == i && fields[1][1] == i && fields[2][1] == i) {
					return slot;
				} else if (fields[0][2] == i && fields[1][2] == i && fields[2][2] == i) {
					return slot;
				} else if (fields[0][0] == i && fields[1][1] == i && fields[2][2] == i) {
					return slot;
				} else if (fields[2][0] == i && fields[1][1] == i && fields[0][2] == i) {
					return slot;
				}
			}
		}
		
		return possible.get(Core.getCore().getRandomUtil().nextInt(possible.size()));
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void start() {
		super.start();
		
		setPhase(new TicTacToePhase(this));
		TwoPlayerFeature tpf = (TwoPlayerFeature) getPhase().getFeature(FeatureType.TWOPLAYER);
		User starter;
		User other;
		
		if (Core.getCore().getRandomUtil().nextBoolean()) {
			starter = Core.getCore().getUserHandler().get(tpf.getOne());
			other = Core.getCore().getUserHandler().get(tpf.getTwo());
		} else {
			starter = Core.getCore().getUserHandler().get(tpf.getTwo());
			other = Core.getCore().getUserHandler().get(tpf.getOne());
		}
		
		starter.getStats().add(StatsType.TicTacToe.GAMES, 1);
		starter.getStats().save();
		
		other.getStats().add(StatsType.TicTacToe.GAMES, 1);
		other.getStats().save();
		
		itemOne = new ItemStack(Material.WOOL, 1, (short) 0, (byte) 10);
		ItemMeta meta = itemOne.getItemMeta();
		meta.setDisplayName(Core.getCore().getUserHandler().get(tpf.getOne()).getDisplayName());
		// TODO: Lore = Stats?
		itemOne.setItemMeta(meta);
		
		itemTwo = new ItemStack(Material.WOOL, 1, (short) 0, (byte) 1);
		meta = itemTwo.getItemMeta();
		meta.setDisplayName(Core.getCore().getUserHandler().get(tpf.getTwo()).getDisplayName());
		// TODO: Lore = Stats?
		itemTwo.setItemMeta(meta);
		
		((TicTacToePhase) getPhase()).setActing(starter.getUUID());
		getPhase().startPhase();
	}
	
	public void openInv(int u) {
		TwoPlayerFeature tpf = (TwoPlayerFeature) getPhase().getFeature(FeatureType.TWOPLAYER);
		User one = Core.getCore().getUserHandler().get(tpf.getOne());
		User two = Core.getCore().getUserHandler().get(tpf.getTwo());
		if (u == 1) {
			if (one.getUUID().equals(CoreBot.getBotUUID())) {
				return;
			}
			Inventory inv = Bukkit.createInventory(one.getPlayer(), InventoryType.WORKBENCH, "TicTacToe - " + two.getDisplayName());
			inv = fillInv(inv);
			inv.setItem(0, itemOne);
			one.getPlayer().openInventory(inv);
		} else {
			if (two.getUUID().equals(CoreBot.getBotUUID())) {
				return;
			}
			Inventory inv = Bukkit.createInventory(two.getPlayer(), InventoryType.WORKBENCH, "TicTacToe - " + one.getDisplayName());
			inv = fillInv(inv);
			inv.setItem(0, itemTwo);
			try {
				two.getPlayer().openInventory(inv);
			} catch (Exception ex) {}
		}
	}
	
	public int click(UUID uniqueId, int slot) {
		System.out.println("checksol" + checkSlot(slot));
		if (checkSlot(slot) == 0) {
			TwoPlayerFeature tpf = (TwoPlayerFeature) getPhase().getFeature(FeatureType.TWOPLAYER);
			setSlot(slot, tpf.isOne(uniqueId) ? 1 : 2);
			openInv(tpf.isOne(uniqueId) ? 1 : 2);
			Stats s = Core.getCore().getUserHandler().get(uniqueId).getStats();
			s.add(StatsType.TicTacToe.CLICKS, 1);
			s.save();
			if (checkWin(tpf.isOne(uniqueId) ? 1 : 2)) {
				win(uniqueId);
				return 2;
			}
			return 1;
		}
		return 0;
	}
	
	public void win(UUID w) {
		User winner = Core.getCore().getUserHandler().get(w);
		User other = ((TwoPlayerFeature) getPhase().getFeature(FeatureType.TWOPLAYER)).getOther(winner);
		
		winner.getStats().add(StatsType.TicTacToe.WINS, 1);
		winner.getStats().save();
		
		winner.sendMessage(Prefix.TICTACTOE.getPrefix().then("Du hast gegen " + other.getDisplayName() + " gewonnen!"));
		other.sendMessage(Prefix.TICTACTOE.getPrefix().then("Du hast gegen " + winner.getDisplayName() + " verloren!"));
		other.sendMessage(Prefix.TICTACTOE.getPrefix().then("Klicke hier für eine Revange").command("/tictactoe " + winner.getDisplayName()));
		
		winner.sendMessage(Prefix.TICTACTOE.getPrefix().then("Klicke hier um dir das Spiel nochmal anzugucken!").command("/tictactoe showLastGame"));
		other.sendMessage(Prefix.TICTACTOE.getPrefix().then("Klicke hier um dir das Spiel nochmal anzugucken!").command("/tictactoe showLastGame"));
		
		try {
			Inventory inv_1 = Bukkit.createInventory(winner.getPlayer(), InventoryType.WORKBENCH, "TicTacToe - " + other.getDisplayName());
			inv_1 = fillInv(inv_1);
			inv_1.setItem(0, itemOne);
			
			TicTacToeAddOn.saveGame(winner.getUUID(), inv_1);
		} catch (Exception ex) {}
		
		try {
			Inventory inv_2 = Bukkit.createInventory(other.getPlayer(), InventoryType.WORKBENCH, "TicTacToe - " + winner.getDisplayName());
			inv_2 = fillInv(inv_2);
			inv_2.setItem(0, itemTwo);
			
			TicTacToeAddOn.saveGame(other.getUUID(), inv_2);
		} catch (Exception ex) {}
		
		UserLeaveGameEvent e1 = new CoreUserLeaveGameEvent(this, winner);
		Bukkit.getPluginManager().callEvent((Event) e1);
		
		UserLeaveGameEvent e2 = new CoreUserLeaveGameEvent(this, other);
		Bukkit.getPluginManager().callEvent((Event) e2);
		
		Core.getCore().getGameHandler().removeGame(this);
	}
	
	public void drawn() {
		TwoPlayerFeature tpf = ((TwoPlayerFeature) getPhase().getFeature(FeatureType.TWOPLAYER));
		User one = Core.getCore().getUserHandler().get(tpf.getOne());
		User two = Core.getCore().getUserHandler().get(tpf.getTwo());
		
		one.sendMessage(Prefix.TICTACTOE.getPrefix().then("Das Spiel gegen " + two.getDisplayName() + " ist unendschieden ausgegangen!"));
		two.sendMessage(Prefix.TICTACTOE.getPrefix().then("Das Spiel gegen " + one.getDisplayName() + " ist unendschieden ausgegangen!"));
		
		one.sendMessage(Prefix.TICTACTOE.getPrefix().then("Klicke hier für eine Revange").command("/tictactoe " + two.getDisplayName()));
		two.sendMessage(Prefix.TICTACTOE.getPrefix().then("Klicke hier für eine Revange").command("/tictactoe " + one.getDisplayName()));
		
		one.sendMessage(Prefix.TICTACTOE.getPrefix().then("Klicke hier um dir das Spiel nochmal anzugucken!").command("/tictactoe showLastGame"));
		two.sendMessage(Prefix.TICTACTOE.getPrefix().then("Klicke hier um dir das Spiel nochmal anzugucken!").command("/tictactoe showLastGame"));
		
		try {
			Inventory inv_1 = Bukkit.createInventory(one.getPlayer(), InventoryType.WORKBENCH, "TicTacToe - " + two.getDisplayName());
			inv_1 = fillInv(inv_1);
			inv_1.setItem(0, itemOne);
			
			TicTacToeAddOn.saveGame(one.getUUID(), inv_1);
		} catch (Exception ex) {}
		
		try {
			Inventory inv_2 = Bukkit.createInventory(two.getPlayer(), InventoryType.WORKBENCH, "TicTacToe - " + one.getDisplayName());
			inv_2 = fillInv(inv_2);
			inv_2.setItem(0, itemTwo);
			
			TicTacToeAddOn.saveGame(two.getUUID(), inv_2);
		} catch (Exception ex) {}
		
		UserLeaveGameEvent e1 = new CoreUserLeaveGameEvent(this, one);
		Bukkit.getPluginManager().callEvent((Event) e1);
		
		UserLeaveGameEvent e2 = new CoreUserLeaveGameEvent(this, two);
		Bukkit.getPluginManager().callEvent((Event) e2);
		
		Core.getCore().getGameHandler().removeGame(this);
	}
	
	public void setSlot(int slot, int value) {
		String[] s = slotToField(slot).split(":");
		int x = Integer.parseInt(s[0]);
		int y = Integer.parseInt(s[1]);
		fields[x][y] = value;
	}
	
	private String slotToField(int slot) {
		int x = 0;
		int y = 0;
		
		if (slot > 6) {
			x = 2;
			y = slot - 6;
		} else if (slot > 3) {
			x = 1;
			y = slot - 3;
		} else {
			x = 0;
			y = slot;
		}
		y--;
		return x + ":" + y;
	}
	
	private int fieldToSlot(int x, int y) {
		int slot = 1 + y + 3 * x;
		return slot;
	}
	
	public int checkSlot(int slot) {
		String[] s = slotToField(slot).split(":");
		int x = Integer.parseInt(s[0]);
		int y = Integer.parseInt(s[1]);
		return fields[x][y];
	}
	
	public Inventory fillInv(Inventory inv) {
		for (int x = 0; x < 3; x++) {
			@SuppressWarnings("unused") String s = "";
			for (int y = 0; y < 3; y++) {
				int slot = fieldToSlot(x, y);
				try {
					if (fields[x][y] == 1) {
						inv.setItem(slot, itemOne);
						s += "1";
					} else if (fields[x][y] == 2) {
						inv.setItem(slot, itemTwo);
						s += "2";
					} else {
						s += "0";
						inv.setItem(slot, new ItemStack(Material.AIR));
					}
				} catch (Exception ex) {
					continue;
				}
			}
		}
		return inv;
	}
	
	public boolean checkWin(int i) {
		if (fields[0][0] == i && fields[0][1] == i && fields[0][2] == i) {
			
		} else if (fields[1][0] == i && fields[1][1] == i && fields[1][2] == i) {
			
		} else if (fields[2][0] == i && fields[2][1] == i && fields[2][2] == i) {
			
		} else if (fields[0][0] == i && fields[1][0] == i && fields[2][0] == i) {
			
		} else if (fields[0][1] == i && fields[1][1] == i && fields[2][1] == i) {
			
		} else if (fields[0][2] == i && fields[1][2] == i && fields[2][2] == i) {
			
		} else if (fields[0][0] == i && fields[1][1] == i && fields[2][2] == i) {
			
		} else if (fields[2][0] == i && fields[1][1] == i && fields[0][2] == i) {
			
		} else {
			for (int x = 0; x < 3; x++) {
				for (int y = 0; y < 3; y++) {
					if (fields[x][y] == 0) {
						return false;
					}
				}
			}
			drawn();
			return false;
		}
		return true;
	}
	
	public ItemStack getItem(UUID uniqueId) {
		TwoPlayerFeature tpf = (TwoPlayerFeature) getPhase().getFeature(FeatureType.TWOPLAYER);
		return tpf.isOne(uniqueId) ? itemOne : itemTwo;
	}
	
	@Override
	public GameType getType() {
		return GameType.TICTACTOE;
	}
}
