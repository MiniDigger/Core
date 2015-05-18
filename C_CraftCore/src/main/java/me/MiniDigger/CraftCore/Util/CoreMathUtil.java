package me.MiniDigger.CraftCore.Util;

import org.bukkit.craftbukkit.v1_8_R1.TrigMath;
import org.bukkit.craftbukkit.v1_8_R1.util.LongHash;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Util.MathUtil;

/**
 * Multiple Math utilities to compare and calculate using Vectors and raw values
 */

public class CoreMathUtil implements MathUtil {
	
	private final int	CHUNK_BITS	  = 4;
	private final int	CHUNK_VALUES	= 16;
	public final float	DEGTORAD	  = 0.017453293F;
	public final float	RADTODEG	  = 57.29577951F;
	public final double	HALFROOTOFTWO	= 0.707106781;
	
	@Override
	public double lengthSquared(double... values) {
		double rval = 0;
		for (double value : values) {
			rval += value * value;
		}
		return rval;
	}
	
	@Override
	public double length(double... values) {
		return Math.sqrt(lengthSquared(values));
	}
	
	@Override
	public double distance(double x1, double y1, double x2, double y2) {
		return length(x1 - x2, y1 - y2);
	}
	
	@Override
	public double distanceSquared(double x1, double y1, double x2, double y2) {
		return lengthSquared(x1 - x2, y1 - y2);
	}
	
	@Override
	public double distance(double x1, double y1, double z1, double x2, double y2, double z2) {
		return length(x1 - x2, y1 - y2, z1 - z2);
	}
	
	@Override
	public double distanceSquared(double x1, double y1, double z1, double x2, double y2, double z2) {
		return lengthSquared(x1 - x2, y1 - y2, z1 - z2);
	}
	
	@Override
	public double getPercentage(int subtotal, int total, int decimals) {
		return round(getPercentage(subtotal, total), decimals);
	}
	
	@Override
	public double getPercentage(int subtotal, int total) {
		return ((float) subtotal / (float) total) * 100;
	}
	
	@Override
	public int getAngleDifference(int angle1, int angle2) {
		return Math.abs(wrapAngle(angle1 - angle2));
	}
	
	@Override
	public float getAngleDifference(float angle1, float angle2) {
		return Math.abs(wrapAngle(angle1 - angle2));
	}
	
