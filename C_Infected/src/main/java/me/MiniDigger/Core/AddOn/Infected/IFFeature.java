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
package me.MiniDigger.Core.AddOn.Infected;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Chat.ChatChars;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.Core.Prefix.Prefix;
import me.MiniDigger.Core.Scoreboard.Scoreboard;
import me.MiniDigger.Core.User.User;

import me.MiniDigger.CraftCore.Event.Events.CoreUserDamageEvent;
import me.MiniDigger.CraftCore.Event.Events.CoreUserDeathEvent;
import me.MiniDigger.CraftCore.Feature.CoreFeature;
import me.MiniDigger.CraftCore.Item.CoreItemBuilder;
import me.MiniDigger.CraftCore.Scoreboard.CoreScoreboardLine;
import me.MiniDigger.CraftCore.Scoreboard.CoreScoreboardTitle;

import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;

public class IFFeature extends CoreFeature {

	private List<UUID>	infeced	= new ArrayList<>();
	private boolean		cure		= false;

	public IFFeature(final Phase phase) {
		super(phase);
	}

	@Override
	public FeatureType getType() {
		return FeatureType.IF;
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
		final int count = getPhase().getGame().getPlayers().size() / 6;
		System.out.println("count " + count);

		if (count != 0) {
			for (int i = 0; i < count; i++) {
				infeced.add(getPhase().getGame().getPlayers().get(Core.getCore().getRandomUtil().nextInt(getPhase().getGame().getPlayers().size())));
			}
		} else {
			infeced.add(getPhase().getGame().getPlayers().get(Core.getCore().getRandomUtil().nextInt(getPhase().getGame().getPlayers().size())));
			System.out.println("no zombies....");
		}

		for (final UUID id : infeced) {
			final User user = Core.getCore().getUserHandler().get(id);
			Prefix.IF.getPrefix().then("Du bist einer der ersten Zombies!").color(ChatColor.GOLD).send(user.getPlayer());
			Prefix.IF.getPrefix().then("Töte alle Überlebenden um sie zu infizieren!").color(ChatColor.GOLD).send(user.getPlayer());

			Core.getCore().getPlayerUtil().prepare(user.getPlayer());
			user.getPlayer().getInventory().setHelmet(new CoreItemBuilder(Material.SKULL_ITEM).data(2).durability(2).build());
			user.getPlayer().getInventory().addItem(new CoreItemBuilder(Material.WOOD_SWORD).build());
			user.getPlayer().updateInventory();

			final MobDisguise zombie = new MobDisguise(DisguiseType.ZOMBIE);
			DisguiseAPI.disguiseToAll(user.getPlayer(), zombie);
			DisguiseAPI.disguiseIgnorePlayers(user.getPlayer(), zombie, user.getPlayer());
		}

		startTimer();

		updateBoards();
	}

	public void startTimer() {
		Core.getCore().getTaskHandler().runTaskTimer(new BukkitRunnable() {

			private int time = 5 * 60;

			@Override
			public void run() {
				time--;

				if (time == 0) {
					cure();
					cancel();
				}

				for (final UUID id : getPhase().getGame().getPlayers()) {
					final User user = Core.getCore().getUserHandler().get(id);
					if (user == null) {
						continue;
					}

					user.getPlayer().setLevel(time);
					user.getPlayer().setExp((float) (0.0333333 * time) / 10);
				}
			}
		}, 20, 20, getPhase());
	}

	public void updateBoards() {
		final List<UUID> retry = new ArrayList<UUID>();

		for (final UUID id : getPhase().getGame().getPlayers()) {
			try {
				modBoard(Core.getCore().getScoreboardHandler().getBoard(id));
				Core.getCore().getScoreboardHandler().update(id);
			} catch (final Exception ex) {
				retry.add(id);
			}
		}
		Core.getCore().getTaskHandler().runTaskLater(new BukkitRunnable() {

			@Override
			public void run() {
				for (final UUID id : retry) {
					try {
						modBoard(Core.getCore().getScoreboardHandler().getBoard(id));
						Core.getCore().getScoreboardHandler().update(id);
					} catch (final Exception ex) {
						retry.add(id);
					}
				}
			}
		}, 10, getPhase());
	}

	private void modBoard(final Scoreboard board) {
		final int ifcount = infeced.size();
		final int playercount = getPhase().getGame().getPlayers().size();
		final int alive = playercount - ifcount;

		board.clear(DisplaySlot.SIDEBAR);
		board.setTitle(new CoreScoreboardTitle(ChatColor.GOLD + "Infected", DisplaySlot.SIDEBAR));

		board.addLine(new CoreScoreboardLine(3, ChatColor.GOLD + ChatChars.Misc.bullet + " Überlebende:", DisplaySlot.SIDEBAR));
		board.addLine(new CoreScoreboardLine(2, ChatColor.AQUA + " " + alive, DisplaySlot.SIDEBAR));
		board.addLine(new CoreScoreboardLine(1, ChatColor.GOLD + ChatChars.Misc.bullet + " Zombies:", DisplaySlot.SIDEBAR));
		board.addLine(new CoreScoreboardLine(0, ChatColor.AQUA + " " + ifcount + " ", DisplaySlot.SIDEBAR));
	}

