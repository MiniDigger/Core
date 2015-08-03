package me.MiniDigger.Core.AddOn.Ehrenhalle;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.event.DespawnReason;
import net.citizensnpcs.api.npc.NPC;

public class EHNPCs {
	private EHScanner s;
	private EHData d;

	private List<NPC> npcs = new ArrayList<>();
	private List<Location> usedLocs = new ArrayList<>();

	public EHNPCs(EHScanner s, EHData d) {
		this.s = s;
		this.d = d;
	}

	public void respawn(String newName) {
		for (NPC npc : npcs) {
			npc.despawn(DespawnReason.REMOVAL);
		}
		npcs = new ArrayList<>();
		spawn(newName);
	}

	public void spawn(String newName) {
		usedLocs = new ArrayList<>();
		for (String name : d.getDonations().keySet()) {
			NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, name);

			Location loc = null;
			for (Location l : s.getTower1()) {
				if (!usedLocs.contains(l)) {
					loc = l;
				}
			}
			if (loc == null) {
				for (Location l : s.getTower2()) {
					if (!usedLocs.contains(l)) {
						loc = l;
					}
				}
				if (loc == null) {
					for (Location l : s.getTower3()) {
						if (!usedLocs.contains(l)) {
							loc = l;
						}
					}
					if (loc == null) {
						for (Location l : s.getWall1()) {
							if (!usedLocs.contains(l)) {
								loc = l;
							}
						}
						if (loc == null) {
							for (Location l : s.getWall2()) {
								if (!usedLocs.contains(l)) {
									loc = l;
								}
							}
							if (loc == null) {
								for (Location l : s.getWall3()) {
									if (!usedLocs.contains(l)) {
										loc = l;
									}
								}
							}
						}
					}
				}
			}

			if (loc == null) {
				System.out.println("Well, we are fucked");
				return;
			}

			if (newName != null && name.equalsIgnoreCase(newName)) {
				effect(loc, name);
			}

			npcs.add(npc);
			usedLocs.add(loc);
			npc.spawn(loc);
		}
	}

	public void effect(Location loc, String name) {

	}
}
