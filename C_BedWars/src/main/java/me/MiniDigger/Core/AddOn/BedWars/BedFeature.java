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
package me.MiniDigger.Core.AddOn.BedWars;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Lang.LangKeyType;
import me.MiniDigger.Core.Lang.MsgType;
import me.MiniDigger.Core.Map.MapData;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.Core.Team.Team;
import me.MiniDigger.Core.User.User;

import me.MiniDigger.CraftCore.Feature.CoreFeature;
import me.MiniDigger.CraftCore.Feature.Features.SpecateFeature;
import me.MiniDigger.CraftCore.Feature.Features.TeamDeathMatchFeature;
import me.MiniDigger.CraftCore.Feature.Features.TeamFeature;
import me.MiniDigger.CraftCore.Lang.MSG;

public class BedFeature extends CoreFeature {
	
	private Location		bed;
	private final String	teamName;
	
	public BedFeature(final Phase phase, final Location bed, final String teamName) {
		super(phase);
		this.bed = bed;
		this.teamName = teamName;
	}
	
	@Override
	public FeatureType getType() {
		return FeatureType.BED;
	}
	
	@Override
	public List<FeatureType> getDependencies() {
		return new ArrayList<FeatureType>();
	}
	
	@Override
	public List<FeatureType> getSoftDependencies() {
		return new ArrayList<FeatureType>();
	}
	
	@Override
	public List<FeatureType> getIncompabilities() {
		return new ArrayList<FeatureType>();
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void start() {
		BlockFace face = BlockFace.EAST;
		final Block bed = this.bed.getBlock();
		final Block sign = bed.getRelative(BlockFace.DOWN, 2);
		face = getFacing(sign);
		final Block head = bed.getRelative(face);
		byte flags = (byte) 8;
		byte direction = (byte) (0x0);
		
		switch (face) {
		case EAST:
			flags = (byte) (flags | 0x3);
			direction = (byte) (0x3);
			break;
			
		case SOUTH:
			flags = (byte) (flags | 0x0);
			direction = (byte) (0x0);
			break;
			
		case WEST:
			flags = (byte) (flags | 0x1);
			direction = (byte) (0x1);
			break;
			
		case NORTH:
			flags = (byte) (flags | 0x2);
			direction = (byte) (0x2);
			break;
		default:
			break;
		}
		
		bed.setTypeIdAndData(Material.BED_BLOCK.getId(), direction, false);
		head.setTypeIdAndData(Material.BED_BLOCK.getId(), flags, false);
	}
	
	@SuppressWarnings("deprecation")
	public BlockFace getFacing(final Block b) {
		return ((org.bukkit.material.Directional) b.getType().getNewData(b.getData())).getFacing();
	}
	
	@Override
	public void end() {
	}
	
	public Location getBed() {
		return bed;
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerRespawn(final PlayerRespawnEvent e) {
		final User user = Core.getCore().getUserHandler().get(e.getPlayer().getUniqueId());
		if (bed == null) {
			MSG.msg(getPhase().getGame().getGamePrefix(), LangKeyType.Game.BedWars.OUT, MsgType.IMPORTANT, user.getPlayer());
			try {
				final TeamFeature tf = (TeamFeature) getPhase().getFeature(FeatureType.TEAM);
				final Team t = tf.getTeam(user);
				getPhase().getGame().broadCastMessage(LangKeyType.Game.BedWars.SB_OUT, MsgType.IMPORTANT, t.getColor() + user.getDisplayName());
			} catch (final Exception ex) {
				ex.printStackTrace();
				getPhase().getGame().broadCastMessage(LangKeyType.Game.BedWars.SB_OUT, MsgType.IMPORTANT, user.getDisplayName());
			}
		}
		if (bed == null) {
			try {
				final MapData d = Core.getCore().getMapHandler().getMap(getPhase().getGame().getGameData("Lobby"));
				e.setRespawnLocation(d.getLocs(DyeColor.RED).values().iterator().next());
				
				final SpecateFeature s = (SpecateFeature) getPhase().getFeature(FeatureType.SPEC);
				getPhase().getGame().leave(user);
				s.spec(user);
			} catch (final Exception ex) {
				Core.getCore().getInstance().debug("no respawn found");
			}
		} else {
			try {
				final TeamDeathMatchFeature tdm = (TeamDeathMatchFeature) getPhase().getFeature(FeatureType.TEAM_DEATH_MATCH);
				tdm.setRespawns(e.getPlayer().getUniqueId(), tdm.getRespawns(e.getPlayer().getUniqueId()) + 1);
			} catch (final Exception ex) {
				Core.getCore().getInstance().debug("tdm respawn bed error: " + ex.getMessage());
			}
			e.setRespawnLocation(bed.clone().add(0.5, 1, 0.5));
		}
	}
	
	@EventHandler
	public void onBlockPlayer(final BlockPlaceEvent e) {
		if (getPhase().getGame().getPlayers().contains(e.getPlayer().getUniqueId())) {
			if (e.getBlockPlaced().getType() == Material.BED_BLOCK) {
				e.setCancelled(true);
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.HIGH)
	public void onBedDestory(final BlockBreakEvent e) {
		// Core.getCore().getInstance().debug("break");
		if (e.getBlock().getType() == Material.BED_BLOCK) {
			// Core.getCore().getInstance().debug("is bed");
			if (bed == null) {
				// Core.getCore().getInstance().debug("bed null");
				return;
			}
			final Location loc = e.getBlock().getLocation();
			loc.setPitch(0.0F);
			loc.setYaw(0.0F);
			bed.setPitch(0.0F);
			bed.setYaw(0.0F);
			
			// Core.getCore().getInstance().debug(e.getBlock().getLocation().toString() + ": " +
			// bed.toString());
			if (e.getBlock().getLocation().equals(bed) || e.getBlock().getLocation().add(1, 0, 0).equals(bed) || e.getBlock().getLocation().add(-1, 0, 0).equals(bed)
			        || e.getBlock().getLocation().add(0, 0, 1).equals(bed) || e.getBlock().getLocation().add(0, 0, -1).equals(bed)) {
				// Core.getCore().getInstance().debug("does fit");
				if (teamName != null) {
					final User user = Core.getCore().getUserHandler().get(e.getPlayer().getUniqueId());
					final TeamFeature tf = (TeamFeature) getPhase().getFeature(FeatureType.TEAM);
					final Team t = tf.getTeam(user);
					if (t.getName().equalsIgnoreCase(teamName)) {
						Core.getCore().getInstance().debug("eigenes bed");
						e.setCancelled(true);
						return;
					} else {
						getPhase().getGame().broadCastMessage(LangKeyType.Game.BedWars.BED_TEAM_DESTROYED, MsgType.IMPORTANT, teamName);
						bed = null;
					}
				} else {
					getPhase().getGame().broadCastMessage(LangKeyType.Game.BedWars.BED_DESTROYED, MsgType.IMPORTANT);
					bed = null;
				}
				
				e.getBlock().setType(Material.AIR);
				e.getBlock().getState().update(true, true);
				if (e.getBlock().getState().getData().getData() < 8) {
					e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.BED, 1));
				}
				
				e.setCancelled(true);
			}
		}
	}
}
