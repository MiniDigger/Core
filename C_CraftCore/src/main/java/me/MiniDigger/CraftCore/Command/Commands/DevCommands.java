package me.MiniDigger.CraftCore.Command.Commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Command.Command;
import me.MiniDigger.Core.Command.CommandArgs;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Holo.HoloList;
import me.MiniDigger.Core.Villager.VillagerTrade;
import me.MiniDigger.CraftCore.Feature.Features.SpawnerFeature;
import me.MiniDigger.CraftCore.Feature.Features.VillagerFeature;
import me.MiniDigger.CraftCore.Packet.Packets.ChatPacket;
import me.MiniDigger.CraftCore.REST.CoreRESTHandler;
import me.MiniDigger.CraftCore.Socket.CoreSocketClient;
import me.MiniDigger.CraftCore.Socket.CoreSocketServer;
import me.MiniDigger.CraftCore.Villager.CoreVillagerTrade;
import mkremins.fanciful.FancyMessage;
import net.minecraft.server.v1_7_R4.NBTTagCompound;
import net.minecraft.server.v1_7_R4.TileEntity;
import net.minecraft.server.v1_7_R4.TileEntityMobSpawner;
import net.minecraft.server.v1_7_R4.World;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_7_R4.CraftWorld;
import org.bukkit.craftbukkit.v1_7_R4.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.json.simple.JSONObject;

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
	
	@Command(name = "dev.trade", description = "DEV!", usage = "", permission = "dev")
	public void trade(final CommandArgs args) {
		Bukkit.getScheduler().runTask((Plugin) Core.getCore().getInstance(), new Runnable() {
			
			@Override
			public void run() {
				final ItemStack silver = new ItemStack(Material.IRON_INGOT);
				ItemMeta meta = silver.getItemMeta();
				meta.setDisplayName("Silber");
				silver.setItemMeta(meta);
				
				final ItemStack bronce = new ItemStack(Material.BRICK);
				meta = bronce.getItemMeta();
				meta.setDisplayName("Bronze");
				bronce.setItemMeta(meta);
				
				final ItemStack gold = new ItemStack(Material.GOLD_INGOT);
				meta = gold.getItemMeta();
				meta.setDisplayName("Gold");
				gold.setItemMeta(meta);
				
				ItemStack is1;
				Location loc = args.getPlayer().getLocation();
				List<VillagerTrade> trades = new ArrayList<VillagerTrade>();
				bronce.setAmount(16);
				trades.add(new CoreVillagerTrade(bronce, new ItemStack(Material.APPLE)));
				bronce.setAmount(32);
				is1 = new ItemStack(Material.COOKED_BEEF);
				is1.setAmount(2);
				trades.add(new CoreVillagerTrade(bronce, is1));
				silver.setAmount(3);
				trades.add(new CoreVillagerTrade(silver, new ItemStack(Material.GOLDEN_APPLE)));
				bronce.setAmount(64);
				silver.setAmount(1);
				trades.add(new CoreVillagerTrade(bronce, silver));
				silver.setAmount(16);
				gold.setAmount(1);
				trades.add(new CoreVillagerTrade(silver, gold));
				createVillager(loc, trades, Profession.BUTCHER);
				
				// TODO Team armor, vlt beim traden erst?
				
				trades = new ArrayList<VillagerTrade>();
				bronce.setAmount(1);
				is1 = new ItemStack(Material.LEATHER_HELMET);
				is1.addEnchantment(Enchantment.ARROW_INFINITE, 1);
				is1.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
				meta = is1.getItemMeta();
				meta.setDisplayName("Armor");
				is1.setItemMeta(meta);
				trades.add(new CoreVillagerTrade(bronce, is1));
				bronce.setAmount(1);
				is1 = new ItemStack(Material.LEATHER_LEGGINGS);
				is1.addEnchantment(Enchantment.ARROW_INFINITE, 1);
				is1.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
				meta = is1.getItemMeta();
				meta.setDisplayName("Armor");
				is1.setItemMeta(meta);
				trades.add(new CoreVillagerTrade(bronce, is1));
				bronce.setAmount(1);
				is1 = new ItemStack(Material.LEATHER_BOOTS);
				is1.addEnchantment(Enchantment.ARROW_INFINITE, 1);
				is1.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
				meta = is1.getItemMeta();
				meta.setDisplayName("Armor");
				is1.setItemMeta(meta);
				trades.add(new CoreVillagerTrade(bronce, is1));
				silver.setAmount(2);
				is1 = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
				is1.addEnchantment(Enchantment.ARROW_INFINITE, 1);
				is1.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
				meta = is1.getItemMeta();
				meta.setDisplayName("Chestplate");
				is1.setItemMeta(meta);
				trades.add(new CoreVillagerTrade(silver, is1));
				silver.setAmount(8);
				is1 = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
				is1.addEnchantment(Enchantment.ARROW_INFINITE, 1);
				is1.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
				meta = is1.getItemMeta();
				meta.setDisplayName("Chestplate");
				is1.setItemMeta(meta);
				trades.add(new CoreVillagerTrade(silver, is1));
				silver.setAmount(16);
				is1 = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
				is1.addEnchantment(Enchantment.ARROW_INFINITE, 1);
				is1.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
				meta = is1.getItemMeta();
				meta.setDisplayName("Chestplate");
				is1.setItemMeta(meta);
				trades.add(new CoreVillagerTrade(silver, is1));
				silver.setAmount(48);
				is1 = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
				is1.addEnchantment(Enchantment.ARROW_INFINITE, 1);
				is1.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
				meta = is1.getItemMeta();
				meta.setDisplayName("Chestplate");
				is1.setItemMeta(meta);
				trades.add(new CoreVillagerTrade(silver, is1));
				createVillager(loc, trades, Profession.FARMER);
				
				
				trades = new ArrayList<VillagerTrade>();
				gold.setAmount(2);
				is1 = new ItemStack(Material.BOW);
				is1.addEnchantment(Enchantment.ARROW_INFINITE, 1);
				meta = is1.getItemMeta();
				meta.setDisplayName("Bow");
				is1.setItemMeta(meta);
				trades.add(new CoreVillagerTrade(gold, is1));
				gold.setAmount(8);
				is1 = new ItemStack(Material.BOW);
				is1.addEnchantment(Enchantment.ARROW_INFINITE, 1);
				is1.addEnchantment(Enchantment.ARROW_DAMAGE, 1);
				meta = is1.getItemMeta();
				meta.setDisplayName("Bow");
				is1.setItemMeta(meta);
				trades.add(new CoreVillagerTrade(gold, is1));
				gold.setAmount(12);
				is1 = new ItemStack(Material.BOW);
				is1.addEnchantment(Enchantment.ARROW_INFINITE, 1);
				is1.addEnchantment(Enchantment.ARROW_DAMAGE, 2);
				meta = is1.getItemMeta();
				meta.setDisplayName("Bow");
				is1.setItemMeta(meta);
				trades.add(new CoreVillagerTrade(gold, is1));
				gold.setAmount(24);
				is1 = new ItemStack(Material.BOW);
				is1.addEnchantment(Enchantment.ARROW_INFINITE, 1);
				is1.addEnchantment(Enchantment.ARROW_DAMAGE, 2);
				is1.addEnchantment(Enchantment.ARROW_KNOCKBACK, 1);
				meta = is1.getItemMeta();
				meta.setDisplayName("Bow");
				is1.setItemMeta(meta);
				trades.add(new CoreVillagerTrade(gold, is1));
				gold.setAmount(1);
				trades.add(new CoreVillagerTrade(gold, new ItemStack(Material.ARROW)));
				silver.setAmount(1);
				trades.add(new CoreVillagerTrade(silver, new ItemStack(Material.LADDER, 3)));
				silver.setAmount(1);
				trades.add(new CoreVillagerTrade(silver, new ItemStack(Material.WEB, 3)));
				createVillager(loc, trades, Profession.PRIEST);
				
				
				trades = new ArrayList<VillagerTrade>();
				silver.setAmount(2);
				is1 = new ItemStack(Material.GOLD_SWORD);
				is1.addEnchantment(Enchantment.ARROW_INFINITE, 1);
				trades.add(new CoreVillagerTrade(silver, is1));
				silver.setAmount(8);
				is1 = new ItemStack(Material.GOLD_SWORD);
				is1.addEnchantment(Enchantment.ARROW_INFINITE, 1);
				is1.addEnchantment(Enchantment.DAMAGE_ALL, 1);
				trades.add(new CoreVillagerTrade(silver, is1));
				silver.setAmount(16);
				is1 = new ItemStack(Material.GOLD_SWORD);
				is1.addEnchantment(Enchantment.ARROW_INFINITE, 1);
				is1.addEnchantment(Enchantment.DAMAGE_ALL, 2);
				trades.add(new CoreVillagerTrade(silver, is1));
				silver.setAmount(48);
				is1 = new ItemStack(Material.GOLD_SWORD);
				is1.addEnchantment(Enchantment.ARROW_INFINITE, 1);
				is1.addEnchantment(Enchantment.DAMAGE_ALL, 2);
				is1.addEnchantment(Enchantment.KNOCKBACK, 1);
				trades.add(new CoreVillagerTrade(silver, is1));
				silver.setAmount(6);
				trades.add(new CoreVillagerTrade(silver, new ItemStack(Material.TNT)));
				gold.setAmount(1);
				trades.add(new CoreVillagerTrade(gold, new ItemStack(Material.FLINT_AND_STEEL)));
				createVillager(loc, trades, Profession.BLACKSMITH);
				
				
				trades = new ArrayList<VillagerTrade>();
				bronce.setAmount(8);
				trades.add(new CoreVillagerTrade(bronce, new ItemStack(Material.NETHERRACK, 32)));
				bronce.setAmount(16);
				trades.add(new CoreVillagerTrade(bronce, new ItemStack(Material.BRICK, 16)));
				bronce.setAmount(32);
				trades.add(new CoreVillagerTrade(bronce, new ItemStack(Material.EMERALD_BLOCK, 8)));
				bronce.setAmount(8);
				trades.add(new CoreVillagerTrade(bronce, new ItemStack(Material.GLOWSTONE, 8)));
				bronce.setAmount(6);
				is1 = new ItemStack(Material.WOOD_PICKAXE);
				is1.addEnchantment(Enchantment.ARROW_INFINITE, 1);
				trades.add(new CoreVillagerTrade(bronce, is1));
				silver.setAmount(3);
				is1 = new ItemStack(Material.IRON_PICKAXE);
				is1.addEnchantment(Enchantment.ARROW_INFINITE, 1);
				is1.addEnchantment(Enchantment.DIG_SPEED, 1);
				trades.add(new CoreVillagerTrade(silver, is1));
				gold.setAmount(1);
				is1 = new ItemStack(Material.GOLD_PICKAXE);
				is1.addEnchantment(Enchantment.ARROW_INFINITE, 1);
				is1.addEnchantment(Enchantment.DIG_SPEED, 3);
				trades.add(new CoreVillagerTrade(gold, is1));
				silver.setAmount(1);
				trades.add(new CoreVillagerTrade(silver, new ItemStack(Material.CHEST)));
				createVillager(loc, trades, Profession.BUTCHER);
			}
		});
	}
	
	private void createVillager(Location loc, List<VillagerTrade> trades, Profession prof) {
		Villager v = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);
		v.setProfession(prof);
		Core.getCore().getVillagerHandler().setTrades(v, trades);
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
}
