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

import java.io.File;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Filter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

import com.earth2me.essentials.EssentialsTimer;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Main;
import me.MiniDigger.Core.Lang.LangKeyType;
import me.MiniDigger.Core.Lang.LogLevel;
import me.MiniDigger.Core.Prefix.Prefix;
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
import me.MiniDigger.CraftCore.Command.Commands.GameCommands;
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
import me.MiniDigger.CraftCore.Command.Completer.GameCompleter;
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
import me.MiniDigger.CraftCore.Generator.CoreCleanroomChunkGenerator;
import me.MiniDigger.CraftCore.Lang.MSG;
import me.MiniDigger.CraftCore.Stats.CoreActionListener;
import me.MiniDigger.CraftCore.User.CoreUserListener;
import me.MiniDigger.CraftCore.Villager.CoreVillagerListener;

import mkremins.fanciful.FancyMessage;

public class CoreMain extends JavaPlugin implements Main {

	private boolean			update	= false;
	private static Core		core;
	public static Logger	logger;
	private EssentialsTimer	timer;

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
		Prefix.load();

		MSG.log(LogLevel.INFO, LangKeyType.Main.ACTIVATE, getDescription().getFullName(), "MiniDigger");

		Date d1 = new Date();
		MSG.log(LogLevel.INFO, LangKeyType.Main.CHECK_LICENCE);
		try {
			if (Core.getCore().getLicenseHandler().register()) {
				final Date d2 = new Date();
				MSG.log(LogLevel.INFO, LangKeyType.Main.CHECK_LICENCE_PASSED, (d2.getTime() - d1.getTime()) + "");
			} else {
				final Date d2 = new Date();
				MSG.log(LogLevel.INFO, LangKeyType.Main.CHECK_LICENCE_FAILED, (d2.getTime() - d1.getTime()) + "");
				core.getCommonMethods().killPlugin();
				return;
			}
		}
		catch (final Exception ex) {
			MSG.log(LogLevel.ERROR, LangKeyType.Main.ERROR, ex.getMessage());
			MSG.stacktrace(LogLevel.DEBUG, ex);
			core.getCommonMethods().killPlugin();
			return;
		}

		d1 = new Date();
		MSG.log(LogLevel.INFO, LangKeyType.Main.CHECK_UPDATES);
		try {
			Core.getCore().getDependencyHanlder().check();

			if (Core.getCore().getUpdateHandler().updateCheck()) {
				update = true;
				return;
			}
			final Date d2 = new Date();
			MSG.log(LogLevel.INFO, LangKeyType.Main.CHECK_DONE, (d2.getTime() - d1.getTime()) + "");
		}
		catch (final Exception ex) {
			MSG.log(LogLevel.ERROR, LangKeyType.Main.ERROR, ex.getMessage());
			MSG.stacktrace(LogLevel.DEBUG, ex);
		}

		d1 = new Date();
		MSG.log(LogLevel.INFO, LangKeyType.Main.SAVE_CONFIG);
		try {
			saveDefaultConfig();
			final Date d2 = new Date();
			MSG.log(LogLevel.INFO, LangKeyType.Main.CHECK_DONE, (d2.getTime() - d1.getTime()) + "");
		}
		catch (final Exception ex) {
			MSG.log(LogLevel.ERROR, LangKeyType.Main.ERROR, ex.getMessage());
			MSG.stacktrace(LogLevel.DEBUG, ex);
		}

		d1 = new Date();
		MSG.log(LogLevel.INFO, LangKeyType.Main.ENABLE_LIBS);
		try {
			enableExternalDependencies();
			final Date d2 = new Date();
			MSG.log(LogLevel.INFO, LangKeyType.Main.CHECK_DONE, (d2.getTime() - d1.getTime()) + "");
		}
		catch (final Exception ex) {
			MSG.log(LogLevel.ERROR, LangKeyType.Main.ERROR, ex.getMessage());
			MSG.stacktrace(LogLevel.DEBUG, ex);
		}

		d1 = new Date();
		MSG.log(LogLevel.INFO, LangKeyType.Main.REGISTER_COMMANDS);
		try {
			registerCommands();
			final Date d2 = new Date();
			MSG.log(LogLevel.INFO, LangKeyType.Main.CHECK_DONE, (d2.getTime() - d1.getTime()) + "");
		}
		catch (final Exception ex) {
			MSG.log(LogLevel.ERROR, LangKeyType.Main.ERROR, ex.getMessage());
			MSG.stacktrace(LogLevel.DEBUG, ex);
		}

