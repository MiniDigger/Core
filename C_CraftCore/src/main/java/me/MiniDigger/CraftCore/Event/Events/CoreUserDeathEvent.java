package me.MiniDigger.CraftCore.Event.Events;

import me.MiniDigger.Core.Event.Events.UserDeathEvent;
import me.MiniDigger.Core.Game.Game;
import me.MiniDigger.Core.User.User;
import me.MiniDigger.CraftCore.Event.CoreEvent;

public class CoreUserDeathEvent extends CoreEvent implements UserDeathEvent {
	
	private User	user;
	private User	killer;
	private Game	game;
	private boolean	keepDrops;
	private boolean	shouldRespawn;
	
	public CoreUserDeathEvent(User user, User killer, Game game, boolean keepDrops, boolean shouldRespawn) {
		this.user = user;
		this.killer = killer;
		this.game = game;
		this.keepDrops = keepDrops;
		this.shouldRespawn = shouldRespawn;
	}
	
	@Override
	public User getUser() {
		return user;
	}
	
	@Override
	public User getKiller() {
		return killer;
	}
	
	@Override
	public Game getGame() {
		return game;
	}
	
	@Override
	public void setKeepDrops(boolean keepDrops) {
		this.keepDrops = keepDrops;
	}
	
	@Override
	public boolean keepDrops() {
		return keepDrops;
	}
	
	@Override
	public void setShouldRespawn(boolean shouldRespawn) {
		this.shouldRespawn = shouldRespawn;
	}
	
	@Override
	public boolean shouldRespawn() {
		return shouldRespawn;
	}
}
