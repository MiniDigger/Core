package me.MiniDigger.CraftCore.Entity;

import java.util.Map;

import me.MiniDigger.Core.Core;
import net.minecraft.server.v1_7_R4.Entity;
import net.minecraft.server.v1_7_R4.EntityTypes;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_7_R4.CraftWorld;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

public enum CoreEntityType {
	ZOMBIE("CoreZombie", 54, CoreZombie.class);
	
	private String	                name;
	private int	                    id;
	private Class<? extends Entity>	clazz;
	
	private CoreEntityType(String name, int id, Class<? extends Entity> clazz) {
		this.name = name;
		this.id = id;
		this.clazz = clazz;
		addToMaps();
	}
	
	public void spawnEntity(Location loc) {
		Entity entity;
		try {
			entity = clazz.getConstructor(org.bukkit.World.class).newInstance(loc.getWorld());
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		entity.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
		((CraftWorld) loc.getWorld()).getHandle().addEntity(entity, SpawnReason.CUSTOM);
	}
	
	@SuppressWarnings("unchecked")
	private void addToMaps() {
		Map<String, Class<? extends Entity>> c = (Map<String, Class<? extends Entity>>) Core.getCore().getReflectionUtil().getPrivateField("c", EntityTypes.class, null);
		Map<Class<? extends Entity>, String> d = (Map<Class<? extends Entity>, String>) Core.getCore().getReflectionUtil().getPrivateField("d", EntityTypes.class, null);
		Map<Integer, Class<? extends Entity>> e = (Map<Integer, Class<? extends Entity>>) Core.getCore().getReflectionUtil()
		        .getPrivateField("e", EntityTypes.class, null);
		Map<Class<? extends Entity>, Integer> f = (Map<Class<? extends Entity>, Integer>) Core.getCore().getReflectionUtil()
		        .getPrivateField("f", EntityTypes.class, null);
		Map<String, Integer> g = (Map<String, Integer>) Core.getCore().getReflectionUtil().getPrivateField("g", EntityTypes.class, null);
		
		if (c.containsKey(name)) {
			c.remove(name);
		}
		if (d.containsKey(clazz)) {
			d.remove(clazz);
		}
		if (e.containsKey(Integer.valueOf(id))) {
			e.remove(Integer.valueOf(id));
		}
		if (f.containsKey(clazz)) {
			f.remove(clazz);
		}
		if (g.containsKey(name)) {
			g.remove(name);
		}
		
		c.put(name, clazz);
		d.put(clazz, name);
		e.put(Integer.valueOf(id), clazz);
		f.put(clazz, Integer.valueOf(id));
		g.put(name, Integer.valueOf(id));
	}
}
