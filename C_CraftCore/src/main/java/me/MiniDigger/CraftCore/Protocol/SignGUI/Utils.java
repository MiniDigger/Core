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
package me.MiniDigger.CraftCore.Protocol.SignGUI;

import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class Utils {

	private static final List<Material> SIGNS = Arrays.asList(Material.WALL_SIGN, Material.SIGN_POST, Material.SIGN);

	public static boolean isSign(final Block b) {
		return SIGNS.contains(b.getType());
	}

	public static void write(Block clickedBlock, final BlockFace clicked, final Player writer, final String... texts) {
		if (!isSign(clickedBlock)) {
			return;
		}

		BlockFace left;
		switch (clicked) {
			case EAST:
				left = BlockFace.SOUTH;
				break;
			case NORTH:
				left = BlockFace.EAST;
				break;
			case SOUTH:
				left = BlockFace.WEST;
				break;
			case WEST:
				left = BlockFace.NORTH;
				break;
			default:
				return;
		}

		String text = "";

		for (String line : texts) {
			line = line.replace("\n", " ");
			if (!text.endsWith(" ") && !line.startsWith(" ")) {
				text += " ";
			}

			text += line;
		}

		text = ChatColor.translateAlternateColorCodes('&', text).replace("\t", " ").replace("  ", " ");

		Block sign = clickedBlock;

		while (isSign(sign.getRelative(left))) {
			sign = sign.getRelative(left);
		}

		while (isSign(sign.getRelative(BlockFace.UP))) {
			sign = sign.getRelative(BlockFace.UP);
		}

		clickedBlock = sign;

		while (isSign(clickedBlock)) {
			while (isSign(sign)) {
				for (int i = 0; i < 4; i++) {
					final Sign s = (Sign) sign.getState();
					if (text.length() <= 15) {
						s.setLine(i, text);
						s.update();
						return;
					}
					if (text.startsWith(" ")) {
						text = text.substring(1);
					}
					s.setLine(i, text.substring(0, 15));
					text = text.substring(15);
					s.update();
				}
				sign = sign.getRelative(left.getOppositeFace());
			}
			sign = clickedBlock = clickedBlock.getRelative(BlockFace.DOWN);
		}
	}

	public static boolean isAction(final PlayerInteractEvent e) {
		return e.getPlayer().isSneaking();
	}

	// ---------------------------- Text ---------------------------- \\

	public static String[] colorCodes(final String... lines) {
		final String[] r = new String[lines.length];
		for (int i = 0; i < lines.length; i++) {
			r[i] = lines[i].replaceAll(ChatColor.COLOR_CHAR + "([a-z0-9])", "&$1");
			while (r[i].startsWith("&0") || r[i].startsWith("§0")) {
				r[i] = r[i].substring(2);
			}
		}
		return r;
	}

	public static void clearLines(final SignChangeEvent e) {
		for (int i = 0; i < e.getLines().length; i++) {
			e.setLine(i, clearLine(e.getLine(i)));
		}
	}

	public static String clearLine(final String line) {
		final StringBuilder builder = new StringBuilder();
		for (final char c : line.toCharArray()) {
			if (c < 0xF700 || c > 0xF747) {
				builder.append(c);
			}
		}
		return builder.toString();
	}
}
