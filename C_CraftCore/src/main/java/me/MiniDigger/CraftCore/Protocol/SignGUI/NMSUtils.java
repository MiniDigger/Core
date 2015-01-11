package me.MiniDigger.CraftCore.Protocol.SignGUI;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.util.NumberConversions;


public abstract class NMSUtils implements NMSCore {
	private static String NMSVersion = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
	
	protected static NMSCore impl = getImpl();

	private static NMSCore getImpl() {
		try {
			int version = NumberConversions.toInt(NMSVersion.replace("_", "").substring(1, 3));
			if (version >= 18)
				version = 18;
			if (version <= 17)
				version = 17;

			return (NMSCore) Class.forName(NMSUtils.class.getPackage().getName() + ".NMSImpl" + version).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	protected static Class<?> getClass(String name) {
		try {
			name = name.replaceAll("(obc|nms)", "$1\\." + NMSVersion);
			name = name.replace("obc", "org.bukkit.craftbukkit");
			name = name.replace("nms", "net.minecraft.server");
			return Class.forName(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	protected static Constructor<?> getConstructor(Class<?> clazz, Class<?>... classes) {
		try {
			return clazz.getConstructor(classes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	protected static Method getMethod(Class<?> clazz, String method, Class<?>... classes) {
		try {
			return Reflect.getMethod(clazz, method, classes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

	public static void sendPacket(Player p, Object packet) {
		try {
			if (!packetClass.isAssignableFrom(packet.getClass())) {
				throw new IllegalArgumentException(packet.getClass().getName() + " is not a " + packetClass.getName());
			}
			
			sendPacket.invoke(Reflect.get(getHandlePlayer.invoke(p), "playerConnection"), packet);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Object getPacket(String name) {
		try {
			Class<?> packet = getClass("nms." + name);

			if (!packetClass.isAssignableFrom(packet)) {
				throw new IllegalArgumentException(packet.getCanonicalName() + " is not a " + packetClass.getCanonicalName());
			}
			return packet.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Object getNMSWorld(World world) {
		try {
			return nmsWorld.cast(getHandleWorld.invoke(world));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Object getSignEditPacket(int x, int y, int z) {
		return impl.getSignEdit(x, y, z);
	}

	public static Object getSignChangePacket(Block sign, String... lines) {
		return impl.getSignChange(sign, lines);
	}

	public static void sendEditor(Player p, Block sign) {
		impl.sendSignEditor(p, sign);
	}

	public static Object getTile(Block sign) {
		return impl.getTileEntity(sign);
	}

	public static String getVersion() {
		try {
			return Reflect.invoke(getHandleServer.invoke(Bukkit.getServer()), "getVersion");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
