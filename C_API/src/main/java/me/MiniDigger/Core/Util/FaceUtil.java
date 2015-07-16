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
import org.bukkit.util.Vector;

public interface FaceUtil {

	/**
	 * Gets the Notch integer representation of a BlockFace<br>
	 * <b>These are the horizontal faces, which exclude up and down</b>
	 *
	 * @param face
	 *            to get
	 * @return Notch of the face
	 */
	int faceToNotch(final BlockFace face);

	/**
	 * Checks whether a given face is an offset along the X-axis
	 *
	 * @param face
	 *            to check
	 * @return True if it is along the X-axis, False if not
	 */
	boolean isAlongX(final BlockFace face);

	/**
	 * Checks whether a given face is an offset along the Y-axis
	 *
	 * @param face
	 *            to check
	 * @return True if it is along the Y-axis, False if not
	 */
	boolean isAlongY(final BlockFace face);

	/**
	 * Checks whether a given face is an offset along the Z-axis
	 *
	 * @param face
	 *            to check
	 * @return True if it is along the Z-axis, False if not
	 */
	boolean isAlongZ(final BlockFace face);

	/**
	 * Gets the Block Face at the notch index specified<br>
	 * <b>These are the horizontal faces, which exclude up and down</b>
	 *
	 * @param notch
	 *            to get
	 * @return BlockFace of the notch
	 */
	BlockFace notchToFace(final int notch);

	/**
	 * Rotates a given Block Face horizontally
	 *
	 * @param from
	 *            face
	 * @param notchCount
	 *            to rotate at
	 * @return rotated face
	 */
	BlockFace rotate(final BlockFace from, final int notchCount);

	/**
	 * Combines two non-subcardinal faces into one face<br>
	 * - NORTH and WEST returns NORTH_WEST<br>
	 * - NORTH and SOUTH returns NORTH (not possible to combine)
	 *
	 * @param from
	 *            face to combined
	 * @param to
	 *            face to combined
	 * @return the combined face
	 */
	BlockFace combine(final BlockFace from, final BlockFace to);

	/**
	 * Subtracts two faces
	 *
	 * @param face1
	 * @param face2
	 *            to subtract from face1
	 * @return Block Face result ofthe subtraction
	 */
	BlockFace subtract(final BlockFace face1, final BlockFace face2);

	/**
	 * Adds two faces together
	 *
	 * @param face1
	 * @param face2
	 *            to add to face1
	 * @return Block Face result of the addition
	 */
	BlockFace add(final BlockFace face1, final BlockFace face2);

	/**
	 * Gets all the individual faces represented by a Block Face<br>
	 * - NORTH_WEST returns NORTH and WEST<br>
	 * - NORTH returns NORTH and SOUTH<br>
	 *
	 * @param main
	 *            face to get the faces for
	 * @return an array of length 2 containing all the faces
	 */
	BlockFace[] getFaces(final BlockFace main);

	/**
	 * Gets the direction a minecart faces when on a given track
	 *
	 * @param raildirection
	 *            of the rails
	 * @return minecart direction
	 */
	BlockFace getRailsCartDirection(final BlockFace raildirection);

	/**
	 * Gets the rail direction from a Direction<br>
	 * NORTH becomes SOUTH and WEST becomes EAST
	 *
	 * @param direction
	 *            to convert
	 * @return rail direction
	 */
	BlockFace toRailsDirection(final BlockFace direction);

	/**
	 * Gets whether a given Block Face is sub-cardinal (such as NORTH_WEST)
	 *
	 * @param face
	 *            to check
	 * @return True if sub-cardinal, False if not
	 */
	boolean isSubCardinal(final BlockFace face);

	/**
	 * Checks whether a face is up or down
	 *
	 * @param face
	 *            to check
	 * @return True if it is UP or DOWN
	 */
	boolean isVertical(final BlockFace face);

	/**
	 * Gets the BlockFace.UP or BlockFace.DOWN constant based on the up
	 * parameter
	 *
	 * @param up
	 *            parameter
	 * @return UP if up is true, DOWN if up is false
	 */
	BlockFace getVertical(final boolean up);

