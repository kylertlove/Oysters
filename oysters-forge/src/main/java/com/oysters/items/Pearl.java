package com.oysters.items;

import com.oysters.Oysters;
import net.minecraft.item.Item;

public class Pearl extends Item {

    public Pearl(String name) {
        super(new Item.Properties().group(Oysters.oystersItemGroup));
        this.setRegistryName(Oysters.ID, name);
    }
}
