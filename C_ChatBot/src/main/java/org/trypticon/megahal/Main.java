package org.trypticon.megahal;

import org.trypticon.megahal.engine.MegaHAL;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

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
	public static void main(String[] args) {
		try {
			new Main().run();
		} catch (IOException e) {
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
		MegaHAL hal = new MegaHAL(new File("data"));
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out, true);
		
		while (true) {
			out.print("User> ");
			out.flush();
			
			String line = in.readLine();
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
