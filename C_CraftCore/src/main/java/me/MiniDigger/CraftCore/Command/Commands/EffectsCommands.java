/**
 * █████████████████████████████████████████████████████████████████████████████████████████████████████████████████
 * █░░░░░░░░░░░░░░█░░░░░░██░░░░░░█░░░░░░░░░░░░░░████░░░░░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░░░░░░░░░░░███░░░░░░░░░░░░░░█
 * █░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░████░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀▄▀░░███░░▄▀▄▀▄▀▄▀▄▀░░█
 * █░░░░░░▄▀░░░░░░█░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░░░░░████░░▄▀░░░░░░░░░░█░░▄▀░░░░░░▄▀░░█░░▄▀░░░░░░░░▄▀░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░████████████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░████░░▄▀░░███░░▄▀░░█████████
 * █████░░▄▀░░█████░░▄▀░░░░░░▄▀░░█░░▄▀░░░░░░░░░░████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░░░▄▀░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀▄▀░░███░░▄▀▄▀▄▀▄▀▄▀░░█
 * █████░░▄▀░░█████░░▄▀░░░░░░▄▀░░█░░▄▀░░░░░░░░░░████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░▄▀░░░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░████████████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░██░░▄▀░░█████░░▄▀░░█████████
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░░░░░████░░▄▀░░░░░░░░░░█░░▄▀░░░░░░▄▀░░█░░▄▀░░██░░▄▀░░░░░░█░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░████░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░██░░▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█
 * █████░░░░░░█████░░░░░░██░░░░░░█░░░░░░░░░░░░░░████░░░░░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░██░░░░░░░░░░█░░░░░░░░░░░░░░█
 * █████████████████████████████████████████████████████████████████████████████████████████████████████████████████
 * 
 * Copyright © MiniDigger and others - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Martin Benndorf <admin@minidigger.me>, 2013-2015 and others
 */
package me.MiniDigger.CraftCore.Command.Commands;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;

import org.bukkit.ChatColor;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Command.Command;
import me.MiniDigger.Core.Command.CommandArgs;

import de.slikey.effectlib.EffectManager;
import de.slikey.effectlib.effect.AnimatedBallEffect;
import de.slikey.effectlib.effect.ArcEffect;
import de.slikey.effectlib.effect.AtomEffect;
import de.slikey.effectlib.effect.BigBangEffect;
import de.slikey.effectlib.effect.BleedEffect;
import de.slikey.effectlib.effect.CircleEffect;
import de.slikey.effectlib.effect.CloudEffect;
import de.slikey.effectlib.effect.ColoredImageEffect;
import de.slikey.effectlib.effect.ConeEffect;
import de.slikey.effectlib.effect.CubeEffect;
import de.slikey.effectlib.effect.CylinderEffect;
import de.slikey.effectlib.effect.DiscoBallEffect;
import de.slikey.effectlib.effect.DnaEffect;
import de.slikey.effectlib.effect.DonutEffect;
import de.slikey.effectlib.effect.DragonEffect;
import de.slikey.effectlib.effect.EarthEffect;
import de.slikey.effectlib.effect.ExplodeEffect;
import de.slikey.effectlib.effect.FlameEffect;
import de.slikey.effectlib.effect.FountainEffect;
import de.slikey.effectlib.effect.GridEffect;
import de.slikey.effectlib.effect.HeartEffect;
import de.slikey.effectlib.effect.HelixEffect;
import de.slikey.effectlib.effect.HillEffect;
import de.slikey.effectlib.effect.IconEffect;
import de.slikey.effectlib.effect.ImageEffect;
import de.slikey.effectlib.effect.JumpEffect;
import de.slikey.effectlib.effect.LineEffect;
import de.slikey.effectlib.effect.LoveEffect;
import de.slikey.effectlib.effect.MusicEffect;
import de.slikey.effectlib.effect.ShieldEffect;
import de.slikey.effectlib.effect.SkyRocketEffect;
import de.slikey.effectlib.effect.SmokeEffect;
import de.slikey.effectlib.effect.SphereEffect;
import de.slikey.effectlib.effect.StarEffect;
import de.slikey.effectlib.effect.TextEffect;
import de.slikey.effectlib.effect.TornadoEffect;
import de.slikey.effectlib.effect.TraceEffect;
import de.slikey.effectlib.effect.TurnEffect;
import de.slikey.effectlib.effect.VortexEffect;
import de.slikey.effectlib.effect.WarpEffect;
import de.slikey.effectlib.effect.WaveEffect;
import de.slikey.effectlib.util.ParticleEffect;

