/**
 * █████████████████████████████████████████████████████████████████████████████████████████████████████████████████
 * █░░░░░░░░░░░░░░█░░░░░░██░░░░░░█░░░░░░░░░░░░░░████░░░░░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░░░░░░░░░░░███░░░░░░░░░░░░░░█
 * █░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░████░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀▄▀░░███░░▄▀▄▀▄▀▄▀▄▀░░█
 * █░░░░░░▄▀░░░░░░█░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░░░░░████░░▄▀░░░░░░░░░░█░░▄▀░░░░░░▄▀░░█░░▄▀░░░░░░░░▄▀░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░████████████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░████░░▄▀░░███░░▄▀░░█████████
 * █████░░▄▀░░█████░░▄▀░░░░░░▄▀░░█░░▄▀░░░░░░░░░░████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░░░▄▀░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀▄▀░░███░░▄▀▄▀▄▀▄▀▄▀░░█
 * █████░░▄▀░░█████░░▄▀░░░░░░▄▀░░█░░▄▀░░░░░░░░░░████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░▄▀░░░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░████████████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░██░░▄▀░░█████░░▄▀░░█████████
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░░░░░████░░▄▀░░░░░░░░░░█░░▄▀░░░░░░▄▀░░█░░▄▀░░██░░▄▀░░░░░░█░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░████░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░██░░▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█
 * █████░░░░░░█████░░░░░░██░░░░░░█░░░░░░░░░░░░░░████░░░░░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░██░░░░░░░░░░█░░░░░░░░░░░░░░█
 * █████████████████████████████████████████████████████████████████████████████████████████████████████████████████
 *
 * Copyright © MiniDigger and others - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Martin Benndorf <admin@minidigger.me>, 2013-2015 and others
 */
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
import me.MiniDigger.Core.Game.Game;
import me.MiniDigger.Core.Map.MapData;
import me.MiniDigger.Core.Util.EntityUtil.Type;

import me.MiniDigger.CraftCore.Feature.Features.AutoRespawnFeature;
import me.MiniDigger.CraftCore.Feature.Features.BleedFeature;
import me.MiniDigger.CraftCore.Feature.Features.BuildFeature;
import me.MiniDigger.CraftCore.Feature.Features.FixedTimeFeature;
import me.MiniDigger.CraftCore.Feature.Features.FixedWeatherFeature;
import me.MiniDigger.CraftCore.Feature.Features.LastManStandingFeature;
import me.MiniDigger.CraftCore.Feature.Features.MapFeature;
import me.MiniDigger.CraftCore.Feature.Features.MobFeature;
import me.MiniDigger.CraftCore.Feature.Features.PvPFeature;
import me.MiniDigger.CraftCore.Feature.Features.SpawnFeature;
import me.MiniDigger.CraftCore.Feature.Features.SpecateFeature;
import me.MiniDigger.CraftCore.Feature.Features.WorldBoarderFeature;
import me.MiniDigger.CraftCore.Phase.CorePhase;

import mkremins.fanciful.FancyMessage;

public class SGPhase2 extends CorePhase {

	public SGPhase2(final Game game) {
		super(game, null);
	}

	@Override
	public String getName() {
		return "SG";
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
		addFeature(new MobFeature(this, Core.getCore().getEntityUtil().getAll(Type.OTHER, Type.CART, Type.PROJECTILE, Type.UTILITY, Type.FRIENDLY)));
		addFeature(new FixedTimeFeature(this, 6000));
		addFeature(new FixedWeatherFeature(this, WeatherType.CLEAR));
		addFeature(new MapFeature(this, null, true));
		addFeature(new PvPFeature(this, true));
		addFeature(new SpawnFeature(this, false));
		addFeature(new SpecateFeature(this));
		addFeature(new BuildFeature(this, Material.LEAVES, Material.LEAVES_2, Material.VINE, Material.RED_MUSHROOM, Material.BROWN_MUSHROOM, Material.COAL_ORE, Material.IRON_ORE, Material.DIAMOND_ORE, Material.GOLD_ORE,
				Material.REDSTONE_ORE, Material.LAPIS_ORE, Material.MELON, Material.MELON_BLOCK, Material.WHEAT, Material.PUMPKIN));
		addFeature(new WorldBoarderFeature(this, true));
	}

	@SuppressWarnings("deprecation")
	@Override
	public void startPhase() {
		getGame().broadCastMessage(new FancyMessage("Die WorldBoarder verkleinert sich nun!"));
		final String winner = getGame().getGameData("VoteWinner");

		((MapFeature) getFeature(FeatureType.MAP)).setMap(winner);

		final MapData m = ((MapFeature) getFeature(FeatureType.MAP)).getMap();
		((WorldBoarderFeature) getFeature(FeatureType.WORLDBOARDER)).fakeStart(m.getLocs(DyeColor.BLACK).values().iterator().next(), 5 * 60, 25);

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
		}, 2, this);

		super.startPhase();
	}
}
