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
package me.MiniDigger.Core.Cinematic.meta;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import me.MiniDigger.Core.Cinematic.Meta;
import me.MiniDigger.Core.Cinematic.MetaType;

/**
 * @author Mato Kormuth
 * 		
 */
public class MetaParticleEffect implements Meta {

	private final double	posX;
	private final double	posY;
	private final double	posZ;
	private final int		particle;

	/**
	 * @param posX
	 * @param posY
	 * @param posZ
	 * @param particle
	 */
	public MetaParticleEffect(final double posX, final double posY, final double posZ, final int particle) {
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

	public static MetaParticleEffect readMeta(final DataInputStream stream) throws IOException {
		final double x = stream.readDouble();
		final double y = stream.readDouble();
		final double z = stream.readDouble();
		final int particle = stream.readInt();

		return new MetaParticleEffect(x, y, z, particle);
	}

	@Override
	public MetaType getMetaType() {
		return MetaType.PARTICLE_EFFECT;
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
		return 0;
	}

	/**
	 * @return
	 */
	public float getOffsetY() {
		return 0;
	}

	/**
	 * @return
	 */
	public float getOffsetZ() {
		return 0;
	}

	/**
	 * @return
	 */
	public float getSpeed() {
		return 1;
	}

	/**
	 * @return
	 */
	public int getAmount() {
		return 20;
	}

}
