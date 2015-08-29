/**
 * █████████████████████████████████████████████████████████████████████████████████████████████████████████████████
 * █░░░░░░░░░░░░░░█░░░░░░██░░░░░░█░░░░░░░░░░░░░░████░░░░░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░░░░░░░░░░░███░░░░░░░░░░░░░░█
 * █░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░████░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀▄▀░░███░░▄▀▄▀▄▀▄▀▄▀░░█
 * █░░░░░░▄▀░░░░░░█░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░░░░░████░░▄▀░░░░░░░░░░█░░▄▀░░░░░░▄▀░░█░░▄▀░░░░░░░░▄▀░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░████████████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░████░░▄▀░░███░░▄▀░░█████████
 * █████░░▄▀░░█████░░▄▀░░░░░░▄▀░░█░░▄▀░░░░░░░░░░████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░░░▄▀░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀▄▀░░███░░▄▀▄▀▄▀▄▀▄▀░░█
 * █████░░▄▀░░█████░░▄▀░░░░░░▄▀░░█░░▄▀░░░░░░░░░░████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░▄▀░░░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░████████████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░██░░▄▀░░█████░░▄▀░░█████████
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░░░░░████░░▄▀░░░░░░░░░░█░░▄▀░░░░░░▄▀░░█░░▄▀░░██░░▄▀░░░░░░█░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░████░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░██░░▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█
 * █████░░░░░░█████░░░░░░██░░░░░░█░░░░░░░░░░░░░░████░░░░░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░██░░░░░░░░░░█░░░░░░░░░░░░░░█
 * █████████████████████████████████████████████████████████████████████████████████████████████████████████████████
 * 
 * Copyright © MiniDigger and others - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Martin Benndorf <admin@minidigger.me>, 2013-2015 and others
 */
