package com.oysters;

import com.oysters.oysters.OysterManager;
import com.oysters.oysters.OysterTile;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class OystersRegistration {
    @SubscribeEvent
    public static void blocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
        OysterManager.oysterList.forEach(oyster -> blockRegistryEvent.getRegistry().register(oyster));
    }

    @SubscribeEvent
    public static void itemRegistry(final RegistryEvent.Register<Item> itemRegisterEvent) {
        OysterManager.oysterList.forEach(oyster -> itemRegisterEvent.getRegistry().register(
                    new BlockItem(oyster, new Item.Properties()
                            .group(Oysters.oystersItemGroup)
                            .maxStackSize(8))
                        .setRegistryName(Objects.requireNonNull(oyster.getRegistryName()))
                ));
        OysterManager.pearlList.forEach(item -> itemRegisterEvent.getRegistry().register(item));
    }

    @SubscribeEvent
    public static void tileRegistry(final RegistryEvent.Register<TileEntityType<?>> event) {
        OysterManager.oysterList.forEach(oyster -> {
            TileEntityType<?> type = TileEntityType.Builder.create(OysterTile::new).build(null);
            type.setRegistryName(Objects.requireNonNull(oyster.getRegistryName()));
            event.getRegistry().register(type);
        });
    }
}
