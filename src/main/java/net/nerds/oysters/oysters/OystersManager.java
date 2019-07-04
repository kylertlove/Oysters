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
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class OystersManager {

    public static Map<Identifier, BlockEntityType<BlockEntity>> oysterEntityTypeMap = new HashMap<>();

    public static void init() {
        buildOysterEntityTypeMap();
        Arrays.stream(OysterBreed.values())
                .forEach(oysterBreed -> {
                    //Entity builds
                    oysterEntityTypeMap.put(oysterBreed.getIdentifier(),
                            Registry.register(Registry.BLOCK_ENTITY, oysterBreed.getIdentifier(),
                                    BlockEntityType.Builder.create((Supplier<BlockEntity>) () -> {
                                                return new OysterEntity(oysterEntityTypeMap.get(oysterBreed.getIdentifier()));
                                            },
                                            oysterBreed.getOysterBlock()).build(null)));

                    //Gui builds
                    ContainerProviderRegistry.INSTANCE.registerFactory(oysterBreed.getContainerIdentifier(), (syncid, identifier, player, buf) -> {
                        return new OysterContainer(syncid, player.inventory, (OysterEntity) player.world.getBlockEntity(buf.readBlockPos()));
                    });

                    //block/Item builds
                    Registry.register(Registry.BLOCK, oysterBreed.getIdentifier(), oysterBreed.getOysterBlock());
                    Registry.register(Registry.ITEM,
                            oysterBreed.getIdentifier(),
                            oysterBreed.getOysterBlockItem());
                });
    }
    private static void buildOysterEntityTypeMap() {
        Arrays.stream(OysterBreed.values())
                .forEach(oysterBreed -> oysterEntityTypeMap.put(oysterBreed.getIdentifier(), null));
    }

}
