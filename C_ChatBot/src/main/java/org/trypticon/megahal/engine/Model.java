package org.trypticon.megahal.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Class implementing the language model used by MegaHAL to record text it sees.
 *
 * @author Trejkaz
 */
public class Model {
	
	/**
	 * The order of this model.
	 */
	private final int order;
	
	/**
	 * The forward trie.
	 */
	private final TrieNode forwardTrie;
	
	/**
	 * The backward trie.
	 */
	private final TrieNode backwardTrie;
	
	/**
	 * Create a new model with the default order of 4.
	 */
	public Model() {
		this(4);
	}
	
	/**
	 * Create a new model with the given order. The order is the maximum number
	 * of symbols which can occur in a given context.
	 *
	 * @param order
	 *            the desired order.
	 */
	public Model(final int order) {
		this.order = order;
		forwardTrie = new TrieNode();
		backwardTrie = new TrieNode();
	}
	
	public int getOrder() {
		return order;
	}
	
	/**
	 * Finds the context associated with the end of the given list of symbols.
	 * I'm not sure if I've named this appropriately...
	 *
	 * @param trie
	 *            the trie to use.
	 * @param symbols
	 *            the list of symbols.
	 * @return the trie node representing the last symbol in the list, in the
	 *         context of the symbols before it.
	 */
	private TrieNode findLongestContext(final TrieNode trie, final List<Symbol> symbols) {
		int start = symbols.size() - order;
		if (start < 0) {
			start = 0;
		}
		TrieNode node = trie;
		for (int i = start; i < symbols.size(); i++) {
			node = node.getChild(symbols.get(i), false);
		}
		return node;
	}
	
	// ---------------- METHODS FOR TRAINING THE BRAIN ----------------
	
	/**
	 * Train the model with a list of symbols.
	 *
	 * @param symbols
	 *            the list of symbols.
	 */
	public void train(final List<Symbol> symbols) {
		// If there aren't enough symbols, then don't bother training from the
		// list.
		if (symbols.size() < order + 1) {
			return;
		}
		
		// Train in the forward direction.
		train(forwardTrie, symbols);
		
		// Train in the backward direction.
		Collections.reverse(symbols);
		train(backwardTrie, symbols);
		Collections.reverse(symbols);
	}
	
	/**
	 * For convenience, to avoid duplicating code. This is called from the
	 * public train(List) method, once for each of the forward and backward
	 * tries.
	 *
	 * @param trie
	 *            the trie to train.
	 * @param symbols
	 *            the list of symbols.
	 */
	private void train(final TrieNode trie, final List<Symbol> symbols) {
		
		// Iterate from the start to the end of the list.
		for (int i = 0; i < symbols.size(); i++) {
			TrieNode node = trie;
			
			// Iterate over the five symbols occurring at the current position.
			for (int j = i; j < i + order + 1 && j < symbols.size(); j++) {
				final Symbol symbol = symbols.get(j);
				final TrieNode child = node.getChild(symbol, true);
				child.usage++;
				node.count++;
				node = child;
			}
		}
	}
	
	// ---------------- METHODS TO GENERATE RESPONSES ----------------
	/**
	 * Generates a list of random symbols, forming a random response to the
	 * given lists of keywords.
	 *
	 * @param rng
	 *            a random number generator.
	 * @param userKeywords
	 *            the list of keywords from the user's input.
	 * @return the list of symbols generated.
	 */
	public List<Symbol> generateRandomSymbols(final Random rng, final List<Symbol> userKeywords) {
		// This is the list which will be returned.
		final ArrayList<Symbol> symbols = new ArrayList<Symbol>();
		
		// Generate in the forward direction.
		generateRandomSymbols(forwardTrie, symbols, rng, userKeywords, Symbol.END);
		
		// Reverse the list, and then generate in the backward direction.
		Collections.reverse(symbols);
		generateRandomSymbols(backwardTrie, symbols, rng, userKeywords, Symbol.START);
		
		// Reverse the list back again, and return it.
		Collections.reverse(symbols);
		return symbols;
	}
	
	/**
	 * Generates random symbols until a list terminator is found.
	 *
	 * @param trie
	 *            the trie to use to generate the symbol.
	 * @param symbols
	 *            the current list of symbols.
	 * @param rng
	 *            a random number generator.
	 * @param userKeywords
	 *            the list of keywords from the user's input.
	 * @param stopSymbol
	 *            the magic symbol which signals to stop adding to the list.
	 */
	protected void generateRandomSymbols(final TrieNode trie, final List<Symbol> symbols, final Random rng, final List<Symbol> userKeywords, final Symbol stopSymbol) {
		// System.out.print("(generateRandomSymbols)");
		Symbol symbol;
		do {
			symbol = generateRandomSymbol(trie, symbols, rng, userKeywords);
			symbols.add(symbol);
			// System.out.print("{"+symbol+"}");
		} while (!symbol.equals(stopSymbol));
	}
	
