// @formatter:off
/*******************************************************************************
 * Test
 *******************************************************************************/
// @formatter:on
package eu.matejkormuth.pexel.PexelCore.util;

import net.minecraft.server.v1_7_R4.Packet;

import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * Pomocna trieda pouzivana na posielanie pakiet.
 * 
 */
public class PacketHelper {
	
	/**
	 * Posle paketu packet hracovi player.
	 * 
	 * @param player
	 * @param packet
	 */
	public static void send(final Player player, final Packet packet) {
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
	}
}
