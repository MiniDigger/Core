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
 */
package me.MiniDigger.CraftCore.Mirror;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Mirror.MirrorHandler;
import me.MiniDigger.Core.Mirror.MirrorReviever;
import me.MiniDigger.Core.Mirror.MirrorSender;

import org.bukkit.plugin.Plugin;

import com.comphenix.net.sf.cglib.proxy.Factory;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketAdapter.AdapterParameteters;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.injector.GamePhase;
import com.comphenix.protocol.reflect.EquivalentConverter;
import com.comphenix.protocol.reflect.PrettyPrinter;
import com.comphenix.protocol.reflect.PrettyPrinter.ObjectPrinter;
import com.comphenix.protocol.utility.MinecraftReflection;
import com.comphenix.protocol.wrappers.BukkitConverters;

public class CoreMirrorHandler implements MirrorHandler {
	
	private static final String	IP	 = "localhost";
	private static final int	PORT	= 1337;
	
	private MirrorReviever	    reviever;
	private MirrorSender	    sender;
	
	public void init() {
		
		try {
			if (System.getProperty("mirrorserver") != null && System.getProperty("mirrorserver").equalsIgnoreCase("true")) {
				sender = new CoreMirrorSender();
				sender.init(PORT);
			} else if (System.getProperty("mirrorclient") != null && System.getProperty("mirrorclient").equalsIgnoreCase("true")) {
				reviever = new CoreMirrorReciever();
				reviever.init(IP, PORT);
				return;
			} else {
				return;
			}
		} catch (Exception ex) {
			System.out.println("ex" + ex.getMessage());
			return;
		}
		
		AdapterParameteters params = new AdapterParameteters();
		Set<PacketType> types = new HashSet<PacketType>();
		for (PacketType type : PacketType.values()) {
			types.add(type);
		}
		params.gamePhase(GamePhase.PLAYING).optionAsync().plugin((Plugin) Core.getCore().getInstance()).serverSide().types(types);
		ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(params) {
			
			@Override
			public void onPacketSending(PacketEvent event) {
				try {
					// printInformation(event);
					System.out.println("got " + event.getPacketType().name());
					sender.send(event);
				} catch (Exception e) {
					
				}
			}
			
			@Override
			public void onPacketReceiving(PacketEvent event) {
				try {
					// printInformation(event);
				} catch (Exception e) {
					
				}
			}
		});
	}
	
	private void printInformation(PacketEvent event) {
		String verb = event.isServerPacket() ? "Sent" : "Received";
		String format = event.isServerPacket() ? "%s %s to %s" : "%s %s from %s";
		
		String shortDescription = String.format(format, event.isCancelled() ? "Cancelled" : verb, event.getPacketType(), event.getPlayer().getName());
		
		try {
			Core.getCore().getInstance().info(shortDescription + ":\n" + getPacketDescription(event.getPacket()));
		} catch (IllegalAccessException e) {
			Core.getCore().getInstance().error("Unable to use reflection.");
			e.printStackTrace();
		}
	}
	
	public String getPacketDescription(PacketContainer packetContainer) throws IllegalAccessException {
		Object packet = packetContainer.getHandle();
		Class<?> clazz = packet.getClass();
		
		// Get the first Minecraft super class
		while (clazz != null && clazz != Object.class && (!MinecraftReflection.isMinecraftClass(clazz) || Factory.class.isAssignableFrom(clazz))) {
			clazz = clazz.getSuperclass();
		}
		
		return PrettyPrinter.printObject(packet, clazz, MinecraftReflection.getPacketClass(), PrettyPrinter.RECURSE_DEPTH, new ObjectPrinter() {
			
			@Override
			public boolean print(StringBuilder output, Object value) {
				EquivalentConverter<Object> converter = findConverter(value.getClass());
				if (converter != null) {
					output.append(converter.getSpecific(value));
					return true;
				}
				return false;
			}
		});
	}
	
	private EquivalentConverter<Object> findConverter(Class<?> clazz) {
		Map<Class<?>, EquivalentConverter<Object>> converters = BukkitConverters.getConvertersForGeneric();
		
		while (clazz != null) {
			EquivalentConverter<Object> result = converters.get(clazz);
			
			if (result != null)
				return result;
			else
				clazz = clazz.getSuperclass();
		}
		return null;
	}
}
