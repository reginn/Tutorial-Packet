package com.sample.packet.com.sample.packet.network;


import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.internal.HandshakeCompletionHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraftforge.common.network.DimensionMessageHandler;
import net.minecraftforge.common.network.ForgeRuntimeCodec;

import java.util.EnumMap;

public class SampleNetworkHandler
{
	private static EnumMap<Side, FMLEmbeddedChannel> channelPair;

	public static void registerChannel(Side side)
	{
		channelPair = NetworkRegistry.INSTANCE.newChannel("SamplePacket", new SampleRuntimeCodec(), new HandshakeCompletionHandler());

		if (side == Side.SERVER)
		{
			FMLEmbeddedChannel serverChannel = channelPair.get(Side.SERVER);
			String handlerName = serverChannel.findChannelHandlerNameForType(SampleRuntimeCodec.class);
			serverChannel.pipeline().addAfter(handlerName, "AddChatHandler", new AddChatMessageHandler());
		}
	}
}
