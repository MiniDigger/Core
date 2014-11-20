package me.MiniDigger.Core.Kit;

public interface KitHandler {
	
	/**
	 * Creates an empty kit with that name
	 * 
	 * @param name
	 * @return
	 */
	public Kit createKit(String name);
	
	/**
	 * Adds an precreated kit to the list
	 * 
	 * @param kit
	 * @return
	 */
	public Kit createKit(Kit kit);
	
	/**
	 * @param name
	 * @return the kit with that name, can be null
	 */
	public Kit getKit(String name);
	
	/**
	 * Removes the kit with that name if exists
	 * 
	 * @param name
	 */
	public void removeKit(String name);
	
}
