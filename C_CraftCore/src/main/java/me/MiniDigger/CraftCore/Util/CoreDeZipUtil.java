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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Util.DeZipUtil;

public class CoreDeZipUtil implements DeZipUtil {
	
	@Override
	public void UnzipToDirectory(final String zipFilePath, final String outputDirectory) {
		FileInputStream fileInputStream = null;
		ZipInputStream zipInputStream = null;
		FileOutputStream fileOutputStream = null;
		BufferedOutputStream bufferedOutputStream = null;
		BufferedInputStream bufferedInputStream = null;
		
		try {
			fileInputStream = new FileInputStream(zipFilePath);
			bufferedInputStream = new BufferedInputStream(fileInputStream);
			zipInputStream = new ZipInputStream(bufferedInputStream);
			
			ZipEntry entry;
			while ((entry = zipInputStream.getNextEntry()) != null) {
				final byte[] buffer = new byte[2048];
				
				final File file = new File(outputDirectory + File.separator + entry.getName());
				file.mkdirs();
				
				fileOutputStream = new FileOutputStream(outputDirectory + File.separator + entry.getName());
				bufferedOutputStream = new BufferedOutputStream(fileOutputStream, buffer.length);
				int size;
				while ((size = zipInputStream.read(buffer, 0, buffer.length)) != -1) {
					bufferedOutputStream.write(buffer, 0, size);
				}
				
				bufferedOutputStream.flush();
				bufferedOutputStream.close();
				fileOutputStream.flush();
				fileOutputStream.close();
			}
			
			zipInputStream.close();
			bufferedInputStream.close();
			fileInputStream.close();
		} catch (final IOException e) {
			e.printStackTrace();
			
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (final IOException e1) {
					e1.printStackTrace();
				}
			}
			
			if (bufferedInputStream != null) {
				try {
					bufferedInputStream.close();
				} catch (final IOException e1) {
					e1.printStackTrace();
				}
			}
			
			if (zipInputStream != null) {
				try {
					zipInputStream.close();
				} catch (final IOException e1) {
					e1.printStackTrace();
				}
			}
			
			if (fileOutputStream != null) {
				try {
					fileOutputStream.close();
				} catch (final IOException e1) {
					e1.printStackTrace();
				}
			}
			
			if (bufferedOutputStream != null) {
				try {
					bufferedOutputStream.close();
				} catch (final IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
	private final int	BUFFER_SIZE	= 4096;
	
	private void extractFile(final ZipInputStream in, final File outdir, final String name) throws IOException {
		final byte[] buffer = new byte[BUFFER_SIZE];
		final File f = new File(outdir, name);
		f.getParentFile().mkdirs();
		final File p = f.getParentFile();
		p.setWritable(true);
		try {
			f.createNewFile();
		} catch (final Exception ex) {
			Core.getCore().getInstance().debug("failed");
		}
		final BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(f));
		int count = -1;
		while ((count = in.read(buffer)) != -1) {
			out.write(buffer, 0, count);
		}
		out.close();
	}
	
	private void mkdirs(final File outdir, final String path) {
		final File d = new File(outdir, path);
		if (!d.exists()) {
			d.mkdirs();
		}
	}
	
	private String dirpart(final String name) {
		final int s = name.lastIndexOf(File.separatorChar);
		return s == -1 ? null : name.substring(0, s);
	}
	
	@Override
	public void extract(final File zipfile, final File outdir) {
		try {
			final ZipInputStream zin = new ZipInputStream(new FileInputStream(zipfile));
			ZipEntry entry;
			String name, dir;
			while ((entry = zin.getNextEntry()) != null) {
				name = entry.getName();
				if (entry.isDirectory()) {
					mkdirs(outdir, name);
					continue;
				}
				dir = dirpart(name);
				if (dir != null) {
					mkdirs(outdir, dir);
				}
				try {
					extractFile(zin, outdir, name);
				} catch (final Exception ex) {
					// ex.printStackTrace();
					Core.getCore().getInstance().debug("Error: " + ex.getMessage());
					ex.printStackTrace();
				}
			}
			zin.close();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}
	
}
