package com.sample.packet.button;

import com.sample.packet.network.AbstractPacket;
import cpw.mods.fml.common.FMLLog;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentTranslation;

public class GuiButtonPacket extends AbstractPacket
{
	/*
	 *今回パケットで扱うデータ
	 */
	private int buttonId;

	/*
	 * PacketPipelineのencodeでnewInstance()するため, 引数なしコンストラクタが必要.
	 */
	public GuiButtonPacket()
	{
	}

	/*
	 * パケットの生成には通常こちらを用いる.
	 */
	public GuiButtonPacket(int _buttonId)
	{
		this.buttonId = _buttonId;
	}

	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer)
	{
		buffer.writeInt(buttonId);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer)
	{
		buttonId = buffer.readInt();
	}

	/*
	 * クライアント側のパケット処理を実行するメソッド
	 */
	@Override
	public void handleClientSide(EntityPlayer player)
	{
		// 今回は何もしない
	}

	/*
	 * サーバー側のパケット処理を実行するメソッド
	 */
	@Override
	public void handleServerSide(EntityPlayer player)
	{
		player.addChatMessage(new ChatComponentTranslation("Pressed " + String.valueOf(buttonId) + " Button"));
	}
}
