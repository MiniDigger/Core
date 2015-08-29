package me.MiniDigger.Core.Bar;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public interface Laser {

	/**
	 * Sets the target for the laser for one player
	 *
	 * @param show
	 *            who should see the laser
	 * @param target
	 *            the target
	 */
	void setTarget(Player show, LivingEntity target);

	/**
	 * Sets the target for the laser for all players
	 *
	 * @param target
	 *            the target
	 */
	void setTarget(LivingEntity target);

	/**
	 * Sets the target for the laser for all players
	 *
	 * @param target
	 *            the target
	 */
	void setTarget(Location target);

	/**
	 * Despawns the laser for one or all players
	 *
	 * @param show
	 *            the player to despawn for, null for all
	 */
	void despawn(Player show);

	/**
	 * D Sets the target for the laser for one players
	 *
	 * @param show
	 *            the player to show for
	 * @param target
	 *            the target
	 */
	void setTarget(Player show, Location target);

}
