package me.MiniDigger.CraftCore.Error;

import java.util.Enumeration;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import me.MiniDigger.Core.Error.ErrorHandler;

public class CoreErrorHandler implements ErrorHandler {
	
	private Handler	h;
	
	@Override
	public void init() {
		h = new Handler() {
			
			@Override
			public void flush() {
				System.out.println("FLUSH");
			}
			
			@Override
			public void close() throws SecurityException {
				System.out.println("CLOSE");
			}
			
			@Override
			public void publish(LogRecord record) {
				if (record.getLevel() == Level.SEVERE) {
					System.out.println("CATCH: " + record.getLevel().toString() + " " + record.getMessage());
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
