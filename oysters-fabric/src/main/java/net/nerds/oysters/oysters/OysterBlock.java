package net.nerds.oysters.oysters;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EntityContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class OysterBlock extends Block implements Waterloggable, BlockEntityProvider {

    private VoxelShape voxelShape = Block.createCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 8.0D, 13.0D);
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    private OysterBreed oysterBreed;

    public OysterBlock() {
        super(FabricBlockSettings.of(Material.STONE)
                .strength(1.5F, 6.0F)
                .build());
        this.setDefaultState(getStateManager().getDefaultState()
                .with(WATERLOGGED, true)
                .with(FACING, Direction.NORTH));
    }

    public void setOysterBreed(OysterBreed breed) {
        this.oysterBreed = breed;
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
    public BlockState getStateForNeighborUpdate(BlockState blockState_1, Direction direction_1, BlockState blockState_2, IWorld iWorld_1, BlockPos blockPos_1, BlockPos blockPos_2) {
        if (!blockState_1.canPlaceAt(iWorld_1, blockPos_1)) {
            return Blocks.AIR.getDefaultState();
        } else {
            if ((Boolean)blockState_1.get(WATERLOGGED)) {
                iWorld_1.getFluidTickScheduler().schedule(blockPos_1, Fluids.WATER, Fluids.WATER.getTickRate(iWorld_1));
            }

            return super.getStateForNeighborUpdate(blockState_1, direction_1, blockState_2, iWorld_1, blockPos_1, blockPos_2);
        }
    }

    @Override
    public boolean canPlaceAt(BlockState blockState_1, WorldView viewableWorld_1, BlockPos blockPos_1) {
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
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(WATERLOGGED, FACING);
    }

    @Override
    public BlockEntity createBlockEntity(BlockView blockView) {
        return new OysterEntity(OystersManager.oysterEntityTypeMap.get(oysterBreed.getIdentifier()), oysterBreed);
    }

    @Override
    public Identifier getDropTableId() {
        Identifier identifier = Registry.BLOCK.getId(this);
        return new Identifier(identifier.getNamespace(), "blocks/" + identifier.getPath());
    }

    @Override
    public void onBlockRemoved(BlockState blockState_1, World world_1, BlockPos blockPos_1, BlockState blockState_2, boolean boolean_1) {
        if (blockState_1.getBlock() != blockState_2.getBlock()) {
            BlockEntity blockEntity_1 = world_1.getBlockEntity(blockPos_1);
            if (blockEntity_1 instanceof Inventory) {
                ItemScatterer.spawn(world_1, blockPos_1, (Inventory)blockEntity_1);
                ItemScatterer.spawn(world_1, blockPos_1.getX(), blockPos_1.getY(), blockPos_1.getZ(), new ItemStack(oysterBreed.getOysterBlockItem()));
                world_1.updateHorizontalAdjacent(blockPos_1, this);
            }
            super.onBlockRemoved(blockState_1, world_1, blockPos_1, blockState_2, boolean_1);
        }
    }

    @Override
    public ActionResult onUse(BlockState blockState_1, World world_1, BlockPos blockPos_1, PlayerEntity playerEntity_1, Hand hand_1, BlockHitResult blockHitResult_1) {
        if (!world_1.isClient) {
            ContainerProviderRegistry.INSTANCE.openContainer(
                    oysterBreed.getContainerIdentifier(),
                    playerEntity_1, buf -> buf.writeBlockPos(blockPos_1));
        }
        return ActionResult.PASS;
    }
}
