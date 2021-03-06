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
package me.MiniDigger.CraftCore.Map;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Game.GameType;
import me.MiniDigger.Core.Lang.LogLevel;
import me.MiniDigger.Core.Map.MapData;
import me.MiniDigger.Core.Map.MapHandler;

import me.MiniDigger.CraftCore.Lang.MSG;

public class CoreMapHandler implements MapHandler {

	private final ArrayList<MapData>	maps	= new ArrayList<>();
	private final File					mapFolder;
	private final File					mapConfig;
	private final List<String>			mapNames;

	private final FileConfiguration con;

	public CoreMapHandler() {
		mapFolder = new File((Core.getCore().getInstance()).getConfig().getString("mapFolder"));
		mapConfig = new File(mapFolder, "maps.yml");
		con = YamlConfiguration.loadConfiguration(mapConfig);
		mapNames = con.getStringList("maps");
		final File warning = new File(mapFolder, "DO_NOT_EDIT_FILES_IN_THIS_DIRECTORY_OR_CORE_WILL_LIKLY_FAIL_RUNNING");
		if (!warning.exists()) {
			warning.mkdir();
		}
	}

	@Override
	public List<String> getMapNames() {
		return mapNames;
	}

	@Override
	public void addMap(final MapData data) {
		if (data.getName() == null) {
			Core.getCore().getInstance().debug("Could not add map: Name was null");
			return;
		}

		maps.add(data);
	}

	@Override
	public MapData getMap(final String name) {
		if (name == null) {
			return null;
		}

		for (final MapData data : maps) {
			if (data == null) {
				continue;
			}

			if (data.getName().equals(name)) {
				return data;
			}
		}
		for (final MapData data : maps) {
			if (data == null) {
				continue;
			}

			if (data.getOldName() == null) {
				continue;
			}

			if (data.getOldName().equals(name)) {
				return data;
			}
		}
		return null;
	}

	@Override
	public List<MapData> getMaps(final List<DyeColor> types) {
		final List<MapData> result = new ArrayList<>();
		for (final MapData data : maps) {
			boolean breaked = false;
			for (final DyeColor type : types) {
				if (data.getLocs(type) == null) {
					breaked = true;
					break;
				}
			}

			if (!breaked) {
				result.add(data);
			}
		}
		return result;
	}

	@Override
	public String getAuthor(final String map) {
		return con.getString(map + ".author");
	}

	@Override
	public String getName(final String map) {
		return con.getString(map + ".name");
	}

	@Override
	public ArrayList<String> loadMapConfig(final GameType type) {
		final List<String> maps = con.getStringList("maps");
		final List<String> matched = new ArrayList<>();

		for (final String s : maps) {
			final List<String> types = con.getStringList(s + ".gametypes");
			if (types.contains(type.name())) {
				matched.add(s);
			}
		}

		String map1 = null;
		String map2 = null;
		String map3 = null;
		try {
			map1 = matched.get(Core.getCore().getRandomUtil().nextInt(matched.size()));
			matched.remove(map1);

			map2 = matched.get(Core.getCore().getRandomUtil().nextInt(matched.size()));
			matched.remove(map2);

			map3 = matched.get(Core.getCore().getRandomUtil().nextInt(matched.size()));
			matched.remove(map3);
		}
		catch (final Exception ex) {

		}

		if (map1 == null) {
			Core.getCore().getInstance().error("No Map found for GameType " + type);
			return new ArrayList<>();
		}

		final ArrayList<String> list = new ArrayList<>();
		if (map1 != null) {
			list.add(map1);
		}
		if (map2 != null) {
			list.add(map2);
		}
		if (map3 != null) {
			list.add(map3);
		}
		return list;
	}

	@Override
	public List<GameType> getGameTypes(final String name) {
		final List<GameType> result = new ArrayList<GameType>();

		for (final String s : con.getStringList(name + ".gametypes")) {
			try {
				result.add(GameType.valueOf(s));
			}
			catch (final Exception ex) {

			}
		}

		return result;
	}

	@Override
	public void unload(final MapData map) {
		maps.remove(map);
	}

	@Override
	public void fixMap(final MapData map) {
		String name = map.getOldName();
		if (name == null) {
			name = map.getName();
		}

		try {
			final OutputStream out = new FileOutputStream(new File(new File(Bukkit.getWorldContainer(), map.getName()), "map.yml"));
			final FileInputStream fin = new FileInputStream(new File(Core.getCore().getInstance().getConfig().getString("mapFolder"), name + ".zip"));
			final BufferedInputStream bin = new BufferedInputStream(fin);
			final ZipInputStream zin = new ZipInputStream(bin);
			ZipEntry ze = null;
			while ((ze = zin.getNextEntry()) != null) {
				if (ze.getName().equals(map.getName() + "/map.yml")) {
					final byte[] buffer = new byte[8192];
					int len;
					while ((len = zin.read(buffer)) != -1) {
						out.write(buffer, 0, len);
					}
					out.close();
					zin.close();
					break;
				}
			}
		}
		catch (final Exception ex) {
			MSG.stacktrace(LogLevel.DEBUG, ex);
		}

		final File mapDataFile = new File(new File(Bukkit.getWorldContainer(), map.getName()), "map.yml");
		final FileConfiguration con = YamlConfiguration.loadConfiguration(mapDataFile);
		map.load(con);
	}
}
