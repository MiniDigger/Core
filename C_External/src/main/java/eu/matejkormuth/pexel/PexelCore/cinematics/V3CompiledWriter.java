// @formatter:off
/*
 * Pexel Project - Minecraft minigame server platform. 
 * Copyright (C) 2014 Matej Kormuth <http://www.matejkormuth.eu>
 * 
 * This file is part of Pexel.
 * 
 * Pexel is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * 
 * Pexel is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 *
 */
// @formatter:on
package eu.matejkormuth.pexel.PexelCore.cinematics;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.bukkit.Location;

/**
 * Pouziva sa na ukladanie kompilovanych suborov klipov V3.
 * 
 * @author Mato Kormuth
 * 
 */
public class V3CompiledWriter {
	
	/**
	 * Vystupny stream.
	 */
	private DataOutputStream	output;
	
	/**
	 * Kuzelne cislo 1.
	 */
	public final static int	 MAGIC_1	= 86;
	/**
	 * Kuzelne cislo 2.
	 */
	public final static int	 MAGIC_2	= 114;
	/**
	 * Verzia suboru.
	 */
	public final static int	 VERSION	= 1;
	
	public static V3CompiledWriter createFile(final String path) {
		return new V3CompiledWriter(path);
	}
	
	// Sukromny konstruktor.
	private V3CompiledWriter(final String path) {
		try {
			output = new DataOutputStream(new FileOutputStream(path));
		} catch (final FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Nezatvori subor!
	 * 
	 * @param clip
	 * @throws IOException
	 */
	public void writeClip(final V3CameraClip clip) throws IOException {
		checkForNull(clip);
		
		// Write FileHeader.
		writeFileHeader(clip.FPS);
		// Write Frames.
		for (final V3CameraFrame frame : clip.frames) {
			// Write FrameHeader.
			final Location l = frame.getCameraLocation();
			writeFrameHeader(l.getX(), l.getY(), l.getZ(), l.getYaw(), l.getPitch(), frame.getZoom(), frame.isMetaOnly());
			// Write FrameMeta (ExtraData).
			writeMetaHeader(frame.getMetaCount());
			
			if (frame.hasMeta()) {
				for (final V3Meta meta : frame.getMetas()) {
					// Write importatnt things.
					output.writeByte(meta.getType());
					
					// Write data metas's way.
					meta.writeMeta(output);
				}
			}
			
			// Proceed to next frame.
		}
	}
	
	/**
	 * @param clip
	 */
	private void checkForNull(final V3CameraClip clip) {
		
	}
	
	private void writeMetaHeader(final int metaCount) throws IOException {
		// Write Meta Header.
		output.writeShort(metaCount);
	}
	
	private void writeFileHeader(final int fps) throws IOException {
		// Write FileHeader.
		output.writeByte(V3CompiledWriter.MAGIC_1);
		output.writeByte(V3CompiledWriter.MAGIC_2);
		output.writeByte(V3CompiledWriter.VERSION);
		if (fps >= 255) {
			throw new IOException("FPS must be lower than 255!");
		} else {
			output.writeByte(fps);
		}
	}
	
	private void writeFrameHeader(final double camX, final double camY, final double camZ, final float yaw, final float pitch, final float zoom, final boolean isMeta)
	        throws IOException {
		// Write FrameHeader.
		output.writeDouble(camX);
		output.writeDouble(camY);
		output.writeDouble(camZ);
		output.writeFloat(yaw);
		output.writeFloat(pitch);
		output.writeFloat(zoom);
		output.writeBoolean(isMeta);
	}
	
	/**
	 * Zatvori subor.
	 * 
	 * @throws IOException
	 */
	public void close() throws IOException {
		output.close();
	}
}
