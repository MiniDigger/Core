package me.MiniDigger.CraftCore.Stats;

import java.util.Date;
import java.util.UUID;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Game.GameType;
import me.MiniDigger.Core.Stats.Action;
import me.MiniDigger.Core.Stats.ActionType;

public class CoreAction implements Action {
	private Date		date;
	private ActionType	action;
	private String		data;
	private UUID		user;
	private GameType	gameType;
	private String		server;

	private static final GameType	GAMETYPE;
	private static final String		SERVER;

	static {
		final String game = Core.getCore().getInstance().getConfig().getString("server-type");
		GameType type = GameType.NOTHING;
		if (game != null) {
			try {
				type = GameType.valueOf(game);
			}
			catch (final Exception ex2) {
				type = GameType.NOTHING;
			}
			finally {
				GAMETYPE = type;
			}
		} else {
			GAMETYPE = GameType.NOTHING;
		}

		SERVER = Core.getCore().getInstance().getConfig().getString("server-name");
	}

	public CoreAction(ActionType action, String data, UUID user) {
		super();
		this.date = new Date();
		this.action = action;
		this.data = data;
		this.user = user;
		this.gameType = GAMETYPE;
		this.server = SERVER;
	}

	@Override
	public Date getDate() {
		return date;
	}

	@Override
	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public ActionType getAction() {
		return action;
	}

	@Override
	public void setAction(ActionType action) {
		this.action = action;
	}

	@Override
	public String getData() {
		return data;
	}

	@Override
	public void setData(String data) {
		this.data = data;
	}

	@Override
	public UUID getUser() {
		return user;
	}

	@Override
	public void setUser(UUID user) {
		this.user = user;
	}

	@Override
	public GameType getGameType() {
		return gameType;
	}

	@Override
	public void setGameType(GameType gameType) {
		this.gameType = gameType;
	}

	@Override
	public String getServer() {
		return server;
	}

	@Override
	public void setServer(String server) {
		this.server = server;
	}
}
