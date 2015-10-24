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
package me.MiniDigger.CraftCore.Bar;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Bar.BarHandler;

public class CoreBarHandler implements BarHandler {

	private final Map<UUID, CoreFakeDragon> DRAGONS = new HashMap<>();

	@Override
	public boolean hasBar(final Player player) {
		return DRAGONS.containsKey(player.getUniqueId()) && DRAGONS.get(player.getUniqueId()) != null;
	}

	@Override
	public void removeBar(final Player player) {
		if (hasBar(player)) {
			sendPacket(player, DRAGONS.get(player.getUniqueId()).getDestroyPacket());
			DRAGONS.remove(player.getUniqueId());
		}
	}

	@Override
	public void setBar(final Player player, String text, float health) {
		CoreFakeDragon dragon = DRAGONS.containsKey(player.getUniqueId()) ? DRAGONS.get(player.getUniqueId()) : null;

		if (text.length() > 64) {
			text = text.substring(0, 63);
		}
		if (health > 100) {
			health = 100;
		}
		if (health < 0) {
			health = 0;
		}

		if (text.isEmpty() && dragon != null) {
			removeBar(player);
		}

		if (dragon == null) {
			dragon = new CoreFakeDragon(player.getLocation().add(0, -200, 0), text, health);
			sendPacket(player, dragon.getSpawnPacket());
			DRAGONS.put(player.getUniqueId(), dragon);
		} else {
			dragon.setName(text);
			dragon.setHealth(health);
			sendPacket(player, dragon.getMetaPacket(dragon.getWatcher()));
			sendPacket(player, dragon.getTeleportPacket(player.getLocation().add(0, -200, 0)));
		}

	}

	@Override
	public void setBar(final Player player, String text, double health) {
		CoreFakeDragon dragon = DRAGONS.containsKey(player.getUniqueId()) ? DRAGONS.get(player.getUniqueId()) : null;

		if (text.length() > 64) {
			text = text.substring(0, 63);
		}
		if (health > 200) {
			health = 200;
		}
		if (health < 0) {
			health = 0;
		}

		if (text.isEmpty() && dragon != null) {
			removeBar(player);
		}

		if (dragon == null) {
			dragon = new CoreFakeDragon(player.getLocation().add(0, -200, 0), text, (float) (200 / health));
			sendPacket(player, dragon.getSpawnPacket());
			DRAGONS.put(player.getUniqueId(), dragon);
		} else {
			dragon.setName(text);
			dragon.setHealth(health);
			sendPacket(player, dragon.getMetaPacket(dragon.getWatcher()));
			sendPacket(player, dragon.getTeleportPacket(player.getLocation().add(0, -200, 0)));
		}

	}

	@Override
	public void removeAllStatusBars() {
		for (final Player each : Core.getCore().getUserHandler().getOnlinePlayers()) {
			removeBar(each);
		}
	}

	@Override
	public void setAllStatusBars(final String text, final float percent) {
		for (final Player each : Core.getCore().getUserHandler().getOnlinePlayers()) {
			setBar(each, text, percent);
		}
	}

	private static void sendPacket(final Player player, final Object packet) {
		try {
			final Object nmsPlayer = Core.getCore().getReflectionUtil().getHandle(player);
			final Field connectionField = nmsPlayer.getClass().getField("playerConnection");
			final Object connection = connectionField.get(nmsPlayer);
			final Method sendPacket = Core.getCore().getReflectionUtil().getMethod(connection.getClass(), "sendPacket");
			sendPacket.invoke(connection, packet);
		}
		catch (final Exception e) {
			e.printStackTrace();
		}
	}
}
