package me.MiniDigger.CraftCore.Protocol.SignGUI;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class Reflect {

	public static void set(Class<?> c, String field, Object value) throws Exception {
		getField(c, field).set(null, value);
	}
	
	public static void set(Object o, String field, Object value) throws Exception {
		set(o, o.getClass(), field, value);
	}
	
	public static void set(Object o, Class<?> c, String field, Object value) throws Exception {
		getField(c, field).set(o, value);
	}
	
	public static <T> T get(Class<?> c, String field) throws Exception {
		return (T) getField(c, field).get(null);
	}
	
	public static <T> T get(Object o, String field) throws Exception {
		return (T) getField(o.getClass(), field).get(o);
	}
	
	public static <T> T get(Object o, Class<?> c, String field) throws Exception {
		return (T) getField(c, field).get(o);
	}
	
	public static Field getField(Class<?> c, String field) throws Exception {
		Field f;
		try {
			f = c.getDeclaredField(field);
		} catch (NoSuchFieldException e) {
			f = c.getField(field);
		}
		if (f == null) throw new NoSuchFieldException();
		f.setAccessible(true);
		return f;
	}
	
//	----------------------------------- Methods -----------------------------------	\\
	
	public static <T> T invoke(Object o, String method, Object arg, Class<?> argClass) throws Exception {
		return invoke(o, method, new Object[] { arg }, new Class<?>[] { argClass });
	}
	
	public static <T> T invoke(Object o, String method, Object... args) throws Exception {
		return invoke(o, method, args, getClasses(args));
	}
	
	public static <T> T invoke(Class<?> c, String method, Object... args) throws Exception {
		return invoke(c, method, getClasses(args), args);
	}
	
	public static <T> T invoke(Object obj, String method, Object[] args, Class<?>[] classes) throws Exception {
		return (T) getMethod(obj, method, classes).invoke(obj, args);
	}
	
	public static <T> T invoke(Class<?> clazz, String method, Class<?>[] argClasses, Object[] args) throws Exception {
		return (T) getMethod(clazz, method, argClasses).invoke(null, args);
	}
	
	public static Method getMethod(Object obj, String method, Class<?>... classes) throws Exception {
		return getMethod(obj.getClass(), method, classes);
	}
	
	public static Method getMethod(Class<?> clazz, String method, Class<?>... classes) throws Exception {
		Method m;
		try {
			m = clazz.getDeclaredMethod(method, classes);
		} catch (NoSuchMethodException e) {
			m = clazz.getMethod(method, classes);
		}
		if (m == null) throw new NoSuchMethodException();
		m.setAccessible(true);
		return m;
	}
	
	public static Class<?>[] getClasses(Object... o) {
		List<Class<?>> params = new ArrayList<Class<?>>();
		for (Object c : o) {
			if (c.getClass() == Integer.class) {
				params.add(Integer.TYPE);
			} else if(c.getClass() == Double.class) {
				params.add(Double.TYPE);
			} else {
				params.add(c.getClass());
			}
		}
		return params.toArray(new Class<?>[o.length]);
	}
	
}