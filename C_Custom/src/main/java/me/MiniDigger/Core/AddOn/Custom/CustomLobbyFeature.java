package me.MiniDigger.Core.AddOn.Custom;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Command.Command;
import me.MiniDigger.Core.Command.CommandArgs;
import me.MiniDigger.Core.Command.Completer;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.Core.Prefix.Prefix;
import me.MiniDigger.CraftCore.Feature.CoreFeature;
import me.MiniDigger.CraftCore.Feature.Features.LivesFeature;
import me.MiniDigger.CraftCore.Feature.Features.MapFeature;

public class CustomLobbyFeature extends CoreFeature {

	public CustomLobbyFeature(Phase phase) {
		super(phase);
	}

	@Override
	public FeatureType getType() {
		return FeatureType.CUSTOM_LOBBY;
	}

	@Override
	public List<FeatureType> getDependencies() {
		return new ArrayList<FeatureType>();
	}

	@Override
	public List<FeatureType> getSoftDependencies() {
		return new ArrayList<FeatureType>();
	}

	@Override
	public List<FeatureType> getIncompabilities() {
		return new ArrayList<FeatureType>();
	}

	@Override
	public void start() {

	}

	@Override
	public void end() {

	}

	@Command(name = "setmap", permission = "setmap", min = 1, sync = true)
	public void setmap(final CommandArgs args) {
		if (Core.getCore().getMapHandler().getMapNames().contains(args.getArgs()[0])) {
			MapFeature m = (MapFeature) getPhase().getNextPhase().getFeature(FeatureType.MAP);
			m.setMap(args.getArgs()[0]);
			args.getUser().sendMessage(Prefix.API.getPrefix().then("Map SET!").color(ChatColor.GREEN));
		} else {
			args.getUser().sendMessage(Prefix.API.getPrefix().then("Unbekannte Map!").color(ChatColor.RED));
		}
	}

	@Completer(name = "setmap")
	public List<String> setmapC(final CommandArgs args) {
		if (args.getArgs().length == 1) {
			return Core.getCore().getCommonMethods().completer(Core.getCore().getMapHandler().getMapNames(),
					args.getArgs()[0]);
		} else {
			return new ArrayList<String>();
		}
	}

	@Command(name = "setplayer", permission = "setplayer", min = 1, sync = true)
	public void setplayer(final CommandArgs args) {
		Player p = Bukkit.getPlayer(args.getArgs()[0]);
		if (p != null) {
			CustomFeature m = (CustomFeature) getPhase().getNextPhase().getFeature(FeatureType.CUSTOM);
			m.addPlayer(p, args);
		} else {
			args.getUser().sendMessage(Prefix.API.getPrefix().then("Unbekannter Spieler!").color(ChatColor.RED));
		}
	}

	@Command(name = "setlives", permission = "setlives", min = 1, sync = true)
	public void setlives(final CommandArgs args) {
		try {
			int i = Integer.parseInt(args.getArgs()[0]);
			LivesFeature m = (LivesFeature) getPhase().getNextPhase().getFeature(FeatureType.LIVES);
			m.setLives(i);
			args.getUser().sendMessage(Prefix.API.getPrefix().then("Lives SET!").color(ChatColor.GREEN));
		} catch (Exception ex) {
			args.getUser().sendMessage(Prefix.API.getPrefix().then("Unbekannter Spieler!").color(ChatColor.RED));
		}
	}
}