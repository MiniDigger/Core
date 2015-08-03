package me.MiniDigger.Core.AddOn.Ehrenhalle;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.User.User;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.event.DespawnReason;
import net.citizensnpcs.api.npc.NPC;

public class EHNPCs {
	private EHScanner s;
	private EHData d;

	private List<NPC> npcs = new ArrayList<>();
	private List<Location> usedLocs = new ArrayList<>();

	public EHNPCs(EHScanner s, EHData d) {
		this.s = s;
		this.d = d;
	}

	public void respawn(String newName, double amount) {
		for (NPC npc : npcs) {
			npc.despawn(DespawnReason.REMOVAL);
		}
		npcs = new ArrayList<>();
		spawn(newName, amount);
	}

	public void spawn(String newName, double amount) {
		usedLocs = new ArrayList<>();
		for (String name : d.getDonations().keySet()) {
			NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, name);

			Location loc = null;
			for (Location l : s.getTower1()) {
				if (!usedLocs.contains(l)) {
					loc = l;
				}
			}
			if (loc == null) {
				for (Location l : s.getTower2()) {
					if (!usedLocs.contains(l)) {
						loc = l;
					}
				}
				if (loc == null) {
					for (Location l : s.getTower3()) {
						if (!usedLocs.contains(l)) {
							loc = l;
						}
					}
					if (loc == null) {
						for (Location l : s.getWall1()) {
							if (!usedLocs.contains(l)) {
								loc = l;
							}
						}
						if (loc == null) {
							for (Location l : s.getWall2()) {
								if (!usedLocs.contains(l)) {
									loc = l;
								}
							}
							if (loc == null) {
								for (Location l : s.getWall3()) {
									if (!usedLocs.contains(l)) {
										loc = l;
									}
								}
							}
						}
					}
				}
			}

			if (loc == null) {
				System.out.println("Well, we are fucked");
				return;
			}

			usedLocs.add(loc);
			loc = loc.clone().add(0.5, -0.5, 0.5);
			loc.setDirection(loc.getDirection().multiply(-1));

			if (newName != null && name.equalsIgnoreCase(newName)) {
				effect(loc, name, amount);
			}

			npcs.add(npc);
			npc.spawn(loc);
		}
	}

	public void effect(Location loc, String name, double amount) {
		for (User u : Core.getCore().getUserHandler().getOnlineUsers()) {
			Core.getCore().getTitleHandler().sendTitle(u.getPlayer(), 1 * 20, 150, 1 * 20, ChatColor.GOLD + "Donation");
			Core.getCore().getTitleHandler().sendSubTitle(u.getPlayer(), 1 * 20, 150, 1 * 20,
					ChatColor.GOLD + name + " hat " + amount + " Euro gespendet!");

			FireworkEffect e = FireworkEffect.builder().flicker(true).with(Type.BALL).trail(false)
					.withColor(Color.ORANGE, Color.YELLOW, Color.RED).build();
			Firework fw = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
			FireworkMeta m = fw.getFireworkMeta();
			m.setPower(0);
			m.addEffect(e);
			fw.setFireworkMeta(m);

			for (Location l : s.getFireworksI()) {
				FireworkEffect e1 = FireworkEffect.builder().flicker(true).with(Type.BURST).trail(false)
						.withColor(Color.YELLOW).withFade(Color.AQUA).build();
				Firework fw1 = (Firework) l.getWorld().spawnEntity(l, EntityType.FIREWORK);
				FireworkMeta m1 = fw1.getFireworkMeta();
				m1.setPower(0);
				m1.addEffect(e1);
				fw1.setFireworkMeta(m1);
			}

			new BukkitRunnable() {

				@Override
				public void run() {
					for (Location l : s.getFireworksO()) {
						FireworkEffect e1 = FireworkEffect.builder().flicker(true).with(Type.STAR).trail(false)
								.withColor(Color.GREEN, Color.BLACK).build();
						Firework fw1 = (Firework) l.getWorld().spawnEntity(l, EntityType.FIREWORK);
						FireworkMeta m1 = fw1.getFireworkMeta();
						m1.setPower(0);
						m1.addEffect(e1);
						fw1.setFireworkMeta(m1);
					}
				}
			}.runTaskLater(Core.getCore().getInstance(), 20);
		}
	}
}
