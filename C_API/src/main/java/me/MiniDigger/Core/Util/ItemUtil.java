package me.MiniDigger.Core.Util;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public interface ItemUtil {
	
	String invToBase64(Inventory inventory);
	
	Inventory invFromBase64(String data);
	
	String itemToBase64(ItemStack is);
	
	ItemStack itemFromBase64(String data);
	
	ItemStack[] itemArrayFromBase64(String data);
	
	String itemArrayToBase64(ItemStack[] is);
	
}
