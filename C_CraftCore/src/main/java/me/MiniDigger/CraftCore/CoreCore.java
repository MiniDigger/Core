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
package me.MiniDigger.CraftCore;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Main;
import me.MiniDigger.Core.Achievement.AchievementHandler;
import me.MiniDigger.Core.AddOn.AddOnHandler;
import me.MiniDigger.Core.Bar.BarHandler;
import me.MiniDigger.Core.Block.BuildHandler;
import me.MiniDigger.Core.Booster.BoosterHandler;
import me.MiniDigger.Core.Chat.ChatHandler;
import me.MiniDigger.Core.Command.CommandHandler;
import me.MiniDigger.Core.Game.GameHandler;
import me.MiniDigger.Core.Holo.HoloHandler;
import me.MiniDigger.Core.Item.ItemHandler;
import me.MiniDigger.Core.Kit.KitHandler;
import me.MiniDigger.Core.Lang.LangHandler;
import me.MiniDigger.Core.Licence.LicenseHandler;
import me.MiniDigger.Core.Map.MapHandler;
import me.MiniDigger.Core.Mirror.MirrorHandler;
import me.MiniDigger.Core.Nametag.NametagHandler;
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
import me.MiniDigger.Core.Tip.TipHandler;
import me.MiniDigger.Core.Update.UpdateHandler;
import me.MiniDigger.Core.User.UserHandler;
import me.MiniDigger.Core.Util.BaseUtil;
import me.MiniDigger.Core.Util.ChatColorUtil;
import me.MiniDigger.Core.Util.CommonMethods;
import me.MiniDigger.Core.Util.DeZipUtil;
import me.MiniDigger.Core.Util.EntityUtil;
import me.MiniDigger.Core.Util.EnumUtil;
import me.MiniDigger.Core.Util.FileUtil;
import me.MiniDigger.Core.Util.ItemUtil;
import me.MiniDigger.Core.Util.LocationUtil;
import me.MiniDigger.Core.Util.PlayerUtil;
import me.MiniDigger.Core.Util.RandomUtil;
import me.MiniDigger.Core.Util.ReflectionUtil;
import me.MiniDigger.Core.Util.ShutdownUtil;
import me.MiniDigger.Core.Util.StringUtil;
import me.MiniDigger.Core.Util.TimeUtil;
import me.MiniDigger.Core.Util.ZipUtil;
import me.MiniDigger.Core.Villager.VillagerHandler;
import me.MiniDigger.Core.World.WorldHandler;

import me.MiniDigger.CraftCore.Achievement.CoreAchievementHandler;
import me.MiniDigger.CraftCore.AddOn.CoreAddOnHandler;
import me.MiniDigger.CraftCore.Bar.CoreBarHandler;
import me.MiniDigger.CraftCore.Block.CoreBuildHandler;
import me.MiniDigger.CraftCore.Booster.CoreBoosterHandler;
import me.MiniDigger.CraftCore.Chat.CoreChatHandler;
import me.MiniDigger.CraftCore.Command.CoreCommandHandler;
import me.MiniDigger.CraftCore.Game.CoreGameHandler;
import me.MiniDigger.CraftCore.Holo.CoreHoloHandler;
import me.MiniDigger.CraftCore.Item.CoreItemHandler;
import me.MiniDigger.CraftCore.Kit.CoreKitHandler;
import me.MiniDigger.CraftCore.Lang.CoreLangHandler;
import me.MiniDigger.CraftCore.License.CoreLicenseHandler;
import me.MiniDigger.CraftCore.Map.CoreMapHandler;
import me.MiniDigger.CraftCore.Mirror.CoreMirrorHandler;
import me.MiniDigger.CraftCore.Nametag.CoreNametagHandler;
import me.MiniDigger.CraftCore.Packet.CorePacketHandler;
import me.MiniDigger.CraftCore.Protocol.CoreProtocolHandler;
import me.MiniDigger.CraftCore.REST.CoreDashingHandler;
import me.MiniDigger.CraftCore.REST.CoreRESTHandler;
import me.MiniDigger.CraftCore.Regen.CoreRegenHandler;
import me.MiniDigger.CraftCore.SQL.CoreSQLHandler;
import me.MiniDigger.CraftCore.Scoreboard.CoreScoreboardHandler;
import me.MiniDigger.CraftCore.Server.CoreServerHandler;
import me.MiniDigger.CraftCore.Socket.CoreSocketHandler;
import me.MiniDigger.CraftCore.Socket.Client.CoreClientHandler;
import me.MiniDigger.CraftCore.Squad.CoreSquadHandler;
import me.MiniDigger.CraftCore.Stats.CoreStatsHandler;
import me.MiniDigger.CraftCore.Tip.CoreTipHandler;
import me.MiniDigger.CraftCore.Update.CoreUpdateHandler;
import me.MiniDigger.CraftCore.User.CoreUserHandler;
import me.MiniDigger.CraftCore.Util.CoreBaseUtil;
import me.MiniDigger.CraftCore.Util.CoreChatColorUtil;
import me.MiniDigger.CraftCore.Util.CoreCommonMethods;
import me.MiniDigger.CraftCore.Util.CoreDeZipUtil;
import me.MiniDigger.CraftCore.Util.CoreEntityUtil;
import me.MiniDigger.CraftCore.Util.CoreEnumUtil;
import me.MiniDigger.CraftCore.Util.CoreFileUtil;
import me.MiniDigger.CraftCore.Util.CoreItemUtil;
import me.MiniDigger.CraftCore.Util.CoreLocationUtil;
import me.MiniDigger.CraftCore.Util.CorePlayerUtil;
import me.MiniDigger.CraftCore.Util.CoreRandomUtil;
import me.MiniDigger.CraftCore.Util.CoreReflectionUtil;
import me.MiniDigger.CraftCore.Util.CoreShutdownUtil;
import me.MiniDigger.CraftCore.Util.CoreStringUtil;
import me.MiniDigger.CraftCore.Util.CoreTimeUtil;
import me.MiniDigger.CraftCore.Util.CoreZipUtil;
import me.MiniDigger.CraftCore.Villager.CoreVillagerHandler;
import me.MiniDigger.CraftCore.World.CoreWorldHandler;

