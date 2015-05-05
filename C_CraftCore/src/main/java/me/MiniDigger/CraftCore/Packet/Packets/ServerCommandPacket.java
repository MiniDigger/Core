package me.MiniDigger.CraftCore.Packet.Packets;

import java.util.regex.Pattern;

import org.java_websocket.WebSocket;

import me.MiniDigger.Core.Core;

import me.MiniDigger.CraftCore.Packet.CorePacket;

public class ServerCommandPacket extends CorePacket {
	
	private String	cmd;
	
	@Override
	public void fromString(String s) {
		cmd = s.split(Pattern.quote("|"))[0];
	}
	
	@Override
	public String toString() {
		return getName() + "|" + cmd;
	}
	
	@Override
	public void handle() {
		switch (cmd) {
		case "Shutdown":
			Core.getCore().getShutdownUtil().doShutdown();
			break;
		case "CloseClient":
			Core.getCore().getSocketHandler().stopClient();
			break;
		}
	}
	
	@Override
	public void handle(WebSocket con) {
		
	}
	
	@Override
	public String getName() {
		return "ServerCommand";
	}
	
	public void setCommand(String cmd) {
		this.cmd = cmd;
	}
	
}
