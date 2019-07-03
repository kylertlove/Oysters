package net.nerds.oysters.oysters;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Waterloggable;
import net.minecraft.entity.EntityContext;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.IWorld;

public class OysterBlock extends Block implements Waterloggable {

    private VoxelShape voxelShape = Block.createCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 4.0D, 13.0D);
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    public OysterBlock(Settings blockSettings) {
        super(blockSettings);
        this.setDefaultState(getStateFactory().getDefaultState().with(WATERLOGGED, true));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1, EntityContext entityContext_1) {
        return voxelShape;
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext itemPlacementContext) {
            FluidState fluidState = itemPlacementContext.getWorld().getFluidState(itemPlacementContext.getBlockPos());
            boolean waterLog = fluidState.matches(FluidTags.WATER) && fluidState.getLevel() == 8;
            return super.getPlacementState(itemPlacementContext).with(WATERLOGGED, waterLog);
    }

    @Override
    public FluidState getFluidState(BlockState blockState_1) {
        return (Boolean)blockState_1.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(blockState_1);
    }

    @Override
    protected void appendProperties(StateFactory.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(WATERLOGGED);
    }
}