public class EffectsCommands {
	
	public static final String[]	effects	   = new String[] { "animatedball", "arc", "atom", "bigbang", "bleed", "circle", "cloud", "coloredimage", "cone", "cube",
	        "cylinder", "discoball", "dna", "donut", "dragon", "earth", "explode", "flame", "fountain", "grid", "heart", "helix", "hill", "icon", "image", "jump",
	        "line", "love", "music", "shield", "skyrocket", "smoke", "sphere", "star", "text", "tornado", "trace", "turn", "vortex", "warp", "wave" };
	
	private final EffectManager	 effectManager	= new EffectManager(Core.getCore().getInstance());
	
	@Command(name = "effect", permission = "effect", usage = "", description = "Macht alles mit effects")
	public void effect(final CommandArgs args) {
		// TODO effects help
	}
	
	@Command(name = "effect.animatedball", permission = "effect.animatedball", usage = "", description = "")
	public void animatedball(final CommandArgs args) {
		final AnimatedBallEffect animatedBallEffect = new AnimatedBallEffect(effectManager);
		animatedBallEffect.setEntity(args.getPlayer());
		animatedBallEffect.start();
	}
	
	@SuppressWarnings("deprecation")
	@Command(name = "effect.arc", permission = "effect.arc", usage = "", description = "")
	public void arc(final CommandArgs args) {
		final ArcEffect arcEffect = new ArcEffect(effectManager);
		arcEffect.setLocation(args.getPlayer().getLocation());
		arcEffect.setTarget(args.getPlayer().getTargetBlock((HashSet<Byte>) null, 64).getLocation().subtract(0.0, 5.0, 0.0));
		arcEffect.start();
	}
	
	@Command(name = "effect.atom", permission = "effect.atom", usage = "", description = "")
	public void atom(final CommandArgs args) {
		final AtomEffect atomEffect = new AtomEffect(effectManager);
		atomEffect.setLocation(args.getPlayer().getLocation());
		atomEffect.start();
	}
	
	@Command(name = "effect.bigbang", permission = "effect.bigbang", usage = "", description = "")
	public void bigbang(final CommandArgs args) {
		final BigBangEffect bigBangEffect = new BigBangEffect(effectManager);
		bigBangEffect.setEntity(args.getPlayer());
		bigBangEffect.start();
	}
	
	@Command(name = "effect.bleed", permission = "effect.bleed", usage = "", description = "")
	public void bleed(final CommandArgs args) {
		final BleedEffect bleedEffect = new BleedEffect(effectManager);
		bleedEffect.setEntity(args.getPlayer());
		bleedEffect.start();
	}
	
	@Command(name = "effect.circle", permission = "effect.circle", usage = "", description = "")
	public void circle(final CommandArgs args) {
		final CircleEffect circleEffect = new CircleEffect(effectManager);
		circleEffect.setEntity(args.getPlayer());
		circleEffect.enableRotation = true;
		circleEffect.iterations = 200;
		circleEffect.radius = 2.0f;
		circleEffect.start();
	}
	
	@Command(name = "effect.cloud", permission = "effect.cloud", usage = "", description = "")
	public void cloud(final CommandArgs args) {
		final CloudEffect cloudEffect = new CloudEffect(effectManager);
		cloudEffect.setEntity(args.getPlayer());
		cloudEffect.start();
	}
	
