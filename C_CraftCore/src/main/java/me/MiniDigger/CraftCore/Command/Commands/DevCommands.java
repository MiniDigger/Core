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
package me.MiniDigger.CraftCore.Command.Commands;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Chat.ChatChars;
import me.MiniDigger.Core.Command.Command;
import me.MiniDigger.Core.Command.CommandArgs;
import me.MiniDigger.Core.Holo.HoloList;
import me.MiniDigger.CraftCore.Item.CoreItemBuilder;
import me.MiniDigger.CraftCore.Packet.Packets.ChatPacket;
import me.MiniDigger.CraftCore.REST.CoreRESTHandler;
import me.MiniDigger.CraftCore.Socket.CoreSocketClient;
import me.MiniDigger.CraftCore.Socket.CoreSocketServer;
import mkremins.fanciful.FancyMessage;
import net.minecraft.server.v1_7_R4.NBTTagCompound;
import net.minecraft.server.v1_7_R4.TileEntity;
import net.minecraft.server.v1_7_R4.TileEntityMobSpawner;
import net.minecraft.server.v1_7_R4.World;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.v1_7_R4.CraftWorld;
import org.bukkit.craftbukkit.v1_7_R4.inventory.CraftItemStack;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import org.json.simple.JSONObject;
import org.spigotmc.AsyncCatcher;

import eu.matejkormuth.pexel.PexelCore.cinematics.V3BasicRecorder;
import eu.matejkormuth.pexel.PexelCore.cinematics.V3CameraClip;
import eu.matejkormuth.pexel.PexelCore.cinematics.V3CameraFrame;
import eu.matejkormuth.pexel.PexelCore.cinematics.V3CompiledReader;
import eu.matejkormuth.pexel.PexelCore.cinematics.V3Generator;
import eu.matejkormuth.pexel.PexelCore.cinematics.V3Player;
import eu.matejkormuth.pexel.PexelCore.cinematics.v3meta.V3MetaEntityMove;
import eu.matejkormuth.pexel.PexelCore.cinematics.v3meta.V3MetaEntitySpawn;

public class DevCommands {
	
	@Command(name = "dev", description = "DEV!", usage = "", permission = "dev")
	public void devCommand(final CommandArgs args) {
		args.getSender().sendMessage(ChatColor.RED + "DEV ONLY!!!!");
	}
	
	@Command(name = "dev.startServer", description = "DEV!", usage = "", permission = "dev")
	public void startServer(final CommandArgs args) {
		Core.getCore().getSocketHandler().startServer();
	}
	
	@Command(name = "dev.startClient", description = "DEV!", usage = "", permission = "dev")
	public void startClient(final CommandArgs args) {
		Core.getCore().getSocketHandler().startClient();
	}
	
	@Command(name = "dev.stopClient", description = "DEV!", usage = "", permission = "dev")
	public void stopClient(final CommandArgs args) {
		((CoreSocketClient) Core.getCore().getSocketHandler().getClient()).close();
	}
	
