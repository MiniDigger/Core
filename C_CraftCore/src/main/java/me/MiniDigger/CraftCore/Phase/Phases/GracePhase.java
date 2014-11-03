/*******************************************************************************
 * Test
 *******************************************************************************/
package me.MiniDigger.CraftCore.Phase.Phases;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Game.Game;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.Core.Util.EntityUtil.Type;
import me.MiniDigger.CraftCore.Feature.Features.FixedFoodFeature;
import me.MiniDigger.CraftCore.Feature.Features.FixedHealthFeature;
import me.MiniDigger.CraftCore.Feature.Features.FixedTimeFeature;
import me.MiniDigger.CraftCore.Feature.Features.FixedWeatherFeature;
import me.MiniDigger.CraftCore.Feature.Features.MapFeature;
import me.MiniDigger.CraftCore.Feature.Features.MobFeature;
import me.MiniDigger.CraftCore.Feature.Features.PvPFeature;
import me.MiniDigger.CraftCore.Feature.Features.SpawnFeature;
import me.MiniDigger.CraftCore.Phase.CoreTimedPhase;
import mkremins.fanciful.FancyMessage;

import org.bukkit.ChatColor;
import org.bukkit.WeatherType;

public class GracePhase extends CoreTimedPhase {
	
	public GracePhase(final Game game, final Phase nextPhase, final int time) {
		super(game, nextPhase, time);
		init();
	}
	
	@Override
	public void init() {
		addFeature(new FixedHealthFeature(this));
		addFeature(new FixedFoodFeature(this));
		addFeature(new MobFeature(this, Core.getCore().getEntityUtil().getAll(Type.OTHER)));
		addFeature(new FixedTimeFeature(this, 12000));
		addFeature(new FixedWeatherFeature(this, WeatherType.CLEAR));
		addFeature(new MapFeature(this, getGame().getGameData("VoteWinner")));
		addFeature(new SpawnFeature(this, true));
		addFeature(new PvPFeature(this, false));
	}
	
	@Override
	public void startPhase() {
		final String winner = getGame().getGameData("VoteWinner");
		((MapFeature) getFeature(FeatureType.MAP)).setMap(winner);
		getGame().broadCastMessage(game.getPrefix().then("Die Fiedensphase hat begonnen!").color(ChatColor.AQUA));
		super.startPhase();
	}
	
	@Override
	public void endPhase() {
		getGame().broadCastMessage(game.getPrefix().then("Die Fiedensphase ist vorbei!").color(ChatColor.AQUA));
		super.endPhase();
	}
	
	@Override
	public String getName() {
		return "Grace";
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
		return true;
	}
	
	@Override
	public void tickLast5secs(final int secsPassed, final int secsToGo) {
		super.tickLast5secs(secsPassed, secsToGo);
		final FancyMessage msg = game.getPrefix().then("Die Friedensphase endet in ").color(ChatColor.AQUA).then(secsToGo + "").color(ChatColor.YELLOW)
		        .then(" Sekunden!").color(ChatColor.AQUA);
		game.broadCastMessage(msg);
	}
}
