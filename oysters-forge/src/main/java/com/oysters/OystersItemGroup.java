package com.oysters;

import com.oysters.oysters.OysterManager;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class OystersItemGroup extends ItemGroup {

	public OystersItemGroup() {
		super("oysters");
	}

	@Override
	public ItemStack createIcon() {
		return new ItemStack(OysterManager.oysterList.get(0));
	}
}
