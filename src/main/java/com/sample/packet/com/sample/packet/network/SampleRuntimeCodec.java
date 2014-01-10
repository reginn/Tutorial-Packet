package com.sample.packet.com.sample.packet.network;

import cpw.mods.fml.common.network.FMLIndexedMessageToMessageCodec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

public class SampleRuntimeCodec extends FMLIndexedMessageToMessageCodec<SamplePacketMessage>
{
	public SampleRuntimeCodec()
	{
		addDiscriminator(1, SamplePacketMessage.class);
	}

	@Override
	public void encodeInto(ChannelHandlerContext ctx, SamplePacketMessage msg, ByteBuf target) throws Exception
	{
		msg.toBytes(target);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf source, SamplePacketMessage msg)
	{
		msg.fromBytes(source);
	}
}
