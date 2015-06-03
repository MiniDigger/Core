package me.MiniDigger.Core.AddOn.SuchenUndVerstecken;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Command.Command;
import me.MiniDigger.Core.Command.CommandArgs;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.Core.Prefix.Prefix;
import me.MiniDigger.Core.User.User;

import me.MiniDigger.CraftCore.Feature.CoreFeature;
import me.MiniDigger.CraftCore.Feature.Features.TeamSelectFeature;

public class SUVSelectFeature extends CoreFeature {
	
	private UUID	sucher1;
	private UUID	sucher2;
	
	public SUVSelectFeature(Phase phase) {
		super(phase);
	}
	
	@Override
	public FeatureType getType() {
		return FeatureType.SUVSELECT;
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
		try {
			SUVFeature f = (SUVFeature) getPhase().getNextPhase().getFeature(FeatureType.SUV);
			f.setSucher1(sucher1);
			f.setSucher2(sucher2);
			
			TeamSelectFeature tf = (TeamSelectFeature) getPhase().getFeature(FeatureType.TEAM_SELECT);
			tf.addException(sucher1);
			tf.addException(sucher2);
		} catch (Exception ex) {
			System.out.println("Couldn't search for searchers....");
		}
	}
	
	@Command(name = "sucher", consol = false, min = 0, max = 1, usage = "[player]", permission = "sucher")
	public void sucher(final CommandArgs args) {
		User user = args.getUser();
		if (args.getArgs().length == 1) {
			user = Core.getCore().getUserHandler().getFromDisplayName(args.getArgs()[0]);
		}
		
		if (sucher1 == null) {
			sucher1 = user.getUUID();
		} else if (sucher2 == null) {
			sucher2 = user.getUUID();
		} else {
			final User sucher2 = Core.getCore().getUserHandler().get(this.sucher2);
			sucher2.sendMessage(Prefix.API.getPrefix().then("Du bist nun kein Sucher mehr!").color(ChatColor.RED));
			
			this.sucher2 = this.sucher1;
			sucher1 = user.getUUID();
		}
		
		user.sendMessage(Prefix.API.getPrefix().then("Du bist nun Sucher!").color(ChatColor.GREEN));
	}
	
}
