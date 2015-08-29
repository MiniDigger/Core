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
package me.MiniDigger.CraftCore.AddOn;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.bukkit.plugin.InvalidPluginException;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.AddOn.AddOn;
import me.MiniDigger.Core.AddOn.AddOnClassLoader;
import me.MiniDigger.Core.Lang.LangKeyType;
import me.MiniDigger.CraftCore.Lang.MSG;

/**
 * A ClassLoader for plugins, to allow shared classes across multiple plugins
 * <br>
 * Idea copied from the bukkit project at bukkit.org
 */
public class CoreAddOnClassLoader extends URLClassLoader implements AddOnClassLoader {

	private final Map<String, Class<?>>	classes	= new HashMap<String, Class<?>>();
	private final CoreAddOn				plugin;

	public CoreAddOnClassLoader(final ClassLoader parent, final String main, final URL url) throws InvalidPluginException, MalformedURLException {
		super(new URL[] { url }, parent);

		try {
			Class<?> jarClass;
			try {
				jarClass = Class.forName(main, true, this);
			}
			catch (final ClassNotFoundException ex) {
				throw new InvalidPluginException(MSG.msg(LangKeyType.AddOn.ERROR_NO_MAIN, main), ex);
			}

			Class<? extends CoreAddOn> pluginClass;
			try {
				pluginClass = jarClass.asSubclass(CoreAddOn.class);
			}
			catch (final ClassCastException ex) {
				throw new InvalidPluginException(MSG.msg(LangKeyType.AddOn.ERROR_NO_EXTEND, main), ex);
			}

			plugin = pluginClass.newInstance();
		}
		catch (final IllegalAccessException ex) {
			throw new InvalidPluginException(MSG.msg(LangKeyType.AddOn.ERROR_NO_CONSTRUCTOR, main), ex);
		}
		catch (final InstantiationException ex) {
			throw new InvalidPluginException(MSG.msg(LangKeyType.AddOn.ERROR_ABNORMAL_TYPE, main), ex);
		}
	}

	@Override
	protected Class<?> findClass(final String name) throws ClassNotFoundException {
		return findClass(name, true);
	}

	@Override
	public Class<?> findClass(final String name, final boolean checkGlobal) throws ClassNotFoundException {
		if (name.startsWith("org.bukkit.") || name.startsWith("net.minecraft.")) {
			throw new ClassNotFoundException(name);
		}
		Class<?> result = classes.get(name);

		if (result == null) {
			if (checkGlobal) {
				result = Core.getCore().getAddOnHandler().getClassByName(name);
			}

			if (result == null) {
				result = super.findClass(name);

				if (result != null) {
					Core.getCore().getAddOnHandler().setClass(name, result);
				}
			}

			classes.put(name, result);
		}

		return result;
	}

	@Override
	public Set<String> getClasses() {
		return classes.keySet();
	}

	@Override
	public AddOn getAddOn() {
		return plugin;
	}
}
