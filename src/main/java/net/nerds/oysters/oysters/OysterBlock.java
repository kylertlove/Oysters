package net.nerds.oysters.oysters;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.EntityContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.ViewableWorld;
import net.minecraft.world.World;

import java.util.Arrays;

public class OysterBlock extends Block implements Waterloggable, BlockEntityProvider {

    private VoxelShape voxelShape = Block.createCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 8.0D, 13.0D);
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    private Identifier identifier;

    public OysterBlock(Identifier identifier) {
        super(FabricBlockSettings.of(Material.METAL)
                .breakByHand(true)
                .resistance(2.0f)
                .build());
        this.identifier = identifier;
        this.setDefaultState(getStateFactory().getDefaultState()
                .with(WATERLOGGED, true)
                .with(FACING, Direction.NORTH));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1, EntityContext entityContext_1) {
        return voxelShape;
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext itemPlacementContext) {
            FluidState fluidState = itemPlacementContext.getWorld().getFluidState(itemPlacementContext.getBlockPos());
            boolean waterLog = fluidState.matches(FluidTags.WATER) && fluidState.getLevel() == 8;
            return super.getPlacementState(itemPlacementContext)
                    .with(WATERLOGGED, waterLog)
                    .with(FACING, itemPlacementContext.getPlayerFacing().getOpposite());
    }

    @Override
    public boolean canPlaceAt(BlockState blockState_1, ViewableWorld viewableWorld_1, BlockPos blockPos_1) {
        return viewableWorld_1.getBlockState(blockPos_1.down()).getBlock() == Blocks.SAND;
    }

    @Override
    public BlockState rotate(BlockState blockState, BlockRotation rotation) {
        return blockState.with(FACING, rotation.rotate(blockState.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState blockState, BlockMirror mirror) {
        return blockState.rotate(mirror.getRotation(blockState.get(FACING)));
    }

    @Override
    public FluidState getFluidState(BlockState blockState_1) {
        return (Boolean)blockState_1.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(blockState_1);
    }

    @Override
    protected void appendProperties(StateFactory.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(WATERLOGGED, FACING);
    }

    @Override
    public BlockEntity createBlockEntity(BlockView blockView) {
        return new OysterEntity(OystersManager.oysterEntityTypeMap.get(identifier));
    }

    @Override
    public boolean activate(BlockState state, World world, BlockPos blockPos, PlayerEntity player, Hand hand, BlockHitResult blockHitResult) {
        if (!world.isClient) {
            ContainerProviderRegistry.INSTANCE.openContainer(getContainerIdentifer(), player, buf -> buf.writeBlockPos(blockPos));
        }
        return true;
    }

    private Identifier getContainerIdentifer() {
        return Arrays.stream(OysterBreed.values())
                .filter(oysterBreed -> oysterBreed.getName().equalsIgnoreCase(identifier.getPath()))
                .findFirst().get().getContainerIdentifier();
    }
}
