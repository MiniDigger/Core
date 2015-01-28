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
import java.util.UUID;

import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.reflect.StructureModifier;

import net.minecraft.server.v1_8_R1.EntityTypes;

import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Nametag.NametagEntity;
import me.MiniDigger.Core.Nametag.NametagHandler;

public class CoreNametagHandler implements NametagHandler {
	
	private final Map<UUID, NametagEntity>	entities	= new HashMap<UUID, NametagEntity>();
	
	@Override
	public void enable() {
		addCustomEntity(CoreNametagEntity.class, "NametagBat", 65);
		
		int count = 0;
		for (final World world : Core.getCore().getInstance().getServer().getWorlds()) {
			for (final Entity entity : world.getEntities()) {
				if (entity instanceof NametagEntity) {
					entity.remove();
					++count;
				}
			}
		}
		
		Core.getCore().getInstance().getServer().getPluginManager().registerEvents(this, Core.getCore().getInstance());
		
	}
	
	public List<String> listModifier(final PacketContainer c) {
		final List<String> result = new ArrayList<String>();
		
		for (final Method m : c.getClass().getMethods()) {
			if (m.getName().startsWith("get")) {
				if (m.getReturnType().getName().contains("StructureModifier")) {
					try {
						final StructureModifier<?> mod = (StructureModifier<?>) m.invoke(c);
						result.add(m.getName() + ": " + mod.size());
					} catch (final Exception e) {
						// e.printStackTrace();
					}
				}
			}
		}
		
		return result;
	}
	
	@EventHandler
	public void onRespawn(final PlayerRespawnEvent event) {
		if (isTagHidden(event.getPlayer())) {
			showTag(event.getPlayer());
			hideTag(event.getPlayer());
		}
	}
	
	@EventHandler
	public void onQuit(final PlayerQuitEvent event) {
		entities.remove(event.getPlayer().getUniqueId());
	}
	
	@Override
	public void disable() {
		for (final NametagEntity entity : entities.values()) {
			((CoreNametagEntity) entity).die();
		}
		entities.clear();
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
		} catch (final Exception e) {
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
	
	@Override
	public void setTag(final UUID uniqueId, final String string) {
		
	}
}
