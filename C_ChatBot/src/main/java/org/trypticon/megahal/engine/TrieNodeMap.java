package org.trypticon.megahal.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Wrapper class for mapping String node names to TrieNode node objects.
 *
 * This was created to do away with the inefficiency of (a) searching for nodes
 * in a List, and (b) iterating through nodes in a Map.
 *
 * In addition, it should give us a little more control over what types are used
 * in the map.
 *
 * @author Trejkaz
 * @see TrieNode
 */
public class TrieNodeMap {
	
	// The two underlying data structures which we'll be using for this data
	// type.
	private final List<TrieNode>		list	= new ArrayList<TrieNode>();
	private final Map<Symbol, TrieNode>	map		= new HashMap<Symbol, TrieNode>();
	
	public synchronized TrieNode get(final Symbol symbol) {
		return map.get(symbol);
	}
	
	public synchronized void put(final Symbol symbol, final TrieNode node) {
		map.put(symbol, node);
		list.add(node);
	}
	
	public synchronized List<TrieNode> getList() {
		return Collections.unmodifiableList(list);
	}
	
	public synchronized Map<Symbol, TrieNode> getMap() {
		return Collections.unmodifiableMap(map);
	}
}
