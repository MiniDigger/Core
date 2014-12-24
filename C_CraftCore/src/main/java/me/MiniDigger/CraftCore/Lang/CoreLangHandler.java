package me.MiniDigger.CraftCore.Lang;

import java.util.ArrayList;

import me.MiniDigger.Core.Lang.LangHandler;
import me.MiniDigger.Core.Lang.LangStorage;
import me.MiniDigger.Core.Lang.LangType;
import me.MiniDigger.Core.Lang.LogLevel;

public class CoreLangHandler implements LangHandler {
	
	private ArrayList<LangStorage>	langs;
	private LangType	           defaultLang;
	private LogLevel	           log;
	
	@Override
	public void load() {
		
	}
	
	@Override
	public LangStorage getStorage(LangType type) {
		if (type == null) {
			if (defaultLang == null) {
				return langs.get(0);
			}
			return getStorage(defaultLang);
		}
		for (LangStorage s : langs) {
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
