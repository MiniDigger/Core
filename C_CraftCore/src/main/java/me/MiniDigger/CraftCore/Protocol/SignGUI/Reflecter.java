package me.MiniDigger.CraftCore.Protocol.SignGUI;

public class Reflecter {

	private Class<?> clazz = null;
	private Object instance = null;
	
	public Reflecter(Object instance) {
		this.instance = instance;
		this.clazz = instance.getClass();
	}
	
	public Reflecter(Class<?> clazz) {
		this.clazz = clazz;
	}
	
	public Object getInstance() {
		return this.instance;
	}
	
	public Class<?> getClazz() {
		return this.clazz;
	}
	
	public void set(String field, Object value) throws Exception {
		if (instance == null) {
			Reflect.set(clazz, field, value);
		} else {
			Reflect.set(instance, field, value);
		}
	}
	
	public void set(Class<?> clazz, String field, Object value) throws Exception {
		if (instance == null) {
			throw new NullPointerException("Instance is null");
		} else {
			Reflect.set(instance, clazz, field, value);
		}
	}
	
	public <T> T get(String field) throws Exception {
		if (instance == null) {
			return Reflect.get(clazz, field);
		} else {
			return Reflect.get(instance, field);
		}
	}
	
	public <T> T get(Class<?> clazz, String field) throws Exception {
		if (instance == null) {
			throw new NullPointerException("Instance is null");
		} else {
			return Reflect.get(instance, clazz, field);
		}
	}

}
