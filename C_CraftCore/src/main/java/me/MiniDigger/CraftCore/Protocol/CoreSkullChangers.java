/*******************************************************************************
 * Test
 *******************************************************************************/
/**
 * ██████████████████████████████████████████████████████████████████████████████████████████████████████████████
 * █░░░░░░░░░░░░░░█░░░░░░██░░░░░░█░░░░░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░░░░░░░░░░░███░░░░░░░░░░░░░░█
 * █░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀▄▀░░███░░▄▀▄▀▄▀▄▀▄▀░░█
 * █░░░░░░▄▀░░░░░░█░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░░░░░█░░▄▀░░░░░░░░░░█░░▄▀░░░░░░▄▀░░█░░▄▀░░░░░░░░▄▀░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░█████████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░████░░▄▀░░███░░▄▀░░█████████
 * █████░░▄▀░░█████░░▄▀░░░░░░▄▀░░█░░▄▀░░░░░░░░░░█░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░░░▄▀░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀▄▀░░███░░▄▀▄▀▄▀▄▀▄▀░░█
 * █████░░▄▀░░█████░░▄▀░░░░░░▄▀░░█░░▄▀░░░░░░░░░░█░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░▄▀░░░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░█████████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░██░░▄▀░░█████░░▄▀░░█████████
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░░░░░█░░▄▀░░░░░░░░░░█░░▄▀░░░░░░▄▀░░█░░▄▀░░██░░▄▀░░░░░░█░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░██░░▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█
 * █████░░░░░░█████░░░░░░██░░░░░░█░░░░░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░██░░░░░░░░░░█░░░░░░░░░░░░░░█
 * ██████████████████████████████████████████████████████████████████████████████████████████████████████████████
 */

/**
 *
 * ██████████████████████████████████████████████████████████████████████████████████████████████████████████████
 * █░░░░░░░░░░░░░░█░░░░░░██░░░░░░█░░░░░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░░░░░░░░░░░███░░░░░░░░░░░░░░█
 * █░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀▄▀░░███░░▄▀▄▀▄▀▄▀▄▀░░█
 * █░░░░░░▄▀░░░░░░█░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░░░░░█░░▄▀░░░░░░░░░░█░░▄▀░░░░░░▄▀░░█░░▄▀░░░░░░░░▄▀░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░█████████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░████░░▄▀░░███░░▄▀░░█████████
 * █████░░▄▀░░█████░░▄▀░░░░░░▄▀░░█░░▄▀░░░░░░░░░░█░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░░░▄▀░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀▄▀░░███░░▄▀▄▀▄▀▄▀▄▀░░█
 * █████░░▄▀░░█████░░▄▀░░░░░░▄▀░░█░░▄▀░░░░░░░░░░█░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░▄▀░░░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░█████████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░██░░▄▀░░█████░░▄▀░░█████████
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░░░░░█░░▄▀░░░░░░░░░░█░░▄▀░░░░░░▄▀░░█░░▄▀░░██░░▄▀░░░░░░█░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░██░░▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█
 * █████░░░░░░█████░░░░░░██░░░░░░█░░░░░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░██░░░░░░░░░░█░░░░░░░░░░░░░░█
 * ██████████████████████████████████████████████████████████████████████████████████████████████████████████████
 */

package me.MiniDigger.CraftCore.Protocol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Protocol.SkullChangers;
import me.MiniDigger.Core.Stats.StatsType;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Skull;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import com.comphenix.packetwrapper.WrapperPlayServerTileEntityData;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.nbt.NbtCompound;

public class CoreSkullChangers implements SkullChangers {
	
	private final ArrayList<SkullChanger>	           skullchangers	= new ArrayList<>();
	private final ArrayList<Location>	               last_seen_skulls	= new ArrayList<>();
	private final HashMap<Location, ArrayList<String>>	players_skulls	= new HashMap<>();
	
	@Override
	public void update() {
		for (final Location loc : last_seen_skulls) {
			for (final Entity e : loc.getWorld().getChunkAt(loc).getEntities()) {
				if (e != null && e.getType() == EntityType.PLAYER) {
					if (loc.getBlock().getState() instanceof Skull) {
						if (!Core.getCore().getNPCHandler().getFactory().isNPC(e)) {
							sendSkullChange((Player) e, loc);
						}
					}
				}
			}
		}
	}
	
