/**
 * █████████████████████████████████████████████████████████████████████████████
 * ████████████████████████████████████
 * █░░░░░░░░░░░░░░█░░░░░░██░░░░░░█░░░░░░░░░░░░░░████░░░░░░░░░░░░░░█░░░░░░░░░░░░░
 * ░█░░░░░░░░░░░░░░░░███░░░░░░░░░░░░░░█
 * █░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░████░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░
 * ░█░░▄▀▄▀▄▀▄▀▄▀▄▀░░███░░▄▀▄▀▄▀▄▀▄▀░░█
 * █░░░░░░▄▀░░░░░░█░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░░░░░████░░▄▀░░░░░░░░░░█░░▄▀░░░░░░▄▀░
 * ░█░░▄▀░░░░░░░░▄▀░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░████████████░░▄▀░░█████████░░▄▀░░██░░▄▀░
 * ░█░░▄▀░░████░░▄▀░░███░░▄▀░░█████████
 * █████░░▄▀░░█████░░▄▀░░░░░░▄▀░░█░░▄▀░░░░░░░░░░████░░▄▀░░█████████░░▄▀░░██░░▄▀░
 * ░█░░▄▀░░░░░░░░▄▀░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░████░░▄▀░░█████████░░▄▀░░██░░▄▀░
 * ░█░░▄▀▄▀▄▀▄▀▄▀▄▀░░███░░▄▀▄▀▄▀▄▀▄▀░░█
 * █████░░▄▀░░█████░░▄▀░░░░░░▄▀░░█░░▄▀░░░░░░░░░░████░░▄▀░░█████████░░▄▀░░██░░▄▀░
 * ░█░░▄▀░░░░░░▄▀░░░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░████████████░░▄▀░░█████████░░▄▀░░██░░▄▀░
 * ░█░░▄▀░░██░░▄▀░░█████░░▄▀░░█████████
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░░░░░████░░▄▀░░░░░░░░░░█░░▄▀░░░░░░▄▀░
 * ░█░░▄▀░░██░░▄▀░░░░░░█░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░████░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░
 * ░█░░▄▀░░██░░▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█
 * █████░░░░░░█████░░░░░░██░░░░░░█░░░░░░░░░░░░░░████░░░░░░░░░░░░░░█░░░░░░░░░░░░░
 * ░█░░░░░░██░░░░░░░░░░█░░░░░░░░░░░░░░█
 * █████████████████████████████████████████████████████████████████████████████
 * ████████████████████████████████████
 *
 * Copyright © MiniDigger and others - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Martin Benndorf <admin@minidigger.me>, 2013-2015 and others
 */
package me.MiniDigger.CraftCore.Util;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import org.spigotmc.AsyncCatcher;
import org.spigotmc.RestartCommand;
import org.spigotmc.SpigotConfig;
import org.spigotmc.WatchdogThread;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Prefix.Prefix;
import me.MiniDigger.Core.Util.ShutdownUtil;
import me.MiniDigger.CraftCore.Packet.Packets.ServerPacket;
import me.MiniDigger.CraftCore.Server.CoreServer;
import mkremins.fanciful.FancyMessage;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.MinecraftServer;

public class CoreShutdownUtil implements ShutdownUtil {

	private static BukkitTask task;

	public static boolean isShuttingDown() {
		return task != null;
	}