		d1 = new Date();
		MSG.log(LogLevel.INFO, LangKeyType.Main.ACTIVATE_HANDLER);
		try {
			enableHandler();
			final Date d2 = new Date();
			MSG.log(LogLevel.INFO, LangKeyType.Main.CHECK_DONE, (d2.getTime() - d1.getTime()) + "");
		}
		catch (final Exception ex) {
			MSG.log(LogLevel.ERROR, LangKeyType.Main.ERROR, ex.getMessage());
			MSG.stacktrace(LogLevel.DEBUG, ex);
		}

		d1 = new Date();
		MSG.log(LogLevel.INFO, LangKeyType.Main.LOAD_DATA);
		try {
			loadStuff();
			final Date d2 = new Date();
			MSG.log(LogLevel.INFO, LangKeyType.Main.CHECK_DONE, (d2.getTime() - d1.getTime()) + "");
		}
		catch (final Exception ex) {
			MSG.log(LogLevel.ERROR, LangKeyType.Main.ERROR, ex.getMessage());
			MSG.stacktrace(LogLevel.DEBUG, ex);
		}

		d1 = new Date();
		MSG.log(LogLevel.INFO, LangKeyType.Main.REGISTER_LISTENER);
		try {
			registerListener();
			final Date d2 = new Date();
			MSG.log(LogLevel.INFO, LangKeyType.Main.CHECK_DONE, (d2.getTime() - d1.getTime()) + "");
		}
		catch (final Exception ex) {
			MSG.log(LogLevel.ERROR, LangKeyType.Main.ERROR, ex.getMessage());
			MSG.stacktrace(LogLevel.DEBUG, ex);
		}

		d1 = new Date();
		MSG.log(LogLevel.INFO, LangKeyType.Main.APPLY_FIXES);
		try {
			fixReload();
			final Date d2 = new Date();
			MSG.log(LogLevel.INFO, LangKeyType.Main.CHECK_DONE, (d2.getTime() - d1.getTime()) + "");
		}
		catch (final Exception ex) {
			MSG.log(LogLevel.ERROR, LangKeyType.Main.ERROR, ex.getMessage());
			MSG.stacktrace(LogLevel.DEBUG, ex);
		}

		d1 = new Date();
		MSG.log(LogLevel.INFO, LangKeyType.Main.SEARCH_GAME);
		try {
			Core.getCore().getGameHandler().searchMainGame();
			final Date d2 = new Date();
			MSG.log(LogLevel.INFO, LangKeyType.Main.CHECK_DONE, (d2.getTime() - d1.getTime()) + "");
		}
		catch (final Exception ex) {
			MSG.log(LogLevel.ERROR, LangKeyType.Main.ERROR, ex.getMessage());
			MSG.stacktrace(LogLevel.DEBUG, ex);
		}

		d1 = new Date();
		MSG.log(LogLevel.INFO, LangKeyType.Main.ENABLE_LOGGING);
		try {
			Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {

				@Override
				public void uncaughtException(final Thread t, final Throwable e) {
					String msg = e.getMessage();
					if (msg == null) {
						msg = e.getCause().getMessage();
						if (msg == null) {
							msg = "<msg null>";
						}
					}
					MSG.log(LogLevel.ERROR, LangKeyType.Log.CATCHED, "1", msg);
					MSG.stacktrace(LogLevel.DEBUG, e);
					MSG.log(LogLevel.DEBUG, LangKeyType.Log.CAUSED);
					MSG.stacktrace(LogLevel.DEBUG, e.getCause());
				}
			});

			Bukkit.getLogger().setFilter(new Filter() {

				@Override
				public boolean isLoggable(final LogRecord record) {
					try {
						if (record.getThrown() != null) {
							String msg = record.getThrown().getMessage();
							if (msg == null) {
								msg = record.getThrown().getCause().getMessage();
								if (msg == null) {
									msg = "<msg null>";
								}
							}
							MSG.log(LogLevel.ERROR, LangKeyType.Log.CATCHED, "2", msg);
							MSG.stacktrace(LogLevel.DEBUG, record.getThrown());
							MSG.log(LogLevel.DEBUG, LangKeyType.Log.CAUSED);
							MSG.stacktrace(LogLevel.DEBUG, record.getThrown().getCause());
							return false;
						}
					}
					catch (Exception ex) {
						return true;
					}
					return false;
				}
			});
			// ErrorLogger.register(this, "Core", "me.MiniDigger",
			// "bugs.minidigger.me");
			// TODO Fix ErrorLogger
			Core.getCore().getErrorHandler().init();

			// TPS GETTING
			timer = new EssentialsTimer();
			Bukkit.getScheduler().runTaskTimer(Core.getCore().getInstance(), timer, 1000, 50);

