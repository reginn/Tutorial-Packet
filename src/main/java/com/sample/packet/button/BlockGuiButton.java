package com.sample.packet.button;

import com.sample.gui.container.SampleGuiContainerCore;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class BlockGuiButton extends Block
{
	public BlockGuiButton()
	{
		super(Material.rock);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float dx, float dy, float dz)
	{
		if (!world.isRemote)
		{
			player.openGui(SampleGuiButtonCore.instance, 0, world, x, y, z);
		}
		return true;
	}
}