	/**
	 * Gets the BlockFace.UP or BlockFace.DOWN based on the delta-y parameter
	 *
	 * @param dy
	 *            parameter
	 * @return UP if dy >= 0, DOWN if dy < 0
	 */
	BlockFace getVertical(final double dy);

	/**
	 * Gets whether two faces have a sub-cardinal difference or less
	 *
	 * @param face1
	 *            to check
	 * @param face2
	 *            to check
	 * @return True if the difference <= 45 degrees
	 */
	boolean hasSubDifference(final BlockFace face1, final BlockFace face2);

	/**
	 * Gets the Vector direction from a Block Face
	 *
	 * @param face
	 *            to use
	 * @param length
	 *            of the vector
	 * @return Vector of the direction and length
	 */
	Vector faceToVector(final BlockFace face, final double length);

	/**
	 * Gets the Vector direction from a Block Face
	 *
	 * @param face
	 *            to use
	 * @return Vector of the direction and length 1
	 */
	Vector faceToVector(final BlockFace face);

	/**
	 * Gets the Block Face direction to go from one point to another
	 *
	 * @param from
	 *            point
	 * @param to
	 *            point
	 * @param useSubCardinalDirections
	 *            setting
	 * @return the Block Face of the direction
	 */
	BlockFace getDirection(final Location from, final Location to, final boolean useSubCardinalDirections);

	/**
	 * Gets the Block Face direction to go from one block to another
	 *
	 * @param from
	 *            block
	 * @param to
	 *            block
	 * @param useSubCardinalDirections
	 *            setting
	 * @return the Block Face of the direction
	 */
	BlockFace getDirection(final Block from, final Block to, final boolean useSubCardinalDirections);

	/**
	 * Gets the Block Face direction to go into the movement vector direction
	 *
	 * @param movement
	 *            vector
	 * @return the Block Face of the direction
	 */
	BlockFace getDirection(final Vector movement);

	/**
	 * Gets the Block Face direction to go into the movement vector direction
	 *
	 * @param movement
	 *            vector
	 * @param useSubCardinalDirections
	 *            setting
	 * @return the Block Face of the direction
	 */
	BlockFace getDirection(final Vector movement, final boolean useSubCardinalDirections);

	/**
	 * Gets the Block Face direction to go into the movement vector direction
	 *
	 * @param dx
	 *            vector axis
	 * @param dz
	 *            vector axis
	 * @param useSubCardinalDirections
	 *            setting
	 * @return the Block Face of the direction
	 */
	BlockFace getDirection(final double dx, final double dz, final boolean useSubCardinalDirections);

	/**
	 * Gets the yaw angle in degrees difference between two Block Faces
	 *
	 * @param face1
	 * @param face2
	 * @return angle in degrees
	 */
	int getFaceYawDifference(final BlockFace face1, final BlockFace face2);

	/**
	 * Gets the co-sinus value from a Block Face treated as an Angle
	 *
	 * @param face
	 *            to get the co-sinus value from
	 * @return co-sinus value
	 */
	double cos(final BlockFace face);

	/**
	 * Gets the sinus value from a Block Face treated as an Angle
	 *
	 * @param face
	 *            to get the sinus value from
	 * @return sinus value
	 */
	double sin(final BlockFace face);

	/**
	 * Gets the angle from a horizontal Block Face
	 *
	 * @param face
	 *            to get the angle for
	 * @return face angle
	 */
	int faceToYaw(final BlockFace face);

	/**
	 * Gets the horizontal Block Face from a given yaw angle<br>
	 * This includes the NORTH_WEST faces
	 *
	 * @param yaw
	 *            angle
	 * @return The Block Face of the angle
	 */
	BlockFace yawToFace(final float yaw);

	/**
	 * Gets the horizontal Block Face from a given yaw angle
	 *
	 * @param yaw
	 *            angle
	 * @param useSubCardinalDirections
	 *            setting, True to allow NORTH_WEST to be returned
	 * @return The Block Face of the angle
	 */
	BlockFace yawToFace(final float yaw, final boolean useSubCardinalDirections);

}
