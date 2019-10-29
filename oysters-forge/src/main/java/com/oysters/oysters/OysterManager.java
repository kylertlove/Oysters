package com.oysters.oysters;

import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntityType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class OysterManager {

	public static List<Oyster> oysterBlockList = buildOysterMap();
	public static TileEntityType<OysterTile> oysterTileTileEntityType = createTileEntityType();

	/**
	 * Build the oyster map.  eventually rip all this out and use API for someone else to add other oysters
	 */
	private static List<Oyster> buildOysterMap() {
		return Lists.newArrayList(
				new Oyster(defaultProps(), "blemished_oyster", null, "blemished_pearl", null),
				new Oyster(defaultProps(), "clean_oyster", null, "clean_pearl", null),
				new Oyster(defaultProps(), "flawless_oyster",null, "flawless_pearl", null),
				new Oyster(defaultProps(), "bone_meal_oyster", OysterTier.BLEMISHED, "bone_meal_pearl", Items.BONE_MEAL),
				new Oyster(defaultProps(), "coal_oyster", OysterTier.BLEMISHED, "coal_pearl", Items.COAL),
				new Oyster(defaultProps(), "wood_oyster", OysterTier.BLEMISHED, "wood_pearl", Items.OAK_LOG),
				new Oyster(defaultProps(), "dirt_oyster", OysterTier.BLEMISHED, "dirt_pearl", Items.DIRT),
				new Oyster(defaultProps(), "stone_oyster", OysterTier.BLEMISHED, "stone_pearl", Items.STONE),
				new Oyster(defaultProps(), "sand_oyster", OysterTier.BLEMISHED, "sand_pearl", Items.SAND),
				new Oyster(defaultProps(), "string_oyster", OysterTier.CLEAN, "string_pearl", Items.STRING),
				new Oyster(defaultProps(), "quartz_oyster", OysterTier.CLEAN, "quartz_pearl", Items.QUARTZ),
				new Oyster(defaultProps(), "iron_oyster", OysterTier.CLEAN, "iron_pearl", Items.IRON_INGOT),
				new Oyster(defaultProps(), "glowstone_oyster", OysterTier.CLEAN, "glowstone_pearl", Items.GLOWSTONE_DUST),
				new Oyster(defaultProps(), "redstone_oyster", OysterTier.CLEAN, "redstone_pearl", Items.REDSTONE),
				new Oyster(defaultProps(), "lapis_oyster", OysterTier.CLEAN, "lapis_pearl", Items.LAPIS_LAZULI),
				new Oyster(defaultProps(), "gold_oyster", OysterTier.FLAWLESS, "gold_pearl", Items.GOLD_INGOT),
				new Oyster(defaultProps(), "diamond_oyster", OysterTier.FLAWLESS, "diamond_pearl", Items.DIAMOND),
				new Oyster(defaultProps(), "emerald_oyster", OysterTier.FLAWLESS, "emerald_pearl", Items.EMERALD),
				new Oyster(defaultProps(), "ender_pearl_oyster", OysterTier.FLAWLESS, "ender_pearl_pearl", Items.ENDER_PEARL)
		);
	}

	private static Block.Properties defaultProps() {
		return Block.Properties.create(Material.ROCK);
	}

	private static TileEntityType<OysterTile> createTileEntityType() {
		return TileEntityType.Builder.create(OysterTile::new, oysterBlockList.toArray(new Oyster[0])).build(null);
	}
}
