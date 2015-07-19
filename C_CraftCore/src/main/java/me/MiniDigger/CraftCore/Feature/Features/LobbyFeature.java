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
package me.MiniDigger.CraftCore.Feature.Features;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Game.GameType;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.Core.User.User;

import me.MiniDigger.CraftCore.Event.Events.CoreUserJoinGameEvent;
import me.MiniDigger.CraftCore.Feature.CoreFeature;

public class LobbyFeature extends CoreFeature {
	
	private Map<Integer, Map<Integer, String[]>>	text1	= new HashMap<Integer, Map<Integer, String[]>>();
	private Map<Integer, Map<Integer, String[]>>	text2	= new HashMap<Integer, Map<Integer, String[]>>();
	
	public LobbyFeature(final Phase phase) {
		super(phase);
	}
	
	@Override
	public FeatureType getType() {
		return FeatureType.LOBBYFEATURE;
	}
	
	@Override
	public List<FeatureType> getDependencies() {
		return new ArrayList<FeatureType>();
	}
	
	@Override
	public List<FeatureType> getSoftDependencies() {
		return new ArrayList<FeatureType>();
	}
	
	@Override
	public List<FeatureType> getIncompabilities() {
		return new ArrayList<FeatureType>();
	}
	
	@EventHandler
	public void onUserJoin(final CoreUserJoinGameEvent e) {
		if (e.getGame().getIdentifier() == getPhase().getGame().getIdentifier()) {
			final User u = e.getUser();
			try {
				Core.getCore().getMenuHandler().openMenu(u, getPhase().getGame().getType().getName());
			} catch (final Exception ex) {
//				try {
//					Core.getCore().getMenuHandler().openMenu(u, "game");
//				} catch (final Exception ex1) {
//					try {
//						Core.getCore().getMenuHandler().openMenu(u, "menu");
//					} catch (final Exception ex2) {}
//				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerJoin(final PlayerJoinEvent e) {
		if (getPhase().getGame().getPlayers().contains(e.getPlayer().getUniqueId())) {
			final User u = Core.getCore().getUserHandler().get(e.getPlayer().getUniqueId());
			try {
				Core.getCore().getMenuHandler().openMenu(u, getPhase().getGame().getType().getName());
			} catch (final Exception ex) {
				try {
					Core.getCore().getMenuHandler().openMenu(u, "game");
				} catch (final Exception ex1) {
					try {
						Core.getCore().getMenuHandler().openMenu(u, "menu");
					} catch (final Exception ex2) {}
				}
			}
			
		}
	}
	
	public String getName() {
		String name = getPhase().getGame().getGameData("Lobby");
		if (name.equals("Lobby")) {
			name = getPhase().getGame().getType().getAbk() + "_Lobby";
		}
		return name;
	}
	
	@Override
	public void start() {
		Core.getCore().getTaskHandler().runTaskLater(new BukkitRunnable() {
			
			@Override
			public void run() {
				// Menu Stuff
				for (final UUID id : getPhase().getGame().getPlayers()) {
					final User u = Core.getCore().getUserHandler().get(id);
					try {
						Core.getCore().getMenuHandler().openMenu(u, getPhase().getGame().getType().getName());
					} catch (final Exception ex) {
						try {
							Core.getCore().getMenuHandler().openMenu(u, "game");
						} catch (final Exception ex1) {
							try {
								Core.getCore().getMenuHandler().openMenu(u, "menu");
							} catch (final Exception ex2) {}
						}
					}
				}
				
				// Sign Stuff
				final int down = 3;
				final int side = 4;
				
				text1 = SignStorage.getOne(getPhase().getGame().getType());
				text2 = SignStorage.getTwo(getPhase().getGame().getType());
				
				final World w = Bukkit.getWorld(getName());
				final Location origin = new Location(w, -48, 4, -51);
				final Block blockOrigion = origin.getBlock();
				
				Block start = blockOrigion.getRelative(BlockFace.SOUTH, 5);
				start = start.getRelative(BlockFace.UP, 3);
				
				// Wall 1
				for (int x = 0; x < down; x++) {
					for (int i = 0; i < side; i++) {
						start.setType(Material.AIR);
						start.setType(Material.WALL_SIGN);
						
						if (start.getState() instanceof Sign) {
							final Sign sign = (Sign) start.getState();
							final org.bukkit.material.Sign mat = ((org.bukkit.material.Sign) start.getState().getData());
							mat.setFacingDirection(BlockFace.EAST);
							sign.setData(mat);
							
							sign.setLine(0, Core.getCore().getChatColorUtil().replaceAndToMc(text1.get(x).get(i)[0]));
							sign.setLine(1, Core.getCore().getChatColorUtil().replaceAndToMc(text1.get(x).get(i)[1]));
							sign.setLine(2, Core.getCore().getChatColorUtil().replaceAndToMc(text1.get(x).get(i)[2]));
							sign.setLine(3, Core.getCore().getChatColorUtil().replaceAndToMc(text1.get(x).get(i)[3]));
							
							sign.update();
							
							start = start.getRelative(BlockFace.NORTH, 1);
						}
					}
					start = start.getRelative(BlockFace.DOWN);
					start = start.getRelative(BlockFace.SOUTH, side);
				}
				
				start = blockOrigion.getRelative(BlockFace.EAST, 2);
				start = start.getRelative(BlockFace.UP, 3);
				
				// Wall 2
				for (int x = 0; x < down; x++) {
					for (int i = 0; i < side; i++) {
						start.setType(Material.AIR);
						start.setType(Material.WALL_SIGN);
						
						if (start.getState() instanceof Sign) {
							final Sign sign = (Sign) start.getState();
							final org.bukkit.material.Sign mat = ((org.bukkit.material.Sign) start.getState().getData());
							mat.setFacingDirection(BlockFace.SOUTH);
							sign.setData(mat);
							
							sign.setLine(0, Core.getCore().getChatColorUtil().replaceAndToMc(text2.get(x).get(i)[0]));
							sign.setLine(1, Core.getCore().getChatColorUtil().replaceAndToMc(text2.get(x).get(i)[1]));
							sign.setLine(2, Core.getCore().getChatColorUtil().replaceAndToMc(text2.get(x).get(i)[2]));
							sign.setLine(3, Core.getCore().getChatColorUtil().replaceAndToMc(text2.get(x).get(i)[3]));
							
							sign.update();
							
							start = start.getRelative(BlockFace.EAST, 1);
						}
					}
					start = start.getRelative(BlockFace.DOWN);
					start = start.getRelative(BlockFace.WEST, side);
				}
			}
		}, 20, getPhase());// wait for chunks loaded
	}
	
	@Override
	public void end() {
		
	}
	
	private static class SignStorage {
		
		// Bedwars link
		private static Map<Integer, Map<Integer, String[]>> bedwarsOne() {
			final Map<Integer, Map<Integer, String[]>> result = new HashMap<Integer, Map<Integer, String[]>>();
			
			// Line 1
			Map<Integer, String[]> temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "&5&l<><><><><>", "", "", "&5&l<><><><><>" });
			temp.put(1, new String[] { "&5&l<><><><><>", "", "&4&BEDWARS", "&5&l<><><><><>" });
			temp.put(2, new String[] { "&5&l<><><><><>", "", "&4&BEDWARS", "&5&l<><><><><>" });
			temp.put(3, new String[] { "&5&l<><><><><>", "", "", "&5&l<><><><><>" });
			result.put(0, temp);
			
			// Line 2
			temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(1, temp);
			
			// Line 3
			temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(2, temp);
			
			return result;
		}
		
		// Bedwars rechts
		private static Map<Integer, Map<Integer, String[]>> bedwarsTwo() {
			final Map<Integer, Map<Integer, String[]>> result = new HashMap<Integer, Map<Integer, String[]>>();
			
			// Line 1
			Map<Integer, String[]> temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(0, temp);
			
			// Line 2
			temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(1, temp);
			
			// Line 3
			temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(2, temp);
			
			return result;
		}
		
		// Crank links
		private static Map<Integer, Map<Integer, String[]>> crankOne() {
			final Map<Integer, Map<Integer, String[]>> result = new HashMap<Integer, Map<Integer, String[]>>();
			
			// Line 1
			Map<Integer, String[]> temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "&5&l<><><><><>", "", "", "&5&l<><><><><>" });
			temp.put(1, new String[] { "&5&l<><><><><>", "", "&4&lCRANK", "&5&l<><><><><>" });
			temp.put(2, new String[] { "&5&l<><><><><>", "", "&4&lCRANK", "&5&l<><><><><>" });
			temp.put(3, new String[] { "&5&l<><><><><>", "", "", "&5&l<><><><><>" });
			result.put(0, temp);
			
