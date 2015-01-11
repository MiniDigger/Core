package me.MiniDigger.CraftCore.Protocol.SignGUI;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import me.MiniDigger.Core.Core;

import com.google.common.collect.Lists;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class NMSImpl18 implements NMSCore {
	
	private static final Class<?> blockPosition = NMSUtils.getClass("nms.BlockPosition");
	
	private static final Constructor<?> blockPositionConst = NMSUtils.getConstructor(blockPosition, int.class, int.class, int.class);
	
	private static final Method getTile = NMSUtils.getMethod(nmsWorld, "getTileEntity", blockPosition);
	private static final Method openSign = NMSUtils.getMethod(nmsPlayer, "openSign", tileEntitySign);

	@Override
	public Object getSignEdit(int x, int y, int z) {
		try {
			Reflecter packet = new PacketReflecter("PacketPlayOutOpenSignEditor");
			packet.set("a", getBlockPosition(x, y, z));
			return packet.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Object getSignChange(Block sign, String... lines) {
		try {
			List chatbase = Lists.newArrayList();
			for (String line : Core.getCore().getChatColorUtil().replaceAndToMc(lines)) {
				chatbase.add(chatComponentConst.newInstance(line));
			}
			
			Reflecter packet = new PacketReflecter("PacketPlayOutUpdateSign");
			packet.set("a", NMSUtils.getNMSWorld(sign.getWorld()));
			packet.set("b", getBlockPosition(sign.getX(), sign.getY(), sign.getZ()));
			packet.set("c", chatbase.toArray((Object[]) Array.newInstance(ichatBase, chatbase.size())));
			
			return packet.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void sendSignEditor(Player p, Block sign) {
		try {
			Object tile = getTileEntity(sign);
			Object entity = getHandlePlayer.invoke(p);
			
			Reflect.set(tile, "isEditable", true);
			openSign.invoke(entity, tile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Object getTileEntity(Block sign) {
		try {
			return tileEntitySign.cast(getTile.invoke(getHandleWorld.invoke(sign.getWorld()), getBlockPosition(sign.getX(), sign.getY(), sign.getZ())));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Object getBlockPosition(int x, int y, int z) {
		try {
			return blockPosition.cast(blockPositionConst.newInstance(x, y, z));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
