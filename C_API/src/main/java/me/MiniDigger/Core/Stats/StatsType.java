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
package me.MiniDigger.Core.Stats;

import java.util.ArrayList;
import java.util.List;

public class StatsType {

	public static class BedWars extends StatsGame {

		private static String gameName = "BedWars";

		public static StatsType	BEDS	= new StatsType(gameName, "beds");
		public static StatsType	TRADED	= new StatsType(gameName, "traded");

		public static List<StatsType> values() {
			final List<StatsType> values = StatsGame.values(gameName);
			values.add(TRADED);
			values.add(BEDS);
			return values;
		}
	}

	public static class Common {

		protected static String	gameName		= "common";
		public static StatsType	PAESSE			= new StatsType(gameName, "paesse");
		public static StatsType	SPIELZEIT		= new StatsType(gameName, "spielzeit");
		public static StatsType	TOKENS			= new StatsType(gameName, "tokens", 100);
		public static StatsType	TOKENS_SPEND	= new StatsType(gameName, "tokens_spend", 100);

		public static List<StatsType> values() {
			final List<StatsType> values = new ArrayList<>();
			values.add(TOKENS);
			values.add(TOKENS_SPEND);
			values.add(SPIELZEIT);
			return values;
		}
	}

	public static class Crank extends StatsGame {

		private static String gameName = "Crank";

		public static StatsType EXPLODED = new StatsType(gameName, "exploded");

		public static List<StatsType> values() {
			final List<StatsType> values = StatsGame.values(gameName);
			values.add(EXPLODED);
			return values;
		}
	}

	public static class GetTheDrop extends StatsGame {

		private static String gameName = "GetTheDrop";

		public static StatsType	DROPS_1	= new StatsType(gameName, "drops1");
		public static StatsType	DROPS_2	= new StatsType(gameName, "drops2");

		public static List<StatsType> values() {
			final List<StatsType> values = StatsGame.values(gameName);
			values.add(DROPS_1);
			values.add(DROPS_2);
			return values;
		}
	}

	public static class OneInTheChamber extends StatsGame {

		private static String gameName = "OneInTheChamber";

		public static StatsType BOW = new StatsType(gameName, "bow");

		public static List<StatsType> values() {
			final List<StatsType> values = StatsGame.values(gameName);
			values.add(BOW);
			return values;
		}
	}

	public static class StatsGame {

		protected static String gameName = "";

		public static StatsType	DEATHS	= new StatsType(gameName, "deaths");
		public static StatsType	GAMES	= new StatsType(gameName, "games");
		public static StatsType	JOINS	= new StatsType(gameName, "joins");
		public static StatsType	KILLS	= new StatsType(gameName, "kills");
		public static StatsType	WINS	= new StatsType(gameName, "wins");

		public static List<StatsType> values(final String gameName) {
			final List<StatsType> values = new ArrayList<>();
			values.add(new StatsType(gameName, "wins"));
			values.add(new StatsType(gameName, "deaths"));
			values.add(new StatsType(gameName, "games"));
			values.add(new StatsType(gameName, "kills"));
			values.add(new StatsType(gameName, "joins"));
			return values;
		}

		public static List<StatsType> values(final String gameName, final boolean pvp) {
			if (pvp) {
				return values(gameName);
			}
			final List<StatsType> values = new ArrayList<>();
			values.add(new StatsType(gameName, "wins"));
			values.add(new StatsType(gameName, "games"));
			return values;
		}
	}

	public static class TicTacToe extends StatsGame {

		private static String gameName = "TicTacToe";

		public static StatsType CLICKS = new StatsType(gameName, "clicks");

		public static List<StatsType> values() {
			final List<StatsType> values = StatsGame.values(gameName, false);
			values.add(CLICKS);
			return values;
		}
	}

	public static class UltraSpleef extends StatsGame {

		private static String gameName = "UltraSpleef";

		public static List<StatsType> values() {
			final List<StatsType> values = StatsGame.values(gameName);
			return values;
		}
	}

	public static StatsType valueOf(final String s) {
		for (final StatsType type : values()) {
			if ((type.getGame() + "." + type.getStats()).equalsIgnoreCase(s)) {
				return type;
			}
		}
		return null;
	}

	public static List<StatsType> values() {
		final List<StatsType> values = new ArrayList<>();
		values.addAll(GetTheDrop.values());
		values.addAll(Common.values());
		values.addAll(Crank.values());
		values.addAll(BedWars.values());
		values.addAll(OneInTheChamber.values());
		values.addAll(UltraSpleef.values());
		return values;
	}

	private int defaultValue;

	private final String game;

	private final String stat;

	public StatsType(final String game, final String stat) {
		this.game = game;
		this.stat = stat;
		defaultValue = 0;
	}

	public StatsType(final String game, final String stat, final int defaultValue) {
		this(game, stat);
		this.defaultValue = defaultValue;
	}

	public int getDefaultValue() {
		return defaultValue;
	}

	public String getGame() {
		return game;
	}

	public String getStats() {
		return stat;
	}
}
