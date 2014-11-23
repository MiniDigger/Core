package me.MiniDigger.CraftCore.Kit;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.MiniDigger.Core.Kit.Kit;
import me.MiniDigger.Core.SQL.Saveable;

public class CoreKit implements Kit, Saveable {
	
	private String	    name;
	private String	    perm;
	private int	        charge;
	
	private ItemStack[]	content;
	private ItemStack[]	armor;
	
	public CoreKit(String name) {
		this(name, "");
	}
	
	public CoreKit(String name, String perm) {
		this(name, perm, 0);
	}
	
	public CoreKit(String name, String perm, int charge) {
		this.name = name;
		this.perm = perm;
		this.charge = charge;
		
		armor = new ItemStack[8];
		for (int i = 5; i < armor.length; i++) {
			armor[i] = new ItemStack(Material.AIR);
		}
		
		content = new ItemStack[44];
		for (int i = 9; i < content.length; i++) {
			content[i] = new ItemStack(Material.AIR);
		}
	}
	
	@Override
	public void setArmor(int slot, ItemStack is) {
		armor[slot] = is;
	}
	
	@Override
	public void setContent(int slot, ItemStack is) {
		content[slot] = is;
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
	public String getPerm() {
		return perm;
	}
	
	@Override
	public void setPerm(String perm) {
		this.perm = perm;
	}
	
	@Override
	public int getCharge() {
		return charge;
	}
	
	@Override
	public void setCharge(int charge) {
		this.charge = charge;
	}
	
	@Override
	public ItemStack[] getContent() {
		return content;
	}
	
	@Override
	public void setContent(ItemStack[] content) {
		this.content = content;
	}
	
	@Override
	public ItemStack[] getArmor() {
		return armor;
	}
	
	@Override
	public void setArmor(ItemStack[] armor) {
		this.armor = armor;
	}

	@Override
    public boolean save() {
	    // TODO Auto-generated method stub
	    return false;
    }

	@Override
    public boolean load() {
	    // TODO Auto-generated method stub
	    return false;
    }

	@Override
    public boolean createTable() {
	    // TODO Auto-generated method stub
	    return false;
    }
}
