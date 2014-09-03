package me.MiniDigger.CraftCore.Event.Events;

import me.MiniDigger.Core.Event.Events.UserJoinGameEvent;
import me.MiniDigger.Core.Game.Game;
import me.MiniDigger.Core.User.User;
import me.MiniDigger.CraftCore.Event.CoreEvent;

public class CoreUserJoinGameEvent extends CoreEvent implements UserJoinGameEvent {
	
	private final Game	game;
	private final User	user;
	
	public CoreUserJoinGameEvent(final Game game, final User user) {
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