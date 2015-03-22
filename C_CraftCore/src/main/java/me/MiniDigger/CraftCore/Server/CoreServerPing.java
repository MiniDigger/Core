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
 * 
 * Copyright © MiniDigger and others - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Martin Benndorf <admin@minidigger.me>, 2013-2015 and others
 */
package me.MiniDigger.CraftCore.Server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.Charset;

import me.MiniDigger.Core.Game.GameType;
import me.MiniDigger.Core.Server.Server;
import me.MiniDigger.Core.Server.ServerPing;

public class CoreServerPing implements ServerPing {
	
	private String	     address	     = "localhost";
	private int	         port	         = 25565;
	private final String	name;
	
	private int	         timeout	     = 1500;
	
	private int	         pingVersion	 = -1;
	private int	         protocolVersion	= -1;
	private String	     gameVersion;
	private String	     motd;
	private int	         playersOnline	 = -1;
	private int	         maxPlayers	     = -1;
	
	public CoreServerPing(final String name) {
		this.name = name;
	}
	
	public CoreServerPing(final String name, final String address) {
		this(name);
		
		setAddress(address);
	}
	
	public CoreServerPing(final String name, final String address, final int port) {
		this(name, address);
		
		setPort(port);
	}
	
	public CoreServerPing(final String name, final String address, final int port, final int timeout) {
		this(name, address, port);
		
		setTimeout(timeout);
	}
	
	public void setAddress(final String address) {
		this.address = address;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setPort(final int port) {
		this.port = port;
	}
	
	public int getPort() {
		return port;
	}
	
	public void setTimeout(final int timeout) {
		this.timeout = timeout;
	}
	
	public int getTimeout() {
		return timeout;
	}
	
	private void setPingVersion(final int pingVersion) {
		this.pingVersion = pingVersion;
	}
	
	public int getPingVersion() {
		return pingVersion;
	}
	
	private void setProtocolVersion(final int protocolVersion) {
		this.protocolVersion = protocolVersion;
	}
	
	public int getProtocolVersion() {
		return protocolVersion;
	}
	
	private void setGameVersion(final String gameVersion) {
		this.gameVersion = gameVersion;
	}
	
	public String getGameVersion() {
		return gameVersion;
	}
	
	private void setMotd(final String motd) {
		this.motd = motd;
	}
	
	public String getMotd() {
		return motd;
	}
	
	private void setPlayersOnline(final int playersOnline) {
		this.playersOnline = playersOnline;
	}
	
	public int getPlayersOnline() {
		return playersOnline;
	}
	
	private void setMaxPlayers(final int maxPlayers) {
		this.maxPlayers = maxPlayers;
	}
	
	public int getMaxPlayers() {
		return maxPlayers;
	}
	
	public boolean fetchData() {
		try {
			final Socket socket = new Socket();
			OutputStream outputStream;
			DataOutputStream dataOutputStream;
			InputStream inputStream;
			InputStreamReader inputStreamReader;
			
			socket.setSoTimeout(timeout);
			
			socket.connect(new InetSocketAddress(getAddress(), getPort()), getTimeout());
			
			outputStream = socket.getOutputStream();
			dataOutputStream = new DataOutputStream(outputStream);
			
			inputStream = socket.getInputStream();
			inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-16BE"));
			
			dataOutputStream.write(new byte[] { (byte) 0xFE, (byte) 0x01 });
			
			final int packetId = inputStream.read();
			
			if (packetId == -1) {
				close(socket, outputStream, dataOutputStream, inputStream, inputStreamReader);
				throw new IOException("Premature end of stream.");
			}
			
			if (packetId != 0xFF) {
				close(socket, outputStream, dataOutputStream, inputStream, inputStreamReader);
				throw new IOException("Invalid packet ID (" + packetId + ").");
			}
			
			final int length = inputStreamReader.read();
			
			if (length == -1) {
				close(socket, outputStream, dataOutputStream, inputStream, inputStreamReader);
				throw new IOException("Premature end of stream.");
			}
			
			if (length == 0) {
				close(socket, outputStream, dataOutputStream, inputStream, inputStreamReader);
				throw new IOException("Invalid string length.");
			}
			
			final char[] chars = new char[length];
			
			if (inputStreamReader.read(chars, 0, length) != length) {
				close(socket, outputStream, dataOutputStream, inputStream, inputStreamReader);
				throw new IOException("Premature end of stream.");
			}
			
			final String string = new String(chars);
			
			if (string.startsWith("§")) {
				final String[] data = string.split("\0");
				
				setPingVersion(Integer.parseInt(data[0].substring(1)));
				setProtocolVersion(Integer.parseInt(data[1]));
				setGameVersion(data[2]);
				setMotd(data[3]);
				setPlayersOnline(Integer.parseInt(data[4]));
				setMaxPlayers(Integer.parseInt(data[5]));
			} else {
				final String[] data = string.split("§");
				
				setMotd(data[0]);
				setPlayersOnline(Integer.parseInt(data[1]));
				setMaxPlayers(Integer.parseInt(data[2]));
			}
			
			dataOutputStream.close();
			outputStream.close();
			
			inputStreamReader.close();
			inputStream.close();
			
			socket.close();
		} catch (final SocketException exception) {
			return false;
		} catch (final IOException exception) {
			return false;
		}
		
		return true;
	}
	
	private void close(final Socket socket, final OutputStream outputStream, final DataOutputStream dataOutputStream, final InputStream inputStream,
	        final InputStreamReader inputStreamReader) {
		try {
			dataOutputStream.close();
			inputStreamReader.close();
			outputStream.close();
			inputStream.close();
			socket.close();
		} catch (final IOException e) {
			// e.printStackTrace();
		}
	}
	
	@Override
	public Server getServerInfo() {
		fetchData();
		final Server server = new CoreServer();
		server.setJoin(true);
		server.setSpectate(true);
		server.setMaxPlayers(maxPlayers);
		server.setNumPlayers(playersOnline);
		server.setName(name);
		server.setOnline(true);
		server.setPhase(motd);
		server.setPrimaryGameType(GameType.NOTHING);
		return server;
	}
}
