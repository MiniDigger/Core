package me.MiniDigger.CraftCore.Command.Commands;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Command.Command;
import me.MiniDigger.Core.Command.CommandArgs;
import me.MiniDigger.Core.Game.GameType;
import me.MiniDigger.Core.Map.MapData;
import me.MiniDigger.Core.Prefix.Prefix;
import me.MiniDigger.CraftCore.CoreMain;
import me.MiniDigger.CraftCore.Map.CoreMapData;
import mkremins.fanciful.FancyMessage;
import net.minecraft.server.v1_7_R4.ExceptionWorldConflict;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_7_R4.CraftServer;
import org.bukkit.craftbukkit.v1_7_R4.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class WorldCommands {
	
	@Command(name = "weather", description = "Ändert das Wetter", usage = "[wetter] [zeit]", permission = "weather", consol = false)
	public void weather(final CommandArgs args) {
		final World w = args.getPlayer().getWorld();
		if (args.getArgs().length == 0) {
			if (w.hasStorm()) {
				if (w.isThundering()) {
					args.getUser().sendMessage(
					        Prefix.API.getPrefix().then("Es gewittert noch ").color(ChatColor.GOLD).then(w.getThunderDuration() + "").color(ChatColor.YELLOW)
					                .then(" Ticks und regnet noch ").color(ChatColor.GOLD).then("" + w.getWeatherDuration()).color(ChatColor.YELLOW).then(" Ticks.")
					                .color(ChatColor.GOLD));
				} else {
					args.getUser().sendMessage(
					        Prefix.API.getPrefix().then("Es regnet noch ").color(ChatColor.GOLD).then("" + w.getWeatherDuration()).color(ChatColor.YELLOW)
					                .then(" Ticks.").color(ChatColor.GOLD));
				}
			} else {
				args.getUser().sendMessage(
				        Prefix.API.getPrefix().then("Die Sonne scheint noch ").color(ChatColor.GOLD).then("" + w.getWeatherDuration()).color(ChatColor.YELLOW)
				                .then(" Ticks.").color(ChatColor.GOLD));
			}
		} else if (args.getArgs().length >= 1) {
			int duration = 5 * 20;
			if (args.getArgs().length >= 2) {
				try {
					duration = Integer.parseInt(args.getArgs()[1]);
				} catch (final Exception ex) {
					args.getUser().sendMessage(Prefix.API.getPrefix().then("Unbekannte Dauer! ").color(ChatColor.RED).then(args.getArgs()[1]).color(ChatColor.DARK_RED));
					return;
				}
			}
			boolean storm = false;
			boolean thunder = false;
			final String[] stormWords = new String[] { "storm", "rain", "regen", "sturm" };
			final String[] thunderWords = new String[] { "thunder", "gewitter" };
			final String[] sunWorlds = new String[] { "sun", "sonne" };
			if (Core.getCore().getStringUtil().contains(stormWords, args.getArgs()[0].toLowerCase())) {
				storm = true;
			} else if (Core.getCore().getStringUtil().contains(thunderWords, args.getArgs()[0].toLowerCase())) {
				storm = true;
				thunder = true;
			} else if (Core.getCore().getStringUtil().contains(sunWorlds, args.getArgs()[0].toLowerCase())) {
				storm = false;
				thunder = false;
			} else {
				args.getUser().sendMessage(Prefix.API.getPrefix().then("Unbekanntes Wetter! ").color(ChatColor.RED).then(args.getArgs()[0]).color(ChatColor.DARK_RED));
				return;
			}
			
			w.setWeatherDuration(duration);
			w.setStorm(storm);
			w.setThundering(thunder);
			w.setThunderDuration(duration);
			
			args.getUser().sendMessage(Prefix.API.getPrefix().then("Wetter aktualisiert!").color(ChatColor.GREEN));
		}
	}
	
	@Command(name = "world.info", description = "Zeigt Infos übder die aktuelle Welt", usage = "", permission = "world.info", consol = false)
	public void info(final CommandArgs args) {
		args.getUser().sendMessage(Prefix.API.getPrefix().then("Du bist auf World " + args.getPlayer().getWorld().getName()).color(ChatColor.GREEN));
	}
	
	@Command(name = "tp", description = "Teleportiert", usage = "<x> <y> <z> / <spieler>", consol = false, min = 1, max = 3)
	public void tp2(final CommandArgs args) {
		if (args.getArgs().length == 1) {
			final Player p = Bukkit.getPlayer(args.getArgs()[0]);
			if (p == null) {
				args.getUser().sendMessage(Prefix.API.getPrefix().then("Unbekannter Spieler!").color(ChatColor.RED));
				return;
			}
			args.getPlayer().teleport(p);
			args.getUser().sendMessage(Prefix.API.getPrefix().then("Wuusch..."));
		} else if (args.getArgs().length == 3) {
			int x, y, z;
			try {
				x = Integer.parseInt(args.getArgs()[0]);
				y = Integer.parseInt(args.getArgs()[1]);
				z = Integer.parseInt(args.getArgs()[2]);
			} catch (final Exception ex) {
				args.getUser().sendMessage(Prefix.API.getPrefix().then("Falsche Eingabe!").color(ChatColor.RED));
				return;
			}
			args.getPlayer().teleport(new Location(args.getPlayer().getWorld(), x, y, z));
			args.getUser().sendMessage(Prefix.API.getPrefix().then("Wuusch..."));
		} else {
			args.getSender().sendMessage(Prefix.API.getConsolPrefix() + " Falsche Benutzung!");
		}
	}
	
	@Command(name = "world.load", description = "Läd eine Welt", usage = "<gametype/name>", permission = "world.load", consol = false, min = 1)
	public void load(final CommandArgs args) {
		final String map = args.getArgs()[0];
		
		if (!(args.getArgs().length > 1 && args.getArgs()[1].contains("-f"))) {
			final File mapFolder = new File(((CoreMain) Core.getCore().getInstance()).getConfig().getString("mapFolder"));
			final File mapConfig = new File(mapFolder, "maps.yml");
			
			final FileConfiguration con = YamlConfiguration.loadConfiguration(mapConfig);
			final List<String> maps = con.getStringList("maps");
			
			if (maps.contains(map)) {
				Core.getCore().getWorldHandler().copyWorld(map);
				Core.getCore().getWorldHandler().loadWorld(map);
				args.getUser().sendMessage(Prefix.API.getPrefix().then("Map geladen!").color(ChatColor.GREEN));
			} else {
				args.getUser().sendMessage(Prefix.API.getPrefix().then("Unbekannte Map!").color(ChatColor.RED));
			}
		} else {
			try {
				Core.getCore().getWorldHandler().loadWorld(map);
				args.getUser().sendMessage(Prefix.API.getPrefix().then("Map geladen!").color(ChatColor.GREEN));
			} catch (final Exception ex) {
				args.getUser().sendMessage(Prefix.API.getPrefix().then("Map nicht geladen!").color(ChatColor.RED));
			}
		}
		
		if ((args.getArgs().length > 1 && args.getArgs()[1].contains("-m"))) {
			args.getUser().sendMessage(Prefix.API.getPrefix().then("Setze Wool!").color(ChatColor.GREEN));
			final int i = Core.getCore().getMapHandler().getMap(map).setLocs();
			args.getUser().sendMessage(Prefix.API.getPrefix().then(i + " Wools gesetzt").color(ChatColor.GREEN));
		} else {
			args.getUser().sendMessage(Prefix.API.getPrefix().then("Lösche Wolle!").color(ChatColor.GREEN));
			Core.getCore().getMapHandler().getMap(map).clearLocs();
		}
	}
	
	@Command(name = "world.unload", description = "Löscht eine Welt", usage = "<name>", permission = "world.unload", consol = false, min = 1)
	public void unload(final CommandArgs args) {
		final String map = args.getArgs()[0];
		
		final File mapFolder = new File(((JavaPlugin) Core.getCore().getInstance()).getConfig().getString("mapFolder"));
		final File mapConfig = new File(mapFolder, "maps.yml");
		
		final FileConfiguration con = YamlConfiguration.loadConfiguration(mapConfig);
		final List<String> maps = con.getStringList("maps");
		
		if (maps.contains(map) && Bukkit.getWorld(map) != null || (args.getArgs().length > 1 && args.getArgs()[1].contains("-f"))) {
			try {
				Core.getCore().getWorldHandler().unloadWorld(map, Bukkit.getWorlds().get(0).getSpawnLocation());
				args.getUser().sendMessage(Prefix.API.getPrefix().then("Welt unloaded! Lösche sie..."));
			} catch (final Exception ex) {}
			System.gc();
			final File file = new File(Core.getCore().getStringUtil().replaceLast(Bukkit.getWorldContainer().getAbsolutePath(), ".", ""), map);
			Core.getCore().getFileUtil().deleteDirectory(file);
			args.getUser().sendMessage(Prefix.API.getPrefix().then("Welt gelöscht!"));
		} else {
			args.getUser().sendMessage(Prefix.API.getPrefix().then("Unbekannt Map!").color(ChatColor.RED));
		}
	}
	
	@Command(name = "world.tp", description = "Teleportiert zu einer Welt", usage = "<name>", permission = "world.tp", min = 1, consol = false)
	public void tp(final CommandArgs args) {
		final World w = Bukkit.getWorld(args.getArgs()[0]);
		if (w == null) {
			args.getUser().sendMessage(Prefix.API.getPrefix().then("Unbekannte Welt!").color(ChatColor.RED));
			return;
		}
		
		args.getPlayer().teleport(w.getSpawnLocation());
		args.getUser().sendMessage(Prefix.API.getPrefix().then("Wuusch..."));
	}
	
	@Command(name = "world.list", description = "Zeit alle Welten", usage = "", permission = "world.list")
	public void list(final CommandArgs args) {
		final File mapFolder = new File(((JavaPlugin) Core.getCore().getInstance()).getConfig().getString("mapFolder"));
		final File mapConfig = new File(mapFolder, "maps.yml");
		
		final FileConfiguration con = YamlConfiguration.loadConfiguration(mapConfig);
		final List<String> maps = con.getStringList("maps");
		
		final FancyMessage header = Prefix.API.getPrefix().then("Registrierte Welten: ").color(ChatColor.GOLD);
		
		if (args.isUser()) {
			args.getUser().sendMessage(header);
		} else {
			args.getSender().sendMessage(header.toOldMessageFormat());
		}
		
		for (final String map : maps) {
			FancyMessage msg;
			if (Bukkit.getWorld(map) == null) {
				msg = Prefix.API.getPrefix().then("# ").then(map).color(ChatColor.RED).command("/world load " + map)
				        .formattedTooltip(new FancyMessage("Klicke hier um die Map zu laden!").color(ChatColor.GOLD));
			} else {
				msg = Prefix.API.getPrefix().then("# ").then(map).color(ChatColor.GREEN).command("/world tp " + map)
				        .formattedTooltip(new FancyMessage("Klicke ").then("hier").style(ChatColor.UNDERLINE).then(" um zu der Welt zu kommen!"));
				
			}
			if (args.isUser()) {
				args.getUser().sendMessage(msg);
			} else {
				args.getSender().sendMessage(msg.toOldMessageFormat());
			}
		}
	}
	
	HashMap<UUID, HashMap<String, String>>	data	= new HashMap<>();
	
	@Command(name = "world.create", description = "Erstellt ne neue Map", usage = "", permission = "world.create", consol = false)
	public void create(final CommandArgs args) {
		if (data.get(args.getUser().getUUID()) == null) {
			data.put(args.getUser().getUUID(), new HashMap<String, String>());
		}
		final HashMap<String, String> session = data.get(args.getUser().getUUID());
		if (args.getArgs().length != 0) {
			if (args.getArgs()[0].startsWith("w:")) {
				final String in = args.getArgs()[0].replace("w:", "");
				if (Bukkit.getWorld(in) == null) {
					args.getUser().sendMessage(Prefix.API.getPrefix().then("Unbekannt Welt!").color(ChatColor.RED));
					world(args);
					return;
				}
				session.put("world", in);
			} else if (args.getArgs()[0].startsWith("s:")) {
				session.put("start", "supersecretstuffinhere!");
				world(args);
				return;
			} else if (args.getArgs()[0].startsWith("m:")) {
				final Location l = args.getPlayer().getLocation();
				if (l == null || !l.getWorld().getName().equals(session.get("world"))) {
					args.getUser().sendMessage(Prefix.API.getPrefix().then("Fehler!").color(ChatColor.RED));
					middle(args);
					return;
				}
				session.put("middle", Core.getCore().getLocationUtil().LocationToString(l));
			} else if (args.getArgs()[0].startsWith("r:")) {
				final String in = args.getArgs()[0].replace("r:", "");
				try {
					Integer.parseInt(in);
				} catch (final Exception ex) {
					args.getUser().sendMessage(Prefix.API.getPrefix().then("Gibt ne Zahl ein!").color(ChatColor.RED));
					range(args);
					return;
				}
				session.put("range", in);
			} else if (args.getArgs()[0].startsWith("n:")) {
				final String in = args.getArgs()[0].replace("n:", "");
				session.put("name", in);
			} else if (args.getArgs()[0].startsWith("a:")) {
				final String in = args.getArgs()[0].replace("a:", "");
				session.put("author", in);
			} else if (args.getArgs()[0].startsWith("t:")) {
				final String in = args.getArgs()[0].replace("t:", "");
				if (session.containsKey("gameTypes")) {
					final String old = session.get("gameTypes");
					if (old.contains(",")) {
						final List<String> types = Core.getCore().getStringUtil().stringToList(old);
						types.add(in);
						session.remove("gameTypes");
						session.put("gameTypes", Core.getCore().getStringUtil().listToString(types));
					} else {
						final List<String> types = new ArrayList<>();
						types.add(old);
						types.add(in);
						session.remove("gameTypes");
						session.put("gameTypes", Core.getCore().getStringUtil().listToString(types));
					}
				} else {
					session.put("gameTypes", in);
				}
			} else if (args.getArgs()[0].equals("finish")) {
				session.put("finished", "yeah!");
			} else {
				args.getUser().sendMessage(Prefix.API.getPrefix().then("Unknown args!").color(ChatColor.RED));
			}
			data.remove(data.get(args.getUser().getUUID()));
			data.put(args.getUser().getUUID(), session);
		}
		
		if (!session.containsKey("start")) {
			start(args);
			return;
		}
		if (!session.containsKey("world")) {
			world(args);
			return;
		}
		if (!session.containsKey("middle")) {
			middle(args);
			return;
		}
		if (!session.containsKey("range")) {
			range(args);
			return;
		}
		if (!session.containsKey("author")) {
			author(args);
			return;
		}
		if (!session.containsKey("name")) {
			name(args);
			return;
		}
		if (!session.containsKey("gameTypes")) {
			gameTypes(args);
			return;
		}
		if (!session.containsKey("finished")) {
			return;// WAIT
		}
		
		final MapData data = new CoreMapData(session.get("world"));
		data.scanMap(Core.getCore().getLocationUtil().StringToLocation(session.get("middle")), Integer.parseInt(session.get("range")), new Runnable() {
			
			@Override
			public void run() {
				data.createConfig();
				
				final File mapFolder = new File(((JavaPlugin) Core.getCore().getInstance()).getConfig().getString("mapFolder"));
				final File mapConfig = new File(mapFolder, "maps.yml");
				
				final FileConfiguration con = YamlConfiguration.loadConfiguration(mapConfig);
				final List<String> maps = con.getStringList("maps");
				maps.add(session.get("world"));
				con.set("maps", maps);
				
				final List<String> types = Core.getCore().getStringUtil().stringToList(session.get("gameTypes"));
				con.set(session.get("world") + ".gametypes", types);
				con.set(session.get("world") + ".author", session.get("author"));
				con.set(session.get("world") + ".name", session.get("name"));
				
				Bukkit.getWorld(session.get("world")).setAutoSave(false);
				args.getUser().sendMessage(Prefix.API.getPrefix().then("Speicher...").color(ChatColor.GREEN));
				final World world = Bukkit.getWorld(session.get("world"));
				final CraftWorld w = (CraftWorld) world;
				
				((CraftServer) Bukkit.getServer()).checkSaveState();
				w.getHandle().savingDisabled = false;
				try {
					w.getHandle().save(true, null);
				} catch (final ExceptionWorldConflict e2) {
					e2.printStackTrace();
				}
				w.getHandle().savingDisabled = true;
				
				try {
					con.save(mapConfig);
					args.getUser().sendMessage(Prefix.API.getPrefix().then("FERTIG!").color(ChatColor.GREEN));
				} catch (final IOException e) {
					args.getUser().sendMessage(Prefix.API.getPrefix().then("Error!").color(ChatColor.RED));
					e.printStackTrace();
				}
				
				args.getUser().sendMessage(Prefix.API.getPrefix().then("Zippe..."));
				
				try {
					Core.getCore()
					        .getZipUtil()
					        .zipFolder(
					                Core.getCore().getStringUtil().replaceLast(Bukkit.getWorldContainer().getAbsolutePath(), ".", "") + File.separatorChar
					                        + session.get("world"),
					                ((JavaPlugin) Core.getCore().getInstance()).getConfig().getString("mapFolder") + File.separatorChar + session.get("world") + ".zip");
				} catch (final Exception e) {
					e.printStackTrace();
					System.out.println("ZIP ALLREADY THERE?! Retrying...");
					try {
						Core.getCore()
						        .getZipUtil()
						        .zipFolder(
						                Core.getCore().getStringUtil().replaceLast(Bukkit.getWorldContainer().getAbsolutePath(), ".", "") + File.separatorChar
						                        + session.get("world"),
						                ((JavaPlugin) Core.getCore().getInstance()).getConfig().getString("mapFolder") + File.separatorChar + session.get("world")
						                        + ".zip" + Core.getCore().getRandomUtil().nextInt(1000));
					} catch (final Exception e1) {
						e1.printStackTrace();
						System.out.println("Giving up....");
					}
				}
				
				args.getUser().sendMessage(Prefix.API.getPrefix().then("Finished!"));
				WorldCommands.this.data.remove(args.getUser().getUUID());
			}
		});
	}
	
	private void gameTypes(final CommandArgs args) {
		args.getUser().sendMessage(Prefix.API.getPrefix().then("Welche Modi soll man Spielen? Du kannst mehrmals klicken!").color(ChatColor.YELLOW));
		final FancyMessage msg = Prefix.API.getPrefix();
		for (final GameType type : GameType.values()) {
			msg.then(type.getName() + " ").command("/world create t:" + type.name());
		}
		args.getUser().sendMessage(msg);
		args.getUser().sendMessage(Prefix.API.getPrefix().then("Fertig? Klick mich hart!").command("/world create finish"));
		
	}
	
	private void name(final CommandArgs args) {
		args.getUser().sendMessage(Prefix.API.getPrefix().then("Wie heißt die Map? KEINE LEERZEICHEN!").color(ChatColor.YELLOW));
		args.getUser().sendMessage(Prefix.API.getPrefix().then("Klick hier und gibt den Namen ein").color(ChatColor.YELLOW).suggest("/world create n:"));
	}
	
	private void author(final CommandArgs args) {
		args.getUser().sendMessage(Prefix.API.getPrefix().then("Wer hat die Map gebaut?").color(ChatColor.YELLOW));
		args.getUser().sendMessage(Prefix.API.getPrefix().then("Klick hier und gibt den Author ein").color(ChatColor.YELLOW).suggest("/world create a:"));
	}
	
	private void range(final CommandArgs args) {
		args.getUser().sendMessage(Prefix.API.getPrefix().then("Wie groß ist der Radius der Map? Lieber zu viel als zu wenig!").color(ChatColor.YELLOW));
		args.getUser().sendMessage(Prefix.API.getPrefix().then("Klick hier und gib den Radius ein").color(ChatColor.YELLOW).suggest("/world create r:"));
	}
	
	private void middle(final CommandArgs args) {
		args.getUser().sendMessage(Prefix.API.getPrefix().then("Wo ist der Mittelpunkt? Muss nicht exakt sein, aber annähernd!").color(ChatColor.YELLOW));
		args.getUser().sendMessage(Prefix.API.getPrefix().then("Klick hier wenn du im Mittelpunkt bist").color(ChatColor.YELLOW).command("/world create m:"));
	}
	
	private void world(final CommandArgs args) {
		args.getUser().sendMessage(Prefix.API.getPrefix().then("Test bestannden!").color(ChatColor.GOLD));
		args.getUser().sendMessage(Prefix.API.getPrefix().then("Welche Welt meinst du?").color(ChatColor.YELLOW));
		final FancyMessage msg = Prefix.API.getPrefix();
		for (final World w : Bukkit.getWorlds()) {
			msg.then(w.getName() + " ").color(ChatColor.AQUA).command("/world create w:" + w.getName()).tooltip("Klickste hier!");
		}
		args.getUser().sendMessage(msg);
	}
	
	private void start(final CommandArgs args) {
		args.getUser().sendMessage(Prefix.API.getPrefix().then("Starte Welt erstellung...").color(ChatColor.GOLD));
		args.getUser().sendMessage(Prefix.API.getPrefix().then("Beim Setup wirst du deine Maus brauchen.").color(ChatColor.GOLD));
		args.getUser().sendMessage(
		        Prefix.API.getPrefix().then("Klicke zum Testen ").color(ChatColor.YELLOW).then("HIER").color(ChatColor.RED)
		                .formattedTooltip(new FancyMessage("Na klick schon!").color(ChatColor.GOLD).style(ChatColor.UNDERLINE, ChatColor.BOLD))
		                .style(ChatColor.UNDERLINE, ChatColor.BOLD).command("/world create s:"));
	}
	
}
