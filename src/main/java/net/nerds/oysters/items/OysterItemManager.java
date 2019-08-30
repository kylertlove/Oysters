package net.nerds.oysters.items;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.nerds.oysters.Oysters;

public class OysterItemManager {

    public static PearlNecklace pearlNecklace = new PearlNecklace();


    public static void init() {
        Registry.register(Registry.ITEM, new Identifier(Oysters.MODID, "pearl_necklace"), pearlNecklace);
    }
}
