package com.oysters.utils;

import com.oysters.oysters.Oyster;
import com.oysters.oysters.OysterManager;

import java.util.Optional;

public class OysterStreamUtils {

	public static Optional<Oyster> getBreedBlockFromName(String name) {
		return OysterManager.oysterBlockList.stream().filter(oyster -> oyster.getRegistryName().getPath().equalsIgnoreCase(name)).findFirst();
	}
}
