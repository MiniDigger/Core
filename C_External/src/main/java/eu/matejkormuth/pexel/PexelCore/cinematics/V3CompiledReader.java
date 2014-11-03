// @formatter:off
/*******************************************************************************
 * Test
 *******************************************************************************/
// @formatter:on
package eu.matejkormuth.pexel.PexelCore.cinematics;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.World;

import eu.matejkormuth.pexel.PexelCore.cinematics.v3meta.V3MetaEntityDamage;
import eu.matejkormuth.pexel.PexelCore.cinematics.v3meta.V3MetaEntityInventory;
import eu.matejkormuth.pexel.PexelCore.cinematics.v3meta.V3MetaEntityRemove;
import eu.matejkormuth.pexel.PexelCore.cinematics.v3meta.V3MetaEntitySpawn;
import eu.matejkormuth.pexel.PexelCore.cinematics.v3meta.V3MetaEntityTeleport;
import eu.matejkormuth.pexel.PexelCore.cinematics.v3meta.V3MetaEntityVelocity;
import eu.matejkormuth.pexel.PexelCore.cinematics.v3meta.V3MetaFallingSand;
import eu.matejkormuth.pexel.PexelCore.cinematics.v3meta.V3MetaParticleEffect;
import eu.matejkormuth.pexel.PexelCore.cinematics.v3meta.V3MetaSoundEffect;

/**
 * Nacitava skompilovane V3 klipy.
 * 
 * @author Mato Kormuth
 * 
 */
public class V3CompiledReader {
	
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
	private V3CameraClip	      clip;
	/**
	 * Svet.
	 */
	private World	              w;
	
	public static V3CameraClip loadFile(final String path) throws Exception {
		return new V3CompiledReader(path).clip;
	}
	
	private V3CompiledReader(final String path) throws Exception {
		input = new DataInputStream(new FileInputStream(path));
		readFile();
	}
	
	/**
	 * Nacita data zo suboru do pamate.
	 * 
	 * @throws Exception
	 */
	private void readFile() throws Exception {
		clip = new V3CameraClip();
		
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
				final V3CameraFrame frame = readFrameHeader();
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
		
		if (magic1 != V3CompiledReader.MAGIC_1 || magic2 != V3CompiledReader.MAGIC_2) {
			throw new Exception("This is not a valid V3C file!");
		}
		
		input.readByte(); // Version byte
		clip.FPS = input.readByte();
	}
	
	private V3CameraFrame readFrameHeader() throws IOException {
		// read FrameHeader.
		final double x = input.readDouble();
		final double y = input.readDouble();
		final double z = input.readDouble();
		final float yaw = input.readFloat();
		final float pitch = input.readFloat();
		final float zoom = input.readFloat();
		final boolean isMetaOnly = input.readBoolean();
		
		final V3CameraFrame frame = new V3CameraFrame(new Location(w, x, y, z, yaw, pitch), isMetaOnly).setZoom(zoom);
		
		// Process meta.
		final short metaCount = input.readShort(); // MetaCount
		
		for (short i = 0; i < metaCount; i++) {
			// Meta type
			final byte metaType = input.readByte();
			
			switch (metaType) {
			case 0:
				frame.addMeta(V3MetaSoundEffect.readMeta(input));
				break;
			case 1:
				frame.addMeta(V3MetaEntitySpawn.readMeta(input));
				break;
			case 2:
				frame.addMeta(V3MetaEntityDamage.readMeta(input));
				break;
			case 3:
				frame.addMeta(V3MetaEntityTeleport.readMeta(input));
				break;
			case 4:
				frame.addMeta(V3MetaEntityInventory.readMeta(input));
				break;
			case 5:
				frame.addMeta(V3MetaEntityRemove.readMeta(input));
				break;
			case 6:
				frame.addMeta(V3MetaEntityVelocity.readMeta(input));
				break;
			case 7:
				frame.addMeta(V3MetaParticleEffect.readMeta(input));
				break;
			case 8:
				frame.addMeta(V3MetaFallingSand.readMeta(input));
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
