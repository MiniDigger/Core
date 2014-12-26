package me.MiniDigger.CraftCore.Command.Completer;

import java.util.ArrayList;
import java.util.List;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Command.CommandArgs;
import me.MiniDigger.Core.Command.Completer;
import me.MiniDigger.Core.Lang.LangType;

public class LangCompleter {
	
	@Completer(name = "lang")
	public List<String> langC(CommandArgs args) {
		final List<String> result = new ArrayList<>();
		
		if (args.getArgs().length == 1) {
			result.add("set");
			
			return Core.getCore().getCommonMethods().completer(result, args.getArgs()[0]);
		} else {
			return new ArrayList<String>();
		}
	}
	
	@Completer(name = "lang.set")
	public List<String> setC(CommandArgs args) {
		final List<String> result = new ArrayList<>();
		
		if (args.getArgs().length == 1) {
			for (LangType t : LangType.values()) {
				result.add(t.name());
			}
			
			return Core.getCore().getCommonMethods().completer(result, args.getArgs()[0]);
		} else {
			return new ArrayList<String>();
		}
	}
}
