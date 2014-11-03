/*******************************************************************************
 * Test
 *******************************************************************************/
/**
 * ██████████████████████████████████████████████████████████████████████████████████████████████████████████████
 * █░░░░░░░░░░░░░░█░░░░░░██░░░░░░█░░░░░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░░░░░░░░░░░███░░░░░░░░░░░░░░█
 * █░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀▄▀░░███░░▄▀▄▀▄▀▄▀▄▀░░█
 * █░░░░░░▄▀░░░░░░█░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░░░░░█░░▄▀░░░░░░░░░░█░░▄▀░░░░░░▄▀░░█░░▄▀░░░░░░░░▄▀░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░█████████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░████░░▄▀░░███░░▄▀░░█████████
 * █████░░▄▀░░█████░░▄▀░░░░░░▄▀░░█░░▄▀░░░░░░░░░░█░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░░░▄▀░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀▄▀░░███░░▄▀▄▀▄▀▄▀▄▀░░█
 * █████░░▄▀░░█████░░▄▀░░░░░░▄▀░░█░░▄▀░░░░░░░░░░█░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░▄▀░░░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░█████████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░██░░▄▀░░█████░░▄▀░░█████████
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░░░░░█░░▄▀░░░░░░░░░░█░░▄▀░░░░░░▄▀░░█░░▄▀░░██░░▄▀░░░░░░█░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░██░░▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█
 * █████░░░░░░█████░░░░░░██░░░░░░█░░░░░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░██░░░░░░░░░░█░░░░░░░░░░░░░░█
 * ██████████████████████████████████████████████████████████████████████████████████████████████████████████████
 */

/**
 *
 * ██████████████████████████████████████████████████████████████████████████████████████████████████████████████
 * █░░░░░░░░░░░░░░█░░░░░░██░░░░░░█░░░░░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░░░░░░░░░░░███░░░░░░░░░░░░░░█
 * █░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀▄▀░░███░░▄▀▄▀▄▀▄▀▄▀░░█
 * █░░░░░░▄▀░░░░░░█░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░░░░░█░░▄▀░░░░░░░░░░█░░▄▀░░░░░░▄▀░░█░░▄▀░░░░░░░░▄▀░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░█████████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░████░░▄▀░░███░░▄▀░░█████████
 * █████░░▄▀░░█████░░▄▀░░░░░░▄▀░░█░░▄▀░░░░░░░░░░█░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░░░▄▀░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀▄▀░░███░░▄▀▄▀▄▀▄▀▄▀░░█
 * █████░░▄▀░░█████░░▄▀░░░░░░▄▀░░█░░▄▀░░░░░░░░░░█░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░▄▀░░░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░█████████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░██░░▄▀░░█████░░▄▀░░█████████
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░░░░░█░░▄▀░░░░░░░░░░█░░▄▀░░░░░░▄▀░░█░░▄▀░░██░░▄▀░░░░░░█░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░██░░▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█
 * █████░░░░░░█████░░░░░░██░░░░░░█░░░░░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░██░░░░░░░░░░█░░░░░░░░░░░░░░█
 * ██████████████████████████████████████████████████████████████████████████████████████████████████████████████
 */

