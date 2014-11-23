/**
 * █████████████████████████████████████████████████████████████████████████████████████████████████████████████████
 * █░░░░░░░░░░░░░░█░░░░░░██░░░░░░█░░░░░░░░░░░░░░████░░░░░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░░░░░░░░░░░███░░░░░░░░░░░░░░█
 * █░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░████░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀▄▀░░███░░▄▀▄▀▄▀▄▀▄▀░░█
 * █░░░░░░▄▀░░░░░░█░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░░░░░████░░▄▀░░░░░░░░░░█░░▄▀░░░░░░▄▀░░█░░▄▀░░░░░░░░▄▀░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░████████████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░████░░▄▀░░███░░▄▀░░█████████
 * █████░░▄▀░░█████░░▄▀░░░░░░▄▀░░█░░▄▀░░░░░░░░░░████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░░░▄▀░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀▄▀░░███░░▄▀▄▀▄▀▄▀▄▀░░█
 * █████░░▄▀░░█████░░▄▀░░░░░░▄▀░░█░░▄▀░░░░░░░░░░████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░▄▀░░░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░████████████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░██░░▄▀░░█████░░▄▀░░█████████
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░░░░░████░░▄▀░░░░░░░░░░█░░▄▀░░░░░░▄▀░░█░░▄▀░░██░░▄▀░░░░░░█░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░████░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░██░░▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█
 * █████░░░░░░█████░░░░░░██░░░░░░█░░░░░░░░░░░░░░████░░░░░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░██░░░░░░░░░░█░░░░░░░░░░░░░░█
 * █████████████████████████████████████████████████████████████████████████████████████████████████████████████████
 * 
 * Copyright © MiniDigger and others - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Martin Benndorf <admin@minidigger.me>, 2013-2014 and others
 */
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
	
	public CoreKit(final String name) {
		this(name, "");
	}
	
	public CoreKit(final String name, final String perm) {
		this(name, perm, 0);
	}
	
	public CoreKit(final String name, final String perm, final int charge) {
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
	public void setArmor(final int slot, final ItemStack is) {
		armor[slot] = is;
	}
	
	@Override
	public void setContent(final int slot, final ItemStack is) {
		content[slot] = is;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public void setName(final String name) {
		this.name = name;
	}
	
	@Override
	public String getPerm() {
		return perm;
	}
	
	@Override
	public void setPerm(final String perm) {
		this.perm = perm;
	}
	
	@Override
	public int getCharge() {
		return charge;
	}
	
	@Override
	public void setCharge(final int charge) {
		this.charge = charge;
	}
	
	@Override
	public ItemStack[] getContent() {
		return content;
	}
	
	@Override
	public void setContent(final ItemStack[] content) {
		this.content = content;
	}
	
	@Override
	public ItemStack[] getArmor() {
		return armor;
	}
	
	@Override
	public void setArmor(final ItemStack[] armor) {
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
