package com.sample.packet.button;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.util.ChatComponentTranslation;

/*
 * パケットの処理を行うメソッド
 */
public class GuiButtonPacketHandler implements IMessageHandler<GuiButtonPacket, IMessage>
{
	@Override
	public IMessage onMessage(GuiButtonPacket packet, MessageContext ctx)
	{
		/*
		 * クリックしたボタンのIDをチャット欄に表示する.
		 */
		ctx.getServerHandler().playerEntity.addChatMessage(new ChatComponentTranslation("Pressed " + String.valueOf(packet.getButtonId()) + " Button"));

		/*
		 * 応答用のパケットを返す場合は返す. 今回は返さないのでnullを返す.
		 */
		return null;
	}
}
