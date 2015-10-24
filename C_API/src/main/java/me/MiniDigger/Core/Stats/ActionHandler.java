package me.MiniDigger.Core.Stats;

public interface ActionHandler {

	/**
	 * initialize shitt
	 */
	void start();

	/**
	 * creates the table
	 */
	void createTable();

	/**
	 * inserts all pending actions
	 */
	void insertWaiting();

	/**
	 * adds a new action to the queque
	 * 
	 * @param a
	 */
	void newAction(Action a);

	/**
	 * stops the timer and inserts pending actions
	 */
	void stop();
}
