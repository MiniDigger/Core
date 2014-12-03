package me.MiniDigger.Core.REST;

import org.json.simple.JSONObject;


public interface DashingHandler {

	/**
	 * Sends a post for the widget with the content
	 * 
	 * @param widget 
	 * @param content
	 */
	void go(String widget, JSONObject content);

	/**
	 * Sends to 10 most speaked words
	 */
	void mostWords();

	/**
	 * Registers a new chat entry
	 * 
	 * @param word
	 */
	void say(String msg);

	/**
	 * inits everything
	 */
	void init();
	
}