public class CoreCore extends Core {
	
	private final Main	       instance;
	
	private ItemHandler	       itemHandler;
	private AchievementHandler	achievementHandler;
	private BuildHandler	   buildHandler;
	private BoosterHandler	   boosterHandler;
	private ChatHandler	       chatHandler;
	private CommandHandler	   commandHandler;
	private GameHandler	       gameHandler;
	private HoloHandler	       holoHandler;
	private MapHandler	       mapHandler;
	private NametagHandler	   nametagHandler;
	private PacketHandler	   packetHandler;
	private ProtocolHandler	   protocolHandler;
	private RegenHandler	   regenHandler;
	private ServerHandler	   serverHandler;
	private SocketHandler	   socketHandler;
	private ClientHandler	   clientHandler;
	private SquadHandler	   squadHandler;
	private SQLHandler	       sqlHandler;
	private StatsHandler	   statsHandler;
	private UpdateHandler	   updateHandler;
	private TipHandler	       tipHandler;
	private UserHandler	       userHandler;
	private WorldHandler	   worldHandler;
	private BarHandler	       barHandler;
	private RESTHandler	       restHandler;
	private AddOnHandler	   addOnHandler;
	private MirrorHandler	   mirrorHandler;
	private LicenseHandler	   licenseHandler;
	private VillagerHandler	   villagerHandler;
	private KitHandler	       kitHandler;
	private ScoreboardHandler	scoreboardHandler;
	private DashingHandler	   dashingHandler;
	private LangHandler	       langHandler;
	
	private ChatColorUtil	   chatColorUtil;
	private CommonMethods	   commonMethods;
	private DeZipUtil	       deZipUtil;
	private EntityUtil	       entityUtil;
	private FileUtil	       fileUtil;
	private LocationUtil	   locationUtil;
	private PlayerUtil	       playerUtil;
	private RandomUtil	       randomUtil;
	private ShutdownUtil	   shutdownUtil;
	private StringUtil	       stringUtil;
	private TimeUtil	       timeUtil;
	private ZipUtil	           zipUtil;
	private ReflectionUtil	   reflectionUtil;
	private BaseUtil	       baseUtil;
	private EnumUtil	       enumUtil;
	private ItemUtil	       itemUtil;
	
	public CoreCore(final Main instance) {
		super();
		this.instance = instance;
	}
	
	@Override
	public Main getInstance() {
		return instance;
	}
	
	@Override
	public AchievementHandler getAchievmentHandler() {
		if (achievementHandler == null) {
			achievementHandler = new CoreAchievementHandler();
		}
		return achievementHandler;
	}
	
