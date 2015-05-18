package me.MiniDigger.CraftCore.Util;

import java.util.EnumMap;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.util.Vector;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Util.FaceUtil;

public class CoreFaceUtil implements FaceUtil {
	
	public final BlockFace[]	              AXIS	        = new BlockFace[4];
	public final BlockFace[]	              RADIAL	    = { BlockFace.SOUTH, BlockFace.SOUTH_WEST, BlockFace.WEST, BlockFace.NORTH_WEST, BlockFace.NORTH,
	        BlockFace.NORTH_EAST, BlockFace.EAST, BlockFace.SOUTH_EAST };
	public final BlockFace[]	              BLOCK_SIDES	= { BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST, BlockFace.UP, BlockFace.DOWN };
	public final BlockFace[]	              ATTACHEDFACES	= { BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST, BlockFace.UP };
	private final EnumMap<BlockFace, Integer>	notches	    = new EnumMap<BlockFace, Integer>(BlockFace.class);
	
	public CoreFaceUtil() {
		for (int i = 0; i < RADIAL.length; i++) {
			notches.put(RADIAL[i], i);
		}
		for (int i = 0; i < AXIS.length; i++) {
			AXIS[i] = RADIAL[i << 1];
		}
	}
	
	@Override
	public int faceToNotch(BlockFace face) {
		Integer notch = notches.get(face);
		return notch == null ? 0 : notch.intValue();
	}
	
	@Override
	public boolean isAlongX(BlockFace face) {
		return face.getModX() != 0 && face.getModZ() == 0;
	}
	
	@Override
	public boolean isAlongY(BlockFace face) {
		return isVertical(face);
	}
	
	@Override
	public boolean isAlongZ(BlockFace face) {
		return face.getModZ() != 0 && face.getModX() == 0;
	}
	
	@Override
	public BlockFace notchToFace(int notch) {
		return RADIAL[notch & 0x7];
	}
	
	@Override
	public BlockFace rotate(BlockFace from, int notchCount) {
		return notchToFace(faceToNotch(from) + notchCount);
	}
	
	@Override
	public BlockFace combine(BlockFace from, BlockFace to) {
		if (from == BlockFace.NORTH) {
			if (to == BlockFace.WEST) {
				return BlockFace.NORTH_WEST;
			} else if (to == BlockFace.EAST) {
				return BlockFace.NORTH_EAST;
			}
		} else if (from == BlockFace.EAST) {
			if (to == BlockFace.NORTH) {
				return BlockFace.NORTH_EAST;
			} else if (to == BlockFace.SOUTH) {
				return BlockFace.SOUTH_EAST;
			}
		} else if (from == BlockFace.SOUTH) {
			if (to == BlockFace.WEST) {
				return BlockFace.SOUTH_WEST;
			} else if (to == BlockFace.EAST) {
				return BlockFace.SOUTH_EAST;
			}
		} else if (from == BlockFace.WEST) {
			if (to == BlockFace.NORTH) {
				return BlockFace.NORTH_WEST;
			} else if (to == BlockFace.SOUTH) {
				return BlockFace.SOUTH_WEST;
			}
		}
		return from;
	}
	
	@Override
	public BlockFace subtract(BlockFace face1, BlockFace face2) {
		return notchToFace(faceToNotch(face1) - faceToNotch(face2));
	}
	
	@Override
	public BlockFace add(BlockFace face1, BlockFace face2) {
		return notchToFace(faceToNotch(face1) + faceToNotch(face2));
	}
	
	@Override
	public BlockFace[] getFaces(BlockFace main) {
		switch (main) {
		case SOUTH_EAST:
			return new BlockFace[] { BlockFace.SOUTH, BlockFace.EAST };
		case SOUTH_WEST:
			return new BlockFace[] { BlockFace.SOUTH, BlockFace.WEST };
		case NORTH_EAST:
			return new BlockFace[] { BlockFace.NORTH, BlockFace.EAST };
		case NORTH_WEST:
			return new BlockFace[] { BlockFace.NORTH, BlockFace.WEST };
		default:
			return new BlockFace[] { main, main.getOppositeFace() };
		}
	}
	
