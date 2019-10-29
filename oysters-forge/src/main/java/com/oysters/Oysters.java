package com.oysters;

import com.oysters.oysters.OysterManager;
import com.oysters.oysters.OysterTile;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;
import java.util.function.Supplier;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Oysters.ID)
public class Oysters {
    // Directly reference a log4j logger.
    public static final String ID = "oysters";
    private static final Logger log = LogManager.getLogger();
    public static ItemGroup oystersItemGroup = new OystersItemGroup();

    public Oysters() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::init);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientEvent);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void init(final FMLCommonSetupEvent event) {
    }

    private void clientEvent(final FMLClientSetupEvent event) {
    }

    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            OysterManager.oysterBlockList.forEach(oyster -> blockRegistryEvent.getRegistry().register(oyster));
        }

        @SubscribeEvent
        public static void itemRegistry(final RegistryEvent.Register<Item> itemRegisterEvent) {
            OysterManager.oysterBlockList.forEach(oyster -> itemRegisterEvent.getRegistry().register(
                    new BlockItem(oyster, new Item.Properties()
                                    .group(oystersItemGroup))
                                    .setRegistryName(Objects.requireNonNull(oyster.getRegistryName()))
            ));
        }

        @SubscribeEvent
        public static void tileRegistry(final RegistryEvent.Register<TileEntityType<?>> event) {
            OysterManager.oysterBlockList.forEach(oyster -> {
                TileEntityType<?> type = TileEntityType.Builder.create(OysterTile::new).build(null);
                type.setRegistryName(Objects.requireNonNull(oyster.getRegistryName()));
                event.getRegistry().register(type);
            });
        }
    }
}
