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
package me.MiniDigger.CraftCore.Protocol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.reflect.FieldAccessException;
import com.comphenix.protocol.wrappers.BlockPosition;
import com.comphenix.protocol.wrappers.WrappedChatComponent;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Prefix.Prefix;
import me.MiniDigger.Core.Protocol.SignChangers;
import me.MiniDigger.Core.Stats.StatsType;
import me.MiniDigger.Core.User.User;

import me.MiniDigger.CraftCore.Protocol.SignGUI.NMSUtils;

import mkremins.fanciful.FancyMessage;

public class CoreSignChangers implements SignChangers {
	
	private final ArrayList<String>	                   edit	           = new ArrayList<>();
	private final ArrayList<SignChanger>	           signchangers	   = new ArrayList<>();
	private final ArrayList<Location>	               last_seen_signs	= new ArrayList<>();
	private final HashMap<Location, ArrayList<String>>	players_signs	= new HashMap<>();
	public final ArrayList<String>	                   justJoined	   = new ArrayList<>();
	
	@SuppressWarnings("unchecked")
	@Override
	public void update(final List<UUID> noUpdates) {
		for (final Location loc : (ArrayList<Location>) last_seen_signs.clone()) {
			for (final Entity e : loc.getWorld().getChunkAt(loc).getEntities()) {
				if (e != null && e.getType() == EntityType.PLAYER) {
					if (loc.getBlock().getState() instanceof Sign) {
						if (!justJoined.contains(((Player) e).getName())) {
							if (!noUpdates.contains(((Player) e).getUniqueId())) {
								sendSignChange((Player) e, (Sign) loc.getBlock().getState());
							}
						} else {
							justJoined.remove(((Player) e).getName());
						}
					}
				}
			}
		}
	}
	
