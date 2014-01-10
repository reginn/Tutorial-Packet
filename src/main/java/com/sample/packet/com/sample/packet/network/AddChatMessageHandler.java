package com.sample.packet.com.sample.packet.network;

import cpw.mods.fml.common.FMLCommonHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class AddChatMessageHandler extends SimpleChannelInboundHandler<SamplePacketMessage>
{
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, SamplePacketMessage msg) throws Exception
	{
		System.out.println(msg.blockID);
	}
}
