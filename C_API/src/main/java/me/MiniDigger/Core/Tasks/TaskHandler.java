package me.MiniDigger.Core.Tasks;

import java.util.List;

import org.bukkit.scheduler.BukkitRunnable;

import me.MiniDigger.Core.Phase.Phase;


public interface TaskHandler {

	Task runTaskTimerAsynchronously(BukkitRunnable task, long delay, long period, Phase phase);

	Task runTaskTimer(BukkitRunnable task, long delay, long period, Phase phase);

	Task runTaskLaterAsynchronously(BukkitRunnable task, long delay, Phase phase);

	Task runTaskLater(BukkitRunnable task, long delay, Phase phase);

	Task runTaskAsynchronously(BukkitRunnable task, Phase phase);

	Task runTask(BukkitRunnable task, Phase phase);

	List<Task> getTaskByPhase(Phase phase);

	void cancelAll();
	
}
