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

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Nahravac tretej verzie.
 * 
 * @author Mato Kormuth
 * 
 */
public class V3BasicRecorder extends BasicRecorder {
	
	private Player	     player;
	private boolean	     recording;
	private V3CameraClip	clip;
	private long	     frames	= 0;
	private int	         ID	    = 0;
	
	/**
	 * Pocet FPS v tomto nahravaci.
	 */
	public int	         FPS	= 20;
	
	public V3BasicRecorder(final Player p, final int fps) {
		super(p, fps);
	}
	
	/**
	 * Nastavi ID nahravaca.
	 * 
	 * @param id
	 *            id
	 */
	@Override
	public void setID(final int id) {
		ID = id;
	}
	
	/**
	 * Zacne nahravat.
	 */
	@SuppressWarnings("deprecation")
	@Override
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
		clip = new V3CameraClip();
		
		// Start recording thread.
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				V3BasicRecorder.this._record();
			}
		}).start();
	}
	
	/**
	 * Zastavi nahravanie.
	 */
	@Override
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
			clip.addFrame(new V3CameraFrame(player.getEyeLocation(), false)); // was
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
	@Override
	public Player getPlayer() {
		return player;
	}
	
	/**
	 * Nastavi hraca.
	 * 
	 * @param player
	 *            hrac
	 */
	@Override
	public void setPlayer(final Player player) {
		this.player = player;
	}
	
	public V3CameraClip getClip() {
		return clip;
	}
}
