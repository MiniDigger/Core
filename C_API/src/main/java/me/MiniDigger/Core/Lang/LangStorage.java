package me.MiniDigger.Core.Lang;

import java.io.File;

public interface LangStorage {
	
	/**
	 * Load this storage from this file
	 * 
	 * @param file
	 */
	void load(File file);
	
	/**
	 * Save all changes to the file
	 * 
	 * @param file
	 */
	void save(File file);
	
	/**
	 * @param type
	 *            the key
	 * @return the translated key
	 */
	String get(LangKeyType type);
	
	/**
	 * @return the language this storage is storing
	 */
	LangType getLangType();
	
	/**
	 * @return the translator of this file
	 */
	String getAuthor();
	
}
