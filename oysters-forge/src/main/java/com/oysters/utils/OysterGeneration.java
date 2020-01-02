package com.oysters.utils;

import com.oysters.oysters.OysterManager;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class OysterGeneration {

	public static void oysterGen() {
		for(Biome biome : ForgeRegistries.BIOMES) {
			if(biome.getCategory() == Biome.Category.OCEAN) {
				BlockClusterFeatureConfig featureConfigGrass = (new BlockClusterFeatureConfig.Builder((new WeightedBlockStateProvider()).func_227407_a_(OysterManager.oysterList.get(0).getDefaultState(), 1), new SimpleBlockPlacer())).func_227315_a_(64).func_227322_d_();

				biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
						Feature.field_227247_y_.func_225566_b_(featureConfigGrass).func_227228_a_(Placement.COUNT_HEIGHTMAP_DOUBLE.func_227446_a_(new FrequencyConfig(2))));
			}
		}
	}
}
