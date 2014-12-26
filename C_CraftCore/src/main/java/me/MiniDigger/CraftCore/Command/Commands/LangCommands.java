package me.MiniDigger.CraftCore.Command.Commands;

import me.MiniDigger.Core.Command.Command;
import me.MiniDigger.Core.Command.CommandArgs;
import me.MiniDigger.Core.Lang.LangKeyType;
import me.MiniDigger.Core.Lang.LangType;
import me.MiniDigger.Core.Lang.MsgType;
import me.MiniDigger.Core.Prefix.Prefix;

import me.MiniDigger.CraftCore.Lang._;

public class LangCommands {
	
	@Command(name = "lang", usage = "", description = "Macht alles mit Sprachen", permission = "lang")
	public void lang(CommandArgs args) {
		
	}
	
	@Command(name = "lang.set", usage = "lang set <lang>", description = "Setzt deine Sprache neu", permission = "lang.set", min = 1, max = 1, consol = false)
	public void set(CommandArgs args) {
		try {
			LangType lang = LangType.valueOf(args.getArgs()[0]);
			args.getUser().setLanguage(lang);
			_.msg(Prefix.LANG, LangKeyType.Lang.SET, MsgType.SUCESS, args.getSender(), args.getArgs()[0]);
		} catch (Exception ex) {
			_.msg(Prefix.LANG, LangKeyType.Lang.ERROR_SET, MsgType.FAIL, args.getSender(), args.getArgs()[0]);
		}
	}
}
