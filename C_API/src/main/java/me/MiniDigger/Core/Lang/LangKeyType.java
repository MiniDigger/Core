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
package me.MiniDigger.Core.Lang;

import java.util.ArrayList;
import java.util.List;

public class LangKeyType {
	
	public static class Achievment {
		
		protected static String		    type		= "achievment";
		public static final LangKeyType	MSG_JOIN	= new LangKeyType("msg_join", type, "You have joined %0% times");
		public static final LangKeyType	TITLE_JOIN	= new LangKeyType("title_join", type, "Joins");
		
		public static List<LangKeyType> values() {
			final List<LangKeyType> values = new ArrayList<>();
			values.add(TITLE_JOIN);
			values.add(MSG_JOIN);
			return values;
		}
	}
	
	public static class AddOn {
		
		protected static String		    type		         = "addon";
		public static final LangKeyType	CREATE_CONFIG		 = new LangKeyType("create_config", type, "Creating ConfigFile for AddOn `%0%`");
		public static final LangKeyType	CREATE_FOLDER		 = new LangKeyType("create_folder", type, "Creating DataFolder for AddOn `%0%`");
		public static final LangKeyType	DISABLE		         = new LangKeyType("disable", type, "Disabling Addon `%0%` v`%1% by `%2%`");
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
		public static final LangKeyType	SHOW_URL		     = new LangKeyType("load", type, "Url for addon `%0%`: `%1%`");
		
		public static List<LangKeyType> values() {
			final List<LangKeyType> values = new ArrayList<>();
			values.add(CREATE_CONFIG);
			values.add(CREATE_FOLDER);
			values.add(DISABLE);
			values.add(ENABLE);
			values.add(ERROR_ABNORMAL_TYPE);
			values.add(ERROR_CREATE_CONFIG);
			values.add(ERROR_ENABLE);
			values.add(ERROR_FILE_CURRUPTED);
			values.add(ERROR_LOAD);
			values.add(ERROR_NO_CONSTRUCTOR);
			values.add(ERROR_NO_EXTEND);
			values.add(ERROR_NO_MAIN);
			values.add(ERROR_RESULT_NULL);
			values.add(ERROR_SAVE_CONFIG);
			values.add(FOUND_UPDATE);
			values.add(LOAD);
			return values;
		}
	}
	
	public static class Cmd {
		
		protected static String		    type		  = "cmd";
		public static final LangKeyType	EXCEPTION		= new LangKeyType("exception", type, "Unhandled exception executing command `%0%` in plugin `%1%`!");
		public static final LangKeyType	EXCEPTION_TAB	= new LangKeyType("exception_tab", type,
		                                                      "Unhandled exception during tab completion for command `%0%` in plugin `%1%`");
		public static final LangKeyType	CANNOT_NULL		= new LangKeyType("cannot_null", type, "`%0%` cannot be null");
		
		public static List<LangKeyType> values() {
			final List<LangKeyType> values = new ArrayList<>();
			values.add(EXCEPTION);
			values.add(EXCEPTION_TAB);
			values.add(CANNOT_NULL);
			return values;
		}
	}
	
	public static class SQL {
		
		protected static String		    type		        = "sql";
		public static final LangKeyType	QUERY_FAILED		= new LangKeyType("query_failed", type, "ResultSet returned by query can not be null!");
		public static final LangKeyType	CREATE_TABLE		= new LangKeyType("create_table", type, "Creating table for `%0%`");
		public static final LangKeyType	CREATE_TABLE_FAILED	= new LangKeyType("create_table_failed", type, "Didn't created table!");
		
		public static List<LangKeyType> values() {
			final List<LangKeyType> values = new ArrayList<>();
			values.add(QUERY_FAILED);
			return values;
		}
	}
	
	public static class World {
		
		protected static String		    type		     = "world";
		public static final LangKeyType	LOADING_CHUNKS		= new LangKeyType("loading_chunks", type, "Loading Chunks...");
		public static final LangKeyType	CHUNKS_LOADED		= new LangKeyType("chunks_loaded", type, "`%0%` chunks loaded!");
		public static final LangKeyType	CHUNK_LOAD_ERROR	= new LangKeyType("chunk_load_error", type, "Error while loading chunks: `%0%`!");
		public static final LangKeyType	DELETE_OLD		 = new LangKeyType("delete_old", type, "Old map found, deleting...");
		public static final LangKeyType	UNZIP		     = new LangKeyType("unzip", type, "Unzipping `%0%` to `%1%`");
		public static final LangKeyType	LOADING_WORLD		= new LangKeyType("loading_world", type, "Loading world `%0%`...");
		public static final LangKeyType	FILE_ERROR		 = new LangKeyType("file_error", type, "File exists with the name `%0%` and isn't a folder");
		public static final LangKeyType	CONVERTING		 = new LangKeyType("converting", type, "Converting world `%0%`...");
		public static final LangKeyType	CREATED		     = new LangKeyType("created", type, "Created world with dimension `%0%`");
		
