package com.oysters.utils;

import com.oysters.items.Pearl;
import com.oysters.oysters.Oyster;
import com.oysters.oysters.OysterManager;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Optional;

public class OysterStreamUtils {

	public static Optional<Oyster> getBreedBlockFromName(String name) {
		return OysterManager.oysterList.stream().filter(oyster -> oyster.getRegistryName().getPath().equalsIgnoreCase(name)).findFirst();
	}

	public static Optional<Pearl> getPearlFromPearlName(String name) {
		return OysterManager.pearlList.stream().filter(item -> item.getRegistryName().getPath().equalsIgnoreCase(name)).findFirst();
	}

}
