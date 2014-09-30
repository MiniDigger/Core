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
package me.MiniDigger.AddOn.GrablingHook;

import java.util.ArrayList;
import java.util.UUID;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Item.ItemType;
import me.MiniDigger.CraftCore.Item.CoreItem;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerFishEvent.State;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

public class GrablingHook extends CoreItem {
	
	private ArrayList<UUID>	noFallDmg	= new ArrayList<>();
	
	public GrablingHook() {
		super(ItemType.GRABLING_HOOK);
	}
	
	@Override
	public ItemStack getItem() {
		ItemStack is = new ItemStack(Material.FISHING_ROD);
		is.setAmount(1);
		ItemMeta meta = is.getItemMeta();
		meta.setDisplayName(ChatColor.RED + "Grappling" + ChatColor.BLUE + "Hook");
		is.setItemMeta(meta);
		return is;
	}
	
	@Override
	public void onUse(PlayerInteractEvent e) {
	}
	
	@EventHandler
	public void onPlayerFish(PlayerFishEvent e) {
		if (!hasItem(Core.getCore().getUserHandler().get(e.getPlayer().getUniqueId()))) {
			return;
		}
		if (e.getState() == State.IN_GROUND || e.getHook().isOnGround() || e.getHook().doesBounce()) {
			// System.out.println("hook");
			Location loc = e.getHook().getLocation();
			
			for (Entity ent : e.getHook().getNearbyEntities(1.5D, 1.0D, 1.5D)) {
				if ((ent instanceof Item)) {
					hook(e.getPlayer(), ent, e.getPlayer().getLocation());
					return;
				}
			}
			hook(e.getPlayer(), e.getPlayer(), loc);
			
		} else if (e.getState() == State.CAUGHT_ENTITY) {
			hook(e.getPlayer(), e.getCaught(), e.getPlayer().getLocation());
		}
	}
	
	@EventHandler
	public void onDmg(EntityDamageEvent e) {
		if (e.getCause() == DamageCause.FALL) {
			if (noFallDmg.contains(e.getEntity().getUniqueId())) {
				e.setCancelled(true);
				noFallDmg.remove(e.getEntity().getUniqueId());
			}
		}
	}
	
	public void hook(Player p, Entity e, Location loc) {
		loc.getWorld().playSound(loc, Sound.MAGMACUBE_JUMP, 10.0F, 1.0F);
		if (p.equals(e)) {
			if (p.getLocation().distance(loc) < 3.0D) {
				pullPlayerSlightly(p, loc);
			} else {
				pullEntityToLocation(p, loc);
			}
		} else {
			pullEntityToLocation(e, loc);
		}
	}
	
	private void pullPlayerSlightly(final Player p, Location loc) {
		if (loc.getY() > p.getLocation().getY()) {
			p.setVelocity(new Vector(0.0D, 0.25D, 0.0D));
			return;
		}
		Location playerLoc = p.getLocation();
		
		Vector vector = loc.toVector().subtract(playerLoc.toVector());
		p.setVelocity(vector);
		
		noFallDmg.add(p.getUniqueId());
		Bukkit.getScheduler().runTaskLater((Plugin) Core.getCore().getInstance(), new Runnable() {
			
			@Override
			public void run() {
				noFallDmg.remove(p.getUniqueId());
			}
		}, 5 * 20);
	}
	
	private void pullEntityToLocation(final Entity e, Location loc) {
		Location entityLoc = e.getLocation();
		
		entityLoc.setY(entityLoc.getY() + 0.5D);
		e.teleport(entityLoc);
		
		double g = -0.08D;
		double d = loc.distance(entityLoc);
		double t = d;
		double v_x = (1.0D + 0.07000000000000001D * t) * (loc.getX() - entityLoc.getX()) / t;
		double v_y = (1.0D + 0.03D * t) * (loc.getY() - entityLoc.getY()) / t - 0.5D * g * t;
		double v_z = (1.0D + 0.07000000000000001D * t) * (loc.getZ() - entityLoc.getZ()) / t;
		
		Vector v = e.getVelocity();
		v.setX(v_x);
		v.setY(v_y);
		v.setZ(v_z);
		e.setVelocity(v);
		e.setFallDistance(0);
		
		noFallDmg.add(e.getUniqueId());
		Bukkit.getScheduler().runTaskLater((Plugin) Core.getCore().getInstance(), new Runnable() {
			
			@Override
			public void run() {
				noFallDmg.remove(e.getUniqueId());
			}
		}, 5 * 20);
	}
}
