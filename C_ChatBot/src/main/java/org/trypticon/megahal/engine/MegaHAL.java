package org.trypticon.megahal.engine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import me.MiniDigger.Core.Core;

/**
 * Main class implementing the main MegaHAL engine. Provides methods to train
 * the brain, and to generate text responses from it.
 *
 * @author Trejkaz
 */
public class MegaHAL {
	
	// Fixed word sets.
	private final Map<Symbol, Symbol> swapWords; // A mapping of
	                                             // words which will
	                                             // be swapped for
	                                             // other words.

	// Hidden Markov first_attempt.Model
	private final Model model;
	
	// Parsing utilities
	private final Splitter splitter;
	
	// Random Number Generator
	private final Random rng = new Random();
	
	/**
	 * Constructs the engine, reading the configuration from the data directory.
	 *
	 * @throws IOException
	 *             if an error occurs reading the configuration.
	 */
	public MegaHAL(final File data) throws IOException {
		/*
		 * 0. Initialise. Add the special "<BEGIN>" and "<END>" symbols to the
		 * dictionary. Ex: 0:"<BEGIN>", 1:"<END>"
		 *
		 * NOTE: Currently debating the need for a dictionary.
		 */
		// dictionary.add("<BEGIN>");
		// dictionary.add("<END>");
		
		swapWords = Utils.readSymbolMapFromFile(data.getAbsolutePath() + "/megahal.swp");
		final Set<Symbol> banWords = Utils.readSymbolSetFromFile(data.getAbsolutePath() + "/megahal.ban");
		final Set<Symbol> auxWords = Utils.readSymbolSetFromFile(data.getAbsolutePath() + "/megahal.aux");
		// TODO: Implement first message to user (formulateGreeting()?)
		// Set<Symbol> greetWords =
		// Utils.readSymbolSetFromFile(data.getAbsolutePath() + "/megahal.grt");
		final SymbolFactory symbolFactory = new SymbolFactory(new SimpleKeywordChecker(banWords, auxWords));
		splitter = new WordNonwordSplitter(symbolFactory);
		
		model = new Model();
		
		final BufferedReader reader = new BufferedReader(new FileReader(data.getAbsolutePath() + "/megahal.trn"));
		String line;
		int trainCount = 0;
		while ((line = reader.readLine()) != null) {
			line = line.trim();
			if (line.length() == 0) {
				continue;
			}
			if (line.charAt(0) == '#') {
				continue;
			}
			trainOnly(line);
			trainCount++;
		}
		reader.close();
		Core.getCore().getInstance().debug("Trained with " + trainCount + " sentences.");
	}
	
	/**
	 * Trains on a single line of text.
	 *
	 * @param userText
	 *            the line of text.
	 */
	public void trainOnly(final String userText) {
		// Split the user's line into symbols.
		final List<Symbol> userWords = splitter.split(userText.toUpperCase());
		
		// Train the brain from the user's list of symbols.
		model.train(userWords);
	}
	
	/**
	 * Formulates a line back to the user, and also trains from the user's text.
	 *
	 * @param userText
	 *            the line of text.
	 * @return the reply.
	 */
	public String formulateReply(final String userText) {
		if (userText == null) {
			return null;
		}
		
		// Split the user's line into symbols.
		final List<Symbol> userWords = splitter.split(userText.toUpperCase());
		
		// Train the brain from the user's list of symbols.
		model.train(userWords);
		
		// Find keywords in the user's input.
		final List<Symbol> userKeywords = new ArrayList<Symbol>(userWords.size());
		for (Symbol s : userWords) {
			if (s.isKeyword()) {
				final Symbol swap = swapWords.get(s);
				if (swap != null) {
					s = swap;
				}
				userKeywords.add(s);
			}
		}
		
		// Generate candidate replies.
		int candidateCount = 0;
		double bestInfoContent = 0.0;
		List<Symbol> bestReply = null;
		final int timeToTake = 1000 * 5; // 5 seconds.
		final long t0 = System.currentTimeMillis();
		while (System.currentTimeMillis() - t0 < timeToTake) {
			System.out.print("Generating... ");
			final List<Symbol> candidateReply = model.generateRandomSymbols(rng, userKeywords);
			candidateCount++;
			Core.getCore().getInstance().debug("Candidate: " + candidateReply);
			
			final double infoContent = model.calculateInformation(candidateReply, userKeywords);
			Core.getCore().getInstance().debug("infoContent=" + infoContent);
			if (infoContent > bestInfoContent && !Utils.equals(candidateReply, userWords)) {
				bestInfoContent = infoContent;
				bestReply = candidateReply;
			}
		}
		Core.getCore().getInstance().debug("Candidates generated: " + candidateCount);
		Core.getCore().getInstance().debug("Best reply generated: " + bestReply);
		
		// Return the generated string, tacked back together.
		return (bestReply == null) ? null : splitter.join(bestReply);
	}
}
