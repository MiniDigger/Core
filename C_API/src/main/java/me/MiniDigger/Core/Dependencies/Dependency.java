package me.MiniDigger.Core.Dependencies;

public interface Dependency {
	
	String getFullName();
	
	/**
	 * @param version
	 *            the version to set
	 */
	void setVersion(String version);
	
	/**
	 * @return the version
	 */
	String getVersion();
	
	/**
	 * @param name
	 *            the name to set
	 */
	void setName(String name);
	
	/**
	 * @return the name
	 */
	String getName();
	
}
