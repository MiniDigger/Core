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
package eu.matejkormuth.pexel.PexelCore.cinematics;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Reprezentuje zakladny nahravac
 * 
 * @author Mato Kormuth
 * 
 */
public class BasicRecorder {
	
	private Player	player;
	private boolean	recording;
	private long	frames	= 0;
	private int	    ID	   = 0;
	
	/**
	 * Pocet FPS v tomto nahravaci.
	 */
	public int	    FPS	   = 20;
	
	/**
	 * Vytvori instanciu nahravaca.
	 * 
	 * @param p
	 *            hrac, podla ktoreho nahrava
	 * @param fps
	 *            pocet obrazkov za sekundu
	 */
	public BasicRecorder(final Player p, final int fps) {
		player = p;
		FPS = fps;
	}
	
	/**
	 * Nastavi ID nahravaca.
	 * 
	 * @param id
	 *            id
	 */
	public void setID(final int id) {
		ID = id;
	}
	
	/**
	 * Zacne nahravat.
	 */
	public void record() {
		player.sendMessage("Recording " + ChatColor.RED + "[ID " + ID + "] " + ChatColor.YELLOW + " (" + FPS + "fps) " + ChatColor.GREEN + " has started...");
		
		if (FPS > 20) {
			player.sendMessage(ChatColor.RED + "Nahravanie na VIAC AKO 20 FPS nema vyznam! Vysledny zaznam bude rovnaky pri pouziti 20 aj 60 fps.");
		}
		
		recording = true;
		
		// Start recording thread.
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				BasicRecorder.this._record();
			}
		}).start();
	}
	
	/**
	 * Zastavi nahravanie.
	 */
	public void stop() {
		player.sendMessage("Stopped! Recorded " + frames + " frames (" + (frames / FPS) + " seconds)...");
		recording = false;
		player.sendMessage("Saved as: recording" + System.currentTimeMillis() + ".dat");
	}
	
	/**
	 * Interna metoda urcena na nahravanie.
	 */
	private void _record() {
		while (recording) {
			if ((frames % 100) == 0) {
				if (FPS > 20) {
					player.sendMessage(ChatColor.RED + "Nahravanie na VIAC AKO 20 FPS nema vyznam! Vysledny zaznam bude rovnaky pri pouziti 20 aj 60 fps.");
				}
				player.sendMessage("Recorded " + frames + " frames (" + (frames / FPS) + " seconds)");
			}
			frames++;
			
			if (frames > 30000) {
				// prekrocene maximum
				player.sendMessage("Zastavujem nahravanie. Prekrocene maximum frameov.");
				stop();
			}
			
			try {
				Thread.sleep(1000 / FPS);
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Vrati player.
	 * 
	 * @return player
	 */
	public Player getPlayer() {
		return player;
	}
	
	/**
	 * Nastavi hraca.
	 * 
	 * @param player
	 *            hrac
	 */
	public void setPlayer(final Player player) {
		this.player = player;
	}
}
