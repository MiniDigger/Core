package me.MiniDigger.CraftCore.Metrics;

import java.io.IOException;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.AddOn.AddOnBean;
import me.MiniDigger.Core.Game.GameType;
import me.MiniDigger.Core.Lang.LogLevel;
import me.MiniDigger.Core.Metrics.Metrics;
import me.MiniDigger.Core.Metrics.Metrics.Graph;
import me.MiniDigger.Core.Metrics.Metrics.Plotter;
import me.MiniDigger.Core.Metrics.MetricsHandler;
import me.MiniDigger.CraftCore.Lang.MSG;

public class CoreMetricsHandler implements MetricsHandler {

	@Override
	public void start() {
		try {
			final Metrics metrics = new Metrics(Core.getCore().getInstance());

			gamemode(metrics);
			addons(metrics);

			metrics.start();
		}
		catch (final IOException e) {
			// Failed to submit the stats :-(
			Core.getCore().getInstance().debug("No metrics for you then!");
			MSG.stacktrace(LogLevel.DEBUG, e);
		}

		final CoreConnectHandler c = new CoreConnectHandler();
		c.start();
	}

	private void gamemode(final Metrics metrics) {
		final Graph g = metrics.createGraph("MainGameMode");
		for (final GameType t : GameType.values()) {
			g.addPlotter(new Plotter(t.getName()) {

				@Override
				public int getValue() {
					return Core.getCore().getGameHandler().getMainGame().getType() == t ? 1 : 0;
				}
			});
		}
	}

	private void addons(final Metrics metrics) {
		final Graph g = metrics.createGraph("AddOns");
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
