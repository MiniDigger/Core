package me.MiniDigger.CraftCore.Util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import me.MiniDigger.Core.Util.ReflectionUtil;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;

public class CoreReflectionUtil implements ReflectionUtil {
	
	@Override
	public Class<?> getCraftClass(String ClassName) {
		String name = Bukkit.getServer().getClass().getPackage().getName();
		String version = name.substring(name.lastIndexOf('.') + 1) + ".";
		String className = "net.minecraft.server." + version + ClassName;
		Class<?> c = null;
		try {
			c = Class.forName(className);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}
	
	@Override
	public Object getHandle(Entity entity) {
		try {
			return getMethod(entity.getClass(), "getHandle").invoke(entity);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public Object getHandle(World world) {
		try {
			return getMethod(world.getClass(), "getHandle").invoke(world);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public Field getField(Class<?> cl, String field_name) {
		try {
			return cl.getDeclaredField(field_name);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public Method getMethod(Class<?> cl, String method, Class<?>... args) {
		for (Method m : cl.getMethods()) {
			if (m.getName().equals(method) && ClassListEqual(args, m.getParameterTypes())) {
				return m;
			}
		}
		return null;
	}
	
	@Override
	public Method getMethod(Class<?> cl, String method) {
		for (Method m : cl.getMethods()) {
			if (m.getName().equals(method)) {
				return m;
			}
		}
		return null;
	}
	
	@Override
	public boolean ClassListEqual(Class<?>[] l1, Class<?>[] l2) {
		boolean equal = true;
		if (l1.length != l2.length) return false;
		for (int i = 0; i < l1.length; i++) {
			if (l1[i] != l2[i]) {
				equal = false;
				break;
			}
		}
		return equal;
	}
	
}
