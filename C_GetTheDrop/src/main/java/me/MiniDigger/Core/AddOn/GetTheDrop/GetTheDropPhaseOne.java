package me.MiniDigger.Core.AddOn.GetTheDrop;



import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Feature.Feature;
import me.MiniDigger.Core.Game.Game;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.Core.Util.EntityUtil.Type;

import org.bukkit.ChatColor;
import org.bukkit.WeatherType;

public class GetTheDropPhaseOne extends TimedPhase {
	
	public GetTheDropPhaseOne(Game game, Phase next, int secs) {
		super(game, next, secs);
		init();
	}
	
	@Override
	public String getName() {
		return "GetTheDropOne";
	}
	
	@Override
	public String getBarMessage() {
		return "GetTheDrops!";
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
		addFeature(new FixedHealthFeature(this));
		addFeature(new ClearInvFeature(this, false));
		addFeature(new FixedFoodFeature(this));
		addFeature(new MobFeature(this, Core.getCore().getEntityUtil().getAll(Type.OTHER, Type.CART, Type.PROJECTILE, Type.UTILITY)));
		addFeature(new FixedTimeFeature(this, 12000));
		addFeature(new FixedWeatherFeature(this, WeatherType.CLEAR));
		addFeature(new DropFeature(this));
		addFeature(new MapFeature(this, "GTD_Arena"));
		addFeature(new PvPFeature(this, false));
		addFeature(new SpawnFeature(this, false));
	}
	
	@Override
	public void endPhase() {
		getGame().broadCastMessage(game.getPrefix().then("Die Phase ist vorbei!").color(ChatColor.AQUA));
		String winner = getGame().getGameData("VoteWinner");
		Core.getCore().getWorldHandler().copyWorld(winner);
		Core.getCore().getWorldHandler().loadWorld(winner);
		super.endPhase();
	}
	
	@Override
	public void startPhase() {
		getGame().broadCastMessage(game.getPrefix().then("Die Erste Phase hat begonnen!").color(ChatColor.AQUA));
		getGame().broadCastMessage(game.getPrefix().then("Sammle deine Items und crafte deine Waffen!").color(ChatColor.AQUA));
		WorldHandler.getInstance().copyWorld("GTD_Arena");
		WorldHandler.getInstance().loadWorld("GTD_Arena");
		super.startPhase();
	}
	
	@Override
	public void tick(int secsPassed, int secsToGo) {
		DropFeature drop = (DropFeature) getFeature(Feature.DROP);
		drop.drop(false);
		super.tick(secsPassed, secsToGo);
	}
}
