package com.oysters;

import com.oysters.OysterBasket.OysterBasket;
import com.oysters.OysterBasket.OysterBasketTile;
import com.oysters.oysters.Oyster;
import com.oysters.oysters.OysterManager;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class OystersRegistration {

    public static OysterBasket oysterBasket = new OysterBasket();

    public static TileEntityType<OysterBasketTile> oysterBasketEntityType = buildBasketEntityType();

    @SubscribeEvent
    public static void blocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
        OysterManager.oysterList.forEach(oyster -> blockRegistryEvent.getRegistry().register(oyster));
        blockRegistryEvent.getRegistry().register(oysterBasket);
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
        itemRegisterEvent.getRegistry().register(new BlockItem(oysterBasket, new Item.Properties().group(Oysters.oystersItemGroup)));
    }

    @SubscribeEvent
    public static void tileRegistry(final RegistryEvent.Register<TileEntityType<?>> event) {
        event.getRegistry().register(oysterBasketEntityType);
    }

    private static TileEntityType<OysterBasketTile> buildBasketEntityType() {
        TileEntityType<OysterBasketTile> type = TileEntityType.Builder.create(OysterBasketTile::new).build(null);
        type.setRegistryName(new ResourceLocation(Oysters.ID, "oyster_basket_entity_type"));
        return type;
    }
}
