package me.MiniDigger.CraftCore.Nametag;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.UUID;

public class CoreRecieveNametagEvent extends Event {
	
	private static final HandlerList	handlers	= new HandlerList();
	
	private final Player	         player;
	private final Player	         namedPlayer;
	private String	                 tag;
	private UUID	                 UUID;
	private boolean	                 tagModified;
	private boolean	                 UUIDModified;
	
	public CoreRecieveNametagEvent(Player who, Player namedPlayer, String initialName, UUID uuid) {
		
		this.player = who;
		this.namedPlayer = namedPlayer;
		this.tag = initialName;
		this.tagModified = namedPlayer.getName().equals(initialName);
		this.UUID = uuid;
	}
	
	public boolean setTag(String tag) {
		this.tag = tag;
		this.tagModified = true;
		
		return tag.length() < 16;
	}
	
	public void setUUID(UUID uuid) {
		this.UUID = uuid;
		this.UUIDModified = true;
	}
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList() {
		return handlers;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public Player getNamedPlayer() {
		return namedPlayer;
	}
	
	public String getTag() {
		return tag;
	}
	
	public UUID getUUID() {
		return UUID;
	}
	
	public boolean isTagModified() {
		return tagModified;
	}
	
	public boolean isUUIDModified() {
		return UUIDModified;
	}
}
