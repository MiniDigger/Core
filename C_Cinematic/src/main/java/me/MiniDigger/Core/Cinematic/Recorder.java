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

import me.MiniDigger.Core.Prefix.Prefix;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Nahravac tretej verzie.
 * 
 * @author Mato Kormuth
 * 
 */
public class Recorder {
	
	private Player	   player;
	private boolean	   recording;
	private CameraClip	clip;
	private long	   frames	= 0;
	private int	       ID	  = 0;
	private String	   name;
	
	/**
	 * Pocet FPS v tomto nahravaci.
	 */
	public int	       FPS	  = 20;
	
	public Recorder(final Player p, final int fps) {
		player = p;
		FPS = fps;
	}
	
	public Recorder(final Player p, final int fps, final String name) {
		this(p, fps);
		this.name = name;
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
		Prefix.CINE.getPrefix().then("Aufnahme " + ChatColor.RED + "[ID " + ID + "] " + ChatColor.YELLOW + " (" + FPS + "fps) " + ChatColor.GREEN + " hat gestartet...")
		        .send(player);
		
		if (FPS > 20) {
			Prefix.CINE.getPrefix().then("Eine Aufnahme mit mehr als 20 FPS is irelevant! Es gibt keinen Unterschied zwichen 20 und 60").color(ChatColor.RED)
			        .send(player);
		}
		
		recording = true;
		clip = new CameraClip();
		
		// Start recording thread.
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				Recorder.this._record();
			}
		}).start();
	}
	
	/**
	 * Zastavi nahravanie.
	 */
	public void stop() {
		
		Prefix.CINE.getPrefix().then("Aufnahme gestoppt! Es wurden " + frames + " Frames aufgenommen (" + (frames / FPS) + " Sekunden)").send(player);
		recording = false;
		
		if (name == null) {
			name = "cinematic_" + player.getName() + "_" + System.currentTimeMillis();
		}
		
		clip.save(name);
		Prefix.CINE.getPrefix().then("Cinematic wurde als " + name + ".cine gespeichert").send(player);
	}
	
	/**
	 * Interna metoda urcena na nahravanie.
	 */
	private void _record() {
		while (recording) {
			if ((frames % 100) == 0) {
				if (FPS > 20) {
					Prefix.CINE.getPrefix().then("Eine Aufnahme mit mehr als 20 FPS is irelevant! Es gibt keinen Unterschied zwichen 20 und 60").color(ChatColor.RED)
					        .send(player);
				}
				Prefix.CINE.getPrefix().then("Es wurden " + frames + " Frames aufgenommen (" + (frames / FPS) + " Sekunden)").send(player);
			}
			
			clip.addFrame(new CameraFrame(player.getEyeLocation(), false)); // was
			                                                                // player.getLocation()
			frames++;
			
			if (frames > 30000) {
				Prefix.CINE.getPrefix().then("Aufnahme gestoppen! Framelimit überschritten!").color(ChatColor.RED).send(player);
				stop();
			}
			
			try {
				Thread.sleep(1000 / FPS);
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void setPlayer(final Player player) {
		this.player = player;
	}
	
	public CameraClip getClip() {
		return clip;
	}
}
