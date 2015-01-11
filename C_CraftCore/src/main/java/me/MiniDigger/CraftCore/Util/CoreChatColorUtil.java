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
package me.MiniDigger.CraftCore.Util;

import org.bukkit.ChatColor;

import me.MiniDigger.Core.Util.ChatColorUtil;

public class CoreChatColorUtil implements ChatColorUtil {
	
	final String	possibleColours	= "0123456789abcdefklmnor";
	final char	 and	            = Character.valueOf((char) 38);
	final char	 mc	                = Character.valueOf((char) 167);
	
	@Override
	public String[] replaceMcToAnd(final String[] lines) {
		return replaceColorChars(lines, mc, and);
	}
	
	@Override
	public String[] replaceAndToMc(final String[] lines) {
		String[] newLines = new String[lines.length];
		for (int i = 0; i < lines.length; i++) {
			newLines[i] = ChatColor.translateAlternateColorCodes(and, lines[i]);
		}
		return newLines;
		// return replaceColorChars(lines, and, mc);
	}
	
	@Override
	public ChatColor toChatColor(final String s) {
		for (int i = 0; i < s.length(); i++) {
			final char c = s.charAt(i);
			if (c != and && c != mc) {
				continue;
			}
			
			final char next = s.charAt(i + 1);
			if (next == -1) {
				continue;
			}
			
			if (possibleColours.contains(next + "")) {
				for (final ChatColor cc : ChatColor.values()) {
					if (cc.getChar() == next) {
						return cc;
					}
				}
			}
		}
		return ChatColor.RESET;
	}
	
	@Override
	public String[] replaceColorChars(final String[] lines, final char mc, final char and) {
		final String colourChar = mc + "";
		
		for (int forInt = 0; forInt < 4; forInt++) {
			if (lines[forInt].isEmpty()) {
				continue;
			}
			final String[] splitLine = lines[forInt].split(colourChar);
			String newLine = splitLine[0];
			for (int i = 1; i < splitLine.length; i++) {
				if (splitLine[i].length() == 0 || (possibleColours.indexOf(splitLine[i].toLowerCase().charAt(0))) == -1 || splitLine[i].length() <= 1) {
					newLine += colourChar;
				} else {
					newLine += and;
				}
				newLine += splitLine[i];
			}
			lines[forInt] = newLine;
		}
		return lines;
	}
	
	@Override
	public String replaceMcToAnd(final String msg) {
		return replaceColorChars(msg, mc, and);
	}
	
	@Override
	public String replaceAndToMc(final String msg) {
		return ChatColor.translateAlternateColorCodes(and, msg);
		// return replaceColorChars(msg, and, mc);
	}
	
	@Override
	public String replaceColorChars(final String msg, final char old, final char neu) {
		final char colourChar = old;
		final String[] splitLine = msg.split(colourChar + "");
		String newLine = splitLine[0];
		for (int i = 1; i < splitLine.length; i++) {
			if (splitLine[i].length() == 0 || (possibleColours.indexOf(splitLine[i].toLowerCase().charAt(0))) == -1 || splitLine[i].length() <= 1) {
				newLine += colourChar;
			} else {
				newLine += neu;
			}
			newLine += splitLine[i];
		}
		return newLine;
	}
	
}
