package me.MiniDigger.CraftCore.Tasks;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.Core.Tasks.Task;
import me.MiniDigger.Core.Tasks.TaskHandler;

public class CoreTaskHandler implements TaskHandler {
	
	private List<Task>	tasks	= new ArrayList<Task>();
	
	@Override
	public Task runTaskTimerAsynchronously(BukkitRunnable task, long delay, long period, Phase phase) {
		BukkitTask bt = task.runTaskTimerAsynchronously(Core.getCore().getInstance(), delay, period);
		Task ct = new CoreTask(phase, bt);
		tasks.add(ct);
		return ct;
	}
	
	@Override
	public Task runTaskTimer(BukkitRunnable task, long delay, long period, Phase phase) {
		BukkitTask bt = task.runTaskTimer(Core.getCore().getInstance(), delay, period);
		Task ct = new CoreTask(phase, bt);
		tasks.add(ct);
		return ct;
	}
	
	@Override
	public Task runTaskLaterAsynchronously(BukkitRunnable task, long delay, Phase phase) {
		return runTaskTimerAsynchronously(task, delay, -1L, phase);
	}
	
	@Override
	public Task runTaskLater(BukkitRunnable task, long delay, Phase phase) {
		return runTaskTimer(task, delay, -1L, phase);
	}
	
	@Override
	public Task runTaskAsynchronously(BukkitRunnable task, Phase phase) {
		return runTaskLaterAsynchronously(task, 0L, phase);
	}
	
	@Override
	public Task runTask(BukkitRunnable task, Phase phase) {
		return runTaskLater(task, 0L, phase);
	}
	
	@Override
	public List<Task> getTaskByPhase(Phase phase) {
		List<Task> t = new ArrayList<Task>();
		for (Task task : tasks) {
			if (task.getPhase().equals(phase)) {
				t.add(task);
			}
		}
		return t;
	}
	
	@Override
	public void cancelAll() {
		for (Task task : tasks) {
			task.getTask().cancel();
		}
	}
}
