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

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.simple.JSONObject;

import org.bukkit.scheduler.BukkitRunnable;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.REST.DashingHandler;

public class CoreDashingHandler implements DashingHandler {
	
	private Map<String, List<Date>>	words	= new HashMap<String, List<Date>>();
	
	@Override
	public void init() {
		new BukkitRunnable() {
			
			@Override
			public void run() {
				mostWords();
			}
		}.runTaskTimer(Core.getCore().getInstance(), 10 * 1 * 20, 60 * 1 * 20);
	}
	
	@Override
	public void say(String msg) {
		String[] split = msg.split(" ");
		for (String word : split) {
			word = word.toLowerCase();
			if (words.containsKey(word)) {
				List<Date> dates = new ArrayList<Date>();
				Date now = new Date();
				for (Date d : words.remove(word)) {
					if ((now.getTime() - d.getTime()) < (60 * 60 * 1000)) {
						dates.add(d);
					}
				}
				dates.add(now);
				words.put(word, dates);
			} else {
				List<Date> dates = new ArrayList<Date>();
				dates.add(new Date());
				words.put(word, dates);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void mostWords() {
		Map<String, Integer> count = new HashMap<String, Integer>();
		for (String s : words.keySet()) {
			count.put(s, words.get(s).size());
		}
		
		Set<Entry<String, Integer>> set = count.entrySet();
		List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(set);
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});
		
		int max = 10;
		if (list.size() < max) {
			max = list.size();
		}
		
		Map<String, String> content = new HashMap<String, String>();
		JSONObject obj = new JSONObject();
		for (int i = 0; i < max; i++) {
			Entry<String, Integer> entry = list.get(i);
			obj.put(entry.getKey(), entry.getValue() + "");
		}
		
		System.out.println("send");
		
		content.put("items", obj.toJSONString());
		go("mostwords", content);
	}
	
	@SuppressWarnings("unchecked")
	public void go(String widget, Map<String, String> content) {
		HttpClient client = HttpClients.createDefault();
		JSONObject json = new JSONObject();
		try {
			HttpPost post = new HttpPost("http://minidigger.me:3030/widgets/" + widget);
			json.put("auth_token", "YOUR_AUTH_TOKEN");
			for (String s : content.keySet()) {
				json.put(s, content.get(s));
			}
			System.out.println("json: " + json.toJSONString());
			StringEntity se = new StringEntity(json.toJSONString());
			se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
			post.setEntity(se);
			HttpResponse r = client.execute(post);
			if (r == null) {
				System.out.println("r == null");
				return;
			}
			if (r.getEntity() == null) {
				System.out.println("entity == null");
				return;
			}
			if (r.getEntity().getContent() == null) {
				System.out.println("content == null");
				return;
			}
			BufferedReader reader = new BufferedReader(new InputStreamReader(r.getEntity().getContent()));
			String buffer;
			String complete = "";
			while ((buffer = reader.readLine()) != null) {
				complete += " " + buffer;
			}
			System.out.println("complete respons: " + complete);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
