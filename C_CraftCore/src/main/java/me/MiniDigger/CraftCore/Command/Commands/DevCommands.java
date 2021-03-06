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

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.DisplaySlot;
import org.json.simple.JSONObject;

import com.comphenix.protocol.utility.MinecraftReflection;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Chat.ChatChars;
import me.MiniDigger.Core.Command.Command;
import me.MiniDigger.Core.Command.CommandArgs;
import me.MiniDigger.Core.Menu.ItemBarMenu;
import me.MiniDigger.Core.Menu.ItemBarMenu.ClickHandler;
import me.MiniDigger.Core.Menu.ItemMenu;
import me.MiniDigger.Core.Menu.ItemMenu.Row;
import me.MiniDigger.Core.Menu.ItemMenu.onClick;
import me.MiniDigger.Core.Prefix.Prefix;
import me.MiniDigger.Core.Scoreboard.Scoreboard;
import me.MiniDigger.Core.User.User;

import me.MiniDigger.CraftCore.Bar.CoreLaser;
import me.MiniDigger.CraftCore.Entity.CoreZombie;
import me.MiniDigger.CraftCore.Item.CoreItemBuilder;
import me.MiniDigger.CraftCore.Menu.CoreItemBarMenu;
import me.MiniDigger.CraftCore.Menu.CoreItemMenu;
import me.MiniDigger.CraftCore.Packet.Packets.ChatPacket;
import me.MiniDigger.CraftCore.Packet.Packets.ServerCommandPacket;
import me.MiniDigger.CraftCore.REST.CoreRESTHandler;
import me.MiniDigger.CraftCore.Scoreboard.CoreScoreboardLine;
import me.MiniDigger.CraftCore.Scoreboard.CoreScoreboardTitle;
import me.MiniDigger.CraftCore.Socket.CoreSocketClient;
import me.MiniDigger.CraftCore.Socket.CoreSocketServer;

