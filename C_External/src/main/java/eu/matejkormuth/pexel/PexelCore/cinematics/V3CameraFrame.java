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
 * Written by Martin Benndorf <admin@minidigger.me>, 2013-2014 and others
 */
package eu.matejkormuth.pexel.PexelCore.cinematics;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

/**
 * Reprezentuje ram/frame v klipe tretej verzie.
 * 
 * @author Mato Kormuth
 * 
 */
public class V3CameraFrame {
	
	/**
	 * Pozicia hraca.
	 */
	private Location	 cameraLocation;
	/**
	 * Specifikuje, ci frame obsahuje iba meta.
	 */
	private boolean	     isMetaOnly	= false;
	/**
	 * Zoznam dalsich extra dat v ramci.
	 */
	private List<V3Meta>	metas	= new ArrayList<V3Meta>();
	/**
	 * Zoom.
	 */
	private float	     zoom;
	/**
	 * Odkaz na klip, ktoremu ram parti.
	 */
	public V3CameraClip	 clip;
	
	/**
	 * Vytvori novy frame.
	 * 
	 * @param cameraLocation
	 * @param isMetaOnly
	 */
	public V3CameraFrame(final Location cameraLocation, final boolean isMetaOnly) {
		this.cameraLocation = cameraLocation;
		camX = cameraLocation.getX();
		camY = cameraLocation.getY();
		camZ = cameraLocation.getZ();
		yaw = cameraLocation.getYaw();
		pitch = cameraLocation.getPitch();
		this.isMetaOnly = isMetaOnly;
	}
	
	public V3CameraFrame(final double x, final double y, final double z, final float yaw, final float pitch, final boolean isMetaOnly) {
		camX = x;
		camY = y;
		camZ = z;
		this.pitch = pitch;
		this.yaw = yaw;
		this.isMetaOnly = isMetaOnly;
	}
	
	public V3CameraFrame(final String line) {
		
	}
	
	public int	  verzia	= 3;
	public double	camX;
	public double	camY;
	public double	camZ;
	public float	yaw;
	public float	pitch;
	
	public String serialize() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Prida zaznam o pozicii entity.
	 * 
	 * @param e
	 */
	public void addEntityLocation(final Entity e) {
		if (clip.entites.contains(e)) {
			// this.clip.entites.
		}
	}
	
	/**
	 * @return the playerLocation
	 */
	public Location getCameraLocation() {
		return cameraLocation;
	}
	
	/**
	 * @param playerLocation
	 *            the playerLocation to set
	 */
	public void setCameraLocation(final Location playerLocation) {
		cameraLocation = playerLocation;
	}
	
	/**
	 * @return the isMeta
	 */
	public boolean isMetaOnly() {
		return isMetaOnly;
	}
	
	/**
	 * @param isMetaOnly
	 *            the isMeta to set
	 */
	public void setMetaOnly(final boolean isMetaOnly) {
		this.isMetaOnly = isMetaOnly;
	}
	
	/**
	 * @return the extraData
	 */
	public List<V3Meta> getMetas() {
		return metas;
	}
	
	/**
	 * Prida extra data.
	 * 
	 * @param data
	 */
	public void addMeta(final V3Meta data) {
		metas.add(data);
	}
	
	/**
	 * @param metas
	 *            the extraData to set
	 */
	public void setMeta(final List<V3Meta> metas) {
		this.metas = metas;
	}
	
	/**
	 * @return the zoom
	 */
	public float getZoom() {
		return zoom;
	}
	
	/**
	 * @param zoom
	 *            the zoom to set
	 */
	public V3CameraFrame setZoom(final float zoom) {
		this.zoom = zoom;
		return this; // For method chaining.
	}
	
	/**
	 * Vrati ci tento frame ma nejake meta.
	 * 
	 * @return
	 */
	public boolean hasMeta() {
		return (metas.size() != 0);
	}
	
	public int getMetaCount() {
		return metas.size();
	}
}