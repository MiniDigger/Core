package me.MiniDigger.Core.Tasks;

import org.bukkit.scheduler.BukkitTask;

import me.MiniDigger.Core.Phase.Phase;

public interface Task {
	
	/**
	 * @return the phase that task runs in
	 */
	Phase getPhase();
	
	/**
	 * @return the matched bukkittask
	 */
	BukkitTask getTask();
	
}
