package com.sample.packet.button;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;

@Mod(modid = SampleGuiButtonCore.MODID, version = SampleGuiButtonCore.VERSION)
public class SampleGuiButtonCore
{
	public static final String MODID   = "GuiButton";
	public static final String VERSION = "0.0.0";

	@Mod.Instance("GuiButton")
	public static SampleGuiButtonCore instance;

	public static Block blockGuiButton;

	/*
	 * ModIDを渡して新しいチャネルを取得する.
	 * このインスタンスは引数で与えたModIDのMod専用のチャネルになる.
	 */
	public static final SimpleNetworkWrapper packetDispatcher = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);

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

		/*
		 * パケットとパケットを処理するハンドラを登録するメソッド.
		 * 引数は
		 * パケットハンドラ(IMessageHandler実装), パケット(IMessage実装), パケットID, パケットを受け取る側(CLIENTかSERVER)
		 * パケットIDは256まで, 同ModID内で重複していなければよい.
		 */
		packetDispatcher.registerMessage(GuiButtonPacketHandler.class, GuiButtonPacket.class, 0, Side.SERVER);
	}

}
