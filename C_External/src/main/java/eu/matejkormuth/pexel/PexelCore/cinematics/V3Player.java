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
package eu.matejkormuth.pexel.PexelCore.cinematics;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedTransferQueue;

import me.MiniDigger.Core.Core;
import net.minecraft.server.v1_7_R4.EntityPlayer;
import net.minecraft.server.v1_7_R4.PacketPlayOutPosition;
import net.minecraft.server.v1_7_R4.PacketPlayOutRelEntityMove;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import eu.matejkormuth.pexel.PexelCore.cinematics.v3meta.V3MetaEntityDamage;
import eu.matejkormuth.pexel.PexelCore.cinematics.v3meta.V3MetaEntityInventory;
import eu.matejkormuth.pexel.PexelCore.cinematics.v3meta.V3MetaEntityMove;
import eu.matejkormuth.pexel.PexelCore.cinematics.v3meta.V3MetaEntityRemove;
import eu.matejkormuth.pexel.PexelCore.cinematics.v3meta.V3MetaEntitySpawn;
import eu.matejkormuth.pexel.PexelCore.cinematics.v3meta.V3MetaEntityTeleport;
import eu.matejkormuth.pexel.PexelCore.cinematics.v3meta.V3MetaEntityVelocity;
import eu.matejkormuth.pexel.PexelCore.cinematics.v3meta.V3MetaFallingSand;
import eu.matejkormuth.pexel.PexelCore.cinematics.v3meta.V3MetaParticleEffect;
import eu.matejkormuth.pexel.PexelCore.cinematics.v3meta.V3MetaSoundEffect;
import eu.matejkormuth.pexel.PexelCore.util.NMS;
import eu.matejkormuth.pexel.PexelCore.util.PacketHelper;
import eu.matejkormuth.pexel.PexelCore.util.ParticleEffect;
import eu.matejkormuth.pexel.PexelCore.util.SoundUtility;

/**
 * Prehravac V3 klipov.
 * 
 * @author Mato Kormuth
 * 
 */
public class V3Player {
	
	private final Player	              player;
	private final V3CameraClip	          clip;
	private final Map<Long, LivingEntity>	entityMapping	= Collections.synchronizedMap(new HashMap<Long, LivingEntity>());
	private boolean	                      playing;
	private int	                          currentFrameNum	= 0;
	private V3CameraFrame	              currentFrame;
	private int	                          syncUpdateTaskId;
	private final Queue<Runnable>	      syncTasks	      = new LinkedTransferQueue<Runnable>();
	private int	                          syncTaskCounter	= 0;
	private Runnable	                  onCompleted;
	
	// private double lastX;
	// private double lastY;
	// private double lastZ;
	
	// private int eid = 0;
	
	public V3Player(final Player camera, final V3CameraClip clip) {
		player = camera;
		this.clip = clip;
	}
	
