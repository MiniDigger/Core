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
 * Written by Martin Benndorf <admin@minidigger.me>, 2013-2014 and others
 */
package me.MiniDigger.CraftCore.Generator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Generator.CleanroomChunkGenerator;

public class CoreCleanroomChunkGenerator extends ChunkGenerator implements CleanroomChunkGenerator {
	
	private short[]	layer;
	private byte[]	layerDataValues;
	
	public CoreCleanroomChunkGenerator() {
		this("64,stone");
	}
	
	public CoreCleanroomChunkGenerator(final String id) {
		parse(id);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void parse(String id) {
		if (id != null) {
			try {
				int y = 0;
				
				layer = new short[''];
				layerDataValues = null;
				if ((id.length() > 0) && (id.charAt(0) == '.')) {
					id = id.substring(1);
				} else {
					layer[(y++)] = ((short) Material.BEDROCK.getId());
				}
				if (id.length() > 0) {
					final String[] tokens = id.split("[,]");
					if (tokens.length % 2 != 0) {
						throw new Exception();
					}
					for (int i = 0; i < tokens.length; i += 2) {
						int height = Integer.parseInt(tokens[i]);
						if (height <= 0) {
							Core.getCore().getInstance().error("[CleanroomGenerator] Invalid height '" + tokens[i] + "'. Using 64 instead.");
							height = 64;
						}
						final String[] materialTokens = tokens[(i + 1)].split("[:]", 2);
						byte dataValue = 0;
						if (materialTokens.length == 2) {
							try {
								dataValue = Byte.parseByte(materialTokens[1]);
							} catch (final Exception e) {
								Core.getCore().getInstance().error("[CleanroomGenerator] Invalid Data Value '" + materialTokens[1] + "'. Defaulting to 0.");
								dataValue = 0;
							}
						}
						Material mat = Material.matchMaterial(materialTokens[0]);
						if (mat == null) {
							try {
								mat = Material.getMaterial(Integer.parseInt(materialTokens[0]));
							} catch (final Exception e) {}
							if (mat == null) {
								Core.getCore().getInstance().error("[CleanroomGenerator] Invalid Block ID '" + materialTokens[0] + "'. Defaulting to stone.");
								mat = Material.STONE;
							}
						}
						if (!mat.isBlock()) {
							Core.getCore().getInstance().error("[CleanroomGenerator] Error, '" + materialTokens[0] + "' is not a block. Defaulting to stone.");
							mat = Material.STONE;
						}
						if (y + height > layer.length) {
							final short[] newLayer = new short[Math.max(y + height, layer.length * 2)];
							System.arraycopy(layer, 0, newLayer, 0, y);
							layer = newLayer;
							if (layerDataValues != null) {
								final byte[] newLayerDataValues = new byte[Math.max(y + height, layerDataValues.length * 2)];
								System.arraycopy(layerDataValues, 0, newLayerDataValues, 0, y);
								layerDataValues = newLayerDataValues;
							}
						}
						Arrays.fill(layer, y, y + height, (short) mat.getId());
						if (dataValue != 0) {
							if (layerDataValues == null) {
								layerDataValues = new byte[layer.length];
							}
							Arrays.fill(layerDataValues, y, y + height, dataValue);
						}
						y += height;
					}
				}
				if (layer.length > y) {
					final short[] newLayer = new short[y];
					System.arraycopy(layer, 0, newLayer, 0, y);
					layer = newLayer;
				}
				if ((layerDataValues != null) && (layerDataValues.length > y)) {
					final byte[] newLayerDataValues = new byte[y];
					System.arraycopy(layerDataValues, 0, newLayerDataValues, 0, y);
					layerDataValues = newLayerDataValues;
				}
			} catch (final Exception e) {
				Core.getCore().getInstance().error("[CleanroomGenerator] Error parsing CleanroomGenerator ID '" + id + "'. using defaults '64,1': " + e.toString());
				e.printStackTrace();
				layerDataValues = null;
				layer = new short[65];
				layer[0] = ((short) Material.BEDROCK.getId());
				Arrays.fill(layer, 1, 65, (short) Material.STONE.getId());
			}
		} else {
			layerDataValues = null;
			layer = new short[65];
			layer[0] = ((short) Material.BEDROCK.getId());
			Arrays.fill(layer, 1, 65, (short) Material.STONE.getId());
		}
	}
	
	@Override
	public short[][] generateExtBlockSections(final World world, final Random random, final int x, final int z, final BiomeGrid biomes) {
		final int maxHeight = world.getMaxHeight();
		if (layer.length > maxHeight) {
			Core.getCore()
			        .getInstance()
			        .error("[CleanroomGenerator] Error, chunk height " + layer.length + " is greater than the world max height (" + maxHeight
			                + "). Trimming to world max height.");
			final short[] newLayer = new short[maxHeight];
			System.arraycopy(layer, 0, newLayer, 0, maxHeight);
			layer = newLayer;
		}
		final short[][] result = new short[maxHeight / 16][];
		for (int i = 0; i < layer.length; i += 16) {
			result[(i >> 4)] = new short[4096];
			for (int y = 0; y < Math.min(16, layer.length - i); y++) {
				Arrays.fill(result[(i >> 4)], y * 16 * 16, (y + 1) * 16 * 16, layer[(i + y)]);
			}
		}
		return result;
	}
	
	@Override
	public List<BlockPopulator> getDefaultPopulators(final World world) {
		if (layerDataValues != null) {
			return Arrays.asList(new BlockPopulator[] { new CoreCleanroomBlockPopulator(layerDataValues) });
		}
		return new ArrayList<>();
	}
	
	@Override
	public Location getFixedSpawnLocation(final World world, final Random random) {
		if (!world.isChunkLoaded(0, 0)) {
			world.loadChunk(0, 0);
		}
		if ((world.getHighestBlockYAt(0, 0) <= 0) && (world.getBlockAt(0, 0, 0).getType() == Material.AIR)) {
			return new Location(world, 0.0D, 64.0D, 0.0D);
		}
		return new Location(world, 0.0D, world.getHighestBlockYAt(0, 0), 0.0D);
		
	}
	
}
