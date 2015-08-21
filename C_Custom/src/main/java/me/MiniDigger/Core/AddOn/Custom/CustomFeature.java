package me.MiniDigger.Core.AddOn.Custom;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Command.CommandArgs;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.Core.Prefix.Prefix;
import me.MiniDigger.Core.User.User;
import me.MiniDigger.CraftCore.Feature.CoreFeature;
import me.MiniDigger.CraftCore.Item.CoreItemBuilder;

public class CustomFeature extends CoreFeature {
	private List<UUID> players = new ArrayList<>();

	public CustomFeature(Phase phase) {
		super(phase);
	}

	@Override
	public FeatureType getType() {
		return FeatureType.CUSTOM;
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
		for (UUID id : getPhase().getGame().getPlayers()) {
			User u = Core.getCore().getUserHandler().get(id);
			Player p = u.getPlayer();
			if (players.contains(id)) {
				p.getInventory().addItem(new CoreItemBuilder(Material.DIAMOND_SWORD).name("INSTAKILL")
						.enchantment(Enchantment.DAMAGE_ALL, 9000).build());
				p.getInventory().addItem(new CoreItemBuilder(Material.IRON_SWORD).name("NICHT INSTAKILL").build());
				p.getInventory().addItem(new ItemStack(Material.SNOW_BALL, 16), new ItemStack(Material.SNOW_BALL, 16),
						new ItemStack(Material.SNOW_BALL, 16), new ItemStack(Material.SNOW_BALL, 16));
				p.updateInventory();
			} else {
				p.getInventory().addItem(new CoreItemBuilder(Material.WOOD_SWORD).build());
				p.getInventory().addItem(new ItemStack(Material.SNOW_BALL, 16), new ItemStack(Material.SNOW_BALL, 16),
						new ItemStack(Material.SNOW_BALL, 16), new ItemStack(Material.SNOW_BALL, 16));
				p.updateInventory();
			}
		}
	}

	@Override
	public void end() {

	}

	public void addPlayer(Player p, CommandArgs args) {
		if (players.contains(p.getUniqueId())) {
			players.remove(p.getUniqueId());
			args.getUser().sendMessage(Prefix.API.getPrefix().then("Removed"));
		} else {
			players.add(p.getUniqueId());
			args.getUser().sendMessage(Prefix.API.getPrefix().then("Added"));
		}
	}
}