	@Override
	public void doShutdown() {
		AsyncCatcher.enabled = false;
		if (task != null) {
			Bukkit.getScheduler().cancelTask(task.getTaskId());
			task.cancel();
			shutdown();
			return;
		}

		Bukkit.getScheduler().cancelAllTasks();
		Core.getCore().getPacketHandler().sendPacket(new ServerPacket(CoreServer.getForThis(false)));

		for (final Player p : Core.getCore().getUserHandler().getOnlinePlayers()) {
			Core.getCore().getBarHandler().setBar(p, ChatColor.RED + "Der Server wird nun neugestartet!", 100F);
			Core.getCore().getUserHandler().get(p.getUniqueId()).sendMessage(Prefix.API.getPrefix().then("Der Server wird in 10 Sekunden neugestartet!").color(ChatColor.RED));
		}
		Prefix.API.getPrefix().then("Der Server wird in 10 Sekunden neugestartet!").color(ChatColor.RED).send(Bukkit.getConsoleSender());

		Core.getCore().getUpdateHandler().update();

		task = Bukkit.getScheduler().runTaskLater(Core.getCore().getInstance(), new Runnable() {

			@Override
			public void run() {
				shutdown();
			}
		}, 10 * 20);
	}

	private void shutdown() {
		for (final Player p : Core.getCore().getUserHandler().getOnlinePlayers()) {
			FancyMessage msg = Prefix.CORE.getPrefix().then("Der Server wird neugestartet!").color(ChatColor.RED).style(ChatColor.BOLD);
			msg.send(p);
			msg = Prefix.CORE.getPrefix().then("Er wird gleich wieder da sein!").color(ChatColor.AQUA).style(ChatColor.BOLD);
			msg.send(p);
		}

		for (final Player p : Core.getCore().getUserHandler().getOnlinePlayers()) {
			p.kickPlayer(ChatColor.RED + "Der Server wird nun neugestartet \n " + ChatColor.AQUA + " Er wird gleich wieder online sein");
		}

		for (final World w : Bukkit.getWorlds()) {
			for (final Entity e : w.getEntities()) {
				if (e.getType() != EntityType.PLAYER && e.getType() != EntityType.ENDER_CRYSTAL && e.getType() != EntityType.ITEM_FRAME && e.getType() != EntityType.LEASH_HITCH && e.getType() != EntityType.PAINTING) {
					e.eject();
					e.remove();
				}
			}
			// w.save();
		}

		WatchdogThread.doStop();
		for (final EntityPlayer p : MinecraftServer.getServer().getPlayerList().players) {
			p.playerConnection.disconnect(SpigotConfig.restartMessage);
		}

		try {
			Thread.sleep(100L);
		}
		catch (final InterruptedException ex2) {}

		MinecraftServer.getServer().getServerConnection().b();

		try {
			Thread.sleep(100L);
		}
		catch (final InterruptedException ex3) {}

		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(2000);
				}
				catch (final InterruptedException e) {
					e.printStackTrace();
				}
				System.exit(-1);
			}
		}, "backupstopper");

		try {
			MinecraftServer.getServer().stop();
		}
		catch (final Throwable t) {}

		try {
			Thread.sleep(1000L);
		}
		catch (final InterruptedException ex3) {}
		// Bukkit.shutdown();
		RestartCommand.restart();
	}

	public void restart() {
		restart(new File(SpigotConfig.restartScript));
	}

	public void restart(final File script) {
		AsyncCatcher.enabled = false;
		try {
			if (script.isFile()) {
				Core.getCore().getInstance().debug("Attempting to restart with " + SpigotConfig.restartScript);
				final Thread shutdownHook = new Thread() {

					@Override
					public void run() {
						try {
							final String os = System.getProperty("os.name").toLowerCase();
							if (os.contains("win")) {
								Runtime.getRuntime().exec("cmd /c title TEST \nstart " + script.getPath());
							} else {
								Runtime.getRuntime().exec(new String[] { "sh", script.getPath() });
							}
						}
						catch (final Exception e) {
							e.printStackTrace();
						}
					}
				};
				shutdownHook.setDaemon(true);
				Runtime.getRuntime().addShutdownHook(shutdownHook);
			} else {
				Core.getCore().getInstance().debug("Startup script '" + SpigotConfig.restartScript + "' does not exist! Stopping server.");
			}
		}
		catch (final Exception ex) {
			ex.printStackTrace();
		}
		System.exit(0);
	}
}
