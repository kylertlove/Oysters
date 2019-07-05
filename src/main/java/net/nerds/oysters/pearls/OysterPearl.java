package net.nerds.oysters.pearls;

import net.minecraft.item.Item;
import net.nerds.oysters.Oysters;

public class OysterPearl extends Item {

    private String identifier;

    public OysterPearl(String name) {
        super(new Item.Settings().group(Oysters.oysterGroup));
        this.identifier = name;
    }

    public String getIdentifier() {
        return this.identifier;
    }
}
