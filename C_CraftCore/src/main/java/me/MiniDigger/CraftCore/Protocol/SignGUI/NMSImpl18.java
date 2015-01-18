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

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

import com.google.common.collect.Lists;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import me.MiniDigger.Core.Core;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class NMSImpl18 implements NMSCore {
	
	private static final Class<?>	    blockPosition	   = NMSUtils.getClass("nms.BlockPosition");
	
	private static final Constructor<?>	blockPositionConst	= NMSUtils.getConstructor(blockPosition, int.class, int.class, int.class);
	
	private static final Method	        getTile	           = NMSUtils.getMethod(nmsWorld, "getTileEntity", blockPosition);
	private static final Method	        openSign	       = NMSUtils.getMethod(nmsPlayer, "openSign", tileEntitySign);
	
	@Override
	public Object getSignEdit(final int x, final int y, final int z) {
		try {
			final Reflecter packet = new PacketReflecter("PacketPlayOutOpenSignEditor");
			packet.set("a", getBlockPosition(x, y, z));
			return packet.getInstance();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Object getSignChange(final Block sign, final String... lines) {
		try {
			final List chatbase = Lists.newArrayList();
			for (final String line : Core.getCore().getChatColorUtil().replaceAndToMc(lines)) {
				chatbase.add(chatComponentConst.newInstance(line));
			}
			
			final Reflecter packet = new PacketReflecter("PacketPlayOutUpdateSign");
			packet.set("a", NMSUtils.getNMSWorld(sign.getWorld()));
			packet.set("b", getBlockPosition(sign.getX(), sign.getY(), sign.getZ()));
			packet.set("c", chatbase.toArray((Object[]) Array.newInstance(ichatBase, chatbase.size())));
			
			return packet.getInstance();
		} catch (final Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public void sendSignEditor(final Player p, final Block sign) {
		try {
			final Object tile = getTileEntity(sign);
			final Object entity = getHandlePlayer.invoke(p);
			
			Reflect.set(tile, "isEditable", true);
			openSign.invoke(entity, tile);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Object getTileEntity(final Block sign) {
		try {
			return tileEntitySign.cast(getTile.invoke(getHandleWorld.invoke(sign.getWorld()), getBlockPosition(sign.getX(), sign.getY(), sign.getZ())));
		} catch (final Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Object getBlockPosition(final int x, final int y, final int z) {
		try {
			return blockPosition.cast(blockPositionConst.newInstance(x, y, z));
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
