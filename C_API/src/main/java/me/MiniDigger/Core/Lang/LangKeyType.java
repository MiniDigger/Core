package me.MiniDigger.Core.Lang;

import java.util.ArrayList;
import java.util.List;

public class LangKeyType {
	
	public static class Achievment {
		
		protected static String	type	= "achievment";
		public static LangKeyType	TEST	= new LangKeyType("test", type, "TEST");
		
		public static List<LangKeyType> values() {
			final List<LangKeyType> values = new ArrayList<>();
			values.add(TEST);
			return values;
		}
	}
	
	public static List<LangKeyType> values() {
		final List<LangKeyType> values = new ArrayList<>();
		values.addAll(Achievment.values());
		return values;
	}
	
	public static LangKeyType valueOf(final String s) {
		for (final LangKeyType type : values()) {
			if (type.getFullType().equalsIgnoreCase(s)) {
				return type;
			}
		}
		return null;
	}
	
	private final String	key;
	private final String	type;
	private final String	     value;
	
	public LangKeyType(final String key, final String type, final String value) {
		this.key = key;
		this.type = type;
		this.value = value;
	}
	
	public String getKey() {
		return key;
	}
	
	public String getType() {
		return type;
	}
	
	public String getFullType() {
		return type + "." + key;
	}
	
	public String getDefaultValue() {
		return value;
	}	
}