	@Override
	public ItemHandler getItemHandler() {
		if (itemHandler == null) {
			itemHandler = new CoreItemHandler();
		}
		return itemHandler;
	}
	
	@Override
	public BuildHandler getBuildHandler() {
		if (buildHandler == null) {
			buildHandler = new CoreBuildHandler();
		}
		return buildHandler;
	}
	
	@Override
	public BoosterHandler getBoosterHandler() {
		if (boosterHandler == null) {
			boosterHandler = new CoreBoosterHandler();
		}
		return boosterHandler;
	}
	
	@Override
	public ChatHandler getChatHandler() {
		if (chatHandler == null) {
			chatHandler = new CoreChatHandler();
		}
		return chatHandler;
	}
	
	@Override
	public CommandHandler getCommandHandler() {
		if (commandHandler == null) {
			commandHandler = new CoreCommandHandler();
		}
		return commandHandler;
	}
	
	@Override
	public GameHandler getGameHandler() {
		if (gameHandler == null) {
			gameHandler = new CoreGameHandler();
		}
		return gameHandler;
	}
	
	@Override
	public HoloHandler getHoloHandler() {
		if (holoHandler == null) {
			holoHandler = new CoreHoloHandler();
		}
		return holoHandler;
	}
	
	@Override
	public MapHandler getMapHandler() {
		if (mapHandler == null) {
			mapHandler = new CoreMapHandler();
		}
		return mapHandler;
	}
	
	@Override
	public NametagHandler getNametagHandler() {
		if (nametagHandler == null) {
			nametagHandler = new CoreNametagHandler();
		}
		return nametagHandler;
	}
	
	@Override
	public PacketHandler getPacketHandler() {
		if (packetHandler == null) {
			packetHandler = new CorePacketHandler();
		}
		return packetHandler;
	}
	
	@Override
	public ProtocolHandler getProtocolHandler() {
		if (protocolHandler == null) {
			protocolHandler = new CoreProtocolHandler();
		}
		return protocolHandler;
	}
	
	@Override
	public RegenHandler getRegenHandler() {
		if (regenHandler == null) {
			regenHandler = new CoreRegenHandler();
		}
		return regenHandler;
	}
	
	@Override
	public ServerHandler getServerHandler() {
		if (serverHandler == null) {
			serverHandler = new CoreServerHandler();
		}
		return serverHandler;
	}
	
	@Override
	public SocketHandler getSocketHandler() {
		if (socketHandler == null) {
			socketHandler = new CoreSocketHandler();
		}
		return socketHandler;
	}
	
	@Override
	public ClientHandler getClientHandler() {
		if (clientHandler == null) {
			clientHandler = new CoreClientHandler();
		}
		return clientHandler;
	}
	
	@Override
	public SquadHandler getSquadHandler() {
		if (squadHandler == null) {
			squadHandler = new CoreSquadHandler();
		}
		return squadHandler;
	}
	
	@Override
	public SQLHandler getSqlHandler() {
		if (sqlHandler == null) {
			sqlHandler = new CoreSQLHandler();
		}
		return sqlHandler;
	}
	
	@Override
	public StatsHandler getStatsHandler() {
		if (statsHandler == null) {
			statsHandler = new CoreStatsHandler();
		}
		return statsHandler;
	}
	
	@Override
	public UpdateHandler getUpdateHandler() {
		if (updateHandler == null) {
			updateHandler = new CoreUpdateHandler();
		}
		return updateHandler;
	}
	
	@Override
	public TipHandler getTipHandler() {
		if (tipHandler == null) {
			tipHandler = new CoreTipHandler();
		}
		return tipHandler;
	}
	
	@Override
	public UserHandler getUserHandler() {
		if (userHandler == null) {
			userHandler = new CoreUserHandler();
		}
		return userHandler;
	}
	
	@Override
	public WorldHandler getWorldHandler() {
		if (worldHandler == null) {
			worldHandler = new CoreWorldHandler();
		}
		return worldHandler;
	}
	
	@Override
	public BarHandler getBarHandler() {
		if (barHandler == null) {
			barHandler = new CoreBarHandler();
		}
		return barHandler;
	}
	
	@Override
	public RESTHandler getRESTHandler() {
		if (restHandler == null) {
			restHandler = new CoreRESTHandler();
		}
		return restHandler;
	}
	
	@Override
	public AddOnHandler getAddOnHandler() {
		if (addOnHandler == null) {
			addOnHandler = new CoreAddOnHandler();
		}
		return addOnHandler;
	}
	
