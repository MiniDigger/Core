package me.MiniDigger.CraftCore.Kit;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import me.MiniDigger.Core.Kit.Kit;
import me.MiniDigger.Core.Kit.KitHandler;
import me.MiniDigger.Core.User.User;

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
	public void removeKit(String name) {
		if (getKit(name) != null) {
			kits.remove(getKit(name));
		}
	}
	
	@Override
	public void give(User user, String kit) {
		give(user, getKit(kit));
	}
	
	@Override
	public void give(User user, Kit kit) {
		give(user.getPlayer(), kit);
	}
	
	@Override
	public void give(Player p, String kit) {
		give(p, getKit(kit));
	}
	
	@Override
	public void give(Player p, Kit kit) {
		p.getInventory().clear();
		for (int i = 0; i < p.getInventory().getContents().length; i++) {
			p.getInventory().getContents()[i] = kit.getContent()[i];
		}
		for (int i = 0; i < p.getInventory().getArmorContents().length; i++) {
			p.getInventory().getArmorContents()[i] = kit.getArmor()[i];
		}
	}
	
	@Override
	public List<Kit> getKits() {
		return kits;
	}
}