			final Date d2 = new Date();
			MSG.log(LogLevel.INFO, LangKeyType.Main.CHECK_DONE, (d2.getTime() - d1.getTime()) + "");
		}
		catch (final Exception ex) {
			MSG.log(LogLevel.ERROR, LangKeyType.Main.ERROR, ex.getMessage());
			MSG.stacktrace(LogLevel.DEBUG, ex);
		}

		d1 = new Date();
		MSG.log(LogLevel.INFO, LangKeyType.Main.ACTIVATE_SOCKET);
		try {
			Core.getCore().getSocketHandler().registerPackets();
			// final String server = System.getProperty("server");
			// if (server != null && server.equalsIgnoreCase("true")) {
			if (getConfig().contains("startserver") && getConfig().getBoolean("startserver")) {
				MSG.log(LogLevel.INFO, LangKeyType.Socket.START);
				Core.getCore().getSocketHandler().startServer();
				MSG.log(LogLevel.INFO, LangKeyType.Socket.STARTED);
			}

			Bukkit.getScheduler().runTaskLater(this, new Runnable() {

				@Override
				public void run() {
					MSG.log(LogLevel.INFO, LangKeyType.Socket.START_C);
					Core.getCore().getSocketHandler().startClient();
				}
			}, 10);

			final Date d2 = new Date();
			MSG.log(LogLevel.INFO, LangKeyType.Main.CHECK_DONE, (d2.getTime() - d1.getTime()) + "");
		}
		catch (final Exception ex) {
			MSG.log(LogLevel.ERROR, LangKeyType.Main.ERROR, ex.getMessage());
			MSG.stacktrace(LogLevel.DEBUG, ex);
		}

		core.enable();

		core.getMetricsHandler().start();

		try {
			final FileHandler h = new java.util.logging.FileHandler(new File(Bukkit.getWorldContainer().getAbsolutePath() + "/logs", "websocket.log").getAbsolutePath());
			h.setFormatter(new SimpleFormatter());
			logger = Logger.getLogger("WebSocket");
			for (final Handler hh : logger.getHandlers()) {
				logger.removeHandler(hh);
			}
			logger.addHandler(h);
			logger.setUseParentHandlers(false);
		}
		catch (final Exception e) {
			e.printStackTrace();
		}

		final Date end = new Date();
		MSG.log(LogLevel.INFO, LangKeyType.Main.ACTIVATED, end.getTime() - start.getTime() + "");
	}

	@Override
	public void onDisable() {
		if (update) {
			return;
		}

		MSG.log(LogLevel.INFO, LangKeyType.Main.DEACTIVATE);

		MSG.log(LogLevel.INFO, LangKeyType.Socket.STOP_C);
		try {
			Core.getCore().getSocketHandler().stopClient();
		}
		catch (final Exception ex) {
			MSG.log(LogLevel.ERROR, LangKeyType.Main.ERROR, ex.getMessage());
			MSG.stacktrace(LogLevel.DEBUG, ex);
		}

		MSG.log(LogLevel.INFO, LangKeyType.Main.UNREGISTER_LISTENER);
		try {
			HandlerList.unregisterAll(this);
		}
		catch (final Exception ex) {
			MSG.log(LogLevel.ERROR, LangKeyType.Main.ERROR, ex.getMessage());
			MSG.stacktrace(LogLevel.DEBUG, ex);
		}

		MSG.log(LogLevel.INFO, LangKeyType.Main.STOP_TASKS);
		try {
			Core.getCore().getTaskHandler().cancelAll();
			Bukkit.getScheduler().cancelTasks(this);
		}
		catch (final Exception ex) {
			MSG.log(LogLevel.ERROR, LangKeyType.Main.ERROR, ex.getMessage());
			MSG.stacktrace(LogLevel.DEBUG, ex);
		}

		MSG.log(LogLevel.INFO, LangKeyType.Main.DEACTIVATE_HANDLER);
		try {
			disableHandler();
		}
		catch (final Exception ex) {
			MSG.log(LogLevel.ERROR, LangKeyType.Main.ERROR, ex.getMessage());
			MSG.stacktrace(LogLevel.DEBUG, ex);
		}

		core.disable();
		core = null;

		MSG.log(LogLevel.INFO, LangKeyType.Main.DEACTIVATED);
	}

	private void disableHandler() {
		Core.getCore().getBarHandler().removeAllStatusBars();
		Core.getCore().getActionHandler().stop();
		Core.getCore().getWorldHandler().disable();
		Core.getCore().getAddOnHandler().disableAddOns();
	}

	private void fixReload() {
		for (final Player p : Core.getCore().getUserHandler().getOnlinePlayers()) {
			final User user = Core.getCore().getUserHandler().get(p.getUniqueId());
			user.startSession();
		}
	}

	private void registerCommands() {
		final Map<String, String> reloc = new HashMap<String, String>();
		reloc.put("pex", "coreperm");
		reloc.put("timings", "debug");
		Core.getCore().setCommandHandler(new CoreCommandHandler(reloc));

		final Object[] commandHandler = new Object[] { new PluginCommands(), new DevCommands(), new StatsCommands(), new ChatCommands(), new TrollCommands(), new SquadCommands(), new WorldCommands(),
				new ToggleCommands(), new ItemCommands(), new SettingsCommands(), new AddOnCommands(), new EssentialCommands(), new TeamCommands(), new KitCommands(), new EffectsCommands(), new LangCommands(),
				new TagCommands(), new GameCommands() };
		for (final Object obj : commandHandler) {
			Core.getCore().getCommandHandler().registerCommands(obj);
		}

		final Object[] completerHandler = new Object[] { new PluginCompleter(), new DevCompleter(), new StatsCompleter(), new ChatCompleter(), new TrollCompleter(), new SquadCompleter(), new WorldCompleter(),
				new ToggleCompleter(), new ItemCompleter(), new SettingCompleter(), new AddOnCompleter(), new EssentialCompleter(), new TeamCompleter(), new KitCompleter(), new EffectsCompleter(), new LangCompleter(),
				new TagCompleter(), new GameCompleter() };
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
		Core.getCore().getMirrorHandler().init();
		Core.getCore().getChatHandler().registerChannels();
		Core.getCore().getDashingHandler().init();
		Core.getCore().getMenuHandler().load();
		Core.getCore().getBroadcastHandler().init();
		Core.getCore().getActionHandler().start();

		Core.getCore().getAddOnHandler().enableAddOns();
	}

	private void enableExternalDependencies() {
		// Lag.init();
		// GhostFactory.getInstance(); //TODO Disabled due to a strange NPE
	}

	private void registerListener() {
		final Listener[] listeners = new Listener[] { new CoreUserListener(), new CoreChatListener(), Core.getCore().getProtocolHandler().getSignChangers(), new CoreEventListener(), new CoreBlockListener(),
				Core.getCore().getProtocolHandler(), new CoreAchievementListener(), new CoreVillagerListener(), Core.getCore().getProtocolHandler().getSignListeners(), new ToggleCommands(), new CoreActionListener() };
		for (final Listener listener : listeners) {
			if (listener == null) {
				MSG.log(LogLevel.DEBUG, LangKeyType.Main.LISTENER_NULL);
				continue;
			}
			Bukkit.getPluginManager().registerEvents(listener, this);
		}

		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
	}

	private void loadStuff() {
		MSG.log(LogLevel.INFO, LangKeyType.Main.LOAD, "Stats");
		if (!Core.getCore().getStatsHandler().loadAll()) {
			MSG.log(LogLevel.ERROR, LangKeyType.Main.NOT_LOADED, "Stats");
		}
		MSG.log(LogLevel.INFO, LangKeyType.Main.LOAD, "User");
		if (!Core.getCore().getUserHandler().loadAll()) {
			MSG.log(LogLevel.ERROR, LangKeyType.Main.NOT_LOADED, "User");
		}
		MSG.log(LogLevel.INFO, LangKeyType.Main.LOAD, "Clients");
		if (!Core.getCore().getClientHandler().loadAll()) {
			MSG.log(LogLevel.ERROR, LangKeyType.Main.NOT_LOADED, "Clients");
		}
		MSG.log(LogLevel.INFO, LangKeyType.Main.LOAD, "Kits");
		if (!Core.getCore().getKitHandler().loadAll()) {
			MSG.log(LogLevel.ERROR, LangKeyType.Main.NOT_LOADED, "Kits");
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
			if (s.startsWith("[Client]") || s.startsWith("[Server]")) {
				logger.info("[DEBUG] " + s);
			} else {
				getLogger().info("[DEBUG] " + s);
			}
		}
	}

	@Deprecated
	@Override
	public void info(final String s) {
		if (s.startsWith("[Client]") || s.startsWith("[Server]")) {
			logger.info(s);
		} else {
			getLogger().info(s);
		}
	}

	@Deprecated
	@Override
	public void error(final String s) {
		if (s.startsWith("[Client]") || s.startsWith("[Server]")) {
			logger.severe(s);
		} else {
			getLogger().severe(s);
		}
	}

	@Deprecated
	@Override
	public void broadcast(final FancyMessage msg) {
		for (final Player p : Core.getCore().getUserHandler().getOnlinePlayers()) {
			msg.send(p);
		}
	}

	@Override
	public double getTPS() {
		return timer.getAverageTPS();
	}
	
	@Override
	public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
		return new CoreCleanroomChunkGenerator(".");
	}
}
