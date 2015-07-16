package me.MiniDigger.Core.AddOn.SurvivalGames;

import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.WeatherType;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.material.Lever;
import org.bukkit.scheduler.BukkitRunnable;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Map.MapData;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.Core.Util.EntityUtil.Type;

import me.MiniDigger.CraftCore.Feature.Features.AutoRespawnFeature;
import me.MiniDigger.CraftCore.Feature.Features.BleedFeature;
import me.MiniDigger.CraftCore.Feature.Features.FixedTimeFeature;
import me.MiniDigger.CraftCore.Feature.Features.FixedWeatherFeature;
import me.MiniDigger.CraftCore.Feature.Features.LastManStandingFeature;
import me.MiniDigger.CraftCore.Feature.Features.MapFeature;
import me.MiniDigger.CraftCore.Feature.Features.MapInfoFeature;
import me.MiniDigger.CraftCore.Feature.Features.MobFeature;
import me.MiniDigger.CraftCore.Feature.Features.PvPFeature;
import me.MiniDigger.CraftCore.Feature.Features.SpawnFeature;
import me.MiniDigger.CraftCore.Feature.Features.SpecateFeature;
import me.MiniDigger.CraftCore.Phase.CoreTimedPhase;

public class CagePhase extends CoreTimedPhase {

	public CagePhase(final SGGame sgGame, final Phase next, final int i) {
		super(sgGame, next, i);
	}

	@Override
	public String getName() {
		return "";
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
		addFeature(new MobFeature(this,
		        Core.getCore().getEntityUtil().getAll(Type.OTHER, Type.CART, Type.PROJECTILE, Type.UTILITY, Type.ANGRY, Type.PASSIV, Type.FRIENDLY)));
		addFeature(new FixedTimeFeature(this, 6000));
		addFeature(new FixedWeatherFeature(this, WeatherType.CLEAR));
		addFeature(new MapFeature(this, null, false));
		addFeature(new PvPFeature(this, true));
		addFeature(new SpawnFeature(this, true));
		addFeature(new SpecateFeature(this));
		addFeature(new MapInfoFeature(this));
	}

	@SuppressWarnings("deprecation")
	@Override
	public void startPhase() {
		final String winner = getGame().getGameData("VoteWinner");

		((MapFeature) getFeature(FeatureType.MAP)).setMap(winner);

		final MapData m = ((MapFeature) getFeature(FeatureType.MAP)).getMap();

		Core.getCore().getTaskHandler().runTaskLater(new BukkitRunnable() {

			@Override
			public void run() {
				final Location power = m.getLocs(DyeColor.ORANGE).values().iterator().next();
				power.getBlock().setType(Material.REDSTONE_BLOCK);
				power.getBlock().getState().update(true, true);
				final Block b = power.getBlock().getRelative(BlockFace.UP);
				b.setType(Material.LEVER);
				final Lever lever = new Lever(Material.LEVER, b.getData());
				lever.setFacingDirection(BlockFace.UP);
				lever.setPowered(true);
				b.setData(lever.getData());
				b.getState().update(true, true);
			}
		}, 2, this);

		super.startPhase();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void endPhase() {
		final MapData m = ((MapFeature) getFeature(FeatureType.MAP)).getMap();

		Core.getCore().getTaskHandler().runTaskLater(new BukkitRunnable() {

			@Override
			public void run() {
				final Location power = m.getLocs(DyeColor.ORANGE).values().iterator().next();
				power.getBlock().setType(Material.WOOD);
				power.getBlock().getState().update(true, true);
				final Block b = power.getBlock().getRelative(BlockFace.UP);
				b.setType(Material.LEVER);
				final Lever lever = new Lever(Material.LEVER, b.getData());
				lever.setFacingDirection(BlockFace.UP);
				lever.setPowered(false);
				b.setData(lever.getData());
				b.getState().update(true, true);
			}
		}, 20, this);

		getNextPhase().init();
		((SpawnFeature) getNextPhase().getFeature(FeatureType.SPAWN)).setSpawn(false);

		super.endPhase();
	}

}
