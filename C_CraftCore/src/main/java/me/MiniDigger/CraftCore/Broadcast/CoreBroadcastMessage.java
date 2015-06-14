package me.MiniDigger.CraftCore.Broadcast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import me.MiniDigger.Core.Broadcast.BroadcastMessage;

public class CoreBroadcastMessage implements BroadcastMessage {
	
	private List<String>	msg	 = new ArrayList<String>();
	private int	         delay;
	private int	         interval;
	
	private int	         current	= 0;
	
	@Override
	public String getCurrent() {
		String m = "";
		try {
			m = msg.get(current);
			current++;
			return m;
		} catch (Exception ex) {}
		
		current = 0;
		return getCurrent();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject save() {
		JSONObject o = new JSONObject();
		
		JSONArray list = new JSONArray();
		list.addAll(msg);
		
		o.put("msg", list);
		o.put("delay", delay);
		o.put("interval", interval);
		
		return o;
	}
	
	@Override
	public void load(JSONObject o) {
		JSONArray list = (JSONArray) o.get("msg");
		msg = new ArrayList<String>();
		
		@SuppressWarnings("unchecked") Iterator<String> i = list.iterator();
		while (i.hasNext()) {
			msg.add(i.next());
		}
		
		delay = Integer.parseInt((String) o.get("delay"));
		interval = Integer.parseInt((String) o.get("interval"));
	}
	
	@Override
	public int getDelay() {
		return delay;
	}
	
	@Override
	public List<String> getMsg() {
		return msg;
	}
	
	@Override
	public int getInterval() {
		return interval;
	}
	
	@Override
	public void setMsg(List<String> msg) {
		this.msg = msg;
	}
	
	@Override
	public void setDelay(int delay) {
		this.delay = delay;
	}
	
	@Override
	public void setInterval(int interval) {
		this.interval = interval;
	}
	
	@Override
	public void addMsg(String msg) {
		this.msg.add(msg);
	}
}
