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
package me.MiniDigger.CraftCore.Achievement;

import java.util.ArrayList;
import java.util.List;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Achievement.Achievment;
import me.MiniDigger.Core.Achievement.AchievmentData;
import me.MiniDigger.Core.Achievement.AchievmentLevel;
import me.MiniDigger.Core.Achievement.AchievmentType;
import me.MiniDigger.Core.Lang.LangKeyType;
import me.MiniDigger.Core.User.User;

public class CoreAchievement implements Achievment {

	private final LangKeyType			name;
	private final LangKeyType			msg;
	private final List<AchievmentLevel>	levels;
	private final List<AchievmentData>	data;
	private final AchievmentType		type;

	public CoreAchievement(final LangKeyType name, final LangKeyType msg, final AchievmentType type) {
		this.name = name;
		this.msg = msg;
		levels = new ArrayList<AchievmentLevel>();
		this.type = type;
		data = new ArrayList<AchievmentData>();

		levels.add(new CoreAchievmentLevel(0, 0, 0));
	}

	@Override
	public AchievmentData getData(final User user) {
		for (final AchievmentData d : data) {
			if (d.getUser().equals(user.getUUID())) {
				return d;
			}
		}

		final AchievmentData d = new CoreAchievmentData(type, user.getUUID(), 0, 0);
		data.add(d);
		return d;
	}

	@Override
	public void addLevel(final AchievmentLevel level) {
		levels.add(level);
	}

	@Override
	public LangKeyType getName() {
		return name;
	}

	@Override
	public LangKeyType getMsg() {
		return msg;
	}

	@Override
	public List<AchievmentLevel> getLevels() {
		return levels;
	}

	@Override
	public AchievmentType getType() {
		return type;
	}

	@Override
	public void checkLevel(final User u) {
		final AchievmentData d = getData(u);

		final AchievmentLevel current = getLevels().get(d.getLevel());
		AchievmentLevel next;
		try {
			next = getLevels().get(current.getId() + 1);
		}
		catch (final Exception ex) {
			return;
		}

		if (d.getData() >= next.getData()) {
			d.setLevel(next.getId());
			d.save();
			Core.getCore().getAchievmentHandler().award(u, getType(), d.getLevel());
		}
	}
}