	@Override
	public BlockFace getRailsCartDirection(final BlockFace raildirection) {
		switch (raildirection) {
		case NORTH_EAST:
		case SOUTH_WEST:
			return BlockFace.NORTH_WEST;
		case NORTH_WEST:
		case SOUTH_EAST:
			return BlockFace.SOUTH_WEST;
		default:
			return raildirection;
		}
	}
	
	@Override
	public BlockFace toRailsDirection(BlockFace direction) {
		switch (direction) {
		case NORTH:
			return BlockFace.SOUTH;
		case WEST:
			return BlockFace.EAST;
		default:
			return direction;
		}
	}
	
	@Override
	public boolean isSubCardinal(final BlockFace face) {
		switch (face) {
		case NORTH_EAST:
		case SOUTH_EAST:
		case SOUTH_WEST:
		case NORTH_WEST:
			return true;
		default:
			return false;
		}
	}
	
	@Override
	public boolean isVertical(BlockFace face) {
		return face == BlockFace.UP || face == BlockFace.DOWN;
	}
	
	@Override
	public BlockFace getVertical(boolean up) {
		return up ? BlockFace.UP : BlockFace.DOWN;
	}
	
	@Override
	public BlockFace getVertical(double dy) {
		return getVertical(dy >= 0.0);
	}
	
	@Override
	public boolean hasSubDifference(final BlockFace face1, final BlockFace face2) {
		return getFaceYawDifference(face1, face2) <= 45;
	}
	
	@Override
	public Vector faceToVector(BlockFace face, double length) {
		return faceToVector(face).multiply(length);
	}
	
	@Override
	public Vector faceToVector(BlockFace face) {
		return new Vector(face.getModX(), face.getModY(), face.getModZ());
	}
	
	@Override
	public BlockFace getDirection(Location from, Location to, boolean useSubCardinalDirections) {
		return getDirection(to.getX() - from.getX(), to.getZ() - from.getZ(), useSubCardinalDirections);
	}
	
	@Override
	public BlockFace getDirection(Block from, Block to, boolean useSubCardinalDirections) {
		return getDirection(to.getX() - from.getX(), to.getZ() - from.getZ(), useSubCardinalDirections);
	}
	
	@Override
	public BlockFace getDirection(Vector movement) {
		return getDirection(movement, true);
	}
	
	@Override
	public BlockFace getDirection(Vector movement, boolean useSubCardinalDirections) {
		return getDirection(movement.getX(), movement.getZ(), useSubCardinalDirections);
	}
	
	@Override
	public BlockFace getDirection(final double dx, final double dz, boolean useSubCardinalDirections) {
		return yawToFace(Core.getCore().getMathUtil().getLookAtYaw(dx, dz), useSubCardinalDirections);
	}
	
	@Override
	public int getFaceYawDifference(BlockFace face1, BlockFace face2) {
		return Core.getCore().getMathUtil().getAngleDifference(faceToYaw(face1), faceToYaw(face2));
	}
	
	@Override
	public double cos(final BlockFace face) {
		switch (face) {
		case SOUTH_WEST:
		case NORTH_WEST:
			return -Core.getCore().getMathUtil().getHalfRootOfTwo();
		case SOUTH_EAST:
		case NORTH_EAST:
			return Core.getCore().getMathUtil().getHalfRootOfTwo();
		case EAST:
			return 1;
		case WEST:
			return -1;
		default:
			return 0;
		}
	}
	
	@Override
	public double sin(final BlockFace face) {
		switch (face) {
		case NORTH_EAST:
		case NORTH_WEST:
			return -Core.getCore().getMathUtil().getHalfRootOfTwo();
		case SOUTH_WEST:
		case SOUTH_EAST:
			return Core.getCore().getMathUtil().getHalfRootOfTwo();
		case NORTH:
			return -1;
		case SOUTH:
			return 1;
		default:
			return 0;
		}
	}
	
	@Override
	public int faceToYaw(final BlockFace face) {
		return Core.getCore().getMathUtil().wrapAngle(45 * faceToNotch(face));
	}
	
	@Override
	public BlockFace yawToFace(float yaw) {
		return yawToFace(yaw, true);
	}
	
	@Override
	public BlockFace yawToFace(float yaw, boolean useSubCardinalDirections) {
		if (useSubCardinalDirections) {
			return RADIAL[Math.round(yaw / 45f) & 0x7];
		} else {
			return AXIS[Math.round(yaw / 90f) & 0x3];
		}
	}
}
