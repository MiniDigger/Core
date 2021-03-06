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
package me.MiniDigger.CraftCore.Error;

import static java.lang.System.getProperty;

import java.io.File;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.LogRecord;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginLogger;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import net.minecraft.server.v1_8_R3.CrashReport;
import net.minecraft.server.v1_8_R3.MinecraftServer;

/**
 * Custom pluginLogger to save errors. Multiple-instance safe!
 *
 * @author Icyene, Xiaomao
 */

public class ErrorLogger extends PluginLogger {

	private static Field mcLogger, craftbukkitServer, pluginLogger, prepend;

	private ErrorLogger(final Plugin context) {
		super(context);
	}

	@Override
	public void log(final LogRecord logRecord) {
		if (!generateErrorLog(logRecord)) {
			super.log(logRecord);
		}
	}

	public static void register(final Plugin context, final String name, final String pack, final String tracker) {
		try {
			if (!(pluginLogger.get(context) instanceof ErrorLogger)) {
				final ErrorLogger cLog = new ErrorLogger(context);
				pluginLogger.set(context, cLog);
			}
			if (!(mcLogger.get(craftbukkitServer) instanceof ErrorLogger)) {
				final ErrorLogger pLog = new ErrorLogger(context);
				prepend.set(pLog, "");
				mcLogger.set(craftbukkitServer, pLog);
			}
			final HashMap<String, String> registry = loadMap();
			registry.put(name, pack);
			System.setProperty("__ErrorLogger__", JSONValue.toJSONString(registry));
		}
		catch (final Exception e) {
			e.printStackTrace();
		}
	}

	public static void generateErrorLog(final Throwable thorn) {
		final LogRecord screw = new LogRecord(Level.SEVERE, null);
		screw.setMessage("Bukkit did not catch this, so no additional info is available.");
		screw.setThrown(thorn);
		generateErrorLog(screw);
	}

	private static boolean generateErrorLog(final LogRecord record) {
		Throwable thorn;
		if ((thorn = record.getThrown()) == null) {
			return false;
		}

		final String traceback = ExceptionUtils.getStackTrace(thorn);
		String regName = " ";

		for (final Map.Entry<String, String> entry : loadMap().entrySet()) {
			if (traceback.contains(entry.getValue())) { // If the ERROR contains
														// the package
				regName = entry.getKey();
				break;
			}
		}

		if (!traceback.contains(regName + " has encountered an error!") && traceback.contains(ErrorLogger.class.getName())) {
			// Check if its not our own. If it is, return, to not cause a
			// StackOverflow
			return true;
		}

		final Plugin problematicPlugin = Bukkit.getPluginManager().getPlugin(regName);
		if (problematicPlugin == null) {
			return true; // Plugin doesn't exist!
		}

		System.err.println("\n\n" + regName + " encountered an error: \n" + traceback);
		try {
			final StringBuilder err = new StringBuilder();
			final CrashReport report = new CrashReport(problematicPlugin.getName() + " encountered an error! What follows is the stacktrace of the current thread: ", thorn);
			try {
				final File dump = new File(new File(problematicPlugin.getDataFolder(), "errors").getAbsoluteFile(), String.format("%s_%s.error.log", thorn.getClass().getSimpleName(), // Get
																																														// first
																																														// 6
																																														// chars
																																														// of
																																														// hash
						new BigInteger(1, Arrays.copyOfRange(MessageDigest.getInstance("MD5").digest(err.toString().getBytes()), 0, 6)).toString().substring(0, 6)));
				report.a(dump);
				System.err.println("Don't despair! This error has been saved to '.\\" + problematicPlugin.getDataFolder().getName() + "\\errors\\" + dump.getName() + "'. You should report it to the developers of "
						+ problematicPlugin.getName() + ": " + problematicPlugin.getDescription().getAuthors() + ".\n");
			}
			catch (final Exception e) {
				err.append("\nErrors occured while saving to file. Not saved.");
			}
			return true;
		}
		catch (final Exception e) {
			return true;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	static HashMap<String, String> loadMap() {
		final String ser = getProperty("__ErrorLogger__");
		return ser != null ? (JSONObject) JSONValue.parse(ser) : new HashMap();
	}

	static {
		try {
			Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {

				@Override
				public void uncaughtException(final Thread thread, final Throwable throwable) {
					generateErrorLog(throwable);
				}
			});

			(mcLogger = MinecraftServer.class.getDeclaredField("log")).setAccessible(true);
			(craftbukkitServer = CraftServer.class.getDeclaredField("console")).setAccessible(true);
			(pluginLogger = JavaPlugin.class.getDeclaredField("logger")).setAccessible(true);
			(prepend = PluginLogger.class.getDeclaredField("pluginName")).setAccessible(true);
		}
		catch (final Exception e) {
			e.printStackTrace();
		}
	}
}
