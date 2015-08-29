/**
 * █████████████████████████████████████████████████████████████████████████████
 * ████████████████████████████████████
 * █░░░░░░░░░░░░░░█░░░░░░██░░░░░░█░░░░░░░░░░░░░░████░░░░░░░░░░░░░░█░░░░░░░░░░░░░
 * ░█░░░░░░░░░░░░░░░░███░░░░░░░░░░░░░░█
 * █░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░████░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░
 * ░█░░▄▀▄▀▄▀▄▀▄▀▄▀░░███░░▄▀▄▀▄▀▄▀▄▀░░█
 * █░░░░░░▄▀░░░░░░█░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░░░░░████░░▄▀░░░░░░░░░░█░░▄▀░░░░░░▄▀░
 * ░█░░▄▀░░░░░░░░▄▀░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░████████████░░▄▀░░█████████░░▄▀░░██░░▄▀░
 * ░█░░▄▀░░████░░▄▀░░███░░▄▀░░█████████
 * █████░░▄▀░░█████░░▄▀░░░░░░▄▀░░█░░▄▀░░░░░░░░░░████░░▄▀░░█████████░░▄▀░░██░░▄▀░
 * ░█░░▄▀░░░░░░░░▄▀░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░████░░▄▀░░█████████░░▄▀░░██░░▄▀░
 * ░█░░▄▀▄▀▄▀▄▀▄▀▄▀░░███░░▄▀▄▀▄▀▄▀▄▀░░█
 * █████░░▄▀░░█████░░▄▀░░░░░░▄▀░░█░░▄▀░░░░░░░░░░████░░▄▀░░█████████░░▄▀░░██░░▄▀░
 * ░█░░▄▀░░░░░░▄▀░░░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░████████████░░▄▀░░█████████░░▄▀░░██░░▄▀░
 * ░█░░▄▀░░██░░▄▀░░█████░░▄▀░░█████████
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░░░░░████░░▄▀░░░░░░░░░░█░░▄▀░░░░░░▄▀░
 * ░█░░▄▀░░██░░▄▀░░░░░░█░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░████░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░
 * ░█░░▄▀░░██░░▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█
 * █████░░░░░░█████░░░░░░██░░░░░░█░░░░░░░░░░░░░░████░░░░░░░░░░░░░░█░░░░░░░░░░░░░
 * ░█░░░░░░██░░░░░░░░░░█░░░░░░░░░░░░░░█
 * █████████████████████████████████████████████████████████████████████████████
 * ████████████████████████████████████
 *
 * Copyright © MiniDigger and others - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Martin Benndorf <admin@minidigger.me>, 2013-2015 and others
 */
package me.MiniDigger.CraftCore.Util;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.v1_8_R3.TrigMath;
import org.bukkit.craftbukkit.v1_8_R3.util.LongHash;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Util.MathUtil;

/**
 * Multiple Math utilities to compare and calculate using Vectors and raw values
 */

public class CoreMathUtil implements MathUtil {

	private final int	CHUNK_BITS		= 4;
	private final int	CHUNK_VALUES	= 16;
	public final float	DEGTORAD		= 0.017453293F;
	public final float	RADTODEG		= 57.29577951F;
	public final double	HALFROOTOFTWO	= 0.707106781;

	@Override
	public double lengthSquared(final double... values) {
		double rval = 0;
		for (final double value : values) {
			rval += value * value;
		}
		return rval;
	}

	@Override
	public double length(final double... values) {
		return Math.sqrt(lengthSquared(values));
	}

	@Override
	public double distance(final double x1, final double y1, final double x2, final double y2) {
		return length(x1 - x2, y1 - y2);
	}

	@Override
	public double distanceSquared(final double x1, final double y1, final double x2, final double y2) {
		return lengthSquared(x1 - x2, y1 - y2);
	}

	@Override
	public double distance(final double x1, final double y1, final double z1, final double x2, final double y2, final double z2) {
		return length(x1 - x2, y1 - y2, z1 - z2);
	}

	@Override
	public double distanceSquared(final double x1, final double y1, final double z1, final double x2, final double y2, final double z2) {
		return lengthSquared(x1 - x2, y1 - y2, z1 - z2);
	}

	@Override
	public double getPercentage(final int subtotal, final int total, final int decimals) {
		return round(getPercentage(subtotal, total), decimals);
	}

	@Override
	public double getPercentage(final int subtotal, final int total) {
		return ((float) subtotal / (float) total) * 100;
	}

	@Override
	public int getAngleDifference(final int angle1, final int angle2) {
		return Math.abs(wrapAngle(angle1 - angle2));
	}

	@Override
	public float getAngleDifference(final float angle1, final float angle2) {
		return Math.abs(wrapAngle(angle1 - angle2));
	}