	public void cure() {
		cure = true;
		getPhase().getGame().broadCastMessage(Prefix.IF.getPrefix().then("Es wurde ein Heilmittel gefunden!").color(ChatColor.GOLD));
		getPhase().getGame().broadCastMessage(Prefix.IF.getPrefix().then("Die Zombies können jetzt nicht mehr wiederaufstehen!").color(ChatColor.GOLD));
	}

	@Override
	public void end() {
		for (final UUID id : infeced) {
			final User user = Core.getCore().getUserHandler().get(id);
			if (DisguiseAPI.isDisguised(user.getPlayer())) {
				DisguiseAPI.undisguiseToAll(user.getPlayer());
			}
		}
	}

	@EventHandler
	public void onUserDeath(final CoreUserDeathEvent e) {
		if (e.getGame().getIdentifier() == getPhase().getGame().getIdentifier()) {
			e.setKeepDrops(true);
			if (infeced.contains(e.getUser().getUUID())) {
				if (cure) {
					getPhase().getGame().leave(e.getUser());
				}
			} else {
				infeced.add(e.getUser().getUUID());
				Prefix.IF.getPrefix().then("Du bist jetzt ein Zombie!").color(ChatColor.GOLD).send(e.getUser().getPlayer());
				Prefix.IF.getPrefix().then("Töte die letzten Überlebenen!").color(ChatColor.GOLD).send(e.getUser().getPlayer());
			}

			Core.getCore().getPlayerUtil().prepare(e.getUser().getPlayer());
			e.getUser().getPlayer().getInventory().setHelmet(new CoreItemBuilder(Material.SKULL_ITEM).data(2).durability(2).build());
			e.getUser().getPlayer().getInventory().addItem(new CoreItemBuilder(Material.WOOD_SWORD).build());
			e.getUser().getPlayer().updateInventory();

			final MobDisguise zombie = new MobDisguise(DisguiseType.ZOMBIE);
			DisguiseAPI.disguiseToAll(e.getUser().getPlayer(), zombie);
			DisguiseAPI.disguiseIgnorePlayers(e.getUser().getPlayer(), zombie, e.getUser().getPlayer());

			updateBoards();
			checkEnd();
		}
	}

	public void checkEnd() {
		// clear list
		final List<UUID> newInf = new ArrayList<>();
		for (final UUID id : infeced) {
			if (getPhase().getGame().getPlayers().contains(id)) {
				newInf.add(id);
			}
		}

		infeced = newInf;

		// count
		final int ifcount = infeced.size();
		final int playercount = getPhase().getGame().getPlayers().size();
		final int alive = playercount - ifcount;

		if (alive == 0) {
			getPhase().getGame().broadCastMessage(Prefix.IF.getPrefix().then("Alle Überlebenden wurden ausgelöscht!").color(ChatColor.GOLD));
			getPhase().getGame().broadCastMessage(Prefix.IF.getPrefix().then("Die Menschheit ist untergegangen!").color(ChatColor.GOLD));

			getPhase().getGame().end();
		}

		if (cure) {
			if (ifcount == 0) {
				getPhase().getGame().broadCastMessage(Prefix.IF.getPrefix().then("Der Zombievirus wurde vernichtet!").color(ChatColor.GOLD));
				getPhase().getGame().broadCastMessage(Prefix.IF.getPrefix().then("Die Menschheit ist gerettet!").color(ChatColor.GOLD));

				getPhase().getGame().end();
			}
		}
	}

	@EventHandler
	public void onPlayerDmg(final CoreUserDamageEvent e) {
		if (e.getGame().getIdentifier() == getPhase().getGame().getIdentifier()) {
			boolean damagerZombie = false;
			boolean damagedZombie = false;

			System.out.println("dmg");

			if (e.getDamager() != null) {
				if (infeced.contains(e.getDamager().getUUID())) {
					damagerZombie = true;
					System.out.println("damager is zombie");
				}
			} else {
				System.out.println("damager null");
				e.setDmg(0.0);
				e.setCancelled(true);
				return;
			}

			if (infeced.contains(e.getDamaged().getUUID())) {
				damagedZombie = true;
				System.out.println("damaged is zombie");
			}

			if (damagedZombie && damagerZombie) {
				System.out.println("zombie fight");
				e.setDmg(0.0);
				e.setCancelled(true);
			}

			if (!damagedZombie && !damagerZombie) {
				System.out.println("normal fight");
				e.setDmg(0.0);
				e.setCancelled(true);
			}
		}
	}
}
