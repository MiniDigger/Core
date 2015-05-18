package me.MiniDigger.CraftCore.Feature.Features;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.event.EventHandler;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Game.GameType;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.Core.Prefix.Prefix;
import me.MiniDigger.Core.User.User;

import me.MiniDigger.CraftCore.Event.Events.CoreUserLeaveGameEvent;
import me.MiniDigger.CraftCore.Feature.CoreFeature;

public class LeaveHandlerFeature extends CoreFeature {
	
	public LeaveHandlerFeature(Phase phase) {
		super(phase);
	}
	
	@Override
	public FeatureType getType() {
		return FeatureType.LEAVEHANDLER;
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
	
	@EventHandler
	public void onLeave(final CoreUserLeaveGameEvent e) {
		if (e.getGame().getIdentifier() == getPhase().getGame().getIdentifier()) {
			getPhase().getGame().broadCastMessage(
			        Prefix.getByGameType(getPhase().getGame().getType()).getPrefix().then("Der Spieler " + e.getUser().getDisplayName() + " hat das Spiel verlassen!"));
			if (getPhase().getGame().getPlayers().size() <= 2) {
				if (getPhase().getGame().getType() == GameType.LOBBY) {
					return;
				}
				
				User u = Core.getCore().getUserHandler().get(getPhase().getGame().getPlayers().get(0));
				if (u.getUUID() == e.getUser().getUUID()) {
					u = Core.getCore().getUserHandler().get(getPhase().getGame().getPlayers().get(1));
				}
				getPhase().getGame().end(u);
			}
		}
	}
}