	@Command(name = "dev.stopServer", description = "DEV!", usage = "", permission = "dev")
	public void stopServer(final CommandArgs args) {
		try {
			((CoreSocketServer) Core.getCore().getSocketHandler().getServer()).stop(10);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Command(name = "dev.sendToServer", description = "DEV!", usage = "", permission = "dev")
	public void sendToServer(final CommandArgs args) {
		((CoreSocketClient) Core.getCore().getSocketHandler().getClient()).send(Core.getCore().getStringUtil().toString(args.getArgs()));
	}
	
	@Command(name = "dev.reload", description = "DEV!", usage = "", permission = "dev")
	public void reload(final CommandArgs args) {
		Bukkit.reload();
	}
	
	@Command(name = "dev.reloadPl", description = "DEV!", usage = "", permission = "dev")
	public void reloadPl(final CommandArgs args) {
		Core.getCore().getInstance().onDisable();
		Core.getCore().getInstance().onEnable();
	}
	
	@Command(name = "dev.sendTestPacket", description = "DEV!", usage = "", permission = "dev")
	public void sendTestPacket(final CommandArgs args) {
		final ChatPacket packet = new ChatPacket();
		packet.setMessage("Dies ist die Nachricht");
		packet.setServer("DerServer");
		packet.setUser(UUID.fromString("a0bfa3a5-d42c-34b4-8dc4-9ae4152e8a93"));
		if (args.getArgs().length >= 1) {
			Core.getCore().getPacketHandler().sendPacket(packet);
		} else {
			Core.getCore().getPacketHandler().sendBroadcast(packet);
		}
		System.out.println("packet erstellt: " + packet.toString());
	}
	
	@Command(name = "dev.getUUID", description = "DEV!", usage = "", permission = "dev")
	public void getUUID(final CommandArgs args) {
		if (args.getArgs().length >= 0) {
			@SuppressWarnings("deprecation") final OfflinePlayer player = Bukkit.getOfflinePlayer(args.getArgs()[0]);
			args.getSender().sendMessage("UUID: " + player.getUniqueId());
		}
		if (args.isPlayer()) {
			args.getPlayer().sendMessage("UUID: " + args.getPlayer().getUniqueId());
			System.out.println("UUID " + args.getPlayer().getName() + ": " + args.getPlayer().getUniqueId());
		}
	}
	
	@SuppressWarnings("deprecation")
	@Command(name = "dev.bed", description = "DEV!", usage = "", permission = "dev", sync = true)
	public void bed(final CommandArgs args) {
		BlockFace face = BlockFace.EAST;
		final Block bed = args.getPlayer().getLocation().getBlock();
		final Block sign = bed.getRelative(BlockFace.DOWN, 2);
		face = getFacing(sign);
		final Block head = bed.getRelative(face);
		byte flags = (byte) 8;
		byte direction = (byte) (0x0);
		
		switch (face) {
		case EAST:
			flags = (byte) (flags | 0x3);
			direction = (byte) (0x3);
			break;
		
		case SOUTH:
			flags = (byte) (flags | 0x0);
			direction = (byte) (0x0);
			break;
		
		case WEST:
			flags = (byte) (flags | 0x1);
			direction = (byte) (0x1);
			break;
		
		case NORTH:
			flags = (byte) (flags | 0x2);
			direction = (byte) (0x2);
			break;
		default:
			break;
		}
		
		bed.setTypeIdAndData(Material.BED_BLOCK.getId(), direction, false);
		head.setTypeIdAndData(Material.BED_BLOCK.getId(), flags, false);
		
		bed.setTypeIdAndData(Material.BED_BLOCK.getId(), flags, false);
		head.setTypeIdAndData(Material.BED_BLOCK.getId(), direction, false);
	}
	
	@SuppressWarnings("deprecation")
	public BlockFace getFacing(final Block b) {
		return ((org.bukkit.material.Directional) b.getType().getNewData(b.getData())).getFacing();
	}
	
	@Command(name = "Dev.timeTest", description = "DEV!", usage = "", permission = "dev")
	public void timeTest(final CommandArgs args) {
		final Date d1 = new Date();
		Bukkit.getScheduler().runTaskLater((Plugin) Core.getCore().getInstance(), new Runnable() {
			
			@Override
			public void run() {
				final Date d2 = new Date();
				final long time = d2.getTime() - d1.getTime();
				System.out.println("TIME PASSED: " + Core.getCore().getTimeUtil().formatTime((int) (time / 1000)));
			}
		}, Integer.parseInt(args.getArgs()[0]));
	}
	
	@Command(name = "dev.holoUrl", description = "DEV!", usage = "", permission = "dev")
	public void holoUrl(final CommandArgs args) {
		final List<HoloList> holos = Core.getCore().getHoloHandler().loadImage(args.getArgs()[0]);
		
		for (final HoloList list : holos) {
			list.setLocation(args.getUser(), args.getPlayer().getLocation());
		}
	}
	
	@SuppressWarnings("deprecation")
	@Command(name = "dev.fakeBlock", description = "DEV!", usage = "", permission = "dev")
	public void fakeBlock(final CommandArgs args) {
		// BlockDisguiser b = new BlockDisguiser(Core.getInstance());
		// b.setTranslatedBlock(args.getPlayer().getLocation(),
		// Integer.parseInt(args.getArgs()[0]),
		// Integer.parseInt(args.getArgs()[1]));
		Bukkit.getPlayer(args.getArgs()[1]).sendBlockChange(args.getPlayer().getLocation(), Material.valueOf(args.getArgs()[0]), (byte) 0);
	}
	
	@Command(name = "dev.pack", description = "DEV!", usage = "", permission = "dev")
	public void pack(final CommandArgs args) {
		final Player p = Bukkit.getPlayer(args.getArgs()[0]);
		p.setResourcePack(args.getArgs()[1]);
	}
	
	@Command(name = "dev.hideTag", description = "DEV!", usage = "", permission = "dev")
	public void hideTag(final CommandArgs args) {
		final Player p = Bukkit.getPlayer(args.getArgs()[0]);
		Core.getCore().getNametagHandler().hideTag(p);
	}
	
	@Command(name = "dev.showTag", description = "DEV!", usage = "", permission = "dev")
	public void showTag(final CommandArgs args) {
		final Player p = Bukkit.getPlayer(args.getArgs()[0]);
		Core.getCore().getNametagHandler().showNametag(p);
	}
	
	@Command(name = "dev.rest", description = "DEV!", usage = "", permission = "dev")
	public void rest(final CommandArgs args) {
		final JSONObject obj = ((CoreRESTHandler) Core.getCore().getRESTHandler()).get(args.getArgs()[0]);
		System.out.println(obj.toJSONString());
		System.out.println(obj.get("success"));
	}
	
	@Command(name = "dev.showBlocks", description = "DEV!", usage = "", permission = "dev")
	public void showBlocks(final CommandArgs args) {
		final FancyMessage msg = new FancyMessage(".");
		for (final Material m : Material.values()) {
			msg.then(m.name() + " ");
		}
		msg.send(args.getSender());
	}
	
	@Command(name = "dev.itemSpawner", description = "DEV!", usage = "", permission = "dev")
	public void itemSpawner(final CommandArgs args) {
		@SuppressWarnings("deprecation") final Block target = args.getPlayer().getTargetBlock(null, 200);
		if ((target == null) || (target.getType() != Material.MOB_SPAWNER)) {
			System.out.println("no spawner");
			return;
		}
		final int delay = 20;
		final World world = ((CraftWorld) target.getWorld()).getHandle();
		final TileEntity tileEntity = world.getTileEntity(target.getX(), target.getY(), target.getZ());
		if ((tileEntity instanceof TileEntityMobSpawner)) {
			final TileEntityMobSpawner mobSpawner = (TileEntityMobSpawner) tileEntity;
			final NBTTagCompound spawnerTag = new NBTTagCompound();
			mobSpawner.b(spawnerTag);
			spawnerTag.remove("SpawnPotentials");
			spawnerTag.setString("EntityId", "Item");
			final NBTTagCompound itemTag = new NBTTagCompound();
			itemTag.setShort("Health", (short) 5);
			itemTag.setShort("Age", (short) 0);
			final net.minecraft.server.v1_7_R4.ItemStack itemStack = CraftItemStack.asNMSCopy(args.getPlayer().getItemInHand());
			final NBTTagCompound itemStackTag = new NBTTagCompound();
			itemStack.save(itemStackTag);
			itemStackTag.setByte("Count", (byte) 1);
			itemTag.set("Item", itemStackTag);
			spawnerTag.set("SpawnData", itemTag);
			spawnerTag.setShort("SpawnCount", (short) itemStack.count);
			spawnerTag.setShort("SpawnRange", (short) (int) args.getPlayer().getLocation().distance(target.getLocation()));
			spawnerTag.setShort("Delay", (short) 0);
			spawnerTag.setShort("MinSpawnDelay", (short) (delay));
			spawnerTag.setShort("MaxSpawnDelay", (short) (delay));
			spawnerTag.setShort("MaxNearbyEntities", (short) 300);
			spawnerTag.setShort("RequiredPlayerRange", (short) 300);
			
			mobSpawner.a(spawnerTag);
			args.getPlayer().sendMessage(ChatColor.GREEN + "Properties were successfully edited!");
		} else {
			System.out.println("fail");
		}
	}
	
	@Command(name = "dev.itemBuilder", description = "DEV!", usage = "", permission = "dev")
	public void itemBuilder(final CommandArgs args) {
		final ItemStack is = new CoreItemBuilder(Material.POTION).durability(0).name(ChatColor.RED + "" + ChatColor.BOLD + "Beast Mode")
		        .effect(PotionEffectType.NIGHT_VISION, 2 * 60 * 20).effect(PotionEffectType.SPEED, 2 * 60 * 20, 2).effect(PotionEffectType.FAST_DIGGING, 2 * 60 * 20)
		        .effect(PotionEffectType.INCREASE_DAMAGE, 2 * 60 * 20).effect(PotionEffectType.JUMP, 2 * 60 * 20, 2).effect(PotionEffectType.ABSORPTION, 2 * 60 * 20)
		        .lore(" " + ChatChars.Misc.bullet + " Become The Beast").build();
		args.getPlayer().getInventory().addItem(is);
	}
	
	V3BasicRecorder	r;
	
	@Command(name = "dev.cinematic", description = "DEV!", usage = "", permission = "dev", sync = true)
	public void cinematic(final CommandArgs args) {
		if (args.getArgs().length == 1) {
			try {
				V3CameraClip clip = V3CompiledReader.loadFile(new File(((Plugin) Core.getCore().getInstance()).getDataFolder(), "test.dat").getAbsolutePath());
				V3Player p = new V3Player(args.getUser().getPlayer(), clip);
				p.setOnCompleted(new Runnable() {
					
					@Override
					public void run() {
						System.out.println("DONE");
					}
				});
				p.play();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if (args.getArgs().length == 0) {
			if (args.getSender() == null) {
				System.out.println("wait, whut?");
			}
			r = new V3BasicRecorder((Player) args.getSender(), 20);
			r.record();
		} else if (args.getArgs().length == 2) {
			r.stop();
			V3Player p = new V3Player(args.getUser().getPlayer(), r.getClip());
			p.setOnCompleted(new Runnable() {
				
				@Override
				public void run() {
					System.out.println("DONE");
				}
			});
			p.play();
		}
	}
}
