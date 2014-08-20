package me.MiniDigger.CraftCore.Command.Commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

import me.MiniDigger.Core.Core;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;

public class DevCommands {
	
	@Command(name = "dev", description = "DEV!", usage = "", permission = "dev")
	public void devCommand(CommandArgs args) {
		args.getSender().sendMessage(ChatColor.RED + "DEV ONLY!!!!");
	}
	
	@Command(name = "dev.startServer", description = "DEV!", usage = "", permission = "dev")
	public void startServer(CommandArgs args) {
		WebSocketHandler.getInstance().startServer();
	}
	
	@Command(name = "dev.startClient", description = "DEV!", usage = "", permission = "dev")
	public void startClient(CommandArgs args) {
		WebSocketHandler.getInstance().startClient();
	}
	
	@Command(name = "dev.stopClient", description = "DEV!", usage = "", permission = "dev")
	public void stopClient(CommandArgs args) {
		WebSocketHandler.getInstance().getClient().close();
	}
	
	@Command(name = "dev.stopServer", description = "DEV!", usage = "", permission = "dev")
	public void stopServer(CommandArgs args) {
		try {
			WebSocketHandler.getInstance().getServer().stop(10);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Command(name = "dev.sendToServer", description = "DEV!", usage = "", permission = "dev")
	public void sendToServer(CommandArgs args) {
		WebSocketHandler.getInstance().getClient().send(StringUtils.toString(args.getArgs()));
	}
	
	@Command(name = "dev.reload", description = "DEV!", usage = "", permission = "dev")
	public void reload(CommandArgs args) {
		Bukkit.reload();
	}
	
	@Command(name = "dev.reloadPl", description = "DEV!", usage = "", permission = "dev")
	public void reloadPl(CommandArgs args) {
		Core.getInstance().onDisable();
		Core.getInstance().onEnable();
	}
	
	@Command(name = "dev.sendTestPacket", description = "DEV!", usage = "", permission = "dev")
	public void sendTestPacket(CommandArgs args) {
		ChatPacket packet = new ChatPacket();
		packet.setMsg("Dies ist die Nachricht");
		packet.setServer("DerServer");
		packet.setUser(UUID.fromString("a0bfa3a5-d42c-34b4-8dc4-9ae4152e8a93"));
		if (args.getArgs().length >= 1) {
			PacketHandler.getInstance().sendPacket(packet);
		} else {
			PacketHandler.getInstance().sendBroadcast(packet);
		}
		System.out.println("packet erstellt: " + packet.toString());
	}
	
	@Command(name = "dev.loadClass", description = "DEV!", usage = "", permission = "dev")
	public void loadClass(CommandArgs arg) {
		ClassLoader.getInstance().load("test");
	}
	
	@SuppressWarnings("deprecation")
	@Command(name = "dev.ghost", description = "DEV!", usage = "", permission = "dev")
	public void ghost(CommandArgs args) {
		for (Player p : Bukkit.getOnlinePlayers()) {
			GhostFactory.getInstance().addPlayer(p);
		}
		GhostFactory.getInstance().setGhost(Bukkit.getPlayer(args.getArgs()[0]), Boolean.parseBoolean(args.getArgs()[1]));
	}
	
	@Command(name = "dev.pet", description = "DEV!", usage = "", permission = "dev")
	public void pet(CommandArgs args) {
		Entity e = PlayerUtils.getTarget(args.getPlayer(), Integer.parseInt(args.getArgs()[0]));
		args.getPlayer().sendMessage(e.getType().name());
		PetMaker.makePet((LivingEntity) e, args.getPlayer());
	}
	
	@Command(name = "dev.skin", description = "DEV!", usage = "", permission = "dev")
	public void skin(CommandArgs args) {
		new PlayerDisplayModifier(Core.getInstance()).changeDisplay(args.getArgs()[0], args.getArgs()[1], args.getArgs()[2]);
	}
	
	@Command(name = "dev.lag", description = "DEB!", usage = "", permission = "dev")
	public void lag(CommandArgs args) {
		double tps = Lag.getTPS();
		double lag = Math.round((1.0D - tps / 20.0D) * 100.0D);
		if (args.isUser()) {
			args.getUser().sendMessage(Prefix.API.getPrefix().then("TpS: " + tps));
			args.getUser().sendMessage(Prefix.API.getPrefix().then("Lag: " + lag));
		} else {
			args.getSender().sendMessage("TpS: " + tps);
			args.getSender().sendMessage("Lag: " + lag);
		}
	}
	
	@Command(name = "dev.getUUID", description = "DEV!", usage = "", permission = "dev")
	public void getUUID(CommandArgs args) {
		if (args.getArgs().length >= 0) {
			@SuppressWarnings("deprecation") OfflinePlayer player = Bukkit.getOfflinePlayer(args.getArgs()[0]);
			args.getSender().sendMessage("UUID: " + player.getUniqueId());
		}
		if (args.isPlayer()) {
			args.getPlayer().sendMessage("UUID: " + args.getPlayer().getUniqueId());
			System.out.println("UUID " + args.getPlayer().getName() + ": " + args.getPlayer().getUniqueId());
		}
	}
	
	@Command(name = "Dev.timeTest", description = "DEV!", usage = "", permission = "dev")
	public void timeTest(CommandArgs args) {
		final Date d1 = new Date();
		Bukkit.getScheduler().runTaskLater(Core.getInstance(), new Runnable() {
			
			@Override
			public void run() {
				Date d2 = new Date();
				long time = d2.getTime() - d1.getTime();
				System.out.println("TIME PASSED: " + TimeUtil.formatTime((int) (time / 1000)));
			}
		}, Integer.parseInt(args.getArgs()[0]));
	}
	
	@Command(name = "dev.lightTest", description = "DEV!", usage = "", permission = "dev")
	public void light(CommandArgs args) {
		LightSource.createLightSource(args.getPlayer().getLocation(), Integer.parseInt(args.getArgs()[0]), true);
	}
	
	@Command(name = "dev.holoUrl", description = "DEV!", usage = "", permission = "dev")
	public void holoUrl(CommandArgs args) {
		ArrayList<HologrammList> holos = HologrammUtils.getInstance().loadImage(args.getArgs()[0]);
		
		for (HologrammList list : holos) {
			list.setLocation(args.getPlayer(), args.getPlayer().getLocation());
		}
	}
	
	@SuppressWarnings("deprecation")
	@Command(name = "dev.fakeBlock", description = "DEV!", usage = "", permission = "dev")
	public void fakeBlock(CommandArgs args) {
		// BlockDisguiser b = new BlockDisguiser(Core.getInstance());
		// b.setTranslatedBlock(args.getPlayer().getLocation(),
		// Integer.parseInt(args.getArgs()[0]),
		// Integer.parseInt(args.getArgs()[1]));
		Bukkit.getPlayer(args.getArgs()[1]).sendBlockChange(args.getPlayer().getLocation(), Material.valueOf(args.getArgs()[0]), (byte) 0);
	}
	
	@SuppressWarnings("deprecation")
	@Command(name = "dev.pack", description = "DEV!", usage = "", permission = "dev")
	public void pack(CommandArgs args) {
		Player p = Bukkit.getPlayer(args.getArgs()[0]);
		p.setResourcePack(args.getArgs()[1]);
	}
	
	@Command(name = "dev.hideTag", description = "DEV!", usage = "", permission = "dev")
	public void hideTag(CommandArgs args) {
		@SuppressWarnings("deprecation") Player p = Bukkit.getPlayer(args.getArgs()[0]);
		NameTagHandler.hideNametag(p);
	}
	
	@Command(name = "dev.showTag", description = "DEV!", usage = "", permission = "dev")
	public void showTag(CommandArgs args) {
		@SuppressWarnings("deprecation") Player p = Bukkit.getPlayer(args.getArgs()[0]);
		NameTagHandler.showNametag(p);
	}
	
	@Command(name = "dev.mail", description = "DEV!", usage = "", permission = "dev")
	public void mail(CommandArgs args) {
		System.out.println("bereite mail vor...");
		SMTP.sendEmail("imap-mail.outlook.com", "mini.digger@live.de", "Digger2011", "MiniDigger", "Test", "martin.benndorf@web.de", "TEST", "Dies ist\n die Message",
		        true);
	}
	
	@SuppressWarnings("deprecation")
	@Command(name = "dev.scrollSign", description = "DEV!", usage = "", permission = "dev")
	public void scrollSign(CommandArgs args) {
		HashSet<Byte> set = new HashSet<>();
		set.add((byte) Material.AIR.getId());
		Block b = args.getPlayer().getLineOfSight(set, 10).get(0);
		b.setType(Material.SIGN_POST);
		final Sign s = (Sign) b.getState();
		final Scroller sc1 = new Scroller(ChatColor.RED + "This is a very cool," + ChatColor.GREEN + " but very long message I want to display", 18, 14,
		        ChatColor.COLOR_CHAR);
		final Scroller sc2 = new Scroller(ChatColor.RED + "This is a very cool," + ChatColor.GREEN + " but very long message I want to display", 18, 14,
		        ChatColor.COLOR_CHAR);
		final Scroller sc3 = new Scroller(ChatColor.RED + "This is a very cool," + ChatColor.GREEN + " but very long message I want to display", 18, 14,
		        ChatColor.COLOR_CHAR);
		final Scroller sc4 = new Scroller(ChatColor.RED + "This is a very cool," + ChatColor.GREEN + " but very long message I want to display", 18, 14,
		        ChatColor.COLOR_CHAR);
		Bukkit.getScheduler().runTaskTimer(Core.getInstance(), new BukkitRunnable() {
			
			int	i	= 0;
			
			@Override
			public void run() {
				if (i > 0) s.setLine(0, sc1.next());
				if (i > 4) s.setLine(1, sc2.next());
				if (i > 8) s.setLine(2, sc3.next());
				if (i > 12) s.setLine(3, sc4.next());
				s.update();
				i++;
				if (i == 100) this.cancel();
			}
		}, -1, 2);
	}
}
