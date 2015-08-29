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
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Bar.Laser;
import me.MiniDigger.Core.User.User;
import me.MiniDigger.CraftCore.Bar.CoreLaser;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.event.DespawnReason;
import net.citizensnpcs.api.npc.NPC;

public class EHNPCs {
	private final EHScanner	s;
	private final EHData	d;

	private List<NPC>		npcs		= new ArrayList<>();
	private List<Location>	usedLocs	= new ArrayList<>();

	public EHNPCs(final EHScanner s, final EHData d) {
		this.s = s;
		this.d = d;
	}

	public void respawn(final String newName, final double amount) {
		for (final NPC npc : npcs) {
			npc.despawn(DespawnReason.REMOVAL);
		}
		npcs = new ArrayList<>();
		spawn(newName, amount);
	}

	public void spawn(final String newName, final double amount) {
		usedLocs = new ArrayList<>();
		for (final String name : d.getDonations().keySet()) {
			final NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, name);

			Location loc = null;
			for (final Location l : s.getTower1()) {
				if (!usedLocs.contains(l)) {
					loc = l;
				}
			}
			if (loc == null) {
				for (final Location l : s.getTower2()) {
					if (!usedLocs.contains(l)) {
						loc = l;
					}
				}
				if (loc == null) {
					for (final Location l : s.getTower3()) {
						if (!usedLocs.contains(l)) {
							loc = l;
						}
					}
					if (loc == null) {
						for (final Location l : s.getWall1()) {
							if (!usedLocs.contains(l)) {
								loc = l;
							}
						}
						if (loc == null) {
							for (final Location l : s.getWall2()) {
								if (!usedLocs.contains(l)) {
									loc = l;
								}
							}
							if (loc == null) {
								for (final Location l : s.getWall3()) {
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
				Core.getCore().getInstance().debug("Well, we are fucked");
				return;
			}

			usedLocs.add(loc);
			loc = loc.clone().add(0.5, -0.5, 0.5);
			loc.setDirection(loc.getDirection().multiply(-1));

			npcs.add(npc);
			npc.spawn(loc);

			if (newName != null && name.equalsIgnoreCase(newName)) {
				effect(loc, name, amount, npc);
			}
		}
	}

	public void effect(final Location loc, final String name, final double amount, final NPC npc) {
		for (final User u : Core.getCore().getUserHandler().getOnlineUsers()) {
			Core.getCore().getTitleHandler().sendTitle(u.getPlayer(), 1 * 20, 150, 1 * 20, ChatColor.GOLD + "Donation");
			Core.getCore().getTitleHandler().sendSubTitle(u.getPlayer(), 1 * 20, 150, 1 * 20, ChatColor.GOLD + name + " hat " + amount + " Euro gespendet!");

			final FireworkEffect e = FireworkEffect.builder().flicker(true).with(Type.BALL).trail(false).withColor(Color.ORANGE, Color.YELLOW, Color.RED).build();
			final Firework fw = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
			final FireworkMeta m = fw.getFireworkMeta();
			m.setPower(0);
			m.addEffect(e);
			fw.setFireworkMeta(m);

			for (final Location l : s.getFireworksI()) {
				final FireworkEffect e1 = FireworkEffect.builder().flicker(true).with(Type.BURST).trail(false).withColor(Color.YELLOW).withFade(Color.AQUA).build();
				final Firework fw1 = (Firework) l.getWorld().spawnEntity(l, EntityType.FIREWORK);
				final FireworkMeta m1 = fw1.getFireworkMeta();
				m1.setPower(0);
				m1.addEffect(e1);
				fw1.setFireworkMeta(m1);
			}

			new BukkitRunnable() {

				@Override
				public void run() {
					for (final Location l : s.getFireworksO()) {
						final FireworkEffect e1 = FireworkEffect.builder().flicker(true).with(Type.STAR).trail(false).withColor(Color.GREEN, Color.BLACK).build();
						final Firework fw1 = (Firework) l.getWorld().spawnEntity(l, EntityType.FIREWORK);
						final FireworkMeta m1 = fw1.getFireworkMeta();
						m1.setPower(0);
						m1.addEffect(e1);
						fw1.setFireworkMeta(m1);
					}
				}
			}.runTaskLater(Core.getCore().getInstance(), 20);

			final List<Laser> lasers = new ArrayList<>();
			for (final Location l : s.getFireworksI()) {
				final Laser laser = new CoreLaser(l.clone().add(0.5, 0.5, 0.5));
				laser.setTarget((LivingEntity) npc.getEntity());
				lasers.add(laser);
			}

			new BukkitRunnable() {

				@Override
				public void run() {
					for (final Laser l : lasers) {
						try {
							l.despawn(null);
						}
						catch (final Exception ex) {

						}
					}
				}
			}.runTaskLater(Core.getCore().getInstance(), 10 * 20);
		}
	}
}
