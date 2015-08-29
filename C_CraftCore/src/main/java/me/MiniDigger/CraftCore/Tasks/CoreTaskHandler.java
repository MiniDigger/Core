/**
 * █████████████████████████████████████████████████████████████████████████████
 * ████████████████████████████████████
 * █░░░░░░░░░░░░░░█░░░░░░██░░░░░░█░░░░░░░░░░░░░░████░░░░░░░░░░░░░░█░░░░░░░░░░░░░
 * ░█░░░░░░░░░░░░░░░░███░░░░░░░░░░░░░░█
 * █░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░████░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░
 * ░█░░▄▀▄▀▄▀▄▀▄▀▄▀░░███░░▄▀▄▀▄▀▄▀▄▀░░█
 * █░░░░░░▄▀░░░░░░█░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░░░░░████░░▄▀░░░░░░░░░░█░░▄▀░░░░░░▄▀░
 * ░█░░▄▀░░░░░░░░▄▀░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░████████████░░▄▀░░█████████░░▄▀░░██░░▄▀░
 * ░█░░▄▀░░████░░▄▀░░███░░▄▀░░█████████
 * █████░░▄▀░░█████░░▄▀░░░░░░▄▀░░█░░▄▀░░░░░░░░░░████░░▄▀░░█████████░░▄▀░░██░░▄▀░
 * ░█░░▄▀░░░░░░░░▄▀░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░████░░▄▀░░█████████░░▄▀░░██░░▄▀░
 * ░█░░▄▀▄▀▄▀▄▀▄▀▄▀░░███░░▄▀▄▀▄▀▄▀▄▀░░█
 * █████░░▄▀░░█████░░▄▀░░░░░░▄▀░░█░░▄▀░░░░░░░░░░████░░▄▀░░█████████░░▄▀░░██░░▄▀░
 * ░█░░▄▀░░░░░░▄▀░░░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░████████████░░▄▀░░█████████░░▄▀░░██░░▄▀░
 * ░█░░▄▀░░██░░▄▀░░█████░░▄▀░░█████████
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░░░░░████░░▄▀░░░░░░░░░░█░░▄▀░░░░░░▄▀░
 * ░█░░▄▀░░██░░▄▀░░░░░░█░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░████░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░
 * ░█░░▄▀░░██░░▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█
 * █████░░░░░░█████░░░░░░██░░░░░░█░░░░░░░░░░░░░░████░░░░░░░░░░░░░░█░░░░░░░░░░░░░
 * ░█░░░░░░██░░░░░░░░░░█░░░░░░░░░░░░░░█
 * █████████████████████████████████████████████████████████████████████████████
 * ████████████████████████████████████
 *
 * Copyright © MiniDigger and others - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Martin Benndorf <admin@minidigger.me>, 2013-2015 and others
 */
package me.MiniDigger.CraftCore.Tasks;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.Core.Tasks.Task;
import me.MiniDigger.Core.Tasks.TaskHandler;

public class CoreTaskHandler implements TaskHandler {

	private final List<Task> tasks = new ArrayList<Task>();

	public CoreTaskHandler() {
		new BukkitRunnable() {

			@Override
			public void run() {
				cleanup();
			}
		}.runTaskTimer(Core.getCore().getInstance(), 10 * 20, 5 * 20);
	}

	private void cleanup() {
		final List<Task> dump = new ArrayList<Task>();
		for (final Task task : tasks) {
			if (!Bukkit.getScheduler().isCurrentlyRunning(task.getTask().getTaskId()) && !Bukkit.getScheduler().isQueued(task.getTask().getTaskId())) {
				dump.add(task);
			}
		}
		tasks.removeAll(dump);
	}

	@Override
	public Task runTaskTimerAsynchronously(final BukkitRunnable task, final long delay, final long period, final Phase phase) {
		final BukkitTask bt = task.runTaskTimerAsynchronously(Core.getCore().getInstance(), delay, period);
		final Task ct = new CoreTask(phase, bt);
		tasks.add(ct);
		return ct;
	}

	@Override
	public Task runTaskTimer(final BukkitRunnable task, final long delay, final long period, final Phase phase) {
		final BukkitTask bt = task.runTaskTimer(Core.getCore().getInstance(), delay, period);
		final Task ct = new CoreTask(phase, bt);
		tasks.add(ct);
		return ct;
	}

	@Override
	public Task runTaskLaterAsynchronously(final BukkitRunnable task, final long delay, final Phase phase) {
		return runTaskTimerAsynchronously(task, delay, -1L, phase);
	}

	@Override
	public Task runTaskLater(final BukkitRunnable task, final long delay, final Phase phase) {
		return runTaskTimer(task, delay, -1L, phase);
	}

	@Override
	public Task runTaskAsynchronously(final BukkitRunnable task, final Phase phase) {
		return runTaskLaterAsynchronously(task, 0L, phase);
	}

	@Override
	public Task runTask(final BukkitRunnable task, final Phase phase) {
		return runTaskLater(task, 0L, phase);
	}

	@Override
	public List<Task> getTaskByPhase(final Phase phase) {
		final List<Task> t = new ArrayList<Task>();
		for (final Task task : tasks) {
			if (task.getPhase().equals(phase)) {
				t.add(task);
			}
		}
		return t;
	}

	@Override
	public void cancelAll() {
		for (final Task task : tasks) {
			task.getTask().cancel();
		}
	}

	@Override
	public void cancel(final Task task) {
		tasks.remove(task);
		task.getTask().cancel();
	}
}
