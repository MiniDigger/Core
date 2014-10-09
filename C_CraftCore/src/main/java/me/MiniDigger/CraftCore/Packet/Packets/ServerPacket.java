package me.MiniDigger.CraftCore.Packet.Packets;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Server.Server;
import me.MiniDigger.CraftCore.Packet.CorePacket;
import me.MiniDigger.CraftCore.Server.CoreServer;

import org.java_websocket.WebSocket;

public class ServerPacket extends CorePacket {
	
	private Server	server;
	
	public ServerPacket(final Server server) {
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
		if (s.charAt(0) == '|') {
			s = s.replaceFirst("|", "");
		}
		server = CoreServer.fromString(s);
	}
	
	@Override
	public void handle() {
		Core.getCore().getServerHandler().gotServerInfo(server);
	}
	
	@Override
	public void handle(final WebSocket con) {
		Core.getCore().getPacketHandler().sendBroadcast(this);
	}
	
	@Override
	public String getName() {
		return "Server";
	}
	
}
