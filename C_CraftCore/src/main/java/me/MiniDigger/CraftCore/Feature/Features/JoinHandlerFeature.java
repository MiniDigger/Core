package me.MiniDigger.CraftCore.Feature.Features;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.DyeColor;
import org.bukkit.event.EventHandler;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.Core.User.User;

import me.MiniDigger.CraftCore.Event.Events.CoreUserJoinGameEvent;
import me.MiniDigger.CraftCore.Feature.CoreFeature;

public class JoinHandlerFeature extends CoreFeature {
	
	public JoinHandlerFeature(final Phase phase) {
		super(phase);
	}
	
	@Override
	public FeatureType getType() {
		return FeatureType.JOINHANDLER;
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
		if (getPhase().getGame().getPlayers().size() <= 1) {
			final User u = Core.getCore().getUserHandler().get(getPhase().getGame().getPlayers().get(0));
			getPhase().getGame().end(u);
		}
	}
	
	@Override
	public void end() {
		
	}
	
	@EventHandler
	public void onJoin(final CoreUserJoinGameEvent e) {
		if (getPhase().getGame().getIdentifier() == e.getGame().getIdentifier()) {
			if (getPhase().getGame().allowJoin()) {
				Core.getCore().getPlayerUtil().prepare(e.getUser().getPlayer());
				e.getUser().getPlayer().teleport(((MapFeature) getPhase().getFeature(FeatureType.MAP)).getMap().getLocs(DyeColor.RED).get(0));
			} else if (getPhase().getGame().allowSpectate()) {
				Core.getCore().getPlayerUtil().prepare(e.getUser().getPlayer());
				((SpecateFeature) getPhase().getFeature(FeatureType.SPEC)).spec(e.getUser());
			} else {
				System.out.println("fuck you, you can't join here");
			}
		}
	}
}