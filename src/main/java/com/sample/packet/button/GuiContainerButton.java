package com.sample.packet.button;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiContainerButton extends GuiContainer
{
	private static final ResourceLocation GUI_BACKGROUND = new ResourceLocation("button:textures/gui/container/base.png");

	public GuiContainerButton(InventoryPlayer inventoryPlayer)
	{
		super(new ContainerButton(inventoryPlayer));

		this.xSize = 176;
		this.ySize = 166;
	}

	@Override
	public void initGui()
	{
		super.initGui();
		int i = width  - xSize >> 1;
		int j = height - ySize >> 1;

		// ボタンを追加
		// GuiButton(ボタンID, ボタンの始点X, ボタンの始点Y, ボタンの幅, ボタンの高さ, ボタンに表示する文字列)
		buttonList.add(new GuiButton(0, i + 36, j + 16, 48, 20 , "Button 0"));
		buttonList.add(new GuiButton(1, i + 36, j + 40, 48, 20 , "Button 1"));
		buttonList.add(new GuiButton(2, i + 88, j + 16, 48, 20 , "Button 2"));
		buttonList.add(new GuiButton(3, i + 88, j + 40, 48, 20 , "Button 3"));
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int xMouse, int yMouse)
	{
		this.fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 0x404040);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float renderPartialTicks, int xMouse, int yMouse)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(GUI_BACKGROUND);
		int x = (this.width  - this.xSize) / 2;
		int y = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
	}

	@Override
	protected void actionPerformed(GuiButton guiButton)
	{
		if (!guiButton.enabled)
		{
			return;
		}

		SampleGuiButtonCore.packetPipeline.sendPacketToServer(new GuiButtonPacket(guiButton.id));
	}
}
