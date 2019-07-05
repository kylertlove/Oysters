package net.nerds.oysters.blocks;

import net.minecraft.container.Container;
import net.minecraft.container.Slot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.nerds.oysters.Utils.InsertSlot;
import net.nerds.oysters.Utils.OutputSlot;
import net.nerds.oysters.Utils.OysterBreedUtility;
import net.nerds.oysters.oysters.OysterBlockItem;

public class OysterBasketContainer extends Container {
    public final Inventory inventory;
    public final PlayerInventory playerInventory;
    public final World world;

    public OysterBasketContainer(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(null, syncId);
        this.inventory = inventory;
        this.playerInventory = playerInventory;
        this.world = playerInventory.player.world;

        int invCountNum = 0;

        //inventory, Slot #, x, y
        this.addSlot(new InsertSlot(inventory, invCountNum++, 8, 118, OysterBreedUtility.getAllOysterBlockItem()));
        this.addSlot(new InsertSlot(inventory, invCountNum++, 44, 118, OysterBreedUtility.getAllResouceItems()));

        for(int outInvIndex = 0; outInvIndex < 5; ++outInvIndex) {
            for(int column = 0; column < 9; ++column) {
                this.addSlot(new OutputSlot(inventory, invCountNum++, (8 + column * 18), 18 + (outInvIndex * 18)));
            }
        }

        //player inventory
        int playerInvIndex;
        for(playerInvIndex = 0; playerInvIndex < 3; ++playerInvIndex) {
            for(int var4 = 0; var4 < 9; ++var4) {
                this.addSlot(new Slot(playerInventory, var4 + playerInvIndex * 9 + 9, 8 + var4 * 18, 142 + playerInvIndex * 18));
            }
        }
        for(playerInvIndex = 0; playerInvIndex < 9; ++playerInvIndex) {
            this.addSlot(new Slot(playerInventory, playerInvIndex, 8 + playerInvIndex * 18, 200));
        }
    }

    @Override
    public boolean canUse(PlayerEntity playerEntity_1) {
        return this.inventory.canPlayerUseInv(playerEntity_1);
    }

    @Override
    public void close(PlayerEntity playerEntity_1) {
        super.close(playerEntity_1);
        this.inventory.onInvClose(playerEntity_1);
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public ItemStack transferSlot(PlayerEntity player, int slotIndex) {
        Slot initSlot = this.slotList.get(slotIndex);
        if (initSlot.getStack() == ItemStack.EMPTY) {
            return ItemStack.EMPTY;
        }
        ItemStack originalItem = initSlot.getStack().copy();

        if (initSlot.inventory == player.inventory) {
            ItemStack clickedItem = initSlot.getStack();
            Slot oysterInputSlot = this.slotList.get(0);
            Slot resourceInputSlot = this.slotList.get(1);
            if(clickedItem.getItem() instanceof OysterBlockItem && !oysterInputSlot.hasStack()) {
                oysterInputSlot.setStack(clickedItem);
                initSlot.setStack(ItemStack.EMPTY);
                return originalItem;
            } else if(OysterBreedUtility.isAResource(clickedItem.getItem()) && !resourceInputSlot.hasStack()) {
                resourceInputSlot.setStack(clickedItem);
                initSlot.setStack(ItemStack.EMPTY);
                return originalItem;
            }
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


}
