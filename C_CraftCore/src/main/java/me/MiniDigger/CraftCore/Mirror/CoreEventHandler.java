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
 * Written by Martin Benndorf <admin@minidigger.me>, 2013-2014 and others
 */
package me.MiniDigger.CraftCore.Mirror;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.EventException;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import me.MiniDigger.Core.Core;

public class CoreEventHandler implements Listener, org.bukkit.plugin.EventExecutor {
	
	@SuppressWarnings("unchecked") private static final Class<? extends Event>[]	events	= new Class[] { org.bukkit.event.inventory.InventoryClickEvent.class,
	        org.bukkit.event.entity.EntityDamageEvent.class, org.bukkit.event.player.PlayerInteractEvent.class,
	        org.bukkit.event.player.PlayerCommandPreprocessEvent.class, org.bukkit.event.player.AsyncPlayerPreLoginEvent.class,
	        org.bukkit.event.inventory.InventoryDragEvent.class, org.bukkit.event.player.PlayerLoginEvent.class, org.bukkit.event.player.AsyncPlayerChatEvent.class,
	        org.bukkit.event.block.BlockIgniteEvent.class, org.bukkit.event.player.PlayerEggThrowEvent.class, org.bukkit.event.server.ServerListPingEvent.class,
	        org.bukkit.event.block.BlockPlaceEvent.class, org.bukkit.event.enchantment.EnchantItemEvent.class, org.bukkit.event.player.PlayerEditBookEvent.class,
	        org.bukkit.event.player.PlayerStatisticIncrementEvent.class, org.bukkit.event.player.PlayerFishEvent.class,
	        org.bukkit.event.inventory.InventoryMoveItemEvent.class, org.bukkit.event.inventory.FurnaceBurnEvent.class,
	        org.bukkit.event.enchantment.PrepareItemEnchantEvent.class, org.bukkit.event.world.StructureGrowEvent.class,
	        org.bukkit.event.entity.EntityChangeBlockEvent.class, org.bukkit.event.player.PlayerMoveEvent.class, org.bukkit.event.entity.PotionSplashEvent.class,
	        org.bukkit.event.entity.EntityRegainHealthEvent.class, org.bukkit.event.vehicle.VehicleDamageEvent.class, org.bukkit.event.block.BlockCanBuildEvent.class,
	        org.bukkit.event.player.PlayerPortalEvent.class, org.bukkit.event.server.ServerCommandEvent.class, org.bukkit.event.entity.EntityExplodeEvent.class,
	        org.bukkit.event.block.BlockDispenseEvent.class, org.bukkit.event.block.SignChangeEvent.class, org.bukkit.event.entity.EntityShootBowEvent.class,
	        org.bukkit.event.block.BlockDamageEvent.class, org.bukkit.event.block.NotePlayEvent.class, org.bukkit.event.entity.CreatureSpawnEvent.class,
	        org.bukkit.event.entity.EntityPortalEvent.class, org.bukkit.event.entity.ExplosionPrimeEvent.class, org.bukkit.event.inventory.FurnaceSmeltEvent.class,
	        org.bukkit.event.entity.HorseJumpEvent.class, org.bukkit.event.entity.EntityTeleportEvent.class, org.bukkit.event.entity.EntityTargetEvent.class,
	        org.bukkit.event.entity.ExpBottleEvent.class, org.bukkit.event.player.PlayerKickEvent.class, org.bukkit.event.player.PlayerItemConsumeEvent.class,
	        org.bukkit.event.player.PlayerTeleportEvent.class, org.bukkit.event.entity.EntityDeathEvent.class, org.bukkit.event.block.BlockFromToEvent.class,
	        org.bukkit.event.hanging.HangingBreakEvent.class, org.bukkit.event.hanging.HangingPlaceEvent.class, org.bukkit.event.player.PlayerChatTabCompleteEvent.class,
	        org.bukkit.event.entity.CreeperPowerEvent.class, org.bukkit.event.entity.FoodLevelChangeEvent.class, org.bukkit.event.entity.EntityCreatePortalEvent.class,
	        org.bukkit.event.entity.PigZapEvent.class, org.bukkit.event.inventory.InventoryOpenEvent.class, org.bukkit.event.block.BlockPhysicsEvent.class,
	        org.bukkit.event.entity.SheepDyeWoolEvent.class, org.bukkit.event.world.PortalCreateEvent.class, org.bukkit.event.entity.EntityPortalExitEvent.class,
	        org.bukkit.event.entity.PlayerLeashEntityEvent.class, org.bukkit.event.player.PlayerRespawnEvent.class, org.bukkit.event.block.BlockFadeEvent.class,
	        org.bukkit.event.block.BlockPistonExtendEvent.class, org.bukkit.event.entity.EntityCombustEvent.class, org.bukkit.event.entity.SlimeSplitEvent.class,
	        org.bukkit.event.inventory.InventoryEvent.class, org.bukkit.event.vehicle.VehicleEntityCollisionEvent.class,
	        org.bukkit.event.inventory.InventoryPickupItemEvent.class, org.bukkit.event.player.PlayerPickupItemEvent.class, org.bukkit.event.block.BlockGrowEvent.class,
	        org.bukkit.event.inventory.PrepareItemCraftEvent.class, org.bukkit.event.player.PlayerItemHeldEvent.class, org.bukkit.event.block.BlockRedstoneEvent.class,
	        org.bukkit.event.entity.ItemDespawnEvent.class, org.bukkit.event.player.PlayerVelocityEvent.class, org.bukkit.event.player.PlayerAnimationEvent.class,
	        org.bukkit.event.entity.EntityTameEvent.class, org.bukkit.event.entity.ItemSpawnEvent.class, org.bukkit.event.block.BlockSpreadEvent.class,
	        org.bukkit.event.vehicle.VehicleMoveEvent.class, org.bukkit.event.inventory.BrewEvent.class, org.bukkit.event.vehicle.VehicleDestroyEvent.class,
	        org.bukkit.event.player.PlayerShearEntityEvent.class, org.bukkit.event.entity.EntityInteractEvent.class,
	        org.bukkit.event.player.PlayerAchievementAwardedEvent.class, org.bukkit.event.player.PlayerBedEnterEvent.class,
	        org.bukkit.event.player.PlayerDropItemEvent.class, org.bukkit.event.player.PlayerGameModeChangeEvent.class,
	        org.bukkit.event.player.PlayerInteractEntityEvent.class, org.bukkit.event.player.PlayerLevelChangeEvent.class,
	        org.bukkit.event.vehicle.VehicleEnterEvent.class, org.bukkit.event.vehicle.VehicleExitEvent.class, org.bukkit.event.weather.LightningStrikeEvent.class,
	        org.bukkit.event.block.BlockExpEvent.class, org.bukkit.event.player.PlayerToggleFlightEvent.class, org.bukkit.event.player.PlayerToggleSneakEvent.class,
	        org.bukkit.event.player.PlayerToggleSprintEvent.class, org.bukkit.event.weather.ThunderChangeEvent.class, org.bukkit.event.weather.WeatherChangeEvent.class,
	        org.bukkit.event.player.PlayerExpChangeEvent.class, org.bukkit.event.player.PlayerJoinEvent.class, org.bukkit.event.player.PlayerQuitEvent.class,
	        org.bukkit.event.entity.ProjectileLaunchEvent.class, org.bukkit.event.entity.SheepRegrowWoolEvent.class, org.bukkit.event.block.BlockFormEvent.class,
	        org.bukkit.event.player.PlayerItemBreakEvent.class, org.bukkit.event.block.BlockBurnEvent.class, org.bukkit.event.world.ChunkLoadEvent.class,
	        org.bukkit.event.world.SpawnChangeEvent.class, org.bukkit.event.block.LeavesDecayEvent.class, org.bukkit.event.entity.EntityPortalEnterEvent.class,
	        org.bukkit.event.player.PlayerBedLeaveEvent.class, org.bukkit.event.player.PlayerChangedWorldEvent.class,
	        org.bukkit.event.vehicle.VehicleBlockCollisionEvent.class, org.bukkit.event.block.BlockPistonRetractEvent.class,
	        org.bukkit.event.entity.EntityUnleashEvent.class, org.bukkit.event.inventory.InventoryCloseEvent.class, org.bukkit.event.server.MapInitializeEvent.class,
	        org.bukkit.event.world.ChunkUnloadEvent.class, org.bukkit.event.world.WorldUnloadEvent.class, org.bukkit.event.player.PlayerChannelEvent.class,
	        org.bukkit.event.entity.ProjectileHitEvent.class, org.bukkit.event.player.PlayerBucketEmptyEvent.class, org.bukkit.event.player.PlayerBucketFillEvent.class,
	        org.bukkit.event.world.ChunkPopulateEvent.class, org.bukkit.event.server.ServiceRegisterEvent.class, org.bukkit.event.server.ServiceUnregisterEvent.class,
	        org.bukkit.event.server.RemoteServerCommandEvent.class, org.bukkit.event.server.PluginDisableEvent.class, org.bukkit.event.server.PluginEnableEvent.class,
	        org.bukkit.event.vehicle.VehicleCreateEvent.class, org.bukkit.event.vehicle.VehicleUpdateEvent.class, org.bukkit.event.world.WorldInitEvent.class,
	        org.bukkit.event.world.WorldLoadEvent.class, org.bukkit.event.world.WorldSaveEvent.class };
	
	private final ArrayList<String>	                                             blacklist	= new ArrayList<>();
	
	public void init() {
		blacklist.add("ChunkUnloadEvent");
		
		final PluginManager manager = Bukkit.getServer().getPluginManager();
		for (final Class<? extends Event> clazz : events) {
			manager.registerEvent(clazz, this, EventPriority.NORMAL, this,  Core.getCore().getInstance(), true);
		}
	}
	
	@Override
	public void execute(final Listener listener, final Event event) throws EventException {
		System.out.println("got " + event.getEventName());
		if (!blacklist.contains(event.getEventName())) {
			Core.getCore().getMirrorHandler().getSender().send(event);
		}
	}
	
}
