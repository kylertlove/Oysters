package com.oysters.items;

import com.oysters.Oysters;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class OysterShucker extends Item {

    public OysterShucker() {
        super(new Item.Properties()
                .group(Oysters.oystersItemGroup));
        this.setRegistryName(new ResourceLocation(Oysters.ID, "oyster_shucker"));
    }
}
