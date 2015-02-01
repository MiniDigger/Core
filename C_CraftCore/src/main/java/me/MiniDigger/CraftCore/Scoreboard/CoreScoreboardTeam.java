package me.MiniDigger.CraftCore.Scoreboard;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.scoreboard.NameTagVisibility;

import me.MiniDigger.Core.Scoreboard.ScoreboardTeam;

public class CoreScoreboardTeam implements ScoreboardTeam {
	
	private String	          name;
	private NameTagVisibility	tag;
	
	private String	          prefix	= "";
	private String	          suffix	= "";
	
	private List<UUID>	      players	= new ArrayList<UUID>();
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public void addPlayer(UUID id) {
		if (!players.contains(id)) {
			players.add(id);
		}
	}
	
	@Override
	public void remPlayer(UUID id) {
		players.remove(id);
	}
	
	@Override
	public void setNameTagVisibility(NameTagVisibility tag) {
		this.tag = tag;
	}
	
	@Override
	public NameTagVisibility getNameTagVisibility() {
		return tag;
	}
	
	@Override
	public String getPrefix() {
		return prefix;
	}
	
	@Override
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	@Override
	public String getSuffix() {
		return suffix;
	}
	
	@Override
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
	@Override
	public List<UUID> getPlayers() {
		return players;
	}
	
}
