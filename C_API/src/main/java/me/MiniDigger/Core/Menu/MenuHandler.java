package me.MiniDigger.Core.Menu;

import java.util.UUID;

import me.MiniDigger.Core.User.User;


public interface MenuHandler {

	void load();

	ItemBarMenu getMenu(String name);

	void closeMenu(User u);

	void openMenu(User u, String name);

	ItemBarMenu getMenu(UUID id);
	
}
