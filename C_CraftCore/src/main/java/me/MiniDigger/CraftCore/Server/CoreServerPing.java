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
	
	private String	address	       = "localhost";
	private int	   port	           = 25565;
	private String	name;
	
	private int	   timeout	       = 1500;
	
	private int	   pingVersion	   = -1;
	private int	   protocolVersion	= -1;
	private String	gameVersion;
	private String	motd;
	private int	   playersOnline	= -1;
	private int	   maxPlayers	   = -1;
	
	public CoreServerPing(String name) {
		this.name = name;
	}
	
	public CoreServerPing(String name, String address) {
		this(name);
		
		this.setAddress(address);
	}
	
	public CoreServerPing(String name, String address, int port) {
		this(name, address);
		
		this.setPort(port);
	}
	
	public CoreServerPing(String name, String address, int port, int timeout) {
		this(name, address, port);
		
		this.setTimeout(timeout);
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public void setPort(int port) {
		this.port = port;
	}
	
	public int getPort() {
		return this.port;
	}
	
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	
	public int getTimeout() {
		return this.timeout;
	}
	
	private void setPingVersion(int pingVersion) {
		this.pingVersion = pingVersion;
	}
	
	public int getPingVersion() {
		return this.pingVersion;
	}
	
	private void setProtocolVersion(int protocolVersion) {
		this.protocolVersion = protocolVersion;
	}
	
	public int getProtocolVersion() {
		return this.protocolVersion;
	}
	
	private void setGameVersion(String gameVersion) {
		this.gameVersion = gameVersion;
	}
	
	public String getGameVersion() {
		return this.gameVersion;
	}
	
	private void setMotd(String motd) {
		this.motd = motd;
	}
	
	public String getMotd() {
		return this.motd;
	}
	
	private void setPlayersOnline(int playersOnline) {
		this.playersOnline = playersOnline;
	}
	
	public int getPlayersOnline() {
		return this.playersOnline;
	}
	
	private void setMaxPlayers(int maxPlayers) {
		this.maxPlayers = maxPlayers;
	}
	
	public int getMaxPlayers() {
		return this.maxPlayers;
	}
	
	public boolean fetchData() {
		try {
			Socket socket = new Socket();
			OutputStream outputStream;
			DataOutputStream dataOutputStream;
			InputStream inputStream;
			InputStreamReader inputStreamReader;
			
			socket.setSoTimeout(this.timeout);
			
			socket.connect(new InetSocketAddress(this.getAddress(), this.getPort()), this.getTimeout());
			
			outputStream = socket.getOutputStream();
			dataOutputStream = new DataOutputStream(outputStream);
			
			inputStream = socket.getInputStream();
			inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-16BE"));
			
			dataOutputStream.write(new byte[] { (byte) 0xFE, (byte) 0x01 });
			
			int packetId = inputStream.read();
			
			if (packetId == -1) {
				close(socket, outputStream, dataOutputStream, inputStream, inputStreamReader);
				throw new IOException("Premature end of stream.");
			}
			
			if (packetId != 0xFF) {
				close(socket, outputStream, dataOutputStream, inputStream, inputStreamReader);
				throw new IOException("Invalid packet ID (" + packetId + ").");
			}
			
			int length = inputStreamReader.read();
			
			if (length == -1) {
				close(socket, outputStream, dataOutputStream, inputStream, inputStreamReader);
				throw new IOException("Premature end of stream.");
			}
			
			if (length == 0) {
				close(socket, outputStream, dataOutputStream, inputStream, inputStreamReader);
				throw new IOException("Invalid string length.");
			}
			
			char[] chars = new char[length];
			
			if (inputStreamReader.read(chars, 0, length) != length) {
				close(socket, outputStream, dataOutputStream, inputStream, inputStreamReader);
				throw new IOException("Premature end of stream.");
			}
			
			String string = new String(chars);
			
			if (string.startsWith("§")) {
				String[] data = string.split("\0");
				
				this.setPingVersion(Integer.parseInt(data[0].substring(1)));
				this.setProtocolVersion(Integer.parseInt(data[1]));
				this.setGameVersion(data[2]);
				this.setMotd(data[3]);
				this.setPlayersOnline(Integer.parseInt(data[4]));
				this.setMaxPlayers(Integer.parseInt(data[5]));
			} else {
				String[] data = string.split("§");
				
				this.setMotd(data[0]);
				this.setPlayersOnline(Integer.parseInt(data[1]));
				this.setMaxPlayers(Integer.parseInt(data[2]));
			}
			
			dataOutputStream.close();
			outputStream.close();
			
			inputStreamReader.close();
			inputStream.close();
			
			socket.close();
		} catch (SocketException exception) {
			return false;
		} catch (IOException exception) {
			return false;
		}
		
		return true;
	}
	
	private void close(Socket socket, OutputStream outputStream, DataOutputStream dataOutputStream, InputStream inputStream, InputStreamReader inputStreamReader) {
		try {
			dataOutputStream.close();
			inputStreamReader.close();
			outputStream.close();
			inputStream.close();
			socket.close();
		} catch (IOException e) {
			// e.printStackTrace();
		}
	}
	
	@Override
	public Server getServerInfo() {
		fetchData();
		Server server = new CoreServer();
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
