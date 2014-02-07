package com.sample.packet.button;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerButton extends Container
{
	public ContainerButton(InventoryPlayer inventoryPlayer)
	{
		for (int slotCol = 0; slotCol < 3; ++slotCol)
		{
			for (int slotRow = 0; slotRow < 9; ++slotRow)
			{
				this.addSlotToContainer(new Slot(inventoryPlayer, slotRow + slotCol * 9 + 9, 8 + slotRow * 18, 84 + slotCol * 18));
			}
		}

		for (int slotRow = 0; slotRow < 9; ++slotRow)
		{
			this.addSlotToContainer(new Slot(inventoryPlayer, slotRow, 8 + slotRow * 18, 142));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityPlayer)
	{
		return true;
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex)
	{
		Slot slot = (Slot)this.inventorySlots.get(slotIndex);
		ItemStack srcItemStack = null;

		if (slot != null && slot.getHasStack())
		{
			ItemStack destItemStack = slot.getStack();
			srcItemStack = destItemStack.copy();

			if (slotIndex < 27 && !this.mergeItemStack(destItemStack, 27, 36, false))
			{
				return null;
			}

			if (slotIndex >= 27 && !this.mergeItemStack(destItemStack, 0, 27, false))
			{
				return null;
			}

			if (destItemStack.stackSize == 0)
			{
				slot.putStack((ItemStack)null);
			}
			else
			{
				slot.onSlotChanged();
			}
		}

		return srcItemStack;
	}
}
