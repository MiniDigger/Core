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
package me.MiniDigger.CraftCore.Feature.Features;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Phase.Phase;

import me.MiniDigger.CraftCore.Feature.CoreFeature;

public class DayNightFeature extends CoreFeature {

	/**
	 * Screw that, use http://dev.bukkit.org/bukkit-plugins/real-time-rotation/
	 *
	 * @param phase
	 */
	public DayNightFeature(final Phase phase) {
		super(phase);

	}

	@Override
	public FeatureType getType() {
		return FeatureType.DAYNIGHT;
	}

	@Override
	public List<FeatureType> getDependencies() {
		final List<FeatureType> result = new ArrayList<>();
		result.add(FeatureType.MAP);
		return result;
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
		final MapFeature m = (MapFeature) getPhase().getFeature(FeatureType.MAP);
		if (m.getMap() == null) {
			Core.getCore().getInstance().debug("dnf: map null");
			return;
		}
		final String world = m.getMap().getName();
		final World w = Bukkit.getWorld(world);
		Core.getCore().getTaskHandler().runTaskLater(new TimeUpdater(this, w), 1, getPhase());
	}

	@Override
	public void end() {

	}

}

class TimeUpdater extends BukkitRunnable {
	public static final float		MINECRAFT_TIME_PER_DAY	= 24000.0f;
	public static final float		REAL_TIME_PER_DAY		= 86400.0f;
	private final Plugin			plugin					= Core.getCore().getInstance();
	public static String			sunsetString;
	public static String			sunriseString;
	private static int				currentTick;
	private static float			lastCalculatedTime;
	public static int				lunarIndex;
	public static boolean			timeHaveBeenFetched;
	public static boolean			triedToFetchTime;
	private static int				ticksPerCalculation;
	private static float			timeOffset;
	private final DayNightFeature	f;
	private final World				w;

	static {
		TimeUpdater.currentTick = 0;
		TimeUpdater.lastCalculatedTime = -1.0f;
		TimeUpdater.timeHaveBeenFetched = false;
		TimeUpdater.triedToFetchTime = false;
		TimeUpdater.ticksPerCalculation = 20;
		TimeUpdater.timeOffset = 0.0f;
	}

	public TimeUpdater(final DayNightFeature f, final World w) {
		this.f = f;
		this.w = w;
	}

	public boolean loadConfiguration() {
		if (!TimeUpdater.triedToFetchTime) {
			// this.retrieveSunsetAndSunrise();
			this.retrieveLunarIndex();

			TimeUpdater.triedToFetchTime = true;
			TimeUpdater.timeHaveBeenFetched = true;
			TimeUpdater.sunriseString = "06:00:00";
			this.plugin.getLogger().info("Sunrise: " + TimeUpdater.sunriseString);
			TimeUpdater.sunsetString = "21:00:00";
			this.plugin.getLogger().info("Sunset: " + TimeUpdater.sunsetString);
			// TimeUpdater.lunarIndex = 0;
			this.plugin.getLogger().info("Lunar index: " + TimeUpdater.lunarIndex);

			if (!TimeUpdater.timeHaveBeenFetched) {
				this.plugin.getLogger().info("ErrorCode: #2001");
				return false;
			}
			return true;
		} else {
			if (!TimeUpdater.timeHaveBeenFetched) {
				this.plugin.getLogger().info("ErrorCode: #2003");
				return false;
			}
			return true;
		}
	}

	@Override
	public void run() {
		if (!this.loadConfiguration()) {
			return;
		}
		Core.getCore().getTaskHandler().runTaskLater(new TimeUpdater(f, w), 1, f.getPhase());
		this.setEquivalentTime();
	}

	public void setEquivalentTime() {
		++TimeUpdater.currentTick;
		if (TimeUpdater.currentTick == TimeUpdater.ticksPerCalculation || TimeUpdater.lastCalculatedTime == -1.0f) {
			final float timeMilliseconds = new Date().getTime() % 1000L / 1000.0f;
			final String theTime = new Date().toString().substring(11, 19);
			final float timeOfDayInSeconds = this.getTimeInSeconds(theTime) + timeMilliseconds + TimeUpdater.timeOffset;
			TimeUpdater.lastCalculatedTime = this.calculateRealTimeToMCTime(timeOfDayInSeconds);
			TimeUpdater.currentTick = 0;
		}

		w.setFullTime((long) TimeUpdater.lastCalculatedTime);
	}

