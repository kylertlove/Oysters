package net.nerds.oysters;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.nerds.oysters.Utils.OysterWorldGen;
import net.nerds.oysters.Utils.OystersConfig;
import net.nerds.oysters.blocks.OysterBlockManager;
import net.nerds.oysters.oysters.OysterBreed;
import net.nerds.oysters.oysters.OystersManager;
import net.nerds.oysters.pearls.OysterPearlManager;

public class Oysters implements ModInitializer {

	public static String MODID = "oysters";
	public static ItemGroup oysterGroup = FabricItemGroupBuilder
			.build(new Identifier(MODID, MODID), () -> new ItemStack(OysterBreed.BLEMISHED.getOysterBlockItem()));

	public static OystersConfig oystersConfig;

	@Override
	public void onInitialize() {
		oystersConfig = new OystersConfig();
		oystersConfig.loadConfigs();
		OystersManager.init();
		OysterPearlManager.init();
		OysterBlockManager.init();
		OysterWorldGen.generate();
	}

}
