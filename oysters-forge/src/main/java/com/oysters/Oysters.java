package com.oysters;

import com.oysters.oysters.OysterManager;
import com.oysters.utils.OysterGeneration;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Oysters.ID)
public class Oysters {

    public static final String ID = "oysters";
    private static final Logger log = LogManager.getLogger();
    public static ItemGroup oystersItemGroup = new OystersItemGroup();

    public Oysters() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::init);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientEvent);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void init(final FMLCommonSetupEvent event) {
        OysterGeneration.oysterGen();
    }

    private void clientEvent(final FMLClientSetupEvent event) {
        //RenderTypeLookup.setRenderLayer(OysterManager.oysterList.get(0), RenderType.func_228641_d_());
    }
}
