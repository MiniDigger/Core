/**
 * █████████████████████████████████████████████████████████████████████████████████████████████████████████████████
 * █░░░░░░░░░░░░░░█░░░░░░██░░░░░░█░░░░░░░░░░░░░░████░░░░░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░░░░░░░░░░░███░░░░░░░░░░░░░░█
 * █░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░████░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀▄▀░░███░░▄▀▄▀▄▀▄▀▄▀░░█
 * █░░░░░░▄▀░░░░░░█░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░░░░░████░░▄▀░░░░░░░░░░█░░▄▀░░░░░░▄▀░░█░░▄▀░░░░░░░░▄▀░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░████████████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░████░░▄▀░░███░░▄▀░░█████████
 * █████░░▄▀░░█████░░▄▀░░░░░░▄▀░░█░░▄▀░░░░░░░░░░████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░░░▄▀░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀▄▀░░███░░▄▀▄▀▄▀▄▀▄▀░░█
 * █████░░▄▀░░█████░░▄▀░░░░░░▄▀░░█░░▄▀░░░░░░░░░░████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░▄▀░░░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░████████████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░██░░▄▀░░█████░░▄▀░░█████████
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░░░░░████░░▄▀░░░░░░░░░░█░░▄▀░░░░░░▄▀░░█░░▄▀░░██░░▄▀░░░░░░█░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░████░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░██░░▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█
 * █████░░░░░░█████░░░░░░██░░░░░░█░░░░░░░░░░░░░░████░░░░░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░██░░░░░░░░░░█░░░░░░░░░░░░░░█
 * █████████████████████████████████████████████████████████████████████████████████████████████████████████████████
 */
package me.MiniDigger.CraftCore.Mirror;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;

import net.minecraft.util.com.google.gson.JsonObject;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.libs.com.google.gson.Gson;
import org.bukkit.craftbukkit.libs.jline.internal.InputStreamReader;
import org.bukkit.event.Event;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;

import me.MiniDigger.Core.Mirror.MirrorReviever;

public class CoreMirrorReciever implements MirrorReviever {
	
	@Override
	public void init(String ip, int port) {
		try {
			System.out.println("starting client");
			Socket client = new Socket(ip, port);
			new InputThread(client).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private class InputThread extends Thread {
		
		Socket	client;
		
		public InputThread(Socket client) {
			this.client = client;
		}
		
		@Override
		public void run() {
			try {
				BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
				
				String buffer;
				while ((buffer = in.readLine()) != null) {
					
					Event event = new Gson().fromJson(buffer, Event.class);
					System.out.println("received gson " + event.getEventName());
					Bukkit.getServer().getPluginManager().callEvent(event);
				}
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// while (true) {
			// try {
			// ObjectInputStream in = new
			// ObjectInputStream(client.getInputStream());
			//
			// Object obj = in.readObject();
			// if (obj instanceof PacketContainer) {
			// PacketContainer packet = (PacketContainer) obj;
			// System.out.println("received " + packet.getType().name());
			// ProtocolLibrary.getProtocolManager().broadcastServerPacket(packet);
			// } else if (obj instanceof Event) {
			// Event event = (Event) obj;
			// System.out.println("received " + event.getEventName());
			// Bukkit.getServer().getPluginManager().callEvent(event);
			// }
			// } catch (Exception ex) {
			// if (ex instanceof SocketException) {
			// return;
			// }
			// ex.printStackTrace();
			// }
			// }
		}
	}
}
