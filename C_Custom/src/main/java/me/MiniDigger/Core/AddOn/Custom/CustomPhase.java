package me.MiniDigger.Core.AddOn.Custom;

import org.bukkit.WeatherType;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Game.Game;
import me.MiniDigger.Core.Util.EntityUtil.Type;
import me.MiniDigger.CraftCore.Feature.Features.AutoRespawnFeature;
import me.MiniDigger.CraftCore.Feature.Features.BleedFeature;
import me.MiniDigger.CraftCore.Feature.Features.FixedFoodFeature;
import me.MiniDigger.CraftCore.Feature.Features.FixedTimeFeature;
import me.MiniDigger.CraftCore.Feature.Features.FixedWeatherFeature;
import me.MiniDigger.CraftCore.Feature.Features.KitFeature;
import me.MiniDigger.CraftCore.Feature.Features.LivesFeature;
import me.MiniDigger.CraftCore.Feature.Features.MapFeature;
import me.MiniDigger.CraftCore.Feature.Features.MobFeature;
import me.MiniDigger.CraftCore.Feature.Features.NoDropFeature;
import me.MiniDigger.CraftCore.Feature.Features.NoPickupFeature;
import me.MiniDigger.CraftCore.Feature.Features.PvPFeature;
import me.MiniDigger.CraftCore.Feature.Features.SpawnFeature;
import me.MiniDigger.CraftCore.Feature.Features.SpecateFeature;
import me.MiniDigger.CraftCore.Phase.CorePhase;

public class CustomPhase extends CorePhase {
	public CustomPhase(final Game game) {
		super(game, null);
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
		addFeature(new MobFeature(this,
				Core.getCore().getEntityUtil().getAll(Type.OTHER, Type.CART, Type.PROJECTILE, Type.UTILITY)));
		addFeature(new FixedTimeFeature(this, 6000));
		addFeature(new FixedWeatherFeature(this, WeatherType.CLEAR));
		addFeature(new MapFeature(this, null, true));
		addFeature(new PvPFeature(this, true));
		addFeature(new SpawnFeature(this, false));
		addFeature(new CustomFeature(this));
		addFeature(new LivesFeature(this, 3));
		addFeature(new SpecateFeature(this));
		addFeature(new KitFeature(this));
		addFeature(new NoPickupFeature(this));
		addFeature(new NoDropFeature(this));
	}

	@Override
	public void startPhase() {
	}

	@Override
	public String getName() {
		return "Custom";
	}
}
