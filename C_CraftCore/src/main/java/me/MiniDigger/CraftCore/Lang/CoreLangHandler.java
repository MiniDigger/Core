package me.MiniDigger.CraftCore.Lang;

import java.io.File;
import java.util.ArrayList;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Lang.LangHandler;
import me.MiniDigger.Core.Lang.LangKeyType;
import me.MiniDigger.Core.Lang.LangStorage;
import me.MiniDigger.Core.Lang.LangType;
import me.MiniDigger.Core.Lang.LogLevel;

public class CoreLangHandler implements LangHandler {
	
	private ArrayList<LangStorage>	langs;
	private LangType	           defaultLang	= LangType.en_US;
	private LogLevel	           log;
	private File	               langFolder	= new File(Core.getCore().getInstance().getDataFolder(), "lang");
	
	@Override
	public void load() {
		if (!langFolder.exists()) {
			langFolder.mkdirs();
		}
		for (File f : langFolder.listFiles()) {
			if (f.isFile()) {
				if (f.getName().endsWith(".lang")) {
					LangStorage s = new CoreLangStorage();
					s.load(f);
					langs.add(s);
					_.log(LogLevel.INFO, LangKeyType.Lang.LOAD, s.getLangType(), s.getLangType().getCode(), s.getAuthor());
				}
			}
		}
		
		defaultLang = LangType.valueOf(Core.getCore().getInstance().getConfig().getString("default-lang"));
	}
	
	@Override
	public LangStorage getStorage(final LangType type) {
		if (type == null) {
			if (defaultLang == null) {
				return langs.get(0);
			}
			return getStorage(defaultLang);
		}
		for (final LangStorage s : langs) {
			if (s.getLangType().equals(type)) {
				return s;
			}
		}
		return getStorage(defaultLang);
	}
	
	@Override
	public LangStorage getStorage() {
		return getStorage(defaultLang);
	}
	
	@Override
	public LogLevel getLogLevel() {
		return log;
	}
	
	@Override
	public LangType getDefaultLang() {
		return defaultLang;
	}
}
