package me.MiniDigger.CraftCore.Packet.Packets;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.LogRecord;
import java.util.regex.Pattern;




import me.MiniDigger.Core.Core;
import me.MiniDigger.CraftCore.Packet.CorePacket;

import org.java_websocket.WebSocket;

public class LogRecordPacket extends CorePacket {
	
	private String	message;
	
	public LogRecordPacket(LogRecord record) {
		Date date = new Date(record.getMillis());
		DateFormat formatter = new SimpleDateFormat("HH:mm:ss:SSS");
		String dateFormatted = formatter.format(date);
		
		String[] s = record.getLoggerName().split(Pattern.quote("."));
		String name = s[s.length - 1];
		
		message = "[" + dateFormatted + "] " + name + " [" + record.getLevel().getLocalizedName() + "] " + record.getMessage();
	}
	
	public LogRecordPacket(String msg) {
		Date date = new Date(System.currentTimeMillis());
		DateFormat formatter = new SimpleDateFormat("HH:mm:ss:SSS");
		String dateFormatted = formatter.format(date);
		message = "[" + dateFormatted + "] " + msg;
	}
	
	public LogRecordPacket() {
		
	}
	
	@Override
	public String toString() {
		return getName() + "|" + message;
	}
	
	@Override
	public void fromString(String s) {
		this.message = s.replaceFirst(getName() + "|", "");
	}
	
	@Override
	public void handle() {
		
	}
	
	@Override
	public void handle(WebSocket con) {
		Core.getCore().getPacketHandler().sendBroadcast(this);
	}
	
	@Override
	public String getName() {
		return "LogRecord";
	}
	
}
