package net.nerds.oysters.Utils;

import net.minecraft.screen.slot.Slot;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;

public class OutputSlot extends Slot {

    public OutputSlot(Inventory inventory, int invIndex, int x, int y) {
        super(inventory, invIndex, x, y);
    }

    @Override
    public boolean canInsert(ItemStack itemStack) {
        return false;
    }

    public ItemStack takeStack(int i) {
        return super.takeStack(i);
    }
}