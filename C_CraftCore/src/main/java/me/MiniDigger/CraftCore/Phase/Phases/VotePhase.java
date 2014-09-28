package me.MiniDigger.CraftCore.Phase.Phases;

import java.util.ArrayList;
import java.util.List;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Command.Command;
import me.MiniDigger.Core.Command.CommandArgs;
import me.MiniDigger.Core.Command.Completer;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Game.Game;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.Core.Prefix.Prefix;
import me.MiniDigger.Core.Util.EntityUtil.Type;
import me.MiniDigger.CraftCore.Feature.Features.ClearInvFeature;
import me.MiniDigger.CraftCore.Feature.Features.FixedFoodFeature;
import me.MiniDigger.CraftCore.Feature.Features.FixedHealthFeature;
import me.MiniDigger.CraftCore.Feature.Features.FixedTimeFeature;
import me.MiniDigger.CraftCore.Feature.Features.FixedWeatherFeature;
import me.MiniDigger.CraftCore.Feature.Features.MapFeature;
import me.MiniDigger.CraftCore.Feature.Features.MobFeature;
import me.MiniDigger.CraftCore.Feature.Features.PvPFeature;
import me.MiniDigger.CraftCore.Feature.Features.SpawnFeature;
import me.MiniDigger.CraftCore.Feature.Features.VoteFeature;
import me.MiniDigger.CraftCore.Phase.CoreTimedPhase;

import org.bukkit.ChatColor;
import org.bukkit.WeatherType;

public class VotePhase extends CoreTimedPhase {
	
	public VotePhase(final Game game, final Phase next, final int secs) {
		super(game, next, secs);
		init();
	}
	
	@Override
	public String getName() {
		return "Vote";
	}
	
	@Override
	public String getBarMessage() {
		return ChatColor.RED + "" + ChatColor.MAGIC + "||" + ChatColor.RESET + "" + ChatColor.GOLD + getName() + ChatColor.RED + "" + ChatColor.MAGIC + "||";
	}
	
	@Override
	public boolean displayBar() {
		return true;
	}
	
	@Override
	public boolean displayLevel() {
		return true;
	}
	
	@Override
	public void init() {
		addFeature(new FixedHealthFeature(this));
		addFeature(new ClearInvFeature(this, false));
		addFeature(new FixedFoodFeature(this));
		addFeature(new MobFeature(this, Core.getCore().getEntityUtil().getAll(Type.OTHER)));
		addFeature(new FixedTimeFeature(this, 12000));
		addFeature(new FixedWeatherFeature(this, WeatherType.CLEAR));
		addFeature(new MapFeature(this, getGame().getGameData("Lobby")));
		addFeature(new SpawnFeature(this, false));
		addFeature(new PvPFeature(this, false));
		addFeature(new VoteFeature(this));
	}
	
	@Override
	public void startPhase() {
		super.startPhase();
		getGame().broadCastMessage(Prefix.getByGameType(getGame().getType()).getPrefix().then("Die Voting Phase hat begonnen!").color(ChatColor.GOLD));
		
		getGame().broadCastMessage(
		        Prefix.getByGameType(getGame().getType()).getPrefix().then("Du hast ").color(ChatColor.GOLD).then(getSecs() + "").color(ChatColor.YELLOW)
		                .then(" Sekunden Zeit um eine Map auszuwählen.").color(ChatColor.GOLD));
		((VoteFeature) getFeature(FeatureType.VOTE)).sendVoteMessages();
	}
	
	@Override
	public void endPhase() {
		final VoteFeature vote = ((VoteFeature) getFeature(FeatureType.VOTE));
		vote.announceWinner();
		
		final String map = vote.getWinner();
		getGame().setGameData("VoteWinner", map);
		Core.getCore().getWorldHandler().copyWorld(map);
		Core.getCore().getWorldHandler().loadWorld(map);
		((MapFeature) getFeature(FeatureType.MAP)).setMap(vote.getWinner());
		
		super.endPhase();
	}
	
	@Command(name = "vote", description = "Votet für eine Map", permission = "vote", usage = "vote [id]", consol = false, noConsol = "Nur ein Spieler kann voten!", min = 0, max = 1)
	public void vote(final CommandArgs args) {
		final VoteFeature f = (VoteFeature) getFeature(FeatureType.VOTE);
		
		if (args.getArgs().length == 0) {
			f.sendVoteMessage(args.getUser());
			return;
		}
		
		int id;
		try {
			id = Integer.parseInt(args.getArgs()[0]);
		} catch (final Exception ex) {
			args.getUser().sendMessage(Prefix.VOTE.getPrefix().then("Unbekannte Map!").color(ChatColor.RED));
			return;
		}
		
		if (f.vote(args.getUser(), id)) {
			args.getUser().sendMessage(Prefix.VOTE.getPrefix().then("Vote abgeschickt!").color(ChatColor.GREEN));
		} else {
			args.getUser().sendMessage(Prefix.VOTE.getPrefix().then("Vote fehlgeschlagen!").color(ChatColor.RED));
			args.getUser().sendMessage(Prefix.VOTE.getPrefix().then("Hast du bereits gevoted?!").color(ChatColor.RED));
		}
	}
	
	@Completer(name = "vote")
	public List<String> voteC(CommandArgs args) {
		List<String> result = new ArrayList<>();
		
		final VoteFeature f = (VoteFeature) getFeature(FeatureType.VOTE);
		
		switch (f.getMapCount()) {
		case 3:
			result.add("3");
		case 2:
			result.add("2");
		case 1:
			result.add("1");
		}
		
		result.add("");
		
		return result;
	}
	
}
