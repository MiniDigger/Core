package me.MiniDigger.CraftCore.Lang;

import java.io.File;
import java.util.HashMap;

import me.MiniDigger.Core.Lang.LangKeyType;
import me.MiniDigger.Core.Lang.LangStorage;
import me.MiniDigger.Core.Lang.LangType;

public class CoreLangStorage implements LangStorage {
	
	private LangType	                 lang;
	private HashMap<LangKeyType, String>	values	= new HashMap<>();
	
	@Override
	public LangType getLangType() {
		return lang;
	}
	
	@Override
	public String get(LangKeyType type) {
		if (values.containsKey(type)) {
			return values.get(type);
		} else {
			return type.getDefaultValue();
		}
	}
	
	@Override
	public void load(File file) {
		
	}
	
	@Override
	public void save(File file) {
		
	}
}
