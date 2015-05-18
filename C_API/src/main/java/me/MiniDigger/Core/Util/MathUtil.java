package me.MiniDigger.Core.Util;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

public interface MathUtil {
	
	double lengthSquared(double... values);
	
	double length(double... values);
	
	double distance(double x1, double y1, double x2, double y2);
	
	double distanceSquared(double x1, double y1, double x2, double y2);
	
	double distance(double x1, double y1, double z1, double x2, double y2, double z2);
	
	double distanceSquared(double x1, double y1, double z1, double x2, double y2, double z2);
	
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
	
	double getPercentage(int subtotal, int total, int decimals);
	
	/**
	 * Gets a percentags of 2 values
	 * 
	 * @param subtotal
	 *            to get percentage for
	 * @param total
	 *            to sue as 100% value
	 * @return percentage
	 */
	
	double getPercentage(int subtotal, int total);
	
	/**
	 * Gets the angle difference between two angles
	 * 
	 * @param angle1
	 * @param angle2
	 * @return angle difference
	 */
	int getAngleDifference(int angle1, int angle2);
	
	/**
	 * Gets the angle difference between two angles
	 * 
	 * @param angle1
	 * @param angle2
	 * @return angle difference
	 */
	float getAngleDifference(float angle1, float angle2);
	
	/**
	 * Wraps the angle to be between -180 and 180 degrees
	 * 
	 * @param angle
	 *            to wrap
	 * @return [-180 > angle >= 180]
	 */
	int wrapAngle(int angle);
	
	/**
	 * Wraps the angle to be between -180 and 180 degrees
	 * 
	 * @param angle
	 *            to wrap
	 * @return [-180 > angle >= 180]
	 */
	float wrapAngle(float angle);
	
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
	double normalize(double x, double z, double reqx, double reqz);
	
	float getLookAtYaw(Entity loc, Entity lookat);
	
	float getLookAtYaw(Block loc, Block lookat);
	
	float getLookAtYaw(Location loc, Location lookat);
	
	float getLookAtYaw(Vector motion);
	
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
	float getLookAtYaw(double dx, double dz);
	
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
	float getLookAtPitch(double dX, double dY, double dZ);
	
	/**
	 * Gets the pitch angle in degrees to look into the direction specified
	 * 
	 * @param dY
	 *            axis of the direction
	 * @param dXZ
	 *            axis of the direction (length of x and z)
	 * @return look-at angle in degrees
	 */
	float getLookAtPitch(double dY, double dXZ);
	
	/**
	 * Gets the inverse tangent of the value in degrees
	 * 
	 * @param value
	 * @return inverse tangent angle in degrees
	 */
	float atan(double value);
	
	/**
	 * Gets the inverse tangent angle in degrees of the rectangle vector
	 * 
	 * @param y
	 *            axis
	 * @param x
	 *            axis
	 * @return inverse tangent 2 angle in degrees
	 */
	float atan2(double y, double x);
	
	/**
	 * Gets the floor integer value from a double value
	 * 
	 * @param value
	 *            to get the floor of
	 * @return floor value
	 */
	int floor(double value);
	
	/**
	 * Gets the floor integer value from a float value
	 * 
	 * @param value
	 *            to get the floor of
	 * @return floor value
	 */
	int floor(float value);
	
	/**
	 * Gets the ceiling integer value from a double value
	 * 
	 * @param value
	 *            to get the ceiling of
	 * @return ceiling value
	 */
	int ceil(double value);
	
	/**
	 * Gets the ceiling integer value from a float value
	 * 
	 * @param value
	 *            to get the ceiling of
	 * @return ceiling value
	 */
	int ceil(float value);
	
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
	Location move(Location loc, Vector offset);
	
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
	Location move(Location loc, double dx, double dy, double dz);
	
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
	Vector rotate(float yaw, float pitch, Vector vector);
	
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
	Vector rotate(float yaw, float pitch, double x, double y, double z);
	
	/**
	 * Rounds the specified value to the amount of decimals specified
	 * 
	 * @param value
	 *            to round
	 * @param decimals
	 *            count
	 * @return value round to the decimal count specified
	 */
	double round(double value, int decimals);
	
	/**
	 * Returns 0 if the value is not-a-number
	 * 
	 * @param value
	 *            to check
	 * @return The value, or 0 if it is NaN
	 */
	double fixNaN(double value);
	
