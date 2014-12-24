package me.MiniDigger.Core.Lang;

public interface LangHandler {
	
	/**
	 * Retruns the storage for this lang
	 * 
	 * @param type
	 * @return
	 */
	LangStorage getStorage(LangType type);
	
	/**
	 * Returns the storage for the default lang
	 * 
	 * @return
	 */
	LangStorage getStorage();
	
	/**
	 * Load all avialable storages from file system
	 */
	void load();
	
	/**
	 * @return the minimum level of messages that should be logged
	 */
	LogLevel getLogLevel();
	
	/**
	 * @return the fallback lang
	 */
	LangType getDefaultLang();
	
}
