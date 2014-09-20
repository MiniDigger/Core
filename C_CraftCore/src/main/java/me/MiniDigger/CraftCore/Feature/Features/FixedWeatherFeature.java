package me.MiniDigger.CraftCore.Feature.Features;

import java.util.ArrayList;
import java.util.List;

import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.CraftCore.Feature.CoreFeature;

import org.bukkit.Bukkit;
import org.bukkit.WeatherType;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.weather.WeatherChangeEvent;

public class FixedWeatherFeature extends CoreFeature {
	
	private String	                    world;
	private WeatherType	weather;
	
	public FixedWeatherFeature(Phase phase, WeatherType weather) {
		super(phase);
		this.weather = weather;
	}
	
	public WeatherType getWeatherType(){
		return weather;
	}
	
	public void setWeatherType(WeatherType type){
		weather = type;
	}
	
	@Override
	public FeatureType getType() {
		return FeatureType.FIXEDWEATHER;
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
		MapFeature m = (MapFeature) getPhase().getFeature(FeatureType.MAP);
		world = m.getMap().getName();
		
		World w = Bukkit.getWorld(world);
		switch (weather) {
		case CLEAR:
			w.setStorm(false);
			w.setThundering(false);
			break;
		case DOWNFALL:
			w.setStorm(true);
			w.setThundering(true);
			break;
		}
	}
	
	@Override
	public void end() {
		
	}
	
	@EventHandler
	public void onWeatherChange(WeatherChangeEvent e) {
		if (e.getWorld().getName().equals(world)) {
			e.setCancelled(true);
		}
	}
	
}