			// Line 2
			temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "In &4&lCrank", "ist es die", "Aufgabe, alle", "&4Gegner&r zu" });
			temp.put(1, new String[] { "töten. Jedoch", "hast du nur", "&a30 Sekunden", "Zeit." });
			temp.put(2, new String[] { "Fals du es", "in der Zeit", "schaffst einen", "&4Gegner&r zu" });
			temp.put(3, new String[] { "töten, so", "wird deine Zeit", "wieder auf", "&a30 Sekunden" });
			result.put(1, temp);
			
			// Line 3
			temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "resettet.", "Schaffst du es ", "jedoch nicht,", "so heißt es" });
			temp.put(1, new String[] { "&c&l&oBOOM", "und du", "verlierst", "&2ein Leben." });
			temp.put(2, new String[] { "Jeder &9Spieler", "besitzt &2fünf", "&2Leben&r. Wenn", "du alle &2Leben" });
			temp.put(3, new String[] { "verloren hast", "ist das &3Spiel", "für dich", "beendet." });
			result.put(2, temp);
			
			return result;
		}
		
		// Crank rechts
		private static Map<Integer, Map<Integer, String[]>> crankTwo() {
			final Map<Integer, Map<Integer, String[]>> result = new HashMap<Integer, Map<Integer, String[]>>();
			
			// Line 1
			Map<Integer, String[]> temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "&5&l<><><><><>", "", "&6&lKIT'S", "&5&l<><><><><>" });
			temp.put(1, new String[] { "&5&l<><><><><>", "", "&4&lCRANK", "&5&l<><><><><>" });
			temp.put(2, new String[] { "&5&l<><><><><>", "", "&4&lCRANK", "&5&l<><><><><>" });
			temp.put(3, new String[] { "&5&l<><><><><>", "", "&6&lKIT'S", "&5&l<><><><><>" });
			result.put(0, temp);
			
			// Line 2
			temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "[Kit]", "Tank", "---------------", "&9&lUser-Kit" });
			temp.put(1, new String[] { "[Kit]", "Soldat", "---------------", "&9&lUser-Kit" });
			temp.put(2, new String[] { "[Kit]", "Jäger", "---------------", "&9&lUser-Kit" });
			temp.put(3, new String[] { "[Kit]", "Bauer", "---------------", "&9&lUser-Kit" });
			result.put(1, temp);
			
			// Line 3
			temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "[Kit]", "Kleriker", "---------------", "&9&lUser-Kit" });
			temp.put(1, new String[] { "[Kit]", "Ice-Man", "---------------", "&a&lToken-Kit" });
			temp.put(2, new String[] { "[Kit]", "Runner", "---------------", "&a&lToken-Kit" });
			temp.put(3, new String[] { "[Kit]", "Magier", "---------------", "&6&lPremium" });
			result.put(2, temp);
			
			return result;
		}
		
		// getthedrop links
		private static Map<Integer, Map<Integer, String[]>> getthedropOne() {
			final Map<Integer, Map<Integer, String[]>> result = new HashMap<Integer, Map<Integer, String[]>>();
			
			// Line 1
			Map<Integer, String[]> temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "&5&l<><><><><>", "", "", "&5&l<><><><><>" });
			temp.put(1, new String[] { "&5&l<><><><><>", "&4&lGet The", "&4&lDrop", "&5&l<><><><><>" });
			temp.put(2, new String[] { "&5&l<><><><><>", "&4&lGet The", "&4&lDrop", "&5&l<><><><><>" });
			temp.put(3, new String[] { "&5&l<><><><><>", "", "", "&5&l<><><><><>" });
			result.put(0, temp);
			
			// Line 2
			temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "In &4&lGET THE", "&4&lDROP", "gibt es zwei", "&6Phasen&r. In" });
			temp.put(1, new String[] { "der &61. Phase", "musst du so", "viele &aItems", "wie möglich" });
			temp.put(2, new String[] { "einsammeln. In", "dieser &6Phase", "muss du auch", "deine &1Rüstung" });
			temp.put(3, new String[] { "und &cWaffen", "bauen. In der", "&62. Phase", "musst du" });
			result.put(1, temp);
			
			// Line 3
			temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "versuchen deine", "&4Gegner&r zu", "töten. Wirst", "jedoch du" });
			temp.put(1, new String[] { "&4getötet&r so", "scheidest du", "aus. Jede", "&2Minute" });
			temp.put(2, new String[] { "droppen", "&5Power-Items", "auf den", "&bGolem-Spots." });
			temp.put(3, new String[] { "Versuche sie", "zu ergattern", "um zu", "&agewinnen." });
			result.put(2, temp);
			
			return result;
		}
		
		// getthedrop rechts
		private static Map<Integer, Map<Integer, String[]>> getthedropTwo() {
			final Map<Integer, Map<Integer, String[]>> result = new HashMap<Integer, Map<Integer, String[]>>();
			
			// Line 1
			Map<Integer, String[]> temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "&5&l<><><><><>", "", "&6&lBUFF'S", "&5&l<><><><><>" });
			temp.put(1, new String[] { "&5&l<><><><><>", "&4&lGet The", "&4&lDrop", "&5&l<><><><><>" });
			temp.put(2, new String[] { "&5&l<><><><><>", "&4&lGet The", "&4&lDrop", "&5&l<><><><><>" });
			temp.put(3, new String[] { "&5&l<><><><><>", "", "&6&lBUFF'S", "&5&l<><><><><>" });
			result.put(0, temp);
			
			// Line 2
			temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(1, temp);
			
			// Line 3
			temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "&lCOMING", "", "" });
			temp.put(2, new String[] { "", "&lSOON", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(2, temp);
			
			return result;
		}
		
		// oneinthechamber links
		private static Map<Integer, Map<Integer, String[]>> oitcOne() {
			final Map<Integer, Map<Integer, String[]>> result = new HashMap<Integer, Map<Integer, String[]>>();
			
			// Line 1
			Map<Integer, String[]> temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "&5&l<><><><><>", "", "", "&5&l<><><><><>" });
			temp.put(1, new String[] { "&5&l<><><><><>", "&4&lONE IN THE", "&4&lCHAMBER", "&5&l<><><><><>" });
			temp.put(2, new String[] { "&5&l<><><><><>", "&4&lONE IN THE", "&4&lCHAMBER", "&5&l<><><><><>" });
			temp.put(3, new String[] { "&5&l<><><><><>", "", "", "&5&l<><><><><>" });
			result.put(0, temp);
			
			// Line 2
			temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "In diesem", "&5Spiel&r geht", "es darum dein", "&4Gegner&r mit" });
			temp.put(1, new String[] { "einem &cSchuss", "zu töten.", "Triffst du", "nicht, so muss" });
			temp.put(2, new String[] { "du deine", "&4Gegner&r mit", "lediglich einem", "&2Holzschwert" });
			temp.put(3, new String[] { "töten. Tötet", "jedoch jemand", "dich, selbst", "verlierst du" });
			result.put(1, temp);
			
			// Line 3
			temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "|", "|", "|", "|" });
			temp.put(1, new String[] { "ein &aLeben&r.", "Dir stehen", "insgesammt", "&a5 Leben zur" });
			temp.put(2, new String[] { "verfügung. Der", "jenige mit den", "meisten &4Kills", "gewinnt!" });
			temp.put(3, new String[] { "|", "|", "|", "|" });
			result.put(2, temp);
			
			return result;
		}
		
		// oneinthechamber rechts
		private static Map<Integer, Map<Integer, String[]>> oitcTwo() {
			final Map<Integer, Map<Integer, String[]>> result = new HashMap<Integer, Map<Integer, String[]>>();
			
			// Line 1
			Map<Integer, String[]> temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "&5&l<><><><><>", "", "&6&lBÖGEN", "&5&l<><><><><>" });
			temp.put(1, new String[] { "&5&l<><><><><>", "&4&lONE IN THE", "&4&lCHAMBER", "&5&l<><><><><>" });
			temp.put(2, new String[] { "&5&l<><><><><>", "&4&lONE IN THE", "&4&lCHAMBER", "&5&l<><><><><>" });
			temp.put(3, new String[] { "&5&l<><><><><>", "", "&6&lBÖGEN", "&5&l<><><><><>" });
			result.put(0, temp);
			
			// Line 2
			temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(1, temp);
			
			// Line 3
			temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "&lCOMING", "", "" });
			temp.put(2, new String[] { "", "&lSOON", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(2, temp);
			
			return result;
		}
		
		// ultraspleef links
		private static Map<Integer, Map<Integer, String[]>> ultraspleefOne() {
			final Map<Integer, Map<Integer, String[]>> result = new HashMap<Integer, Map<Integer, String[]>>();
			
			// Line 1
			Map<Integer, String[]> temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "&5&l<><><><><>", "", "", "&5&l<><><><><>" });
			temp.put(1, new String[] { "&5&l<><><><><>", "", "&4&UltraSpleef", "&5&l<><><><><>" });
			temp.put(2, new String[] { "&5&l<><><><><>", "", "&4&UltraSpleef", "&5&l<><><><><>" });
			temp.put(3, new String[] { "&5&l<><><><><>", "", "", "&5&l<><><><><>" });
			result.put(0, temp);
			
			// Line 2
			temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(1, temp);
			
			// Line 3
			temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(2, temp);
			
			return result;
		}
		
		// unltraspleef rechts
		private static Map<Integer, Map<Integer, String[]>> ultraspleefTwo() {
			final Map<Integer, Map<Integer, String[]>> result = new HashMap<Integer, Map<Integer, String[]>>();
			
			// Line 1
			Map<Integer, String[]> temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(0, temp);
			
			// Line 2
			temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(1, temp);
			
			// Line 3
			temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(2, temp);
			
			return result;
		}
		
		// buildmything links
		private static Map<Integer, Map<Integer, String[]>> bmtOne() {
			final Map<Integer, Map<Integer, String[]>> result = new HashMap<Integer, Map<Integer, String[]>>();
			
			// Line 1
			Map<Integer, String[]> temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(0, temp);
			
			// Line 2
			temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(1, temp);
			
			// Line 3
			temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(2, temp);
			
			return result;
		}
		
		// buildmything rechts
		private static Map<Integer, Map<Integer, String[]>> bmtTwo() {
			final Map<Integer, Map<Integer, String[]>> result = new HashMap<Integer, Map<Integer, String[]>>();
			
			// Line 1
			Map<Integer, String[]> temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(0, temp);
			
			// Line 2
			temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(1, temp);
			
			// Line 3
			temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(2, temp);
			
			return result;
		}
		
		// infected links
		private static Map<Integer, Map<Integer, String[]>> ifOne() {
			final Map<Integer, Map<Integer, String[]>> result = new HashMap<Integer, Map<Integer, String[]>>();
			
			// Line 1
			Map<Integer, String[]> temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(0, temp);
			
			// Line 2
			temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(1, temp);
			
			// Line 3
			temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(2, temp);
			
			return result;
		}
		
		// infected rechts
		private static Map<Integer, Map<Integer, String[]>> ifTwo() {
			final Map<Integer, Map<Integer, String[]>> result = new HashMap<Integer, Map<Integer, String[]>>();
			
			// Line 1
			Map<Integer, String[]> temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(0, temp);
			
			// Line 2
			temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(1, temp);
			
			// Line 3
			temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(2, temp);
			
			return result;
		}
		
		// kistenkrieg links
		private static Map<Integer, Map<Integer, String[]>> kkOne() {
			final Map<Integer, Map<Integer, String[]>> result = new HashMap<Integer, Map<Integer, String[]>>();
			
			// Line 1
			Map<Integer, String[]> temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(0, temp);
			
			// Line 2
			temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(1, temp);
			
			// Line 3
			temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(2, temp);
			
			return result;
		}
		
		// kistenkrieg rechts
		private static Map<Integer, Map<Integer, String[]>> kkTwo() {
			final Map<Integer, Map<Integer, String[]>> result = new HashMap<Integer, Map<Integer, String[]>>();
			
			// Line 1
			Map<Integer, String[]> temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(0, temp);
			
			// Line 2
			temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(1, temp);
			
			// Line 3
			temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(2, temp);
			
			return result;
		}
		
		// survivalgames links
		private static Map<Integer, Map<Integer, String[]>> sgOne() {
			final Map<Integer, Map<Integer, String[]>> result = new HashMap<Integer, Map<Integer, String[]>>();
			
			// Line 1
			Map<Integer, String[]> temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(0, temp);
			
			// Line 2
			temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(1, temp);
			
			// Line 3
			temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(2, temp);
			
			return result;
		}
		
		// survivalgames rechts
		private static Map<Integer, Map<Integer, String[]>> sgTwo() {
			final Map<Integer, Map<Integer, String[]>> result = new HashMap<Integer, Map<Integer, String[]>>();
			
			// Line 1
			Map<Integer, String[]> temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(0, temp);
			
			// Line 2
			temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(1, temp);
			
			// Line 3
			temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(2, temp);
			
			return result;
		}
		
		// kitpvp links
		private static Map<Integer, Map<Integer, String[]>> kpOne() {
			final Map<Integer, Map<Integer, String[]>> result = new HashMap<Integer, Map<Integer, String[]>>();
			
			// Line 1
			Map<Integer, String[]> temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(0, temp);
			
			// Line 2
			temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(1, temp);
			
			// Line 3
			temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(2, temp);
			
			return result;
		}
		
		// kitpvp rechts
		private static Map<Integer, Map<Integer, String[]>> kpTwo() {
			final Map<Integer, Map<Integer, String[]>> result = new HashMap<Integer, Map<Integer, String[]>>();
			
			// Line 1
			Map<Integer, String[]> temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(0, temp);
			
			// Line 2
			temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(1, temp);
			
			// Line 3
			temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(2, temp);
			
			return result;
		}
		
		// suchenundverstecken links
		private static Map<Integer, Map<Integer, String[]>> suvOne() {
			final Map<Integer, Map<Integer, String[]>> result = new HashMap<Integer, Map<Integer, String[]>>();
			
			// Line 1
			Map<Integer, String[]> temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(0, temp);
			
			// Line 2
			temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(1, temp);
			
			// Line 3
			temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(2, temp);
			
			return result;
		}
		
		// suchenundverstecken rechts
		private static Map<Integer, Map<Integer, String[]>> suvTwo() {
			final Map<Integer, Map<Integer, String[]>> result = new HashMap<Integer, Map<Integer, String[]>>();
			
			// Line 1
			Map<Integer, String[]> temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(0, temp);
			
			// Line 2
			temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(1, temp);
			
			// Line 3
			temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(2, temp);
			
			return result;
		}
		
		private static Map<Integer, Map<Integer, String[]>> placeholder() {
			final Map<Integer, Map<Integer, String[]>> result = new HashMap<Integer, Map<Integer, String[]>>();
			
			for (int x = 0; x < 3; x++) {
				final Map<Integer, String[]> temp = new HashMap<Integer, String[]>();
				for (int i = 0; i < 4; i++) {
					temp.put(i, new String[] { "", "", "", "" });
				}
				result.put(x, temp);
			}
			
			return result;
		}
		
		public static Map<Integer, Map<Integer, String[]>> getOne(final GameType type) {
			switch (type) {
			case BEDWARS:
				return bedwarsOne();
			case CRANK:
				return crankOne();
			case GETTHEDROP:
				return getthedropOne();
			case LOBBY:
				return placeholder();
			case NOTHING:
				return placeholder();
			case OITC:
				return oitcOne();
			case TICTACTOE:
				return placeholder();
			case GK:
				return ultraspleefOne();
			case BMT:
				return bmtOne();
			case IF:
				return ifOne();
			case KK:
				return kkOne();
			case KP:
				return kpOne();
			case SG:
				return sgOne();
			case SUV:
				return suvOne();
			}
			
			return placeholder();
		}
		
		public static Map<Integer, Map<Integer, String[]>> getTwo(final GameType type) {
			switch (type) {
			case BEDWARS:
				return bedwarsTwo();
			case CRANK:
				return crankTwo();
			case GETTHEDROP:
				return getthedropTwo();
			case LOBBY:
				return placeholder();
			case NOTHING:
				return placeholder();
			case OITC:
				return oitcTwo();
			case TICTACTOE:
				return placeholder();
			case GK:
				return ultraspleefTwo();
			case BMT:
				return bmtTwo();
			case IF:
				return ifTwo();
			case KK:
				return kkTwo();
			case KP:
				return kpTwo();
			case SG:
				return sgTwo();
			case SUV:
				return suvTwo();
			}
			
			return placeholder();
		}
	}
}
