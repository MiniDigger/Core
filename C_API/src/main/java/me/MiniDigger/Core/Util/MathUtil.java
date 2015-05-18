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
package me.MiniDigger.Core.Util;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

public interface MathUtil {
	
	double lengthSquared(final double... values);
	
	double length(final double... values);
	
	double distance(final double x1, final double y1, final double x2, final double y2);
	
	double distanceSquared(final double x1, final double y1, final double x2, final double y2);
	
	double distance(final double x1, final double y1, final double z1, final double x2, final double y2, final double z2);
	
	double distanceSquared(final double x1, final double y1, final double z1, final double x2, final double y2, final double z2);
	
	/**
	 * Gets a percentage and round it with a cusotm amound of decimals
	 * 
	 * @param subtotal
	 *            to get percentags for
	 * @param total
	 *            to use as 100% value
	 * @param decimals
	 *            to round with
	 * @return Percentage for subtotal with custom decimals
	 */
	
	double getPercentage(final int subtotal, final int total, final int decimals);
	
	/**
	 * Gets a percentags of 2 values
	 * 
	 * @param subtotal
	 *            to get percentage for
	 * @param total
	 *            to sue as 100% value
	 * @return percentage
	 */
	
	double getPercentage(final int subtotal, final int total);
	
	/**
	 * Gets the angle difference between two angles
	 * 
	 * @param angle1
	 * @param angle2
	 * @return angle difference
	 */
	int getAngleDifference(final int angle1, final int angle2);
	
	/**
	 * Gets the angle difference between two angles
	 * 
	 * @param angle1
	 * @param angle2
	 * @return angle difference
	 */
	float getAngleDifference(final float angle1, final float angle2);
	
	/**
	 * Wraps the angle to be between -180 and 180 degrees
	 * 
	 * @param angle
	 *            to wrap
	 * @return [-180 > angle >= 180]
	 */
	int wrapAngle(final int angle);
	
	/**
	 * Wraps the angle to be between -180 and 180 degrees
	 * 
	 * @param angle
	 *            to wrap
	 * @return [-180 > angle >= 180]
	 */
	float wrapAngle(final float angle);
	
	/**
	 * Normalizes a 2D-vector to be the length of another 2D-vector<br>
	 * Calculates the normalization factor to multiply the input vector with, to
	 * get the requested length
	 * 
	 * @param x
	 *            axis of the vector
	 * @param z
	 *            axis of the vector
	 * @param reqx
	 *            axis of the length vector
	 * @param reqz
	 *            axis of the length vector
	 * @return the normalization factor
	 */
	double normalize(final double x, final double z, final double reqx, final double reqz);
	
	float getLookAtYaw(final Entity loc, final Entity lookat);
	
	float getLookAtYaw(final Block loc, final Block lookat);
	
	float getLookAtYaw(final Location loc, final Location lookat);
	
	float getLookAtYaw(final Vector motion);
	
	/**
	 * Gets the horizontal look-at angle in degrees to look into the
	 * 2D-direction specified
	 * 
	 * @param dx
	 *            axis of the direction
	 * @param dz
	 *            axis of the direction
	 * @return the angle in degrees
	 */
	float getLookAtYaw(final double dx, final double dz);
	
	/**
	 * Gets the pitch angle in degrees to look into the direction specified
	 * 
	 * @param dX
	 *            axis of the direction
	 * @param dY
	 *            axis of the direction
	 * @param dZ
	 *            axis of the direction
	 * @return look-at angle in degrees
	 */
	float getLookAtPitch(final double dX, final double dY, final double dZ);
	
	/**
	 * Gets the pitch angle in degrees to look into the direction specified
	 * 
	 * @param dY
	 *            axis of the direction
	 * @param dXZ
	 *            axis of the direction (length of x and z)
	 * @return look-at angle in degrees
	 */
	float getLookAtPitch(final double dY, final double dXZ);
	
	/**
	 * Gets the inverse tangent of the value in degrees
	 * 
	 * @param value
	 * @return inverse tangent angle in degrees
	 */
	float atan(final double value);
	
	/**
	 * Gets the inverse tangent angle in degrees of the rectangle vector
	 * 
	 * @param y
	 *            axis
	 * @param x
	 *            axis
	 * @return inverse tangent 2 angle in degrees
	 */
	float atan2(final double y, final double x);
	
	/**
	 * Gets the floor integer value from a double value
	 * 
	 * @param value
	 *            to get the floor of
	 * @return floor value
	 */
	int floor(final double value);
	
	/**
	 * Gets the floor integer value from a float value
	 * 
	 * @param value
	 *            to get the floor of
	 * @return floor value
	 */
	int floor(final float value);
	
	/**
	 * Gets the ceiling integer value from a double value
	 * 
	 * @param value
	 *            to get the ceiling of
	 * @return ceiling value
	 */
	int ceil(final double value);
	
	/**
	 * Gets the ceiling integer value from a float value
	 * 
	 * @param value
	 *            to get the ceiling of
	 * @return ceiling value
	 */
	int ceil(final float value);
	
	/**
	 * Moves a Location into the yaw and pitch of the Location in the offset
	 * specified
	 * 
	 * @param loc
	 *            to move
	 * @param offset
	 *            vector
	 * @return Translated Location
	 */
	Location move(final Location loc, final Vector offset);
	
	/**
	 * Moves a Location into the yaw and pitch of the Location in the offset
	 * specified
	 * 
	 * @param loc
	 *            to move
	 * @param dx
	 *            offset
	 * @param dy
	 *            offset
	 * @param dz
	 *            offset
	 * @return Translated Location
	 */
	Location move(final Location loc, final double dx, final double dy, final double dz);
	
