package com.oysters.oysters;

import com.oysters.Oysters;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.item.Item;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class Oyster extends Block implements IWaterLoggable {

	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	//private VoxelShape voxelShape = Block.createCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 8.0D, 13.0D);
	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

	private String containerName;
	private String pearlName;
	private Item resourceItem;
	private OysterTier tier;

	public Oyster(Properties props, String name, OysterTier tier, String pearlName, Item resourceItem) {
		super(props);
		this.setRegistryName(new ResourceLocation(Oysters.ID, name));
		this.pearlName = pearlName;
		this.resourceItem = resourceItem;
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
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Nullable
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new OysterTile();
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(WATERLOGGED, FACING);
	}

	public String getContainerName() {
		return containerName;
	}

	public String getPearlName() {
		return pearlName;
	}

	public Item getResourceItem() {
		return resourceItem;
	}

	public OysterTier getTier() {
		return tier;
	}
}
