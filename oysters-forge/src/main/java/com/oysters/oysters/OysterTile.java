package com.oysters.oysters;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;

public class OysterTile extends TileEntity implements ITickableTileEntity {

	public OysterTile() {
		super(OysterManager.oysterTileTileEntityType);
	}

	@Override
	public void tick() {

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
