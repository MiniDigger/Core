package me.MiniDigger.CraftCore.Event.Events;

import me.MiniDigger.Core.Event.Events.UserDamageEvent;
import me.MiniDigger.Core.Game.Game;
import me.MiniDigger.Core.User.User;
import me.MiniDigger.CraftCore.Event.CoreCancelableEvent;

public class CoreUserDamageEvent extends CoreCancelableEvent implements UserDamageEvent {
	
	private final double	dmg;
	private final User	 damager;
	private final User	 damaged;
	private final Game	 game;
	
	public CoreUserDamageEvent(final double dmg, final User damager, final User damaged, final Game game) {
		this.dmg = dmg;
		this.damager = damager;
		this.damaged = damaged;
		this.game = game;
	}
	
	@Override
	public boolean hasGame() {
		return game == null ? false : true;
	}
	
	@Override
	public boolean hasDamager() {
		return damager == null ? false : true;
	}
	
	@Override
	public double getDmg() {
		return dmg;
	}
	
	@Override
	public User getDamager() {
		return damager;
	}
	
	@Override
	public User getDamaged() {
		return damaged;
	}
	
	@Override
	public Game getGame() {
		return game;
	}
}
