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
package me.MiniDigger.Core.AddOn.GravityGun;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Item.ItemType;

import me.MiniDigger.CraftCore.Item.CoreItem;
import me.MiniDigger.CraftCore.Item.CoreItemBuilder;

/**
 *
 * @author LabyStudio, http://www.spigotmc.org/resources/gravitygun.2851/
 *
 */
@SuppressWarnings("deprecation")
public class GravityGun extends CoreItem {

	public static Map<Player, Material>		block;
	public static Map<Player, Byte>			blockByte;
	public static Map<Player, FallingBlock>	hold;
	public static Map<Player, Entity>		entity;
	public static List<Player>				entityList;
	public static Map<FallingBlock, Player>	flyingBlock;
	public static List<FallingBlock>		flyingBlockList;

	public static boolean doEntiyPushing = false;

	public String	permission_give;
	public String	permission_use;

	static {
		GravityGun.block = new HashMap<Player, Material>();
		GravityGun.blockByte = new HashMap<Player, Byte>();
		GravityGun.hold = new HashMap<Player, FallingBlock>();
		GravityGun.entity = new HashMap<Player, Entity>();
		GravityGun.entityList = new ArrayList<Player>();
		GravityGun.flyingBlock = new HashMap<FallingBlock, Player>();
		GravityGun.flyingBlockList = new ArrayList<FallingBlock>();

		Bukkit.getScheduler().runTaskTimer(Core.getCore().getInstance(), new Runnable() {

			@Override
			public void run() {
				move();
				hit();
			}
		}, 0L, 0L);
	}

	public GravityGun() {
		super(ItemType.GRAVITYGUN);
		permission_give = "GravityGun.give";
		permission_use = "GravityGun.use";
	}

	@Override
	public ItemStack getItem() {
		return new CoreItemBuilder(Material.STICK).name(ChatColor.BLUE + "Gravity" + ChatColor.RED + "Gun").amount(1).enchantment(Enchantment.ARROW_DAMAGE, 9001).lore("Power over 9000!").build();
	}

	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
	public void EntityChangeBlockEvent(final EntityChangeBlockEvent event) {
		if (event.getEntityType() == EntityType.FALLING_BLOCK) {
			List<Player> onlinePlayers;
			event.setCancelled(false);
			for (int length = (onlinePlayers = Core.getCore().getUserHandler().getOnlinePlayers()).size(), i = 0; i < length; ++i) {
				final Player all = onlinePlayers.get(i);
				if ((FallingBlock) event.getEntity() == GravityGun.hold.get(all)) {
					event.setCancelled(true);
				}
			}
			if (!event.isCancelled()) {
				event.getEntity().getLocation().getBlock().setType(((FallingBlock) event.getEntity()).getMaterial());
				event.getEntity().getLocation().getBlock().setData(((FallingBlock) event.getEntity()).getBlockData());
			}
			if (GravityGun.flyingBlock.containsKey(event.getEntity())) {
				GravityGun.flyingBlock.remove(event.getEntity());
				GravityGun.flyingBlockList.remove(event.getEntity());
			}
		}
	}

	@EventHandler(ignoreCancelled = true)
	public void SlotChange(final PlayerItemHeldEvent e) {
		try {
			GravityGun.block.remove(e.getPlayer());
			GravityGun.hold.remove(e.getPlayer());
			GravityGun.entity.remove(e.getPlayer());
		}
		catch (final Exception ex) {}
	}

	public static void hit() {
		try {
			for (final FallingBlock all : GravityGun.flyingBlockList) {
				for (final Entity e : all.getNearbyEntities(1.0, 1.0, 1.0)) {
					final Damageable d = (Damageable) e;
					d.damage(4.0);
					final Player p = GravityGun.flyingBlock.get(all);
					d.setVelocity(d.getLocation().toVector().subtract(p.getLocation().toVector()).normalize().multiply(2));
					p.playSound(p.getLocation(), Sound.ORB_PICKUP, 20.0f, 0.0f);
					GravityGun.flyingBlock.remove(all);
					GravityGun.flyingBlockList.remove(all);
				}
			}
		}
		catch (final Exception ex) {}
	}

	@EventHandler(ignoreCancelled = true)
	public void onDeath(final PlayerDeathEvent e) {
		try {
			GravityGun.block.remove(e.getEntity().getPlayer());
			GravityGun.hold.remove(e.getEntity().getPlayer());
			GravityGun.entity.remove(e.getEntity().getPlayer());
		}
		catch (final Exception ex) {}
	}

