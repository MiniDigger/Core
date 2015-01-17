package me.MiniDigger.CraftCore.Nametag;

import java.util.ArrayList;
import java.util.List;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;

public class CoreNametagTeams {
	
	class Team {
		
		String	name;
		String	prefix;
		String	suffix;
	}
	
	private List<Team>	teams	= new ArrayList<Team>();
	
	public static void create(String name, String prefix, String suffix, boolean nametag) {
		String nametagS = nametag ? "always" : "never";
		PacketContainer p = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.SCOREBOARD_TEAM);
		p.getStrings().write(0, name);
//		p.getBytes().write(0, (byte) 0);
		p.getIntegers().write(0, 0);
		p.getStrings().write(1, name);
		p.getStrings().write(2, prefix);
		p.getStrings().write(3, suffix);
//		p.getBytes().write(0, (byte) 1);
		p.getIntegers().write(1, 1);
		p.getStrings().write(4, nametagS);
//		p.getBytes().write(1, (byte) 1);
//		p.getStrings().write(5, "green");
		p.getIntegers().write(2, 1);
		p.getStringArrays().write(0, new String[] { name });
		
		ProtocolLibrary.getProtocolManager().broadcastServerPacket(p);
	}
}
