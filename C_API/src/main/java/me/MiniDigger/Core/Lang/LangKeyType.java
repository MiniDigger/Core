package me.MiniDigger.Core.Lang;

import java.util.ArrayList;
import java.util.List;

public class LangKeyType {
	
	public static class Achievment {
		
		protected static String		type	= "achievment";
		public static LangKeyType	TEST	= new LangKeyType("test", type, "TEST");
		
		public static List<LangKeyType> values() {
			final List<LangKeyType> values = new ArrayList<>();
			values.add(TEST);
			return values;
		}
	}
	
	public static class AddOn {
		
		protected static String		    type		         = "addon";
		public static final LangKeyType	CREATE_CONFIG		 = new LangKeyType("create_config", type, "Creating ConfigFile for AddOn `%0%`");
		public static final LangKeyType	CREATE_FOLDER		 = new LangKeyType("create_folder", type, "Creating DataFolder for AddOn `%0%`");
		public static final LangKeyType	DISABLE		         = new LangKeyType("disable", type, "Disabling Addon `%0%` v`%1%` by `%2%`");
		public static final LangKeyType	ENABLE		         = new LangKeyType("enable", type, "Enabling Addon `%0%` v`%1%` by `%2%`");
		public static final LangKeyType	ERROR_ABNORMAL_TYPE		= new LangKeyType("error_abnormal_type", type, "Abnormal plugin type for main class `%0%`");
		public static final LangKeyType	ERROR_CREATE_CONFIG		= new LangKeyType("error_create_config", type, "Error while creating ConfigFile for AddOn `%0%`");
		public static final LangKeyType	ERROR_ENABLE		 = new LangKeyType("error_enable", type, "Could not enable AddOn `%0%` v`%1%` by `%2%`:");
		public static final LangKeyType	ERROR_FILE_CURRUPTED	= new LangKeyType("error_file_currupted", type, "AddOn file currupted! Creating new one...");
		public static final LangKeyType	ERROR_LOAD		     = new LangKeyType("error_load", type, "Could not load AddOn `%0%` v`%1%` by `%2%`:");
		public static final LangKeyType	ERROR_NO_CONSTRUCTOR	= new LangKeyType("error_no_constructor", type, "No public constructor in main class `%0%`");
		public static final LangKeyType	ERROR_NO_EXTEND		 = new LangKeyType("error_no_extend", type, "Main class `%0%` does not extend CoreAddOn");
		public static final LangKeyType	ERROR_NO_MAIN		 = new LangKeyType("error_no_main", type, "Cannot find main class `%0%`");
		public static final LangKeyType	ERROR_RESULT_NULL		= new LangKeyType("error_result_null", type, "Error while loading Addon `%0%`: Request returned null!");
		public static final LangKeyType	ERROR_SAVE_CONFIG		= new LangKeyType("error_save_config", type, "Error while saving ConfigFile for AddOn `%0%`");
		public static final LangKeyType	FOUND_UPDATE		 = new LangKeyType("found_update", type, "Found update for AddOn `%0%`: `%1%` -> `%2%`");
		public static final LangKeyType	LOAD		         = new LangKeyType("load", type, "Loading Addon `%0%` v`%1%` by `%2%`");
		
		public static List<LangKeyType> values() {
			final List<LangKeyType> values = new ArrayList<>();
			values.add(FOUND_UPDATE);
			return values;
		}
	}
	
	public static LangKeyType valueOf(final String s) {
		for (final LangKeyType type : values()) {
			if (type.getFullType().equalsIgnoreCase(s)) {
				return type;
			}
		}
		return null;
	}
	
	public static List<LangKeyType> values() {
		final List<LangKeyType> values = new ArrayList<>();
		values.addAll(Achievment.values());
		return values;
	}
	
	private final String	key;
	private final String	type;
	private final String	value;
	
	public LangKeyType(final String key, final String type, final String value) {
		this.key = key;
		this.type = type;
		this.value = value;
	}
	
	public String getDefaultValue() {
		return value;
	}
	
	public String getFullType() {
		return type + "_" + key;
	}
	
	public String getKey() {
		return key;
	}
	
	public String getType() {
		return type;
	}
}