	public static void move() {
		List<Player> onlinePlayers;
		for (int length = (onlinePlayers = Core.getCore().getUserHandler().getOnlinePlayers()).size(), i = 0; i < length; ++i) {
			final Player all = onlinePlayers.get(i);
			if (GravityGun.block.containsKey(all)) {
				if (GravityGun.hold.containsKey(all)) {
					final FallingBlock fs = GravityGun.hold.get(all);
					final Location loc = all.getLocation();
					final Vector direction = loc.getDirection();
					direction.multiply(4);
					loc.add(direction);
					loc.add(0.0, 2.0, 0.0);
					if (fs == null || fs.isDead()) {
						GravityGun.hold.remove(all);
					}
					if (fs.getLocation().distance(loc) >= 0.0 && fs.getLocation().distance(loc) <= 1.0) {
						fs.setVelocity(new Vector(0.0, 0.1, 0.0));
					} else if (fs.getLocation().distance(loc) >= 1.0 && fs.getLocation().distance(loc) <= 2.0) {
						fs.setVelocity(loc.toVector().subtract(fs.getLocation().toVector()).normalize().multiply(1));
					} else if (fs.getLocation().distance(loc) >= 2.0 && fs.getLocation().distance(loc) <= 3.0) {
						fs.setVelocity(loc.toVector().subtract(fs.getLocation().toVector()).normalize().multiply(2));
					} else if (fs.getLocation().distance(loc) >= 4.0 && fs.getLocation().distance(loc) <= 5.0) {
						fs.setVelocity(loc.toVector().subtract(fs.getLocation().toVector()).normalize().multiply(4));
					} else if (fs.getLocation().distance(loc) >= 6.0) {
						fs.setVelocity(loc.toVector().subtract(fs.getLocation().toVector()).normalize().multiply(5));
						fs.teleport(loc);
					} else {
						fs.setVelocity(loc.toVector().subtract(fs.getLocation().toVector()).normalize().multiply(0.4));
					}
					if (fs.getLocation().getBlock().getType() != Material.AIR) {
						Bukkit.getScheduler().runTaskLater(Core.getCore().getInstance(), new Runnable() {

							@Override
							public void run() {
								all.sendBlockChange(fs.getLocation(), fs.getLocation().getBlock().getType(), fs.getLocation().getBlock().getData());
							}
						}, 2L);
					}
				} else {
					final Location loc2 = all.getLocation();
					final Vector direction2 = loc2.getDirection();
					direction2.multiply(4);
					loc2.add(direction2);
					loc2.add(0.0, 2.0, 0.0);
					final FallingBlock fs2 = all.getWorld().spawnFallingBlock(loc2, GravityGun.block.get(all), GravityGun.blockByte.get(all));
					fs2.setDropItem(false);
					GravityGun.hold.put(all, fs2);
				}
			} else if (GravityGun.entity.containsKey(all)) {
				if (!GravityGun.entity.get(all).isDead()) {
					final Location loc2 = all.getLocation();
					final Vector direction2 = loc2.getDirection();
					direction2.multiply(4);
					loc2.add(direction2);
					loc2.add(0.0, 2.0, 0.0);
					if (GravityGun.entity.get(all) instanceof Player || GravityGun.entity.get(all) instanceof Minecart) {
						final Entity fs3 = GravityGun.entity.get(all);
						if (fs3.getLocation().distance(loc2) >= 0.0 && fs3.getLocation().distance(loc2) <= 1.0) {
							fs3.setVelocity(new Vector(0.0, 0.1, 0.0));
						} else if (fs3.getLocation().distance(loc2) >= 1.0 && fs3.getLocation().distance(loc2) <= 2.0) {
							fs3.setVelocity(loc2.toVector().subtract(fs3.getLocation().toVector()).normalize().multiply(0.4));
						} else if (fs3.getLocation().distance(loc2) >= 2.0 && fs3.getLocation().distance(loc2) <= 3.0) {
							fs3.setVelocity(loc2.toVector().subtract(fs3.getLocation().toVector()).normalize().multiply(0.7));
						} else if (fs3.getLocation().distance(loc2) >= 4.0 && fs3.getLocation().distance(loc2) <= 5.0) {
							fs3.setVelocity(loc2.toVector().subtract(fs3.getLocation().toVector()).normalize().multiply(1));
						} else if (fs3.getLocation().distance(loc2) >= 6.0) {
							fs3.setVelocity(loc2.toVector().subtract(fs3.getLocation().toVector()).normalize().multiply(5));
							fs3.teleport(loc2);
						} else {
							fs3.setVelocity(loc2.toVector().subtract(fs3.getLocation().toVector()).normalize().multiply(0.4));
						}
					} else {
						GravityGun.entity.get(all).teleport(loc2);
						GravityGun.entity.get(all).setVelocity(new Vector(0, 0, 0));
					}
				} else {
					GravityGun.entity.remove(all);
				}
			}
		}
	}

