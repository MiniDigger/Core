package me.MiniDigger.Core.AddOn.Ehrenhalle;

import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.WeatherType;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Command.Command;
import me.MiniDigger.Core.Command.CommandArgs;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Game.Game;
import me.MiniDigger.Core.Prefix.Prefix;
import me.MiniDigger.Core.Util.EntityUtil.Type;

import me.MiniDigger.CraftCore.Feature.Features.FixedFoodFeature;
import me.MiniDigger.CraftCore.Feature.Features.FixedHealthFeature;
import me.MiniDigger.CraftCore.Feature.Features.FixedTimeFeature;
import me.MiniDigger.CraftCore.Feature.Features.FixedWeatherFeature;
import me.MiniDigger.CraftCore.Feature.Features.MapFeature;
import me.MiniDigger.CraftCore.Feature.Features.MobFeature;
import me.MiniDigger.CraftCore.Feature.Features.NoDropFeature;
import me.MiniDigger.CraftCore.Feature.Features.NoPickupFeature;
import me.MiniDigger.CraftCore.Feature.Features.PvPFeature;
import me.MiniDigger.CraftCore.Feature.Features.SpawnFeature;
import me.MiniDigger.CraftCore.Phase.CorePhase;

public class EHPhase extends CorePhase {

	private EHScanner s;
	private EHData d;
	private EHNPCs n;

	public EHPhase(final Game game) {
		super(game, null);
	}

	@Override
	public String getName() {
		return "Hub";
	}

	@Override
	public boolean displayBar() {
		return true;
	}

	@Override
	public boolean displayLevel() {
		return false;
	}

	@Override
	public void init() {
		addFeature(new FixedHealthFeature(this));
		addFeature(new EHFeature(this));
		addFeature(new FixedFoodFeature(this));
		addFeature(new MobFeature(this, Core.getCore().getEntityUtil().getAll(Type.OTHER)));
		addFeature(new FixedTimeFeature(this, 6000));
		addFeature(new FixedWeatherFeature(this, WeatherType.CLEAR));
		addFeature(new MapFeature(this, "Ehrenhalle", true));
		addFeature(new SpawnFeature(this, true));
		addFeature(new PvPFeature(this, false));
		addFeature(new NoDropFeature(this));
		addFeature(new NoPickupFeature(this));
	}

	@Override
	public void startPhase() {
		s = new EHScanner(Bukkit.getWorld("Ehrenhalle"));
		s.load(EHAddOn.INSTATNCE.getDataFolder());

		s.clear();

		d = new EHData();
		try {
			d.load();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		n = new EHNPCs(s, d);
		n.spawn(null);

		super.startPhase();
	}

	@Command(name = "donation", permission = "donation", usage = "<name> <amount>", min = 2, max = 2, sync = true)
	public void donation(final CommandArgs args) {
		try {
			d.add(args.getArgs()[0], Double.parseDouble(args.getArgs()[1]));
		} catch (NumberFormatException e) {
			Prefix.API.getPrefix().then("Fehler: Dies ist eine Zahl! Format 10.00!").send(args.getSender());
		} catch (SQLException e) {
			Prefix.API.getPrefix()
					.then("Fehler: SQL Fehler! " + e.getErrorCode() + " " + e.getSQLState() + " " + e.getMessage())
					.send(args.getSender());
			e.printStackTrace();
		}

		Prefix.API.getPrefix().then("Der User hat nun schon " + d.get(args.getArgs()[0]) + "€ gespendet!");
		n.respawn(args.getArgs()[0]);

		((EHFeature) getFeature(FeatureType.EH)).updateScoreboard();
	}

	public EHScanner getS() {
		return s;
	}

	public EHData getD() {
		return d;
	}

	public EHNPCs getN() {
		return n;
	}
}
