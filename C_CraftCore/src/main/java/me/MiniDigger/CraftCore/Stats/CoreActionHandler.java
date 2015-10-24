package me.MiniDigger.CraftCore.Stats;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Lang.LogLevel;
import me.MiniDigger.Core.SQL.SQLQuery;
import me.MiniDigger.Core.Stats.Action;
import me.MiniDigger.Core.Stats.ActionHandler;
import me.MiniDigger.Core.Stats.ActionType;

import me.MiniDigger.CraftCore.Lang.MSG;
import me.MiniDigger.CraftCore.SQL.CoreSQLQuery;

public class CoreActionHandler implements ActionHandler {
	private List<Action>	waiting			= new ArrayList<>();
	private BukkitTask		task;
	public static UUID		PLACEHOLDERUUID	= UUID.fromString("00000000-0000-0000-0000-000000000000");

	@Override
	public void start() {
		createTable();

		task = new BukkitRunnable() {

			@Override
			public void run() {
				insertWaiting();
			}
		}.runTaskTimer(Core.getCore().getInstance(), 20 * 60 * 5, 20 * 60 * 5);

		new BukkitRunnable() {

			@Override
			public void run() {
				new CoreAction(ActionType.TPS, Core.getCore().getInstance().getTPS() + "", PLACEHOLDERUUID).submit();
			}
		}.runTaskTimer(Core.getCore().getInstance(), 20 * 60, 20 * 60);
	}

	@Override
	public void stop() {
		new CoreAction(ActionType.SERVER_RESTART, "", PLACEHOLDERUUID).submit();;
		
		task.cancel();
		insertWaiting();
	}

	@Override
	public void createTable() {
		//@formatter:off
		String query =""
				+ "CREATE TABLE IF NOT EXISTS `action` ( "
				+ "`id` bigint(20) NOT NULL AUTO_INCREMENT, "
				+ "  `date` bigint(20) NOT NULL, "
				+ "  `action` varchar(64) NOT NULL, "
				+ "  `data` varchar(200) NOT NULL, "
				+ "  `user` varchar(64) NOT NULL, "
				+ "  `gametype` varchar(64) NOT NULL, "
				+ "  `server` varchar(64) NOT NULL, "
				+ "  primary key (id)"
				+ ")";
		//@formatter:on

		SQLQuery q = new CoreSQLQuery(query);
		try {
			System.out.println("create table");
			boolean b = q.getStatement().execute();
			System.out.println("success: " + b);
		}
		catch (SQLException e) {
			Core.getCore().getInstance().error("Error while creating table for actions");
			MSG.stacktrace(LogLevel.DEBUG, e);
		}
	}

	@Override
	public void insertWaiting() {
		if (waiting.size() > 0) {
			if (waiting.size() > 500) {
				String query = "INSERT INTO `action` (`id`, `date`, `action`, `data`, `user`, `gametype`, `server`) VALUES ";
				for (int i = 0; i < 500; i++) {
					Action a = waiting.remove(i);
					query += "(NULL, '" + a.getDate().getTime() + "', '" + a.getAction().name() + "', '" + a.getData() + "', '" + a.getUser().toString() + "', '" + a.getGameType().name() + "', '" + a.getServer() + "'),";
				}
				query = Core.getCore().getStringUtil().replaceLast(query, ",", ";");

				SQLQuery q = new CoreSQLQuery(query);
				try {
					q.getStatement().execute();
				}
				catch (SQLException e) {
					Core.getCore().getInstance().error("**************************************************");
					Core.getCore().getInstance().error("**************************************************");
					Core.getCore().getInstance().error("ERROR WHILE SAVING ACTIONS TO DATABASE!");
					Core.getCore().getInstance().error("YOU SHOULD TAKE THIS QUERRY AND EXECUTE IT BY HAND");
					Core.getCore().getInstance().error("OR THE DATA WILL BE LOST FOREVER!");
					Core.getCore().getInstance().error(query);
					Core.getCore().getInstance().error("**************************************************");
					Core.getCore().getInstance().error("**************************************************");
					MSG.stacktrace(LogLevel.DEBUG, e);
				}

				insertWaiting();
			} else {
				String query = "INSERT INTO `action` (`id`, `date`, `action`, `data`, `user`, `gametype`, `server`) VALUES ";
				for (final Action a : waiting) {
					query += "(NULL, '" + a.getDate().getTime() + "', '" + a.getAction().name() + "', '" + a.getData() + "', '" + a.getUser().toString() + "', '" + a.getGameType().name() + "', '" + a.getServer() + "'),";
				}
				query = Core.getCore().getStringUtil().replaceLast(query, ",", ";");

				SQLQuery q = new CoreSQLQuery(query);
				try {
					q.getStatement().execute();
				}
				catch (SQLException e) {
					Core.getCore().getInstance().error("**************************************************");
					Core.getCore().getInstance().error("**************************************************");
					Core.getCore().getInstance().error("ERROR WHILE SAVING ACTIONS TO DATABASE!");
					Core.getCore().getInstance().error("YOU SHOULD TAKE THIS QUERRY AND EXECUTE IT BY HAND");
					Core.getCore().getInstance().error("OR THE DATA WILL BE LOST FOREVER!");
					Core.getCore().getInstance().error(query);
					Core.getCore().getInstance().error("**************************************************");
					Core.getCore().getInstance().error("**************************************************");
					MSG.stacktrace(LogLevel.DEBUG, e);
				}

				waiting.clear();
			}
		}
	}

	@Override
	public void newAction(Action a) {
		waiting.add(a);
	}
}
