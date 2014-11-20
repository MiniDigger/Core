package me.MiniDigger.Core.Kit;

import org.bukkit.inventory.ItemStack;

public interface Kit {
	
	/**
	 * @param armor
	 *            the new armor to set (length needs to be 8, 5-8 are the slots)
	 */
	public void setArmor(ItemStack[] armor);
	
	/**
	 * @return the armor
	 */
	public ItemStack[] getArmor();
	
	/**
	 * @param content
	 *            the new content to set (length needs to be 44,9-44 are the
	 *            slots)
	 */
	public void setContent(ItemStack[] content);
	
	/**
	 * @return the content
	 */
	public ItemStack[] getContent();
	
	/**
	 * @param charge
	 *            how much a use of this kit should cost
	 */
	public void setCharge(int charge);
	
	/**
	 * @return how much a use of this kit costs
	 */
	public int getCharge();
	
	/**
	 * @param perm
	 *            the permission to use this kit
	 */
	public void setPerm(String perm);
	
	/**
	 * @return the permission to use this kit
	 */
	public String getPerm();
	
	/**
	 * @param name
	 *            the new name of this kit
	 */
	public void setName(String name);
	
	/**
	 * @return the name of this kit
	 */
	public String getName();
	
	/**
	 * @param slot
	 *            the slot
	 * @param is
	 *            the new itemstack to set into the slot
	 */
	public void setArmor(int slot, ItemStack is);
	
	/**
	 * @param slot
	 *            the slot
	 * @param is
	 *            the new itemstack to set into the slot
	 */
	public void setContent(int slot, ItemStack is);
	
}
