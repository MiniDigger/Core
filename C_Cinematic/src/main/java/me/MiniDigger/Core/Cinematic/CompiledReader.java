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

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.World;

import me.MiniDigger.Core.Cinematic.meta.MetaEntityDamage;
import me.MiniDigger.Core.Cinematic.meta.MetaEntityInventory;
import me.MiniDigger.Core.Cinematic.meta.MetaEntityRemove;
import me.MiniDigger.Core.Cinematic.meta.MetaEntitySpawn;
import me.MiniDigger.Core.Cinematic.meta.MetaEntityTeleport;
import me.MiniDigger.Core.Cinematic.meta.MetaEntityVelocity;
import me.MiniDigger.Core.Cinematic.meta.MetaFallingSand;
import me.MiniDigger.Core.Cinematic.meta.MetaParticleEffect;
import me.MiniDigger.Core.Cinematic.meta.MetaSoundEffect;

/**
 * Nacitava skompilovane V3 klipy.
 * 
 * @author Mato Kormuth
 * 
 */
public class CompiledReader {
	
	/**
	 * Stream pouzivany na nacitanie suboru.
	 */
	private final DataInputStream	input;
	/**
	 * Kuzelne cislo 1.
	 */
	public final static int	      MAGIC_1	= 86;
	/**
	 * Kuzelne cislo 2.
	 */
	public final static int	      MAGIC_2	= 114;
	/**
	 * Verzia suboru.
	 */
	public final static int	      VERSION	= 1;
	/**
	 * Klip.
	 */
	private CameraClip	          clip;
	/**
	 * Svet.
	 */
	private World	              w;
	
	public static CameraClip loadFile(final String path) throws Exception {
		return new CompiledReader(path).clip;
	}
	
	private CompiledReader(final String path) throws Exception {
		input = new DataInputStream(new FileInputStream(path));
		readFile();
	}
	
	/**
	 * Nacita data zo suboru do pamate.
	 * 
	 * @throws Exception
	 */
	private void readFile() throws Exception {
		clip = new CameraClip();
		
		readClip();
	}
	
	/**
	 * 
	 * @param clip
	 * @throws Exception
	 */
	private void readClip() throws Exception {
		// read FileHeader.
		readFileHeader();
		// read Frames.
		while (true) {
			try {
				final CameraFrame frame = readFrameHeader();
				clip.addFrame(frame);
			} catch (final EOFException exc) {
				break;
			}
		}
		close();
	}
	
	private void readFileHeader() throws Exception {
		// Check for magic.
		final byte magic1 = input.readByte();
		final byte magic2 = input.readByte();
		
		if (magic1 != CompiledReader.MAGIC_1 || magic2 != CompiledReader.MAGIC_2) {
			throw new Exception("This is not a valid V3C file!");
		}
		
		input.readByte(); // Version byte
		clip.FPS = input.readByte();
	}
	
	private CameraFrame readFrameHeader() throws IOException {
		// read FrameHeader.
		final double x = input.readDouble();
		final double y = input.readDouble();
		final double z = input.readDouble();
		final float yaw = input.readFloat();
		final float pitch = input.readFloat();
		final float zoom = input.readFloat();
		final boolean isMetaOnly = input.readBoolean();
		
		final CameraFrame frame = new CameraFrame(new Location(w, x, y, z, yaw, pitch), isMetaOnly).setZoom(zoom);
		
		// Process meta.
		final short metaCount = input.readShort(); // MetaCount
		
		for (short i = 0; i < metaCount; i++) {
			// Meta type
			final byte metaType = input.readByte();
			
			switch (metaType) {
			case 0:
				frame.addMeta(MetaSoundEffect.readMeta(input));
				break;
			case 1:
				frame.addMeta(MetaEntitySpawn.readMeta(input));
				break;
			case 2:
				frame.addMeta(MetaEntityDamage.readMeta(input));
				break;
			case 3:
				frame.addMeta(MetaEntityTeleport.readMeta(input));
				break;
			case 4:
				frame.addMeta(MetaEntityInventory.readMeta(input));
				break;
			case 5:
				frame.addMeta(MetaEntityRemove.readMeta(input));
				break;
			case 6:
				frame.addMeta(MetaEntityVelocity.readMeta(input));
				break;
			case 7:
				frame.addMeta(MetaParticleEffect.readMeta(input));
				break;
			case 8:
				frame.addMeta(MetaFallingSand.readMeta(input));
				break;
			}
		}
		
		return frame;
	}
	
	/**
	 * Zatvori subor.
	 * 
	 * @throws IOException
	 */
	public void close() throws IOException {
		input.close();
	}
}
