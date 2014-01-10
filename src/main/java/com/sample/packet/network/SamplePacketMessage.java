package com.sample.packet.network;

import io.netty.buffer.ByteBuf;

public class SamplePacketMessage {

	public int blockID;

	public SamplePacketMessage()
	{
	}

	public SamplePacketMessage(int blockID)
	{
		this.blockID = blockID;
	}

	void toBytes(ByteBuf bytes)
	{
		bytes.writeInt(this.blockID);
	}

	void fromBytes(ByteBuf bytes)
	{
		this.blockID = bytes.readInt();
	}
}