	@Override
	public int wrapAngle(int angle) {
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
	public float wrapAngle(float angle) {
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
	public double normalize(double x, double z, double reqx, double reqz) {
		return Math.sqrt(lengthSquared(reqx, reqz) / lengthSquared(x, z));
	}
	
	@Override
	public float getLookAtYaw(Entity loc, Entity lookat) {
		return getLookAtYaw(loc.getLocation(), lookat.getLocation());
	}
	
	@Override
	public float getLookAtYaw(Block loc, Block lookat) {
		return getLookAtYaw(loc.getLocation(), lookat.getLocation());
	}
	
	@Override
	public float getLookAtYaw(Location loc, Location lookat) {
		return getLookAtYaw(lookat.getX() - loc.getX(), lookat.getZ() - loc.getZ());
	}
	
	@Override
	public float getLookAtYaw(Vector motion) {
		return getLookAtYaw(motion.getX(), motion.getZ());
	}
	
	@Override
	public float getLookAtYaw(double dx, double dz) {
		return atan2(dz, dx) - 180f;
	}
	
	@Override
	public float getLookAtPitch(double dX, double dY, double dZ) {
		return getLookAtPitch(dY, length(dX, dZ));
	}
	
	@Override
	public float getLookAtPitch(double dY, double dXZ) {
		return -atan(dY / dXZ);
	}
	
	@Override
	public float atan(double value) {
		return RADTODEG * (float) TrigMath.atan(value);
	}
	
	@Override
	public float atan2(double y, double x) {
		return RADTODEG * (float) TrigMath.atan2(y, x);
	}
	
	@Override
	public int floor(double value) {
		int i = (int) value;
		return value < (double) i ? i - 1 : i;
	}
	
	@Override
	public int floor(float value) {
		int i = (int) value;
		return value < (float) i ? i - 1 : i;
	}
	
	@Override
	public int ceil(double value) {
		return -floor(-value);
	}
	
	@Override
	public int ceil(float value) {
		return -floor(-value);
	}
	
	@Override
	public Location move(Location loc, Vector offset) {
		return move(loc, offset.getX(), offset.getY(), offset.getZ());
	}
	
	@Override
	public Location move(Location loc, double dx, double dy, double dz) {
		Vector off = rotate(loc.getYaw(), loc.getPitch(), dx, dy, dz);
		double x = loc.getX() + off.getX();
		double y = loc.getY() + off.getY();
		double z = loc.getZ() + off.getZ();
		return new Location(loc.getWorld(), x, y, z, loc.getYaw(), loc.getPitch());
	}
	
	@Override
	public Vector rotate(float yaw, float pitch, Vector vector) {
		return rotate(yaw, pitch, vector.getX(), vector.getY(), vector.getZ());
	}
	
	@Override
	public Vector rotate(float yaw, float pitch, double x, double y, double z) {
		// Conversions found by (a lot of) testing
		float angle;
		angle = yaw * DEGTORAD;
		double sinyaw = Math.sin(angle);
		double cosyaw = Math.cos(angle);
		
		angle = pitch * DEGTORAD;
		double sinpitch = Math.sin(angle);
		double cospitch = Math.cos(angle);
		
		Vector vector = new Vector();
		vector.setX((x * sinyaw) - (y * cosyaw * sinpitch) - (z * cosyaw * cospitch));
		vector.setY((y * cospitch) - (z * sinpitch));
		vector.setZ(-(x * cosyaw) - (y * sinyaw * sinpitch) - (z * sinyaw * cospitch));
		return vector;
	}
	
	@Override
	public double round(double value, int decimals) {
		double p = Math.pow(10, decimals);
		return Math.round(value * p) / p;
	}
	
	@Override
	public double fixNaN(double value) {
		return fixNaN(value, 0.0);
	}
	
	@Override
	public double fixNaN(double value, double def) {
		return Double.isNaN(value) ? def : value;
	}
	
	@Override
	public int toChunk(double loc) {
		return floor(loc / (double) CHUNK_VALUES);
	}
	
	@Override
	public int toChunk(int loc) {
		return loc >> CHUNK_BITS;
	}
	
	@Override
	public double useOld(double oldvalue, double newvalue, double peruseold) {
		return oldvalue + (peruseold * (newvalue - oldvalue));
	}
	
	@Override
	public double lerp(double d1, double d2, double stage) {
		if (Double.isNaN(stage) || stage > 1) {
			return d2;
		} else if (stage < 0) {
			return d1;
		} else {
			return d1 * (1 - stage) + d2 * stage;
		}
	}
	
	@Override
	public Vector lerp(Vector vec1, Vector vec2, double stage) {
		Vector newvec = new Vector();
		newvec.setX(lerp(vec1.getX(), vec2.getX(), stage));
		newvec.setY(lerp(vec1.getY(), vec2.getY(), stage));
		newvec.setZ(lerp(vec1.getZ(), vec2.getZ(), stage));
		return newvec;
	}
	
	@Override
	public Location lerp(Location loc1, Location loc2, double stage) {
		Location newloc = new Location(loc1.getWorld(), 0, 0, 0);
		newloc.setX(lerp(loc1.getX(), loc2.getX(), stage));
		newloc.setY(lerp(loc1.getY(), loc2.getY(), stage));
		newloc.setZ(lerp(loc1.getZ(), loc2.getZ(), stage));
		newloc.setYaw((float) lerp(loc1.getYaw(), loc2.getYaw(), stage));
		newloc.setPitch((float) lerp(loc1.getPitch(), loc2.getPitch(), stage));
		return newloc;
	}
	
	@Override
	public boolean isInverted(double value1, double value2) {
		return (value1 > 0 && value2 < 0) || (value1 < 0 && value2 > 0);
	}
	
	@Override
	public Vector getDirection(float yaw, float pitch) {
		Vector vector = new Vector();
		double rotX = DEGTORAD * yaw;
		double rotY = DEGTORAD * pitch;
		vector.setY(-Math.sin(rotY));
		double h = Math.cos(rotY);
		vector.setX(-h * Math.sin(rotX));
		vector.setZ(h * Math.cos(rotX));
		return vector;
	}
	
	@Override
	public double clamp(double value, double limit) {
		return clamp(value, -limit, limit);
	}
	
	@Override
	public double clamp(double value, double min, double max) {
		return value < min ? min : (value > max ? max : value);
	}
	
	@Override
	public float clamp(float value, float limit) {
		return clamp(value, -limit, limit);
	}
	
	@Override
	public float clamp(float value, float min, float max) {
		return value < min ? min : (value > max ? max : value);
	}
	
	@Override
	public int clamp(int value, int limit) {
		return clamp(value, -limit, limit);
	}
	
	@Override
	public int clamp(int value, int min, int max) {
		return value < min ? min : (value > max ? max : value);
	}
	
	@Override
	public int invert(int value, boolean negative) {
		return negative ? -value : value;
	}
	
	@Override
	public float invert(float value, boolean negative) {
		return negative ? -value : value;
	}
	
	@Override
	public double invert(double value, boolean negative) {
		return negative ? -value : value;
	}
	
	@Override
	public long toLong(int msw, int lsw) {
		return longHashToLong(msw, lsw);
	}
	
	@Override
	public long longHashToLong(int msw, int lsw) {
		return LongHash.toLong(msw, lsw);
	}
	
	@Override
	public int longHashMsw(long key) {
		return LongHash.msw(key);
	}
	
	@Override
	public int longHashLsw(long key) {
		return LongHash.lsw(key);
	}
	
	@Override
	public void setVectorLength(Vector vector, double length) {
		setVectorLengthSquared(vector, Math.signum(length) * length * length);
	}
	
	@Override
	public void setVectorLengthSquared(Vector vector, double lengthsquared) {
		double vlength = vector.lengthSquared();
		if (Math.abs(vlength) > 0.0001) {
			if (lengthsquared < 0) {
				vector.multiply(-Math.sqrt(-lengthsquared / vlength));
			} else {
				vector.multiply(Math.sqrt(lengthsquared / vlength));
			}
		}
	}
	
	@Override
	public boolean isHeadingTo(BlockFace direction, Vector velocity) {
		return isHeadingTo(Core.getCore().getFaceUtil().faceToVector(direction), velocity);
	}
	
	@Override
	public boolean isHeadingTo(Location from, Location to, Vector velocity) {
		return isHeadingTo(new Vector(to.getX() - from.getX(), to.getY() - from.getY(), to.getZ() - from.getZ()), velocity);
	}
	
	@Override
	public boolean isHeadingTo(Vector offset, Vector velocity) {
		double dbefore = offset.lengthSquared();
		if (dbefore < 0.0001) {
			return true;
		}
		Vector clonedVelocity = velocity.clone();
		setVectorLengthSquared(clonedVelocity, dbefore);
		return dbefore > clonedVelocity.subtract(offset).lengthSquared();
	}
	
	@Override
	public double getHalfRootOfTwo() {
		return HALFROOTOFTWO;
	}
}
