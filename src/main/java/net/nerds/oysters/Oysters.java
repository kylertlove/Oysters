package net.nerds.oysters;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.nerds.oysters.oysters.OystersManager;

public class Oysters implements ModInitializer {

	public static String MODID = "oysters";
	public static ItemGroup oysterGroup = FabricItemGroupBuilder
			.build(new Identifier(MODID, MODID), () -> new ItemStack(Items.FISHING_ROD));

	@Override
	public void onInitialize() {
		OystersManager.init();
	}
}