	/**
	 * Generates a random symbol for the next symbol in the list.
	 *
	 * @param trie
	 *            the trie to use to generate the symbol.
	 * @param symbols
	 *            the current list of symbols.
	 * @param rng
	 *            a random number generator.
	 * @param userKeywords
	 *            the list of keywords from the user's input.
	 * @return the randomly-determined next symbol in the list.
	 */
	protected Symbol generateRandomSymbol(final TrieNode trie, final List<Symbol> symbols, final Random rng, final List<Symbol> userKeywords) {
		// System.out.println("symbols=" + symbols);
		// Trivial case: If the list of generated symbols is empty, use a random
		// keyword, if one exists.
		if (symbols.isEmpty() && !userKeywords.isEmpty()) {
			return userKeywords.get(rng.nextInt(userKeywords.size()));
		}
		
		// Find the longest context available in the list of symbols.
		final TrieNode node = findLongestContext(trie, symbols);
		
		// Pick a random number, which will be used as a count-down.
		int total = rng.nextInt(node.count > 0 ? node.count : 1); // remember,
		                                                          // our 'count'
		                                                          // is the
		                                                          // total of
		                                                          // all
		                                                          // children's
		                                                          // 'usages'
		
		// Pick a random number, which will be used as an initial index into the
		// list of children.
		final List<?> childNodes = node.getChildList();
		int index = childNodes.size() > 0 ? rng.nextInt(childNodes.size()) : 0;
		
		TrieNode subnode;
		Symbol subnodeSymbol;

		// System.out.println("size: " + childNodes.size() + " index: " +
		// index);
		
		do {
			subnode = (TrieNode) childNodes.get(index);
			subnodeSymbol = subnode.symbol;
			
			// If the child is a keyword the user used, use it immediately.
			if (userKeywords.contains(subnodeSymbol)) {
				return subnodeSymbol;
			}
			
			// Otherwise, subtract the count of the child off the total, and
			// look at the
			// next word in the list. We'll actually loop around backwards
			// because it's faster
			// to compare with 0 than to compare with the size of the list every
			// iteration around
			// this loop.
			// System.out.print("{subnode.usage=" + subnode.usage + "}");
			total -= subnode.usage;
			index--;
			if (index < 0) {
				index = childNodes.size() - 1;
			}
		} while (total >= 0);
		
		// Once total hits zero, return the current child.
		return subnodeSymbol;
	}
	
	// ---------------- METHODS TO ANALYSE RESPONSES ----------------
	
	/**
	 * Calculates the amount of 'information' contained in the given candidate
	 * response.
	 *
	 * @param reply
	 *            the candidate reply.
	 * @param userKeywords
	 *            the list of keywords in the user's original line of text.
	 * @return a measure of the amount of information in the response.
	 */
	protected double calculateInformation(final List<Symbol> reply, final List<Symbol> userKeywords) {
		double info = 0.0;
		
		// Calculate information for the forward trie.
		info += calculateInformation(forwardTrie, reply, userKeywords);
		
		// Calculate information for the backward trie.
		Collections.reverse(reply);
		info += calculateInformation(backwardTrie, reply, userKeywords);
		Collections.reverse(reply);
		
		// Count the keywords in the reply, and scale the information value
		// depending on how
		// many keywords are present.
		int num = 0;
		for (final Symbol aReply : reply) {
			if (userKeywords.contains(aReply)) {
				num++;
			}
		}
		if (num > 4) {
			info /= Math.sqrt(num * 2 - 1);
			if (num > 8) {
				info /= (num * 2);
			}
		}
		return info;
	}
	
	/**
	 * Calculates the amount of 'information' contained in the given list of
	 * symbols, with respect to the given trie.
	 *
	 * @param trie
	 *            the trie.
	 * @param reply
	 *            the candidate reply.
	 * @param userKeywords
	 *            the list of keywords in the user's original line of text.
	 * @return a measure of the amount of information in the response.
	 */
	protected double calculateInformation(final TrieNode trie, final List<Symbol> reply, final List<Symbol> userKeywords) {
		double info = 0.0;
		
		for (int i = 0; i < reply.size(); i++) {
			final Symbol symbol = reply.get(i);
			if (userKeywords.contains(symbol)) {
				int lowerBound = i - order - 1;
				if (lowerBound < 0) {
					lowerBound = 0;
				}
				info += -Math.log(calculateAverageProbability(trie, reply.subList(lowerBound, i + 1)));
			}
		}
		return info;
	}
	
	/**
	 * Calculates the average probability of the last symbol in a list being in
	 * each context.
	 *
	 * @param trie
	 *            the trie.
	 * @param symbols
	 *            the list of symbols.
	 * @return the average of the probability counts for each context this
	 *         symbol is appearing in.
	 */
	protected double calculateAverageProbability(final TrieNode trie, final List<Symbol> symbols) {
		double total = 0.0;
		for (int i = 0; i < symbols.size(); i++) {
			TrieNode node = trie;
			TrieNode parent = null;
			for (int j = i; j < symbols.size(); j++) {
				parent = node;
				node = trie.getChild(symbols.get(i), false);
			}
			assert parent != null; // because there is at least one symbol.
			total += (double) node.usage / (double) parent.count;
		}
		return (total / symbols.size());
	}
	
}
