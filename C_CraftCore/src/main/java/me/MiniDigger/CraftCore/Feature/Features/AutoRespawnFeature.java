package me.MiniDigger.CraftCore.Feature.Features;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.server.v1_7_R3.EnumClientCommand;
import net.minecraft.server.v1_7_R3.PacketPlayInClientCommand;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_7_R3.entity.CraftPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.Plugin;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Event.Events.UserDeathEvent;
import me.MiniDigger.Core.Feature.Feature;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.CraftCore.CoreMain;
import me.MiniDigger.CraftCore.Feature.CoreFeature;

public class AutoRespawnFeature extends CoreFeature {
	
	public AutoRespawnFeature(Phase phase) {
		super(phase);
	}
	
	@Override
	public FeatureType getType() {
		return FeatureType.AUTORESPAWN;
	}
	
	@Override
	public List<FeatureType> getDependencies() {
		return new ArrayList<>();
	}
	
	@Override
	public List<FeatureType> getSoftDependencies() {
		return new ArrayList<>();
	}
	
	@Override
	public List<FeatureType> getIncompabilities() {
		return new ArrayList<>();
	}
	
	@Override
	public void start() {
		
	}
	
	@Override
	public void end() {
		
	}
	
	@EventHandler
	public void onDeath(UserDeathEvent e) {
		final PacketPlayInClientCommand packet = new PacketPlayInClientCommand(EnumClientCommand.PERFORM_RESPAWN);
		final CraftPlayer craftPlayer = (CraftPlayer) e.getUser().getPlayer();
		Bukkit.getScheduler().runTaskLater((CoreMain) Core.getCore().getInstance(), new Runnable() {
			
			@Override
			public void run() {
				craftPlayer.getHandle().playerConnection.a(packet);
			}
		}, 2);
	}
	
}
