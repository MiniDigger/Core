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
	
	public LogRecordPacket(final LogRecord record) {
		final Date date = new Date(record.getMillis());
		final DateFormat formatter = new SimpleDateFormat("HH:mm:ss:SSS");
		final String dateFormatted = formatter.format(date);
		
		final String[] s = record.getLoggerName().split(Pattern.quote("."));
		final String name = s[s.length - 1];
		
		message = "[" + dateFormatted + "] " + name + " [" + record.getLevel().getLocalizedName() + "] " + record.getMessage();
	}
	
	public LogRecordPacket(final String msg) {
		final Date date = new Date(System.currentTimeMillis());
		final DateFormat formatter = new SimpleDateFormat("HH:mm:ss:SSS");
		final String dateFormatted = formatter.format(date);
		message = "[" + dateFormatted + "] " + msg;
	}
	
	public LogRecordPacket() {
		
	}
	
	@Override
	public String toString() {
		return getName() + "|" + message;
	}
	
	@Override
	public void fromString(final String s) {
		message = s.replaceFirst(getName() + "|", "");
	}
	
	@Override
	public void handle() {
		
	}
	
	@Override
	public void handle(final WebSocket con) {
		Core.getCore().getPacketHandler().sendBroadcast(this);
	}
	
	@Override
	public String getName() {
		return "LogRecord";
	}
	
}