	@Override
	public MirrorHandler getMirrorHandler() {
		if (mirrorHandler == null) {
			mirrorHandler = new CoreMirrorHandler();
		}
		return mirrorHandler;
	}
	
	@Override
	public LicenseHandler getLicenseHandler() {
		if (licenseHandler == null) {
			licenseHandler = new CoreLicenseHandler();
		}
		return licenseHandler;
	}
	
	@Override
	public VillagerHandler getVillagerHandler() {
		if (villagerHandler == null) {
			villagerHandler = new CoreVillagerHandler();
		}
		return villagerHandler;
	}
	
	@Override
	public KitHandler getKitHandler() {
		if (kitHandler == null) {
			kitHandler = new CoreKitHandler();
		}
		return kitHandler;
	}
	
	@Override
	public ScoreboardHandler getScoreboardHandler() {
		if (scoreboardHandler == null) {
			scoreboardHandler = new CoreScoreboardHandler();
		}
		return scoreboardHandler;
	}
	
	@Override
	public DashingHandler getDashingHandler() {
		if (dashingHandler == null) {
			dashingHandler = new CoreDashingHandler();
		}
		return dashingHandler;
	}
	
	@Override
	public LangHandler getLangHandler() {
		if (langHandler == null) {
			langHandler = new CoreLangHandler();
		}
		return langHandler;
	}
	
	/***********/
	
	@Override
	public ChatColorUtil getChatColorUtil() {
		if (chatColorUtil == null) {
			chatColorUtil = new CoreChatColorUtil();
		}
		return chatColorUtil;
	}
	
	@Override
	public CommonMethods getCommonMethods() {
		if (commonMethods == null) {
			commonMethods = new CoreCommonMethods();
		}
		return commonMethods;
	}
	
	@Override
	public DeZipUtil getDeZipUtil() {
		if (deZipUtil == null) {
			deZipUtil = new CoreDeZipUtil();
		}
		return deZipUtil;
	}
	
	@Override
	public EntityUtil getEntityUtil() {
		if (entityUtil == null) {
			entityUtil = new CoreEntityUtil();
		}
		return entityUtil;
	}
	
	@Override
	public FileUtil getFileUtil() {
		if (fileUtil == null) {
			fileUtil = new CoreFileUtil();
		}
		return fileUtil;
	}
	
	@Override
	public LocationUtil getLocationUtil() {
		if (locationUtil == null) {
			locationUtil = new CoreLocationUtil();
		}
		return locationUtil;
	}
	
	@Override
	public PlayerUtil getPlayerUtil() {
		if (playerUtil == null) {
			playerUtil = new CorePlayerUtil();
		}
		return playerUtil;
	}
	
	@Override
	public RandomUtil getRandomUtil() {
		if (randomUtil == null) {
			randomUtil = new CoreRandomUtil();
		}
		return randomUtil;
	}
	
	@Override
	public ShutdownUtil getShutdownUtil() {
		if (shutdownUtil == null) {
			shutdownUtil = new CoreShutdownUtil();
		}
		return shutdownUtil;
	}
	
	@Override
	public StringUtil getStringUtil() {
		if (stringUtil == null) {
			stringUtil = new CoreStringUtil();
		}
		return stringUtil;
	}
	
	@Override
	public TimeUtil getTimeUtil() {
		if (timeUtil == null) {
			timeUtil = new CoreTimeUtil();
		}
		return timeUtil;
	}
	
	@Override
	public ZipUtil getZipUtil() {
		if (zipUtil == null) {
			zipUtil = new CoreZipUtil();
		}
		return zipUtil;
	}
	
	@Override
	public ReflectionUtil getReflectionUtil() {
		if (reflectionUtil == null) {
			reflectionUtil = new CoreReflectionUtil();
		}
		return reflectionUtil;
	}
	
	@Override
	public BaseUtil getBaseUtil() {
		if (baseUtil == null) {
			baseUtil = new CoreBaseUtil();
		}
		return baseUtil;
	}
	
	@Override
	public EnumUtil getEnumUtil() {
		if (enumUtil == null) {
			enumUtil = new CoreEnumUtil();
		}
		return enumUtil;
	}
	
	@Override
	public ItemUtil getItemUtil() {
		if (itemUtil == null) {
			itemUtil = new CoreItemUtil();
		}
		return itemUtil;
	}
}
