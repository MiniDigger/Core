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
package me.MiniDigger.CraftCore;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Filter;
import java.util.logging.LogRecord;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Main;
import me.MiniDigger.Core.Lang.LangKeyType;
import me.MiniDigger.Core.Lang.LogLevel;
import me.MiniDigger.Core.User.User;

import me.MiniDigger.CraftCore.Achievement.CoreAchievementListener;
import me.MiniDigger.CraftCore.Block.CoreBlockListener;
import me.MiniDigger.CraftCore.Chat.CoreChatListener;
import me.MiniDigger.CraftCore.Command.CoreCommandHandler;
import me.MiniDigger.CraftCore.Command.Commands.AddOnCommands;
import me.MiniDigger.CraftCore.Command.Commands.ChatCommands;
import me.MiniDigger.CraftCore.Command.Commands.DevCommands;
import me.MiniDigger.CraftCore.Command.Commands.EffectsCommands;
import me.MiniDigger.CraftCore.Command.Commands.EssentialCommands;
import me.MiniDigger.CraftCore.Command.Commands.ItemCommands;
import me.MiniDigger.CraftCore.Command.Commands.KitCommands;
import me.MiniDigger.CraftCore.Command.Commands.LangCommands;
import me.MiniDigger.CraftCore.Command.Commands.PluginCommands;
import me.MiniDigger.CraftCore.Command.Commands.SettingsCommands;
import me.MiniDigger.CraftCore.Command.Commands.SquadCommands;
import me.MiniDigger.CraftCore.Command.Commands.StatsCommands;
import me.MiniDigger.CraftCore.Command.Commands.TagCommands;
import me.MiniDigger.CraftCore.Command.Commands.TeamCommands;
import me.MiniDigger.CraftCore.Command.Commands.ToggleCommands;
import me.MiniDigger.CraftCore.Command.Commands.TrollCommands;
import me.MiniDigger.CraftCore.Command.Commands.WorldCommands;
import me.MiniDigger.CraftCore.Command.Completer.AddOnCompleter;
import me.MiniDigger.CraftCore.Command.Completer.ChatCompleter;
import me.MiniDigger.CraftCore.Command.Completer.DevCompleter;
import me.MiniDigger.CraftCore.Command.Completer.EffectsCompleter;
import me.MiniDigger.CraftCore.Command.Completer.EssentialCompleter;
import me.MiniDigger.CraftCore.Command.Completer.ItemCompleter;
import me.MiniDigger.CraftCore.Command.Completer.KitCompleter;
import me.MiniDigger.CraftCore.Command.Completer.LangCompleter;
import me.MiniDigger.CraftCore.Command.Completer.PluginCompleter;
import me.MiniDigger.CraftCore.Command.Completer.SettingCompleter;
import me.MiniDigger.CraftCore.Command.Completer.SquadCompleter;
import me.MiniDigger.CraftCore.Command.Completer.StatsCompleter;
import me.MiniDigger.CraftCore.Command.Completer.TagCompleter;
import me.MiniDigger.CraftCore.Command.Completer.TeamCompleter;
import me.MiniDigger.CraftCore.Command.Completer.ToggleCompleter;
import me.MiniDigger.CraftCore.Command.Completer.TrollCompleter;
import me.MiniDigger.CraftCore.Command.Completer.WorldCompleter;
import me.MiniDigger.CraftCore.Event.CoreEventListener;
import me.MiniDigger.CraftCore.Lang._;
import me.MiniDigger.CraftCore.Socket.CoreSocketClient;
import me.MiniDigger.CraftCore.Socket.CoreSocketServer;
import me.MiniDigger.CraftCore.User.CoreUserListener;
import me.MiniDigger.CraftCore.Villager.CoreVillagerListener;

import mkremins.fanciful.FancyMessage;

public class CoreMain extends JavaPlugin implements Main {
	
	private boolean	    update	= false;
	private static Core	core;
	
	/**
	 * DO NO USE
	 * 
	 * @deprecated DO NOT USE
	 */
	@Deprecated
	public static Core getCore() {
		return core;
	}
	
