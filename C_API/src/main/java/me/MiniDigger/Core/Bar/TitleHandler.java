package me.MiniDigger.Core.Bar;

import org.bukkit.entity.Player;


public interface TitleHandler {

	void sendTitle(Player p, String title);

	void sendTitle(Player p, int fadeIn, int stay, int fadeOut, String title);

	void sendSubTitle(Player p, String subtitle);

	void sendSubTitle(Player p, int fadeIn, int stay, int fadeOut, String subtitle);

	void sendTimings(Player p, int fadeIn, int stay, int fadeOut);

	void reset(Player p);

	void clear(Player p);

	int getVersion(Player p);
	
}