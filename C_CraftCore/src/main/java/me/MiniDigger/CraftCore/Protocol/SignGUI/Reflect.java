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
package me.MiniDigger.CraftCore.Protocol.SignGUI;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class Reflect {
	
	public static void set(final Class<?> c, final String field, final Object value) throws Exception {
		getField(c, field).set(null, value);
	}
	
	public static void set(final Object o, final String field, final Object value) throws Exception {
		set(o, o.getClass(), field, value);
	}
	
	public static void set(final Object o, final Class<?> c, final String field, final Object value) throws Exception {
		getField(c, field).set(o, value);
	}
	
	public static <T> T get(final Class<?> c, final String field) throws Exception {
		return (T) getField(c, field).get(null);
	}
	
	public static <T> T get(final Object o, final String field) throws Exception {
		return (T) getField(o.getClass(), field).get(o);
	}
	
	public static <T> T get(final Object o, final Class<?> c, final String field) throws Exception {
		return (T) getField(c, field).get(o);
	}
	
	public static Field getField(final Class<?> c, final String field) throws Exception {
		Field f;
		try {
			f = c.getDeclaredField(field);
		} catch (final NoSuchFieldException e) {
			f = c.getField(field);
		}
		if (f == null) {
			throw new NoSuchFieldException();
		}
		f.setAccessible(true);
		return f;
	}
	
	// ----------------------------------- Methods
	// ----------------------------------- \\
	
	public static <T> T invoke(final Object o, final String method, final Object arg, final Class<?> argClass) throws Exception {
		return invoke(o, method, new Object[] { arg }, new Class<?>[] { argClass });
	}
	
	public static <T> T invoke(final Object o, final String method, final Object... args) throws Exception {
		return invoke(o, method, args, getClasses(args));
	}
	
	public static <T> T invoke(final Class<?> c, final String method, final Object... args) throws Exception {
		return invoke(c, method, getClasses(args), args);
	}
	
	public static <T> T invoke(final Object obj, final String method, final Object[] args, final Class<?>[] classes) throws Exception {
		return (T) getMethod(obj, method, classes).invoke(obj, args);
	}
	
	public static <T> T invoke(final Class<?> clazz, final String method, final Class<?>[] argClasses, final Object[] args) throws Exception {
		return (T) getMethod(clazz, method, argClasses).invoke(null, args);
	}
	
	public static Method getMethod(final Object obj, final String method, final Class<?>... classes) throws Exception {
		return getMethod(obj.getClass(), method, classes);
	}
	
	public static Method getMethod(final Class<?> clazz, final String method, final Class<?>... classes) throws Exception {
		Method m;
		try {
			m = clazz.getDeclaredMethod(method, classes);
		} catch (final NoSuchMethodException e) {
			m = clazz.getMethod(method, classes);
		}
		if (m == null) {
			throw new NoSuchMethodException();
		}
		m.setAccessible(true);
		return m;
	}
	
	public static Class<?>[] getClasses(final Object... o) {
		final List<Class<?>> params = new ArrayList<Class<?>>();
		for (final Object c : o) {
			if (c.getClass() == Integer.class) {
				params.add(Integer.TYPE);
			} else if (c.getClass() == Double.class) {
				params.add(Double.TYPE);
			} else {
				params.add(c.getClass());
			}
		}
		return params.toArray(new Class<?>[o.length]);
	}
	
}