		public static List<LangKeyType> values() {
			final List<LangKeyType> values = new ArrayList<>();
			values.add(LOADING_CHUNKS);
			values.add(CHUNKS_LOADED);
			values.add(CHUNK_LOAD_ERROR);
			values.add(DELETE_OLD);
			values.add(UNZIP);
			values.add(LOADING_WORLD);
			values.add(FILE_ERROR);
			values.add(CONVERTING);
			values.add(CREATED);
			return values;
		}
	}
	
	public static class Lang {
		
		protected static String		    type		           = "lang";
		public static final LangKeyType	ERROR_LOAD		       = new LangKeyType("error_load", type, "Failed to load translation file `%0%`!");
		public static final LangKeyType	ERROR_NO_ARG		   = new LangKeyType("no_arg", type, "Failed to translate `%%0%%`, no arg provided... (`%1%`)");
		public static final LangKeyType	ERROR_NO_AUTHOR		   = new LangKeyType("error_no_author", type, "Looked for author in file `%0%` but found `%1%`!");
		public static final LangKeyType	ERROR_NO_DESCRIPTION	= new LangKeyType("error_no_description", type, "Desription missing for translation file `%0%`!");
		public static final LangKeyType	ERROR_NO_LANG_KEY		= new LangKeyType("error_no_lang_key", type, "Looked for lang_key in file `%0%` but found `%1%`!");
		public static final LangKeyType	ERROR_NO_LANGS		   = new LangKeyType("no_langs", type, "No langs loaded!");
		public static final LangKeyType	ERROR_SAVE		       = new LangKeyType("error_save", type, "Error while saving lang to file `%0%`");
		public static final LangKeyType	ERROR_SET		       = new LangKeyType("error_set", type, "Unknown language `%0%`");
		public static final LangKeyType	LOAD		           = new LangKeyType("load", type, "Lang `%0%`, translated by `%1%`, loaded!");
		public static final LangKeyType	SET		               = new LangKeyType("set", type, "Your language was changed to `%0%`!");
		public static final LangKeyType	WARNING_NOT_MATCHED		= new LangKeyType("warning_not_matched", type, "Could not find lang key for key `%0%` in file `%1%`");
		public static final LangKeyType	WARNING_NOT_TRANSLATED	= new LangKeyType("warning_not_translated", type,
		                                                               "Could not find lang key `%0%` in file `%1%`: Using default value");
		
		public static List<LangKeyType> values() {
			final List<LangKeyType> values = new ArrayList<>();
			values.add(LOAD);
			values.add(ERROR_LOAD);
			values.add(ERROR_NO_DESCRIPTION);
			values.add(ERROR_NO_AUTHOR);
			values.add(ERROR_NO_LANG_KEY);
			values.add(WARNING_NOT_MATCHED);
			values.add(WARNING_NOT_TRANSLATED);
			values.add(ERROR_SAVE);
			values.add(ERROR_SET);
			values.add(SET);
			values.add(ERROR_NO_ARG);
			return values;
		}
	}
	
	public static class Log {
		
		protected static String		    type	= "log";
		public static final LangKeyType	CATCHED	= new LangKeyType("catched", type, "Catched an error(`%0%`): `%1%`");
		
		public static List<LangKeyType> values() {
			final List<LangKeyType> values = new ArrayList<>();
			values.add(CATCHED);
			return values;
		}
	}
	
	public static class Main {
		
