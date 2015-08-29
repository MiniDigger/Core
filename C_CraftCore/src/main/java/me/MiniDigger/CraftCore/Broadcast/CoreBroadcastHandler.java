package me.MiniDigger.CraftCore.Broadcast;

import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Broadcast.BroadcastHandler;
import me.MiniDigger.Core.Broadcast.BroadcastMessage;
import me.MiniDigger.Core.Lang.LogLevel;
import me.MiniDigger.Core.Prefix.Prefix;
import me.MiniDigger.CraftCore.Lang.MSG;
import mkremins.fanciful.FancyMessage;

public class CoreBroadcastHandler implements BroadcastHandler {

	private List<BroadcastMessage>	msgs	= new ArrayList<BroadcastMessage>();
	private final File				file	= new File(Core.getCore().getInstance().getDataFolder(), "broadcast.json");
	private final List<BukkitTask>	tasks	= new ArrayList<BukkitTask>();

	@Override
	public void init() {
		loadAll();
		start();
	}

	public void start() {
		for (final BukkitTask t : tasks) {
			t.cancel();
		}
		tasks.clear();

		for (final BroadcastMessage msg : msgs) {
			tasks.add(new BukkitRunnable() {

				@Override
				public void run() {
					final FancyMessage msg1 = Prefix.BROADCAST.getPrefix().then(msg.getCurrent());
					for (final Player p : Core.getCore().getUserHandler().getOnlinePlayers()) {
						msg1.send(p);
					}
				}
			}.runTaskTimer(Core.getCore().getInstance(), msg.getDelay(), msg.getInterval()));
		}
	}

	public void loadAll() {
		msgs = new ArrayList<BroadcastMessage>();
		try {
			final JSONArray a = (JSONArray) new JSONParser().parse(new FileReader(file));
			@SuppressWarnings("unchecked")
			final Iterator<JSONObject> iterator = a.iterator();
			while (iterator.hasNext()) {
				final BroadcastMessage msg = new CoreBroadcastMessage();
				msg.load(iterator.next());
				msgs.add(msg);
			}
		}
		catch (final Exception e) {
			MSG.stacktrace(LogLevel.DEBUG, e);
		}
	}

	@SuppressWarnings("unchecked")
	public void saveAll() {
		Core.getCore().getInstance().debug("save");
		final List<JSONObject> o = new ArrayList<JSONObject>();

		for (final BroadcastMessage msg : msgs) {
			o.add(msg.save());
		}

		final JSONArray a = new JSONArray();
		a.addAll(o);

		try {
			a.writeJSONString(new PrintWriter(file));
			Core.getCore().getInstance().debug("saved");
		}
		catch (final Exception e) {
			MSG.stacktrace(LogLevel.DEBUG, e);
		}
	}

}
