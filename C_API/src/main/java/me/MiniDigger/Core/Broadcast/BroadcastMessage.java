package me.MiniDigger.Core.Broadcast;

import java.util.List;

import org.json.simple.JSONObject;


public interface BroadcastMessage {

	JSONObject save();

	void load(JSONObject o);

	int getDelay();

	List<String> getMsg();

	int getInterval();

	String getCurrent();

	void setMsg(List<String> msg);

	void setDelay(int delay);

	void setInterval(int interval);

	void addMsg(String msg);
	
}
