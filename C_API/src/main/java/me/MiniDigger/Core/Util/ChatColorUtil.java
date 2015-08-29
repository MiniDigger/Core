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

import org.bukkit.ChatColor;
import org.bukkit.Color;

public interface ChatColorUtil {

	final static String possibleColours = "0123456789abcdefklmnor";

	/**
	 *
	 * Replaces the Minecraft color chars with & chars
	 *
	 * @param lines
	 *            The lines to replace
	 * @return
	 */
	public String[] replaceMcToAnd(final String[] lines);

	/**
	 * Replaces the & chars with Minecraft color chars
	 *
	 * @param lines
	 *            The lines to replace
	 * @return
	 */
	public String[] replaceAndToMc(final String[] lines);

	/**
	 * Generate a ChatColor from a String
	 *
	 * @param s
	 *            The String to parse
	 * @return The ChatColor, RESET if noting found
	 */
	public ChatColor toChatColor(final String s);

	/**
	 * Replaces the old chars with the new ones
	 *
	 * @param lines
	 *            The input lines
	 * @param old
	 *            The old char
	 * @param neu
	 *            The new char
	 * @return The lines with replaced chars
	 */
	public String[] replaceColorChars(final String[] lines, final char old, final char neu);

	/**
	 *
	 * Replaces the Minecraft color chars with & chars
	 *
	 * @param msg
	 *            The String to replace
	 * @return
	 */
	public String replaceMcToAnd(final String msg);

	/**
	 * Replaces the & chars with Minecraft color chars
	 *
	 * @param msg
	 *            The msg
	 * @return
	 */
	public String replaceAndToMc(final String msg);

	/**
	 * Replaces the old chars with the new ones
	 *
	 * @param msg
	 *            The input String
	 * @param old
	 *            The old char
	 * @param neu
	 *            The new char
	 * @return The String with replaced chars
	 */

	public String replaceColorChars(final String msg, final char old, final char neu);

	/**
	 * @param c
	 * @return a Color object that matches the chatcolor
	 */
	Color toColor(final ChatColor c);

	/**
	 * Converts a string into an json text message
	 *
	 * @param string
	 * @return
	 */
	String convertToJSON(String string);
}