		protected static String		    type		         = "main";
		public static final LangKeyType	ACTIVATE		     = new LangKeyType("activate", type, "Activating `%0%` by `%1%`");
		public static final LangKeyType	ACTIVATE_HANDLER		= new LangKeyType("activate_handler", type, "Activateing handler...");
		public static final LangKeyType	ACTIVATE_SOCKET		 = new LangKeyType("activate_socket", type, "Activating sockets...");
		public static final LangKeyType	ACTIVATED		     = new LangKeyType("activated", type, "Activated! (`%0%ms`)");
		public static final LangKeyType	APPLY_FIXES		     = new LangKeyType("apply_fixes", type, "Appling fixes...");
		public static final LangKeyType	CHECK_DONE		     = new LangKeyType("check_done", type, "Check done (`%0%ms`)");
		public static final LangKeyType	CHECK_LICENCE		 = new LangKeyType("check_licence", type, "Checking licence...");
		public static final LangKeyType	CHECK_LICENCE_FAILED	= new LangKeyType("check_licence_failed", type, "Licence check FAILED! (`%0%ms`)");
		public static final LangKeyType	CHECK_LICENCE_PASSED	= new LangKeyType("check_licence_passed", type, "Licence check passed! (`%0%ms`)");
		public static final LangKeyType	CHECK_UPDATES		 = new LangKeyType("check_licence", type, "Checking updater...");
		public static final LangKeyType	ENABLE_LIBS		     = new LangKeyType("enable_libs", type, "Enabling external dependencies...");
		public static final LangKeyType	ENABLE_LOGGING		 = new LangKeyType("enable_logging", type, "Enabling logging...");
		public static final LangKeyType	ERROR		         = new LangKeyType("error", type, "Error: `%0%`");
		public static final LangKeyType	LOAD_DATA		     = new LangKeyType("load_data", type, "Load data...");
		public static final LangKeyType	REGISTER_COMMANDS		= new LangKeyType("register_commands", type, "Registering commands...");
		public static final LangKeyType	REGISTER_LISTENER		= new LangKeyType("register_listener", type, "Registering listener...");
		public static final LangKeyType	SAVE_CONFIG		     = new LangKeyType("save_config", type, "Saving config...");
		public static final LangKeyType	SEARCH_GAME		     = new LangKeyType("search_game", type, "Searching main game...");
		public static final LangKeyType	DEACTIVATE		     = new LangKeyType("deactivate", type, "Deactivating");
		public static final LangKeyType	UNREGISTER_LISTENER		= new LangKeyType("unregister_listener", type, "Unregister listener...");
		public static final LangKeyType	STOP_TASKS		     = new LangKeyType("stop_tasks", type, "Stopping tasks...");
		public static final LangKeyType	DEACTIVATE_HANDLER		= new LangKeyType("deactivate_handler", type, "Deaktivating handlers...");
		public static final LangKeyType	DEACTIVATED		     = new LangKeyType("deactivated", type, "Deactivated!");
		public static final LangKeyType	NOT_LOADED		     = new LangKeyType("not_loaded", type, "`%0%` not loaded!");
		public static final LangKeyType	LOAD		         = new LangKeyType("load", type, "Loading `%0%`");
		public static final LangKeyType	LISTENER_NULL		 = new LangKeyType("listener_null", type, "A listener was null! Skipping...");
		
		public static List<LangKeyType> values() {
			final List<LangKeyType> values = new ArrayList<>();
			values.add(ACTIVATE);
			values.add(ACTIVATED);
			values.add(CHECK_LICENCE);
			values.add(CHECK_LICENCE_PASSED);
			values.add(CHECK_LICENCE_FAILED);
			values.add(CHECK_UPDATES);
			values.add(SAVE_CONFIG);
			values.add(ENABLE_LIBS);
			values.add(REGISTER_COMMANDS);
			values.add(ACTIVATE_HANDLER);
			values.add(LOAD_DATA);
			values.add(REGISTER_LISTENER);
			values.add(APPLY_FIXES);
			values.add(SEARCH_GAME);
			values.add(ENABLE_LOGGING);
			values.add(ACTIVATE_SOCKET);
			values.add(DEACTIVATE);
			values.add(UNREGISTER_LISTENER);
			values.add(STOP_TASKS);
			values.add(DEACTIVATE_HANDLER);
			values.add(DEACTIVATED);
			values.add(LOAD);
			values.add(LISTENER_NULL);
			return values;
		}
	}
	
	public static class Socket {
		
		protected static String		    type	= "socket";
		public static final LangKeyType	START	= new LangKeyType("start", type, "Starting socket server...");
		public static final LangKeyType	START_C	= new LangKeyType("start-c", type, "Starting socket client...");
		public static final LangKeyType	STARTED	= new LangKeyType("started", type, "Socket server started!");
		public static final LangKeyType	STOP	= new LangKeyType("stop", type, "Stopping socket server...");
		public static final LangKeyType	STOP_C	= new LangKeyType("stop-c", type, "Stopping socket client...");
		
		public static List<LangKeyType> values() {
			final List<LangKeyType> values = new ArrayList<>();
			values.add(START);
			values.add(START_C);
			values.add(STARTED);
			values.add(STOP);
			values.add(STOP_C);
			return values;
		}
	}
	
	public static final LangKeyType valueOf(final String s) {
		for (final LangKeyType type : values()) {
			if (type.getFullType().equalsIgnoreCase(s)) {
				return type;
			}
		}
		return null;
	}
	
	public static List<LangKeyType> values() {
		final List<LangKeyType> values = new ArrayList<>();
		values.addAll(Lang.values());
		values.addAll(Achievment.values());
		values.addAll(AddOn.values());
		values.addAll(Main.values());
		values.addAll(Log.values());
		values.addAll(Socket.values());
		values.addAll(Cmd.values());
		values.addAll(World.values());
		values.addAll(SQL.values());
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
