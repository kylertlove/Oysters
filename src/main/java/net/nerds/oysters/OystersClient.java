package net.nerds.oysters;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;
import net.minecraft.util.math.BlockPos;
import net.nerds.oysters.blocks.OysterBasketContainer;
import net.nerds.oysters.blocks.OysterBasketEntity;
import net.nerds.oysters.blocks.OysterBasketGui;
import net.nerds.oysters.blocks.OysterBlockManager;
import net.nerds.oysters.oysters.*;

import java.util.Arrays;

public class OystersClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        Arrays.stream(OysterBreed.values())
                .forEach(oysterBreed -> {
                    ScreenProviderRegistry.INSTANCE.registerFactory(oysterBreed.getContainerIdentifier(), ((syncid, identifier, player, buf) -> {
                        BlockPos pos = buf.readBlockPos();
                        OysterEntity oysterEntity = (OysterEntity) player.world.getBlockEntity(pos);
                        return new OysterGui(oysterEntity,
                                new OysterContainer(syncid, player.inventory, oysterEntity),
                                oysterBreed.getName(), "block.oysters."+ oysterBreed.getName());
                    }));
                });
        ScreenProviderRegistry.INSTANCE.registerFactory(OysterBlockManager.OYSTER_BASKET_CONTAINER_IDENTIFIER, ((syncid, identifier, player, buf) -> {
            BlockPos pos = buf.readBlockPos();
            OysterBasketEntity oysterBasketEntity = (OysterBasketEntity) player.world.getBlockEntity(pos);
            return new OysterBasketGui(oysterBasketEntity,
                    new OysterBasketContainer(syncid, player.inventory, oysterBasketEntity),
                    "block.oysters.oyster_basket", "block.oysters.oyster_basket");
        }));
    }
}
