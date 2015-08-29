package me.MiniDigger.CraftCore.Bar;

import java.lang.reflect.Field;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Bar.Laser;
import net.minecraft.server.v1_8_R3.DataWatcher;
import net.minecraft.server.v1_8_R3.EntityInsentient;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityMetadata;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityLiving;

public class CoreLaser implements Laser {

	private final int			entityId;
	private final DataWatcher	dataWatcher;
	private List<Entity>		targets;

	private final Location location;

	public CoreLaser(final Location location) {
		entityId = Core.getCore().getRandomUtil().nextInt(Integer.MAX_VALUE);
		dataWatcher = new DataWatcher(null);
		byte data = (byte) calcData(0, 0, false); // onFire
		data = (byte) calcData(data, 1, false); // Crouched
		data = (byte) calcData(data, 3, false); // Sprinting
		data = (byte) calcData(data, 4, false); // Eating/Drinking/Block
		data = (byte) calcData(data, 5, true); // Invisible
		dataWatcher.a(0, data);
		dataWatcher.a(6, 20F);
		int type = calcType(0, 2, false); // Is Elderly
		type = calcType(type, 4, false); // Is retracting spikes
		dataWatcher.a(16, type);
		this.location = location;
	}

	private void sendPacket(final Packet<?> packet) {
		for (final Player player : Bukkit.getOnlinePlayers()) {
			((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
		}
	}

	private void sendPacket(final Player player, final Packet<?> packet) {
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
	}

	private void spawn(final Player show) {
		try {
			final PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving();
			set(packet, "a", entityId);
			set(packet, "b", 68);
			set(packet, "c", toFixedPointNumber(location.getX()));
			set(packet, "d", toFixedPointNumber(location.getY()));
			set(packet, "e", toFixedPointNumber(location.getZ()));
			set(packet, "f", (int) toPackedByte(location.getYaw()));
			set(packet, "g", (int) toPackedByte(location.getPitch()));
			set(packet, "h", (int) toPackedByte(location.getPitch()));
			set(packet, "i", (byte) 0);
			set(packet, "j", (byte) 0);
			set(packet, "k", (byte) 0);
			set(packet, "l", dataWatcher);
			if (show == null) {
				sendPacket(packet);
			} else {
				sendPacket(show, packet);
			}
		}
		catch (final Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setTarget(final LivingEntity target) {
		try {
			spawn(null);
			final PacketPlayOutEntityMetadata packet = new PacketPlayOutEntityMetadata();
			set(packet, "a", entityId);
			dataWatcher.a(17, ((CraftEntity) target).getHandle().getId());
			set(packet, "b", dataWatcher.b());
			sendPacket(packet);
		}
		catch (final Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setTarget(final Player show, final LivingEntity target) {
		try {
			spawn(show);
			final PacketPlayOutEntityMetadata packet = new PacketPlayOutEntityMetadata();
			set(packet, "a", entityId);
			dataWatcher.a(17, ((CraftEntity) target).getHandle().getId());
			set(packet, "b", dataWatcher.b());
			sendPacket(show, packet);
		}
		catch (final Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void despawn(final Player show) {
		final PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(new int[] { entityId });
		if (show == null) {
			sendPacket(packet);
		} else {
			sendPacket(show, packet);
		}
		for (final Entity e : targets) {
			e.remove();
		}
	}

	private int calcData(final int data, final int id, final boolean flag) {
		if (flag) {
			return Integer.valueOf(data | 1 << id);
		} else {
			return Integer.valueOf(data & ~(1 << id));
		}
	}

	private int calcType(final int type, final int id, final boolean flag) {
		if (flag) {
			return Integer.valueOf(type | id);
		} else {
			return Integer.valueOf(type & ~id);
		}
	}

	private byte toPackedByte(final float f) {
		return (byte) ((int) (f * 256.0F / 360.0F));
	}

	private int toFixedPointNumber(final Double d) {
		return (int) (d * 32D);
	}

	private void set(final Object instance, final String name, final Object value) throws Exception {
		final Field field = instance.getClass().getDeclaredField(name);
		field.setAccessible(true);
		field.set(instance, value);
	}

	@Override
	public void setTarget(final Location target) {
		final CraftEntity e = (CraftEntity) target.getWorld().spawnEntity(target, EntityType.BAT);
		e.getHandle().b(true);// Silent
		e.getHandle().setInvisible(true);
		((EntityInsentient) e.getHandle()).k(true);// No ai
		setTarget((LivingEntity) e);
		targets.add(e);
	}

	@Override
	public void setTarget(final Player show, final Location target) {
		final CraftEntity e = (CraftEntity) target.getWorld().spawnEntity(target, EntityType.BAT);
		e.getHandle().b(true);// Silent
		e.getHandle().setInvisible(true);
		((EntityInsentient) e.getHandle()).k(true);// No ai
		setTarget(show, (LivingEntity) e);
		targets.add(e);
	}
}