package com.sample.packet.button;

import com.sample.packet.button.network.GuiButtonPacket;
import com.sample.packet.button.network.PacketPipeline;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLEventChannel;
import cpw.mods.fml.common.network.FMLNetworkEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;

@Mod(modid = SampleGuiButtonCore.MODID, version = SampleGuiButtonCore.VERSION)
public class SampleGuiButtonCore
{
	public static final String MODID = "GuiButton";
	public static final String VERSION = "0.0.0";

	@Mod.Instance("GuiButton")
	public static SampleGuiButtonCore instance;

	public static Block blockGuiButton;

	public static final PacketPipeline packetPipeline = new PacketPipeline();

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		blockGuiButton = (new BlockGuiButton())
				.setBlockName("blockGuiButton")
				.setBlockTextureName("stone")
				.setCreativeTab(CreativeTabs.tabBlock);

		GameRegistry.registerBlock(blockGuiButton, "blockGuiButton");
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiButtonHandler());
		packetPipeline.init(MODID);
		packetPipeline.registerPacket(GuiButtonPacket.class);
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		packetPipeline.postInit();
	}
}
