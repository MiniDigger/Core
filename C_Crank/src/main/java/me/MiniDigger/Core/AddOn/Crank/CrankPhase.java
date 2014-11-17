package me.MiniDigger.Core.AddOn.Crank;

import org.bukkit.ChatColor;
import org.bukkit.WeatherType;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Game.Game;
import me.MiniDigger.Core.Util.EntityUtil.Type;

import me.MiniDigger.CraftCore.Feature.Features.AutoRespawnFeature;
import me.MiniDigger.CraftCore.Feature.Features.BleedFeature;
import me.MiniDigger.CraftCore.Feature.Features.FixedFoodFeature;
import me.MiniDigger.CraftCore.Feature.Features.FixedTimeFeature;
import me.MiniDigger.CraftCore.Feature.Features.FixedWeatherFeature;
import me.MiniDigger.CraftCore.Feature.Features.LastManStandingFeature;
import me.MiniDigger.CraftCore.Feature.Features.MapFeature;
import me.MiniDigger.CraftCore.Feature.Features.MobFeature;
import me.MiniDigger.CraftCore.Feature.Features.PvPFeature;
import me.MiniDigger.CraftCore.Feature.Features.SpawnFeature;
import me.MiniDigger.CraftCore.Phase.CorePhase;

public class CrankPhase extends CorePhase {
	
	public CrankPhase(Game game) {
		super(game, null);
	}
	
	@Override
	public String getName() {
		return "Crank";
	}
	
	@Override
	public String getBarMessage() {
		return "You are Cranked! Get adrinaline!";
	}
	
	@Override
	public boolean displayBar() {
		return true;
	}
	
	@Override
	public boolean displayLevel() {
		return false;
	}
	
	@Override
	public void init() {
		addFeature(new BleedFeature(this));
		addFeature(new AutoRespawnFeature(this));
		addFeature(new LastManStandingFeature(this));
		addFeature(new FixedFoodFeature(this));
		addFeature(new MobFeature(this, Core.getCore().getEntityUtil().getAll(Type.OTHER, Type.CART, Type.PROJECTILE, Type.UTILITY)));
		addFeature(new FixedTimeFeature(this, 12000));
		addFeature(new FixedWeatherFeature(this, WeatherType.CLEAR));
		addFeature(new MapFeature(this, null));
		addFeature(new PvPFeature(this, true));
		addFeature(new SpawnFeature(this, false));
		addFeature(new CrankFeature(this, 30));
	}
	
	@Override
	public void startPhase() {
		getGame().broadCastMessage(game.getPrefix().then("Dir wurde ein Gift injeziert!").color(ChatColor.AQUA));
		getGame().broadCastMessage(game.getPrefix().then("Töten Gegner, um deinen Adrenalinspiegel hoch zu halten!!").color(ChatColor.AQUA));
		getGame().broadCastMessage(game.getPrefix().then("Wenn er zu tieft singt stribst du!").color(ChatColor.AQUA));
		final String winner = getGame().getGameData("VoteWinner");
		
		((MapFeature) getFeature(FeatureType.MAP)).setMap(winner);
		super.startPhase();
	}
	
}
