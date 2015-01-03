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
package me.MiniDigger.CraftCore.Lang;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Lang.LangHandler;
import me.MiniDigger.Core.Lang.LangKeyType;
import me.MiniDigger.Core.Lang.LangStorage;
import me.MiniDigger.Core.Lang.LangType;
import me.MiniDigger.Core.Lang.LogLevel;

public class CoreLangHandler implements LangHandler {
	
	private List<LangStorage>	langs	  = new ArrayList<LangStorage>();
	private LangType	      defaultLang	= LangType.en_US;
	private LogLevel	      log;
	private final File	      langFolder	= new File(Core.getCore().getInstance().getDataFolder(), "lang");
	
	@Override
	public void load() {
		if (!langFolder.exists()) {
			langFolder.mkdirs();
		}
		
		log = LogLevel.valueOf(Core.getCore().getInstance().getConfig().getString("log-level"));
		if (log == null) {
			log = LogLevel.DEBUG;
		}
		defaultLang = LangType.valueOf(Core.getCore().getInstance().getConfig().getString("default-lang"));
		
		for (final File f : langFolder.listFiles()) {
			if (f.isFile()) {
				if (f.getName().endsWith(".lang")) {
					final LangStorage s = new CoreLangStorage();
					s.load(f);
					langs.add(s);
					_.log(LogLevel.INFO, LangKeyType.Lang.LOAD, s.getLangType(), s.getLangType().getCode(), s.getAuthor());
				}
			}
		}
		
		if (langs.size() == 0) {
			System.out.println("Saving default lang!");
			LangStorage s = new CoreLangStorage();
			s.setAuthor("MiniDigger");
			s.setLangType(LangType.en_US);
			s.save(new File(langFolder, "en_US.lang"));
			System.out.println("reload");
			load();
		}
	}
	
	@Override
	public LangStorage getStorage(final LangType type) {
		if (type == defaultLang) {
			for (final LangStorage s : langs) {
				if (s.getLangType().equals(type)) {
					return s;
				}
			}
			return langs.get(0);
		}
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