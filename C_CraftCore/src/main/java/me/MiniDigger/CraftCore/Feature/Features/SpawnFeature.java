package me.MiniDigger.CraftCore.Feature.Features;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Event.Events.UserDeathEvent;
import me.MiniDigger.Core.Event.Events.UserJoinGameEvent;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Map.MapData;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.Core.User.User;
import me.MiniDigger.CraftCore.Feature.CoreFeature;

import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerRespawnEvent;

public class SpawnFeature extends CoreFeature {
	
	private boolean	spawn;
	
	public SpawnFeature(Phase phase, boolean spawn) {
		super(phase);
		this.spawn = spawn;
	}
	
	private List<Location>	usedSpawns;
	private Location[]	        spawns;
	
	public boolean spawn(){
		return spawn;
	}
	
	public void setSpawn(boolean spawn){
		this.spawn = spawn;
	}
	
	@Override
	public FeatureType getType() {
		return FeatureType.SPAWN;
	}
	
	@Override
	public List<FeatureType> getDependencies() {
		List<FeatureType> result = new ArrayList<>();
		result.add(FeatureType.MAP);
		return result;
	}
	
	@Override
	public List<FeatureType> getSoftDependencies() {
		return new ArrayList<>();
	}
	
	@Override
	public List<FeatureType> getIncompabilities() {
		return new ArrayList<>();
	}
	
	@Override
	public void start() {
		usedSpawns = new ArrayList<>();
		MapData data = ((MapFeature) getPhase().getFeature(FeatureType.MAP)).getMap();
		HashMap<String, Location> spawns = data.getLocs(DyeColor.RED);
		this.spawns = spawns.values().toArray(new Location[spawns.values().size()]);
		if (spawn) {
			for (UUID id : getPhase().getGame().getPlayers()) {
				spawn(Core.getCore().getUserHandler().get(id));
			}
		}
	}
	
	@Override
	public void end() {
		usedSpawns = null;
		spawns = null;
	}
	
	public Location spawn(User user) {
		if (usedSpawns.size() >= spawns.length) {
			usedSpawns = new ArrayList<>();
		}
		int counter = 0;// To prevent invinitiv loop
		while (true) {
			Location loc = spawns[Core.getCore().getRandomUtil().nextInt(spawns.length)];
			if (loc == null) {
				counter++;
				continue;
			}
			if (usedSpawns.contains(loc)) {
				if (counter >= 100) {
					usedSpawns = new ArrayList<>();
					counter = 0;
				} else {
					counter++;
				}
				continue;
			}
			usedSpawns.add(loc);
			user.getPlayer().teleport(loc);
			return loc;
		}
	}
	
	@EventHandler
	public void onJoin(UserJoinGameEvent e) {
		if (e.getGame().getIdentifier().equals(getPhase().getGame().getIdentifier())) {
			spawn(e.getUser());
		}
	}
	
	@EventHandler(priority = EventPriority.LOW)
	public void onDeath(UserDeathEvent e) {
		if (!e.shouldRespawn()) {
			return;
		}
		if (e.getGame() != null && e.getGame().getIdentifier().equals(getPhase().getGame().getIdentifier())) {
			spawn(e.getUser());
		}
	}
	
	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) {
		User user = Core.getCore().getUserHandler().get(e.getPlayer().getUniqueId());
		if (getPhase().getGame().getPlayers().contains(user.getUUID())) {
			e.setRespawnLocation(spawn(user));
		}
	}
}