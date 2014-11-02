// @formatter:off
/*
 * Pexel Project - Minecraft minigame server platform. 
 * Copyright (C) 2014 Matej Kormuth <http://www.matejkormuth.eu>
 * 
 * This file is part of Pexel.
 * 
 * Pexel is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * 
 * Pexel is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 *
 */
// @formatter:on
package eu.matejkormuth.pexel.PexelCore.cinematics.v3meta;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import eu.matejkormuth.pexel.PexelCore.cinematics.V3Meta;
import eu.matejkormuth.pexel.PexelCore.cinematics.V3MetaType;

/**
 * @author Mato Kormuth
 * 
 */
public class V3MetaEntityInventory implements V3Meta {
	
	private final long	internalId;
	private final byte	slot;
	private final int	itemType;
	
	/**
	 * @param internalId
	 * @param slot
	 * @param type
	 */
	public V3MetaEntityInventory(final long internalId, final byte slot, final int type) {
		super();
		this.internalId = internalId;
		this.slot = slot;
		itemType = type;
	}
	
	@Override
	public void writeMeta(final DataOutputStream stream) throws IOException {
		stream.writeLong(internalId);
		stream.writeByte(slot);
		stream.writeInt(itemType);
	}
	
	public static V3MetaEntityInventory readMeta(final DataInputStream stream) throws IOException {
		final long internalId = stream.readLong();
		final byte slot = stream.readByte();
		final int type = stream.readInt();
		
		return new V3MetaEntityInventory(internalId, slot, type);
	}
	
	@Override
	public int getType() {
		return 4;
	}
	
	@Override
	public V3MetaType getMetaType() {
		return V3MetaType.V3MetaEntityInventory;
	}
	
	/**
	 * @return the internalId
	 */
	public long getInternalId() {
		return internalId;
	}
	
	/**
	 * @return the slot
	 */
	public byte getSlot() {
		return slot;
	}
	
	/**
	 * @return the itemType
	 */
	public int getItemType() {
		return itemType;
	}
}
