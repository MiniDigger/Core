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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

import me.MiniDigger.Core.Lang.LangKeyType;
import me.MiniDigger.Core.Lang.LangStorage;
import me.MiniDigger.Core.Lang.LangType;
import me.MiniDigger.Core.Lang.LogLevel;

public class CoreLangStorage implements LangStorage {
	
	private LangType	                       lang;
	private String	                           author;
	private final HashMap<LangKeyType, String>	values	= new HashMap<>();
	
	private static final String	               LANG_KEY	= "LANG_KEY";
	private static final String	               AUTHOR	= "AUTHOR";
	
	@Override
	public LangType getLangType() {
		return lang;
	}
	
	@Override
	public void setLangType(final LangType type) {
		lang = type;
	}
	
	@Override
	public void setAuthor(final String author) {
		this.author = author;
	}
	
	@Override
	public String get(final LangKeyType type) {
		if (values.containsKey(type)) {
			return values.get(type);
		} else {
			return type.getDefaultValue();
		}
	}
	
	@Override
	public void load(final File file) {
		try {
			final List<String> lines = Files.readAllLines(Paths.get(file.toURI()), Charset.defaultCharset());
			if (lines.size() < 2) {
				_.log(LogLevel.WARNING, LangKeyType.Lang.ERROR_NO_DESCRIPTION, file.getAbsolutePath());
				return;
			}
			int x = 0;
			String s = lines.get(x);
			while (s.startsWith("#")) {
				s = lines.get(x);
				x++;
			}
			
			if (s.startsWith(LANG_KEY + "=")) {
				s = s.replace(LANG_KEY + "=", "");
				lang = LangType.valueOf(s);
			} else {
				_.log(LogLevel.WARNING, LangKeyType.Lang.ERROR_NO_LANG_KEY, file.getAbsolutePath(), s);
			}
			
			x++;
			while (s.startsWith("#")) {
				s = lines.get(x);
				x++;
			}
			
			if (s.startsWith(AUTHOR + "=")) {
				s = s.replace(AUTHOR + "=", "");
				author = s;
			} else {
				_.log(LogLevel.WARNING, LangKeyType.Lang.ERROR_NO_AUTHOR, file.getAbsolutePath(), s);
			}
			
			for (int i = 2; i < lines.size(); i++) {
				s = lines.get(i);
				if (s.startsWith("#")) {
					continue;
				}
				for (final LangKeyType type : LangKeyType.values()) {
					if (s.startsWith(type.getFullType())) {
						s = s.replace(type.getFullType() + "=", "");
						values.put(type, s);
						continue;
					}
				}
				_.log(LogLevel.WARNING, LangKeyType.Lang.WARNING_NOT_MATCHED, s, file.getAbsolutePath());
			}
			
			for (final LangKeyType type : LangKeyType.values()) {
				if (!values.containsKey(type)) {
					values.put(type, type.getDefaultValue());
					_.log(LogLevel.WARNING, LangKeyType.Lang.WARNING_NOT_TRANSLATED, type.getFullType(), file.getAbsolutePath());
					break;
				}
			}
		} catch (final IOException e) {
			_.log(LogLevel.ERROR, LangKeyType.Lang.ERROR_LOAD, file.getAbsolutePath());
			e.printStackTrace();
		}
	}
	
	@Override
	public void save(final File file) {
		PrintWriter w;
		try {
			w = new PrintWriter(file);
			
			if (lang != null) {
				w.println(LANG_KEY + "=" + lang.getCode());
			} else {
				w.println(LANG_KEY + "=INSERT LANG CODE HERE!");
			}
			
			if (author != null) {
				w.println(AUTHOR + "=" + author);
			} else {
				w.println(AUTHOR + "=INSERT YOUR NAME HERE!");
			}
			
			for (final LangKeyType type : values.keySet()) {
				w.println(type.getFullType() + "=" + values.get(type));
			}
			w.close();
		} catch (final FileNotFoundException e) {
			_.log(LogLevel.ERROR, LangKeyType.Lang.ERROR_SAVE, file.getAbsolutePath());
			e.printStackTrace();
		}
	}
	
	@Override
	public String getAuthor() {
		return author;
	}
}
