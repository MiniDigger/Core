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

import org.bukkit.Bukkit;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.User.User;

public interface SignListeners extends Listener {

	/**
	 * Registers a new listener
	 *
	 * @param l
	 *            the listener
	 */
	public void register(final SignListener l);

	/**
	 * Handles all interactions with signs
	 *
	 * @param e
	 *            the bukkit event
	 */
	public void onInteract(final PlayerInteractEvent e);

	/**
	 * Registers all listeners
	 */
	public void init();

	public abstract class SignAction implements Runnable {

		private Sign	sign;
		private Player	player;
		private User	user;

		/**
		 * @param sign
		 *            the sign to set
		 */
		public void setSign(final Sign sign) {
			this.sign = sign;
		}

		/**
		 * @return the sign
		 */
		public Sign getSign() {
			return sign;
		}

		/**
		 * @param player
		 *            the player to set
		 */
		public void setPlayer(final Player player) {
			this.player = player;
		}

		/**
		 * @return the player
		 */
		public Player getPlayer() {
			return player;
		}

		/**
		 * @param user
		 *            the user to set
		 */
		public void setUser(final User user) {
			this.user = user;
		}

		/**
		 * @return the user
		 */
		public User getUser() {
			return user;
		}
	}

	public class SignListener {

		private final String		key;
		private final SignAction	action;
		private final boolean		async;
		private boolean				finalAction;

		public SignListener(final String key, final SignAction action, final boolean async) {
			this.key = key;
			this.action = action;
			this.async = async;
		}

		public SignListener(final String key, final SignAction action, final boolean async, final boolean finalAction) {
			this.key = key;
			this.action = action;
			this.async = async;
			this.finalAction = finalAction;
		}

		public SignAction getAction() {
			return action;
		}

		/**
		 * Calls the runnable
		 *
		 * @param sign
		 *            the sign which was clicked
		 */
		public void doAction(final Sign sign) {
			if (isAsync()) {
				Bukkit.getScheduler().runTaskAsynchronously(Core.getCore().getInstance(), action);
			} else {
				Bukkit.getScheduler().runTask(Core.getCore().getInstance(), action);
			}
		}

		/**
		 * @return if the action should be run async
		 */
		public boolean isAsync() {
			return async;
		}

		/**
		 * @return the key
		 */
		public String getKey() {
			return key;
		}

		/**
		 * @return if other actions should be allowed after this
		 */
		public boolean isFinal() {
			return finalAction;
		}
	}
}
