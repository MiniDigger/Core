package me.MiniDigger.CraftCore.Feature.Features;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.ChatColor;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Command.Command;
import me.MiniDigger.Core.Command.CommandArgs;
import me.MiniDigger.Core.Command.Completer;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.Core.Prefix.Prefix;
import me.MiniDigger.Core.User.User;

import me.MiniDigger.CraftCore.Feature.CoreFeature;

import de.slikey.effectlib.Effect;
import de.slikey.effectlib.EffectLib;
import de.slikey.effectlib.EffectManager;
import de.slikey.effectlib.EffectType;
import de.slikey.effectlib.effect.TraceEffect;
import de.slikey.effectlib.util.ParticleEffect;

public class ParticleTrailFeature extends CoreFeature {

	private final Map<UUID, Effect>	effects	= new HashMap<>();
	private final EffectLib			lib		= EffectLib.instance();
	private final EffectManager		em		= new EffectManager(lib);

	public ParticleTrailFeature(final Phase phase) {
		super(phase);
	}

	@Override
	public FeatureType getType() {
		return FeatureType.PARTICLE_TRAIL;
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
		em.disposeOnTermination();
	}

	private void effect(final User user, final ParticleEffect e) {
		final TraceEffect effect = new TraceEffect(em) {
			@Override
			public void onDone() {
				System.out.println("looks like I am done");
			}
		};
		effect.setEntity(user.getPlayer());
		effect.particle = e;
		effect.maxWayPoints = 5;
		effect.iterations = Integer.MAX_VALUE;
		effect.type = EffectType.REPEATING;
		effect.start();
		effects.put(user.getUUID(), effect);

	}

	private void cancel(final User user) {
		try {
			effects.remove(user.getUUID()).cancel();
		}
		catch (final Exception ex) {}
	}

	@Command(name = "trail", usage = "<type>", min = 1, max = 1, consol = false, sync = true, permission = "trail")
	public void effectCmd(final CommandArgs args) {
		if (args.getArgs()[0].equalsIgnoreCase("NONE")) {
			cancel(args.getUser());
			return;
		}

		try {
			cancel(args.getUser());
			effect(args.getUser(), ParticleEffect.valueOf(args.getArgs()[0]));
		}
		catch (final Exception ex) {
			Prefix.API.getPrefix().then("Unbekannter Effect Typ").color(ChatColor.RED).send(args.getPlayer());
		}
	}

	@Completer(name = "trail")
	public List<String> effectComp(final CommandArgs args) {
		final List<String> result = new ArrayList<>();

		if (args.getArgs().length == 1) {
			result.add("NONE");
			for (final ParticleEffect e : ParticleEffect.values()) {
				result.add(e.name());
			}

			return Core.getCore().getCommonMethods().completer(result, args.getArgs()[0]);
		} else {
			return new ArrayList<String>();
		}
	}
}
