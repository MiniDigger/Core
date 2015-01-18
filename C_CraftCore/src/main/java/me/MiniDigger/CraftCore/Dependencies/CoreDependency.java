package me.MiniDigger.CraftCore.Dependencies;

import me.MiniDigger.Core.Dependencies.Dependency;

public class CoreDependency implements Dependency {
	
	private String	name;
	private String	version;
	
	public CoreDependency(String name, String version) {
		this.name = name;
		this.version = version;
	}
	
	
	@Override
	public String getName() {
		return name;
	}
	

	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	
	@Override
	public String getVersion() {
		return version;
	}

	@Override
	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String getFullName(){
		return getName() + "-" + getVersion();
	}
}
