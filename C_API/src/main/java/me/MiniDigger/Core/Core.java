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
package me.MiniDigger.Core;

import me.MiniDigger.Core.Achievement.AchievementHandler;
import me.MiniDigger.Core.AddOn.AddOnHandler;
import me.MiniDigger.Core.Bar.BarHandler;
import me.MiniDigger.Core.Block.BuildHandler;
import me.MiniDigger.Core.Booster.BoosterHandler;
import me.MiniDigger.Core.Chat.ChatHandler;
import me.MiniDigger.Core.Command.CommandHandler;
import me.MiniDigger.Core.Dependencies.DependencyHanlder;
import me.MiniDigger.Core.Error.ErrorHandler;
import me.MiniDigger.Core.Game.GameHandler;
import me.MiniDigger.Core.Holo.HoloHandler;
import me.MiniDigger.Core.Item.ItemHandler;
import me.MiniDigger.Core.Kit.KitHandler;
import me.MiniDigger.Core.Lang.LangHandler;
import me.MiniDigger.Core.Licence.LicenseHandler;
import me.MiniDigger.Core.Map.MapHandler;
import me.MiniDigger.Core.Menu.MenuHandler;
import me.MiniDigger.Core.Mirror.MirrorHandler;
import me.MiniDigger.Core.Packet.PacketHandler;
import me.MiniDigger.Core.Protocol.ProtocolHandler;
import me.MiniDigger.Core.REST.DashingHandler;
import me.MiniDigger.Core.REST.RESTHandler;
import me.MiniDigger.Core.Regen.RegenHandler;
import me.MiniDigger.Core.SQL.SQLHandler;
import me.MiniDigger.Core.Scoreboard.ScoreboardHandler;
import me.MiniDigger.Core.Server.ServerHandler;
import me.MiniDigger.Core.Socket.SocketHandler;
import me.MiniDigger.Core.Socket.Client.ClientHandler;
import me.MiniDigger.Core.Squad.SquadHandler;
import me.MiniDigger.Core.Stats.StatsHandler;
import me.MiniDigger.Core.Tasks.TaskHandler;
import me.MiniDigger.Core.Tip.TipHandler;
import me.MiniDigger.Core.Update.UpdateHandler;
import me.MiniDigger.Core.User.UserHandler;
import me.MiniDigger.Core.Util.BaseUtil;
import me.MiniDigger.Core.Util.ChatColorUtil;
import me.MiniDigger.Core.Util.CommonMethods;
import me.MiniDigger.Core.Util.DeZipUtil;
import me.MiniDigger.Core.Util.EntityUtil;
import me.MiniDigger.Core.Util.EnumUtil;
import me.MiniDigger.Core.Util.FaceUtil;
import me.MiniDigger.Core.Util.FileUtil;
import me.MiniDigger.Core.Util.ItemUtil;
import me.MiniDigger.Core.Util.LocationUtil;
import me.MiniDigger.Core.Util.MathUtil;
import me.MiniDigger.Core.Util.PlayerUtil;
import me.MiniDigger.Core.Util.RandomUtil;
import me.MiniDigger.Core.Util.ReflectionUtil;
import me.MiniDigger.Core.Util.ShutdownUtil;
import me.MiniDigger.Core.Util.StringUtil;
import me.MiniDigger.Core.Util.TimeUtil;
import me.MiniDigger.Core.Util.ZipUtil;
import me.MiniDigger.Core.Villager.VillagerHandler;
import me.MiniDigger.Core.World.WorldHandler;

public abstract class Core {
	
	private static Core	     core;
	private static final int	max	= 100;
	private static int	     i	    = max + 1;
	
	public Core() {
		Core.core = this;
	}
	
	public void enable() {
		i = 0;
	}
	
	public static Core getCore() {
		if (i == max) {
			core.getLicenseHandler().performCheckAsync();
			i = 0;
		} else {
			i++;
		}
		return core;
	}
	
	public void disable() {
		core = null;
	}
	
	public abstract Main getInstance();
	
	/*
	 * #################### HANDLER ####################
	 */
	
	public abstract ItemHandler getItemHandler();
	
	public abstract AchievementHandler getAchievmentHandler();
	
	public abstract BuildHandler getBuildHandler();
	
	public abstract BoosterHandler getBoosterHandler();
	
	public abstract ChatHandler getChatHandler();
	
	public abstract CommandHandler getCommandHandler();
	
	public abstract GameHandler getGameHandler();
	
	public abstract HoloHandler getHoloHandler();
	
	public abstract MapHandler getMapHandler();
	
	public abstract PacketHandler getPacketHandler();
	
	public abstract ProtocolHandler getProtocolHandler();
	
	public abstract RegenHandler getRegenHandler();
	
	public abstract ServerHandler getServerHandler();
	
	public abstract SocketHandler getSocketHandler();
	
	public abstract ClientHandler getClientHandler();
	
	public abstract SquadHandler getSquadHandler();
	
	public abstract SQLHandler getSqlHandler();
	
	public abstract StatsHandler getStatsHandler();
	
	public abstract UpdateHandler getUpdateHandler();
	
	public abstract TipHandler getTipHandler();
	
	public abstract UserHandler getUserHandler();
	
	public abstract WorldHandler getWorldHandler();
	
	public abstract BarHandler getBarHandler();
	
	public abstract RESTHandler getRESTHandler();
	
	public abstract AddOnHandler getAddOnHandler();
	
	public abstract MirrorHandler getMirrorHandler();
	
	public abstract LicenseHandler getLicenseHandler();
	
	public abstract VillagerHandler getVillagerHandler();
	
	public abstract KitHandler getKitHandler();
	
	public abstract ScoreboardHandler getScoreboardHandler();
	
	public abstract DashingHandler getDashingHandler();
	
	public abstract LangHandler getLangHandler();
	
	public abstract DependencyHanlder getDependencyHanlder();
	
	public abstract MenuHandler getMenuHandler();
	
	public abstract TaskHandler getTaskHandler();
	
	/*
	 * #################### UTILS ####################
	 */
	
	public abstract ChatColorUtil getChatColorUtil();
	
	public abstract CommonMethods getCommonMethods();
	
	public abstract DeZipUtil getDeZipUtil();
	
	public abstract EntityUtil getEntityUtil();
	
	public abstract FileUtil getFileUtil();
	
	public abstract LocationUtil getLocationUtil();
	
	public abstract PlayerUtil getPlayerUtil();
	
	public abstract RandomUtil getRandomUtil();
	
	public abstract ShutdownUtil getShutdownUtil();
	
	public abstract StringUtil getStringUtil();
	
	public abstract TimeUtil getTimeUtil();
	
	public abstract ZipUtil getZipUtil();
	
	public abstract ReflectionUtil getReflectionUtil();
	
	public abstract BaseUtil getBaseUtil();
	
	public abstract EnumUtil getEnumUtil();
	
	public abstract ItemUtil getItemUtil();
	
	public abstract MathUtil getMathUtil();
	
	public abstract FaceUtil getFaceUtil();
	
	public abstract void setCommandHandler(final CommandHandler c);
	
	public abstract ErrorHandler getErrorHandler();
}
