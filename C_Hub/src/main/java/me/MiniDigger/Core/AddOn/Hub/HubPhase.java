package me.MiniDigger.Core.AddOn.Hub;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Game.Game;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.Core.Util.EntityUtil.Type;
import me.MiniDigger.CraftCore.Feature.Features.DoubleJumpFeature;
import me.MiniDigger.CraftCore.Feature.Features.FixedFoodFeature;
import me.MiniDigger.CraftCore.Feature.Features.FixedHealthFeature;
import me.MiniDigger.CraftCore.Feature.Features.FixedTimeFeature;
import me.MiniDigger.CraftCore.Feature.Features.FixedWeatherFeature;
import me.MiniDigger.CraftCore.Feature.Features.JumpPadFeature;
import me.MiniDigger.CraftCore.Feature.Features.MapFeature;
import me.MiniDigger.CraftCore.Feature.Features.MobFeature;
import me.MiniDigger.CraftCore.Feature.Features.PvPFeature;
import me.MiniDigger.CraftCore.Feature.Features.SpawnFeature;
import me.MiniDigger.CraftCore.Phase.CorePhase;

import org.bukkit.WeatherType;

public class HubPhase extends CorePhase {
	
	private String	barMsg;
	
	public HubPhase(final Game game, final Phase nextPhase, final String barMsg) {
		super(game, nextPhase);
		this.barMsg = barMsg;
		init();
	}
	
	public String getBarMsg() {
		return barMsg;
	}
	
	public void setBarMsg(final String barMsg) {
		this.barMsg = barMsg;
	}
	
	@Override
	public String getName() {
		return "Hub";
	}
	
	@Override
	public String getBarMessage() {
		return barMsg;
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
		addFeature(new FixedHealthFeature(this));
		addFeature(new DoubleJumpFeature(this));
		addFeature(new JumpPadFeature(this));
		addFeature(new HubFeature(this));
		addFeature(new FixedFoodFeature(this));
		addFeature(new MobFeature(this, Core.getCore().getEntityUtil().getAll(Type.OTHER)));
		addFeature(new FixedTimeFeature(this, 12000));
		addFeature(new FixedWeatherFeature(this, WeatherType.CLEAR));
		addFeature(new MapFeature(this, "Spawn"));
		addFeature(new SpawnFeature(this, true));
		addFeature(new PvPFeature(this, false));
	}
	
}
