package me.MiniDigger.Core.AddOn.OITC;

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
import me.MiniDigger.CraftCore.Feature.Features.LivesFeature;
import me.MiniDigger.CraftCore.Feature.Features.MapFeature;
import me.MiniDigger.CraftCore.Feature.Features.MobFeature;
import me.MiniDigger.CraftCore.Feature.Features.PvPFeature;
import me.MiniDigger.CraftCore.Feature.Features.SpawnFeature;
import me.MiniDigger.CraftCore.Feature.Features.SpecateFeature;
import me.MiniDigger.CraftCore.Phase.CorePhase;

public class OITCPhase extends CorePhase {
	
	public OITCPhase(final Game game) {
		super(game, null);
	}
	
	@Override
	public String getName() {
		return "OITC";
	}
	
	@Override
	public String getBarMessage() {
		return ChatColor.RED + "" + ChatColor.MAGIC + "||" + ChatColor.RESET + "" + ChatColor.GOLD + getName() + ChatColor.RED + "" + ChatColor.MAGIC + "||";
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
		addFeature(new FixedFoodFeature(this));
		addFeature(new MobFeature(this, Core.getCore().getEntityUtil().getAll(Type.OTHER, Type.CART, Type.PROJECTILE, Type.UTILITY)));
		addFeature(new FixedTimeFeature(this, 12000));
		addFeature(new FixedWeatherFeature(this, WeatherType.CLEAR));
		addFeature(new MapFeature(this, null));
		addFeature(new PvPFeature(this, true));
		addFeature(new SpawnFeature(this, false));
		addFeature(new OITCFeature(this));
		addFeature(new LivesFeature(this, 3));
		addFeature(new SpecateFeature(this));
	}
	
	@Override
	public void startPhase() {
		getGame().broadCastMessage(game.getPrefix().then("Du hast nur noch einen Pfeil!").color(ChatColor.AQUA));
		getGame().broadCastMessage(game.getPrefix().then("Töten Gegner, um neue zu bekommen!").color(ChatColor.AQUA));
		getGame().broadCastMessage(game.getPrefix().then("Du hast nur drei Leben!").color(ChatColor.AQUA));
		final String winner = getGame().getGameData("VoteWinner");
		
		((MapFeature) getFeature(FeatureType.MAP)).setMap(winner);
		super.startPhase();
	}
	
}