	@Override
	public void onEnable() {
		final Date start = new Date();
		core = new CoreCore(this);
		Core.getCore().getLangHandler().load();
		
		_.log(LogLevel.INFO, LangKeyType.Main.ACTIVATE, getDescription().getFullName(), "MiniDigger");
		
		Date d1 = new Date();
		_.log(LogLevel.INFO, LangKeyType.Main.CHECK_LICENCE);
		try {
			if (Core.getCore().getLicenseHandler().register()) {
				final Date d2 = new Date();
				_.log(LogLevel.INFO, LangKeyType.Main.CHECK_LICENCE_PASSED, (d2.getTime() - d1.getTime()) + "");
			} else {
				final Date d2 = new Date();
				_.log(LogLevel.INFO, LangKeyType.Main.CHECK_LICENCE_FAILED, (d2.getTime() - d1.getTime()) + "");
				core.getCommonMethods().killPlugin();
				return;
			}
		} catch (final Exception ex) {
			_.log(LogLevel.ERROR, LangKeyType.Main.ERROR, ex.getMessage());
			_.stacktrace(LogLevel.DEBUG, ex);
			core.getCommonMethods().killPlugin();
			return;
		}
		
		d1 = new Date();
		_.log(LogLevel.INFO, LangKeyType.Main.CHECK_UPDATES);
		try {
			Core.getCore().getDependencyHanlder().check();
			
			if (Core.getCore().getUpdateHandler().updateCheck()) {
				update = true;
				return;
			}
			final Date d2 = new Date();
			_.log(LogLevel.INFO, LangKeyType.Main.CHECK_DONE, (d2.getTime() - d1.getTime()) + "");
		} catch (final Exception ex) {
			_.log(LogLevel.ERROR, LangKeyType.Main.ERROR, ex.getMessage());
			_.stacktrace(LogLevel.DEBUG, ex);
		}
		
		d1 = new Date();
		_.log(LogLevel.INFO, LangKeyType.Main.SAVE_CONFIG);
		try {
			saveDefaultConfig();
			final Date d2 = new Date();
			_.log(LogLevel.INFO, LangKeyType.Main.CHECK_DONE, (d2.getTime() - d1.getTime()) + "");
		} catch (final Exception ex) {
			_.log(LogLevel.ERROR, LangKeyType.Main.ERROR, ex.getMessage());
			_.stacktrace(LogLevel.DEBUG, ex);
		}
		
		d1 = new Date();
		_.log(LogLevel.INFO, LangKeyType.Main.ENABLE_LIBS);
		try {
			enableExternalDependencies();
			final Date d2 = new Date();
			_.log(LogLevel.INFO, LangKeyType.Main.CHECK_DONE, (d2.getTime() - d1.getTime()) + "");
		} catch (final Exception ex) {
			_.log(LogLevel.ERROR, LangKeyType.Main.ERROR, ex.getMessage());
			_.stacktrace(LogLevel.DEBUG, ex);
		}
		
		d1 = new Date();
		_.log(LogLevel.INFO, LangKeyType.Main.REGISTER_COMMANDS);
		try {
			registerCommands();
			final Date d2 = new Date();
			_.log(LogLevel.INFO, LangKeyType.Main.CHECK_DONE, (d2.getTime() - d1.getTime()) + "");
		} catch (final Exception ex) {
			_.log(LogLevel.ERROR, LangKeyType.Main.ERROR, ex.getMessage());
			_.stacktrace(LogLevel.DEBUG, ex);
		}
		
		d1 = new Date();
		_.log(LogLevel.INFO, LangKeyType.Main.ACTIVATE_HANDLER);
		try {
			enableHandler();
			final Date d2 = new Date();
			_.log(LogLevel.INFO, LangKeyType.Main.CHECK_DONE, (d2.getTime() - d1.getTime()) + "");
		} catch (final Exception ex) {
			_.log(LogLevel.ERROR, LangKeyType.Main.ERROR, ex.getMessage());
			_.stacktrace(LogLevel.DEBUG, ex);
		}
		
		d1 = new Date();
		_.log(LogLevel.INFO, LangKeyType.Main.LOAD_DATA);
		try {
			loadStuff();
			final Date d2 = new Date();
			_.log(LogLevel.INFO, LangKeyType.Main.CHECK_DONE, (d2.getTime() - d1.getTime()) + "");
		} catch (final Exception ex) {
			_.log(LogLevel.ERROR, LangKeyType.Main.ERROR, ex.getMessage());
			_.stacktrace(LogLevel.DEBUG, ex);
		}
		
		d1 = new Date();
		_.log(LogLevel.INFO, LangKeyType.Main.REGISTER_LISTENER);
		try {
			registerListener();
			final Date d2 = new Date();
			_.log(LogLevel.INFO, LangKeyType.Main.CHECK_DONE, (d2.getTime() - d1.getTime()) + "");
		} catch (final Exception ex) {
			_.log(LogLevel.ERROR, LangKeyType.Main.ERROR, ex.getMessage());
			_.stacktrace(LogLevel.DEBUG, ex);
		}
		
		d1 = new Date();
		_.log(LogLevel.INFO, LangKeyType.Main.APPLY_FIXES);
		try {
			fixReload();
			final Date d2 = new Date();
			_.log(LogLevel.INFO, LangKeyType.Main.CHECK_DONE, (d2.getTime() - d1.getTime()) + "");
		} catch (final Exception ex) {
			_.log(LogLevel.ERROR, LangKeyType.Main.ERROR, ex.getMessage());
			_.stacktrace(LogLevel.DEBUG, ex);
		}
		
		d1 = new Date();
		_.log(LogLevel.INFO, LangKeyType.Main.SEARCH_GAME);
		try {
			Core.getCore().getGameHandler().searchMainGame();
			final Date d2 = new Date();
			_.log(LogLevel.INFO, LangKeyType.Main.CHECK_DONE, (d2.getTime() - d1.getTime()) + "");
		} catch (final Exception ex) {
			_.log(LogLevel.ERROR, LangKeyType.Main.ERROR, ex.getMessage());
			_.stacktrace(LogLevel.DEBUG, ex);
		}
		
		d1 = new Date();
		_.log(LogLevel.INFO, LangKeyType.Main.ENABLE_LOGGING);
		try {
			Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
				
				@Override
				public void uncaughtException(final Thread t, final Throwable e) {
					System.out.println("something got thrwon:  " + e.getMessage());
					e.printStackTrace();
				}
			});
			