	@Command(name = "effect.coloredimage", permission = "effect.coloredimage", usage = "", description = "")
	public void coloredimage(final CommandArgs args) {
		try {
			final ColoredImageEffect coloredImageEffect = new ColoredImageEffect(effectManager);
			coloredImageEffect.setLocation(args.getPlayer().getLocation());
			coloredImageEffect.loadFile(new File("plugins/EffectLibTest/gifimage.gif"));
			coloredImageEffect.iterations = 200;
			coloredImageEffect.start();
		} catch (final IOException e) {
			args.getPlayer().sendMessage(
			        ChatColor.RED + "The image was not loaded! Make sure you have " + "an image called gifimage.gif in your /plugins/EffectLibTest/ folder!");
			e.printStackTrace();
		}
	}
	
	@Command(name = "effect.cone", permission = "effect.cone", usage = "", description = "")
	public void cone(final CommandArgs args) {
		final ConeEffect coneEffect = new ConeEffect(effectManager);
		coneEffect.setEntity(args.getPlayer());
		coneEffect.start();
	}
	
	@Command(name = "effect.cube", permission = "effect.cube", usage = "", description = "")
	public void cube(final CommandArgs args) {
		final CubeEffect cubeEffect = new CubeEffect(effectManager);
		cubeEffect.setLocation(args.getPlayer().getLocation());
		cubeEffect.start();
	}
	
	@Command(name = "effect.cylinder", permission = "effect.cylinder", usage = "", description = "")
	public void cylinder(final CommandArgs args) {
		final CylinderEffect cylinderEffect = new CylinderEffect(effectManager);
		cylinderEffect.setLocation(args.getPlayer().getLocation());
		cylinderEffect.start();
	}
	
	@Command(name = "effect.discoball", permission = "effect.discoball", usage = "", description = "")
	public void discoball(final CommandArgs args) {
		final DiscoBallEffect discoBallEffect = new DiscoBallEffect(effectManager);
		discoBallEffect.setLocation(args.getPlayer().getLocation());
		if (args.getArgs().length == 1) {
			discoBallEffect.direction = DiscoBallEffect.Direction.valueOf(args.getArgs()[0].toUpperCase());
		}
		discoBallEffect.start();
	}
	
	@Command(name = "effect.dna", permission = "effect.dna", usage = "", description = "")
	public void dna(final CommandArgs args) {
		final DnaEffect dnaEffect = new DnaEffect(effectManager);
		dnaEffect.setLocation(args.getPlayer().getLocation());
		dnaEffect.start();
	}
	
	@Command(name = "effect.donut", permission = "effect.donut", usage = "", description = "")
	public void donut(final CommandArgs args) {
		final DonutEffect donutEffect = new DonutEffect(effectManager);
		donutEffect.setLocation(args.getPlayer().getLocation());
		donutEffect.start();
	}
	
	@Command(name = "effect.dragon", permission = "effect.dragon", usage = "", description = "")
	public void dragon(final CommandArgs args) {
		final DragonEffect dragonEffect = new DragonEffect(effectManager);
		dragonEffect.setEntity(args.getPlayer());
		dragonEffect.start();
	}
	
	@Command(name = "effect.earth", permission = "effect.earth", usage = "", description = "")
	public void earth(final CommandArgs args) {
		final EarthEffect earthEffect = new EarthEffect(effectManager);
		earthEffect.setLocation(args.getPlayer().getLocation());
		earthEffect.start();
	}
	
	@Command(name = "effect.explode", permission = "effect.explode", usage = "", description = "")
	public void explode(final CommandArgs args) {
		final ExplodeEffect explodeEffect = new ExplodeEffect(effectManager);
		explodeEffect.setLocation(args.getPlayer().getLocation());
		explodeEffect.start();
	}
	