import mkremins.fanciful.FancyMessage;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.Entity;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;
import net.minecraft.server.v1_8_R3.NBTTagString;
import net.minecraft.server.v1_8_R3.TileEntity;
import net.minecraft.server.v1_8_R3.TileEntityMobSpawner;
import net.minecraft.server.v1_8_R3.World;

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
		}
		catch (IOException | InterruptedException e) {
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
		Core.getCore().getInstance().debug("packet erstellt: " + packet.toString());
	}

	@Command(name = "dev.getUUID", description = "DEV!", usage = "", permission = "dev")
	public void getUUID(final CommandArgs args) {
		if (args.getArgs().length >= 0) {
			@SuppressWarnings("deprecation")
			final OfflinePlayer player = Bukkit.getOfflinePlayer(args.getArgs()[0]);
			args.getSender().sendMessage("UUID: " + player.getUniqueId());
		}
		if (args.isPlayer()) {
			args.getPlayer().sendMessage("UUID: " + args.getPlayer().getUniqueId());
			Core.getCore().getInstance().debug("UUID " + args.getPlayer().getName() + ": " + args.getPlayer().getUniqueId());
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
		Bukkit.getScheduler().runTaskLater(Core.getCore().getInstance(), new Runnable() {

			@Override
			public void run() {
				final Date d2 = new Date();
				final long time = d2.getTime() - d1.getTime();
				Core.getCore().getInstance().debug("TIME PASSED: " + Core.getCore().getTimeUtil().formatTime((int) (time / 1000)));
			}
		}, Integer.parseInt(args.getArgs()[0]));
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

	@Command(name = "dev.rest", description = "DEV!", usage = "", permission = "dev")
	public void rest(final CommandArgs args) {
		final JSONObject obj = ((CoreRESTHandler) Core.getCore().getRESTHandler()).get(args.getArgs()[0]);
		Core.getCore().getInstance().debug(obj.toJSONString());
		Core.getCore().getInstance().debug(obj.get("success") + "");
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
		final Block target = args.getPlayer().getTargetBlock((Set<Material>) null, 200);
		if ((target == null) || (target.getType() != Material.MOB_SPAWNER)) {
			Core.getCore().getInstance().debug("no spawner");
			return;
		}
		final int delay = 20;
		final World world = ((CraftWorld) target.getWorld()).getHandle();
		final TileEntity tileEntity = world.getTileEntity(new BlockPosition(target.getX(), target.getY(), target.getZ()));
		if ((tileEntity instanceof TileEntityMobSpawner)) {
			final TileEntityMobSpawner mobSpawner = (TileEntityMobSpawner) tileEntity;
			final NBTTagCompound spawnerTag = new NBTTagCompound();
			mobSpawner.b(spawnerTag);
			spawnerTag.remove("SpawnPotentials");
			spawnerTag.setString("EntityId", "Item");
			final NBTTagCompound itemTag = new NBTTagCompound();
			itemTag.setShort("Health", (short) 5);
			itemTag.setShort("Age", (short) 0);
			final net.minecraft.server.v1_8_R3.ItemStack itemStack = CraftItemStack.asNMSCopy(args.getPlayer().getItemInHand());
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
			Core.getCore().getInstance().debug("fail");
		}
	}

	@Command(name = "dev.itemBuilder", description = "DEV!", usage = "", permission = "dev")
	public void itemBuilder(final CommandArgs args) {
		final ItemStack is = new CoreItemBuilder(Material.MONSTER_EGG).durability(95).data(95).name(ChatColor.RED + "" + ChatColor.BOLD + "Beast Mode").effect(PotionEffectType.NIGHT_VISION, 2 * 60 * 20)
				.effect(PotionEffectType.SPEED, 2 * 60 * 20, 2).effect(PotionEffectType.FAST_DIGGING, 2 * 60 * 20).effect(PotionEffectType.INCREASE_DAMAGE, 2 * 60 * 20).effect(PotionEffectType.JUMP, 2 * 60 * 20, 2)
				.effect(PotionEffectType.ABSORPTION, 2 * 60 * 20).lore(" " + ChatChars.Misc.bullet + " Become The Beast").build();
		args.getPlayer().getInventory().addItem(is);
	}

	@Command(name = "dev.menu", description = "DEV!", usage = "", permission = "dev")
	public void menu(final CommandArgs args) {
		final ItemMenu menu = new CoreItemMenu("ItemMenu", 2, new onClick() {

			@Override
			public boolean click(final Player p, final ItemMenu menu, final Row row, final int slot, final ItemStack item) {
				if (row.getRow() == 1) {
					Core.getCore().getInstance().debug(row.getRowItem(slot).getType().name());
				}
				return true;
			}
		});
		menu.addButton(menu.getRow(1), 0, new ItemStack(Material.STONE), "Stone Button ;)", "1", "2");
		menu.addButton(menu.getRow(1), 1, new ItemStack(Material.WOOD), "Wood Button ;)", "1", "2");
		menu.addButton(menu.getRow(1), 2, new ItemStack(Material.DIAMOND), "Diamond Button ;)", "1", "2");
		menu.addButton(menu.getRow(1), 3, new ItemStack(Material.GOLD_BLOCK), "Gold Button ;)", "1", "2");
		menu.addButton(menu.getRow(1), 4, new ItemStack(Material.IRON_BLOCK), "Iron Button ;)", "1", "2");
		menu.addButton(menu.getRow(1), 5, new ItemStack(Material.OBSIDIAN), "Obby Button ;)", "1", "2");
		menu.addButton(menu.getRow(1), 6, new ItemStack(Material.ANVIL), "Anvil Button ;)", "1", "2");
		menu.addButton(menu.getRow(1), 7, new ItemStack(Material.STONE_BUTTON), "Button Button ;)", "1", "2");
		menu.addButton(menu.getRow(1), 8, new ItemStack(Material.PORTAL), "Portal Button ;)", "1", "2");
		menu.open(args.getPlayer().getPlayer());
	}

	@Command(name = "dev.mob", description = "DEV!", usage = "", permission = "dev", sync = true)
	public void mob(final CommandArgs args) {
		// CoreEntityType.ZOMBIE.spawnEntity(args.getPlayer().getLocation());
		final Entity entity = new CoreZombie(args.getPlayer().getWorld());
		entity.setLocation(args.getPlayer().getLocation().getX(), args.getPlayer().getLocation().getY(), args.getPlayer().getLocation().getZ(), args.getPlayer().getLocation().getYaw(),
				args.getPlayer().getLocation().getPitch());
		entity.getBukkitEntity().setMetadata("spawn", new FixedMetadataValue(Core.getCore().getInstance(), true));
		((CraftWorld) args.getPlayer().getLocation().getWorld()).getHandle().addEntity(entity);
	}

	@Command(name = "dev.sb", description = "DEV!", usage = "", permission = "dev", sync = true)
	public void sb(final CommandArgs args) {
		final Scoreboard sb = Core.getCore().getScoreboardHandler().getBoard(args.getUser().getUUID());
		sb.setTitle(new CoreScoreboardTitle("SIDE", DisplaySlot.SIDEBAR));
		sb.setTitle(new CoreScoreboardTitle("BELOW", DisplaySlot.BELOW_NAME));
		sb.setTitle(new CoreScoreboardTitle("PLAYER", DisplaySlot.PLAYER_LIST));
		sb.addLine(new CoreScoreboardLine(1, "SIDE", DisplaySlot.SIDEBAR));
		sb.addLine(new CoreScoreboardLine(1, "BELOW", DisplaySlot.BELOW_NAME));
		sb.addLine(new CoreScoreboardLine(1, "PLAYER", DisplaySlot.PLAYER_LIST));
		Core.getCore().getScoreboardHandler().update(args.getUser().getUUID());
	}

	@Command(name = "dev.inv", description = "DEV!", usage = "", permission = "dev", sync = true)
	public void inv(final CommandArgs args) {
		final String data = Core.getCore().getItemUtil().invToBase64(args.getPlayer().getInventory());
		Core.getCore().getInstance().debug("data!: " + data);
		args.getPlayer().openInventory(Core.getCore().getItemUtil().invFromBase64(data));
	}

	@Command(name = "dev.action", description = "DEV!", usage = "", permission = "dev", sync = true)
	public void action(final CommandArgs args) {
		Core.getCore().getActionBarHandler().sendAction(args.getPlayer(), "Dies ist ein Test!");
	}

	@Command(name = "dev.title", description = "DEV!", usage = "", permission = "dev", sync = true)
	public void title(final CommandArgs args) {
		Core.getCore().getTitleHandler().sendTitle(args.getPlayer(), 1 * 20, 5 * 20, 1 * 20, ChatColor.BOLD + "" + ChatColor.RED + "I am the " + ChatColor.BLUE + "" + ChatColor.UNDERLINE + "Title");
		Core.getCore().getTitleHandler().sendSubTitle(args.getPlayer(), 1 * 20, 5 * 20, 1 * 20, ChatColor.BOLD + "" + ChatColor.RED + "I am the " + ChatColor.BLUE + "" + ChatColor.UNDERLINE + "SubTitle");
	}

	@Command(name = "dev.book1", description = "DEV!", usage = "", permission = "dev")
	public void book1(final CommandArgs args) {
		final ItemStack is = args.getPlayer().getItemInHand();
		final BookMeta meta = (BookMeta) is.getItemMeta();
		final FancyMessage msg = new FancyMessage("Hello").color(ChatColor.RED).then(" there").color(ChatColor.BLUE);
		meta.setPage(1, msg.toJSONString());
		is.setItemMeta(meta);
	}

	@Command(name = "dev.closeClients", description = "DEV!", usage = "", permission = "dev")
	public void closeClients(final CommandArgs args) {
		final ServerCommandPacket packet = new ServerCommandPacket();
		packet.setCommand("CloseClient");
		Core.getCore().getPacketHandler().sendBroadcast(packet);
	}

	@Command(name = "dev.laser", description = "DEV!", usage = "", permission = "dev")
	public void laser(final CommandArgs args) {
		final CoreLaser laser = new CoreLaser(args.getPlayer().getLocation().add(10, 10, 10));
		laser.setTarget(args.getPlayer());
	}

	@Command(name = "dev.book2", description = "DEV!", usage = "", permission = "dev")
	public void book2(final CommandArgs args) {
		final ItemStack is = args.getPlayer().getItemInHand();
		final net.minecraft.server.v1_8_R3.ItemStack mcStack = ((net.minecraft.server.v1_8_R3.ItemStack) MinecraftReflection.getMinecraftItemStack(is));
		final NBTTagCompound tag = mcStack.getTag();
		final NBTTagList pages = tag.getList("pages", 0);
		pages.a(1, new NBTTagString(new FancyMessage("Hello").color(ChatColor.RED).then(" there").color(ChatColor.BLUE).toJSONString()));
		try {
			final Field f = mcStack.getClass().getDeclaredField("tag");
			f.setAccessible(true);
			f.set(mcStack, tag);
		}
		catch (final Exception e) {
			e.printStackTrace();
		}
	}

	ItemBarMenu	m	= new CoreItemBarMenu("m1");
	ItemBarMenu	m2	= new CoreItemBarMenu("m2");

	@Command(name = "dev.iconbar", description = "DEV!", usage = "", permission = "dev")
	public void iconbar(final CommandArgs args) {
		m2.setIcon(0, new CoreItemBuilder(Material.BUCKET).name("Test3").build());
		m2.setAction(0, new ClickHandler() {

			@Override
			public void click(final ItemBarMenu m, final ItemStack is, final User u, final org.bukkit.entity.Entity entity) {
				u.sendMessage(Prefix.API.getPrefix().then("test3"));
			}
		});
		m2.setIcon(1, new CoreItemBuilder(Material.WATCH).name("Back").build());
		m2.setAction(1, new ClickHandler() {

			@Override
			public void click(final ItemBarMenu md, final ItemStack is, final User u, final org.bukkit.entity.Entity entity) {
				u.sendMessage(Prefix.API.getPrefix().then("gooing back"));
				m2.close(u);
				m.open(u);
			}
		});

		m.setIcon(0, new CoreItemBuilder(Material.GOLDEN_APPLE).name("Test").build());
		m.setAction(0, new ClickHandler() {

			@Override
			public void click(final ItemBarMenu m, final ItemStack is, final User u, final org.bukkit.entity.Entity entity) {
				u.sendMessage(Prefix.API.getPrefix().then("test"));
			}
		});

		m.setIcon(8, new CoreItemBuilder(Material.APPLE).name("Test2").build());
		m.setAction(8, new ClickHandler() {

			@Override
			public void click(final ItemBarMenu m, final ItemStack is, final User u, final org.bukkit.entity.Entity entity) {
				u.sendMessage(Prefix.API.getPrefix().then("test2"));
				m.close(u);
				m2.open(u);
			}
		});

		m.open(args.getUser());
	}
}
