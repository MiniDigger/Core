package me.MiniDigger.CraftCore.Packet.Packets;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Server.Server;
import me.MiniDigger.CraftCore.Packet.CorePacket;

import org.java_websocket.WebSocket;

public class ServerPacket extends CorePacket {
	
	private Server	server;
	
	public ServerPacket(Server server) {
		this.server = server;
	}
	
	public ServerPacket() {
		
	}
	
	@Override
	public String toString() {
		return getName() + "|" + server.toString();
	}
	
	@Override
	public void fromString(String s) {
		s = s.replaceFirst(getName() + "|", "");
		this.server = Server.fromString(s);
	}
	
	@Override
	public void handle() {
		Core.getCore().getServerHandler().gotServerInfo(server);
	}
	
	@Override
	public void handle(WebSocket con) {
		Core.getCore().getPacketHandler().sendBroadcast(this);
	}
	
	@Override
	public String getName() {
		return "Server";
	}
	
}