	@Command(name = "effect.flame", permission = "effect.flame", usage = "", description = "")
	public void flame(final CommandArgs args) {
		final FlameEffect flameEffect = new FlameEffect(effectManager);
		flameEffect.setEntity(args.getPlayer());
		flameEffect.start();
	}
	
	@Command(name = "effect.fountain", permission = "effect.fountain", usage = "", description = "")
	public void fountain(final CommandArgs args) {
		final FountainEffect fountainEffect = new FountainEffect(effectManager);
		fountainEffect.setLocation(args.getPlayer().getLocation());
		fountainEffect.start();
	}
	
	@Command(name = "effect.grid", permission = "effect.grid", usage = "", description = "")
	public void grid(final CommandArgs args) {
		final GridEffect gridEffect = new GridEffect(effectManager);
		gridEffect.setLocation(args.getPlayer().getLocation());
		gridEffect.start();
	}
	
	@Command(name = "effect.heart", permission = "effect.heart", usage = "", description = "")
	public void heart(final CommandArgs args) {
		final HeartEffect heartEffect = new HeartEffect(effectManager);
		heartEffect.setEntity(args.getPlayer());
		heartEffect.start();
	}
	
	@Command(name = "effect.helix", permission = "effect.helix", usage = "", description = "")
	public void helix(final CommandArgs args) {
		final HelixEffect helixEffect = new HelixEffect(effectManager);
		helixEffect.setLocation(args.getPlayer().getLocation());
		helixEffect.start();
	}
	
	@Command(name = "effect.hill", permission = "effect.hill", usage = "", description = "")
	public void hill(final CommandArgs args) {
		final HillEffect hillEffect = new HillEffect(effectManager);
		hillEffect.setLocation(args.getPlayer().getLocation());
		hillEffect.start();
	}
	
	@Command(name = "effect.icon", permission = "effect.icon", usage = "", description = "")
	public void icon(final CommandArgs args) {
		final IconEffect iconEffect = new IconEffect(effectManager);
		iconEffect.setLocation(args.getPlayer().getLocation());
		iconEffect.start();
	}
	
	@Command(name = "effect.image", permission = "effect.image", usage = "", description = "")
	public void image(final CommandArgs args) {
		try {
			final ImageEffect imageEffect = new ImageEffect(effectManager);
			imageEffect.setLocation(args.getPlayer().getLocation());
			imageEffect.loadFile(new File("plugins/EffectLibTest/image.png"));
			imageEffect.enableRotation = true;
			imageEffect.start();
		} catch (final IOException e2) {
			args.getPlayer().sendMessage(
			        ChatColor.RED + "The image was not loaded! Make sure you have " + "an image called image.png in your /plugins/EffectLibTest/ folder!");
			e2.printStackTrace();
		}
	}
	
	@Command(name = "effect.jump", permission = "effect.jump", usage = "", description = "")
	public void jump(final CommandArgs args) {
		final JumpEffect jumpEffect = new JumpEffect(effectManager);
		jumpEffect.setEntity(args.getPlayer());
		jumpEffect.start();
	}
	
	@SuppressWarnings("deprecation")
	@Command(name = "effect.line", permission = "effect.line", usage = "", description = "")
	public void line(final CommandArgs args) {
		final LineEffect lineEffect = new LineEffect(effectManager);
		lineEffect.setEntity(args.getPlayer());
		lineEffect.setTarget(args.getPlayer().getTargetBlock((HashSet<Byte>) null, 64).getLocation());
		lineEffect.start();
	}
	
	@Command(name = "effect.love", permission = "effect.love", usage = "", description = "")
	public void love(final CommandArgs args) {
		final LoveEffect loveEffect = new LoveEffect(effectManager);
		loveEffect.setEntity(args.getPlayer());
		loveEffect.start();
	}
	
	@Command(name = "effect.music", permission = "effect.music", usage = "", description = "")
	public void music(final CommandArgs args) {
		final MusicEffect musicEffect = new MusicEffect(effectManager);
		musicEffect.setEntity(args.getPlayer());
		musicEffect.start();
	}
	