	@Override
	public int wrapAngle(final int angle) {
		int wrappedAngle = angle;
		while (wrappedAngle <= -180) {
			wrappedAngle += 360;
		}
		while (wrappedAngle > 180) {
			wrappedAngle -= 360;
		}
		return wrappedAngle;
	}

	@Override
	public float wrapAngle(final float angle) {
		float wrappedAngle = angle;
		while (wrappedAngle <= -180f) {
			wrappedAngle += 360f;
		}
		while (wrappedAngle > 180f) {
			wrappedAngle -= 360f;
		}
		return wrappedAngle;
	}

	@Override
	public double normalize(final double x, final double z, final double reqx, final double reqz) {
		return Math.sqrt(lengthSquared(reqx, reqz) / lengthSquared(x, z));
	}

	@Override
	public float getLookAtYaw(final Entity loc, final Entity lookat) {
		return getLookAtYaw(loc.getLocation(), lookat.getLocation());
	}

	@Override
	public float getLookAtYaw(final Block loc, final Block lookat) {
		return getLookAtYaw(loc.getLocation(), lookat.getLocation());
	}

	@Override
	public float getLookAtYaw(final Location loc, final Location lookat) {
		return getLookAtYaw(lookat.getX() - loc.getX(), lookat.getZ() - loc.getZ());
	}

	@Override
	public float getLookAtYaw(final Vector motion) {
		return getLookAtYaw(motion.getX(), motion.getZ());
	}

	@Override
	public float getLookAtYaw(final double dx, final double dz) {
		return atan2(dz, dx) - 180f;
	}

	@Override
	public float getLookAtPitch(final double dX, final double dY, final double dZ) {
		return getLookAtPitch(dY, length(dX, dZ));
	}

	@Override
	public float getLookAtPitch(final double dY, final double dXZ) {
		return -atan(dY / dXZ);
	}

	@Override
	public float atan(final double value) {
		return RADTODEG * (float) TrigMath.atan(value);
	}

	@Override
	public float atan2(final double y, final double x) {
		return RADTODEG * (float) TrigMath.atan2(y, x);
	}

	@Override
	public int floor(final double value) {
		final int i = (int) value;
		return value < i ? i - 1 : i;
	}

	@Override
	public int floor(final float value) {
		final int i = (int) value;
		return value < i ? i - 1 : i;
	}

	@Override
	public int ceil(final double value) {
		return -floor(-value);
	}

	@Override
	public int ceil(final float value) {
		return -floor(-value);
	}

	@Override
	public Location move(final Location loc, final Vector offset) {
		return move(loc, offset.getX(), offset.getY(), offset.getZ());
	}

	@Override
	public Location move(final Location loc, final double dx, final double dy, final double dz) {
		final Vector off = rotate(loc.getYaw(), loc.getPitch(), dx, dy, dz);
		final double x = loc.getX() + off.getX();
		final double y = loc.getY() + off.getY();
		final double z = loc.getZ() + off.getZ();
		return new Location(loc.getWorld(), x, y, z, loc.getYaw(), loc.getPitch());
	}

	@Override
	public Vector rotate(final float yaw, final float pitch, final Vector vector) {
		return rotate(yaw, pitch, vector.getX(), vector.getY(), vector.getZ());
	}

	@Override
	public Vector rotate(final float yaw, final float pitch, final double x, final double y, final double z) {
		// Conversions found by (a lot of) testing
		float angle;
		angle = yaw * DEGTORAD;
		final double sinyaw = Math.sin(angle);
		final double cosyaw = Math.cos(angle);

		angle = pitch * DEGTORAD;
		final double sinpitch = Math.sin(angle);
		final double cospitch = Math.cos(angle);

		final Vector vector = new Vector();
		vector.setX((x * sinyaw) - (y * cosyaw * sinpitch) - (z * cosyaw * cospitch));
		vector.setY((y * cospitch) - (z * sinpitch));
		vector.setZ(-(x * cosyaw) - (y * sinyaw * sinpitch) - (z * sinyaw * cospitch));
		return vector;
	}

	@Override
	public double round(final double value, final int decimals) {
		final double p = Math.pow(10, decimals);
		return Math.round(value * p) / p;
	}

	@Override
	public double fixNaN(final double value) {
		return fixNaN(value, 0.0);
	}

	@Override
	public double fixNaN(final double value, final double def) {
		return Double.isNaN(value) ? def : value;
	}

	@Override
	public int toChunk(final double loc) {
		return floor(loc / CHUNK_VALUES);
	}

	@Override
	public int toChunk(final int loc) {
		return loc >> CHUNK_BITS;
	}

	@Override
	public double useOld(final double oldvalue, final double newvalue, final double peruseold) {
		return oldvalue + (peruseold * (newvalue - oldvalue));
	}

