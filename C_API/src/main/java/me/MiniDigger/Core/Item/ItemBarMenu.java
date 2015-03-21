package me.MiniDigger.Core.Item;

import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import me.MiniDigger.Core.User.User;


public interface ItemBarMenu extends Listener{
	
	
	interface ClickHandler{
		
		void click(ItemBarMenu m, ItemStack is, User u);
	}

	void setAction(int id, ClickHandler action);

	void setIcon(int id, ItemStack icon);

	void open(User u);

	void close(User u);
}
