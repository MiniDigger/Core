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
 * Written by Martin Benndorf <admin@minidigger.me>, 2013-2014 and others
 */
package me.MiniDigger.CraftCore.Holo;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import me.MiniDigger.Core.Holo.Holo;
import me.MiniDigger.Core.User.User;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import com.comphenix.packetwrapper.WrapperPlayServerAttachEntity;
import com.comphenix.packetwrapper.WrapperPlayServerEntityDestroy;
import com.comphenix.packetwrapper.WrapperPlayServerEntityMetadata;
import com.comphenix.packetwrapper.WrapperPlayServerEntityTeleport;
import com.comphenix.packetwrapper.WrapperPlayServerSpawnEntity;
import com.comphenix.packetwrapper.WrapperPlayServerSpawnEntityLiving;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;

public class CoreHolo implements Holo {
	
	private final int	   id;
	private final Location	loc;
	private final double	dy;
	private String	       name;
	private final double	horeOffset	= 100;
	
	public CoreHolo(final int id, final Location loc, final double dy, final String name) {
		this.id = id;
		this.loc = loc;
		this.dy = dy;
		this.name = name;
	}
	
	@Override
	public void show(final User u) {
		final Player p = u.getPlayer();
		final WrapperPlayServerSpawnEntityLiving horse = new WrapperPlayServerSpawnEntityLiving();
		
		horse.setEntityID(id);
		
		horse.setType(EntityType.HORSE);
		
		horse.setX(loc.getX());
		horse.setY(loc.getY() + dy + horeOffset);
		horse.setZ(loc.getZ());
		
		final WrappedDataWatcher wdw = new WrappedDataWatcher();
		
		wdw.setObject(10, name);
		wdw.setObject(11, (byte) 1);
		
		wdw.setObject(12, -1700000);
		
		horse.setMetadata(wdw);
		
		try {
			ProtocolLibrary.getProtocolManager().sendServerPacket(p, horse.getHandle());
		} catch (final InvocationTargetException e2) {
			e2.printStackTrace();
		}
		
		final WrapperPlayServerSpawnEntity skull = new WrapperPlayServerSpawnEntity();
		
		skull.setEntityID(id + 1);
		
		skull.setType(66);
		
		skull.setX(loc.getX());
		skull.setY(loc.getY() + dy + horeOffset);
		skull.setZ(loc.getZ());
		
		try {
			ProtocolLibrary.getProtocolManager().sendServerPacket(p, skull.getHandle());
		} catch (final InvocationTargetException e1) {
			e1.printStackTrace();
		}
		
		final WrapperPlayServerAttachEntity attach = new WrapperPlayServerAttachEntity();
		
		attach.setEntityId(id);
		attach.setVehicleId(id + 1);
		
		try {
			ProtocolLibrary.getProtocolManager().sendServerPacket(p, attach.getHandle());
		} catch (final InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void hide(final User u) {
		final WrapperPlayServerEntityDestroy destroy = new WrapperPlayServerEntityDestroy();
		
		final ArrayList<Integer> entities = new ArrayList<>();
		
		entities.add(id);
		entities.add(id + 1);
		
		destroy.setEntities(entities);
		
		try {
			ProtocolLibrary.getProtocolManager().sendServerPacket(u.getPlayer(), destroy.getHandle());
		} catch (final InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void setLocation(final User u, final Location loc) {
		final WrapperPlayServerEntityTeleport teleport = new WrapperPlayServerEntityTeleport();
		
		teleport.setEntityID(id + 1);
		
		teleport.setX(loc.getX());
		teleport.setY(loc.getY() + horeOffset + dy);
		teleport.setZ(loc.getZ());
		
		try {
			ProtocolLibrary.getProtocolManager().sendServerPacket(u.getPlayer(), teleport.getHandle());
		} catch (final InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void setMessage(final User u, final String msg) {
		name = msg;
		
		final WrapperPlayServerEntityMetadata eMeta = new WrapperPlayServerEntityMetadata();
		eMeta.setEntityId(id);
		
		final WrappedDataWatcher wdw = new WrappedDataWatcher();
		wdw.setObject(10, name);
		
		eMeta.setEntityMetadata(wdw.getWatchableObjects());
		
		try {
			ProtocolLibrary.getProtocolManager().sendServerPacket(u.getPlayer(), eMeta.getHandle());
		} catch (final InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
}
