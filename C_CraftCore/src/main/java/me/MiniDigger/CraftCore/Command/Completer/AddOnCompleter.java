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
	public List<String> addOnC(CommandArgs args) {
		List<String> result = new ArrayList<>();
		
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
	public List<String> listC(CommandArgs args) {
		List<String> result = new ArrayList<>();
		
		if (args.getArgs().length == 1) {
			result.add("all");
			result.add("installed");
			result.add("author");
		}
		
		return result;
	}
	
	@Completer(name = "addon.list.installed")
	public List<String> installedC(CommandArgs args) {
		List<String> result = new ArrayList<>();
		
		result.add("");
		
		return result;
	}
	
	@Completer(name = "addon.list.all")
	public List<String> allC(CommandArgs args) {
		List<String> result = new ArrayList<>();
		
		result.add("");
		
		return result;
	}
	
	@Completer(name = "addon.list.author")
	public List<String> authorC(CommandArgs args) {
		List<String> result = new ArrayList<>();
		
		result.add("");
		
		return result;
	}
	
	@Completer(name = "addon.info")
	public List<String> infoC(CommandArgs args) {
		List<String> result = new ArrayList<>();
		
		if (args.getArgs().length == 1) {
			for (String addon : Core.getCore().getAddOnHandler().getInstalledNames()) {
				result.add(addon);
			}
		}
		
		return result;
	}
	
	@Completer(name = "addon.install")
	public List<String> installC(CommandArgs args) {
		List<String> result = new ArrayList<>();
		
		if (args.getArgs().length == 1) {
			result.add("yes");
			result.add("no");
		}
		
		return result;
	}
	
	@Completer(name = "addon.install.yes")
	public List<String> yesC(CommandArgs args) {
		List<String> result = new ArrayList<>();
		
		result.add("");
		
		return result;
	}
	
	@Completer(name = "addon.install.no")
	public List<String> noC(CommandArgs args) {
		List<String> result = new ArrayList<>();
		
		result.add("");
		
		return result;
	}
	
	@Completer(name = "addon.uninstall")
	public List<String> uninstallC(CommandArgs args) {
		List<String> result = new ArrayList<>();
		
		if (args.getArgs().length == 1) {
			for (String s : Core.getCore().getAddOnHandler().getInstalledNames()) {
				result.add(s);
			}
		}
		
		return result;
	}
	
	@Completer(name = "addon.update")
	public List<String> updateC(CommandArgs args) {
		List<String> result = new ArrayList<>();
		
		if (args.getArgs().length == 1) {
			for(String s : Core.getCore().getAddOnHandler().getInstalledNames()){
				result.add(s);
			}
		}
		
		return result;
	}
}
