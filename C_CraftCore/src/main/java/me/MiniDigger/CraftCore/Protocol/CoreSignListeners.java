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
package me.MiniDigger.CraftCore.Protocol;

import java.util.ArrayList;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Protocol.SignListeners;
import me.MiniDigger.CraftCore.Feature.Features.TeamFeature;
import me.MiniDigger.CraftCore.Feature.Features.TeamSelectFeature;

import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class CoreSignListeners implements SignListeners {
	
	private final ArrayList<SignListener>	listener	= new ArrayList<SignListener>();
	
	@Override
	public void register(final SignListener l) {
		listener.add(l);
	}
	
	@Override
	public void init() {
		register(new SignListener("[TeamJoin]", new SignAction() {
			
			@Override
			public void run() {
				final String team = getSign().getLine(1);
				final TeamSelectFeature tf = (TeamFeature) Core.getCore().getGameHandler().getMainGame().getPhase().getFeature(FeatureType.TEAM);
				if (tf == null) {
					return;
				}
				if (tf.getTeam(getPlayer()) != null) {
					tf.getTeam(getPlayer()).leave(getPlayer().getUniqueId());
				}
				tf.getTeam(team).join(getPlayer().getUniqueId());
			}
		}, false));
		
		register(new SignListener("[TEST]", new SignAction() {
			
			@Override
			public void run() {
				System.out.println("Player " + getPlayer().getName() + " punshed sign with lines: " + Core.getCore().getStringUtil().toString(getSign().getLines()));
			}
		}, false));
	}
	
	@Override
	@EventHandler
	public void onInteract(final PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			final Block b = e.getClickedBlock();
			if (b.getState() instanceof Sign) {
				final Sign sign = (Sign) b.getState();
				final Thread t = new Thread() {
					
					@Override
					public void run() {
						for (final String line : sign.getLines()) {
							for (final SignListener l : listener) {
								if (l.getKey().equalsIgnoreCase(line)) {
									l.getAction().setSign(sign);
									l.getAction().setPlayer(e.getPlayer());
									l.doAction(sign);
									if (l.isFinal()) {
										return;
									}
								}
							}
						}
					}
				};
				t.setName("SignKeySearcher");
				t.start();
			}
		}
	}
}
