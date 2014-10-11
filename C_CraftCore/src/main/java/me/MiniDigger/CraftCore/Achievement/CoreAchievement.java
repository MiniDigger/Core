/**
 *
 * ██████████████████████████████████████████████████████████████████████████████████████████████████████████████
 * █░░░░░░░░░░░░░░█░░░░░░██░░░░░░█░░░░░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░░░░░░░░░░░███░░░░░░░░░░░░░░█
 * █░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀▄▀░░███░░▄▀▄▀▄▀▄▀▄▀░░█
 * █░░░░░░▄▀░░░░░░█░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░░░░░█░░▄▀░░░░░░░░░░█░░▄▀░░░░░░▄▀░░█░░▄▀░░░░░░░░▄▀░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░█████████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░████░░▄▀░░███░░▄▀░░█████████
 * █████░░▄▀░░█████░░▄▀░░░░░░▄▀░░█░░▄▀░░░░░░░░░░█░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░░░▄▀░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀▄▀░░███░░▄▀▄▀▄▀▄▀▄▀░░█
 * █████░░▄▀░░█████░░▄▀░░░░░░▄▀░░█░░▄▀░░░░░░░░░░█░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░▄▀░░░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░█████████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░██░░▄▀░░█████░░▄▀░░█████████
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░░░░░█░░▄▀░░░░░░░░░░█░░▄▀░░░░░░▄▀░░█░░▄▀░░██░░▄▀░░░░░░█░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░██░░▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█
 * █████░░░░░░█████░░░░░░██░░░░░░█░░░░░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░██░░░░░░░░░░█░░░░░░░░░░░░░░█
 * ██████████████████████████████████████████████████████████████████████████████████████████████████████████████
 */

package me.MiniDigger.CraftCore.Achievement;

import java.util.ArrayList;
import java.util.List;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Achievement.Achievment;
import me.MiniDigger.Core.Achievement.AchievmentData;
import me.MiniDigger.Core.Achievement.AchievmentLevel;
import me.MiniDigger.Core.Achievement.AchievmentType;
import me.MiniDigger.Core.User.User;

public class CoreAchievement implements Achievment {
	
	private String	              name;
	private String	              msg;
	private List<AchievmentLevel>	levels;
	private List<AchievmentData>	data;
	private AchievmentType	      type;
	
	public CoreAchievement(String name, String msg, AchievmentType type) {
		this.name = name;
		this.msg = msg;
		this.levels = new ArrayList<AchievmentLevel>();
		this.type = type;
		this.data = new ArrayList<AchievmentData>();
		
		levels.add(new CoreAchievmentLevel(0, 0, 0));
	}
	
	@Override
	public AchievmentData getData(User user) {
		for (AchievmentData d : data) {
			if (d.getUser().equals(user.getUUID())) {
				return d;
			}
		}
		
		AchievmentData d = new CoreAchievmentData(name, user.getUUID(), 0, 0);
		data.add(d);
		return d;
	}
	
	@Override
	public void addLevel(AchievmentLevel level) {
		levels.add(level);
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public String getMsg() {
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
	public void checkLevel(User u) {
		AchievmentData d = getData(u);
		
		AchievmentLevel current = getLevels().get(d.getLevel());
		AchievmentLevel next;
		try {
			next = getLevels().get(current.getId() + 1);
		} catch (Exception ex) {
			return;
		}
		
		if (d.getData() >= next.getData()) {
			d.setLevel(next.getId());
			d.save();
			Core.getCore().getAchievmentHandler().award(u, getType(), d.getLevel());
		}
	}
}
