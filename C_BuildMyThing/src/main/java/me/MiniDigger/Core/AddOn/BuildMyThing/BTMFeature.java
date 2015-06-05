package me.MiniDigger.Core.AddOn.BuildMyThing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Map.MapData;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.Core.Scoreboard.Scoreboard;
import me.MiniDigger.Core.User.User;

import me.MiniDigger.CraftCore.Event.Events.CoreUserChatEvent;
import me.MiniDigger.CraftCore.Feature.CoreFeature;
import me.MiniDigger.CraftCore.Feature.Features.MapFeature;
import me.MiniDigger.CraftCore.Scoreboard.CoreScoreboardLine;
import me.MiniDigger.CraftCore.Scoreboard.CoreScoreboardTitle;

public class BTMFeature extends CoreFeature {
	
	public BTMFeature(Phase phase) {
		super(phase);
	}
	
	private String	           word;
	private String[]	       words;
	private UUID	           builder;
	private UUID	           oldBuilder;
	private int	               found	= 0;
	private List<UUID>	       builded	= new ArrayList<UUID>();
	private Map<UUID, Integer>	points	= new HashMap<UUID, Integer>();
	
	@Override
	public FeatureType getType() {
		return FeatureType.BTM;
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
		// Words
		try {
			List<String> l = new ArrayList<String>();
			
			File f = new File(Core.getCore().getInstance().getDataFolder(), "words.txt");
			BufferedReader r = new BufferedReader(new FileReader(f));
			
			String buffer;
			while ((buffer = r.readLine()) != null) {
				l.add(buffer);
			}
			
			r.close();
			words = l.toArray(new String[l.size()]);
		} catch (Exception ex) {
			words = new String[] { "banane", "apfel", "pferd" };// FALLBACK
		}
		
		// User
		List<UUID> available = new ArrayList<UUID>();
		for (UUID id : getPhase().getGame().getPlayers()) {
			if (!builded.contains(id)) {
				available.add(id);
			}
		}
		
		builder = available.get(Core.getCore().getRandomUtil().nextInt(available.size()));
		
		final User u = Core.getCore().getUserHandler().get(builder);
		getPhase().getGame().broadCastMessage(getPhase().getGame().getGamePrefix().getPrefix().then("Der Spieler " + u.getDisplayName() + " ist nun dran"));
		
		// Word
		word = words[Core.getCore().getRandomUtil().nextInt(words.length)];
		
		u.sendMessage(getPhase().getGame().getGamePrefix().getPrefix().then("Dein Wort ist: " + word));
		
		Core.getCore().getBuildHandler().setBuilder(u, true);
		Core.getCore().getBuildHandler().disallow(u, Material.BARRIER);
		u.getPlayer().setGameMode(GameMode.CREATIVE);
		
		// TP
		final MapData data = ((MapFeature) getPhase().getFeature(FeatureType.MAP)).getMap();
		System.out.println("map " + data.getName());
		if (data.getLocs(DyeColor.GREEN) == null) {
			System.out.println("locs null");
		}
		u.getPlayer().teleport(data.getLocs(DyeColor.GREEN).get(0));
		
		for (UUID id : getPhase().getGame().getPlayers()) {
			Location loc = data.getLocs(DyeColor.RED).get(Core.getCore().getRandomUtil().nextInt(data.getLocs(DyeColor.RED).size()));
			User o = Core.getCore().getUserHandler().get(id);
			if (o.getPlayer().getLocation().getWorld().getName().equalsIgnoreCase(loc.getWorld().getName())) {
				o.getPlayer().teleport(loc);
			}
		}
		
		updateBoards();
	}
	
	@Override
	public void end() {
		final User u = Core.getCore().getUserHandler().get(builder);
		
		Core.getCore().getBuildHandler().allow(u, Material.BARRIER);
		Core.getCore().getBuildHandler().setBuilder(u, false);
		u.getPlayer().setGameMode(GameMode.SURVIVAL);
		
		final MapData data = ((MapFeature) getPhase().getFeature(FeatureType.MAP)).getMap();
		u.getPlayer().teleport(data.getLocs(DyeColor.GREEN).get(0));
		
		getPhase().getNextPhase().init();
		
		BTMFeature b = (BTMFeature) getPhase().getNextPhase().getFeature(FeatureType.BTM);
		b.setWords(words);
		b.setBuilded(builded);
		b.setBuilder(builder);
		b.setPoints(points);
	}
	
	@EventHandler
	public void onChat(CoreUserChatEvent e) {
		if (getPhase().getGame().getPlayers().contains(e.getUser().getUUID())) {
			if (e.getMsg().equalsIgnoreCase(word)) {
				e.setCancelled(true);
				getPhase().getGame().broadCastMessage(
				        getPhase().getGame().getGamePrefix().getPrefix().then("Der Spieler " + e.getUser().getDisplayName() + " hat das Wort erraten!"));
				
				found++;
				int point = 3;
				switch (found) {
				case 1:
					point = 3;
					break;
				case 2:
					point = 2;
					break;
				default:
					point = 1;
					break;
				}
				
				int p = points.remove(e.getUser().getUUID());
				points.put(e.getUser().getUUID(), p + point);
				
				p = points.remove(builder);
				points.put(builder, p + 2);
				
				updateBoards();
			}
		}
	}
	
	private void modBoard(final Scoreboard board) {
		board.clear(DisplaySlot.SIDEBAR);
		board.setTitle(new CoreScoreboardTitle(ChatColor.GOLD + "Punkte", DisplaySlot.SIDEBAR));
		
		for (final UUID id : getPhase().getGame().getPlayers()) {
			final User u = Core.getCore().getUserHandler().get(id);
			
			if (!points.containsKey(id)) {
				points.put(id, 0);
			}
			
			board.addLine(new CoreScoreboardLine(points.get(id), u.getDisplayName(), DisplaySlot.SIDEBAR));
		}
	}
	
	public void updateBoards() {
		final List<UUID> retry = new ArrayList<UUID>();
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				
				for (final UUID uuid : getPhase().getGame().getPlayers()) {
					if (Bukkit.getPlayer(uuid) == null) {
						retry.add(uuid);
						continue;
					}
					modBoard(Core.getCore().getScoreboardHandler().getBoard(uuid));
					Core.getCore().getScoreboardHandler().update(uuid);
				}
			}
		}.runTask(Core.getCore().getInstance());
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				for (final UUID uuid : retry) {
					if (Bukkit.getPlayer(uuid) == null) {
						continue;// Fuck you
					}
					modBoard(Core.getCore().getScoreboardHandler().getBoard(uuid));
					Core.getCore().getScoreboardHandler().update(uuid);
				}
			}
		}.runTaskLater(Core.getCore().getInstance(), 20);// WAit for respawn
	}
	
	public void setBuilder(UUID builder) {
		this.builder = builder;
	}
	
	public void setBuilded(List<UUID> builded) {
		this.builded = builded;
	}
	
	public void setPoints(Map<UUID, Integer> points) {
		this.points = points;
	}
	
	private void setWords(String[] words) {
		this.words = words;
	}
}
