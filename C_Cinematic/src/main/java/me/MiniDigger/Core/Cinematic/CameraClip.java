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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.MiniDigger.Core.Core;

import org.bukkit.entity.Entity;
import org.bukkit.plugin.Plugin;

/**
 * Zlozity klip 3 urovne.
 * 
 * @author Mato Kormuth
 * 
 */
public class CameraClip {
	
	List<CameraFrame>	frames	  = Collections.synchronizedList(new ArrayList<CameraFrame>());
	List<Entity>	    entites	  = Collections.synchronizedList(new ArrayList<Entity>());
	
	/**
	 * Pocet ramov za sekundu v tomto klipe.
	 */
	public int	        FPS	      = 20;
	public int	        verzia	  = 3;
	public int	        metaCount	= 0;
	
	/**
	 * Prida jeden frame do zoznamu frameov.
	 * 
	 * @param frame
	 */
	public void addFrame(final CameraFrame frame) {
		frame.clip = this;
		frames.add(frame);
		metaCount += frame.getMetaCount();
	}
	
	/**
	 * Prida kolekciu frameov do zoznamu frameov.
	 * 
	 * @param frames
	 */
	public void addFrames(final List<CameraFrame> frames) {
		this.frames.addAll(frames);
	}
	
	/**
	 * Vrati vsetky framy v klipe.
	 * 
	 * @return
	 */
	public List<CameraFrame> getFrames() {
		return frames;
	}
	
	/**
	 * Vrati frame specifikovany indexom.
	 * 
	 * @param index
	 * @return
	 */
	public CameraFrame getFrame(final int index) {
		return frames.get(index);
	}
	
	/**
	 * Ulozi klip do suboru.
	 * 
	 * @param meno
	 *            suboru.
	 */
	public void save(final String name) {
		// PrintWriter writer = null;
		// try {
		// writer = new PrintWriter(new File(((Plugin)
		// Core.getCore().getInstance()).getDataFolder(), name + ".dat"));
		// writer.println("#mertex-fun | CameraClip | v 1.2.1");
		// writer.println("#{fsavetime=" + System.currentTimeMillis() +
		// ",fcount=" + frames.size() + ",ver=1}");
		// writer.println("#{FPS=" + FPS + "}");
		// writer.println("#{VERSION3}");
		//
		// for (final V3CameraFrame cframe : frames) {
		// writer.println(cframe.serialize());
		// }
		// writer.close();
		// } catch (final FileNotFoundException e) {
		// e.printStackTrace();
		// }
		try {
			CompiledWriter.createFile(new File(((Plugin) Core.getCore().getInstance()).getDataFolder(), name + ".dat").getAbsolutePath()).writeClip(this);
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Nacita klip zo suboru.
	 * 
	 * @param meno
	 *            suboru
	 * @return klip.
	 */
	public static CameraClip load(final String name) {
		System.out.println("Nacitavam subor " + name);
		final CameraClip clip = new CameraClip();
		clip.verzia = 3;
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(new File(((Plugin) Core.getCore().getInstance()).getDataFolder(), name + ".dat")));
			String line;
			while ((line = reader.readLine()) != null) {
				// Poznamky sa nesnaz nacitat ako bloky.
				if (!line.startsWith("#")) {
					// Pridaj blok.
					try {
						clip.addFrame(new CameraFrame(line));
					} catch (final Exception e) {
						e.printStackTrace();
					}
				} else {
					// Poznamka alebo specialne metadata?
					if (line.startsWith("#{")) {
						if (line.startsWith("#{FPS=")) {
							clip.FPS = Integer.parseInt(line.substring(6, line.indexOf("}")));
						}
						
						if (line.startsWith("#{VERSION3")) {
							clip.verzia = 3;
						}
						// Metadata!
						// Fajn, aj tak ich zatial nevyuzivame...
					}
				}
				
			}
			reader.close();
		} catch (final FileNotFoundException e) {
			e.printStackTrace();
		} catch (final IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (final IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Nacitanych " + clip.getFrames().size() + " frameov.");
		return clip;
	}
	
	/**
	 * Vrati pocet ramov v klipe.
	 * 
	 * @return
	 */
	public int getNumOfFrames() {
		return frames.size();
	}
}
