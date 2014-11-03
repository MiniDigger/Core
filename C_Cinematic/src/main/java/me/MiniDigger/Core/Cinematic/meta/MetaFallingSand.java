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
package me.MiniDigger.Core.Cinematic.meta;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import me.MiniDigger.Core.Cinematic.Meta;
import me.MiniDigger.Core.Cinematic.MetaType;

import org.bukkit.Material;

/**
 * @author Mato Kormuth
 * 
 */
public class MetaFallingSand implements Meta {
	
	private final double	posX;
	private final double	posY;
	private final double	posZ;
	private final double	velX;
	private final double	velY;
	private final double	velZ;
	private final Material	material;
	
	/**
	 * @param posX
	 * @param posY
	 * @param posZ
	 * @param velX
	 * @param velY
	 * @param velZ
	 * @param material
	 */
	public MetaFallingSand(final double posX, final double posY, final double posZ, final double velX, final double velY, final double velZ, final Material material) {
		this.posX = posX;
		this.posY = posY;
		this.posZ = posZ;
		this.velX = velX;
		this.velY = velY;
		this.velZ = velZ;
		this.material = material;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void writeMeta(final DataOutputStream stream) throws IOException {
		stream.writeDouble(posX);
		stream.writeDouble(posY);
		stream.writeDouble(posZ);
		stream.writeDouble(velX);
		stream.writeDouble(velY);
		stream.writeDouble(velZ);
		stream.writeInt(material.getId());
	}
	
	@SuppressWarnings("deprecation")
	public static MetaFallingSand readMeta(final DataInputStream stream) throws IOException {
		final double posX = stream.readDouble();
		final double posY = stream.readDouble();
		final double posZ = stream.readDouble();
		final double velX = stream.readDouble();
		final double velY = stream.readDouble();
		final double velZ = stream.readDouble();
		final Material material = Material.getMaterial(stream.readInt());
		
		return new MetaFallingSand(posX, posY, posZ, velX, velY, velZ, material);
	}
	
	@Override
	public int getType() {
		return 8;
	}
	
	@Override
	public MetaType getMetaType() {
		return MetaType.ENTITY_FALLING_SAND;
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
	 * @return the velX
	 */
	public double getVelX() {
		return velX;
	}
	
	/**
	 * @return the velY
	 */
	public double getVelY() {
		return velY;
	}
	
	/**
	 * @return the velZ
	 */
	public double getVelZ() {
		return velZ;
	}
	
	/**
	 * @return the material
	 */
	public Material getMaterial() {
		return material;
	}
	
}
