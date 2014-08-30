package me.MiniDigger.CraftCore.Packet.Packets;

import java.util.regex.Pattern;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Socket.Session;
import me.MiniDigger.Core.Socket.Client.Client;
import me.MiniDigger.CraftCore.Packet.CorePacket;
import me.MiniDigger.CraftCore.Socket.Client.CoreClient;

import org.java_websocket.WebSocket;

public class IdentificationPacket extends CorePacket {
	
	private String	clientName;
	private String	pass;
	
	@Override
	public String toString() {
		return getName() + "|" + getClientName() + "|" + getPass();
	}
	
	@Override
	public void fromString(String s) {
		String[] ss = s.split(Pattern.quote("|"));
		
		clientName = ss[1];
		pass = ss[2];
	}
	
	@Override
	public void handle() {
		Core.getCore().getInstance().info("[Client] Logged in!");
	}
	
	@Override
	public void handle(WebSocket con) {
		Core.getCore().getSocketHandler().reciveName(clientName, con.getRemoteSocketAddress());
		Session session = Core.getCore().getSocketHandler().getSession(clientName);
		Client client = new CoreClient(clientName);
		client.load();
		session.setClient(client);
		if (pass.equalsIgnoreCase(client.getPassword())) {
			Core.getCore().getInstance().info("[Server] User " + clientName + " logged in!");
			session.setIdentified(true);
		} else {
			Core.getCore().getInstance().error("[Server] User " + clientName + " used wrong password!");
			con.close(1000, "Wrong password!");
			session.setIdentified(false);
		}
	}
	
	@Override
	public String getName() {
		return "Identification";
	}
	
	public String getClientName() {
		return clientName;
	}
	
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	
	public String getPass() {
		return pass;
	}
	
	public void setPass(String pass) {
		this.pass = pass;
	}
}