package org.jibble.jmegahal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class JMegaHal implements Serializable {

	private static final long	serialVersionUID	= 4495881780435935097L;
	// These are valid chars for words. Anything else is treated as punctuation.
	public static final String	WORD_CHARS			= "abcdefghijklmnopqrstuvwxyz" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789";
	public static final String	END_CHARS			= ".!?";

	/**
	 * Construct an instance of JMegaHal with an empty brain.
	 */
	public JMegaHal() {

	}

	/**
	 * Adds an entire documents to the 'brain'. Useful for feeding in stray
	 * theses, but be careful not to put too much in, or you may run out of
	 * memory!
	 */
	public void addDocument(final String uri) throws IOException {
		final BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(uri).openStream()));
		StringBuffer buffer = new StringBuffer();
		int ch = 0;
		while ((ch = reader.read()) != -1) {
			buffer.append((char) ch);
			if (END_CHARS.indexOf((char) ch) >= 0) {
				String sentence = buffer.toString();
				sentence = sentence.replace('\r', ' ');
				sentence = sentence.replace('\n', ' ');
				add(sentence);
				buffer = new StringBuffer();
			}
		}
		add(buffer.toString());
		reader.close();
	}

	/**
	 * Adds a new sentence to the 'brain'
	 */
	@SuppressWarnings("unchecked")
	public void add(String sentence) {
		sentence = sentence.trim();
		final ArrayList<String> parts = new ArrayList<String>();
		final char[] chars = sentence.toCharArray();
		int i = 0;
		boolean punctuation = false;
		StringBuffer buffer = new StringBuffer();
		while (i < chars.length) {
			final char ch = chars[i];
			if ((WORD_CHARS.indexOf(ch) >= 0) == punctuation) {
				punctuation = !punctuation;
				final String token = buffer.toString();
				if (token.length() > 0) {
					parts.add(token);
				}
				buffer = new StringBuffer();
				// i++;
				continue;
			}
			buffer.append(ch);
			i++;
		}
		final String lastToken = buffer.toString();
		if (lastToken.length() > 0) {
			parts.add(lastToken);
		}

		if (parts.size() >= 4) {
			for (i = 0; i < parts.size() - 3; i++) {
				// Core.getCore().getInstance().debug("\"" + parts.get(i) +
				// "\"");
				Quad quad = new Quad(parts.get(i), parts.get(i + 1), parts.get(i + 2), parts.get(i + 3));
				if (quads.containsKey(quad)) {
					quad = quads.get(quad);
				} else {
					quads.put(quad, quad);
				}

				if (i == 0) {
					quad.setCanStart(true);
				}
				// else if (i == parts.size() - 4) {
				if (i == parts.size() - 4) {
					quad.setCanEnd(true);
				}

				for (int n = 0; n < 4; n++) {
					final String token = parts.get(i + n);
					if (!words.containsKey(token)) {
						words.put(token, new HashSet<Object>(1));
					}
					final HashSet<Quad> set = (HashSet<Quad>) words.get(token);
					set.add(quad);
				}

				if (i > 0) {
					final String previousToken = parts.get(i - 1);
					if (!previous.containsKey(quad)) {
						previous.put(quad, new HashSet<Object>(1));
					}
					final HashSet<String> set = (HashSet<String>) previous.get(quad);
					set.add(previousToken);
				}

				if (i < parts.size() - 4) {
					final String nextToken = parts.get(i + 4);
					if (!next.containsKey(quad)) {
						next.put(quad, new HashSet<Object>(1));
					}
					final HashSet<String> set = (HashSet<String>) next.get(quad);
					set.add(nextToken);
				}

			}
		} else {
			// Didn't learn anything.
		}

	}

	/**
	 * Generate a random sentence from the brain.
	 */
	public String getSentence() {
		return getSentence(null);
	}

	/**
	 * Generate a sentence that includes (if possible) the specified word.
	 */
	public String getSentence(final String word) {
		final LinkedList<String> parts = new LinkedList<String>();

		Quad[] quads;
		if (words.containsKey(word)) {
			quads = ((HashSet<?>) words.get(word)).toArray(new Quad[0]);
		} else {
			quads = this.quads.keySet().toArray(new Quad[0]);
		}

		if (quads.length == 0) {
			return "";
		}

		final Quad middleQuad = quads[rand.nextInt(quads.length)];
		Quad quad = middleQuad;

		for (int i = 0; i < 4; i++) {
			parts.add(quad.getToken(i));
		}

		while (quad.canEnd() == false) {
			final String[] nextTokens = ((HashSet<?>) next.get(quad)).toArray(new String[0]);
			final String nextToken = nextTokens[rand.nextInt(nextTokens.length)];
			quad = this.quads.get(new Quad(quad.getToken(1), quad.getToken(2), quad.getToken(3), nextToken));
			parts.add(nextToken);
		}

		quad = middleQuad;
		while (quad.canStart() == false) {
			final String[] previousTokens = ((HashSet<?>) previous.get(quad)).toArray(new String[0]);
			final String previousToken = previousTokens[rand.nextInt(previousTokens.length)];
			quad = this.quads.get(new Quad(previousToken, quad.getToken(0), quad.getToken(1), quad.getToken(2)));
			parts.addFirst(previousToken);
		}

		final StringBuffer sentence = new StringBuffer();
		final Iterator<String> it = parts.iterator();
		while (it.hasNext()) {
			final String token = it.next();
			sentence.append(token);
		}

		return sentence.toString();
	}

	// This maps a single word to a HashSet of all the Quads it is in.
	private final HashMap<String, HashSet<?>> words = new HashMap<String, HashSet<?>>();

	// A self-referential HashMap of Quads.
	private final HashMap<Quad, Quad> quads = new HashMap<Quad, Quad>();

	// This maps a Quad onto a Set of Strings that may come next.
	private final HashMap<Quad, HashSet<?>> next = new HashMap<Quad, HashSet<?>>();

	// This maps a Quad onto a Set of Strings that may come before it.
	private final HashMap<Quad, HashSet<?>> previous = new HashMap<Quad, HashSet<?>>();

	private final Random rand = new Random();

}
