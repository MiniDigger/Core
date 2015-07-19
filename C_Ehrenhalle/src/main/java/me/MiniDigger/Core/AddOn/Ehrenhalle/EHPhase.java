package me.MiniDigger.Core.AddOn.Ehrenhalle;

import org.bukkit.WeatherType;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Util.EntityUtil.Type;

import me.MiniDigger.CraftCore.Feature.Features.FixedFoodFeature;
import me.MiniDigger.CraftCore.Feature.Features.FixedHealthFeature;
import me.MiniDigger.CraftCore.Feature.Features.FixedTimeFeature;
import me.MiniDigger.CraftCore.Feature.Features.FixedWeatherFeature;
import me.MiniDigger.CraftCore.Feature.Features.MapFeature;
import me.MiniDigger.CraftCore.Feature.Features.MobFeature;
import me.MiniDigger.CraftCore.Feature.Features.NoDropFeature;
import me.MiniDigger.CraftCore.Feature.Features.NoPickupFeature;
import me.MiniDigger.CraftCore.Feature.Features.PvPFeature;
import me.MiniDigger.CraftCore.Feature.Features.SpawnFeature;
import me.MiniDigger.CraftCore.Phase.CorePhase;

public class EHPhase extends CorePhase {
	
	@Override
	public String getName() {
		return "Hub";
	}

	@Override
	public void startPhase() {
		super.startPhase();
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
		addFeature(new EHFeature(this));
		addFeature(new FixedFoodFeature(this));
		addFeature(new MobFeature(this, Core.getCore().getEntityUtil().getAll(Type.OTHER)));
		addFeature(new FixedTimeFeature(this, 6000));
		addFeature(new FixedWeatherFeature(this, WeatherType.CLEAR));
		addFeature(new MapFeature(this, "Ehrenhalle", true));
		addFeature(new SpawnFeature(this, true));
		addFeature(new PvPFeature(this, false));
		addFeature(new NoDropFeature(this));
		addFeature(new NoPickupFeature(this));
	}
}