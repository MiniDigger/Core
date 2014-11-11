package me.MiniDigger.CraftCore.Entity;

import java.util.List;

import me.MiniDigger.Core.Core;
import net.minecraft.server.v1_7_R4.PathfinderGoalSelector;

public class CoreEntity {
	
	public void clearGoals(PathfinderGoalSelector goalSelector, PathfinderGoalSelector targetSelector) {
		List<?> goalB = (List<?>) Core.getCore().getReflectionUtil().getPrivateField("b", PathfinderGoalSelector.class, goalSelector);
		goalB.clear();
		List<?> goalC = (List<?>) Core.getCore().getReflectionUtil().getPrivateField("c", PathfinderGoalSelector.class, goalSelector);
		goalC.clear();
		List<?> targetB = (List<?>) Core.getCore().getReflectionUtil().getPrivateField("b", PathfinderGoalSelector.class, targetSelector);
		targetB.clear();
		List<?> targetC = (List<?>) Core.getCore().getReflectionUtil().getPrivateField("c", PathfinderGoalSelector.class, targetSelector);
		targetC.clear();
	}
}
