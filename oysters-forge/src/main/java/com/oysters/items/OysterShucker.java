package com.oysters.items;

import com.oysters.Oysters;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class OysterShucker extends Item {

    public OysterShucker() {
        super(new Item.Properties()
                .maxDamage(450)
                .group(Oysters.oystersItemGroup));
        this.setRegistryName(new ResourceLocation(Oysters.ID, "oyster_shucker"));
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        ItemStack itemCopy = itemStack.copy();
        if(itemCopy.attemptDamageItem(1, random, null)){
            return ItemStack.EMPTY;
        }
        return itemCopy;
    }
}
