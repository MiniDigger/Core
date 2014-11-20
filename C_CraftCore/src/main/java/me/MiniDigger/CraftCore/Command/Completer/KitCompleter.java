package me.MiniDigger.CraftCore.Command.Completer;

import java.util.ArrayList;
import java.util.List;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Command.CommandArgs;
import me.MiniDigger.Core.Command.Completer;
import me.MiniDigger.Core.User.User;

public class KitCompleter {
	
	@Completer(name = "kit")
	public List<String> kit(CommandArgs args) {
		final List<String> result = new ArrayList<>();
		
		if (args.getArgs().length == 1) {
			result.add("create");
			result.add("give");
			result.add("modify");
			result.add("delet");
			
			return Core.getCore().getCommonMethods().completer(result, args.getArgs()[0]);
		} else {
			return new ArrayList<String>();
		}
	}
	
	@Completer(name = "kit.create")
	public List<String> create(CommandArgs args) {
		final List<String> result = new ArrayList<>();
		
		result.add("");
		
		return result;
	}
	
	@Completer(name = "kit.give")
	public List<String> give(CommandArgs args) {
		final List<String> result = new ArrayList<>();
		
		if (args.getArgs().length == 1) {
			for (User user : Core.getCore().getUserHandler().getOnlineUsers()) {
				result.add(user.getDisplayName());
			}
			
			return Core.getCore().getCommonMethods().completer(result, args.getArgs()[0]);
		} else {
			return new ArrayList<String>();
		}
	}
	
	@Completer(name = "kit.modify")
	public List<String> modify(CommandArgs args) {
		final List<String> result = new ArrayList<>();
		
		result.add("");
		
		return result;
	}
	
	@Completer(name = "kit.delete")
	public List<String> delete(CommandArgs args) {
		final List<String> result = new ArrayList<>();
		
		result.add("");
		
		return result;
	}
}
