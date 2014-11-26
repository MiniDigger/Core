package me.MiniDigger.CraftCore.Util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import me.MiniDigger.Core.Util.ItemUtil;

/*
 * https://gist.github.com/aadnk/8138186
 */
public class CoreItemUtil implements ItemUtil {
	
	@Override
	public String itemArrayToBase64(ItemStack[] is) {
		try {
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);
			
			dataOutput.writeInt(is.length);
			
			for (int i = 0; i < is.length; i++) {
				dataOutput.writeObject(is[i]);
			}
			
			dataOutput.close();
			return Base64Coder.encodeLines(outputStream.toByteArray());
		} catch (Exception e) {
			throw new IllegalStateException("Unable to save item stacks.", e);
		}
	}
	
	@Override
	public ItemStack[] itemArrayFromBase64(String data) {
		try {
			ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
			BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
			
			ItemStack[] is = new ItemStack[dataInput.readInt()];
			
			for (int i = 0; i < is.length; i++) {
				is[i] = (ItemStack) dataInput.readObject();
			}
			
			dataInput.close();
			return is;
		} catch (Exception e) {
			System.out.println("Unable to decode class type: " + e.getMessage());
		}
		return null;
	}
	
	@Override
	public String itemToBase64(ItemStack is) {
		try {
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);
			
			dataOutput.writeObject(is);
			
			dataOutput.close();
			return Base64Coder.encodeLines(outputStream.toByteArray());
		} catch (Exception e) {
			throw new IllegalStateException("Unable to save item stack.", e);
		}
	}
	
	@Override
	public ItemStack itemFromBase64(String data) {
		try {
			ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
			BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
			
			ItemStack is = (ItemStack) dataInput.readObject();
			
			dataInput.close();
			return is;
		} catch (Exception e) {
			System.out.println("Unable to decode class type: " + e.getMessage());
		}
		return null;
	}
	
	@Override
	public String invToBase64(Inventory inventory) {
		try {
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);
			
			// Write the size of the inventory
			dataOutput.writeInt(inventory.getSize());
			
			// Save every element in the list
			for (int i = 0; i < inventory.getSize(); i++) {
				dataOutput.writeObject(inventory.getItem(i));
			}
			
			// Serialize that array
			dataOutput.close();
			return Base64Coder.encodeLines(outputStream.toByteArray());
		} catch (Exception e) {
			throw new IllegalStateException("Unable to save item stacks.", e);
		}
	}
	
	@Override
	public Inventory invFromBase64(String data) {
		try {
			ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
			BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
			Inventory inventory = Bukkit.getServer().createInventory(null, dataInput.readInt());
			
			// Read the serialized inventory
			for (int i = 0; i < inventory.getSize(); i++) {
				inventory.setItem(i, (ItemStack) dataInput.readObject());
			}
			dataInput.close();
			return inventory;
		} catch (Exception e) {
			System.out.println("Unable to decode class type: " + e.getMessage());
		}
		return null;
	}
}
