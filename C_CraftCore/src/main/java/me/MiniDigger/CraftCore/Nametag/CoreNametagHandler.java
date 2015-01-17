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
package me.MiniDigger.CraftCore.Nametag;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.WrappedGameProfile;

import net.minecraft.server.v1_8_R1.EntityTypes;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Nametag.NametagEntity;
import me.MiniDigger.Core.Nametag.NametagHandler;

public class CoreNametagHandler implements NametagHandler {
	
	private Map<UUID, NametagEntity>	entities	= new HashMap<UUID, NametagEntity>();
	private Map<Integer, UUID>	     entityIdMap;
	private static final int[]	     uuidSplit	 = new int[] { 0, 8, 12, 16, 20, 32 };
	private Map<UUID, String>	     tags	     = new HashMap<UUID, String>();
	
	@Override
	public void enable() {
		this.addCustomEntity((Class<? extends net.minecraft.server.v1_8_R1.Entity>) CoreNametagEntity.class, "NametagBat", 65);
		
		int count = 0;
		for (final World world : Core.getCore().getInstance().getServer().getWorlds()) {
			for (final Entity entity : world.getEntities()) {
				if (entity instanceof NametagEntity) {
					entity.remove();
					++count;
				}
			}
		}
		System.out.println(count + " nametag entities removed");
		
		Core.getCore().getInstance().getServer().getPluginManager().registerEvents(this, Core.getCore().getInstance());
		
		/* ====================== CHANGE TAG ================ */
		
		entityIdMap = new HashMap<Integer, UUID>();
		
		for (Player player : Core.getCore().getUserHandler().getOnlinePlayers()) {
			entityIdMap.put(player.getEntityId(), player.getUniqueId());
		}
		
		ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(Core.getCore().getInstance(), PacketType.Play.Server.NAMED_ENTITY_SPAWN) {
			
			@Override
			public void onPacketSending(PacketEvent event) {
				event.getPacket().getGameProfiles()
				        .write(0, getSentName(event.getPacket().getIntegers().read(0), event.getPacket().getGameProfiles().read(0), event.getPlayer()));
			}
		});
	}
	
	@EventHandler
	public void onRespawn(PlayerRespawnEvent event) {
		if (isTagHidden(event.getPlayer())) {
			showTag(event.getPlayer());
			hideTag(event.getPlayer());
		}
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		entities.remove(event.getPlayer().getUniqueId());
		
		/* ============= CHANGE TAG =============== */
		entityIdMap.remove(event.getPlayer().getEntityId());
	}
	
	@Override
	public void disable() {
		for (final NametagEntity entity : entities.values()) {
			((CoreNametagEntity) entity).die();
		}
		entities.clear();
		
		/* ================= CHANGE TAG ======= */
		ProtocolLibrary.getProtocolManager().removePacketListeners(Core.getCore().getInstance());
		
		entityIdMap.clear();
		entityIdMap = null;
	}
	
	private void addCustomEntity(final Class<? extends net.minecraft.server.v1_8_R1.Entity> entityClass, final String name, final int id) {
		try {
			final List<Map<?, ?>> dataMaps = new ArrayList<Map<?, ?>>();
			Field[] declaredFields;
			for (int length = (declaredFields = EntityTypes.class.getDeclaredFields()).length, i = 0; i < length; ++i) {
				final Field f = declaredFields[i];
				if (f.getType().getSimpleName().equals(Map.class.getSimpleName())) {
					f.setAccessible(true);
					dataMaps.add((Map<?, ?>) f.get(null));
				}
			}
			if (dataMaps.get(2).containsKey(id)) {
				dataMaps.get(0).remove(name);
				dataMaps.get(2).remove(id);
			}
			final Method method = EntityTypes.class.getDeclaredMethod("a", Class.class, String.class, Integer.TYPE);
			method.setAccessible(true);
			method.invoke(null, entityClass, name, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public NametagEntity getTagEntity(final Player player) {
		return entities.get(player.getUniqueId());
	}
	
	@Override
	public void hideTag(final Player player) {
		initPlayer(player);
	}
	
	@Override
	public void showTag(final Player player) {
		if (entities.containsKey(player.getUniqueId())) {
			((CoreNametagEntity) entities.get(player.getUniqueId())).die();
			entities.remove(player.getUniqueId());
		}
	}
	
	@Override
	public boolean isTagHidden(final Player player) {
		return entities.containsKey(player.getUniqueId());
	}
	
	private void initPlayer(final Player player) {
		if (!entities.containsKey(player.getUniqueId())) {
			final NametagEntity entity = new CoreNametagEntity(player);
			entities.put(player.getUniqueId(), entity);
		} else {
			final NametagEntity entity = getTagEntity(player);
			entity.hideTag(player);
		}
	}
	
	/* =============== CHANGE TAG ============ */
	private WrappedGameProfile getSentName(int sentEntityId, WrappedGameProfile sent, Player destinationPlayer) {
		Player namedPlayer = Bukkit.getPlayer(entityIdMap.get(sentEntityId));
		if (namedPlayer == null) {
			// They probably were dead when we reloaded
			return sent;
		}
		
		StringBuilder builtUUID = new StringBuilder();
		if (!sent.getId().contains("-")) {
			for (int i = 0; i < uuidSplit.length - 1; i++) {
				builtUUID.append(sent.getId().substring(uuidSplit[i], uuidSplit[i + 1])).append("-");
			}
		} else {
			builtUUID.append(sent.getId());
		}
		
		CoreRecieveNametagEvent newEvent = new CoreRecieveNametagEvent(destinationPlayer, namedPlayer, sent.getName(), UUID.fromString(builtUUID.toString()));
		Core.getCore().getInstance().getServer().getPluginManager().callEvent(newEvent);
		
		return new WrappedGameProfile(newEvent.getUUID(), newEvent.getTag().substring(0, Math.min(newEvent.getTag().length(), 16)));
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		entityIdMap.put(e.getPlayer().getEntityId(), e.getPlayer().getUniqueId());
	}
	
	@Override
	public void refreshPlayer(Player player) {
		for (Player playerFor : player.getWorld().getPlayers()) {
			refreshPlayer(player, playerFor);
		}
	}
	
	@Override
	public void refreshPlayer(final Player player, final Player forWhom) {
		if (player != forWhom && player.getWorld() == forWhom.getWorld() && forWhom.canSee(player)) {
			forWhom.hidePlayer(player);
			Core.getCore().getInstance().getServer().getScheduler().scheduleSyncDelayedTask(Core.getCore().getInstance(), new Runnable() {
				
				public void run() {
					forWhom.showPlayer(player);
				}
			}, 2);
		}
	}
	
	@Override
	public void refreshPlayer(Player player, Set<Player> forWhom) {
		for (Player playerFor : forWhom) {
			refreshPlayer(player, playerFor);
		}
	}
	
	@Override
	public void setTag(UUID id, String value) {
		if (tags.containsKey(id)) {
			tags.remove(id);
		}
		tags.put(id, value);
	}
	
	@Override
	public String getTag(UUID id) {
		if (!tags.containsKey(id)) {
			tags.put(id, Bukkit.getPlayer(id).getDisplayName());
		}
		return tags.get(id);
		
	}
	
	@EventHandler(priority=EventPriority.HIGH)
	public void tag(CoreRecieveNametagEvent e) {
		if(tags.containsKey(e.getNamedPlayer().getUniqueId())){
			e.setTag(tags.get(e.getNamedPlayer().getUniqueId()));
		}
	}
}
