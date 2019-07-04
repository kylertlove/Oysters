package net.nerds.oysters.pearls;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.nerds.oysters.Oysters;

public class OysterPearlManager {

    public static void init() {
        Registry.register(Registry.ITEM, new Identifier(Oysters.MODID, "plain_pearl"), new Item(new Item.Settings().group(Oysters.oysterGroup)));
        Registry.register(Registry.ITEM, new Identifier(Oysters.MODID, "bone_meal_pearl"), new Item(new Item.Settings().group(Oysters.oysterGroup)));
        Registry.register(Registry.ITEM, new Identifier(Oysters.MODID, "coal_pearl"), new Item(new Item.Settings().group(Oysters.oysterGroup)));
        Registry.register(Registry.ITEM, new Identifier(Oysters.MODID, "iron_pearl"), new Item(new Item.Settings().group(Oysters.oysterGroup)));
        Registry.register(Registry.ITEM, new Identifier(Oysters.MODID, "gold_pearl"), new Item(new Item.Settings().group(Oysters.oysterGroup)));
        Registry.register(Registry.ITEM, new Identifier(Oysters.MODID, "diamond_pearl"), new Item(new Item.Settings().group(Oysters.oysterGroup)));
        Registry.register(Registry.ITEM, new Identifier(Oysters.MODID, "lapis_pearl"), new Item(new Item.Settings().group(Oysters.oysterGroup)));
        Registry.register(Registry.ITEM, new Identifier(Oysters.MODID, "emerald_pearl"), new Item(new Item.Settings().group(Oysters.oysterGroup)));
    }
}
