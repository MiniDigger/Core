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
package me.MiniDigger.Core.Cinematic;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedTransferQueue;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Cinematic.meta.MetaEntityDamage;
import me.MiniDigger.Core.Cinematic.meta.MetaEntityInventory;
import me.MiniDigger.Core.Cinematic.meta.MetaEntityMove;
import me.MiniDigger.Core.Cinematic.meta.MetaEntityRemove;
import me.MiniDigger.Core.Cinematic.meta.MetaEntitySpawn;
import me.MiniDigger.Core.Cinematic.meta.MetaEntityTeleport;
import me.MiniDigger.Core.Cinematic.meta.MetaEntityVelocity;
import me.MiniDigger.Core.Cinematic.meta.MetaFallingSand;
import me.MiniDigger.Core.Cinematic.meta.MetaParticleEffect;
import me.MiniDigger.Core.Cinematic.meta.MetaSoundEffect;
import me.MiniDigger.Core.Cinematic.util.NMS;
import me.MiniDigger.Core.Cinematic.util.PacketHelper;
import me.MiniDigger.Core.Cinematic.util.ParticleEffect;
import me.MiniDigger.Core.Cinematic.util.SoundUtility;
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

/**
 * Prehravac V3 klipov.
 * 
 * @author Mato Kormuth
 * 
 */
public class CinematicPlayer {
	
	private final Player	              player;
	private final CameraClip	          clip;
	private final Map<Long, LivingEntity>	entityMapping	= Collections.synchronizedMap(new HashMap<Long, LivingEntity>());
	private boolean	                      playing;
	private int	                          currentFrameNum	= 0;
	private CameraFrame	              currentFrame;
	private int	                          syncUpdateTaskId;
	private final Queue<Runnable>	      syncTasks	      = new LinkedTransferQueue<Runnable>();
	private int	                          syncTaskCounter	= 0;
	private Runnable	                  onCompleted;
	
	public CinematicPlayer(final Player camera, final CameraClip clip) {
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
				CinematicPlayer.this.syncUpdate();
			}
		}, 0, 1);
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				CinematicPlayer.this.unsyncUpdate();
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
			CameraFrame frame;
			
			if ((frame = nextFrame()) != null) {
				// Nastav kameru.
				
				// Hracovi posli paketu o teleportacii. Server aktualizuje
				// hracovu polohu v synUpdate, aby sa necrashoval.
				PacketHelper.send(player, new PacketPlayOutPosition(frame.camX, frame.camY, frame.camZ, frame.yaw, frame.pitch, true));
				
				// Spracuj zoom.
				
				// Spracuj meta.
				for (final Meta meta : frame.getMetas()) {
					switch (meta.getMetaType()) {
					case V3MetaEntityDamage:
						final MetaEntityDamage v3MetaEntityDamage = ((MetaEntityDamage) meta);
						
						// Bezpecne
						syncTasks.add(new Runnable() {
							
							@Override
							public void run() {
								CinematicPlayer.this.getEntity(v3MetaEntityDamage.getInternalId()).damage(v3MetaEntityDamage.getDamage());
							}
						});
						break;
					case V3MetaEntityInventory:
						final MetaEntityInventory v3MetaEntityInventory = (MetaEntityInventory) meta;
						
						// Bezpecne.
						syncTasks.add(new Runnable() {
							
							@Override
							public void run() {
								final LivingEntity le = CinematicPlayer.this.getEntity(v3MetaEntityInventory.getInternalId());
								
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
						final MetaEntityRemove v3MetaEntityRemove = (MetaEntityRemove) meta;
						
						// Bezpecne.
						syncTasks.add(new Runnable() {
							
							@Override
							public void run() {
								CinematicPlayer.this.removeRemove(v3MetaEntityRemove.getInternalId());
							}
						});
						break;
					case V3MetaEntitySpawn:
						final MetaEntitySpawn v3MetaEntitySpawn = (MetaEntitySpawn) meta;
						
						// Pre bezpocnost mudi byt synchronne.
						syncTasks.add(new Runnable() {
							
							@Override
							public void run() {
								final Entity e = player.getWorld().spawnEntity(
								        new Location(player.getWorld(), v3MetaEntitySpawn.getPosX(), v3MetaEntitySpawn.getPosY(), v3MetaEntitySpawn.getPosZ(),
								                v3MetaEntitySpawn.getYaw(), v3MetaEntitySpawn.getPitch()), v3MetaEntitySpawn.getEntityType());
								if (e instanceof LivingEntity) {
									CinematicPlayer.this.addEntity(v3MetaEntitySpawn.getInternalId(), (LivingEntity) e);
									// NMS.makeIdiot((LivingEntity) e);
									// //Osproti entitu.
								}
							}
						});
						break;
					case V3MetaEntityTeleport:
						final MetaEntityTeleport v3MetaEntityTeleport = (MetaEntityTeleport) meta;
						
						// Musi byt synchronne pre bezpecnost.
						syncTasks.add(new Runnable() {
							
							@Override
							public void run() {
								CinematicPlayer.this.getEntity(v3MetaEntityTeleport.getInternalId()).teleport(
								        new Location(player.getWorld(), v3MetaEntityTeleport.getPosX(), v3MetaEntityTeleport.getPosY(), v3MetaEntityTeleport.getPosZ()));
							}
						});
						
						break;
					case V3MetaEntityVelocity:
						final MetaEntityVelocity v3MetaEntityVelocity = (MetaEntityVelocity) meta;
						
						// Musi byt synchronne pre bezpocnost.
						syncTasks.add(new Runnable() {
							
							@Override
							public void run() {
								CinematicPlayer.this.getEntity(v3MetaEntityVelocity.getInternalId()).setVelocity(
								        new Vector(v3MetaEntityVelocity.getVelX(), v3MetaEntityVelocity.getVelY(), v3MetaEntityVelocity.getVelZ()));
							}
						});
						;
						break;
					case V3MetaFallingSand:
						final MetaFallingSand v3MetaFallingSand = (MetaFallingSand) meta;
						
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
						final MetaParticleEffect v3MetaParticleEffect = (MetaParticleEffect) meta;
						
						ParticleEffect.fromId(v3MetaParticleEffect.getParticle()).display(
						        new Location(player.getWorld(), v3MetaParticleEffect.getPosX(), v3MetaParticleEffect.getPosY(), v3MetaParticleEffect.getPosZ()),
						        v3MetaParticleEffect.getOffsetX(), v3MetaParticleEffect.getOffsetY(), v3MetaParticleEffect.getOffsetZ(), v3MetaParticleEffect.getSpeed(),
						        v3MetaParticleEffect.getAmount());
						break;
					case V3MetaSoundEffect:
						final MetaSoundEffect v3MetaSoundEffect = (MetaSoundEffect) meta;
						
						SoundUtility.playCustomSound(player, v3MetaSoundEffect.getName(), v3MetaSoundEffect.getVolume(), v3MetaSoundEffect.getPitch());
						break;
					case V3MetaExplosion:
						
						break;
					case V3MetaEntityMove:
						final MetaEntityMove v3MetaEntityMove = (MetaEntityMove) meta;
						
						PacketHelper.send(
						        player,
						        new PacketPlayOutRelEntityMove(CinematicPlayer.this.getEntity(v3MetaEntityMove.getInternalId()).getEntityId(), NMS
						                .fixedPointNumByte(v3MetaEntityMove.getMovX()), NMS.fixedPointNumByte(v3MetaEntityMove.getMovY()), NMS
						                .fixedPointNumByte(v3MetaEntityMove.getMovZ()), false));
						
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
	private CameraFrame nextFrame() {
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
