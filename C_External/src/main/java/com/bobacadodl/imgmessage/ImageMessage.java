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
package com.bobacadodl.imgmessage;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.util.ChatPaginator;

/**
 * User: bobacadodl Date: 1/25/14 Time: 10:28 PM
 */
public class ImageMessage {

	private final static char TRANSPARENT_CHAR = ' ';

	private final Color[] colors = { new Color(0, 0, 0), new Color(0, 0, 170), new Color(0, 170, 0), new Color(0, 170, 170), new Color(170, 0, 0), new Color(170, 0, 170), new Color(255, 170, 0), new Color(170, 170, 170),
			new Color(85, 85, 85), new Color(85, 85, 255), new Color(85, 255, 85), new Color(85, 255, 255), new Color(255, 85, 85), new Color(255, 85, 255), new Color(255, 255, 85), new Color(255, 255, 255), };

	private final String[] lines;

	public ImageMessage(final BufferedImage image, final int height, final char imgChar) {
		final ChatColor[][] chatColors = toChatColorArray(image, height);
		lines = toImgMessage(chatColors, imgChar);
	}

	public ImageMessage(final ChatColor[][] chatColors, final char imgChar) {
		lines = toImgMessage(chatColors, imgChar);
	}

	public ImageMessage(final String... imgLines) {
		lines = imgLines;
	}

	public ImageMessage appendText(final String... text) {
		for (int y = 0; y < lines.length; y++) {
			if (text.length > y) {
				lines[y] += " " + text[y];
			}
		}
		return this;
	}

	public ImageMessage appendCenteredText(final String... text) {
		for (int y = 0; y < lines.length; y++) {
			if (text.length > y) {
				final int len = ChatPaginator.AVERAGE_CHAT_PAGE_WIDTH - lines[y].length();
				lines[y] = lines[y] + center(text[y], len);
			} else {
				return this;
			}
		}
		return this;
	}

	private ChatColor[][] toChatColorArray(final BufferedImage image, final int height) {
		final double ratio = (double) image.getHeight() / image.getWidth();
		int width = (int) (height / ratio);
		if (width > 10) {
			width = 10;
		}
		final BufferedImage resized = resizeImage(image, (int) (height / ratio), height);

		final ChatColor[][] chatImg = new ChatColor[resized.getWidth()][resized.getHeight()];
		for (int x = 0; x < resized.getWidth(); x++) {
			for (int y = 0; y < resized.getHeight(); y++) {
				final int rgb = resized.getRGB(x, y);
				final ChatColor closest = getClosestChatColor(new Color(rgb, true));
				chatImg[x][y] = closest;
			}
		}
		return chatImg;
	}

	private String[] toImgMessage(final ChatColor[][] colors, final char imgchar) {
		final String[] lines = new String[colors[0].length];
		for (int y = 0; y < colors[0].length; y++) {
			String line = "";
			for (int x = 0; x < colors.length; x++) {
				final ChatColor color = colors[x][y];
				line += (color != null) ? colors[x][y].toString() + imgchar : TRANSPARENT_CHAR;
			}
			lines[y] = line + ChatColor.RESET;
		}
		return lines;
	}

	private BufferedImage resizeImage(final BufferedImage originalImage, final int width, final int height) {
		final AffineTransform af = new AffineTransform();
		af.scale(width / (double) originalImage.getWidth(), height / (double) originalImage.getHeight());

		final AffineTransformOp operation = new AffineTransformOp(af, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		return operation.filter(originalImage, null);
	}

	private double getDistance(final Color c1, final Color c2) {
		final double rmean = (c1.getRed() + c2.getRed()) / 2.0;
		final double r = c1.getRed() - c2.getRed();
		final double g = c1.getGreen() - c2.getGreen();
		final double b = c1.getBlue() - c2.getBlue();
		final double weightR = 2 + rmean / 256.0;
		final double weightG = 4.0;
		final double weightB = 2 + (255 - rmean) / 256.0;
		return weightR * r * r + weightG * g * g + weightB * b * b;
	}

	@SuppressWarnings("unused")
	// ist net besser ;D
	private double getNewDistance(final Color c1, final Color c2) {
		final long rmean = ((long) c1.getRed() + (long) c2.getRed()) / 2;
		final long r = (long) c1.getRed() - (long) c2.getRed();
		final long g = (long) c1.getGreen() - (long) c2.getGreen();
		final long b = (long) c1.getBlue() - (long) c2.getBlue();
		return Math.sqrt((((512 + rmean) * r * r) >> 8) + 4 * g * g + (((767 - rmean) * b * b) >> 8));
	}

	private boolean areIdentical(final Color c1, final Color c2) {
		return Math.abs(c1.getRed() - c2.getRed()) <= 5 && Math.abs(c1.getGreen() - c2.getGreen()) <= 5 && Math.abs(c1.getBlue() - c2.getBlue()) <= 5;

	}

	private ChatColor getClosestChatColor(final Color color) {
		if (color.getAlpha() < 128) {
			return null;
		}

		int index = 0;
		double best = -1;

		for (int i = 0; i < colors.length; i++) {
			if (areIdentical(colors[i], color)) {
				return ChatColor.values()[i];
			}
		}

		for (int i = 0; i < colors.length; i++) {
			final double distance = getDistance(color, colors[i]);
			if (distance < best || best == -1) {
				best = distance;
				index = i;
			}
		}

		// Minecraft has 15 colors
		return ChatColor.values()[index];
	}

	private String center(final String s, final int length) {
		if (s.length() > length) {
			return s.substring(0, length);
		} else if (s.length() == length) {
			return s;
		} else {
			final int leftPadding = (length - s.length()) / 2;
			final StringBuilder leftBuilder = new StringBuilder();
			for (int i = 0; i < leftPadding; i++) {
				leftBuilder.append(" ");
			}
			return leftBuilder.toString() + s;
		}
	}

	public String[] getLines() {
		return lines;
	}

	public void sendToPlayer(final Player player) {
		for (final String line : lines) {
			player.sendMessage(line);
		}
	}
}
