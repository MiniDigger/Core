package me.MiniDigger.CraftCore.Broadcast;

import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Broadcast.BroadcastHandler;
import me.MiniDigger.Core.Broadcast.BroadcastMessage;
import me.MiniDigger.Core.Lang.LogLevel;
import me.MiniDigger.Core.Prefix.Prefix;

import me.MiniDigger.CraftCore.Lang._;

import mkremins.fanciful.FancyMessage;

public class CoreBroadcastHandler implements BroadcastHandler {
	
	private List<BroadcastMessage>	msgs	= new ArrayList<BroadcastMessage>();
	private File	               file	 = new File(Core.getCore().getInstance().getDataFolder(), "broadcast.json");
	private List<BukkitTask>	   tasks	= new ArrayList<BukkitTask>();
	
	@Override
	public void init() {
		loadAll();
		start();
	}
	
	public void start() {
		for (BukkitTask t : tasks) {
			t.cancel();
		}
		tasks.clear();
		
		for (final BroadcastMessage msg : msgs) {
			tasks.add(new BukkitRunnable() {
				
				@Override
				public void run() {
					FancyMessage msg1 = Prefix.BROADCAST.getPrefix().then(msg.getCurrent());
					for (Player p : Core.getCore().getUserHandler().getOnlinePlayers()) {
						msg1.send(p);
					}
				}
			}.runTaskTimer(Core.getCore().getInstance(), msg.getDelay(), msg.getInterval()));
		}
	}
	
	public void loadAll() {
		msgs = new ArrayList<BroadcastMessage>();
		try {
			JSONArray a = (JSONArray) new JSONParser().parse(new FileReader(file));
			@SuppressWarnings("unchecked") Iterator<JSONObject> iterator = a.iterator();
			while (iterator.hasNext()) {
				BroadcastMessage msg = new CoreBroadcastMessage();
				msg.load(iterator.next());
				msgs.add(msg);
			}
		} catch (Exception e) {
			_.stacktrace(LogLevel.DEBUG, e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void saveAll() {
		System.out.println("save");
		List<JSONObject> o = new ArrayList<JSONObject>();
		
		for (BroadcastMessage msg : msgs) {
			o.add(msg.save());
		}
		
		JSONArray a = new JSONArray();
		a.addAll(o);
		
		try {
			a.writeJSONString(new PrintWriter(file));
			System.out.println("saved");
		} catch (Exception e) {
			_.stacktrace(LogLevel.DEBUG, e);
		}
	}
	
}
