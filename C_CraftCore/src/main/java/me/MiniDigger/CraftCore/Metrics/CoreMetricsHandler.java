package me.MiniDigger.CraftCore.Metrics;

import java.io.IOException;

import org.mcstats.Metrics;
import org.mcstats.Metrics.Graph;
import org.mcstats.Metrics.Plotter;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.AddOn.AddOnBean;
import me.MiniDigger.Core.Game.GameType;
import me.MiniDigger.Core.Lang.LogLevel;
import me.MiniDigger.Core.Metrics.MetricsHandler;
import me.MiniDigger.CraftCore.Lang.MSG;

public class CoreMetricsHandler implements MetricsHandler {

	@Override
	public void start() {
		try {
			Metrics metrics = new Metrics(Core.getCore().getInstance());

			gamemode(metrics);
			addons(metrics);

			metrics.start();
		} catch (IOException e) {
			// Failed to submit the stats :-(
			System.out.println("No metrics for you then!");
			MSG.stacktrace(LogLevel.DEBUG, e);
		}
	}

	private void gamemode(Metrics metrics) {
		Graph g = metrics.createGraph("MainGameMode");
		for (final GameType t : GameType.values()) {
			g.addPlotter(new Plotter(t.getName()) {

				@Override
				public int getValue() {
					return Core.getCore().getGameHandler().getMainGame().getType() == t ? 1 : 0;
				}
			});
		}
	}

	private void addons(Metrics metrics) {
		Graph g = metrics.createGraph("AddOns");
		for (final AddOnBean b : Core.getCore().getRESTHandler().getAllAddOns()) {
			g.addPlotter(new Plotter(b.getName()) {

				@Override
				public int getValue() {
					return Core.getCore().getAddOnHandler().getInstalledNames().contains(b.getName()) ? 1 : 0;
				}
			});
		}
	}
}