	@Override
	public double lerp(final double d1, final double d2, final double stage) {
		if (Double.isNaN(stage) || stage > 1) {
			return d2;
		} else if (stage < 0) {
			return d1;
		} else {
			return d1 * (1 - stage) + d2 * stage;
		}
	}

	@Override
	public Vector lerp(final Vector vec1, final Vector vec2, final double stage) {
		final Vector newvec = new Vector();
		newvec.setX(lerp(vec1.getX(), vec2.getX(), stage));
		newvec.setY(lerp(vec1.getY(), vec2.getY(), stage));
		newvec.setZ(lerp(vec1.getZ(), vec2.getZ(), stage));
		return newvec;
	}

	@Override
	public Location lerp(final Location loc1, final Location loc2, final double stage) {
		final Location newloc = new Location(loc1.getWorld(), 0, 0, 0);
		newloc.setX(lerp(loc1.getX(), loc2.getX(), stage));
		newloc.setY(lerp(loc1.getY(), loc2.getY(), stage));
		newloc.setZ(lerp(loc1.getZ(), loc2.getZ(), stage));
		newloc.setYaw((float) lerp(loc1.getYaw(), loc2.getYaw(), stage));
		newloc.setPitch((float) lerp(loc1.getPitch(), loc2.getPitch(), stage));
		return newloc;
	}

	@Override
	public boolean isInverted(final double value1, final double value2) {
		return (value1 > 0 && value2 < 0) || (value1 < 0 && value2 > 0);
	}

	@Override
	public Vector getDirection(final float yaw, final float pitch) {
		final Vector vector = new Vector();
		final double rotX = DEGTORAD * yaw;
		final double rotY = DEGTORAD * pitch;
		vector.setY(-Math.sin(rotY));
		final double h = Math.cos(rotY);
		vector.setX(-h * Math.sin(rotX));
		vector.setZ(h * Math.cos(rotX));
		return vector;
	}

	@Override
	public double clamp(final double value, final double limit) {
		return clamp(value, -limit, limit);
	}

	@Override
	public double clamp(final double value, final double min, final double max) {
		return value < min ? min : (value > max ? max : value);
	}

	@Override
	public float clamp(final float value, final float limit) {
		return clamp(value, -limit, limit);
	}

	@Override
	public float clamp(final float value, final float min, final float max) {
		return value < min ? min : (value > max ? max : value);
	}

	@Override
	public int clamp(final int value, final int limit) {
		return clamp(value, -limit, limit);
	}

	@Override
	public int clamp(final int value, final int min, final int max) {
		return value < min ? min : (value > max ? max : value);
	}

	@Override
	public int invert(final int value, final boolean negative) {
		return negative ? -value : value;
	}

	@Override
	public float invert(final float value, final boolean negative) {
		return negative ? -value : value;
	}

	@Override
	public double invert(final double value, final boolean negative) {
		return negative ? -value : value;
	}

	@Override
	public long toLong(final int msw, final int lsw) {
		return longHashToLong(msw, lsw);
	}

	@Override
	public long longHashToLong(final int msw, final int lsw) {
		return LongHash.toLong(msw, lsw);
	}

	@Override
	public int longHashMsw(final long key) {
		return LongHash.msw(key);
	}

	@Override
	public int longHashLsw(final long key) {
		return LongHash.lsw(key);
	}

	@Override
	public void setVectorLength(final Vector vector, final double length) {
		setVectorLengthSquared(vector, Math.signum(length) * length * length);
	}

	@Override
	public void setVectorLengthSquared(final Vector vector, final double lengthsquared) {
		final double vlength = vector.lengthSquared();
		if (Math.abs(vlength) > 0.0001) {
			if (lengthsquared < 0) {
				vector.multiply(-Math.sqrt(-lengthsquared / vlength));
			} else {
				vector.multiply(Math.sqrt(lengthsquared / vlength));
			}
		}
	}

	@Override
	public boolean isHeadingTo(final BlockFace direction, final Vector velocity) {
		return isHeadingTo(Core.getCore().getFaceUtil().faceToVector(direction), velocity);
	}

	@Override
	public boolean isHeadingTo(final Location from, final Location to, final Vector velocity) {
		return isHeadingTo(new Vector(to.getX() - from.getX(), to.getY() - from.getY(), to.getZ() - from.getZ()), velocity);
	}

	@Override
	public boolean isHeadingTo(final Vector offset, final Vector velocity) {
		final double dbefore = offset.lengthSquared();
		if (dbefore < 0.0001) {
			return true;
		}
		final Vector clonedVelocity = velocity.clone();
		setVectorLengthSquared(clonedVelocity, dbefore);
		return dbefore > clonedVelocity.subtract(offset).lengthSquared();
	}

	@Override
	public double getHalfRootOfTwo() {
		return HALFROOTOFTWO;
	}
}
