package me.MiniDigger.CraftCore.Feature.Features;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Phase.Phase;

import me.MiniDigger.CraftCore.Feature.CoreFeature;

public class MapResetFeature extends CoreFeature {
	
	private List<BlockState>	changes	= new ArrayList<BlockState>();
	
	public MapResetFeature(Phase phase) {
		super(phase);
	}
	
	@Override
	public FeatureType getType() {
		return FeatureType.MAPRESET;
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
		for (int i = changes.size(); i > 0; i--) {
			System.out.println("update " + changes.get(i).getType().name());
			changes.get(i).update(true, false);
		}
	}
	
	public void add(BlockState s) {
		System.out.println("add " + s.getType().name());
		changes.add(s);
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		if (getPhase().getGame().getPlayers().contains(e.getPlayer().getUniqueId())) {
			add(e.getBlockReplacedState());
		}
	}
	
	@EventHandler
	public void onDestroy(BlockBreakEvent e) {
		if (getPhase().getGame().getPlayers().contains(e.getPlayer().getUniqueId())) {
			add(e.getBlock().getState());
		}
	}
}
