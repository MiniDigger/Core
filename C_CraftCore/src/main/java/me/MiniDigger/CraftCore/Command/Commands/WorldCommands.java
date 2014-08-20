package me.MiniDigger.CraftCore.Command.Commands;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import me.MiniDigger.Core.Core;
import mkremins.fanciful.FancyMessage;
import net.minecraft.server.v1_7_R3.ExceptionWorldConflict;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_7_R3.CraftServer;
import org.bukkit.craftbukkit.v1_7_R3.CraftWorld;
import org.bukkit.entity.Player;

public class WorldCommands {
	
	@Command(name = "weather", description = "Ändert das Wetter", usage = "[wetter] [zeit]", permission = "weather")
	public void weather(CommandArgs args) {
		if (!args.isUser()) {
			args.getSender().sendMessage(Prefix.API.getConsolPrefix() + " Nur Ingame!");
		}
		
		World w = args.getPlayer().getWorld();
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
				} catch (Exception ex) {
					args.getUser().sendMessage(Prefix.API.getPrefix().then("Unbekannte Dauer! ").color(ChatColor.RED).then(args.getArgs()[1]).color(ChatColor.DARK_RED));
					return;
				}
			}
			boolean storm = false;
			boolean thunder = false;
			String[] stormWords = new String[] { "storm", "rain", "regen", "sturm" };
			String[] thunderWords = new String[] { "thunder", "gewitter" };
			String[] sunWorlds = new String[] { "sun", "sonne" };
			if (StringUtils.contains(stormWords, args.getArgs()[0].toLowerCase())) {
				storm = true;
			} else if (StringUtils.contains(thunderWords, args.getArgs()[0].toLowerCase())) {
				storm = true;
				thunder = true;
			} else if (StringUtils.contains(sunWorlds, args.getArgs()[0].toLowerCase())) {
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
	
	@Command(name = "world.info", description = "Zeigt Infos übder die aktuelle Welt", usage = "", permission = "world.info")
	public void info(CommandArgs args) {
		if (!args.isUser()) {
			args.getSender().sendMessage(Prefix.API.getConsolPrefix() + " Nur Ingame!");
		}
		
		args.getUser().sendMessage(Prefix.API.getPrefix().then("Du bist auf World " + args.getPlayer().getWorld().getName()).color(ChatColor.GREEN));
	}
	
	@Command(name = "tp", description = "Teleportiert", usage = "<x> <y> <z> / <spieler>")
	public void tp2(CommandArgs args) {
		if ((args.getArgs().length != 1 && args.getArgs().length != 3) || !args.isUser()) {
			args.getSender().sendMessage(Prefix.API.getConsolPrefix() + " Falsche Benutzung!");
		}
		if (args.getArgs().length == 1) {
			@SuppressWarnings("deprecation") Player p = Bukkit.getPlayer(args.getArgs()[0]);
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
			} catch (Exception ex) {
				args.getUser().sendMessage(Prefix.API.getPrefix().then("Falsche Eingabe!").color(ChatColor.RED));
				return;
			}
			args.getPlayer().teleport(new Location(args.getPlayer().getWorld(), x, y, z));
			args.getUser().sendMessage(Prefix.API.getPrefix().then("Wuusch..."));
		}
	}
	
	@Command(name = "world.load", description = "Läd eine Welt", usage = "<gametype/name>", permission = "world.load")
	public void load(CommandArgs args) {
		if (args.getArgs().length < 1 || !args.isUser()) {
			args.getSender().sendMessage(Prefix.API.getConsolPrefix() + " Falsche benutzung!");
		}
		
		String map = args.getArgs()[0];
		
		if (!(args.getArgs().length > 1 && args.getArgs()[1].contains("-f"))) {
			File mapFolder = new File(Core.getInstance().getConfig().getString("mapFolder"));
			File mapConfig = new File(mapFolder, "maps.yml");
			
			FileConfiguration con = YamlConfiguration.loadConfiguration(mapConfig);
			List<String> maps = con.getStringList("maps");
			
			if (maps.contains(map)) {
				WorldHandler.getInstance().copyWorld(map);
				WorldHandler.getInstance().loadWorld(map);
				args.getUser().sendMessage(Prefix.API.getPrefix().then("Map geladen!").color(ChatColor.GREEN));
			} else {
				args.getUser().sendMessage(Prefix.API.getPrefix().then("Unbekannte Map!").color(ChatColor.RED));
			}
		} else {
			try {
				WorldHandler.getInstance().loadWorld(map);
				args.getUser().sendMessage(Prefix.API.getPrefix().then("Map geladen!").color(ChatColor.GREEN));
			} catch (Exception ex) {
				args.getUser().sendMessage(Prefix.API.getPrefix().then("Map nicht geladen!").color(ChatColor.RED));
			}
		}
		
		if ((args.getArgs().length > 1 && args.getArgs()[1].contains("-m"))) {
			args.getUser().sendMessage(Prefix.API.getPrefix().then("Setze Wool!").color(ChatColor.GREEN));
			int i = MapHandler.getInstance().getMap(map).setLocs();
			args.getUser().sendMessage(Prefix.API.getPrefix().then(i + " Wools gesetzt").color(ChatColor.GREEN));
		} else {
			args.getUser().sendMessage(Prefix.API.getPrefix().then("Lösche Wolle!").color(ChatColor.GREEN));
			MapHandler.getInstance().getMap(map).clearLocs();
		}
	}
	
	@Command(name = "world.unload", description = "Löscht eine Welt", usage = "<name>", permission = "world.unload")
	public void unload(CommandArgs args) {
		if (args.getArgs().length < 1 || !args.isUser()) {
			args.getSender().sendMessage(Prefix.API.getConsolPrefix() + " Falsche benutzung!");
		}
		
		String map = args.getArgs()[0];
		
		File mapFolder = new File(Core.getInstance().getConfig().getString("mapFolder"));
		File mapConfig = new File(mapFolder, "maps.yml");
		
		FileConfiguration con = YamlConfiguration.loadConfiguration(mapConfig);
		List<String> maps = con.getStringList("maps");
		
		if (maps.contains(map) && Bukkit.getWorld(map) != null || (args.getArgs().length > 1 && args.getArgs()[1].contains("-f"))) {
			try {
				WorldHandler.getInstance().unloadWorld(map, Bukkit.getWorlds().get(0).getSpawnLocation());
				args.getUser().sendMessage(Prefix.API.getPrefix().then("Welt unloaded! Lösche sie..."));
			} catch (Exception ex) {}
			System.gc();
			File file = new File(StringUtils.replaceLast(Bukkit.getWorldContainer().getAbsolutePath(), ".", ""), map);
			FileUtils.deleteDirectory(file);
			args.getUser().sendMessage(Prefix.API.getPrefix().then("Welt gelöscht!"));
		} else {
			args.getUser().sendMessage(Prefix.API.getPrefix().then("Unbekannt Map!").color(ChatColor.RED));
		}
	}
	
	@Command(name = "world.tp", description = "Teleportiert zu einer Welt", usage = "<name>", permission = "world.tp")
	public void tp(CommandArgs args) {
		if (!args.isUser()) {
			args.getSender().sendMessage("Player only!");
			return;
		}
		
		if (args.getArgs().length == 0) {
			args.getUser().sendMessage(Prefix.API.getPrefix().then("Du musst ne Welt angeben!").color(ChatColor.RED));
			return;
		}
		
		World w = Bukkit.getWorld(args.getArgs()[0]);
		if (w == null) {
			args.getUser().sendMessage(Prefix.API.getPrefix().then("Unbekannte Welt!").color(ChatColor.RED));
			return;
		}
		
		args.getPlayer().teleport(w.getSpawnLocation());
		args.getUser().sendMessage(Prefix.API.getPrefix().then("Wuusch..."));
	}
	
	@Command(name = "world.list", description = "Zeit alle Welten", usage = "", permission = "world.list")
	public void list(CommandArgs args) {
		File mapFolder = new File(Core.getInstance().getConfig().getString("mapFolder"));
		File mapConfig = new File(mapFolder, "maps.yml");
		
		FileConfiguration con = YamlConfiguration.loadConfiguration(mapConfig);
		List<String> maps = con.getStringList("maps");
		
		FancyMessage header = Prefix.API.getPrefix().then("Registrierte Welten: ").color(ChatColor.GOLD);
		
		if (args.isUser()) {
			args.getUser().sendMessage(header);
		} else {
			args.getSender().sendMessage(header.toOldMessageFormat());
		}
		
		for (String map : maps) {
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
	
	@Command(name = "world.create", description = "Erstellt ne neue Map", usage = "", permission = "world.create")
	public void create(final CommandArgs args) {
		if (data.get(args.getUser().getUuid()) == null) {
			data.put(args.getUser().getUuid(), new HashMap<String, String>());
		}
		final HashMap<String, String> session = data.get(args.getUser().getUuid());
		if (args.getArgs().length != 0) {
			if (args.getArgs()[0].startsWith("w:")) {
				String in = args.getArgs()[0].replace("w:", "");
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
				Location l = args.getPlayer().getLocation();
				if (l == null || !l.getWorld().getName().equals(session.get("world"))) {
					args.getUser().sendMessage(Prefix.API.getPrefix().then("Fehler!").color(ChatColor.RED));
					middle(args);
					return;
				}
				session.put("middle", LocationUtils.LocationToString(l));
			} else if (args.getArgs()[0].startsWith("r:")) {
				String in = args.getArgs()[0].replace("r:", "");
				try {
					Integer.parseInt(in);
				} catch (Exception ex) {
					args.getUser().sendMessage(Prefix.API.getPrefix().then("Gibt ne Zahl ein!").color(ChatColor.RED));
					range(args);
					return;
				}
				session.put("range", in);
			} else if (args.getArgs()[0].startsWith("n:")) {
				String in = args.getArgs()[0].replace("n:", "");
				session.put("name", in);
			} else if (args.getArgs()[0].startsWith("a:")) {
				String in = args.getArgs()[0].replace("a:", "");
				session.put("author", in);
			} else if (args.getArgs()[0].startsWith("t:")) {
				String in = args.getArgs()[0].replace("t:", "");
				if (session.containsKey("gameTypes")) {
					String old = session.get("gameTypes");
					if (old.contains(",")) {
						List<String> types = StringUtils.stringToList(old);
						types.add(in);
						session.remove("gameTypes");
						session.put("gameTypes", StringUtils.listToString(types));
					} else {
						List<String> types = new ArrayList<>();
						types.add(old);
						types.add(in);
						session.remove("gameTypes");
						session.put("gameTypes", StringUtils.listToString(types));
					}
				} else {
					session.put("gameTypes", in);
				}
			} else if (args.getArgs()[0].equals("finish")) {
				session.put("finished", "yeah!");
			} else {
				args.getUser().sendMessage(Prefix.API.getPrefix().then("Unknown args!").color(ChatColor.RED));
			}
			data.remove(data.get(args.getUser().getUuid()));
			data.put(args.getUser().getUuid(), session);
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
		
		final MapData data = new MapData(session.get("world"));
		data.scanMap(LocationUtils.StringToLocation(session.get("middle")), Integer.parseInt(session.get("range")), new Runnable() {
			
			@Override
			public void run() {
				data.createConfig();
				
				File mapFolder = new File(Core.getInstance().getConfig().getString("mapFolder"));
				File mapConfig = new File(mapFolder, "maps.yml");
				
				FileConfiguration con = YamlConfiguration.loadConfiguration(mapConfig);
				List<String> maps = con.getStringList("maps");
				maps.add(session.get("world"));
				con.set("maps", maps);
				
				List<String> types = StringUtils.stringToList(session.get("gameTypes"));
				con.set(session.get("world") + ".gametypes", types);
				con.set(session.get("world") + ".author", session.get("author"));
				con.set(session.get("world") + ".name", session.get("name"));
				
				Bukkit.getWorld(session.get("world")).setAutoSave(false);
				args.getUser().sendMessage(Prefix.API.getPrefix().then("Speicher...").color(ChatColor.GREEN));
				World world = Bukkit.getWorld(session.get("world"));
				CraftWorld w = (CraftWorld) world;
				
				((CraftServer) Bukkit.getServer()).checkSaveState();
				w.getHandle().savingDisabled = false;
				try {
					w.getHandle().save(true, null);
				} catch (ExceptionWorldConflict e2) {
					e2.printStackTrace();
				}
				w.getHandle().savingDisabled = true;
				
				try {
					con.save(mapConfig);
					args.getUser().sendMessage(Prefix.API.getPrefix().then("FERTIG!").color(ChatColor.GREEN));
				} catch (IOException e) {
					args.getUser().sendMessage(Prefix.API.getPrefix().then("Error!").color(ChatColor.RED));
					e.printStackTrace();
				}
				
				args.getUser().sendMessage(Prefix.API.getPrefix().then("Zippe..."));
				
				try {
					ZipUtils.zipFolder(StringUtils.replaceLast(Bukkit.getWorldContainer().getAbsolutePath(), ".", "") + File.separatorChar + session.get("world"), Core
					        .getInstance().getConfig().getString("mapFolder")
					        + File.separatorChar + session.get("world") + ".zip");
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("ZIP ALLREADY THERE?! Retrying...");
					try {
						ZipUtils.zipFolder(StringUtils.replaceLast(Bukkit.getWorldContainer().getAbsolutePath(), ".", "") + File.separatorChar + session.get("world"),
						        Core.getInstance().getConfig().getString("mapFolder") + File.separatorChar + session.get("world") + ".zip"
						                + RandomUtil.getInstance().nextInt(1000));
					} catch (Exception e1) {
						e1.printStackTrace();
						System.out.println("Giving up....");
					}
				}
				
				args.getUser().sendMessage(Prefix.API.getPrefix().then("Finished!"));
				WorldCommands.this.data.remove(args.getUser().getUuid());
			}
		});
	}
	
	private void gameTypes(CommandArgs args) {
		args.getUser().sendMessage(Prefix.API.getPrefix().then("Welche Modi soll man Spielen? Du kannst mehrmals klicken!").color(ChatColor.YELLOW));
		FancyMessage msg = Prefix.API.getPrefix();
		for (GameType type : GameType.values()) {
			msg.then(type.getName() + " ").command("/world create t:" + type.name());
		}
		args.getUser().sendMessage(msg);
		args.getUser().sendMessage(Prefix.API.getPrefix().then("Fertig? Klick mich hart!").command("/world create finish"));
		
	}
	
	private void name(CommandArgs args) {
		args.getUser().sendMessage(Prefix.API.getPrefix().then("Wie heißt die Map? KEINE LEERZEICHEN!").color(ChatColor.YELLOW));
		args.getUser().sendMessage(Prefix.API.getPrefix().then("Klick hier und gibt den Namen ein").color(ChatColor.YELLOW).suggest("/world create n:"));
	}
	
	private void author(CommandArgs args) {
		args.getUser().sendMessage(Prefix.API.getPrefix().then("Wer hat die Map gebaut?").color(ChatColor.YELLOW));
		args.getUser().sendMessage(Prefix.API.getPrefix().then("Klick hier und gibt den Author ein").color(ChatColor.YELLOW).suggest("/world create a:"));
	}
	
	private void range(CommandArgs args) {
		args.getUser().sendMessage(Prefix.API.getPrefix().then("Wie groß ist der Radius der Map? Lieber zu viel als zu wenig!").color(ChatColor.YELLOW));
		args.getUser().sendMessage(Prefix.API.getPrefix().then("Klick hier und gib den Radius ein").color(ChatColor.YELLOW).suggest("/world create r:"));
	}
	
	private void middle(CommandArgs args) {
		args.getUser().sendMessage(Prefix.API.getPrefix().then("Wo ist der Mittelpunkt? Muss nicht exakt sein, aber annähernd!").color(ChatColor.YELLOW));
		args.getUser().sendMessage(Prefix.API.getPrefix().then("Klick hier wenn du im Mittelpunkt bist").color(ChatColor.YELLOW).command("/world create m:"));
	}
	
	private void world(CommandArgs args) {
		args.getUser().sendMessage(Prefix.API.getPrefix().then("Test bestannden!").color(ChatColor.GOLD));
		args.getUser().sendMessage(Prefix.API.getPrefix().then("Welche Welt meinst du?").color(ChatColor.YELLOW));
		FancyMessage msg = Prefix.API.getPrefix();
		for (World w : Bukkit.getWorlds()) {
			msg.then(w.getName() + " ").color(ChatColor.AQUA).command("/world create w:" + w.getName()).tooltip("Klickste hier!");
		}
		args.getUser().sendMessage(msg);
	}
	
	private void start(CommandArgs args) {
		args.getUser().sendMessage(Prefix.API.getPrefix().then("Starte Welt erstellung...").color(ChatColor.GOLD));
		args.getUser().sendMessage(Prefix.API.getPrefix().then("Beim Setup wirst du deine Maus brauchen.").color(ChatColor.GOLD));
		args.getUser().sendMessage(
		        Prefix.API.getPrefix().then("Klicke zum Testen ").color(ChatColor.YELLOW).then("HIER").color(ChatColor.RED)
		                .formattedTooltip(new FancyMessage("Na klick schon!").color(ChatColor.GOLD).style(ChatColor.UNDERLINE, ChatColor.BOLD))
		                .style(ChatColor.UNDERLINE, ChatColor.BOLD).command("/world create s:"));
	}
	
}
