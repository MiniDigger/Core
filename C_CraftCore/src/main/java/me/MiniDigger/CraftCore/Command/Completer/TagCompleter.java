package me.MiniDigger.CraftCore.Command.Completer;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Command.CommandArgs;
import me.MiniDigger.Core.Command.Completer;

public class TagCompleter {
	
	@Completer(name = "tag")
	public List<String> tagC(CommandArgs args) {
		final List<String> result = new ArrayList<>();
		
		if (args.getArgs().length == 1) {
			result.add("prefix");
			result.add("suffix");
			result.add("hide");
			result.add("show");
			
			return Core.getCore().getCommonMethods().completer(result, args.getArgs()[0]);
		} else {
			return new ArrayList<String>();
		}
	}
	
	@Completer(name = "tag.show")
	public List<String> showC(CommandArgs args) {
		final List<String> result = new ArrayList<>();
		
		if (args.getArgs().length == 1) {
			for (final Player p : Core.getCore().getUserHandler().getOnlinePlayers()) {
				result.add(p.getName());
			}
			
			return Core.getCore().getCommonMethods().completer(result, args.getArgs()[0]);
		} else {
			return new ArrayList<String>();
		}
	}
	
	@Completer(name = "tag.hide")
	public List<String> hideC(CommandArgs args) {
		final List<String> result = new ArrayList<>();
		
		if (args.getArgs().length == 1) {
			for (final Player p : Core.getCore().getUserHandler().getOnlinePlayers()) {
				result.add(p.getName());
			}
			
			return Core.getCore().getCommonMethods().completer(result, args.getArgs()[0]);
		} else {
			return new ArrayList<String>();
		}
	}
	
	@Completer(name = "tag.prefix")
	public List<String> prefixX(CommandArgs args) {
		final List<String> result = new ArrayList<>();
		
		if (args.getArgs().length == 1) {
			for (final Player p : Core.getCore().getUserHandler().getOnlinePlayers()) {
				result.add(p.getName());
			}
			
			return Core.getCore().getCommonMethods().completer(result, args.getArgs()[0]);
		} else {
			return new ArrayList<String>();
		}
	}
	
	@Completer(name = "tag.suffix")
	public List<String> suffixC(CommandArgs args) {
		final List<String> result = new ArrayList<>();
		
		if (args.getArgs().length == 1) {
			for (final Player p : Core.getCore().getUserHandler().getOnlinePlayers()) {
				result.add(p.getName());
			}
			
			return Core.getCore().getCommonMethods().completer(result, args.getArgs()[0]);
		} else {
			return new ArrayList<String>();
		}
	}
}