	@EventHandler(ignoreCancelled = true)
	public void EntityDamageByEntityEvent(final EntityDamageByEntityEvent e) {
		if (doEntiyPushing) {
			if (e.getDamager() instanceof Player) {
				final Player h = (Player) e.getDamager();
				if (h.hasPermission(permission_use) && h.getItemInHand().getType() == getItem().getType() && h.getItemInHand().getItemMeta().getDisplayName().contains(getItem().getItemMeta().getDisplayName())) {
					e.setCancelled(true);
					e.getEntity().setVelocity(h.getLocation().getDirection().multiply(3));
					for (int i = 0; i <= 10; ++i) {
						h.getWorld().playSound(e.getEntity().getLocation(), Sound.ENDERMAN_TELEPORT, 2.0f, 0.0f);
					}
				}
			}
		}
	}

	public void setBlock(final int x, final int y, final int z, final Location loc, final Player p) {
		loc.add(x, y, z);
		loc.getBlock().setType(GravityGun.hold.get(p).getMaterial());
		loc.getBlock().setData(GravityGun.hold.get(p).getBlockData());
	}

	@EventHandler(ignoreCancelled = true)
	@Override
	public void onUse(final PlayerInteractEvent e) {
		try {
			if (e.getPlayer().hasPermission(permission_use) && e.getPlayer().getItemInHand().getType() == getItem().getType()
					&& e.getPlayer().getItemInHand().getItemMeta().getDisplayName().contains(getItem().getItemMeta().getDisplayName())) {
				e.setCancelled(true);
				if (!GravityGun.entity.containsKey(e.getPlayer())) {
					if (GravityGun.block.containsKey(e.getPlayer())) {
						if (e.getAction() == Action.LEFT_CLICK_AIR) {
							GravityGun.hold.get(e.getPlayer()).setVelocity(e.getPlayer().getLocation().getDirection().multiply(3));
							for (int i = 0; i <= 10; ++i) {
								e.getPlayer().getWorld().playSound(GravityGun.hold.get(e.getPlayer()).getLocation(), Sound.ENDERMAN_TELEPORT, 2.0f, 0.0f);
							}
							GravityGun.flyingBlock.put(GravityGun.hold.get(e.getPlayer()), e.getPlayer());
							GravityGun.flyingBlockList.add(GravityGun.hold.get(e.getPlayer()));
							GravityGun.block.remove(e.getPlayer());
							GravityGun.hold.remove(e.getPlayer());
						}
						if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
							if (e.getBlockFace() == BlockFace.UP) {
								setBlock(0, 1, 0, e.getClickedBlock().getLocation(), e.getPlayer());
							} else if (e.getBlockFace() == BlockFace.DOWN) {
								setBlock(0, -1, 0, e.getClickedBlock().getLocation(), e.getPlayer());
							} else if (e.getBlockFace() == BlockFace.NORTH) {
								setBlock(0, 0, -1, e.getClickedBlock().getLocation(), e.getPlayer());
							} else if (e.getBlockFace() == BlockFace.EAST) {
								setBlock(1, 0, 0, e.getClickedBlock().getLocation(), e.getPlayer());
							} else if (e.getBlockFace() == BlockFace.SOUTH) {
								setBlock(0, 0, 1, e.getClickedBlock().getLocation(), e.getPlayer());
							} else if (e.getBlockFace() == BlockFace.WEST) {
								setBlock(-1, 0, 0, e.getClickedBlock().getLocation(), e.getPlayer());
							}
							for (int i = 0; i <= 10; ++i) {
								e.getPlayer().getWorld().playSound(GravityGun.hold.get(e.getPlayer()).getLocation(), Sound.ENDERMAN_TELEPORT, 2.0f, 0.0f);
							}
							GravityGun.hold.get(e.getPlayer()).remove();
							GravityGun.block.remove(e.getPlayer());
							GravityGun.hold.remove(e.getPlayer());
						}
						if (e.getAction() == Action.RIGHT_CLICK_AIR && GravityGun.hold.get(e.getPlayer()).getLocation().getBlock().getType() == Material.AIR) {
							if (GravityGun.hold.get(e.getPlayer()).isOnGround()) {
								GravityGun.hold.get(e.getPlayer()).getLocation().getBlock().setType(GravityGun.hold.get(e.getPlayer()).getMaterial());
								GravityGun.hold.get(e.getPlayer()).getLocation().getBlock().setData(GravityGun.hold.get(e.getPlayer()).getBlockData());
								GravityGun.hold.get(e.getPlayer()).remove();
							}
							GravityGun.block.remove(e.getPlayer());
							GravityGun.hold.remove(e.getPlayer());
							e.getPlayer().getWorld().playSound(e.getPlayer().getLocation(), Sound.LAVA_POP, 7.0f, 4.0f);
						}
					} else if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
						if (e.getClickedBlock().getType() != Material.ENCHANTMENT_TABLE && e.getClickedBlock().getType() != Material.BEDROCK) {
							GravityGun.block.put(e.getPlayer(), e.getClickedBlock().getType());
							GravityGun.blockByte.put(e.getPlayer(), e.getClickedBlock().getData());
							e.getClickedBlock().setType(Material.AIR);
							e.getClickedBlock().setData((byte) 0);
							e.getPlayer().getWorld().playSound(e.getPlayer().getLocation(), Sound.ENDERMAN_TELEPORT, 7.0f, 3.0f);
						}
					} else if (e.getAction() == Action.LEFT_CLICK_AIR) {
						for (int i = 0; i <= 10; ++i) {
							e.getPlayer().getWorld().playSound(e.getPlayer().getLocation(), Sound.ENDERMAN_TELEPORT, 2.0f, 0.0f);
						}
					}
				} else {
					if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
						GravityGun.entity.get(e.getPlayer()).setVelocity(e.getPlayer().getLocation().getDirection().multiply(3));
						for (int i = 0; i <= 10; ++i) {
							e.getPlayer().getWorld().playSound(GravityGun.entity.get(e.getPlayer()).getLocation(), Sound.ENDERMAN_TELEPORT, 2.0f, 0.0f);
						}
						GravityGun.entity.remove(e.getPlayer());
					}
					if (e.getAction() == Action.RIGHT_CLICK_AIR && !GravityGun.entityList.contains(e.getPlayer())) {
						GravityGun.entity.remove(e.getPlayer());
						e.getPlayer().getWorld().playSound(e.getPlayer().getLocation(), Sound.LAVA_POP, 7.0f, 4.0f);
					}
				}
			}
		}
		catch (final Exception ex) {}
	}

	@EventHandler(ignoreCancelled = true)
	public void PlayerInteractEntityEvent(final PlayerInteractEntityEvent e) {
		if (e.getPlayer().hasPermission(permission_use) && e.getPlayer().getItemInHand().getType() == getItem().getType()
				&& e.getPlayer().getItemInHand().getItemMeta().getDisplayName().contains(getItem().getItemMeta().getDisplayName())) {
			if (GravityGun.entity.containsKey(e.getPlayer())) {
				GravityGun.entity.remove(e.getPlayer());
				e.getPlayer().getWorld().playSound(e.getPlayer().getLocation(), Sound.LAVA_POP, 7.0f, 4.0f);
			} else {
				try {
					if (doEntiyPushing) {
						if (!(GravityGun.entity.get(e.getRightClicked()) instanceof Player)) {
							GravityGun.entity.put(e.getPlayer(), e.getRightClicked());
							GravityGun.entityList.add(e.getPlayer());
							e.getPlayer().getWorld().playSound(e.getPlayer().getLocation(), Sound.ENDERMAN_TELEPORT, 7.0f, 3.0f);
							Bukkit.getScheduler().runTaskLater(Core.getCore().getInstance(), new Runnable() {

								@Override
								public void run() {
									GravityGun.entityList.remove(e.getPlayer());
								}
							}, 20L);
						}
					}
				}
				catch (final Exception ex) {}
			}
		}
	}

}
