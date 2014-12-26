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
package me.MiniDigger.CraftCore.REST;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.REST.DashingHandler;
import me.MiniDigger.Core.Server.Server;

public class CoreDashingHandler implements DashingHandler {
	
	private final Map<String, List<Date>>	words	= new HashMap<String, List<Date>>();
	private final List<Integer>	          players	= new ArrayList<Integer>();
	private int	                          playerX	= 1;
	
	@Override
	public void init() {
		// new BukkitRunnable() {
		//
		// @Override
		// public void run() {
		// mostWords();
		// }
		// }.runTaskTimer(Core.getCore().getInstance(), 10 * 1 * 20, 60 * 1 *
		// 20);
		//
		// new BukkitRunnable() {
		//
		// @Override
		// public void run() {
		// totalPlayers();
		// }
		// }.runTaskTimer(Core.getCore().getInstance(), 10 * 1 * 20, 10 * 1 *
		// 20);
	}
	
	@Override
	public void say(final String msg) {
		final String[] split = msg.split(" ");
		for (String word : split) {
			word = word.toLowerCase();
			if (words.containsKey(word)) {
				final List<Date> dates = new ArrayList<Date>();
				final Date now = new Date();
				for (final Date d : words.remove(word)) {
					if ((now.getTime() - d.getTime()) < (60 * 60 * 1000)) {
						dates.add(d);
					}
				}
				dates.add(now);
				words.put(word, dates);
			} else {
				final List<Date> dates = new ArrayList<Date>();
				dates.add(new Date());
				words.put(word, dates);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void mostWords() {
		final Map<String, Integer> count = new HashMap<String, Integer>();
		for (final String s : words.keySet()) {
			count.put(s, words.get(s).size());
		}
		
		final Set<Entry<String, Integer>> set = count.entrySet();
		final List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(set);
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			
			@Override
			public int compare(final Map.Entry<String, Integer> o1, final Map.Entry<String, Integer> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});
		
		int max = 10;
		if (list.size() < max) {
			max = list.size();
		}
		
		final JSONArray obj = new JSONArray();
		for (int i = 0; i < max; i++) {
			final Entry<String, Integer> entry = list.get(i);
			final JSONObject o = new JSONObject();
			o.put("label", entry.getKey());
			o.put("value", entry.getValue());
			obj.add(o);
		}
		
		final JSONObject o = new JSONObject();
		o.put("items", obj);
		
		go("mostwords", o);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void totalPlayers() {
		int users = 0;
		for (final Server server : Core.getCore().getServerHandler().getServers()) {
			users += server.getNumPlayers();
		}
		
		while (players.size() < 10) {
			players.add(0);
		}
		
		players.remove(0);
		players.add(users);
		
		int x = playerX;
		playerX++;
		
		final JSONArray array = new JSONArray();
		for (final int i : players) {
			final JSONObject obj = new JSONObject();
			obj.put("x", x);
			obj.put("y", i);
			array.add(obj);
			x++;
		}
		
		final JSONObject o = new JSONObject();
		o.put("points", array);
		
		go("player", o);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void go(final String widget, final JSONObject content) {
		final HttpClient client = HttpClients.createDefault();
		try {
			final HttpPost post = new HttpPost("http://minidigger.me:3030/widgets/" + widget);
			content.put("auth_token", "YOUR_AUTH_TOKEN");
			String j = content.toJSONString();
			j = j.replaceAll(Pattern.quote("\\"), "");
			j = new String(j.getBytes(), "UTF-8");
			
			final StringEntity se = new StringEntity(j);
			se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
			post.setEntity(se);
			final HttpResponse r = client.execute(post);
			if (r == null || r.getEntity() == null || r.getEntity().getContent() == null) {
				return;
			}
			final BufferedReader reader = new BufferedReader(new InputStreamReader(r.getEntity().getContent()));
			String buffer;
			String complete = "";
			while ((buffer = reader.readLine()) != null) {
				complete += " " + buffer;
			}
			System.out.println("error in dashing handler: " + complete);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
}