	public float calculateRealTimeToMCTime(final float timeOfDay) {
		final float MCTimePerDay = 24000.0f;
		final float RLTimePerDay = 86400.0f;
		final int timeModulator = 1;
		final int calculatedMcRlTime = (int) ((timeOfDay + 1200.0f) * timeModulator % RLTimePerDay);
		final float sunrise = this.getTimeInSeconds(TimeUpdater.sunriseString);
		final float sunset = this.getTimeInSeconds(TimeUpdater.sunsetString);
		float MCTime;
		if (calculatedMcRlTime > sunrise && calculatedMcRlTime < sunset) {
			final float sunTime = sunset - sunrise;
			final float currentRLSunsetOffset = calculatedMcRlTime - sunrise;
			MCTime = currentRLSunsetOffset / sunTime * 15600.0f - 1800.0f;
		} else {
			final float nightTime = sunrise + (86400.0f - sunset);
			float offsetToSunset;
			if (calculatedMcRlTime > sunset) {
				offsetToSunset = calculatedMcRlTime - sunset;
			} else {
				offsetToSunset = 86400.0f - sunset + calculatedMcRlTime;
			}
			MCTime = offsetToSunset / nightTime * 8200.0f + 13800.0f;
		}
		MCTime += TimeUpdater.lunarIndex * MCTimePerDay;
		return MCTime;
	}

	public float getTimeInSeconds(final String theTime) {
		final int hour = Integer.parseInt(theTime.substring(0, 2));
		final int minute = Integer.parseInt(theTime.substring(3, 5));
		final int second = Integer.parseInt(theTime.substring(6, 8));
		return hour * 60 * 60 + minute * 60 + second;
	}

	public String getSecondsInTime(final float theTime) {
		final Date time = new Date((long) theTime * 1000L - Calendar.getInstance().get(15));
		return time.toString().substring(11, 19);
	}

	public void retrieveSunsetAndSunrise() {
		final String latitude = "50.9329964";
		final String longitude = "6.9293258";
		final String timeoffset = "+2";
		final Calendar cal = Calendar.getInstance();
		final int currentDay = cal.get(5);
		final int currentMonth = cal.get(2) + 1;
		final String XMLLocation = "http://www.earthtools.org/sun/" + latitude + "/" + longitude + "/" + currentDay + "/" + currentMonth + "/" + timeoffset + "/0";
		final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			final DocumentBuilder db = dbf.newDocumentBuilder();
			final Document doc = db.parse(new URL(XMLLocation).openStream());
			final NodeList sunriseElements = doc.getElementsByTagName("sunrise");
			int x = 0;
			if (x < sunriseElements.getLength()) {
				TimeUpdater.sunriseString = sunriseElements.item(x).getTextContent();
			}
			this.plugin.getLogger().info("Sunrise: " + TimeUpdater.sunriseString);
			final NodeList sunsetElements = doc.getElementsByTagName("sunset");
			x = 0;
			if (x < sunsetElements.getLength()) {
				TimeUpdater.sunsetString = sunsetElements.item(x).getTextContent();
			}
			this.plugin.getLogger().info("Sunset:  " + TimeUpdater.sunsetString);
			if (TimeUpdater.sunriseString != null && TimeUpdater.sunsetString != null) {
				TimeUpdater.timeHaveBeenFetched = true;
			} else {
				TimeUpdater.timeHaveBeenFetched = false;
			}
		}
		catch (final ParserConfigurationException e) {
			this.plugin.getLogger().info("ErrorCode: #2004");
			TimeUpdater.timeHaveBeenFetched = false;
		}
		catch (final MalformedURLException e2) {
			this.plugin.getLogger().info("ErrorCode: #2005");
			TimeUpdater.timeHaveBeenFetched = false;
		}
		catch (final SAXException e3) {
			this.plugin.getLogger().info("ErrorCode: #2006");
			TimeUpdater.timeHaveBeenFetched = false;
		}
		catch (final IOException e4) {
			this.plugin.getLogger().info("ErrorCode: #2007");
			TimeUpdater.timeHaveBeenFetched = false;
			e4.printStackTrace();
		}
		TimeUpdater.triedToFetchTime = true;
	}

	public void retrieveLunarIndex() {
		final int unixTimestamp = (int) (new Date().getTime() / 1000.0f);
		final String JSonLocation = "http://farmsense-prod.apigee.net/v1/moonphases/?d=" + unixTimestamp;
		try {
			final URL JSonUrl = new URL(JSonLocation);
			String JSonContent = "";
			final BufferedReader Input = new BufferedReader(new InputStreamReader(JSonUrl.openStream()));
			String inputLine;
			while ((inputLine = Input.readLine()) != null) {
				JSonContent = String.valueOf(JSonContent) + inputLine;
			}
			Input.close();
			final JSONArray inputStream = (JSONArray) new JSONParser().parse(JSonContent);
			final JSONObject resultObject = (JSONObject) inputStream.get(0);
			if (resultObject.containsKey("Index")) {
				TimeUpdater.lunarIndex = (int) (Long.parseLong(resultObject.get("Index").toString()) / 3.75) + 5;
				this.plugin.getLogger().info("Lunar index: " + resultObject.get("Index").toString() + " " + "-> MC Lunar index: " + TimeUpdater.lunarIndex);
			}
		}
		catch (final ParseException e) {
			this.plugin.getLogger().info("ErrorCode: #2008");
			TimeUpdater.timeHaveBeenFetched = false;
			e.printStackTrace();
		}
		catch (final IOException e2) {
			this.plugin.getLogger().info("ErrorCode: #2009");
			TimeUpdater.timeHaveBeenFetched = false;
			e2.printStackTrace();
		}
	}
}
