package me.MiniDigger.Core.Cinematic.commands;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Location;

import me.MiniDigger.Core.Cinematic.CameraClip;
import me.MiniDigger.Core.Cinematic.CinematicAddOn;
import me.MiniDigger.Core.Cinematic.CinematicPlayer;
import me.MiniDigger.Core.Cinematic.CompiledReader;
import me.MiniDigger.Core.Cinematic.Recorder;
import me.MiniDigger.Core.Command.Command;
import me.MiniDigger.Core.Command.CommandArgs;
import me.MiniDigger.Core.Prefix.Prefix;

public class CineCommands {
	
	private HashMap<UUID, Recorder>	recs	= new HashMap<UUID, Recorder>();
	
	@Command(name = "cinematic", description = "Macht alles mit Cinematics", permission = "cinematic", aliases = "cine")
	public void cinematic(final CommandArgs args) {
		Prefix.CINE.getPrefix().then("Klicke hier ").color(ChatColor.YELLOW).command("/cine record ").then("um eine Cinematic aufzunehmen").color(ChatColor.GOLD)
		        .send(args.getSender());
		Prefix.CINE.getPrefix().then("Klicke hier ").color(ChatColor.YELLOW).suggest("/cine play ").then("um eine Cinematic abzuspielen").color(ChatColor.GOLD)
		        .send(args.getSender());
		Prefix.CINE.getPrefix().then("Klicke hier ").color(ChatColor.YELLOW).suggest("/cine list").then("um alle Cinematics anzuzeigen").color(ChatColor.GOLD)
		        .send(args.getSender());
	}
	
	@Command(name = "cinematic.record", description = "Recorded eine neue Cinematic", permission = "cinematic.record", usage = "[name]", consol = false, aliases = "cine.record")
	public void record(final CommandArgs args) {
		String name = null;
		if (args.getArgs().length == 1) {
			name = args.getArgs()[0];
		}
		
		if (recs.containsKey(args.getPlayer().getUniqueId())) {
			Recorder rec = recs.get(args.getPlayer().getUniqueId());
			rec.stop();
			return;
		}
		
		Recorder rec = new Recorder(args.getPlayer(), 12, name);
		rec.record();
		recs.put(args.getPlayer().getUniqueId(), rec);
	}
	
	@Command(name = "cinematic.play", description = "Spielt eine Cinematic ab", permission = "cinematic.play", usage = "<name>", min = 1, consol = false, aliases = "cine.play")
	public void play(final CommandArgs args) {
		String name = args.getArgs()[0];
		CameraClip clip;
		try {
			clip = CompiledReader.loadFile(new File(CinematicAddOn.INSTANCE.getDataFolder(), name).getAbsolutePath());
		} catch (Exception e) {
			Prefix.CINE.getPrefix().then("Fehler beim Laden des Clips: " + e.getLocalizedMessage()).color(ChatColor.RED).send(args.getSender());
			return;
		}
		
		final Location loc = args.getPlayer().getLocation();
		CinematicPlayer player = new CinematicPlayer(args.getPlayer(), clip);
		player.setOnCompleted(new Runnable() {
			
			@Override
			public void run() {
				args.getPlayer().teleport(loc);
			}
		});
		player.play();
	}
	
	@Command(name = "cinematic.list", description = "Listet alle Cinematics auf", permission = "cinematic.list", aliases = "cine.list")
	public void list(final CommandArgs args) {
		
	}
}
