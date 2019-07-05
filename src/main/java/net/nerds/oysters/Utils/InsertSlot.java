package net.nerds.oysters.Utils;

import net.minecraft.container.Slot;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.Arrays;

public class InsertSlot extends Slot {

    private Item[] allowedInserts;

    public InsertSlot(Inventory inventory, int invIndex, int x, int y, Item[] allowedInserts) {
        super(inventory, invIndex, x, y);
        this.allowedInserts = allowedInserts;
    }

    @Override
    public boolean canInsert(ItemStack itemStack) {
        return Arrays.stream(allowedInserts)
                .anyMatch(s -> itemStack.getItem() == s);
    }

    public ItemStack takeStack(int i) {
        return super.takeStack(i);
    }
}