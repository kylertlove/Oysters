package net.nerds.oysters.blocks;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.nerds.oysters.Oysters;
import net.nerds.oysters.oysters.OysterBreed;

import java.util.function.Supplier;

public class OysterBlockManager {

    public static OysterBasket oysterBasket =
            new OysterBasket(FabricBlockSettings.of(Material.WOOD).strength(2.0F, 2.0f).sounds(BlockSoundGroup.WOOD).build());
    public static Block blemishedPearlBlock = new Block(Block.Settings.of(Material.STONE, MaterialColor.STONE).strength(1.5F, 6.0F));
    public static Block cleanPearlBlock = new Block(Block.Settings.of(Material.STONE, MaterialColor.STONE).strength(1.5F, 6.0F));
    public static Block flawlessPearlBlock = new Block(Block.Settings.of(Material.STONE, MaterialColor.STONE).strength(1.5F, 6.0F));
    public static BlockEntityType<BlockEntity> OYSTER_BASKET_ENTITY_TYPE;
    public static final Identifier OYSTER_BASKET_CONTAINER_IDENTIFIER = new Identifier(Oysters.MODID, "oyster_basket_container");

    public static void init() {
        OYSTER_BASKET_ENTITY_TYPE = Registry.register(Registry.BLOCK_ENTITY, new Identifier(Oysters.MODID, "oyster_basket"),
                BlockEntityType.Builder.create((Supplier<BlockEntity>) () -> {
                    return new OysterBasketEntity(OYSTER_BASKET_ENTITY_TYPE);
                }, oysterBasket).build(null));

        ContainerProviderRegistry.INSTANCE.registerFactory(OYSTER_BASKET_CONTAINER_IDENTIFIER, (syncid, identifier, player, buf) -> {
            return new OysterBasketContainer(syncid, player.inventory, (OysterBasketEntity) player.world.getBlockEntity(buf.readBlockPos()));
        });

        Registry.register(Registry.BLOCK, new Identifier(Oysters.MODID, "oyster_basket"), oysterBasket);
        Registry.register(Registry.BLOCK, new Identifier(Oysters.MODID, "blemished_pearl_block"), blemishedPearlBlock);
        Registry.register(Registry.ITEM,
                new Identifier(Oysters.MODID, "blemished_pearl_block"),
                new BlockItem(blemishedPearlBlock, new Item.Settings().group(Oysters.oysterGroup)));
        Registry.register(Registry.BLOCK, new Identifier(Oysters.MODID, "clean_pearl_block"), cleanPearlBlock);
        Registry.register(Registry.ITEM,
                new Identifier(Oysters.MODID, "clean_pearl_block"),
                new BlockItem(cleanPearlBlock, new Item.Settings().group(Oysters.oysterGroup)));
        Registry.register(Registry.BLOCK, new Identifier(Oysters.MODID, "flawless_pearl_block"), flawlessPearlBlock);
        Registry.register(Registry.ITEM,
                new Identifier(Oysters.MODID, "flawless_pearl_block"),
                new BlockItem(flawlessPearlBlock, new Item.Settings().group(Oysters.oysterGroup)));
        Registry.register(Registry.ITEM,
                new Identifier(Oysters.MODID, "oyster_basket"),
                new BlockItem(oysterBasket, new Item.Settings().group(Oysters.oysterGroup)));
    }
}
