package net.nerds.oysters.oysters;

import net.minecraft.screen.slot.Slot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.Generic3x3ContainerScreenHandler;
import net.minecraft.world.World;
import net.nerds.oysters.Utils.OutputSlot;

public class OysterContainer extends Generic3x3ContainerScreenHandler {

    public final Inventory inventory;
    public final PlayerInventory playerInventory;
    public final World world;

    public OysterContainer(int syncId, PlayerInventory playerInventory, Inventory inventory ) {
        super(syncId, playerInventory, inventory);
        this.inventory = inventory;
        this.playerInventory = playerInventory;
        this.world = playerInventory.player.world;

        //Oyster Inventory
        this.addSlot(new OutputSlot(inventory, 0, 80, 33));

        //player inventory
        int playerInvIndex;
        for(playerInvIndex = 0; playerInvIndex < 3; ++playerInvIndex) {
            for(int var4 = 0; var4 < 9; ++var4) {
                this.addSlot(new Slot(playerInventory, var4 + playerInvIndex * 9 + 9, 8 + var4 * 18, 65 + playerInvIndex * 18));
            }
        }
        for(playerInvIndex = 0; playerInvIndex < 9; ++playerInvIndex) {
            this.addSlot(new Slot(playerInventory, playerInvIndex, 8 + playerInvIndex * 18, 123));
        }
    }

    @Override
    public ItemStack transferSlot(PlayerEntity player, int slotIndex) {
        Slot initSlot = this.slots.get(slotIndex);
        if (initSlot.getStack() == ItemStack.EMPTY) {
            return ItemStack.EMPTY;
        }
        ItemStack originalItem = initSlot.getStack().copy();
        if (initSlot.inventory == player.inventory) {
            return ItemStack.EMPTY;
        } else {
            if(playerInventory.insertStack(originalItem)) {
                initSlot.setStack(ItemStack.EMPTY);
                return originalItem;
            } else {
                return ItemStack.EMPTY;
            }
        }
    }

    @Override
    public boolean canUse(PlayerEntity playerEntity) {
        return this.inventory.canPlayerUse(playerEntity);
    }

    public Inventory getInventory() {
        return this.inventory;
    }
}
