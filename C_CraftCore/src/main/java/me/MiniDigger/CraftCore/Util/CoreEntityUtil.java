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
package me.MiniDigger.CraftCore.Util;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.entity.EntityType;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Util.EntityUtil;

public class CoreEntityUtil implements EntityUtil {
	
	private static HashMap<EntityType, Type>	entitys	= new HashMap<>();
	
	public CoreEntityUtil() {
		super();
		for (final EntityType type : EntityType.values()) {
			switch (type) {
			case ARROW:
				entitys.put(type, Type.PROJECTILE);
				break;
			case BAT:
				entitys.put(type, Type.PASSIV);
				break;
			case BLAZE:
				entitys.put(type, Type.ANGRY);
				break;
			case BOAT:
				entitys.put(type, Type.OTHER);
				break;
			case CAVE_SPIDER:
				entitys.put(type, Type.PASSIV);
				break;
			case CHICKEN:
				entitys.put(type, Type.FRIENDLY);
				break;
			case COMPLEX_PART:
				entitys.put(type, Type.OTHER);
				break;
			case COW:
				entitys.put(type, Type.FRIENDLY);
				break;
			case CREEPER:
				entitys.put(type, Type.FRIENDLY);
				break;
			case DROPPED_ITEM:
				entitys.put(type, Type.OTHER);
				break;
			case EGG:
				entitys.put(type, Type.OTHER);
				break;
			case ENDERMAN:
				entitys.put(type, Type.PASSIV);
				break;
			case ENDER_CRYSTAL:
				entitys.put(type, Type.OTHER);
				break;
			case ENDER_DRAGON:
				entitys.put(type, Type.ANGRY);
				break;
			case ENDER_PEARL:
				entitys.put(type, Type.OTHER);
				break;
			case ENDER_SIGNAL:
				entitys.put(type, Type.OTHER);
				break;
			case EXPERIENCE_ORB:
				entitys.put(type, Type.OTHER);
				break;
			case FALLING_BLOCK:
				entitys.put(type, Type.OTHER);
				break;
			case FIREBALL:
				entitys.put(type, Type.PROJECTILE);
				break;
			case FIREWORK:
				entitys.put(type, Type.OTHER);
				break;
			case FISHING_HOOK:
				entitys.put(type, Type.PROJECTILE);
				break;
			case GHAST:
				entitys.put(type, Type.ANGRY);
				break;
			case GIANT:
				entitys.put(type, Type.ANGRY);
				break;
			case HORSE:
				entitys.put(type, Type.FRIENDLY);
				break;
			case IRON_GOLEM:
				entitys.put(type, Type.UTILITY);
				break;
			case ITEM_FRAME:
				entitys.put(type, Type.OTHER);
				break;
			case LEASH_HITCH:
				entitys.put(type, Type.OTHER);
				break;
			case LIGHTNING:
				entitys.put(type, Type.OTHER);
				break;
			case MAGMA_CUBE:
				entitys.put(type, Type.ANGRY);
				break;
			case MINECART:
				entitys.put(type, Type.CART);
				break;
			case MINECART_CHEST:
				entitys.put(type, Type.CART);
				break;
			case MINECART_COMMAND:
				entitys.put(type, Type.CART);
				break;
			case MINECART_FURNACE:
				entitys.put(type, Type.CART);
				break;
			case MINECART_HOPPER:
				entitys.put(type, Type.CART);
				break;
			case MINECART_MOB_SPAWNER:
				entitys.put(type, Type.CART);
				break;
			case MINECART_TNT:
				entitys.put(type, Type.CART);
				break;
			case MUSHROOM_COW:
				entitys.put(type, Type.FRIENDLY);
				break;
			case OCELOT:
				entitys.put(type, Type.FRIENDLY);
				break;
			case PAINTING:
				entitys.put(type, Type.OTHER);
				break;
			case PIG:
				entitys.put(type, Type.FRIENDLY);
				break;
			case PIG_ZOMBIE:
				entitys.put(type, Type.PASSIV);
				break;
			case PLAYER:
				entitys.put(type, Type.OTHER);
				break;
			case PRIMED_TNT:
				entitys.put(type, Type.OTHER);
				break;
			case SHEEP:
				entitys.put(type, Type.FRIENDLY);
				break;
			case SILVERFISH:
				entitys.put(type, Type.ANGRY);
				break;
			case SKELETON:
				entitys.put(type, Type.ANGRY);
				break;
			case SLIME:
				entitys.put(type, Type.ANGRY);
				break;
			case SMALL_FIREBALL:
				entitys.put(type, Type.PROJECTILE);
				break;
			case SNOWBALL:
				entitys.put(type, Type.PROJECTILE);
				break;
			case SNOWMAN:
				entitys.put(type, Type.UTILITY);
				break;
			case SPIDER:
				entitys.put(type, Type.PASSIV);
				break;
			case SPLASH_POTION:
				entitys.put(type, Type.OTHER);
				break;
			case SQUID:
				entitys.put(type, Type.FRIENDLY);
				break;
			case THROWN_EXP_BOTTLE:
				entitys.put(type, Type.OTHER);
				break;
			case UNKNOWN:
				entitys.put(type, Type.OTHER);
				break;
			case VILLAGER:
				entitys.put(type, Type.FRIENDLY);
				break;
			case WEATHER:
				entitys.put(type, Type.OTHER);
				break;
			case WITCH:
				entitys.put(type, Type.ANGRY);
				break;
			case WITHER:
				entitys.put(type, Type.ANGRY);
				break;
			case WITHER_SKULL:
				entitys.put(type, Type.PROJECTILE);
				break;
			case WOLF:
				entitys.put(type, Type.PASSIV);
				break;
			case ZOMBIE:
				entitys.put(type, Type.ANGRY);
				break;
			case ARMOR_STAND:
				entitys.put(type, Type.UTILITY);
				break;
			case ENDERMITE:
				entitys.put(type, Type.ANGRY);
				break;
			case GUARDIAN:
				entitys.put(type, Type.ANGRY);
				break;
			case RABBIT:
				entitys.put(type, Type.FRIENDLY);
				break;
			}
		}
	}
	
	@Override
	public ArrayList<EntityType> getAll(final Type... types) {
		final ArrayList<EntityType> result = new ArrayList<>();
		
		for (final EntityType type : entitys.keySet()) {
			if (Core.getCore().getStringUtil().contains(types, entitys.get(type))) {
				result.add(type);
			}
		}
		
		return result;
	}
	
}
