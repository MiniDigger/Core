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
 * Written by Martin Benndorf <admin@minidigger.me>, 2013-2015 and others
 */
package me.MiniDigger.Core.Cinematic;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.util.Vector;

/**
 * Pomocna trieda na generovanie rznych typov pohybov.
 * 
 * @author Mato Kormuth
 * 
 */
public class Generator {
	
	// Mala staticka classa.
	private Generator() {
		
	}
	
	/**
	 * Vygeneruje ranmce pre rovnomerne rychlu cestu z pos1 do pos2 so
	 * specifikovanym pitch a yaw pocas specifikovaneho casu v sekundach a
	 * specifikovaneho poctu ramcov za sekundu.
	 * 
	 * @param pos1
	 * @param pos2
	 * @param fps
	 * @param seconds
	 * @param yaw
	 * @param pitch
	 * @return
	 */
	public static List<CameraFrame> line(final Vector pos1, final Vector pos2, final int fps, final int seconds, final float yaw, final float pitch) {
		final List<CameraFrame> frameList = new ArrayList<CameraFrame>();
		final int frameCount = fps * seconds;
		// Vypocitaj vzdialenosti.
		final Vector positionDifference = pos2.subtract(pos1);
		// Zisti posunutia (prirastky).
		final Vector frameChange = new Vector(positionDifference.getX() / frameCount, positionDifference.getY() / frameCount, positionDifference.getZ() / frameCount);
		
		for (int frameNum = 0; frameNum < frameCount; frameNum++) {
			// Zvacsi pos1 o prirastok.
			pos1.add(frameChange);
			// Pridaj ramec.
			frameList.add(new CameraFrame(pos1.getX(), pos1.getY(), pos1.getZ(), yaw, pitch, false));
		}
		return frameList;
	}
	
	public static List<CameraFrame> lineLookAt(final Vector pos1, final Vector pos2, final Vector lookAt, final int fps, final int seconds) {
		final List<CameraFrame> frameList = new ArrayList<CameraFrame>();
		final int frameCount = fps * seconds;
		// Vypocitaj vzdialenosti.
		final Vector positionDifference = pos2.subtract(pos1);
		// Zisti posunutia (prirastky).
		final Vector frameChange = new Vector(positionDifference.getX() / frameCount, positionDifference.getY() / frameCount, positionDifference.getZ() / frameCount);
		
		for (int frameNum = 0; frameNum < frameCount; frameNum++) {
			// Zvacsi pos1 o prirastok.
			pos1.add(frameChange);
			final float pitch = 0;
			final float yaw = (float) -((Math.atan2(pos1.getX() - lookAt.getX(), pos1.getZ() - lookAt.getZ()) * 180 / Math.PI) - 180);
			// Pridaj ramec.
			frameList.add(new CameraFrame(pos1.getX(), pos1.getY(), pos1.getZ(), yaw, pitch, false));
		}
		return frameList;
	}
	
	/**
	 * Vygeneruje ramce pre pohyb po kruznici a lookAt specifikovany.
	 * 
	 * @param center
	 * @param lookAt
	 * @param radius
	 * @param fps
	 * @param seconds
	 * @param speed
	 * @return
	 */
	public static List<CameraFrame> flyInCirleLookAt(final Vector center, final Vector lookAt, final int radius, final int fps, final int seconds, final float speed) {
		final List<CameraFrame> frameList = new ArrayList<CameraFrame>();
		final int frameCount = fps * seconds;
		for (int frameNum = 0; frameNum < frameCount; frameNum++) {
			// Vypocitaj X a Z, Y zostava rovnake.
			final double x = center.getX() + Math.sin(frameNum * speed) * radius;
			final double z = center.getZ() + Math.cos(frameNum * speed) * radius;
			// Vypocitaj pitch a yaw.
			final float pitch = 0;
			final float yaw = (float) -((Math.atan2(x - lookAt.getX(), z - lookAt.getZ()) * 180 / Math.PI) - 180);
			// Pridaj ramec.
			frameList.add(new CameraFrame(x, center.getY(), z, yaw, pitch, false));
		}
		return frameList;
	}
	
	public static List<CameraFrame> splinePath(final List<Vector> points) {
		final List<CameraFrame> frameList = new ArrayList<CameraFrame>();
		
		return frameList;
	}
}
