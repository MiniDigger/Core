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
package eu.matejkormuth.pexel.PexelCore.cinematics.v3meta;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import eu.matejkormuth.pexel.PexelCore.cinematics.V3Meta;
import eu.matejkormuth.pexel.PexelCore.cinematics.V3MetaType;

/**
 * @author M
 * 
 */
public class V3MetaSoundEffect implements V3Meta {
	
	private static final int	TYPEID	= 0;
	
	private final double	 posX;
	private final double	 posY;
	private final double	 posZ;
	private final float	     pitch;
	private final float	     volume;
	private final String	 name;
	
	/**
	 * Vytvori novy V3MetaSoundEffect so specifikovanymi udajmi.
	 * 
	 * @param posX
	 * @param posY
	 * @param posZ
	 * @param pitch
	 * @param volume
	 * @param name
	 */
	public V3MetaSoundEffect(final double posX, final double posY, final double posZ, final float pitch, final float volume, final String name) {
		this.posX = posX;
		this.posY = posY;
		this.posZ = posZ;
		this.pitch = pitch;
		this.volume = volume;
		this.name = name;
	}
	
	@Override
	public void writeMeta(final DataOutputStream stream) throws IOException {
		stream.writeDouble(posX);
		stream.writeDouble(posY);
		stream.writeDouble(posZ);
		stream.writeFloat(volume);
		stream.writeFloat(pitch);
		stream.writeUTF(name);
	}
	
	public static V3MetaSoundEffect readMeta(final DataInputStream stream) throws IOException {
		final double x = stream.readDouble();
		final double y = stream.readDouble();
		final double z = stream.readDouble();
		final float volume = stream.readFloat();
		final float pitch = stream.readFloat();
		final String name = stream.readUTF();
		
		return new V3MetaSoundEffect(x, y, z, pitch, volume, name);
	}
	
	@Override
	public int getType() {
		return V3MetaSoundEffect.TYPEID;
	}
	
	@Override
	public V3MetaType getMetaType() {
		return V3MetaType.V3MetaSoundEffect;
	}
	
	/**
	 * @return the posX
	 */
	public double getPosX() {
		return posX;
	}
	
	/**
	 * @return the posY
	 */
	public double getPosY() {
		return posY;
	}
	
	/**
	 * @return the posZ
	 */
	public double getPosZ() {
		return posZ;
	}
	
	/**
	 * @return the pitch
	 */
	public float getPitch() {
		return pitch;
	}
	
	/**
	 * @return the volume
	 */
	public float getVolume() {
		return volume;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
}
