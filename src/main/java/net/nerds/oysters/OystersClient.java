package net.nerds.oysters;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;
import net.minecraft.util.math.BlockPos;
import net.nerds.oysters.oysters.OysterContainer;
import net.nerds.oysters.oysters.OysterEntity;
import net.nerds.oysters.oysters.OysterGui;
import net.nerds.oysters.oysters.OystersManager;

public class OystersClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        registerGui();
    }

    private static void registerGui() {
        ScreenProviderRegistry.INSTANCE.registerFactory(OystersManager.oysterContainerIdenifer, ((syncid, identifier, player, buf) -> {
            BlockPos pos = buf.readBlockPos();
            OysterEntity oysterEntity = (OysterEntity) player.world.getBlockEntity(pos);
            return new OysterGui(oysterEntity,
                    new OysterContainer(syncid, player.inventory, oysterEntity),
                    "Oyster", "plain_oyster");
        }));
    }
}
