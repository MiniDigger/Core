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
 */
package me.MiniDigger.CraftCore.Command.Completer;

import java.util.ArrayList;
import java.util.List;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Command.CommandArgs;
import me.MiniDigger.Core.Command.Completer;

public class AddOnCompleter {
	
	@Completer(name = "addon")
	public List<String> addOnC(final CommandArgs args) {
		final List<String> result = new ArrayList<>();
		
		if (args.getArgs().length == 1) {
			result.add("list");
			result.add("info");
			result.add("install");
			result.add("uninstall");
			result.add("update");
		}
		
		return result;
	}
	
	@Completer(name = "addon.list")
	public List<String> listC(final CommandArgs args) {
		final List<String> result = new ArrayList<>();
		
		if (args.getArgs().length == 1) {
			result.add("all");
			result.add("installed");
			result.add("author");
		}
		
		return result;
	}
	
	@Completer(name = "addon.list.installed")
	public List<String> installedC(final CommandArgs args) {
		final List<String> result = new ArrayList<>();
		
		result.add("");
		
		return result;
	}
	
	@Completer(name = "addon.list.all")
	public List<String> allC(final CommandArgs args) {
		final List<String> result = new ArrayList<>();
		
		result.add("");
		
		return result;
	}
	
	@Completer(name = "addon.list.author")
	public List<String> authorC(final CommandArgs args) {
		final List<String> result = new ArrayList<>();
		
		result.add("");
		
		return result;
	}
	
	@Completer(name = "addon.info")
	public List<String> infoC(final CommandArgs args) {
		final List<String> result = new ArrayList<>();
		
		if (args.getArgs().length == 1) {
			for (final String addon : Core.getCore().getAddOnHandler().getInstalledNames()) {
				result.add(addon);
			}
		}
		
		return result;
	}
	
	@Completer(name = "addon.install")
	public List<String> installC(final CommandArgs args) {
		final List<String> result = new ArrayList<>();
		
		if (args.getArgs().length == 1) {
			result.add("yes");
			result.add("no");
		}
		
		return result;
	}
	
	@Completer(name = "addon.install.yes")
	public List<String> yesC(final CommandArgs args) {
		final List<String> result = new ArrayList<>();
		
		result.add("");
		
		return result;
	}
	
	@Completer(name = "addon.install.no")
	public List<String> noC(final CommandArgs args) {
		final List<String> result = new ArrayList<>();
		
		result.add("");
		
		return result;
	}
	
	@Completer(name = "addon.uninstall")
	public List<String> uninstallC(final CommandArgs args) {
		final List<String> result = new ArrayList<>();
		
		if (args.getArgs().length == 1) {
			for (final String s : Core.getCore().getAddOnHandler().getInstalledNames()) {
				result.add(s);
			}
		}
		
		return result;
	}
	
	@Completer(name = "addon.update")
	public List<String> updateC(final CommandArgs args) {
		final List<String> result = new ArrayList<>();
		
		if (args.getArgs().length == 1) {
			for (final String s : Core.getCore().getAddOnHandler().getInstalledNames()) {
				result.add(s);
			}
		}
		
		return result;
	}
}