	/**
	 * Rotates a 3D-vector using yaw and pitch
	 * 
	 * @param yaw
	 *            angle in degrees
	 * @param pitch
	 *            angle in degrees
	 * @param vector
	 *            to rotate
	 * @return Vector rotated by the angle (new instance)
	 */
	Vector rotate(final float yaw, final float pitch, final Vector vector);
	
	/**
	 * Rotates a 3D-vector using yaw and pitch
	 * 
	 * @param yaw
	 *            angle in degrees
	 * @param pitch
	 *            angle in degrees
	 * @param x
	 *            axis of the vector
	 * @param y
	 *            axis of the vector
	 * @param z
	 *            axis of the vector
	 * @return Vector rotated by the angle
	 */
	Vector rotate(final float yaw, final float pitch, final double x, final double y, final double z);
	
	/**
	 * Rounds the specified value to the amount of decimals specified
	 * 
	 * @param value
	 *            to round
	 * @param decimals
	 *            count
	 * @return value round to the decimal count specified
	 */
	double round(final double value, final int decimals);
	
	/**
	 * Returns 0 if the value is not-a-number
	 * 
	 * @param value
	 *            to check
	 * @return The value, or 0 if it is NaN
	 */
	double fixNaN(final double value);
	
	/**
	 * Returns the default if the value is not-a-number
	 * 
	 * @param value
	 *            to check
	 * @param def
	 *            value
	 * @return The value, or the default if it is NaN
	 */
	double fixNaN(final double value, final double def);
	
	/**
	 * Converts a location value into a chunk coordinate
	 * 
	 * @param loc
	 *            to convert
	 * @return chunk coordinate
	 */
	int toChunk(final double loc);
	
	/**
	 * Converts a location value into a chunk coordinate
	 * 
	 * @param loc
	 *            to convert
	 * @return chunk coordinate
	 */
	int toChunk(final int loc);
	
	double useOld(final double oldvalue, final double newvalue, final double peruseold);
	
	double lerp(final double d1, final double d2, final double stage);
	
	Vector lerp(final Vector vec1, final Vector vec2, final double stage);
	
	Location lerp(final Location loc1, final Location loc2, final double stage);
	
	/**
	 * Checks whether one value is negative and the other positive, or opposite
	 * 
	 * @param value1
	 *            to check
	 * @param value2
	 *            to check
	 * @return True if value1 is inverted from value2
	 */
	boolean isInverted(final double value1, final double value2);
	
	/**
	 * Gets the direction of yaw and pitch angles
	 * 
	 * @param yaw
	 *            angle in degrees
	 * @param pitch
	 *            angle in degrees
	 * @return Direction Vector
	 */
	Vector getDirection(final float yaw, final float pitch);
	
	/**
	 * Clamps the value between -limit and limit
	 * 
	 * @param value
	 *            to clamp
	 * @param limit
	 * @return value, -limit or limit
	 */
	double clamp(final double value, final double limit);
	
	/**
	 * Clamps the value between the min and max values
	 * 
	 * @param value
	 *            to clamp
	 * @param min
	 * @param max
	 * @return value, min or max
	 */
	double clamp(final double value, final double min, final double max);
	
	/**
	 * Clamps the value between -limit and limit
	 * 
	 * @param value
	 *            to clamp
	 * @param limit
	 * @return value, -limit or limit
	 */
	float clamp(final float value, final float limit);
	
	/**
	 * Clamps the value between the min and max values
	 * 
	 * @param value
	 *            to clamp
	 * @param min
	 * @param max
	 * @return value, min or max
	 */
	float clamp(final float value, final float min, final float max);
	
	/**
	 * Clamps the value between -limit and limit
	 * 
	 * @param value
	 *            to clamp
	 * @param limit
	 * @return value, -limit or limit
	 */
	int clamp(final int value, final int limit);
	
	/**
	 * Clamps the value between the min and max values
	 * 
	 * @param value
	 *            to clamp
	 * @param min
	 * @param max
	 * @return value, min or max
	 */
	int clamp(final int value, final int min, final int max);
	
	/**
	 * Turns a value negative or keeps it positive based on a boolean input
	 * 
	 * @param value
	 *            to work with
	 * @param negative
	 *            - True to invert, False to keep the old value
	 * @return the value or inverted (-value)
	 */
	int invert(final int value, final boolean negative);
	
	/**
	 * Turns a value negative or keeps it positive based on a boolean input
	 * 
	 * @param value
	 *            to work with
	 * @param negative
	 *            - True to invert, False to keep the old value
	 * @return the value or inverted (-value)
	 */
	float invert(final float value, final boolean negative);
	
	/**
	 * Turns a value negative or keeps it positive based on a boolean input
	 * 
	 * @param value
	 *            to work with
	 * @param negative
	 *            - True to invert, False to keep the old value
	 * @return the value or inverted (-value)
	 */
	double invert(final double value, final boolean negative);
	
	/**
	 * Merges two ints into a long
	 * 
	 * @param msw
	 *            integer
	 * @param lsw
	 *            integer
	 * @return merged long value
	 */
	long toLong(final int msw, final int lsw);
	
	long longHashToLong(final int msw, final int lsw);
	
	int longHashMsw(final long key);
	
	int longHashLsw(final long key);
	
	void setVectorLength(final Vector vector, final double length);
	
	void setVectorLengthSquared(final Vector vector, final double lengthsquared);
	
	boolean isHeadingTo(final BlockFace direction, final Vector velocity);
	
	boolean isHeadingTo(final Location from, final Location to, final Vector velocity);
	
	boolean isHeadingTo(final Vector offset, final Vector velocity);
	
	double getHalfRootOfTwo();
	
}