package me.MiniDigger.CraftCore.Holo;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Holo.Holo;
import me.MiniDigger.Core.Holo.HoloHandler;
import me.MiniDigger.Core.Holo.HoloList;
import me.MiniDigger.Core.User.User;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class CoreHoloHandler implements HoloHandler {
	
	private int	          currentID	= 9000;
	
	private final Color[]	colors	= { Color.fromRGB(0, 0, 0), Color.fromRGB(0, 0, 170), Color.fromRGB(0, 170, 0), Color.fromRGB(0, 170, 170), Color.fromRGB(170, 0, 0),
	        Color.fromRGB(170, 0, 170), Color.fromRGB(255, 170, 0), Color.fromRGB(170, 170, 170), Color.fromRGB(85, 85, 85), Color.fromRGB(85, 85, 255),
	        Color.fromRGB(85, 255, 85), Color.fromRGB(85, 255, 255), Color.fromRGB(255, 85, 85), Color.fromRGB(255, 85, 255), Color.fromRGB(255, 255, 85),
	        Color.fromRGB(255, 255, 255) };
	
	@Override
	public List<HoloList> loadImage(final String url) {
		ArrayList<String> lines = new ArrayList<>();
		if (url.endsWith(".gif")) {
			try {
				final ImageReader reader = ImageIO.getImageReadersByFormatName("gif").next();
				reader.setInput(ImageIO.createImageInputStream(new URL(url).openStream()), false);
				final int frames = reader.getNumImages(true);
				
				final List<HoloList> tags = new ArrayList<>();
				for (int i = 0; i < frames; i++) {
					
					lines = getLinesFromImage(reader.read(i));
					
					final HoloList nametagList = new CoreHoloList();
					
					for (int i2 = 0; i2 < lines.size(); i2++) {
						
						final String line = lines.get(i2);
						
						nametagList.getHolos().add(new CoreHolo(currentID, new Location(Bukkit.getWorlds().get(0), 0, 80, 0), 20 - (i2 * 0.25), line));
						
						currentID += 2;
					}
					
					tags.add(nametagList);
					
					if (i == 0) {
						for (final Player p : Core.getCore().getUserHandler().getOnlinePlayers()) {
							nametagList.showHolos(Core.getCore().getUserHandler().get(p.getUniqueId()));
						}
					}
				}
				
				Bukkit.getScheduler().runTask((Plugin) Core.getCore().getInstance(), new UpdateNametagTask(10, tags));
				return tags;
			}
			
			catch (final IOException ex) {
				
				Logger.getLogger(Core.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		
		else {
			
			try {
				
				lines = getLinesFromImage(ImageIO.read(new URL(url)));
			}
			
			catch (final IOException ex) {
				
				Logger.getLogger(Core.class.getName()).log(Level.SEVERE, null, ex);
			}
			
			final HoloList nametagList = new CoreHoloList();
			
			for (int i = 0; i < lines.size(); i++) {
				
				final String line = lines.get(i);
				
				nametagList.getHolos().add(new CoreHolo(currentID, new Location(Bukkit.getWorlds().get(0), 0, 80, 0), 20 - (i * 0.25), line));
				
				currentID += 2;
			}
			
			for (final Player p : Core.getCore().getUserHandler().getOnlinePlayers()) {
				
				nametagList.showHolos(Core.getCore().getUserHandler().get(p.getUniqueId()));
			}
			
			final List<HoloList> tags = new ArrayList<>();
			tags.add(nametagList);
			return tags;
		}
		return null;
	}
	
	private double getDistance(final Color c1, final Color c2) {
		final double rmean = (c1.getRed() + c2.getRed()) / 2.0;
		
		final double r = c1.getRed() - c2.getRed();
		final double g = c1.getGreen() - c2.getGreen();
		final int b = c1.getBlue() - c2.getBlue();
		
		final double weightR = 2 + rmean / 256.0;
		final double weightG = 4.0;
		final double weightB = 2 + (255 - rmean) / 256.0;
		
		return weightR * r * r + weightG * g * g + weightB * b * b;
	}
	
	private ArrayList<String> getLinesFromImage(BufferedImage image) {
		final ArrayList<String> lines = new ArrayList<>();
		if (image.getWidth() > 128) {
			final BufferedImage tmp = new BufferedImage(128, image.getHeight() / (image.getWidth() / 128), BufferedImage.TYPE_INT_ARGB);
			final Graphics2D resizer = tmp.createGraphics();
			
			resizer.setComposite(AlphaComposite.Src);
			resizer.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			resizer.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
			resizer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			resizer.drawImage(image, 0, 0, 128, image.getHeight() / (image.getWidth() / 128), null);
			resizer.dispose();
			
			image = tmp;
		}
		
		final int width = image.getWidth();
		final int height = image.getHeight();
		for (int y = 0; y < height; y++) {
			final StringBuilder line = new StringBuilder();
			for (int x = 0; x < width; x++) {
				final int color = image.getRGB(x, y);
				
				final int red = (color & 0x00ff0000) >> 16;
				final int green = (color & 0x0000ff00) >> 8;
				final int blue = color & 0x000000ff;
				
				final int alpha = (color >> 24) & 0xff;
				if (alpha < 128) {
					line.append(ChatColor.WHITE);
					line.append("⬛");
					continue;
				}
				
				int index = 0;
				int best = -1;
				for (int i = 0; i < colors.length; i++) {
					final double distance = getDistance(Color.fromRGB(red, green, blue), colors[i]);
					if (x == 0 && y == 0) {
						// Bukkit.broadcastMessage(colors[i].toString());
					}
					
					if (distance < best || best == -1) {
						best = (int) distance;
						index = i;
					}
				}
				line.append(ChatColor.values()[index]);
				line.append("⬛");
			}
			lines.add(line.toString());
		}
		return lines;
	}
	
	class UpdateNametagTask implements Runnable {
		
		private int		             frame	= 0;
		private int		             next	= 1;
		
		private final int		     delay;
		
		private final List<HoloList>	tags;
		
		public UpdateNametagTask(final int delay, final List<HoloList> tags) {
			this.delay = delay;
			this.tags = tags;
		}
		
		@Override
		public void run() {
			for (int i = 0; i < tags.get(frame).getHolos().size(); i++) {
				final Holo nametag = tags.get(frame).getHolos().get(i);
				final Holo nametag2 = tags.get(next).getHolos().get(i);
				
				for (final Player p : Core.getCore().getUserHandler().getOnlinePlayers()) {
					final User u = Core.getCore().getUserHandler().get(p.getUniqueId());
					// nametag.setName(p, nametag2.name); Meh doesn't work, not
					// exactly sure why.
					nametag.hide(u);
					nametag2.show(u);
				}
			}
			
			frame++;
			next++;
			if (frame == tags.size()) {
				frame = 0;
			}
			
			if (next == tags.size()) {
				next = 0;
			}
			
			if (next != /* 0 */-1) {
				Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin) Core.getCore().getInstance(), this, delay);
			}
		}
	}
}
