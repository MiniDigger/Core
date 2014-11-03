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
 * Written by Martin Benndorf <admin@minidigger.me>, 2013-2014 and others
 */
package me.MiniDigger.CraftCore.Command;

import java.util.List;

import me.MiniDigger.Core.Command.BukkitCommand;
import me.MiniDigger.Core.Command.BukkitCompleter;

import org.apache.commons.lang.Validate;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.Plugin;

public class CoreBukkitCommand extends Command implements BukkitCommand {
	
	private final Plugin	      owningPlugin;
	private final CommandExecutor	executor;
	private BukkitCompleter	      completer;
	
	protected CoreBukkitCommand(final String label, final Plugin owner) {
		super(label);
		executor = owner;
		owningPlugin = owner;
		usageMessage = "";
	}
	
	@Override
	public boolean execute(final CommandSender sender, final String commandLabel, final String[] args) {
		boolean success = false;
		
		if (!owningPlugin.isEnabled()) {
			return false;
		}
		
		if (!testPermission(sender)) {
			return true;
		}
		
		try {
			success = executor.onCommand(sender, this, commandLabel, args);
		} catch (final Throwable ex) {
			throw new CommandException("Unhandled exception executing command '" + commandLabel + "' in plugin " + owningPlugin.getDescription().getFullName(), ex);
		}
		
		if (!success && usageMessage.length() > 0) {
			for (final String line : usageMessage.replace("<command>", commandLabel).split("\n")) {
				sender.sendMessage(line);
			}
		}
		
		return success;
	}
	
	@Override
	public java.util.List<String> tabComplete(final CommandSender sender, final String alias, final String[] args) throws CommandException, IllegalArgumentException {
		Validate.notNull(sender, "Sender cannot be null");
		Validate.notNull(args, "Arguments cannot be null");
		Validate.notNull(alias, "Alias cannot be null");
		
		List<String> completions = null;
		try {
			if (completer != null) {
				completions = completer.onTabComplete(sender, this, alias, args);
			}
			if (completions == null && executor instanceof TabCompleter) {
				completions = ((TabCompleter) executor).onTabComplete(sender, this, alias, args);
			}
		} catch (final Throwable ex) {
			final StringBuilder message = new StringBuilder();
			message.append("Unhandled exception during tab completion for command '/").append(alias).append(' ');
			for (final String arg : args) {
				message.append(arg).append(' ');
			}
			message.deleteCharAt(message.length() - 1).append("' in plugin ").append(owningPlugin.getDescription().getFullName());
			throw new CommandException(message.toString(), ex);
		}
		
		if (completions == null) {
			return super.tabComplete(sender, alias, args);
		}
		return completions;
	}
	
	@Override
	public BukkitCompleter getCompleter() {
		return completer;
	}
	
	@Override
	public void setCompleter(final BukkitCompleter c) {
		completer = c;
	}
	
}
