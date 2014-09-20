package me.MiniDigger.CraftCore.Feature.Features;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.PlayerInventory;

import me.MiniDigger.Core.Event.Events.UserJoinGameEvent;
import me.MiniDigger.Core.Event.Events.UserLeaveGameEvent;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.CraftCore.Feature.CoreFeature;

public class ClearInvFeature extends CoreFeature {
	
	private HashMap<UUID, PlayerInventory>	invs;
	private boolean	                       restore;
	
	public ClearInvFeature(Phase phase, boolean restore) {
		super(phase);
		this.restore = restore;
	}
	
	public boolean restore() {
		return restore;
	}
	
	@Override
	public FeatureType getType() {
		return FeatureType.CLEARINV;
	}
	
	@Override
	public List<FeatureType> getDependencies() {
		return new ArrayList<>();
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
		invs = new HashMap<>();
		
		for (UUID id : getPhase().getGame().getPlayers()) {
			Player p = Bukkit.getPlayer(id);
			if (restore) {
				invs.put(id, p.getInventory());
			}
			p.getInventory().clear();
		}
	}
	
	@Override
	public void end() {
		if (restore) {
			for (UUID id : getPhase().getGame().getPlayers()) {
				try {
					Player p = Bukkit.getPlayer(id);
					p.getInventory().setContents(invs.get(id).getContents());
					p.getInventory().setArmorContents(invs.get(id).getArmorContents());
				} catch (Exception ex) {}
			}
		}
		
		invs.clear();
		invs = null;
	}
	
	@EventHandler
	public void onJoin(UserJoinGameEvent e) {
		if (e.getGame().getIdentifier().equals(getPhase().getGame().getIdentifier())) {
			if (restore) {
				invs.put(e.getUser().getUUID(), e.getUser().getPlayer().getInventory());
			}
			e.getUser().getPlayer().getInventory().clear();
		}
	}
	
	@EventHandler
	public void onLeave(UserLeaveGameEvent e) {
		if (e.getGame().getIdentifier().equals(getPhase().getGame().getIdentifier())) {
			if (restore) {
				try {
					e.getUser().getPlayer().getInventory().setContents(invs.get(e.getUser().getUUID()).getContents());
					e.getUser().getPlayer().getInventory().setArmorContents(invs.get(e.getUser().getUUID()).getArmorContents());
				} catch (Exception ex) {}
			}
		}
	}
}