	/**
	 * Returns the default if the value is not-a-number
	 * 
	 * @param value
	 *            to check
	 * @param def
	 *            value
	 * @return The value, or the default if it is NaN
	 */
	double fixNaN(double value, double def);
	
	/**
	 * Converts a location value into a chunk coordinate
	 * 
	 * @param loc
	 *            to convert
	 * @return chunk coordinate
	 */
	int toChunk(double loc);
	
	/**
	 * Converts a location value into a chunk coordinate
	 * 
	 * @param loc
	 *            to convert
	 * @return chunk coordinate
	 */
	int toChunk(int loc);
	
	double useOld(double oldvalue, double newvalue, double peruseold);
	
	double lerp(double d1, double d2, double stage);
	
	Vector lerp(Vector vec1, Vector vec2, double stage);
	
	Location lerp(Location loc1, Location loc2, double stage);
	
	/**
	 * Checks whether one value is negative and the other positive, or opposite
	 * 
	 * @param value1
	 *            to check
	 * @param value2
	 *            to check
	 * @return True if value1 is inverted from value2
	 */
	boolean isInverted(double value1, double value2);
	
	/**
	 * Gets the direction of yaw and pitch angles
	 * 
	 * @param yaw
	 *            angle in degrees
	 * @param pitch
	 *            angle in degrees
	 * @return Direction Vector
	 */
	Vector getDirection(float yaw, float pitch);
	
	/**
	 * Clamps the value between -limit and limit
	 * 
	 * @param value
	 *            to clamp
	 * @param limit
	 * @return value, -limit or limit
	 */
	double clamp(double value, double limit);
	
	/**
	 * Clamps the value between the min and max values
	 * 
	 * @param value
	 *            to clamp
	 * @param min
	 * @param max
	 * @return value, min or max
	 */
	double clamp(double value, double min, double max);
	
	/**
	 * Clamps the value between -limit and limit
	 * 
	 * @param value
	 *            to clamp
	 * @param limit
	 * @return value, -limit or limit
	 */
	float clamp(float value, float limit);
	
	/**
	 * Clamps the value between the min and max values
	 * 
	 * @param value
	 *            to clamp
	 * @param min
	 * @param max
	 * @return value, min or max
	 */
	float clamp(float value, float min, float max);
	
	/**
	 * Clamps the value between -limit and limit
	 * 
	 * @param value
	 *            to clamp
	 * @param limit
	 * @return value, -limit or limit
	 */
	int clamp(int value, int limit);
	
	/**
	 * Clamps the value between the min and max values
	 * 
	 * @param value
	 *            to clamp
	 * @param min
	 * @param max
	 * @return value, min or max
	 */
	int clamp(int value, int min, int max);
	
	/**
	 * Turns a value negative or keeps it positive based on a boolean input
	 * 
	 * @param value
	 *            to work with
	 * @param negative
	 *            - True to invert, False to keep the old value
	 * @return the value or inverted (-value)
	 */
	int invert(int value, boolean negative);
	
	/**
	 * Turns a value negative or keeps it positive based on a boolean input
	 * 
	 * @param value
	 *            to work with
	 * @param negative
	 *            - True to invert, False to keep the old value
	 * @return the value or inverted (-value)
	 */
	float invert(float value, boolean negative);
	
	/**
	 * Turns a value negative or keeps it positive based on a boolean input
	 * 
	 * @param value
	 *            to work with
	 * @param negative
	 *            - True to invert, False to keep the old value
	 * @return the value or inverted (-value)
	 */
	double invert(double value, boolean negative);
	
	/**
	 * Merges two ints into a long
	 * 
	 * @param msw
	 *            integer
	 * @param lsw
	 *            integer
	 * @return merged long value
	 */
	long toLong(int msw, int lsw);
	
	long longHashToLong(int msw, int lsw);
	
	int longHashMsw(long key);
	
	int longHashLsw(long key);
	
	void setVectorLength(Vector vector, double length);
	
	void setVectorLengthSquared(Vector vector, double lengthsquared);
	
	boolean isHeadingTo(BlockFace direction, Vector velocity);
	
	boolean isHeadingTo(Location from, Location to, Vector velocity);
	
	boolean isHeadingTo(Vector offset, Vector velocity);

	double getHalfRootOfTwo();
	
}
