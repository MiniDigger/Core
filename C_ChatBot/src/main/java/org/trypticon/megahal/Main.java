package org.trypticon.megahal;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.trypticon.megahal.engine.MegaHAL;

/**
 * Main class primarily for testing.
 *
 * @author Trejkaz
 */
public class Main {

	/**
	 * Main method.
	 *
	 * @param args
	 *            command-line arguments (ignored.)
	 */
	public static void main(final String[] args) {
		try {
			new Main().run();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	// private boolean typingDelay = false;
	// private boolean speech = false;

	/**
	 * Runs the main program.
	 *
	 * @throws IOException
	 *             primarily on errors initialising the system.
	 */
	public void run() throws IOException {
		final MegaHAL hal = new MegaHAL(new File("data"));

		final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		final PrintWriter out = new PrintWriter(System.out, true);

		while (true) {
			out.print("User> ");
			out.flush();

			final String line = in.readLine();
			if (line == null) {
				break;
			}

			String replyLine = hal.formulateReply(line);
			if (replyLine == null) {
				replyLine = "I don't know enough to respond to you yet.";
			}
			out.println("MegaHAL> " + replyLine);
		}
	}

}
