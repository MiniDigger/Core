package me.MiniDigger.Core.AddOn.GetTheDrop;

import me.MiniDigger.Core.Feature.Feature;
import me.MiniDigger.Core.Game.Game;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.Core.Util.EntityUtil;
import me.MiniDigger.CraftCore.Feature.Features.AutoRespawnFeature;
import me.MiniDigger.CraftCore.Feature.Features.BleedFeature;
import me.MiniDigger.CraftCore.Feature.Features.DropFeature;
import me.MiniDigger.CraftCore.Feature.Features.FixedFoodFeature;
import me.MiniDigger.CraftCore.Feature.Features.FixedTimeFeature;
import me.MiniDigger.CraftCore.Feature.Features.FixedWeatherFeature;
import me.MiniDigger.CraftCore.Feature.Features.LastManStandingFeature;
import me.MiniDigger.CraftCore.Feature.Features.MapFeature;
import me.MiniDigger.CraftCore.Feature.Features.MobFeature;
import me.MiniDigger.CraftCore.Feature.Features.PvPFeature;
import me.MiniDigger.CraftCore.Feature.Features.SpawnFeature;

import org.bukkit.ChatColor;
import org.bukkit.WeatherType;

public class GetTheDropPhaseTwo extends TimedPhase {
	
	public GetTheDropPhaseTwo(final Game game, final Phase next, final int secs) {
		super(game, next, secs);
		init();
	}
	
	@Override
	public String getName() {
		return "GetTheDropTwo";
	}
	
	@Override
	public String getBarMessage() {
		return "Kill them ALL!";
	}
	
	@Override
	public boolean displayBar() {
		return true;
	}
	
	@Override
	public boolean displayLevel() {
		return true;
	}
	
	@Override
	public void init() {
		addFeature(new BleedFeature(this));
		addFeature(new AutoRespawnFeature(this));
		addFeature(new LastManStandingFeature(this));
		addFeature(new FixedFoodFeature(this));
		addFeature(new MobFeature(this, EntityUtil.getAll(Type.OTHER, Type.CART, Type.PROJECTILE, Type.UTILITY)));
		addFeature(new FixedTimeFeature(this, 12000));
		addFeature(new FixedWeatherFeature(this, WeatherType.CLEAR));
		addFeature(new DropFeature(this));
		addFeature(new MapFeature(this, null));
		addFeature(new PvPFeature(this, true));
		addFeature(new SpawnFeature(this, false));
	}
	
	@Override
	public void startPhase() {
		getGame().broadCastMessage(game.getPrefix().then("Die Zweite Phase hat begonnen!").color(ChatColor.AQUA));
		getGame().broadCastMessage(game.getPrefix().then("Töte deine Gegner!").color(ChatColor.AQUA));
		getGame().broadCastMessage(game.getPrefix().then("Achte auf die Drops an den Altaren!").color(ChatColor.AQUA));
		final String winner = getGame().getGameData("VoteWinner");
		System.out.println("winner = " + winner);
		((MapFeature) getFeature(Feature.MAP)).setMap(winner);
		super.startPhase();
	}
	
	@Override
	public void endPhase() {
		getGame().broadCastMessage(game.getPrefix().then("Die Götter haben Items hinterlassen!").color(ChatColor.AQUA));
		final DropFeature drop = (DropFeature) getFeature(Feature.DROP);
		drop.drop(true);
		super.endPhase();
	}
	
}