	@Override
	public void handlePacket(final PacketEvent event) {
		if (event.getPacketType() == PacketType.Play.Server.UPDATE_SIGN) {
			try {
				boolean b = true;
				if (edit.contains(event.getPlayer().getName())) {
					b = false;
				}
				event.setPacket(modifySign(event.getPacket(), event.getPlayer(), b));
			} catch (final FieldAccessException e) {
				Core.getCore().getInstance().error("Couldn't access field");
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void init() {
		addSignChanger(new SignChanger("[Player]", "signchanger.create.player", "Zeigt den Spielernamen an") {
			
			@Override
			public String getValue(final Player player, final Location loc) {
				if (!(loc.getBlock().getState() instanceof Sign)) {
					return ChatColor.RED + "fail";
				}
				return player.getName();
			}
		});
		addSignChanger(new SignChanger("[Tokens]", "signchanger.create.tokens", "Zeigt wie viele Tokens der Spieler hat") {
			
			@Override
			public String getValue(final Player player, final Location loc) {
				if (!(loc.getBlock().getState() instanceof Sign)) {
					return ChatColor.RED + "fail";
				}
				return Core.getCore().getStatsHandler().get(player.getUniqueId()).get(StatsType.Common.TOKENS) + "";
			}
		});
		addSignChanger(new SignChanger("[Paesse]", "signchanger.create.paesse", "Zeigt wie viele Pässe der Spieler hat") {
			
			@Override
			public String getValue(final Player player, final Location loc) {
				if (!(loc.getBlock().getState() instanceof Sign)) {
					return ChatColor.RED + "fail";
				}
				return Core.getCore().getStatsHandler().get(player.getUniqueId()).get(StatsType.Common.PAESSE) + "";
			}
		});
		addSignChanger(new SignChanger("[Warns]", "signchanger.create.warns", "Zeigt wie vieler Warns der Spieler hat") {
			
			@Override
			public String getValue(final Player player, final Location loc) {
				if (!(loc.getBlock().getState() instanceof Sign)) {
					return ChatColor.RED + "fail";
				}
				final int i = 0;
				// if (Core.getSQLHandler().getModProvider()
				// .loadList(player.getName()) == null) {
				// return 0 + "";
				// }
				// for (final ModEntry entry : Core.getSQLHandler()
				// .getModProvider().loadList(player.getName())) {
				// if (entry.getAction().equals(Action.WARN.name())) {
				// i++;
				// }
				// }
				return i + "";
			}
		});
		addSignChanger(new SignChanger("[Teleport]", "signchanger.create.teleport", "Teleportiert den Spieler zum angegebenen Server") {
			
			@Override
			public String getValue(final Player p, final Location loc) {
				if (!(loc.getBlock().getState() instanceof Sign)) {
					return ChatColor.RED + "fail";
				}
				return "";
			}
		});
		addSignChanger(new SignChanger(ChatColor.RED + "" + ChatColor.BOLD + ChatColor.UNDERLINE + "[Full]", "signchanger.create.full",
		        "Wichtig für die Teleport Schilder") {
			
			@Override
			public String getValue(final Player p, final Location loc) {
				if (!(loc.getBlock().getState() instanceof Sign)) {
					return ChatColor.RED + "fail";
				}
				if (Core.getCore().getUserHandler().get(p.getUniqueId()).hasPermission("premium.joinfull")) {
					return ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "[KICKJOIN]";
				} else {
					return ChatColor.RED + "" + ChatColor.BOLD + ChatColor.UNDERLINE + "[Full]";
				}
			}
		});
		addSignChanger(new SignChanger("[R_", "signchanger.create.rank", "Zeigt den Rank in dem StatsType an") {
			
			@Override
			public String getValue(final Player p, final Location loc) {
				if (!(loc.getBlock().getState() instanceof Sign)) {
					return ChatColor.RED + "fail";
				}
				final Sign sign = (Sign) loc.getBlock().getState();
				for (String s : sign.getLines()) {
					if (s.startsWith("[R_") || (s.startsWith("&") && s.contains("[R_"))) {
						s = s.replace("[R_", "");
						s = s.replace("]", "");
						s = s.replace("CR", "CRANK");
						s = s.replace("EXPED", "EXPLODED");
						StatsType type = null;
						try {
							type = StatsType.valueOf(s);
						} catch (final Exception ex) {
							return ChatColor.RED + "fail";
						}
						
						int rank = 1;
						for (final String top : Core.getCore().getStatsHandler().getTop(type, -1)) {
							final String name = top.split(":")[0];
							if (name.equalsIgnoreCase(p.getName())) {
								return ChatColor.BOLD + "" + rank + "";
							}
							rank++;
						}
					}
				}
				return ChatColor.RED + "fail";
			}
		});
		addSignChanger(new SignChanger("[T_", "signchanger.create.top", "Zeig den Besten in dem StatsType an") {
			
			@Override
			public String getValue(final Player p, final Location loc) {
				if (!(loc.getBlock().getState() instanceof Sign)) {
					return ChatColor.RED + "fail";
				}
				final Sign sign = (Sign) loc.getBlock().getState();
				for (String s : sign.getLines()) {
					if (s.startsWith("[T_") || (s.startsWith("&") && s.contains("[T_"))) {
						s = s.replace("[T_", "");
						s = s.replace("]", "");
						s = s.replace("CR", "CRANK");
						s = s.replace("EXPED", "EXPLODED");
						int top = 1;
						try {
							top = Integer.parseInt(s.charAt(0) + "");
						} catch (final Exception ex) {
							System.out.println("FAILED TO PARSE INT " + s.charAt(0) + " FOR STATS SIGN AT " + loc.toString());
						}
						s = s.substring(1);
						StatsType type = null;
						try {
							type = StatsType.valueOf(s);
						} catch (final Exception ex) {
							return ChatColor.RED + "fail";
						}
						
						final List<String> tops = Core.getCore().getStatsHandler().getTop(type, top);
						try {
							return ChatColor.BOLD + "" + tops.get(top - 1).split(":")[0];
						} catch (final Exception ex) {
							return ChatColor.RED + "fail";
						}
					}
				}
				return ChatColor.RED + "fail";
			}
		});
		addSignChanger(new SignChanger("[S_", "signchanger.create.stats", "Zeigt den Wert in dem StatsType an") {
			
			@Override
			public String getValue(final Player p, final Location loc) {
				if (!(loc.getBlock().getState() instanceof Sign)) {
					return ChatColor.RED + "fail";
				}
				final Sign sign = (Sign) loc.getBlock().getState();
				for (String s : sign.getLines()) {
					if (s.startsWith("[S_") || (s.startsWith("&") && s.contains("[S_"))) {
						s = s.replaceAll("&+", "");
						s = s.replace("[S_", "");
						s = s.replace("]", "");
						s = s.replace("CR", "CRANK");
						s = s.replace("EXPED", "EXPLODED");
						StatsType type = null;
						try {
							type = StatsType.valueOf(s);
						} catch (final Exception ex) {
							return ChatColor.RED + "fail";
						}
						return ChatColor.BOLD + "" + Core.getCore().getStatsHandler().get(p.getUniqueId()).get(type);
					}
				}
				return ChatColor.RED + "fail";
			}
		});
		
		addSignChanger(new SignChanger("[Fame]", "signchanger.create.fame", "Zeigt den Famen Player an") {
			
			@Override
			public String getValue(final Player paramPlayer, final Location paramLocation) {
				return Core.getCore().getProtocolHandler().getFame();
			}
		});
		
		addSignChanger(new SignChanger("[Team]", "signchangers.create.team", "Zeigt das Team schön formatiert an") {
			
			@Override
			public String getValue(final Player p, final Location loc) {
				if (!(loc.getBlock().getState() instanceof Sign)) {
					return ChatColor.RED + "fail";
				}
				final Sign sign = (Sign) loc.getBlock().getState();
				sign.setLine(0, "Klicke hier, um");
				sign.setLine(1, "Team " + sign.getLine(1));
				sign.setLine(2, "Zu beizutreten!");
				sign.setLine(3, "");
				return "";
			}
		});
		
		addSignChanger(new SignChanger("[GameL]", "signchangers.create.team", "Zeigt das Team schön formatiert an") {
			
			@Override
			public String getValue(final Player p, final Location loc) {
				if (!(loc.getBlock().getState() instanceof Sign)) {
					return ChatColor.RED + "fail";
				}
				final Sign sign = (Sign) loc.getBlock().getState();
				sign.setLine(0, "Klicke hier, um");
				sign.setLine(1, "dein aktuelles");
				sign.setLine(2, "Spiel zu");
				sign.setLine(3, "verlassen");
				return "";
			}
		});
	}
	
	private void handleSignEdit(final PlayerInteractEvent event, final Sign sign) {
		final User user = Core.getCore().getUserHandler().get(event.getPlayer().getUniqueId());
		String[] lines = sign.getLines();
		lines = Core.getCore().getChatColorUtil().replaceMcToAnd(sign.getLines());
		// new CoreSignGUI().open(event.getPlayer(), lines, new
		// SignGUI.SignGUIListener() {
		//
		// @Override
		// public void onSignDone(final Player player, String[] lines) {
		// if (user.hasPermission("builder.changesigns")) {
		// // Color
		// lines = Core.getCore().getChatColorUtil().replaceAndToMc(lines);
		// sign.setLine(0, lines[0]);
		// sign.setLine(1, lines[1]);
		// sign.setLine(2, lines[2]);
		// sign.setLine(3, lines[3]);
		// sign.update();
		// user.sendMessage(Prefix.API.getPrefix().then("Sign editiert!").color(ChatColor.GREEN));
		// edit.remove(player.getName());
		// } else {
		// user.sendMessage(Prefix.SECURITY.getPrefix().then("Du darfst keine Schilder editieren!").color(ChatColor.RED));
		// }
		// }
		// });
		if (!user.hasPermission("builder.changesigns")) {
			user.sendMessage(Prefix.API.getPrefix().then("Du hast keine Rechte, Schilder zu editieren!").color(ChatColor.RED));
		}
		NMSUtils.sendPacket(event.getPlayer(), NMSUtils.getSignChangePacket(sign.getBlock(), lines));
		NMSUtils.sendEditor(event.getPlayer(), sign.getBlock());
	}
	
	private PacketContainer modifySign(final PacketContainer psign, final Player player, final boolean edit) {
		// BlockPosition pos = BlockPosition.fromLong(psign.getLongs().read(0));
		final BlockPosition pos = psign.getBlockPositionModifier().read(0);
		final Location location = new Location(player.getWorld(), pos.getX(), pos.getY(), pos.getZ());
		
		if (!(location.getBlock().getState() instanceof Sign)) {
			return psign;
		}
		
		if (!last_seen_signs.contains(location)) {
			last_seen_signs.add(location);
		}
		
		ArrayList<String> players = players_signs.get(location);
		if (players == null) {
			players = new ArrayList<>();
		}
		if (!players.contains(player.getName())) {
			players.add(player.getName());
		}
		
		players_signs.remove(location);
		players_signs.put(location, players);
		
		// for (final Method m : psign.getClass().getMethods()) {
		// if (m.getName().startsWith("get")) {
		// if
		// (m.getReturnType().getName().startsWith("com.comphenix.protocol.reflect.StructureModifier"))
		// {
		// try {
		// final StructureModifier<?> mod = (StructureModifier<?>)
		// m.invoke(psign);
		// if (mod.size() == 0) {
		// continue;
		// }
		// System.out.println(m.getName() + ": " + mod.size());
		// } catch (final Exception e) {
		// if (e.getMessage() != null &&
		// e.getMessage().contains("wrong number of arguments")) {
		// System.out.println("wron nums");
		// } else {
		// System.out.println("oh noes");
		// e.printStackTrace();
		// }
		// }
		// }
		// }
		// }
		
		final WrappedChatComponent[] lines = psign.getChatComponentArrays().read(0);
		
		WrappedChatComponent[] newLines = { lines[0], lines[1], lines[2], lines[3] };
		
		if (newLines[0] != null && newLines[0].getJson() != null && (newLines[0].getJson().contains("[Teleport]") || newLines[0].getJson().contains("[Game]")) && edit) {
			if (Core.getCore().getServerHandler().getServerInfo(lines) != null) {
				for (int i = 0; i < 4; i++) {
					lines[i].setJson(new FancyMessage(((Sign) location.getBlock().getState()).getLines()[i]).toJSONString());
				}
				newLines = Core.getCore().getServerHandler().getServerInfo(lines);
			} else {
				Bukkit.getScheduler().runTaskLater(Core.getCore().getInstance(), new Runnable() {
					
					@Override
					public void run() {
						sendSignChange(player, (Sign) location.getBlock().getState());
					}
				}, 10L);
			}
		}
		
		String value = null;
		String key = null;
		for (final SignChanger c : signchangers) {
			value = null;
			key = c.getKey();
			if (key != null) {
				for (int i = 0; i < newLines.length; i++) {
					if (newLines[i] == null || newLines[i].getJson() == null) {
						continue;
					}
					if (newLines[i].getJson().contains(key)) {
						boolean clear = false;
						if (key.startsWith("&y") || newLines[i].getJson().contains("&y") || key.startsWith("[S_") || key.startsWith("[R_") || key.startsWith("[T_")) {
							clear = true;
						}
						
						if (value == null) {
							value = c.getValue(player, location);
							if (value == null) {
								break;
							}
						}
						if (!edit) {
							break;
						}
						if (newLines[i].getJson().contains("&z" + key)) {
							newLines[i].setJson(newLines[i].getJson().replace("&z", ""));
						} else {
							if (clear) {
								newLines[i].setJson(new FancyMessage(value).toJSONString());
							} else {
								newLines[i].setJson(newLines[i].getJson().replace(key, value));
							}
						}
					}
				}
			}
		}
		// shorten //TODO Renabled sign shortening
		// for (int i = 0; i < newLines.length; i++) {
		// if (newLines[i].getText().length() > 15) {
		// if ((i < newLines.length - 1) && (newLines[(i +
		// 1)].getText().isEmpty())) {
		// newLines[(i + 1)] = new
		// ChatComponentText(newLines[i].getText().substring(15));
		// }
		// newLines[i] = new
		// ChatComponentText(newLines[i].getText().substring(0, 15));
		// }
		// }
		
		final PacketContainer out = new PacketContainer(PacketType.Play.Server.UPDATE_SIGN);
		out.getBlockPositionModifier().write(0, pos);
		out.getChatComponentArrays().write(0, lines);
		return out;
	}
	
	private void sendSignChange(final Player player, final Sign sign) {
		final String[] lin = sign.getLines();
		final PacketContainer result = Core.getCore().getProtocolHandler().getManager().createPacket(PacketType.Play.Server.UPDATE_SIGN);
		final BlockPosition pos = new BlockPosition(sign.getX(), sign.getY(), sign.getZ());
		final WrappedChatComponent[] lines = new WrappedChatComponent[4];
		for (int i = 0; i < 4; i++) {
			lines[i] = WrappedChatComponent.fromText(lin[i]);
		}
		
		try {
			result.getBlockPositionModifier().write(0, pos);
			result.getChatComponentArrays().write(0, lines);
			Core.getCore().getProtocolHandler().getManager().sendServerPacket(player, result);
		} catch (final Exception e) {
			e.printStackTrace();
			return;
		}
	}
	
	@Override
	public void addSignChanger(final SignChanger changer) {
		if (changer == null) {
			throw new IllegalArgumentException("Changer cannot be null!");
		}
		final String key = changer.getKey();
		for (final SignChanger sc : signchangers) {
			if (sc.getKey().equals(key)) {
				signchangers.remove(sc);
			}
		}
		signchangers.add(changer);
	}
	
	@Override
	public void removeSignChanger(final SignChanger changer) {
		signchangers.remove(changer);
	}
	
	@Override
	public List<SignChanger> getSignChangerList() {
		return signchangers;
	}
	
	@Override
	@EventHandler
	public void handleInteract(final PlayerInteractEvent event) {
		if ((event.getAction() == org.bukkit.event.block.Action.RIGHT_CLICK_BLOCK) && (event.getClickedBlock() != null)) {
			if ((event.getClickedBlock().getState() instanceof Sign)) {
				final Sign sign = (Sign) event.getClickedBlock().getState();
				if (event.getPlayer().isSneaking()) {
					edit.add(event.getPlayer().getName());
					sendSignChange(event.getPlayer(), sign);
					// SignEdit //TO DO This is not even efficient //I don't
					// care right now ;D
					handleSignEdit(event, sign);
					edit.remove(event.getPlayer().getName());
				}
				sendSignChange(event.getPlayer(), sign);
			}
		}
		
	}
}
