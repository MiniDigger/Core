package me.MiniDigger.CraftCore.Scoreboard;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;

import me.MiniDigger.Core.Scoreboard.Scoreboard;
import me.MiniDigger.Core.Scoreboard.ScoreboardHandler;

public class CoreScoreboardHandler implements ScoreboardHandler {
	
	private HashMap<UUID, Scoreboard>	boards	= new HashMap<UUID, Scoreboard>();
	
	@Override
	public Scoreboard getBoard(UUID id) {
		if (boards.get(id) == null) {
			boards.put(id, new CoreScoreboard("dummy"));
		}
		return boards.get(id);
	}
	
	@Override
	public void addToPlayer(Scoreboard b, Player p) {
		p.setScoreboard(b.toBukkitScoreboard());
		if (boards.containsKey(p.getUniqueId())) {
			boards.remove(p.getUniqueId());
		}
		boards.put(p.getUniqueId(), b);
	}
}
