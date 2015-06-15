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
package me.MiniDigger.CraftCore.Command.Commands;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Command.Command;
import me.MiniDigger.Core.Command.CommandArgs;
import me.MiniDigger.Core.Prefix.Prefix;
import me.MiniDigger.Core.User.User;

public class ToggleCommands implements Listener {
	
	@Command(name = "toggle", description = "Stellt viele Sachen um", permission = "toggle")
	public void toggle(final CommandArgs args) {
		Prefix.API.getPrefix().then("Stellt viele Sachen um").send(args.getSender());
		Prefix.API.getPrefix().then("Sub-Commands: build,hacks,fly").send(args.getSender());
	}
	
	@Command(name = "toggle.build", description = "Toggelt den Build Modus", permission = "toggle.build", consol = false, min = 0, max = 1)
	public void build(final CommandArgs args) {
		User user;
		if (args.getArgs().length == 1) {
			user = Core.getCore().getUserHandler().get(Bukkit.getPlayer(args.getArgs()[0]).getUniqueId());
			if (user == null) {
				args.getUser().sendMessage(Prefix.API.getPrefix().then("Unbekannter Spieler " + args.getArgs()[0]).color(ChatColor.RED));
				return;
			}
		} else {
			user = args.getUser();
		}
		
		if (Core.getCore().getBuildHandler().isBuilder(user)) {
			user.sendMessage(Prefix.API.getPrefix().then("BuildMode deaktiviert!").color(ChatColor.GREEN));
			Core.getCore().getBuildHandler().setBuilder(user, false);
		} else {
			user.sendMessage(Prefix.API.getPrefix().then("BuildMode aktiviert!").color(ChatColor.GREEN));
			Core.getCore().getBuildHandler().setBuilder(user, true);
		}
	}
	
	@Command(name = "toggle.updates", description = "Toggelt den Protocollib updates", permission = "toggle.updates", consol = false, min = 0, max = 0)
	public void updates(final CommandArgs args) {
		final boolean b = Core.getCore().getProtocolHandler().toggleUpdates(args.getUser().getUUID());
		
		Prefix.API.getPrefix().then("Du bekommst nun " + (b == false ? "keine" : "") + " Protocol updates");
	}
	
	@Command(name = "toggle.fly", description = "Toggelt den Fly Modus", permission = "fly", consol = false, min = 0, max = 0)
	public void fly(final CommandArgs args) {
		args.getUser().getPlayer().setAllowFlight((!args.getPlayer().getAllowFlight()));
		Prefix.API.getPrefix().then("Du kannst nun " + (args.getPlayer().getAllowFlight() == false ? "nicht mehr" : "") + " fliegen");
	}
	
	private final List<UUID>	hacks	= new ArrayList<>();
	
	@Command(name = "toggle.hacks", description = "Toggelt die Hacks", permission = "toggle.hacks", consol = false, min = 0, max = 1)
	public void hacks(final CommandArgs args) {
		if (hacks.contains(args.getPlayer().getUniqueId())) {
			hacks.remove(args.getPlayer().getUniqueId());
			Prefix.API.getPrefix().then("Du hast deine Hacks deaktiviert!").color(ChatColor.GOLD).send(args.getSender());
		} else {
			hacks.add(args.getPlayer().getUniqueId());
			System.out.println("Apn: " + args.getPlayer().getName() + "uuid: " + args.getPlayer().getUniqueId());
			Prefix.API.getPrefix().then("Du hast deine Hacks aktiviert!").color(ChatColor.GOLD).send(args.getSender());
		}
	}
	
	@EventHandler
	public void onBowShoot(final EntityShootBowEvent event) {
		if (event.getEntityType() != EntityType.PLAYER) {
			return;
		}
		
		final Player player = (Player) event.getEntity();
		System.out.println("pn: " + player.getName() + " uuid: " + player.getUniqueId());
		if (!hacks.contains(player.getUniqueId())) {
			System.out.println("ppfff");
			hacks.add(player.getUniqueId());
		}
		if (hacks.contains(player.getUniqueId())) {
			double minAngle = 6.283185307179586D;
			Entity minEntity = null;
			
			for (final Entity entity : player.getNearbyEntities(64.0D, 64.0D, 64.0D)) {
				if ((player.hasLineOfSight(entity)) && ((entity instanceof LivingEntity)) && (!entity.isDead())) {
					final Vector toTarget = entity.getLocation().toVector().clone().subtract(player.getLocation().toVector());
					final double angle = event.getProjectile().getVelocity().angle(toTarget);
					if (angle < minAngle) {
						minAngle = angle;
						minEntity = entity;
					}
				}
			}
			
			if (minEntity != null) {
				System.out.println("start task to " + minEntity.getType().name());
				new HomingTask((Arrow) event.getProjectile(), (LivingEntity) minEntity);
			} else {
				System.out.println("nothing found");
			}
		} else {
			System.out.println("disabled");
		}
	}
	
	private class HomingTask extends BukkitRunnable {
		
		// private static final double MaxRotationAngle = 0.12D;
		//
		// private static final double TargetSpeed = 1.4D;
		Arrow		 arrow;
		LivingEntity	target;
		
		public HomingTask(final Arrow arrow, final LivingEntity target) {
			this.arrow = arrow;
			this.target = target;
			runTaskTimer(Core.getCore().getInstance(), 1L, 1L);
		}
		
		@Override
		public void run() {
			final double speed = arrow.getVelocity().length();
			if ((arrow.isOnGround()) || (arrow.isDead()) || (target.isDead())) {
				cancel();
				return;
			}
			
			final Vector toTarget = target.getLocation().clone().add(new Vector(0.0D, 0.5D, 0.0D)).subtract(arrow.getLocation()).toVector();
			
			final Vector dirVelocity = arrow.getVelocity().clone().normalize();
			final Vector dirToTarget = toTarget.clone().normalize();
			final double angle = dirVelocity.angle(dirToTarget);
			
			double newSpeed = 0.9D * speed + 0.14D;
			
			if (((target instanceof Player)) && (arrow.getLocation().distance(target.getLocation()) < 8.0D)) {
				final Player player = (Player) target;
				if (player.isBlocking()) {
					newSpeed = speed * 0.6D;
					cancel();
				}
			}
			Vector newVelocity;
			if (angle < 0.12D) {
				newVelocity = dirVelocity.clone().multiply(newSpeed);
			} else {
				final Vector newDir = dirVelocity.clone().multiply((angle - 0.12D) / angle).add(dirToTarget.clone().multiply(0.12D / angle));
				newDir.normalize();
				newVelocity = newDir.clone().multiply(newSpeed);
			}
			
			arrow.setVelocity(newVelocity.add(new Vector(0.0D, 0.03D, 0.0D)));
			arrow.getWorld().playEffect(arrow.getLocation(), Effect.SMOKE, 0);
		}
	}
}
