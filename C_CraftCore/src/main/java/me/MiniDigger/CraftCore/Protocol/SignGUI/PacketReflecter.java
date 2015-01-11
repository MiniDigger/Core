package me.MiniDigger.CraftCore.Protocol.SignGUI;

public class PacketReflecter extends Reflecter {

	public PacketReflecter(String packet) {
		super(NMSUtils.getPacket(packet));
	}

}
