package net.nerds.oysters.Utils;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.nerds.oysters.oysters.OysterBreed;

import java.util.Random;
import java.util.function.Function;

public class OysterGenerationFeature extends Feature<DefaultFeatureConfig> {

    public OysterGenerationFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> config, BlockState state) {
        super(config);
    }

    @Override
    public boolean generate(IWorld world, ChunkGenerator<? extends ChunkGeneratorConfig> gen, Random random, BlockPos pos, DefaultFeatureConfig config) {
        int placed = 0;
        int int_3 = random.nextInt(8) - random.nextInt(8);
        int int_4 = random.nextInt(8) - random.nextInt(8);
        int int_5 = world.getTopY(Heightmap.Type.OCEAN_FLOOR, pos.getX() + int_3, pos.getZ() + int_4);
        BlockPos blockPos_2 = new BlockPos(pos.getX() + int_3, int_5, pos.getZ() + int_4);
        BlockState blockState = OysterBreed.BLEMISHED.getOysterBlock().getDefaultState();
        if (world.getBlockState(blockPos_2).getBlock() == Blocks.WATER && blockState.canPlaceAt(world, blockPos_2)) {
            world.setBlockState(blockPos_2, blockState, 2);
            placed += 2;
        }
        return placed > 0;
    }
}
