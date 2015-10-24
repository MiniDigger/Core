package com.earth2me.essentials;

import java.util.LinkedList;

public class EssentialsTimer implements Runnable {
	
	private transient long				lastPoll		= System.nanoTime();
	private final LinkedList<Double>	history			= new LinkedList<Double>();
	private final long					tickInterval	= 50;

	public EssentialsTimer() {
		history.add(20d);
	}

	@Override
	public void run() {
		final long startTime = System.nanoTime();
		long timeSpent = (startTime - lastPoll) / 1000;
		if (timeSpent == 0) {
			timeSpent = 1;
		}
		if (history.size() > 10) {
			history.remove();
		}
		double tps = tickInterval * 1000000.0 / timeSpent;
		if (tps <= 21) {
			history.add(tps);
		}
		lastPoll = startTime;
	}

	public double getAverageTPS() {
		double avg = 0;
		for (Double f : history) {
			if (f != null) {
				avg += f;
			}
		}
		return avg / history.size();
	}
}
