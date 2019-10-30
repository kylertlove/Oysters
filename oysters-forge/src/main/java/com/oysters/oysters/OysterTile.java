package com.oysters.oysters;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;

public class OysterTile extends TileEntity implements ITickableTileEntity {

	private int counter = 0;
	private int checker = 100;

	private boolean isHarvestable = false;


	public OysterTile() {
		super(OysterManager.oysterTileTileEntityType);
	}

	@Override
	public void tick() {
		if(this.getBlockState().get(BlockStateProperties.WATERLOGGED)) {
			if (counter > checker) {
				isHarvestable = true;
				counter = 0;
			} else {
				counter++;
			}
		} else {
			System.out.println("not waterlogged");
		}

	}

	public boolean isHarvestable() {
		return isHarvestable;
	}

	public void setHarvestable(boolean harvestable) {
		isHarvestable = harvestable;
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		super.write(compound);
		return compound;
	}

	@Override
	public void read(CompoundNBT compound) {
		super.read(compound);
	}
}
