package com.oysters;

import com.oysters.OysterBasket.OysterBasket;
import com.oysters.OysterBasket.OysterBasketTile;
import com.oysters.items.OysterShucker;
import com.oysters.oysters.Oyster;
import com.oysters.oysters.OysterManager;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
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

    public static OysterShucker oysterShucker = new OysterShucker();
    public static OysterBasket oysterBasket = new OysterBasket();
    public static TileEntityType<OysterBasketTile> oysterBasketEntityType = buildBasketEntityType();
    public static Block blemishedPearlBlock = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5F, 6.0F))
            .setRegistryName(new ResourceLocation(Oysters.ID, "blemished_pearl_block"));
    public static Block cleanPearlBlock = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5F, 6.0F))
            .setRegistryName(new ResourceLocation(Oysters.ID, "clean_pearl_block"));
    public static Block flawlessPearlBlock = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5F, 6.0F))
            .setRegistryName(new ResourceLocation(Oysters.ID, "flawless_pearl_block"));

    @SubscribeEvent
    public static void blocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
        OysterManager.oysterList.forEach(oyster -> blockRegistryEvent.getRegistry().register(oyster));
        blockRegistryEvent.getRegistry().register(oysterBasket);
        blockRegistryEvent.getRegistry().register(blemishedPearlBlock);
        blockRegistryEvent.getRegistry().register(cleanPearlBlock);
        blockRegistryEvent.getRegistry().register(flawlessPearlBlock);
    }

    @SubscribeEvent
    public static void itemRegistry(final RegistryEvent.Register<Item> itemRegisterEvent) {
        OysterManager.oysterList.forEach(oyster -> itemRegisterEvent.getRegistry().register(
                    new BlockItem(oyster, new Item.Properties()
                        .group(Oysters.oystersItemGroup).maxStackSize(8))
                        .setRegistryName(Objects.requireNonNull(oyster.getRegistryName()))));
        OysterManager.pearlList.forEach(item -> itemRegisterEvent.getRegistry().register(item));
        itemRegisterEvent.getRegistry().register(new BlockItem(oysterBasket,
                new Item.Properties().group(Oysters.oystersItemGroup))
                .setRegistryName(Objects.requireNonNull(oysterBasket.getRegistryName())));
        itemRegisterEvent.getRegistry().register(oysterShucker);
        itemRegisterEvent.getRegistry().register(new BlockItem(blemishedPearlBlock,
                new Item.Properties().group(Oysters.oystersItemGroup))
                .setRegistryName(Objects.requireNonNull(blemishedPearlBlock.getRegistryName())));
        itemRegisterEvent.getRegistry().register(new BlockItem(cleanPearlBlock,
                new Item.Properties().group(Oysters.oystersItemGroup))
                .setRegistryName(Objects.requireNonNull(cleanPearlBlock.getRegistryName())));
        itemRegisterEvent.getRegistry().register(new BlockItem(flawlessPearlBlock,
                new Item.Properties().group(Oysters.oystersItemGroup))
                .setRegistryName(Objects.requireNonNull(flawlessPearlBlock.getRegistryName())));
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
