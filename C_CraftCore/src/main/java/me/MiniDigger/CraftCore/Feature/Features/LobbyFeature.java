package me.MiniDigger.CraftCore.Feature.Features;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.scheduler.BukkitRunnable;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Game.GameType;
import me.MiniDigger.Core.Phase.Phase;

import me.MiniDigger.CraftCore.Feature.CoreFeature;

public class LobbyFeature extends CoreFeature {
	
	private Map<Integer, Map<Integer, String[]>>	text1	= new HashMap<Integer, Map<Integer, String[]>>();
	private Map<Integer, Map<Integer, String[]>>	text2	= new HashMap<Integer, Map<Integer, String[]>>();
	
	public LobbyFeature(Phase phase) {
		super(phase);
	}
	
	@Override
	public FeatureType getType() {
		return FeatureType.LOBBYFEATURE;
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
	
	@Override
	public void start() {
		new BukkitRunnable() {
			
			@Override
			public void run() {
				int down = 3;
				int side = 4;
				
				text1 = SignStorage.getOne(getPhase().getGame().getType());
				text2 = SignStorage.getTwo(getPhase().getGame().getType());
				
				World w = Bukkit.getWorld(getPhase().getGame().getGameData("Lobby"));
				Location origin = new Location(w, -48, 4, -51);
				Block blockOrigion = origin.getBlock();
				
				Block start = blockOrigion.getRelative(BlockFace.SOUTH, 5);
				start = start.getRelative(BlockFace.UP, 3);
				
				// Wall 1
				for (int x = 0; x < down; x++) {
					for (int i = 0; i < side; i++) {
						start.setType(Material.AIR);
						start.setType(Material.WALL_SIGN);
						
						Sign sign = (Sign) start.getState();
						org.bukkit.material.Sign mat = ((org.bukkit.material.Sign) start.getState().getData());
						mat.setFacingDirection(BlockFace.EAST);
						sign.setData(mat);
						
						sign.setLine(0, text1.get(x).get(i)[0]);
						sign.setLine(1, text1.get(x).get(i)[1]);
						sign.setLine(2, text1.get(x).get(i)[2]);
						sign.setLine(3, text1.get(x).get(i)[3]);
						
						sign.update();
						
						start = start.getRelative(BlockFace.NORTH, 1);
					}
					start = start.getRelative(BlockFace.DOWN);
					start = start.getRelative(BlockFace.SOUTH, side);
				}
				
				start = blockOrigion.getRelative(BlockFace.EAST, 2);
				start = start.getRelative(BlockFace.UP, 3);
				
				// Wall 2
				for (int x = 0; x < down; x++) {
					for (int i = 0; i < side; i++) {
						start.setType(Material.AIR);
						start.setType(Material.WALL_SIGN);
						
						Sign sign = (Sign) start.getState();
						org.bukkit.material.Sign mat = ((org.bukkit.material.Sign) start.getState().getData());
						mat.setFacingDirection(BlockFace.SOUTH);
						sign.setData(mat);
						
						sign.setLine(0, text2.get(x).get(i)[0]);
						sign.setLine(1, text2.get(x).get(i)[1]);
						sign.setLine(2, text2.get(x).get(i)[2]);
						sign.setLine(3, text2.get(x).get(i)[3]);
						
						sign.update();
						
						start = start.getRelative(BlockFace.EAST, 1);
					}
					start = start.getRelative(BlockFace.DOWN);
					start = start.getRelative(BlockFace.WEST, side);
				}
			}
		}.runTaskLater(Core.getCore().getInstance(), 20);//wait for chunks loaded
	}
	
	@Override
	public void end() {
		
	}
	
	private static class SignStorage {
		
		private static Map<Integer, Map<Integer, String[]>> bedwarsOne() {
			Map<Integer, Map<Integer, String[]>> result = new HashMap<Integer, Map<Integer, String[]>>();
			
			// Line 1
			Map<Integer, String[]> temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(0, temp);
			
			// Line 2
			temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(1, temp);
			
			// Line 3
			temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(2, temp);
			
			return result;
		}
		
		private static Map<Integer, Map<Integer, String[]>> bedwarsTwo() {
			Map<Integer, Map<Integer, String[]>> result = new HashMap<Integer, Map<Integer, String[]>>();
			
			// Line 1
			Map<Integer, String[]> temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(0, temp);
			
			// Line 2
			temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(1, temp);
			
			// Line 3
			temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(2, temp);
			
			return result;
		}
		
		private static Map<Integer, Map<Integer, String[]>> crankOne() {
			Map<Integer, Map<Integer, String[]>> result = new HashMap<Integer, Map<Integer, String[]>>();
			
			// Line 1
			Map<Integer, String[]> temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(0, temp);
			
			// Line 2
			temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(1, temp);
			
			// Line 3
			temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(2, temp);
			
			return result;
		}
		
		private static Map<Integer, Map<Integer, String[]>> crankTwo() {
			Map<Integer, Map<Integer, String[]>> result = new HashMap<Integer, Map<Integer, String[]>>();
			
			// Line 1
			Map<Integer, String[]> temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(0, temp);
			
			// Line 2
			temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(1, temp);
			
			// Line 3
			temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(2, temp);
			
			return result;
		}
		
		private static Map<Integer, Map<Integer, String[]>> getthedropOne() {
			Map<Integer, Map<Integer, String[]>> result = new HashMap<Integer, Map<Integer, String[]>>();
			
			// Line 1
			Map<Integer, String[]> temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(0, temp);
			
			// Line 2
			temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(1, temp);
			
			// Line 3
			temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(2, temp);
			
			return result;
		}
		
		private static Map<Integer, Map<Integer, String[]>> getthedropTwo() {
			Map<Integer, Map<Integer, String[]>> result = new HashMap<Integer, Map<Integer, String[]>>();
			
			// Line 1
			Map<Integer, String[]> temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(0, temp);
			
			// Line 2
			temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(1, temp);
			
			// Line 3
			temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(2, temp);
			
			return result;
		}
		
		private static Map<Integer, Map<Integer, String[]>> oitcOne() {
			Map<Integer, Map<Integer, String[]>> result = new HashMap<Integer, Map<Integer, String[]>>();
			
			// Line 1
			Map<Integer, String[]> temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(0, temp);
			
			// Line 2
			temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(1, temp);
			
			// Line 3
			temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(2, temp);
			
			return result;
		}
		
		private static Map<Integer, Map<Integer, String[]>> oitcTwo() {
			Map<Integer, Map<Integer, String[]>> result = new HashMap<Integer, Map<Integer, String[]>>();
			
			// Line 1
			Map<Integer, String[]> temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(0, temp);
			
			// Line 2
			temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(1, temp);
			
			// Line 3
			temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(2, temp);
			
			return result;
		}
		
		private static Map<Integer, Map<Integer, String[]>> ultraspleefOne() {
			Map<Integer, Map<Integer, String[]>> result = new HashMap<Integer, Map<Integer, String[]>>();
			
			// Line 1
			Map<Integer, String[]> temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(0, temp);
			
			// Line 2
			temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(1, temp);
			
			// Line 3
			temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(2, temp);
			
			return result;
		}
		
		private static Map<Integer, Map<Integer, String[]>> ultraspleefTwo() {
			Map<Integer, Map<Integer, String[]>> result = new HashMap<Integer, Map<Integer, String[]>>();
			
			// Line 1
			Map<Integer, String[]> temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(0, temp);
			
			// Line 2
			temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(1, temp);
			
			// Line 3
			temp = new HashMap<Integer, String[]>();
			temp.put(0, new String[] { "", "", "", "" });
			temp.put(1, new String[] { "", "", "", "" });
			temp.put(2, new String[] { "", "", "", "" });
			temp.put(3, new String[] { "", "", "", "" });
			result.put(2, temp);
			
			return result;
		}
		
		private static Map<Integer, Map<Integer, String[]>> placeholder() {
			Map<Integer, Map<Integer, String[]>> result = new HashMap<Integer, Map<Integer, String[]>>();
			
			for (int x = 0; x < 3; x++) {
				Map<Integer, String[]> temp = new HashMap<Integer, String[]>();
				for (int i = 0; i < 4; i++) {
					temp.put(i, new String[] { "", "", "", "" });
				}
				result.put(x, temp);
			}
			
			return result;
		}
		
		public static Map<Integer, Map<Integer, String[]>> getOne(GameType type) {
			switch (type) {
			case BEDWARS:
				return bedwarsOne();
			case CRANK:
				return crankOne();
			case GETTHEDROP:
				return getthedropOne();
			case LOBBY:
				return placeholder();
			case NOTHING:
				return placeholder();
			case OITC:
				return oitcOne();
			case TICTACTOE:
				return placeholder();
			case ULTRASPLEEF:
				return ultraspleefOne();
			}
			
			return placeholder();
		}
		
		public static Map<Integer, Map<Integer, String[]>> getTwo(GameType type) {
			switch (type) {
			case BEDWARS:
				return bedwarsTwo();
			case CRANK:
				return crankTwo();
			case GETTHEDROP:
				return getthedropTwo();
			case LOBBY:
				return placeholder();
			case NOTHING:
				return placeholder();
			case OITC:
				return oitcTwo();
			case TICTACTOE:
				return placeholder();
			case ULTRASPLEEF:
				return ultraspleefTwo();
			}
			
			return placeholder();
		}
	}
}
