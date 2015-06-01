package me.MiniDigger.Core.AddOn.SuchenUndVerstecken;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.Core.Prefix.Prefix;
import me.MiniDigger.Core.Scoreboard.Scoreboard;
import me.MiniDigger.Core.User.User;

import me.MiniDigger.CraftCore.Event.Events.CoreUserLeaveGameEvent;
import me.MiniDigger.CraftCore.Feature.CoreFeature;
import me.MiniDigger.CraftCore.Scoreboard.CoreScoreboardLine;
import me.MiniDigger.CraftCore.Scoreboard.CoreScoreboardTitle;

public class SUVFeature extends CoreFeature {
	
	private UUID	   sucher1;
	private UUID	   sucher2;
	
	private List<UUID>	team1	= new ArrayList<UUID>();
	private List<UUID>	team2	= new ArrayList<UUID>();
	
	public SUVFeature(Phase phase) {
		super(phase);
	}
	
	@Override
	public FeatureType getType() {
		return FeatureType.SUV;
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
		showLives();
	}
	
	@Override
	public void end() {
		Core.getCore().getScoreboardHandler().clearAll();
	}
	
	private void modBoard(final Scoreboard board) {
		board.clear(DisplaySlot.SIDEBAR);
		board.setTitle(new CoreScoreboardTitle(ChatColor.GOLD + "Noch da", DisplaySlot.SIDEBAR));
		
		board.addLine(new CoreScoreboardLine(3, "Team " + Core.getCore().getUserHandler().get(sucher1).getDisplayName(), DisplaySlot.SIDEBAR));
		board.addLine(new CoreScoreboardLine(2, team1.size() + " Spieler", DisplaySlot.SIDEBAR));
		board.addLine(new CoreScoreboardLine(1, "Team " + Core.getCore().getUserHandler().get(sucher2).getDisplayName(), DisplaySlot.SIDEBAR));
		board.addLine(new CoreScoreboardLine(0, team2.size() + " Spieler", DisplaySlot.SIDEBAR));
	}
	
	public void showLives() {
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
	
	public void check(UUID id1, UUID id2, boolean leave) {
		if (id1 == null) {
			if (team1.contains(id2)) {
				id1 = sucher2;
			} else {
				id1 = sucher1;
			}
		}
		
		if (id1 == sucher1 || id1 == sucher2) {
			// User s = Core.getCore().getUserHandler().get(id1);
			User v = Core.getCore().getUserHandler().get(id2);
			
			v.getPlayer().damage(9000.0);
			Location l = v.getPlayer().getLocation();
			l.getWorld().createExplosion(l.getX(), l.getY(), l.getZ(), 1, false, false);
			
			if (id1 == sucher1 && team2.contains(id2)) {
				User os = Core.getCore().getUserHandler().get(sucher2);
				team2.remove(id2);
				getPhase().getGame().broadCastMessage(
				        Prefix.getByGameType(getPhase().getGame().getType())
				                .getPrefix()
				                .then("Der Spieler " + v.getDisplayName() + " aus dem Team von " + os.getDisplayName()
				                        + (leave ? " hat das Spiel verlassen!" : " wurde gefunden!")));
				getPhase().getGame().broadCastMessage(
				        Prefix.getByGameType(getPhase().getGame().getType()).getPrefix().then("Es sind noch " + team2.size() + " Spieler in diesem Team am Leben!"));
			} else if (id1 == sucher2 && team1.contains(id2)) {
				User os = Core.getCore().getUserHandler().get(sucher1);
				team1.remove(id2);
				getPhase().getGame().broadCastMessage(
				        Prefix.getByGameType(getPhase().getGame().getType())
				                .getPrefix()
				                .then("Der Spieler " + v.getDisplayName() + " aus dem Team von " + os.getDisplayName()
				                        + (leave ? " hat das Spiel verlassen!" : " wurde gefunden!")));
				getPhase().getGame().broadCastMessage(
				        Prefix.getByGameType(getPhase().getGame().getType()).getPrefix().then("Es sind noch " + team1.size() + " Spieler in diesem Team am Leben!"));
			}
			
			showLives();
			checkEnd();
		}
	}
	
	public void checkEnd() {
		if (team1.size() == 0) {
			User w = Core.getCore().getUserHandler().get(sucher2);
			User[] ww = new User[team2.size() + 1];
			for (int i = 1; i <= ww.length - 1; i++) {
				ww[i] = Core.getCore().getUserHandler().get(team2.get(i));
			}
			ww[0] = w;
			getPhase().getGame().end(w);
		} else if (team2.size() == 0) {
			User w = Core.getCore().getUserHandler().get(sucher1);
			User[] ww = new User[team1.size() + 1];
			for (int i = 1; i <= ww.length - 1; i++) {
				ww[i] = Core.getCore().getUserHandler().get(team1.get(i));
			}
			ww[0] = w;
			getPhase().getGame().end(w);
		}
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEntityEvent e) {
		if (e.getRightClicked() instanceof Player) {
			if (getPhase().getGame().getPlayers().contains(e.getPlayer().getUniqueId()) && getPhase().getGame().getPlayers().contains(e.getRightClicked().getUniqueId())) {
				check(e.getPlayer().getUniqueId(), e.getRightClicked().getUniqueId(), false);
			}
		}
	}
	
	@EventHandler
	public void onQuit(final CoreUserLeaveGameEvent e) {
		if (e.getGame().getIdentifier() == getPhase().getGame().getIdentifier()) {
			check(null, e.getUser().getUUID(), true);
			checkEnd();
		}
	}
	
	public void setSucher1(UUID id) {
		this.sucher1 = id;
	}
	
	public void setSucher2(UUID id) {
		this.sucher2 = id;
	}
}
