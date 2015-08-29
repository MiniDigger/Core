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
package me.MiniDigger.CraftCore.Phase.Phases;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.WeatherType;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Command.Command;
import me.MiniDigger.Core.Command.CommandArgs;
import me.MiniDigger.Core.Command.Completer;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Game.Game;
import me.MiniDigger.Core.Lang.LangKeyType;
import me.MiniDigger.Core.Lang.MsgType;
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
import me.MiniDigger.CraftCore.Feature.Features.NoDropFeature;
import me.MiniDigger.CraftCore.Feature.Features.NoPickupFeature;
import me.MiniDigger.CraftCore.Feature.Features.PvPFeature;
import me.MiniDigger.CraftCore.Feature.Features.SpawnFeature;
import me.MiniDigger.CraftCore.Feature.Features.VoteFeature;
import me.MiniDigger.CraftCore.Lang.MSG;
import me.MiniDigger.CraftCore.Phase.CoreTimedPhase;

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
		addFeature(new FixedTimeFeature(this, 6000));
		addFeature(new FixedWeatherFeature(this, WeatherType.CLEAR));
		addFeature(new MapFeature(this, getGame().getGameData("Lobby"), false));
		addFeature(new SpawnFeature(this, false));
		addFeature(new PvPFeature(this, false));
		addFeature(new VoteFeature(this));
		addFeature(new NoPickupFeature(this));
		addFeature(new NoDropFeature(this));
	}

	@Override
	public void startPhase() {
		super.startPhase();
		getGame().broadCastMessage(LangKeyType.Game.VOTE_START1, MsgType.IMPORTANT);
		getGame().broadCastMessage(LangKeyType.Game.VOTE_START2, MsgType.IMPORTANT, getSecs() + "");

		getGame().setAllowJoin(true);
		getGame().setAllowSpectate(true);
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

		try {
			((MapFeature) getNextPhase().getFeature(FeatureType.MAP)).setMap(vote.getWinner());
		}
		catch (final Exception ex) {}

		Bukkit.getScheduler().runTaskLater(Core.getCore().getInstance(), new Runnable() {

			@Override
			public void run() {
				((MapFeature) getNextPhase().getFeature(FeatureType.MAP)).setMap(vote.getWinner());
			}
		}, 20);

		getGame().setAllowJoin(false);
		getGame().setAllowSpectate(true);
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
		}
		catch (final Exception ex) {
			MSG.msg(Prefix.VOTE, LangKeyType.Game.VOTE_UNKNOWN, MsgType.FAIL, args.getPlayer());
			return;
		}

		if (f.vote(args.getUser(), id)) {
			MSG.msg(Prefix.VOTE, LangKeyType.Game.VOTE_DONE, MsgType.SUCESS, args.getPlayer());
		} else {
			MSG.msg(Prefix.VOTE, LangKeyType.Game.VOTE_FAILED, MsgType.FAIL, args.getPlayer());
		}
	}

	@Completer(name = "vote")
	public List<String> voteC(final CommandArgs args) {
		final List<String> result = new ArrayList<>();

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
