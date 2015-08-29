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
package me.MiniDigger.CraftCore.Dependencies;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Dependencies.Dependency;
import me.MiniDigger.Core.Dependencies.DependencyHanlder;
import me.MiniDigger.Core.Lang.LogLevel;

import me.MiniDigger.CraftCore.Lang.MSG;

public class CoreDependencyHandler implements DependencyHanlder {

	private static final int BYTE_SIZE = 1024;

	private final List<Dependency> dependencies = new ArrayList<Dependency>();

	public CoreDependencyHandler() {
		dependencies.add(new CoreDependency("ProtocolLib", "187"));
		dependencies.add(new CoreDependency("PermissionsEx", "1.23.1"));
		dependencies.add(new CoreDependency("NametagEdit", "19"));
		dependencies.add(new CoreDependency("HolographicDisplays", "2.1.10"));
		dependencies.add(new CoreDependency("EffectLib", "3.4"));
		dependencies.add(new CoreDependency("Citizens", "2.0.16"));
		dependencies.add(new CoreDependency("LibsDisguises", "3.6.3"));
	}

	private String check(final Dependency d) {
		final File f = new File(Core.getCore().getInstance().getDataFolder().getParent());
		for (final File p : f.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(final File dir, final String name) {
				return name.contains(".jar");
			}
		})) {
			if (p.getName().startsWith(d.getName())) {
				return p.getName();
			}
		}
		return "true";
	}

	@Override
	public void check() {
		for (final Dependency d : dependencies) {
			final String fullName = check(d);

			Core.getCore().getInstance().debug("check update for dependencie " + d.getFullName() + ", result: " + fullName);

			if (fullName.equals("true")) {
				download(d);
				continue;
			}

			if (!fullName.equals(d.getFullName() + ".jar")) {
				final File f = new File(Core.getCore().getInstance().getDataFolder().getParent());
				try {
					final File old = new File(f, fullName);
					old.delete();
					old.deleteOnExit();
					Core.getCore().getInstance().debug("Deleting old file: " + old.getName());
				}
				catch (final Exception ex) {
					Core.getCore().getInstance().debug("failed to delete old");
				}
				download(d);
				continue;
			}
		}
	}

	private void download(final Dependency d) {
		saveFile(new File(Core.getCore().getInstance().getDataFolder().getParent()), d.getFullName() + ".jar", "http://api.minidigger.me/dependencies/" + d.getFullName() + ".jar");
	}

	private void saveFile(final File folder, final String file, final String link) {
		if (!folder.exists()) {
			folder.mkdir();
		}
		BufferedInputStream in = null;
		FileOutputStream fout = null;
		try {
			// Download the file
			final URL url = new URL(link);
			final int fileLength = url.openConnection().getContentLength();
			in = new BufferedInputStream(url.openStream());
			fout = new FileOutputStream(folder.getAbsolutePath() + File.separator + file);

			final byte[] data = new byte[BYTE_SIZE];
			int count;

			long downloaded = 0;
			while ((count = in.read(data, 0, BYTE_SIZE)) != -1) {
				downloaded += count;
				fout.write(data, 0, count);
				final int percent = (int) ((downloaded * 100) / fileLength);
				if (((percent % 10) == 0)) {
					Core.getCore().getInstance().info("Downloading dependency file " + file + ": " + percent + "% of " + fileLength + " bytes.");
				}
			}

			Core.getCore().getInstance().info("Finished download of dependency file " + file);
			if (in != null) {
				in.close();
			}
			if (fout != null) {
				fout.close();
			}
		}
		catch (final Exception ex) {
			Core.getCore().getInstance().error("The dependency-updater tried to download a new update for " + file + ", but was unsuccessful. " + ex.getMessage());
			if (ex.getClass().getName().contains("FileNotFound")) {
				Core.getCore().getInstance().error("Looks like it was disabled");
			} else {
				MSG.stacktrace(LogLevel.DEBUG, ex);
			}
		}
		finally {
			try {
				if (in != null) {
					in.close();
				}
				if (fout != null) {
					fout.close();
				}
			}
			catch (final Exception ex) {}
		}
	}
}
