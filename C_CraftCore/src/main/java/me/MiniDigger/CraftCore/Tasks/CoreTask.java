package me.MiniDigger.CraftCore.Tasks;

import org.bukkit.scheduler.BukkitTask;

import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.Core.Tasks.Task;

public class CoreTask implements Task{
	
	private Phase	   phase;
	private BukkitTask	task;
	
	public CoreTask(Phase phase, BukkitTask task) {
		this.phase = phase;
		this.task = task;
	}
	
	@Override
	public Phase getPhase() {
		return phase;
	}

	@Override
	public BukkitTask getTask() {
		return task;
	}
}
