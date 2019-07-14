package net.nerds.oysters.Utils;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.CountDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.*;
import net.nerds.oysters.oysters.OysterBreed;

public class OysterWorldGen {
    public static void generate() {
        for (Biome biome : Registry.BIOME) {
            if (biome.getCategory() == Biome.Category.OCEAN) {
                biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(
                        new OysterGenerationFeature(DefaultFeatureConfig::deserialize, OysterBreed.BLEMISHED.getOysterBlock().getDefaultState()),
                        FeatureConfig.DEFAULT,
                        Decorator.COUNT_HEIGHTMAP_DOUBLE,
                        new CountDecoratorConfig(1)
                ));
            }
        }
    }
}
