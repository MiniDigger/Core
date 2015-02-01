package me.MiniDigger.Core.AddOn.BedWars;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.Core.Team.Team;
import me.MiniDigger.Core.User.User;

import me.MiniDigger.CraftCore.Feature.CoreFeature;
import me.MiniDigger.CraftCore.Feature.Features.TeamFeature;

public class TeamArmorFeature extends CoreFeature {
	
	public TeamArmorFeature(Phase phase) {
		super(phase);
	}
	
	@Override
	public FeatureType getType() {
		return FeatureType.TEAM_ARMOR;
	}
	
	@Override
	public List<FeatureType> getDependencies() {
		return new ArrayList<FeatureType>();
	}
	
	@Override
	public List<FeatureType> getSoftDependencies() {
		return new ArrayList<FeatureType>();
	}
	
	@Override
	public List<FeatureType> getIncompabilities() {
		return new ArrayList<FeatureType>();
	}
	
	@Override
	public void start() {
	}
	
	@Override
	public void end() {
	}
	
	@EventHandler
	public void onIvnCLick(InventoryClickEvent e) {
		if (e.getWhoClicked() instanceof Player) {
			User u = Core.getCore().getUserHandler().get(e.getWhoClicked().getUniqueId());
			if (getPhase().getGame().getPlayers().contains(u.getUUID())) {
				switch (e.getAction()) {
				case COLLECT_TO_CURSOR:
				case DROP_ALL_CURSOR:
				case DROP_ALL_SLOT:
				case DROP_ONE_CURSOR:
				case DROP_ONE_SLOT:
				case MOVE_TO_OTHER_INVENTORY:
					try {
						if (e.getCursor().getType() == Material.LEATHER_BOOTS || e.getCursor().getType() == Material.LEATHER_LEGGINGS
						        || e.getCursor().getType() == Material.LEATHER_HELMET) {
							if (e.getCursor().hasItemMeta() && e.getCursor().getItemMeta().hasLore() && e.getCursor().getItemMeta().getLore().contains("TeamArmor")) {
								LeatherArmorMeta m = (LeatherArmorMeta) e.getCursor().getItemMeta();
								m.setColor(Color.fromRGB(102, 76, 51));// Standard
								                                       // brown
								e.getCursor().setItemMeta(m);
							}
						}
					} catch (Exception ex) {}
					try {
						if (e.getCurrentItem().getType() == Material.LEATHER_BOOTS || e.getCurrentItem().getType() == Material.LEATHER_LEGGINGS
						        || e.getCurrentItem().getType() == Material.LEATHER_HELMET) {
							if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().hasLore()
							        && e.getCurrentItem().getItemMeta().getLore().contains("TeamArmor")) {
								LeatherArmorMeta m = (LeatherArmorMeta) e.getCurrentItem().getItemMeta();
								m.setColor(Color.fromRGB(102, 76, 51));// Standard
								                                       // brown
								e.getCursor().setItemMeta(m);
							}
						}
					} catch (Exception ex) {}
					break;
				case HOTBAR_MOVE_AND_READD:
				case HOTBAR_SWAP:
				case NOTHING:
				case PICKUP_ALL:
				case PICKUP_HALF:
				case PICKUP_ONE:
				case PICKUP_SOME:
				case PLACE_ALL:
				case PLACE_ONE:
				case PLACE_SOME:
				case SWAP_WITH_CURSOR:
				case CLONE_STACK:
				case UNKNOWN:
					TeamFeature tf = (TeamFeature) getPhase().getFeature(FeatureType.TEAM);
					Team t = tf.getTeam(u);
					Color c = Core.getCore().getChatColorUtil().toColor(t.getColor());
					
					try {
						if (e.getCursor().getType() == Material.LEATHER_BOOTS || e.getCursor().getType() == Material.LEATHER_LEGGINGS
						        || e.getCursor().getType() == Material.LEATHER_HELMET) {
							if (e.getCursor().hasItemMeta() && e.getCursor().getItemMeta().hasLore() && e.getCursor().getItemMeta().getLore().contains("TeamArmor")) {
								LeatherArmorMeta m = (LeatherArmorMeta) e.getCursor().getItemMeta();
								m.setColor(c);
								e.getCursor().setItemMeta(m);
							}
						}
					} catch (Exception ex) {}
					try {
						if (e.getCurrentItem().getType() == Material.LEATHER_BOOTS || e.getCurrentItem().getType() == Material.LEATHER_LEGGINGS
						        || e.getCurrentItem().getType() == Material.LEATHER_HELMET) {
							if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().hasLore()
							        && e.getCurrentItem().getItemMeta().getLore().contains("TeamArmor")) {
								LeatherArmorMeta m = (LeatherArmorMeta) e.getCurrentItem().getItemMeta();
								m.setColor(c);
								e.getCursor().setItemMeta(m);
							}
						}
					} catch (Exception ex) {}
					break;
				}
			}
		}
	}
	
}
