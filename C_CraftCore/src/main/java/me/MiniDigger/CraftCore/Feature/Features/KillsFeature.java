package me.MiniDigger.CraftCore.Feature.Features;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.NotImplementedException;

import org.bukkit.event.EventHandler;
import org.bukkit.scoreboard.DisplaySlot;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.Core.Scoreboard.Scoreboard;
import me.MiniDigger.Core.Stats.StatsType;
import me.MiniDigger.Core.User.User;

import me.MiniDigger.CraftCore.Event.Events.CoreUserDeathEvent;
import me.MiniDigger.CraftCore.Feature.CoreFeature;
import me.MiniDigger.CraftCore.Scoreboard.CoreScoreboard;
import me.MiniDigger.CraftCore.Scoreboard.CoreScoreboardLine;

public class KillsFeature extends CoreFeature {
	
	private int	               win	  = -1;
	private boolean	           showScoreboard;
	private Map<UUID, Integer>	kills	= new HashMap<UUID, Integer>();
	
	public KillsFeature(Phase phase, int win, boolean showScoreboard) {
		super(phase);
		this.win = win;
		this.showScoreboard = showScoreboard;
	}
	
	@Override
	public FeatureType getType() {
		return FeatureType.KILLS;
	}
	
	@Override
	public List<FeatureType> getDependencies() {
		return new ArrayList<FeatureType>();
	}
	
	@Override
	public List<FeatureType> getSoftDependencies() {
		return new ArrayList<FeatureType>();
	}
	
	@Override
	public List<FeatureType> getIncompabilities() {
		return new ArrayList<FeatureType>();
	}
	
	@Override
	public void start() {
		
	}
	
	@Override
	public void end() {
		
	}
	
	public void updateSb() {
		Scoreboard sb = new CoreScoreboard();		
		for (UUID id : getPhase().getGame().getPlayers()) {
			for (UUID id2 : kills.keySet()) {
				Core.getCore().getScoreboardHandler().getBoard(id).addLine(new CoreScoreboardLine(kills.get(id2),"",DisplaySlot.BELOW_NAME));
			}
		}
	}
	
	@EventHandler
	public void kill(CoreUserDeathEvent e) {
		if (e.getKiller() != null) {
			if (kills.containsKey(e.getKiller().getUUID())) {
				int kills = this.kills.remove(e.getKiller().getUUID());
				kills++;
				this.kills.put(e.getKiller().getUUID(), kills);
				e.getKiller().getStats().set(getPhase().getGame().getType(), StatsType.StatsGame.KILLS, kills);
				e.getKiller().getStats().save();
				
				if (kills == win) {
					getPhase().getGame().end(e.getKiller());
				}
			}
		}
	}
}
