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
package me.MiniDigger.Core.Protocol;

import java.util.List;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import com.comphenix.protocol.events.PacketEvent;

public interface SignChangers extends Listener {

	/**
	 * Updates all the signs
	 *
	 * @param noUpdates
	 *            a list with uuid not to update
	 */
	public void update(final List<UUID> noUpdates);

	/**
	 * handles the PacketEvent, modifying the sign packet, if the player doesn't
	 * edit the sign
	 *
	 * @param event
	 *            The event
	 */
	public void handlePacket(final PacketEvent event);

	/**
	 * Adds all the changers
	 */
	public void init();

	/**
	 * Registers a signchanger
	 *
	 * @param changer
	 *            The changer to register
	 */
	public void addSignChanger(final SignChanger changer);

	/**
	 * Unregister a changer
	 *
	 * @param changer
	 *            The changer to unregister
	 */
	public void removeSignChanger(final SignChanger changer);

	/**
	 * @return All the registered changers
	 */
	public List<SignChanger> getSignChangerList();

	public void handleInteract(final PlayerInteractEvent event);

	public abstract class SignChanger {

		private final String	key;
		private final String	perm;
		private final String	desc;
		private boolean			full	= false;

		public SignChanger(final String key, final String permission, final String desciption) {
			if ((key == null) || (permission == null)) {
				throw new IllegalArgumentException("The key and the permissions node inside the Changer constructor must not be null!");
			}
			if (key.length() > 15) {
				throw new IllegalArgumentException("The key inside the Changer constructor must not be longer then 15!");
			}
			this.key = key;
			perm = permission;
			desc = desciption;
		}

		public SignChanger(final String key, final String permission, final String desciption, final boolean full) {
			this(key, permission, desciption);
			this.full = full;
		}

		/**
		 * @return The key
		 */
		public String getKey() {
			return key;
		}

		/**
		 * @return The perm needed to create this type of changer
		 */
		public String getPerm() {
			return perm;
		}

		/**
		 * @return A basic description
		 */
		public String getDescription() {
			return desc;
		}

		/**
		 * @return if this changer changes the whole sign
		 */
		public boolean isFullChanger() {
			return full;
		}

		/**
		 * Replaces the key with this value
		 *
		 * @param p
		 *            The player, seeing the sign
		 * @param l
		 *            The location of the sign
		 * @return The value to replace the key
		 */
		public abstract String getValue(final Player p, final Location l);
	}
}
