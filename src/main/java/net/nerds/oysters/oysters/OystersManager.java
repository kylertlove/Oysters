package net.nerds.oysters.oysters;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.nerds.oysters.Oysters;

public class OystersManager {

    public static OysterBlock PLAIN_OYSTER = new OysterBlock(buildOysterSettings());

    public static void buildOysters() {
        Registry.register(Registry.BLOCK, new Identifier(Oysters.MODID, "plain_oyster"), PLAIN_OYSTER);
        Registry.register(Registry.ITEM,
                new Identifier(Oysters.MODID, "plain_oyster"),
                new BlockItem(PLAIN_OYSTER, new Item.Settings().group(Oysters.oysterGroup)));
    }

    private static Block.Settings buildOysterSettings() {
        return FabricBlockSettings.of(Material.METAL)
                .breakByHand(true)
                .resistance(2.0f)
                .build();
    }
}
