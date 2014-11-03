/*******************************************************************************
 * Test
 *******************************************************************************/
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
 */
package me.MiniDigger.Core.AddOn.BedWars;

import java.util.ArrayList;
import java.util.List;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Map.MapData;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.Core.User.User;
import me.MiniDigger.CraftCore.Feature.CoreFeature;

import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class BedFeature extends CoreFeature {
	
	private Location	 bed;
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
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerRespawn(final PlayerRespawnEvent e) {
		if (bed == null) {
			final User user = Core.getCore().getUserHandler().get(e.getPlayer().getUniqueId());
			user.sendMessage(getPhase().getGame().getPrefix().then("Du bist draußen, weil dein Bett kaputt ist!"));
			if (teamName != null) {
				getPhase().getGame().broadCastMessage(getPhase().getGame().getPrefix().then(teamName + ": " + user.getDisplayName() + " ist draußen!"));
			} else {
				getPhase().getGame().broadCastMessage(getPhase().getGame().getPrefix().then(user.getDisplayName() + " ist draußen!"));
			}
		}
		if (bed == null) {
			final MapData d = Core.getCore().getMapHandler().getMap(getPhase().getGame().getGameData("Lobby"));
			e.setRespawnLocation(d.getLocs(DyeColor.RED).values().iterator().next());
		} else {
			e.setRespawnLocation(bed.add(0.5, 1, 0.5));
		}
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onBedDestory(final BlockBreakEvent e) {
		if (e.getBlock().getType() == Material.BED_BLOCK) {
			if (e.getBlock().getLocation().equals(bed) || e.getBlock().getLocation().add(1, 0, 0).equals(bed) || e.getBlock().getLocation().add(-1, 0, 0).equals(bed)
			        || e.getBlock().getLocation().add(0, 0, 1).equals(bed) || e.getBlock().getLocation().add(0, 0, -1).equals(bed)) {
				if (teamName != null) {
					getPhase().getGame().broadCastMessage(getPhase().getGame().getPrefix().then("Das Bed von Team ").then(teamName).then(" wurde zerstört!"));
					bed = null;
				} else {
					getPhase().getGame().broadCastMessage(getPhase().getGame().getPrefix().then("Das Bed wurde zerstört!"));
					bed = null;
				}
			} else {
				System.out.println("Wrong bed? " + (teamName != null ? teamName : "") + " " + bed);
			}
		}
	}
}
