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
package me.MiniDigger.Core.Cinematic;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Nahravac tretej verzie.
 * 
 * @author Mato Kormuth
 * 
 */
public class BasicRecorder{
	
	private Player	     player;
	private boolean	     recording;
	private CameraClip	clip;
	private long	     frames	= 0;
	private int	         ID	    = 0;
	
	/**
	 * Pocet FPS v tomto nahravaci.
	 */
	public int	         FPS	= 20;
	
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
	@SuppressWarnings("deprecation")
	public void record() {
		if (player == null) {
			System.out.println("cmon");
			player = Bukkit.getServer().getOnlinePlayers().clone()[0];
		}
		player.sendMessage("Recording " + ChatColor.RED + "[ID " + ID + "] " + ChatColor.YELLOW + " (" + FPS + "fps) " + ChatColor.GREEN + " has started...");
		
		if (FPS > 20) {
			player.sendMessage(ChatColor.RED + "Nahravanie na VIAC AKO 20 FPS nema vyznam! Vysledny zaznam bude rovnaky pri pouziti 20 aj 60 fps.");
		}
		
		recording = true;
		clip = new CameraClip();
		
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
		clip.save("advrecording" + System.currentTimeMillis());
		player.sendMessage("Saved as: advrecording" + System.currentTimeMillis() + ".dat");
		// clip = null;
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
			
			// Pridaj frame.
			clip.addFrame(new CameraFrame(player.getEyeLocation(), false)); // was
			                                                                  // player.getLocation()
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
	
	public CameraClip getClip() {
		return clip;
	}
}
