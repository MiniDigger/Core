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
 * @author Mato Kormuth
 * 
 */
public class V3MetaParticleEffect implements V3Meta {
	
	private final double	posX;
	private final double	posY;
	private final double	posZ;
	private final int	 particle;
	
	/**
	 * @param posX
	 * @param posY
	 * @param posZ
	 * @param particle
	 */
	public V3MetaParticleEffect(final double posX, final double posY, final double posZ, final int particle) {
		this.posX = posX;
		this.posY = posY;
		this.posZ = posZ;
		this.particle = particle;
	}
	
	@Override
	public void writeMeta(final DataOutputStream stream) throws IOException {
		stream.writeDouble(posX);
		stream.writeDouble(posY);
		stream.writeDouble(posZ);
		stream.writeInt(particle);
	}
	
	public static V3MetaParticleEffect readMeta(final DataInputStream stream) throws IOException {
		final double x = stream.readDouble();
		final double y = stream.readDouble();
		final double z = stream.readDouble();
		final int particle = stream.readInt();
		
		return new V3MetaParticleEffect(x, y, z, particle);
	}
	
	@Override
	public int getType() {
		return 7;
	}
	
	@Override
	public V3MetaType getMetaType() {
		return V3MetaType.V3MetaParticleEffect;
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
	 * @return the particle
	 */
	public int getParticle() {
		return particle;
	}
	
	/**
	 * @return
	 */
	public float getOffsetX() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * @return
	 */
	public float getOffsetY() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * @return
	 */
	public float getOffsetZ() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * @return
	 */
	public float getSpeed() {
		// TODO Auto-generated method stub
		return 1;
	}
	
	/**
	 * @return
	 */
	public int getAmount() {
		// TODO Auto-generated method stub
		return 20;
	}
	
}
