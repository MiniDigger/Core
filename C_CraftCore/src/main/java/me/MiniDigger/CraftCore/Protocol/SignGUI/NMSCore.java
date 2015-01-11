package me.MiniDigger.CraftCore.Protocol.SignGUI;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public interface NMSCore {
	
	static Class<?> packetClass = NMSUtils.getClass("nms.Packet");
	
	static Class<?> tileEntitySign = NMSUtils.getClass("nms.TileEntitySign");
	static Class<?> entityHuman = NMSUtils.getClass("nms.EntityHuman");

	static Class<?> craftPlayer = NMSUtils.getClass("obc.entity.CraftPlayer");
	static Class<?> craftWorld = NMSUtils.getClass("obc.CraftWorld");
	static Class<?> craftServer = NMSUtils.getClass("obc.CraftServer");
	
	static Class<?> nmsWorld = NMSUtils.getClass("nms.World");
	static Class<?> nmsPlayer = NMSUtils.getClass("nms.EntityPlayer");
	
	static Class<?> chatSerializer = NMSUtils.getClass("nms.ChatSerializer");
	static Class<?> ichatBase = NMSUtils.getClass("nms.IChatBaseComponent");
	
	static Constructor<?> chatComponentConst = NMSUtils.getConstructor(NMSUtils.getClass("nms.ChatComponentText"), String.class);
	
	static Method getHandlePlayer = NMSUtils.getMethod(craftPlayer, "getHandle");
	static Method getHandleWorld = NMSUtils.getMethod(craftWorld, "getHandle");
	static Method getHandleServer = NMSUtils.getMethod(craftServer, "getServer");
	
	static Method sendPacket = NMSUtils.getMethod(NMSUtils.getClass("nms.PlayerConnection"), "sendPacket", packetClass);
	
	public Object getSignEdit(int x, int y, int z);
	public Object getSignChange(Block sign, String... lines);
	public void sendSignEditor(Player player, Block sign);
	public Object getTileEntity(Block sign);
	
}