			Bukkit.getLogger().setFilter(new Filter() {
				
				@Override
				public boolean isLoggable(final LogRecord record) {
					if (record.getThrown() != null) {
						System.out.println("soemtinf got thrown: " + record.getThrown().getMessage());
					}
					return true;
				}
			});
			final Date d2 = new Date();
			_.log(LogLevel.INFO, LangKeyType.Main.CHECK_DONE, (d2.getTime() - d1.getTime()) + "");
		} catch (final Exception ex) {
			_.log(LogLevel.ERROR, LangKeyType.Main.ERROR, ex.getMessage());
			_.stacktrace(LogLevel.DEBUG, ex);
		}
		
		d1 = new Date();
		_.log(LogLevel.INFO, LangKeyType.Main.ACTIVATE_SOCKET);
		try {
			Core.getCore().getSocketHandler().registerPackets();
			final String server = System.getProperty("server");
			if (server != null && server.equalsIgnoreCase("true")) {
				_.log(LogLevel.INFO, LangKeyType.Socket.START);
				Core.getCore().getSocketHandler().startServer();
				_.log(LogLevel.INFO, LangKeyType.Socket.STARTED);
			}
			
			Bukkit.getScheduler().runTaskLater(this, new Runnable() {
				
				@Override
				public void run() {
					_.log(LogLevel.INFO, LangKeyType.Socket.START_C);
					Core.getCore().getSocketHandler().startClient();
				}
			}, 10);
			final Date d2 = new Date();
			_.log(LogLevel.INFO, LangKeyType.Main.CHECK_DONE, (d2.getTime() - d1.getTime()) + "");
		} catch (final Exception ex) {
			_.log(LogLevel.ERROR, LangKeyType.Main.ERROR, ex.getMessage());
			_.stacktrace(LogLevel.DEBUG, ex);
		}
		
		core.enable();
		final Date end = new Date();
		_.log(LogLevel.INFO, LangKeyType.Main.ACTIVATED, end.getTime() - start.getTime() + "");
	}
	
	@Override
	public void onDisable() {
		if (update) {
			return;
		}
		info("Deaktivere...");
		
		info("Stoppe Client...");
		try {
			((CoreSocketClient) Core.getCore().getSocketHandler().getClient()).close();
		} catch (final Exception ex) {
			error("Fehler: " + ex.getMessage());
		}
		
		info("Stoppe Server...");
		try {
			((CoreSocketServer) Core.getCore().getSocketHandler().getServer()).stop(30);
		} catch (final Exception ex) {
			error("Fehler: " + ex.getMessage());
		}
		
		info("Unregister Listener...");
		try {
			HandlerList.unregisterAll(this);
		} catch (final Exception ex) {
			error("Fehler: " + ex.getMessage());
		}
		
		info("Stoppe Tasks...");
		try {
			Bukkit.getScheduler().cancelAllTasks();
		} catch (final Exception ex) {
			error("Fehler: " + ex.getMessage());
		}
		
		info("Deaktiviere Handler...");
		try {
			disableHandler();
		} catch (final Exception ex) {
			error("Fehler: " + ex.getMessage());
		}
		
		core.disable();
		core = null;
		
		info("Deaktiviert!");
	}
	
	private void disableHandler() {
		Core.getCore().getBarHandler();
		Core.getCore().getNametagHandler().disable();
		Core.getCore().getAddOnHandler().disableAddOns();
	}
	
	private void fixReload() {
		for (final Player p : Core.getCore().getUserHandler().getOnlinePlayers()) {
			final User user = Core.getCore().getUserHandler().get(p.getUniqueId());
			user.startSession();
		}
	}
	
	private void registerCommands() {
		Map<String, String> reloc = new HashMap<String, String>();
		reloc.put("pex", "coreperm");
		Core.getCore().setCommandHandler(new CoreCommandHandler(reloc));
		
		final Object[] commandHandler = new Object[] { new PluginCommands(), new DevCommands(), new StatsCommands(), new ChatCommands(), new TrollCommands(),
		        new SquadCommands(), new WorldCommands(), new ToggleCommands(), new ItemCommands(), new SettingsCommands(), new AddOnCommands(), new EssentialCommands(),
		        new TeamCommands(), new KitCommands(), new EffectsCommands(), new LangCommands(), new TagCommands() };
		for (final Object obj : commandHandler) {
			Core.getCore().getCommandHandler().registerCommands(obj);
		}
		
		final Object[] completerHandler = new Object[] { new PluginCompleter(), new DevCompleter(), new StatsCompleter(), new ChatCompleter(), new TrollCompleter(),
		        new SquadCompleter(), new WorldCompleter(), new ToggleCompleter(), new ItemCompleter(), new SettingCompleter(), new AddOnCompleter(),
		        new EssentialCompleter(), new TeamCompleter(), new KitCompleter(), new EffectsCompleter(), new LangCompleter(), new TagCompleter() };
		for (final Object obj : completerHandler) {
			Core.getCore().getCommandHandler().registerCommands(obj);
		}
		
		Core.getCore().getCommandHandler().registerHelp();
	}
	
	private void enableHandler() {
		Core.getCore().getAddOnHandler().load();
		Core.getCore().getProtocolHandler().init();
		Core.getCore().getServerHandler().startTask();
		Core.getCore().getSqlHandler();
		Core.getCore().getNametagHandler().enable();
		Core.getCore().getMirrorHandler().init();
		Core.getCore().getChatHandler().registerChannels();
		Core.getCore().getDashingHandler().init();
		
		Core.getCore().getAddOnHandler().enableAddOns();
	}
	
	private void enableExternalDependencies() {
		// Lag.init();
		// GhostFactory.getInstance(); //TODO Disabled due to a strange NPE
	}
	
	private void registerListener() {
		final Listener[] listeners = new Listener[] { new CoreUserListener(), new CoreChatListener(), Core.getCore().getProtocolHandler().getSignChangers(),
		        new CoreEventListener(), new CoreBlockListener(), Core.getCore().getProtocolHandler(), new CoreAchievementListener(), new CoreVillagerListener(),
		        Core.getCore().getProtocolHandler().getSignListeners(), new ToggleCommands() };
		for (final Listener listener : listeners) {
			if (listener == null) {
				System.out.println("A listener was null!");
				continue;
			}
			Bukkit.getPluginManager().registerEvents(listener, this);
		}
	}
	
	private void loadStuff() {
		info("Lade Stats...");
		if (!Core.getCore().getStatsHandler().loadAll()) {
			error("Stats wurden nicht geladen!");
		}
		info("Lade User...");
		if (!Core.getCore().getUserHandler().loadAll()) {
			error("User wurden nicht geladen!");
		}
		info("Lade Clients...");
		if (!Core.getCore().getClientHandler().loadAll()) {
			error("Clients wurden nicht geladen!");
		}
		info("Lade Kits...");
		if (!Core.getCore().getKitHandler().loadAll()) {
			error("Kits wurden nicht geladen!");
		}
	}
	
	@Override
	public boolean onCommand(final CommandSender sender, final org.bukkit.command.Command command, final String label, final String[] args) {
		return Core.getCore().getCommandHandler().handleCommand(sender, label, command, args);
	}
	
	// TODO Remove these methods
	@Deprecated
	@Override
	public void debug(final String s) {
		if (getConfig().isBoolean("debug")) {
			getLogger().info("[DEBUG]" + s);
		}
	}
	
	@Deprecated
	@Override
	public void info(final String s) {
		getLogger().info(s);
	}
	
	@Deprecated
	@Override
	public void error(final String s) {
		getLogger().severe(s);
	}
	
	@Deprecated
	@Override
	public void broadcast(final FancyMessage msg) {
		for (final Player p : Core.getCore().getUserHandler().getOnlinePlayers()) {
			msg.send(p);
		}
	}
}
