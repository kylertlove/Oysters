package net.nerds.oysters.oysters;

import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.nerds.oysters.Oysters;

import java.util.Arrays;
import java.util.function.Supplier;

public class OystersManager {

    public static OysterBlock PLAIN_OYSTER = new OysterBlock();
    public static BlockEntityType<BlockEntity> OysterEntityType;
    public static final Identifier oysterContainerIdenifer = new Identifier(Oysters.MODID, "oyster_container");

    public static void init() {
        buildOysterEntity();
        initGui();
        buildOysters();
    }

    public static void buildOysters() {
        Registry.register(Registry.BLOCK, new Identifier(Oysters.MODID, "plain_oyster"), PLAIN_OYSTER);
        Registry.register(Registry.ITEM,
                new Identifier(Oysters.MODID, "plain_oyster"),
                new BlockItem(PLAIN_OYSTER, new Item.Settings().group(Oysters.oysterGroup)));
    }

    public static void buildOysterEntity() {

        OysterEntityType = Registry.register(Registry.BLOCK_ENTITY, new Identifier(Oysters.MODID, "plain_oyster"),
                BlockEntityType.Builder.create((Supplier<BlockEntity>) () -> {
                    return new OysterEntity(OysterEntityType);
                }, PLAIN_OYSTER).build(null));
    }

    public static void initGui() {
        ContainerProviderRegistry.INSTANCE.registerFactory(oysterContainerIdenifer, (syncid, identifier, player, buf) -> {
            return new OysterContainer(syncid, player.inventory, (OysterEntity) player.world.getBlockEntity(buf.readBlockPos()));
        });
    }
}
