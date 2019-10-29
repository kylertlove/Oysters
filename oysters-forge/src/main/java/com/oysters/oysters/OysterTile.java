package com.oysters.oysters;

import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;

public class OysterTile extends TileEntity implements ITickableTileEntity {

	public OysterTile() {
		super(OysterManager.oysterTileTileEntityType);
	}

	@Override
	public void tick() {

	}
}
