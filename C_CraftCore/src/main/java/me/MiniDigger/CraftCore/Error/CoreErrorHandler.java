package me.MiniDigger.CraftCore.Error;

import java.util.Enumeration;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import me.MiniDigger.Core.Error.ErrorHandler;
import me.MiniDigger.Core.Lang.LangKeyType;
import me.MiniDigger.Core.Lang.LogLevel;

import me.MiniDigger.CraftCore.Lang._;

public class CoreErrorHandler implements ErrorHandler {
	
	private Handler	h;
	
	@Override
	public void init() {
		h = new Handler() {
			
			@Override
			public void flush() {
			}
			
			@Override
			public void close() throws SecurityException {
			}
			
			@Override
			public void publish(LogRecord record) {
				if (record.getLevel() == Level.SEVERE) {
					_.log(LogLevel.ERROR, LangKeyType.Log.CATCHED, "3", record.getThrown().getMessage());
					_.stacktrace(LogLevel.DEBUG, record.getThrown());
				}
			}
			
		};
		
		Enumeration<String> e = LogManager.getLogManager().getLoggerNames();
		while (e.hasMoreElements()) {
			String s = e.nextElement();
			Logger l = LogManager.getLogManager().getLogger(s);
			l.addHandler(h);
		}
	}
}
