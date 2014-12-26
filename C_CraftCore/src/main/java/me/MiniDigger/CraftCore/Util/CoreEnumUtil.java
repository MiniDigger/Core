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

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;

import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Util.EnumUtil;

public class CoreEnumUtil implements EnumUtil {
	
	// TODO Enum util
	
	public static FeatureType addFeatureType(final String name, final int id) {
		final FeatureType type = DynamicEnumType.addEnum(FeatureType.class, name, new Class[] {}, new Object[] {});
		try {
			final Field field = Material.class.getDeclaredField("BY_NAME");
			field.setAccessible(true);
			final Object object = field.get(null);
			@SuppressWarnings("unchecked") final Map<String, FeatureType> BY_NAME = (Map<String, FeatureType>) object;
			BY_NAME.put(name, type);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		try {
			final Field field = FeatureType.class.getDeclaredField("byId");
			field.setAccessible(true);
			final Object object = field.get(0);
			final FeatureType[] byId = (FeatureType[]) object;
			byId[id] = type;
			field.set(object, byId);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return type;
	}
	
	public static class DynamicEnumType {
		
		private static Method	reflectionFactory;
		private static Object	reflectionRealFactory;
		private static Method	newconstructorAccesor;
		private static Method	constructorAccesor;
		private static Method	newFieldAccesor;
		private static Method	fieldAccesorSet;
		
		public static void loadReflection() throws NoSuchMethodException, SecurityException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException,
		        InvocationTargetException {
			reflectionFactory = Class.forName("sun.reflect.ReflectionFactory").getDeclaredMethod("getReflectionFactory", new Class[0]);
			
			reflectionFactory.setAccessible(true);
			reflectionRealFactory = reflectionFactory.invoke(null, new Object[0]);
			
			fieldAccesorSet = Class.forName("sun.reflect.FieldAccessor").getDeclaredMethod("set", new Class[] { Object.class, Object.class });
			newFieldAccesor = Class.forName("sun.reflect.ReflectionFactory").getDeclaredMethod("newFieldAccessor", new Class[] { Field.class, Boolean.TYPE });
			constructorAccesor = Class.forName("sun.reflect.ConstructorAccessor").getDeclaredMethod("newInstance", Object[].class);
			
			constructorAccesor.setAccessible(true);
			newconstructorAccesor = Class.forName("sun.reflect.ReflectionFactory").getDeclaredMethod("newConstructorAccessor", new Class[] { Constructor.class });
		}
		
		private static void setFailsafeFieldValue(final Field field, final Object target, final Object value) throws NoSuchFieldException, IllegalAccessException,
		        IllegalArgumentException, InvocationTargetException {
			field.setAccessible(true);
			final Field modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
			int modifiers = modifiersField.getInt(field);
			modifiers &= -17;
			modifiersField.setInt(field, modifiers);
			final Object fieldAccesor = newFieldAccesor.invoke(reflectionRealFactory, new Object[] { field, Boolean.valueOf(false) });
			fieldAccesorSet.invoke(fieldAccesor, new Object[] { target, value });
		}
		
		private static void blankField(final Class<?> enumClass, final String fieldName) throws NoSuchFieldException, IllegalAccessException, IllegalArgumentException,
		        InvocationTargetException {
			for (final Field field : Class.class.getDeclaredFields()) {
				if (field.getName().contains(fieldName)) {
					AccessibleObject.setAccessible(new Field[] { field }, true);
					setFailsafeFieldValue(field, enumClass, null);
					break;
				}
			}
		}
		
		private static void cleanEnumCache(final Class<?> enumClass) throws NoSuchFieldException, IllegalAccessException, IllegalArgumentException,
		        InvocationTargetException {
			blankField(enumClass, "enumConstantDirectory");
			blankField(enumClass, "enumConstants");
		}
		
		private static Object getConstructorAccessor(final Class<?> enumClass, final Class<?>[] additionalParameterTypes) throws NoSuchMethodException,
		        IllegalAccessException, IllegalArgumentException, InvocationTargetException, SecurityException {
			final Class<?>[] parameterTypes = new Class[additionalParameterTypes.length + 2];
			parameterTypes[0] = String.class;
			parameterTypes[1] = Integer.TYPE;
			System.arraycopy(additionalParameterTypes, 0, parameterTypes, 2, additionalParameterTypes.length);
			return newconstructorAccesor.invoke(reflectionRealFactory, enumClass.getDeclaredConstructor(parameterTypes));
		}
		
		public static Object makeEnum(final Class<?> enumClass, final String value, final int ordinal, final Class<?>[] additionalTypes, final Object[] additionalValues)
		        throws Exception {
			final Object[] parms = new Object[additionalValues.length + 2];
			parms[0] = value;
			parms[1] = Integer.valueOf(ordinal);
			System.arraycopy(additionalValues, 0, parms, 2, additionalValues.length);
			
			return enumClass.cast(constructorAccesor.invoke(getConstructorAccessor(enumClass, additionalTypes), new Object[] { parms }));
		}
		
		@SuppressWarnings("unchecked")
		public static <T extends Enum<?>> T addEnum(final Class<T> enumType, final String enumName, final Class<?>[] paramTypes, final Object[] paramValues) {
			if (!Enum.class.isAssignableFrom(enumType)) {
				throw new RuntimeException("class " + enumType + " is not an instance of Enum");
			}
			
			Field valuesField = null;
			final Field[] fields = enumType.getDeclaredFields();
			for (final Field field : fields) {
				if (field.getName().contains("$VALUES")) {
					valuesField = field;
					break;
				}
			}
			AccessibleObject.setAccessible(new Field[] { valuesField }, true);
			try {
				final T[] previousValues = (T[]) valuesField.get(enumType);
				final List<T> values = new ArrayList<T>(Arrays.asList(previousValues));
				
				final T newValue = (T) makeEnum(enumType, enumName, values.size(), paramTypes, paramValues);
				values.add(newValue);
				
				setFailsafeFieldValue(valuesField, null, values.toArray((T[]) Array.newInstance(enumType, 0)));
				
				cleanEnumCache(enumType);
				
				return newValue;
			} catch (final Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage(), e);
			}
		}
	}
}