	@Override
	public void init() {
		addSkullChanger(new SkullChanger("[Fame]", "skullchanger.create.me", "Zeigt den Famen Player an") {
			
			@Override
			public String getValue(final Player paramPlayer, final Location paramLocation) {
				return Core.getCore().getProtocolHandler().getFame();
			}
		});
		
		addSkullChanger(new SkullChanger("[Player]", "skullchanger.create.playername", "Zeigt den Spielernamen") {
			
			@Override
			public String getValue(final Player player, final Location loc) {
				return player.getName();
			}
		});
		addSkullChanger(new SkullChanger("[T_", "skullchanger.create.stats", "Zeigt den Besten in dem StatsType an") {
			
			@Override
			public String getValue(final Player player, final Location loc) {
				if (!(loc.getBlock().getState() instanceof Skull) || ((Skull) loc.getBlock().getState()).getOwner() == null) {
					return "MHF_Question";
				}
				String s = ((Skull) loc.getBlock().getState()).getOwner();
				s = s.replace("[S_", "");
				s = s.replace("]", "");
				s = s.replace("CR", "CRANK");
				s = s.replace("EXPED", "EXPLODED");
				StatsType type = null;
				try {
					type = StatsType.valueOf(s);
				} catch (final Exception ex) {
					return "MHF_Question";
				}
				
				final List<String> tops = Core.getCore().getStatsHandler().getTop(type, 1);
				return ChatColor.BOLD + "" + tops.get(0).split(":")[0];
			}
		});
	}
	
	private PacketContainer modifySkull(final PacketContainer pa, final Player player) {
		final String SKULL_NICK_KEY = "SkullOwner";
		final WrapperPlayServerTileEntityData p = new WrapperPlayServerTileEntityData(pa);
		final boolean forceMatch = false;
		final Location loc = new Location(player.getWorld(), p.getX(), p.getY(), p.getZ());
		final NbtCompound nbtc = (NbtCompound) p.getNbtData();
		if (nbtc.containsKey(SKULL_NICK_KEY)) {
			final String skullName = nbtc.getString(SKULL_NICK_KEY);
			
			for (final SkullChanger c : skullchangers) {
				if (forceMatch || skullName.equalsIgnoreCase(c.getKey())) {
					if (!last_seen_skulls.contains(loc)) {
						last_seen_skulls.add(loc);
					}
					ArrayList<String> players = players_skulls.get(loc);
					if (players == null) {
						players = new ArrayList<>();
					}
					if (!players.contains(player.getName())) {
						players.add(player.getName());
					}
					players_skulls.remove(loc);
					players_skulls.put(loc, players);
					
					nbtc.put(SKULL_NICK_KEY, c.getValue(player, new Location(player.getWorld(), p.getX(), p.getY(), p.getZ())));
				}
			}
		}
		
		return p.getHandle();
	}
	
	private void sendSkullChange(final Player player, final Location loc) {
		final PacketContainer result = Core.getCore().getProtocolHandler().getManager().createPacket(PacketType.Play.Server.TILE_ENTITY_DATA);
		try {
			@SuppressWarnings("unused") final Skull skull = (Skull) loc.getBlock().getState();
			// TODO Fix Update of SkullChanger
			// NbtCompound tag = new NbtFactory().
			// tag.setString("id", "Skull");
			// tag.setInt("x", (int) loc.getX());
			// tag.setInt("y", (int) loc.getY());
			// tag.setInt("z", (int) loc.getZ());
			// tag.setByte("SkullType", (byte) 4);
			// tag.setByte("Rot", (byte) 0);
			// tag.setString("ExtraType", 0 + "");
			result.getSpecificModifier(Integer.TYPE).write(0, (int) loc.getX());
			result.getSpecificModifier(Integer.TYPE).write(1, (int) loc.getY());
			result.getSpecificModifier(Integer.TYPE).write(2, (int) loc.getZ());
			result.getSpecificModifier(Integer.TYPE).write(3, 4);
			
			Core.getCore().getProtocolHandler().getManager().sendServerPacket(player, result);
		} catch (final Exception e) {
			e.printStackTrace();
			return;
		}
	}
	
	@Override
	public void addSkullChanger(final SkullChanger changer) {
		if (changer == null) {
			throw new IllegalArgumentException("Changer cannot be null!");
		}
		final String key = changer.getKey();
		for (final SkullChanger sc : skullchangers) {
			if (sc.getKey().equals(key)) {
				skullchangers.remove(sc);
			}
		}
		skullchangers.add(changer);
	}
	
	@Override
	public void removeSkullChanger(final SkullChanger changer) {
		skullchangers.remove(changer);
	}
	
	@Override
	public List<SkullChanger> getSkullChangerList() {
		return skullchangers;
	}
	
	@Override
	public void handlePacket(final PacketEvent event) {
		event.setPacket(modifySkull(event.getPacket(), event.getPlayer()));
	}
	
}
