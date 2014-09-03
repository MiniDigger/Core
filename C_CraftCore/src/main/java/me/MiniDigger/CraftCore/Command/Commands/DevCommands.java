package me.MiniDigger.CraftCore.Command.Commands;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Command.Command;
import me.MiniDigger.Core.Command.CommandArgs;
import me.MiniDigger.Core.Holo.HoloList;
import me.MiniDigger.CraftCore.Packet.Packets.ChatPacket;
import me.MiniDigger.CraftCore.REST.CoreRESTHandler;
import me.MiniDigger.CraftCore.Socket.CoreSocketClient;
import me.MiniDigger.CraftCore.Socket.CoreSocketServer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
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
	
	@SuppressWarnings("deprecation")
	@Command(name = "dev.pack", description = "DEV!", usage = "", permission = "dev")
	public void pack(final CommandArgs args) {
		final Player p = Bukkit.getPlayer(args.getArgs()[0]);
		p.setResourcePack(args.getArgs()[1]);
	}
	
	@Command(name = "dev.hideTag", description = "DEV!", usage = "", permission = "dev")
	public void hideTag(final CommandArgs args) {
		@SuppressWarnings("deprecation") final Player p = Bukkit.getPlayer(args.getArgs()[0]);
		Core.getCore().getNametagHandler().hideTag(p);
	}
	
	@Command(name = "dev.showTag", description = "DEV!", usage = "", permission = "dev")
	public void showTag(final CommandArgs args) {
		@SuppressWarnings("deprecation") final Player p = Bukkit.getPlayer(args.getArgs()[0]);
		Core.getCore().getNametagHandler().showNametag(p);
	}
	
	@Command(name = "dev.rest", description = "DEV!", usage = "", permission = "dev")
	public void rest(final CommandArgs args) {
		final JSONObject obj = ((CoreRESTHandler) Core.getCore().getRESTHandler()).get(args.getArgs()[0]);
		System.out.println(obj.toJSONString());
		System.out.println(obj.get("success"));
	}
}
