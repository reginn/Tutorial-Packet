package com.sample.packet.button;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

public class GuiButtonPacket implements IMessage
{
	/*
	 *今回パケットで扱うデータ
	 */
	private byte buttonId;

	public GuiButtonPacket()
	{
	}

	/*
	 * パケットの生成には通常こちらを用いる.
	 */
	public GuiButtonPacket(int _buttonId)
	{
		this.buttonId = (byte)_buttonId;
	}

	/*
	 * パケットのバイト列からデータを読みとる.
	 */
	@Override
	public void fromBytes(ByteBuf buffer)
	{
		buttonId = buffer.readByte();
	}

	/*
	 * パケットのバイト列へデータを書き込む.
	 */
	@Override
	public void toBytes(ByteBuf buffer)
	{
		buffer.writeByte(buttonId);
	}

	/*
	 * PacketHandlerでパケットのデータを参照するためのメソッド.
	 * 必要に応じてgetter, setter, 他を用意する.
	 */
	public byte getButtonId()
	{
		return buttonId;
	}

}