	public void play() {
		playing = true;
		player.setAllowFlight(true);
		player.setFlying(true);
		player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 0));
		
		// Schedule sync updates.
		syncUpdateTaskId = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin) Core.getCore().getInstance(), new Runnable() {
			
			@Override
			public void run() {
				V3Player.this.syncUpdate();
			}
		}, 0, 1);
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				V3Player.this.unsyncUpdate();
				// Cancel sync updates.
				Bukkit.getScheduler().cancelTask(syncUpdateTaskId);
			}
		}).start();
	}
	
	/**
	 * Volane 1000 / FPS krat za sekundu.
	 */
	@SuppressWarnings("deprecation")
	private void unsyncUpdate() {
		while (playing) {
			// Get next frame.
			V3CameraFrame frame;
			
			if ((frame = nextFrame()) != null) {
				// Nastav kameru.
				
				// Hracovi posli paketu o teleportacii. Server aktualizuje
				// hracovu polohu v synUpdate, aby sa necrashoval.
				PacketHelper.send(player, new PacketPlayOutPosition(frame.camX, frame.camY, frame.camZ, frame.yaw, frame.pitch, true));
				
				// Spracuj zoom.
				
				// Spracuj meta.
				for (final V3Meta meta : frame.getMetas()) {
					switch (meta.getMetaType()) {
					case V3MetaEntityDamage:
						final V3MetaEntityDamage v3MetaEntityDamage = ((V3MetaEntityDamage) meta);
						
						// Bezpecne
						syncTasks.add(new Runnable() {
							
							@Override
							public void run() {
								V3Player.this.getEntity(v3MetaEntityDamage.getInternalId()).damage(v3MetaEntityDamage.getDamage());
							}
						});
						break;
					case V3MetaEntityInventory:
						final V3MetaEntityInventory v3MetaEntityInventory = (V3MetaEntityInventory) meta;
						
						// Bezpecne.
						syncTasks.add(new Runnable() {
							
							@Override
							public void run() {
								final LivingEntity le = V3Player.this.getEntity(v3MetaEntityInventory.getInternalId());
								
								switch (v3MetaEntityInventory.getSlot()) {
								case 0:
									le.getEquipment().setBoots(new ItemStack(v3MetaEntityInventory.getItemType(), 1));
									break;
								case 1:
									le.getEquipment().setLeggings(new ItemStack(v3MetaEntityInventory.getItemType(), 1));
									break;
								case 2:
									le.getEquipment().setChestplate(new ItemStack(v3MetaEntityInventory.getItemType(), 1));
									break;
								case 3:
									le.getEquipment().setHelmet(new ItemStack(v3MetaEntityInventory.getItemType(), 1));
									break;
								case 4:
									le.getEquipment().setItemInHand(new ItemStack(v3MetaEntityInventory.getItemType(), 1));
									break;
								}
							}
						});
						break;
					case V3MetaEntityRemove:
						final V3MetaEntityRemove v3MetaEntityRemove = (V3MetaEntityRemove) meta;
						
						// Bezpecne.
						syncTasks.add(new Runnable() {
							
							@Override
							public void run() {
								V3Player.this.removeRemove(v3MetaEntityRemove.getInternalId());
							}
						});
						break;
					case V3MetaEntitySpawn:
						final V3MetaEntitySpawn v3MetaEntitySpawn = (V3MetaEntitySpawn) meta;
						
						// Pre bezpocnost mudi byt synchronne.
						syncTasks.add(new Runnable() {
							
							@Override
							public void run() {
								final Entity e = player.getWorld().spawnEntity(
								        new Location(player.getWorld(), v3MetaEntitySpawn.getPosX(), v3MetaEntitySpawn.getPosY(), v3MetaEntitySpawn.getPosZ(),
								                v3MetaEntitySpawn.getYaw(), v3MetaEntitySpawn.getPitch()), v3MetaEntitySpawn.getEntityType());
								if (e instanceof LivingEntity) {
									V3Player.this.addEntity(v3MetaEntitySpawn.getInternalId(), (LivingEntity) e);
									// NMS.makeIdiot((LivingEntity) e);
									// //Osproti entitu.
								}
							}
						});
						break;
					case V3MetaEntityTeleport:
						final V3MetaEntityTeleport v3MetaEntityTeleport = (V3MetaEntityTeleport) meta;
						
						// Musi byt synchronne pre bezpecnost.
						syncTasks.add(new Runnable() {
							
							@Override
							public void run() {
								V3Player.this.getEntity(v3MetaEntityTeleport.getInternalId()).teleport(
								        new Location(player.getWorld(), v3MetaEntityTeleport.getPosX(), v3MetaEntityTeleport.getPosY(), v3MetaEntityTeleport.getPosZ()));
							}
						});
						
						break;
					case V3MetaEntityVelocity:
						final V3MetaEntityVelocity v3MetaEntityVelocity = (V3MetaEntityVelocity) meta;
						
						// Musi byt synchronne pre bezpocnost.
						syncTasks.add(new Runnable() {
							
							@Override
							public void run() {
								V3Player.this.getEntity(v3MetaEntityVelocity.getInternalId()).setVelocity(
								        new Vector(v3MetaEntityVelocity.getVelX(), v3MetaEntityVelocity.getVelY(), v3MetaEntityVelocity.getVelZ()));
							}
						});
						;
						break;
					case V3MetaFallingSand:
						final V3MetaFallingSand v3MetaFallingSand = (V3MetaFallingSand) meta;
						
						// Packet rewrite
						
						/*
						 * [13:34:41] [Netty IO #5/DEBUG]: OUT: [PLAY:14]
						 * net.minecraft
						 * .server.v1_7_R3.PacketPlayOutSpawnEntity[id=3804,
						 * type=70, x=9,50, y=64,50, z=549,50] [13:34:41] [Netty
						 * IO #5/DEBUG]: OUT: [PLAY:28]
						 * net.minecraft.server.v1_7_R3
						 * .PacketPlayOutEntityMetadata[] [13:34:41] [Netty IO
						 * #5/DEBUG]: OUT: [PLAY:18]
						 * net.minecraft.server.v1_7_R3
						 * .PacketPlayOutEntityVelocity[id=3804, x=0,00, y=0,00,
						 * z=0,00] [13:34:41] [Netty IO #5/DEBUG]: OUT:
						 * [PLAY:25] net.minecraft.server.v1_7_R3.
						 * PacketPlayOutEntityHeadRotation[id=3804, rot=0]
						 */
						
						/*
						 * PacketHelper.send( this.player,
						 * ((PacketPlayOutSpawnEntity) new
						 * V3PacketPlayOutSpawnEntity(
						 * Mertexfun.random.nextInt(),
						 * v3MetaFallingSand.getPosX(),
						 * v3MetaFallingSand.getPosY(),
						 * v3MetaFallingSand.getPosZ(), 0, 0, 70,
						 * v3MetaFallingSand.getMaterial().getId() | (0 <<
						 * 0x10), v3MetaFallingSand.getVelX(),
						 * v3MetaFallingSand.getVelY(),
						 * v3MetaFallingSand.getVelZ())));
						 */
						
						// Musi byt pre bezpocnost synchronne.
						syncTasks.add(new Runnable() {
							
							@Override
							public void run() {
								final FallingBlock fb = player.getWorld().spawnFallingBlock(
								        new Location(player.getWorld(), v3MetaFallingSand.getPosX(), v3MetaFallingSand.getPosY(), v3MetaFallingSand.getPosZ()),
								        v3MetaFallingSand.getMaterial(), (byte) 0);
								fb.setVelocity(new Vector(v3MetaFallingSand.getVelX(), v3MetaFallingSand.getVelY(), v3MetaFallingSand.getVelZ()));
								fb.setDropItem(false);
								fb.setMetadata("isV3", new FixedMetadataValue((Plugin) Core.getCore().getInstance(), true));
							}
						});
						
						break;
					case V3MetaParticleEffect:
						final V3MetaParticleEffect v3MetaParticleEffect = (V3MetaParticleEffect) meta;
						
						ParticleEffect.fromId(v3MetaParticleEffect.getParticle()).display(
						        new Location(player.getWorld(), v3MetaParticleEffect.getPosX(), v3MetaParticleEffect.getPosY(), v3MetaParticleEffect.getPosZ()),
						        v3MetaParticleEffect.getOffsetX(), v3MetaParticleEffect.getOffsetY(), v3MetaParticleEffect.getOffsetZ(), v3MetaParticleEffect.getSpeed(),
						        v3MetaParticleEffect.getAmount());
						break;
					case V3MetaSoundEffect:
						final V3MetaSoundEffect v3MetaSoundEffect = (V3MetaSoundEffect) meta;
						
						SoundUtility.playCustomSound(player, v3MetaSoundEffect.getName(), v3MetaSoundEffect.getVolume(), v3MetaSoundEffect.getPitch());
						break;
					case V3MetaExplosion:
						
						break;
					case V3MetaEntityMove:
						final V3MetaEntityMove v3MetaEntityMove = (V3MetaEntityMove) meta;
						
						PacketHelper.send(
						        player,
						        new PacketPlayOutRelEntityMove(V3Player.this.getEntity(v3MetaEntityMove.getInternalId()).getEntityId(), NMS
						                .fixedPointNumByte(v3MetaEntityMove.getMovX()), NMS.fixedPointNumByte(v3MetaEntityMove.getMovY()), NMS
						                .fixedPointNumByte(v3MetaEntityMove.getMovZ()), false));
						
						/*
						 * NMS.relMoveEntity(
						 * V3Player.this.getEntity(v3MetaEntityMove
						 * .getInternalId()), v3MetaEntityMove.getMovX(),
						 * v3MetaEntityMove.getMovY(),
						 * v3MetaEntityMove.getMovZ());
						 */
						
						// Synchronizovane.
						syncTasks.add(new Runnable() {
							
							@Override
							public void run() {
								
							}
						});
						break;
					default:
						break;
					
					}
				}
			} else {
				playing = false;
				
				// Remove things, clean up.
				synchronized (entityMapping) {
					// Kill all left entites.
					for (final LivingEntity e : entityMapping.values()) {
						e.damage(400D);
					}
				}
				
				player.setFlying(false);
				if (player.getGameMode() == GameMode.SURVIVAL) {
					player.setAllowFlight(false);
				}
				player.removePotionEffect(PotionEffectType.INVISIBILITY);
				// Zavolaj to co treba.
				if (onCompleted != null) {
					onCompleted.run();
				}
			}
			
			// Spinkaj.
			try {
				Thread.sleep(1000 / clip.FPS);
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @param internalId
	 */
	private void removeRemove(final long internalId) {
		synchronized (entityMapping) {
			getEntity(internalId).damage(500D);
			entityMapping.remove(internalId);
		}
	}
	
	/**
	 * @param internalId
	 * @param e
	 */
	private void addEntity(final long internalId, final LivingEntity e) {
		synchronized (entityMapping) {
			entityMapping.put(internalId, e);
		}
	}
	
	/**
	 * Vrati entitu podla internalID.
	 * 
	 * @param internalId
	 * @return
	 */
	private LivingEntity getEntity(final long internalId) {
		return entityMapping.get(internalId);
	}
	
	/**
	 * Vrati dalsi frame.
	 * 
	 * @return
	 */
	private V3CameraFrame nextFrame() {
		currentFrameNum++;
		if (currentFrameNum < clip.frames.size() + 1) {
			return (currentFrame = clip.frames.get(currentFrameNum - 1));
		} else {
			return null;
		}
	}
	
	/**
	 * Volane 20 krat za sekundu.
	 */
	public void syncUpdate() {
		if (playing) {
			// Pomalicky zachovovaj poziciu hraca aj na serveri.
			final EntityPlayer ep = ((CraftPlayer) player).getHandle();
			ep.locX = currentFrame.camX;
			ep.locY = currentFrame.camY;
			ep.locZ = currentFrame.camZ;
			// Spracuj ulohy, co sa musia spravit synchronizovane.
			syncTaskCounter = 0;
			while (syncTaskCounter < Short.MAX_VALUE && syncTasks.peek() != null) {
				syncTasks.poll().run();
				syncTaskCounter++;
			}
		}
	}
	
	/**
	 * @param runnable
	 */
	public void setOnCompleted(final Runnable runnable) {
		onCompleted = runnable;
	}
}
