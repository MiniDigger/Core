package me.MiniDigger.Core.AddOn.GravityGun;

import me.MiniDigger.Core.Core;
import me.MiniDigger.CraftCore.AddOn.CoreAddOn;


public class GravityGunAddOn extends CoreAddOn{

	@Override
	public void enable() {
		Core.getCore().getItemHandler().register(new GravityGun());
		super.enable();
	}
}
