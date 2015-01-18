package me.MiniDigger.CraftCore.Dependencies;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Dependencies.Dependency;
import me.MiniDigger.Core.Dependencies.DependencyHanlder;

public class CoreDependencyHandler implements DependencyHanlder {
	
	private static final int	BYTE_SIZE	 = 1024;
	
	private List<Dependency>	dependencies	= new ArrayList<Dependency>();
	
	public CoreDependencyHandler() {
		dependencies.add(new CoreDependency("ProtocolLib", "73"));
		dependencies.add(new CoreDependency("PermissionsEx", "1.23.1"));
		dependencies.add(new CoreDependency("NametagEdit", "3.0"));
		dependencies.add(new CoreDependency("HolographicDisplays", "2.1.3"));
		dependencies.add(new CoreDependency("EffectLib", "3.0"));
	}
	
	private boolean check(Dependency d) {
		return false;
	}
	
	@Override
	public void check() {
		for (Dependency d : dependencies) {
			if (check(d)) {
				download(d);
			}
		}
	}
	
	private void download(Dependency d) {
		saveFile(new File(Core.getCore().getInstance().getDataFolder().getParent()), d.getFullName() + ".jar", "http://api.minidigger.me/dependencies/" + d.getFullName()
		        + ".jar");
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
		} catch (final Exception ex) {
			Core.getCore().getInstance().error("The dependency-updater tried to download a new update for " + file + ", but was unsuccessful. " + ex.getMessage());
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (fout != null) {
					fout.close();
				}
			} catch (final Exception ex) {}
		}
	}
}
