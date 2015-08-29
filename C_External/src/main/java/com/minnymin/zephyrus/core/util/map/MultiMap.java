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
package com.minnymin.zephyrus.core.util.map;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Zephyrus - MutliMap.java
 *
 * @author minnymin3
 *
 * @param <A>
 *            Key type
 * @param <B>
 *            First value type
 * @param <C>
 *            Second value type
 */
// TODO MultiMap -> API
public class MultiMap<A, B, C> implements Cloneable, Iterable<MultiEntry<A, B, C>> {

	private String				name	= null;
	private Map<A, Object[]>	map		= null;

	public MultiMap() {
		this.map = new HashMap<A, Object[]>();
	}

	public MultiMap(final String name) {
		this();
		this.name = (name != null ? name : "MultiMap");
	}

	public MultiMap(final int size) {
		this.map = new HashMap<A, Object[]>(size);
	}

	public MultiMap(final String name, final int size) {
		this(size);
		this.name = (name != null ? name : "MultiMap");
	}

	public void put(final A key, final B firstValue, final C secondValue) {
		map.put(key, new Object[] { firstValue, secondValue });
	}

	public Set<A> keySet() {
		return map.keySet();
	}

	@SuppressWarnings("unchecked")
	public Collection<B> firstValueSet() {
		final Set<B> set = new HashSet<B>();
		for (final Object[] obj : valueSet()) {
			set.add((B) obj[0]);
		}
		return set;
	}

	@SuppressWarnings("unchecked")
	public Collection<C> secondValueSet() {
		final Set<C> set = new HashSet<C>();
		for (final Object[] obj : valueSet()) {
			set.add((C) obj[1]);
		}
		return set;
	}

	public Collection<Object[]> valueSet() {
		return map.values();
	}

	public void remove(final A key) {
		map.remove(key);
	}

	public void remove(final int index) {
		remove(getKey(index));
	}

	public void clear() {
		map.clear();
	}

	public int size() {
		return map.size();
	}

	@Override
	public int hashCode() {
		return (map.hashCode() * map.values().hashCode() + map.keySet().hashCode() * valueSet().hashCode());
	}

	public int getIndex(final A key) {
		return new ArrayList<A>(this.keySet()).indexOf(key);
	}

	public boolean isEmpty() {
		return map.isEmpty();
	}

	public boolean containsKey(final A key) {
		return map.containsKey(key);
	}

	public A getKey(final int index) {
		return new ArrayList<A>(this.keySet()).get(index);
	}

	@SuppressWarnings("unchecked")
	public B getFirstValue(final A key) {
		return (B) map.get(key)[0];
	}

	@SuppressWarnings("unchecked")
	public C getSecondValue(final A key) {
		return (C) map.get(key)[1];
	}

	public MultiEntry<A, B, C> getEntry(final A key) {
		return new MultiEntry<A, B, C>(key, this);
	}

	public MultiEntry<A, B, C> getEntry(final int index) {
		return getEntry(getKey(index));
	}

	public void putFirst(final A key, final B value) {
		map.get(key)[0] = value;
	}

	public void putSecond(final A key, final C value) {
		map.get(key)[1] = value;
	}

	@Override
	public MultiMap<A, B, C> clone() {
		final MultiMap<A, B, C> clone = new MultiMap<A, B, C>(this.size());
		clone.setMap(getMap());
		clone.setName(getName());
		return clone;
	}

	@Override
	public Iterator<MultiEntry<A, B, C>> iterator() {
		return new MultiMapIterator<A, B, C>(this);
	}

	private String getName() {
		return this.name;
	}

	private Map<A, Object[]> getMap() {
		return this.map;
	}

	private void setName(final String name) {
		this.name = name;
	}

	@SuppressWarnings("unchecked")
	private void setMap(final Map<A, Object[]> c) {
		for (final A cc : c.keySet()) {
			this.put(cc, (B) c.get(cc)[0], (C) c.get(cc)[1]);
		}
	}

}
