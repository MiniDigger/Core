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
package me.MiniDigger.Core.AddOn.SuchenUndVerstecken;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Command.Command;
import me.MiniDigger.Core.Command.CommandArgs;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.Core.Prefix.Prefix;
import me.MiniDigger.Core.User.User;
import me.MiniDigger.CraftCore.Feature.CoreFeature;
import me.MiniDigger.CraftCore.Feature.Features.TeamSelectFeature;

public class SUVSelectFeature extends CoreFeature {

	private UUID	sucher1;
	private UUID	sucher2;

	public SUVSelectFeature(final Phase phase) {
		super(phase);
	}

	@Override
	public FeatureType getType() {
		return FeatureType.SUVSELECT;
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
		try {
			// final SUVFeature f = (SUVFeature) ((SUVGame)
			// getPhase().getGame()).getSuvPhase().getFeature(FeatureType.SUV);
			// f.setSucher1(sucher1);
			// f.setSucher2(sucher2);
			getPhase().getGame().setGameData("sucher1", sucher1 != null ? sucher1.toString() : "");
			getPhase().getGame().setGameData("sucher2", sucher2 != null ? sucher2.toString() : "");

			final TeamSelectFeature tf = (TeamSelectFeature) getPhase().getFeature(FeatureType.TEAM_SELECT);
			tf.addException(sucher1);
			tf.addException(sucher2);

			final User user1 = Core.getCore().getUserHandler().get(sucher1);
			final User user2 = Core.getCore().getUserHandler().get(sucher2);

			try {
				Core.getCore().getInstance().debug("user1: " + user1.getDisplayName());
			}
			catch (final Exception ex) {}
			try {
				Core.getCore().getInstance().debug("user2: " + user2.getDisplayName());
			}
			catch (final Exception ex) {}

		}
		catch (final Exception ex) {
			ex.printStackTrace();
			Core.getCore().getInstance().debug("Couldn't search for searchers....");
		}
	}

	@Command(name = "sucher", consol = false, min = 0, max = 1, usage = "[player]", permission = "sucher")
	public void sucher(final CommandArgs args) {
		User user = args.getUser();
		if (args.getArgs().length == 1) {
			user = Core.getCore().getUserHandler().getFromDisplayName(args.getArgs()[0]);
		}

		if (sucher1 == null) {
			sucher1 = user.getUUID();
		} else if (sucher2 == null) {
			sucher2 = user.getUUID();
		} else {
			final User sucher2 = Core.getCore().getUserHandler().get(this.sucher2);
			sucher2.sendMessage(Prefix.API.getPrefix().then("Du bist nun kein Sucher mehr!").color(ChatColor.RED));

			this.sucher2 = sucher1;
			sucher1 = user.getUUID();
		}

		user.sendMessage(Prefix.API.getPrefix().then("Du bist nun Sucher!").color(ChatColor.GREEN));
	}

}
