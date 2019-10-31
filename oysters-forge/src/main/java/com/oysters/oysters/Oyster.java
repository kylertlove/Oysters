package com.oysters.oysters;

import com.oysters.Oysters;
import com.oysters.items.OysterShucker;
import com.oysters.items.Pearl;
import com.oysters.utils.OysterStreamUtils;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.DebugPacketSender;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.FluidTags;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;

import javax.annotation.Nullable;
import javax.swing.text.html.BlockView;
import java.util.Optional;

public class Oyster extends Block implements IWaterLoggable {

	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	private VoxelShape voxelShape = Block.makeCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 8.0D, 13.0D);
	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

	private String pearlName;
	private Item mutationResource;
	private OysterTier tier;

	/**
	 *
	 * @param props properties with the oyster
	 * @param name resource name of oyster
	 * @param tier tier of oyster.  (Rareity)
	 * @param pearlName resource name of the pearl that the oyster produces
	 * @param mutationResource item that the oyster is mutated from
	 */
	public Oyster(Properties props, String name, OysterTier tier, String pearlName, Item mutationResource) {
		super(props);
		this.setRegistryName(new ResourceLocation(Oysters.ID, name));
		this.pearlName = pearlName;
		this.mutationResource = mutationResource;
		this.tier = tier;
		this.setDefaultState(this.getStateContainer().getBaseState()
				.with(WATERLOGGED, Boolean.TRUE)
				.with(FACING, Direction.NORTH));
	}

	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(WATERLOGGED, FACING);
	}

	@Override
	public VoxelShape getShape(BlockState blockState_1, IBlockReader blockView_1, BlockPos blockPos_1, ISelectionContext entityContext_1) {
		return voxelShape;
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
			IFluidState fluidState = context.getWorld().getFluidState(context.getPos());
			boolean isWaterLogged = fluidState.isTagged(FluidTags.WATER) && fluidState.getLevel() == 8;
			return this.getDefaultState()
					.with(WATERLOGGED, isWaterLogged)
					.with(FACING, context.getPlacementHorizontalFacing().getOpposite());
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		return worldIn.getBlockState(pos.down()).getBlock() == Blocks.SAND;
	}

	@Override
	public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
		if(worldIn.getBlockState(pos.down()).getBlock() != Blocks.SAND) {
			worldIn.destroyBlock(pos, false);
			spawnAsEntity(worldIn, pos, new ItemStack(this.asItem()));
		}
	}

	@Override
	public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		worldIn.getPendingFluidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
	}

	@Override
	public IFluidState getFluidState(BlockState state) {
		return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
	}

	@Override
	public void onPlayerDestroy(IWorld worldIn, BlockPos pos, BlockState state) {
		spawnAsEntity((World)worldIn, pos, new ItemStack(this.asItem()));
	}

	@Override
	public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		if(worldIn.isRemote()) return true;
		if(state.getBlock() instanceof Oyster) {
			if(player.getHeldItemMainhand().getItem() instanceof OysterShucker) {

			}
			return true;
		}
		return false;
	}

	public String getPearlName() {
		return pearlName;
	}

	public Item getMutationResource() {
		return mutationResource;
	}

	public OysterTier getTier() {
		return tier;
	}
}
