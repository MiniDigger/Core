/*******************************************************************************
 * Test
 *******************************************************************************/
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
 */
package me.MiniDigger.CraftCore.Update;

import java.util.regex.Pattern;

import me.MiniDigger.Core.Update.UpdateHandler.PluginVersion;
import me.MiniDigger.Core.Update.UpdateType;

public class CorePluginVersion implements PluginVersion {
	
	String	raw;
	int	   major;
	int	   minor;
	int	   commit;
	int	   build;
	
	public CorePluginVersion(final String raw) {
		this.raw = raw;
		final String[] s = raw.split(Pattern.quote("."));
		major = Integer.parseInt(s[0]);
		minor = Integer.parseInt(s[1]);
		commit = Integer.parseInt(s[2].split("-")[0].replace(" ", ""));
		try {
			build = Integer.parseInt(s[2].split("-")[1]);
		} catch (final Exception ex) {
			build = 0;
		}
	}
	
	@Override
	public boolean isNewer(final PluginVersion other, final UpdateType type) {
		switch (type) {
		case FORCE:
			return true;
		case SNAPSHOT:
			if (isNewer(other, UpdateType.COMMIT) || build > other.getBuild()) {
				return true;
			}
			if (isNewerOrEqual(other, UpdateType.COMMIT) && build > other.getBuild()) {
				return true;
			}
			break;
		case COMMIT:
			if (isNewer(other, UpdateType.MINOR) || commit > other.getCommit()) {
				return true;
			}
			if (isNewerOrEqual(other, UpdateType.MINOR) && commit > other.getCommit()) {
				return true;
			}
			break;
		case MINOR:
			if (isNewer(other, UpdateType.MAJOR) || minor > other.getMinor()) {
				return true;
			}
			if (isNewerOrEqual(other, UpdateType.MAJOR) && minor > other.getMinor()) {
				return true;
			}
			break;
		case MAJOR:
			if (major > other.getMajor()) {
				return true;
			}
			break;
		case NEVER:
			return false;
		}
		return false;
	}
	
	@Override
	public boolean isNewerOrEqual(final PluginVersion other, final UpdateType type) {
		switch (type) {
		case FORCE:
			return true;
		case SNAPSHOT:
			if (isNewerOrEqual(other, UpdateType.COMMIT) && build >= other.getBuild()) {
				return true;
			}
			break;
		case COMMIT:
			if (isNewerOrEqual(other, UpdateType.MINOR) && commit >= other.getCommit()) {
				return true;
			}
			break;
		case MINOR:
			if (isNewerOrEqual(other, UpdateType.MAJOR) && minor >= other.getMinor()) {
				return true;
			}
			break;
		case MAJOR:
			if (major >= other.getMajor()) {
				return true;
			}
			break;
		case NEVER:
			return false;
		}
		return false;
	}
	
	@Override
	public void setRaw(final String raw) {
		this.raw = raw;
	}
	
	@Override
	public void setMajor(final int major) {
		this.major = major;
	}
	
	@Override
	public void setMinor(final int minor) {
		this.minor = minor;
	}
	
	@Override
	public void setCommit(final int commit) {
		this.commit = commit;
	}
	
	@Override
	public void setBuild(final int build) {
		this.build = build;
	}
	
	@Override
	public String getRaw() {
		return raw;
	}
	
	@Override
	public int getMajor() {
		return major;
	}
	
	@Override
	public int getMinor() {
		return minor;
	}
	
	@Override
	public int getCommit() {
		return commit;
	}
	
	@Override
	public int getBuild() {
		return build;
	}
}