	@Command(name = "effect.shield", permission = "effect.shield", usage = "", description = "")
	public void shield(final CommandArgs args) {
		final ShieldEffect shieldEffect = new ShieldEffect(effectManager);
		shieldEffect.setEntity(args.getPlayer());
		shieldEffect.start();
	}
	
	@Command(name = "effect.skyrocket", permission = "effect.skyrocket", usage = "", description = "")
	public void skyrocket(final CommandArgs args) {
		final SkyRocketEffect skyRocketEffect = new SkyRocketEffect(effectManager);
		skyRocketEffect.setEntity(args.getPlayer());
		skyRocketEffect.start();
	}
	
	@Command(name = "effect.smoke", permission = "effect.smoke", usage = "", description = "")
	public void smoke(final CommandArgs args) {
		final SmokeEffect smokeEffect = new SmokeEffect(effectManager);
		smokeEffect.setEntity(args.getPlayer());
		smokeEffect.start();
	}
	
	@Command(name = "effect.sphere", permission = "effect.sphere", usage = "", description = "")
	public void sphere(final CommandArgs args) {
		final SphereEffect sphereEffect = new SphereEffect(effectManager);
		sphereEffect.setLocation(args.getPlayer().getLocation());
		sphereEffect.particle = ParticleEffect.FLAME;
		sphereEffect.start();
	}
	
	@Command(name = "effect.star", permission = "effect.star", usage = "", description = "")
	public void star(final CommandArgs args) {
		final StarEffect starEffect = new StarEffect(effectManager);
		starEffect.setLocation(args.getPlayer().getLocation());
		starEffect.start();
	}
	
	@Command(name = "effect.text", permission = "effect.text", usage = "", description = "")
	public void text(final CommandArgs args) {
		final TextEffect textEffect = new TextEffect(effectManager);
		textEffect.setLocation(args.getPlayer().getLocation());
		textEffect.text = args.getPlayer().getName();
		textEffect.start();
	}
	
	@Command(name = "effect.tornado", permission = "effect.tornado", usage = "", description = "")
	public void tornado(final CommandArgs args) {
		final TornadoEffect tornadoEffect = new TornadoEffect(effectManager);
		tornadoEffect.setLocation(args.getPlayer().getLocation());
		tornadoEffect.maxTornadoRadius = 3.0f;
		tornadoEffect.start();
	}
	
	@Command(name = "effect.trace", permission = "effect.trace", usage = "", description = "")
	public void trace(final CommandArgs args) {
		final TraceEffect traceEffect = new TraceEffect(effectManager);
		traceEffect.setEntity(args.getPlayer());
		traceEffect.start();
	}
	
	@Command(name = "effect.turn", permission = "effect.turn", usage = "", description = "")
	public void turn(final CommandArgs args) {
		final TurnEffect turnEffect = new TurnEffect(effectManager);
		turnEffect.setEntity(args.getPlayer());
		turnEffect.start();
	}
	
	@Command(name = "effect.vortex", permission = "effect.vortex", usage = "", description = "")
	public void vortex(final CommandArgs args) {
		final VortexEffect vortexEffect = new VortexEffect(effectManager);
		vortexEffect.setLocation(args.getPlayer().getLocation());
		vortexEffect.start();
	}
	
	@Command(name = "effect.warp", permission = "effect.warp", usage = "", description = "")
	public void warp(final CommandArgs args) {
		final WarpEffect warpEffect = new WarpEffect(effectManager);
		warpEffect.setEntity(args.getPlayer());
		warpEffect.start();
	}
	
	@Command(name = "effect.wave", permission = "effect.wave", usage = "", description = "")
	public void wave(final CommandArgs args) {
		final WaveEffect waveEffect = new WaveEffect(effectManager);
		waveEffect.setLocation(args.getPlayer().getLocation());
		waveEffect.start();
	}
}
