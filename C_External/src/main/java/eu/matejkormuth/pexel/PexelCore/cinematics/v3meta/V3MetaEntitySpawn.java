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
package eu.matejkormuth.pexel.PexelCore.cinematics.v3meta;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.bukkit.entity.EntityType;

import eu.matejkormuth.pexel.PexelCore.cinematics.V3Meta;
import eu.matejkormuth.pexel.PexelCore.cinematics.V3MetaType;

/**
 * 
 * @author Mato Kormuth
 * 
 */
public class V3MetaEntitySpawn implements V3Meta {
	
	private static final int	TYPEID	= 1;
	
	private final double	 posX;
	private final double	 posY;
	private final double	 posZ;
	private final float	     yaw;
	private final float	     pitch;
	private final EntityType	entityType;
	private final long	     internalId;
	
	/**
	 * Vytvori novy V3MetaEntitySpawn so specifikovanymi udajmi.
	 * 
	 * @param posX
	 * @param posY
	 * @param posZ
	 * @param yaw
	 * @param pitch
	 * @param type
	 * @param internalId
	 */
	public V3MetaEntitySpawn(final double posX, final double posY, final double posZ, final float yaw, final float pitch, final EntityType type, final long internalId) {
		this.posX = posX;
		this.posY = posY;
		this.posZ = posZ;
		this.yaw = yaw;
		this.pitch = pitch;
		entityType = type;
		this.internalId = internalId;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void writeMeta(final DataOutputStream stream) throws IOException {
		stream.writeDouble(posX);
		stream.writeDouble(posY);
		stream.writeDouble(posZ);
		stream.writeFloat(yaw);
		stream.writeFloat(pitch);
		stream.writeInt(entityType.getTypeId());
		stream.writeLong(internalId);
	}
	
	@SuppressWarnings("deprecation")
	public static V3MetaEntitySpawn readMeta(final DataInputStream stream) throws IOException {
		final double x = stream.readDouble();
		final double y = stream.readDouble();
		final double z = stream.readDouble();
		final float yaw = stream.readFloat();
		final float pitch = stream.readFloat();
		final EntityType type = EntityType.fromId(stream.readInt());
		final long internalId = stream.readLong();
		
		return new V3MetaEntitySpawn(x, y, z, yaw, pitch, type, internalId);
	}
	
	@Override
	public int getType() {
		return V3MetaEntitySpawn.TYPEID;
	}
	
	@Override
	public V3MetaType getMetaType() {
		return V3MetaType.V3MetaEntitySpawn;
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
	 * @return the yaw
	 */
	public float getYaw() {
		return yaw;
	}
	
	/**
	 * @return the pitch
	 */
	public float getPitch() {
		return pitch;
	}
	
	/**
	 * @return the internalId
	 */
	public long getInternalId() {
		return internalId;
	}
	
	/**
	 * @return the entityType
	 */
	public EntityType getEntityType() {
		return entityType;
	}
	
}
