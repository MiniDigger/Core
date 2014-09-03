package me.MiniDigger.CraftCore.Event.Events;

import me.MiniDigger.Core.Event.Events.UserLeaveGameEvent;
import me.MiniDigger.Core.Game.Game;
import me.MiniDigger.Core.User.User;
import me.MiniDigger.CraftCore.Event.CoreEvent;

public class CoreUserLeaveGameEvent extends CoreEvent implements UserLeaveGameEvent {
	
	private final Game	game;
	private final User	user;
	
	public CoreUserLeaveGameEvent(final Game game, final User user) {
		this.game = game;
		this.user = user;
	}
	
	@Override
	public Game getGame() {
		return game;
	}
	
	@Override
	public User getUser() {
		return user;
	}
}