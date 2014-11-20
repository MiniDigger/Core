package me.MiniDigger.CraftCore.Kit;

import java.util.ArrayList;
import java.util.List;

import me.MiniDigger.Core.Kit.Kit;
import me.MiniDigger.Core.Kit.KitHandler;

public class CoreKitHandler implements KitHandler {
	
	private List<Kit>	kits	= new ArrayList<Kit>();
	
	@Override
	public Kit createKit(String name) {
		Kit k = new CoreKit(name);
		
		return createKit(k);
	}
	
	@Override
	public Kit createKit(Kit kit) {
		if (getKit(kit.getName()) == null) {
			kits.add(kit);
			return kit;
		} else {
			System.out.println("A kit named " + kit.getName() + " already exists!");
			return null;
		}
	}
	
	@Override
	public Kit getKit(String name) {
		for (Kit k : kits) {
			if (k.getName().equalsIgnoreCase(name)) {
				return k;
			}
		}
		return null;
	}
	
	@Override
	public void removeKit(String name){
		if(getKit(name)!= null){
			kits.remove(getKit(name));
		}
	}
}
