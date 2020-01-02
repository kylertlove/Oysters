package net.nerds.oysters.Utils;

import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.CountDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.*;
import net.nerds.oysters.oysters.OysterBreed;

public class OysterWorldGen {
    public static void generate() {

        Registry.BIOME.forEach(OysterWorldGen::createOysterFeature);

        RegistryEntryAddedCallback.event(Registry.BIOME).register((i, identifier, biome) -> createOysterFeature(biome));
    }

    public static void createOysterFeature(Biome biome) {
            if (biome.getCategory() == Biome.Category.OCEAN) {

                biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        new ConfiguredFeature(
                                new OysterGenerationFeature(DefaultFeatureConfig::deserialize, OysterBreed.BLEMISHED.getOysterBlock().getDefaultState()),
                                FeatureConfig.DEFAULT
                        ));
            }
        }
}
