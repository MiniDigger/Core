package me.MiniDigger.CraftCore.Feature.Features;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.DisplaySlot;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Chat.ChatChars;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Game.GameType;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.Core.Scoreboard.Scoreboard;

import me.MiniDigger.CraftCore.Feature.CoreFeature;
import me.MiniDigger.CraftCore.Scoreboard.CoreScoreboard;
import me.MiniDigger.CraftCore.Scoreboard.CoreScoreboardLine;

public class MapInfoFeature extends CoreFeature {
	
	public MapInfoFeature(Phase phase) {
		super(phase);
	}
	
	@Override
	public FeatureType getType() {
		return FeatureType.MAPINFO;
	}
	
	@Override
	public List<FeatureType> getDependencies() {
		List<FeatureType> result = new ArrayList<FeatureType>();
		result.add(FeatureType.MAP);
		return result;
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
		Scoreboard b = new CoreScoreboard(ChatColor.GOLD + "MapInfo");
		
		MapFeature m = (MapFeature) getPhase().getFeature(FeatureType.MAP);
		int i = 1;
		
		for (GameType t : Core.getCore().getMapHandler().getGameTypes(m.getMap().getName())) {
			b.addLine(new CoreScoreboardLine(i, ChatColor.AQUA + ChatChars.Misc.bullet + " " + t.getName(), DisplaySlot.SIDEBAR));
			i++;
		}
		
		b.addLine(new CoreScoreboardLine(i, ChatColor.GOLD + "GameTypes: ", DisplaySlot.SIDEBAR));
		i++;
		b.addLine(new CoreScoreboardLine(i, ChatColor.AQUA + ChatChars.Misc.bullet + " " + Core.getCore().getMapHandler().getAuthor(m.getMap().getName()),
		        DisplaySlot.SIDEBAR));
		i++;
		b.addLine(new CoreScoreboardLine(i, ChatColor.GOLD + "Author: ", DisplaySlot.SIDEBAR));
		i++;
		b.addLine(new CoreScoreboardLine(i, ChatColor.AQUA + ChatChars.Misc.bullet + " " + Core.getCore().getMapHandler().getName(m.getMap().getName()),
		        DisplaySlot.SIDEBAR));
		i++;
		b.addLine(new CoreScoreboardLine(i, ChatColor.GOLD + "Name: ", DisplaySlot.SIDEBAR));
		
		for (UUID id : getPhase().getGame().getPlayers()) {
			try {
				Core.getCore().getScoreboardHandler().addToPlayer(b, Bukkit.getPlayer(id));
			} catch (Exception ex) {
				
			}
		}
	}
	
	@Override
	public void end() {
		
	}
	
}
